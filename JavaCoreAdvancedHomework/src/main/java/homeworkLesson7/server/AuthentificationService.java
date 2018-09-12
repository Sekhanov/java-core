package homeworkLesson7.server;

import java.util.ArrayList;
import java.util.List;

public class AuthentificationService {
	
	private List<Account> accounts; 
	

	private class Account {
		private String login;
		private String password;
		private String nick;
		
		public Account(String login, String password, String nick) {
			this.login = login;
			this.password = password;
			this.nick = nick;
		}
	}
	
	public AuthentificationService() {
		accounts = new ArrayList<>();
		accounts.add(new Account("user1", "pass1", "us1"));
		accounts.add(new Account("user2", "pass2", "us2"));
		accounts.add(new Account("user3", "pass3", "us3"));
		accounts.add(new Account("user4", "pass4", "us4"));
	}
	
	public String getNickByLogPass(String login, String password) {
		String result = null;
		for (Account account : accounts) {
			if(account.login.equals(login) && account.password.equals(password)) result = account.nick;
		}
		return result;
	}
	

	
}
