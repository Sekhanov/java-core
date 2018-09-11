package homeworkLesson7.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class ChatServer {

	private static final int SERVER_PORT = 9999;
	private ServerSocket serverSocket;	
	private Vector<ClientHandler> clientHandlers;
	private int clientConter;
	
	
	
	public ChatServer() {
		Socket clientSocket = null;
		try {
			serverSocket = new ServerSocket(SERVER_PORT);
			clientConter = 1;
			clientHandlers = new Vector<>();
			while(true) {
				System.out.println("Server is waiting for connection...");
				clientSocket = serverSocket.accept();
				System.out.println("Client "+ clientConter + " connected");
				clientHandlers.add(new ClientHandler(this, clientSocket));
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
	
	
	
	
}
