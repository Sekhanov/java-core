package utility;

public class Message {

	private String body;
	private MessageType messageType;
	
	public Message(String body, MessageType messageType) {
		this.body = body;
		this.messageType = messageType;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public MessageType getMessageType() {
		return messageType;
	}
	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		if (body == null) {
			if (other.body != null)
				return false;
		} else if (!body.equals(other.body))
			return false;
		if (messageType != other.messageType)
			return false;
		return true;
	}
	@Override
	public String toString() {	
		String result = null;		
		switch(messageType) {
		case BROADCAST_MESSAGE:
		case PERSONAL_MESSAGE:
			result = body;
			break;
		case AUTH_MESSAGE:
		case EXIT_MESSAGE:
		case ADD_USER_MESSAGE:
			result = messageType.toString();
			break;
		default:
			System.out.println("Тип сообщения не удалось определить");
			break;
		}
		return result;
	}
	
	
	
	
	
	
}
