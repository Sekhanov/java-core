package chatApplication.server;

import java.io.IOException;
import java.net.Socket;

import utility.Channel;
import utility.ChannelBase;
import utility.Message;
import utility.MessageType;

public class ClientHandler {
	private ChatServer chatServer;
	private String name;
	private Channel channel;

	public Channel getChannel() {
		return channel;
	}

	public String getName() {
		return name;
	}

	public ClientHandler(ChatServer chatServer, Socket clientSocket) {
		this.chatServer = chatServer;
		try {
			channel = ChannelBase.of(clientSocket);
			new Thread(() -> reciveMessage(), "getMessageClient" + chatServer.getClientConter()).start();
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
		if(toHandler != null) {
			toHandler.channel.sendMessage(new Message(name + ":" + strings[1], MessageType.PERSONAL_MESSAGE));
			sendSelfMessage(name + ":" + strings[1]);
		} else {
			 sendSelfMessage("Пользователя с ником " + toHandlerNick + "нет в сети");
		}
		
	}
	
	

	public void reciveMessage() {
		while (true) {
			Message message = channel.getMessage();
			if(message == null) {
				sendSelfMessage("Неправильный формат сообщения");
				continue;
			}
			switch (message.getMessageType()) {
			case AUTH_MESSAGE:
				authentification(message.getBody());
				break;
			case BROADCAST_MESSAGE:
				chatServer.broadcastMessage(name + ":" + message.getBody());
				break;
			case EXIT_MESSAGE:
				chatServer.broadcastMessage("Пользователь " + this.name + " покинул чат");
				sendSelfMessage("Вы вышли из чата. Перезапустите клиент и авторизуйтесь заново");
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
			default:
				break;

			}
		}
	}

	private void deactivateUser(String nick) {
		
		if(chatServer.getAuthentificationService().deactivateUser(nick)) {
			sendSelfMessage("Пользователь с ником " + nick + " деактивирован");
		} else {
			 sendSelfMessage("Пользователя с ником " + nick + " нет на сервере");
		}
	}

	private void addUser(String msg) {
		if(msg.split(" ").length > 1) {
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
				} else {
					sendSelfMessage("Этот пользователь уже авторизован");
				}
			} else {
				sendSelfMessage("Пользователя не сущетсвует или он деактивирован");
			}
		} else {
			sendSelfMessage("Авторизация производится следующей командой [/auth user password]");
		}
	}
}
