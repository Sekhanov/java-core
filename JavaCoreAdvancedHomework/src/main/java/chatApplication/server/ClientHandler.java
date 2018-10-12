package chatApplication.server;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import lombok.extern.slf4j.Slf4j;
import utility.Channel;
import utility.ChannelBase;
import utility.Message;
import utility.MessageType;

@Slf4j
public class ClientHandler {
	private ChatServer chatServer;
	private String name = null;
	private Channel channel;
	private int clientNumber;
	public Channel getChannel() {
		return channel;
	}

	public String getName() {
		return name;
	}

	public ClientHandler(ChatServer chatServer, Socket clientSocket, int clientNumber) {
		this.chatServer = chatServer;
		this.clientNumber = clientNumber;
		try {
			channel = ChannelBase.of(clientSocket);
			chatServer.execute(this::reciveMessage);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void sendSelfMessage(String msg) {
		channel.sendMessage(new Message(msg, MessageType.PERSONAL_MESSAGE));
	}

	private void sendPersonalMessage(String msg) {
		String[] strings = msg.split(" ");
		String toHandlerNick = strings[0];
		ClientHandler toHandler = chatServer.getClientHanderlByName(toHandlerNick);
		if (toHandler != null) {
			toHandler.channel.sendMessage(new Message(name + ":" + strings[1], MessageType.PERSONAL_MESSAGE));
			sendSelfMessage(name + ":" + strings[1]);
			log.info("Клиент №{} с ником {} отправил личное сообщение пользователю с ником {}", clientNumber, name, toHandler);
		} else {
			sendSelfMessage("Пользователя с ником " + toHandlerNick + "нет в сети");
		}

	}

	public void reciveMessage() {
		while (true) {
			if (name == null) {
				ExecutorService executorService = Executors.newFixedThreadPool(1);
				Future<Message> future = executorService.submit(() -> channel.getMessage());
				Message authMessage = null;
					try {
						authMessage = future.get(120, TimeUnit.SECONDS);
						authentification(authMessage.getBody());
						if(name != null) {
							String string = chatServer.restoreMessageHistory(5);
							sendSelfMessage(string);
						}
						executorService.shutdown();
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (ExecutionException e) {
						e.printStackTrace();
					} catch (TimeoutException e) {
						sendSelfMessage("Максимальное время авторизации в чате 120 секунд. Соединение разорвано. Перезагрузите клиент.");
						log.error("Клиент №{} не успел авторизоваться за 120 сек.", clientNumber);
						break;
					}
			} else {				
				Message message = channel.getMessage();
				if (message.getBody().trim().isEmpty()) {
					continue;
				}
				switch (message.getMessageType()) {
				case BROADCAST_MESSAGE:
					String msg = name + ":" + message.getBody();
					chatServer.broadcastMessage(msg);
					chatServer.logMessage(msg);	
					log.info("Клиент №{} с ником {} отправил общее сообщение", clientNumber, name);
					break;
				case EXIT_MESSAGE:
					chatServer.broadcastMessage("Пользователь " + this.name + " покинул чат");
					sendSelfMessage("Вы вышли из чата. Перезапустите клиент и авторизуйтесь заново");
					log.info("Клиент №{} с ником {} вышел из чата", clientNumber, name);
					chatServer.removeClient(this);
					return;
				case PERSONAL_MESSAGE:
					sendPersonalMessage(message.getBody());
					break;
				case ADD_USER_MESSAGE:
					addUser(message.getBody());
					break;
				case DEACTIVATE_USER_MESSAGE:
					deactivateUser(message.getBody());
				case CHANGE_NICK_MESSAGE:
					chatServer.getAuthentificationService().changeNick(name, message.getBody());
					chatServer.broadcastMessage("Пользователь сменил ник с " + name + " на " + message.getBody());
					log.info("Клиент №{} с ником {} сменил ник на {}", message.getBody());
					name = message.getBody();
				default:
					break;
				}
			}
		}
	}

	private void deactivateUser(String nick) {

		if (chatServer.getAuthentificationService().activateDeactivateUser(nick)) {
			sendSelfMessage("Пользователь с ником " + nick + " деактивирован");
		} else {
			sendSelfMessage("Пользователя с ником " + nick + " нет на сервере");
		}
	}

	private void addUser(String msg) {
		if (msg.split(" ").length > 1) {
			String[] strings = msg.split(" ");
			chatServer.getAuthentificationService().addOrActivateUser(strings[0], strings[1], strings[2]);
			sendSelfMessage("Пользователь с данными " + msg + "успешно добавлен");
		} else {
			sendSelfMessage("Неправильный синтаксис команды add - [/add user password nick]");
		}
	}

	private void authentification(String msgBody) {
		if (msgBody.split(" ").length > 1) {
			String[] messageParts = msgBody.split(" ");
			String nick = chatServer.getAuthentificationService().authNickByLogPass(messageParts[0], messageParts[1]);
			if (nick != null) {
				if (!chatServer.isNickBusy(nick)) {
					this.name = nick;
					chatServer.addClient(this);
					chatServer.broadcastMessage("В чат вошел пользователь под именем " + name);
					log.info("В чат вошел клиент №{} под именем {}", clientNumber, name);
				} else {
					sendSelfMessage("Этот пользователь уже авторизован");
					log.warn("Клиент №{} авторизуется с ником  {}, который уже в сети уже авторизован", clientNumber, nick);
				}
			} else {
				sendSelfMessage("Пользователя не сущетсвует или он деактивирован");
				log.warn("Клиент №{} авторизуется с ником{}, который не сущетсвует или деактивирован " ,clientNumber, nick);
			}
		} else {
			sendSelfMessage("Авторизация производится следующей командой [/auth user password]");
			log.warn("Килент №{}. Неправильный формат авторизации:{} ", clientNumber ,msgBody);
		}
	}
}
