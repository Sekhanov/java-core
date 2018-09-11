package homeworkLesson7.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler {

	private Socket clientSocket;
	private ChatServer chatServer;
	private Scanner cientScanner;
	private PrintWriter clientPrintWriter;
//	private String nick;
	
	
	public ClientHandler(ChatServer chatServer, Socket clientSocket)  {
		this.chatServer = chatServer;
		try {			
			cientScanner = new Scanner(clientSocket.getInputStream());
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
		while(true) {
			String message = cientScanner.nextLine();
//			if(message.startsWith("/auth")) {
//				
//			}
			chatServer.broadcastMessage(message);
		}
	}
}
