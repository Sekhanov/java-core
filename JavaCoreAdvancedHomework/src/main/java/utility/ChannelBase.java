package utility;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class ChannelBase implements Channel {

	private Scanner scanner;
	private PrintWriter printWriter;

	private ChannelBase(Scanner scanner, PrintWriter printWriter) {
		super();
		this.scanner = scanner;
		this.printWriter = printWriter;
	}

	public static ChannelBase of(Socket socket) throws IOException {
		Scanner scanner = new Scanner(socket.getInputStream());
		PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
		return new ChannelBase(scanner, printWriter);
	}

	@Override
	public void sendMessage(Message message) {		
		printWriter.println(message.toString());

	}	


	@Override
	public Message getMessage() {
		Message result = null;
		String string = scanner.nextLine();
		if(!string.startsWith("/")) result = new Message(string, MessageType.BROADCAST_MESSAGE);
		if(string.startsWith("/exit")) result = new Message("", MessageType.EXIT_MESSAGE);
		if(string.startsWith("/w ")) result = new Message(string.substring(3).trim(), MessageType.PERSONAL_MESSAGE);
		if(string.startsWith("/auth ")) result = new Message(string.substring(6).trim(), MessageType.AUTH_MESSAGE);
		if(string.startsWith("/add ")) result = new Message(string.substring(5).trim(), MessageType.ADD_USER_MESSAGE);
		if(string.startsWith("/del ")) result = new Message(string.substring(5).trim(), MessageType.DEACTIVATE_USER_MESSAGE);
		if(string.trim().isEmpty()) result = null;
		return result;
	}

}
