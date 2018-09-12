package homeworkLesson7.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ChatServer {

	private static final int SERVER_PORT = 9999;
	private ServerSocket serverSocket;	
	private Vector<ClientHandler> clientHandlers;
	private int clientConter;
	private AuthentificationService authentificationService;
	
	
	
	public AuthentificationService getAuthentificationService() {
		return authentificationService;
	}

	public ChatServer() {
		Socket clientSocket = null;
		try {
			serverSocket = new ServerSocket(SERVER_PORT);
			clientConter = 1;
			clientHandlers = new Vector<>();
			authentificationService = new AuthentificationService();
			while(true) {
				System.out.println("Server is waiting for connection...");
				clientSocket = serverSocket.accept();
				System.out.println("Client "+ clientConter + " connected");
				new ClientHandler(this, clientSocket);
				clientConter++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void broadcastMessage(String message) {
		for (ClientHandler clientHandler : clientHandlers) {
			clientHandler.sendMessage(message);
		}
	}
	
	public static void main(String... agrs) {
		new ChatServer();
	}

	public int getClientConter() {
		return clientConter;
	}
	
	public boolean isNickBusy(String nick) {
		boolean result = false;
		for (ClientHandler clientHandler : clientHandlers) {
			result |= clientHandler.getName().equals(nick);
		}
		return result;
		
	}

	public void addClient(ClientHandler clientHandler) {
		clientHandlers.add(clientHandler);
	}

	public void removeClient(ClientHandler clientHandler) {
		clientHandlers.remove(clientHandler);
	}
	
	
	public ClientHandler getClientHanderlByName(String name) {
		ClientHandler result = null;
		for (ClientHandler clientHandler : clientHandlers) {
			if(clientHandler.getName().equals(name)) result = clientHandler;
		}
		return result;
	}
	

	
}
