package chatApplication.server;

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
	public Account addOrActivateUser(String login, String password, String nick) {
		Account account = accounts.get(nick);
		if (account == null) {
			accounts.put(nick, new Account(login, password, nick));
			System.out.println("Добавлен новый пользователь");
		} else {
			account.setAcrive(true);
			System.out.println("Активирован сущетвующий пользователь");
		}
		return account;
	}

	@Override
	public boolean deactivateUser(String nick) {
		boolean result = true;
		Account account = accounts.get(nick);
		if (account != null) {
			account.setAcrive(false);
		} else {
			result = false;
		}
		return result;
	}

}
