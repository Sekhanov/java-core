package chatApplication.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import utility.Channel;
import utility.ChannelBase;
import utility.Message;
import utility.MessageType;

public class ClientController implements Controller {
	
	private static final String SERVER_HOST = "localhost";
	private static final int SERVER_PORT = 9999;
//	private Scanner clientScanner;
//	private PrintWriter clientPrintWriter;
	private Socket clientSocket;
	private Channel channel;
	public ClientController() {
			try {
				clientSocket = new Socket(SERVER_HOST, SERVER_PORT);
				this.channel = ChannelBase.of(clientSocket);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	@Override
	public void sendMessage(String message) {
		channel.sendMessage(Message.plainMessage(message));
	}


	@Override
	public String reciveMessage() {
		String result;
		result = channel.getMessage().getBody();
		return result;
	}
}
