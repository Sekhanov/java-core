package utility;

import java.util.HashMap;
import java.util.Map;

public class AuthentificationService implements AuthService {

	private Map<String, Account> accounts;

	public AuthentificationService() {
		accounts = new HashMap<>();
		accounts.put("us1", new Account("user1", "pass1", "us1"));
		accounts.put("us2", new Account("user2", "pass2", "us2"));
		accounts.put("us3", new Account("user3", "pass3", "us3"));
		accounts.put("us4", new Account("user4", "pass4", "us4"));
	}

	@Override
	public String authNickByLogPass(String login, String password) {
		String result = null;
		for (Account account : accounts.values()) {
			if (account.getLogin().equals(login) && account.getPassword().equals(password) && account.isAcrive()) {
				result = account.getNick();
			}
		}
		return result;
	}

	@Override
	public boolean addOrActivateUser(String login, String password, String nick) {
		boolean result = false;
		Account account = accounts.get(nick);
		if (account == null) {
			accounts.put(nick, new Account(login, password, nick));
			System.out.println("Добавлен новый пользователь");
			result = true;
		} else {
			account.setAcrive(true);
			System.out.println("Активирован сущетвующий пользователь");
			result = false;
		}
		return result;
	}

	@Override
	public boolean activateDeactivateUser(String nick) {
		boolean result = true;
		Account account = accounts.get(nick);
		if (account != null) {
			if(!account.isAcrive()) {
				account.setAcrive(true);				
			} else {
				 account.setAcrive(false);
			}
			
		} else {
			result = false;
		}
		return result;
	}

	@Override
	public boolean changeNick(String currentNick, String newNick) {
		boolean result = true;
		Account account = accounts.get(currentNick);
		if(account != null) {
			account.setNick(newNick);
		} else {
			result = false;
		}
		return result;
	}

}
