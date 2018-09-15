package chatApplication.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientController implements Controller {
	
	private static final String SERVER_HOST = "localhost";
	private static final int SERVER_PORT = 9999;
	private Scanner clientScanner;
	private PrintWriter clientPrintWriter;
	private Socket clientSocket;
	
	public ClientController() {
		try {
			clientSocket = new Socket(SERVER_HOST, SERVER_PORT);
			clientPrintWriter = new PrintWriter(clientSocket.getOutputStream());
			clientScanner = new Scanner(clientSocket.getInputStream());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void sendMessage(String message) {
		clientPrintWriter.println(message);
		clientPrintWriter.flush();
	}


	@Override
	public String reciveMessage() {
		String result;
		result = clientScanner.nextLine();
		return result;
	}
}
