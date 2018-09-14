package homeworkLesson7.server;

public interface AuthService {

	String authNickByLogPass(String login, String password);

	Account addOrActivateUser(String login, String password, String nick);

	boolean deactivateUser(String nick);

}