package chatApplication.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import utility.AuthService;
import utility.DBAuthService;
import utility.FileMessageLogger;
import utility.Message;
import utility.MessageLogger;
import utility.MessageType;

public class ChatServer {

	private static final int SERVER_PORT = 9999;
	private ServerSocket serverSocket;
	private Map<String, ClientHandler> clientHandlers;
	private int clientConter;
	private AuthService authentificationService;
	private MessageLogger messageLogger;
	private ExecutorService executorService;

	public static void main(String... agrs) {
		new ChatServer();
	}

	public ExecutorService getExecutorService() {
		return executorService;
	}

	public AuthService getAuthentificationService() {
		return authentificationService;
	}

	public ChatServer() {
		Socket clientSocket = null;
		try {
			serverSocket = new ServerSocket(SERVER_PORT);
			clientConter = 1;
			clientHandlers = new HashMap<>();
//			authentificationService = new AuthentificationService();
			authentificationService = new DBAuthService();
			messageLogger = new FileMessageLogger();
			executorService = Executors.newCachedThreadPool();
			while (true) {
				System.out.println("Server is waiting for connection...");
				clientSocket = serverSocket.accept();
				System.out.println("Client " + clientConter + " connected");
				new ClientHandler(this, clientSocket);
				clientConter++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void broadcastMessage(String msg) {
		clientHandlers.forEach((k, v) -> v.getChannel().sendMessage(new Message(msg, MessageType.BROADCAST_MESSAGE)));
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
	
	public void logMessage(String msg) {
		messageLogger.archiveMessage(msg);
	}
	
	public String restoreMessageHistory(int count) {
		String result = "";
		for(String s: messageLogger.restoreMessages(count)) {
			result += s + "\n";
		}
		return result;
	}
	
	public void execute(Runnable runnable) {
		executorService.execute(runnable);
	}

}
