package utility;

public interface AuthService {

	/**
	 * Авторизация по логину и паролю.
	 * @param login
	 * @param password
	 * @return никнейм
	 */
	String authNickByLogPass(String login, String password);

	
	/**
	 * Метод добавляет новую учетную запись или активирует существующую
	 * @param login
	 * @param password
	 * @param nick
	 * @return true при добавлениии пользователя, false при активации
	 */
	boolean addOrActivateUser(String login, String password, String nick);

	/**
	 * Активация или Деактивация пользователя в зависимости от состояния аккаутна. При удачной деактивации возвращает true
	 * @param nick
	 * @return при удачном выполнении операции true
	 */
	boolean activateDeactivateUser(String nick);
	
	
	/**
	 * Смена ника в учетной записи
	 * @param currentNick
	 * @param newNick
	 * @return
	 */
	boolean changeNick(String currentNick, String newNick);
}