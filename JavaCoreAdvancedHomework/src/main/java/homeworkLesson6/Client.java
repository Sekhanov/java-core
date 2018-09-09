package homeworkLesson6;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	
	private static final String SERVER_NAME = "localhost";
	private static final int SERVER_PORT = 9999;
	
	private Socket socket;
	private Scanner clientScanner;
	private Scanner serverScanner;
	private PrintWriter clientPrintWriter;
	private PrintWriter serverPrintWriter;
	
	public Client() {
		try {
			socket = new Socket(SERVER_NAME, SERVER_PORT);
			clientScanner = new Scanner(System.in);
			clientPrintWriter = new PrintWriter(System.out);
			serverScanner = new Scanner(socket.getInputStream());
			serverPrintWriter = new PrintWriter(socket.getOutputStream());
			new Thread(() -> translateServerMessage(), "translateServerMessage").start();
			translateClientMessage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	

	public static void main(String[] args) {
		new Client();

	}
	
	private void translateServerMessage() {
		while (true) {
			String message = serverScanner.nextLine();
			clientPrintWriter.println(message);
			clientPrintWriter.flush();

		}
	}
	
	private void translateClientMessage() {
		while(true) {
			String message = clientScanner.nextLine();
			serverPrintWriter.println("client: " + message);
			serverPrintWriter.flush();
		}
		
	}

}
