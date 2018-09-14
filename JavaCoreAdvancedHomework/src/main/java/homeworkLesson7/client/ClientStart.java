package homeworkLesson7.client;

public class ClientStart {

	public static void main(String[] args) {
		ClientView clientView = new ClientView();
		clientView.init(new ClientController());		
	}

}
