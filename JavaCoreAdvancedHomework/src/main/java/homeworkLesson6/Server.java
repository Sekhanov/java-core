package homeworkLesson6;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

	private ServerSocket serverSocket;
	private Socket socket;
	private Scanner clientScanner;
	private Scanner serverScanner;
	private PrintWriter clientPrintWriter;
	private PrintWriter serverPrintWriter;

	public Server() {

		try {
			serverSocket = new ServerSocket(9999);
			System.out.println("Server is running. Waiting for connection...");
			socket = serverSocket.accept();
			System.out.println("client connected. You can write message to client");
			clientScanner = new Scanner(socket.getInputStream());
			clientPrintWriter = new PrintWriter(socket.getOutputStream());
			serverScanner = new Scanner(System.in);
			serverPrintWriter = new PrintWriter(System.out);
			new Thread(() -> translateServerMessage()).start();
			translateClientMessage();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



	public static void main(String[] args) {
		new Server();
	}

	private void translateServerMessage() {
		while (true) {
			String message = serverScanner.nextLine();
			clientPrintWriter.println("server: " + message);
			clientPrintWriter.flush();

		}
	}
	
	private void translateClientMessage() {
		while(true) {
			if(clientScanner.hasNextLine()) {
				String message = clientScanner.nextLine();
				serverPrintWriter.println(message);
				serverPrintWriter.flush();
			}

		}
		
	}

}
