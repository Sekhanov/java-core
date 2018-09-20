package utility;

public interface Channel {
	
	void sendMessage(Message message);
	
	Message getMessage();
}
