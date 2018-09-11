package homeworkLesson7.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler {

	private ChatServer chatServer;
	private Scanner clientScanner;
	private PrintWriter clientPrintWriter;
	private String name;

	public String getName() {
		return name;
	}

	public ClientHandler(ChatServer chatServer, Socket clientSocket) {
		this.chatServer = chatServer;
		try {
			clientScanner = new Scanner(clientSocket.getInputStream());
			clientPrintWriter = new PrintWriter(clientSocket.getOutputStream());

			new Thread(() -> reciveMessage(), "getMessageClient" + chatServer.getClientConter()).start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void sendMessage(String message) {
		
		clientPrintWriter.println(message);
		clientPrintWriter.flush();
	}

	public void reciveMessage() {
		sendMessage("Введите авторизационные данные [/auth login password]");
		authentification();
		processMessage();
	}

	private void processMessage() {
		while(true) {
			String message = clientScanner.nextLine();
			if(message.startsWith("/exit")) {
				sendMessage(name + " покинул чат");
				chatServer.removeClient(this);
				break;
			} 
			chatServer.broadcastMessage(name + ": " + message);
		}
	}



	private void authentification() {
		while (true) {
			String message = clientScanner.nextLine();
			if (message.startsWith("/auth")) {
				String[] messageParts = message.split(" ");
				String nick = chatServer.getAuthentificationService().getNickByLogPass(messageParts[1],
						messageParts[2]);
				if (nick != null) {
					if(!chatServer.isNickBusy(nick)) {
						this.name = nick;
						chatServer.addClient(this);
						chatServer.broadcastMessage("В чат вошел пользователь под именем " + name);
						sendMessage("Для выхода из чата введите команду /exit");
						break;
					} else {
						sendMessage("Этот пользователь уже авторизован");
					}
				} else {
					sendMessage("Такого пользователя не существует");
				}
			} else {
				sendMessage("Для входа в чат необходимо авторизоваться. [/auth login password]");
			}
		}
	}
}
