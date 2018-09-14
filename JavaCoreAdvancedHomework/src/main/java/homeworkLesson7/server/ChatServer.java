package homeworkLesson7.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;



public class ChatServer {

	private static final int SERVER_PORT = 9999;
	private ServerSocket serverSocket;	
	private Map<String, ClientHandler> clientHandlers;
	private int clientConter;
	private AuthService authentificationService;
	
	
	
	public AuthService getAuthentificationService() {
		return authentificationService;
	}

	public ChatServer() {
		Socket clientSocket = null;
		try {
			serverSocket = new ServerSocket(SERVER_PORT);
			clientConter = 1;
			clientHandlers = new HashMap<>();
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
//		for(Map.Entry<String, ClientHandler> entry : clientHandlers.entrySet()) {
//			entry.getValue().sendMessage(message);			
//		}
		clientHandlers.forEach((k, v) -> v.sendMessage(message));
	}
	
	public static void main(String... agrs) {
		new ChatServer();
	}

	public int getClientConter() {
		return clientConter;
	}
	
	public boolean isNickBusy(String nick) {
		return clientHandlers.containsKey(nick);
		
	}

	public void addClient(ClientHandler clientHandler) {
		clientHandlers.put(clientHandler.getName(), clientHandler);
	}

	public void removeClient(ClientHandler clientHandler) {
		clientHandlers.remove(clientHandler.getName());
	}
	
	
	public ClientHandler getClientHanderlByName(String name) {
		return clientHandlers.get(name);
	}
	

	
}
