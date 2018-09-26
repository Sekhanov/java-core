package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBAuthService implements AuthService {

	private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/chat_auth?serverTimezone=UTC";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "ckjybr";

	private Connection connection;

	public DBAuthService() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.connection = DriverManager.getConnection(CONNECTION_URL, DB_USER, DB_PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String authNickByLogPass(String login, String password) {
		String result = null;
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("SELECT * FROM auth_data where login = ? and password = ?");
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				resultSet.previous();
				while (resultSet.next()) {
					result = resultSet.getString("nickName");
				}
			} else {
				System.out.println("Пользователя с приведенными данными не существует");
			}
			resultSet.close();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

		}
		return result;
	}

	@Override
	public boolean addOrActivateUser(String login, String password, String nick) {
		boolean result = true;
		try {
			if (!isRow("login", login) && !isRow("nickname", nick)) {
				PreparedStatement preparedStatement = connection
						.prepareStatement("INSERT INTO chat_auth.auth_data(login, password, nickname) values(?, ?, ?)");
				preparedStatement.setString(1, login);
				preparedStatement.setString(2, password);
				preparedStatement.setString(3, nick);
				preparedStatement.executeUpdate();
				preparedStatement.close();
			} else {
				if(isRow("nickname", nick)) {
					activateDeactivateUser(nick);					
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * проверка записи в базе данных
	 * @param col - наименование колонки
	 * @param val - значение колонки
	 * @return есть ряд - true
	 * @throws SQLException
	 */
	public boolean isRow(String col, String val) throws SQLException {
		int rowCount = 0;
		String query = String.format("select count(*) from auth_data where %s = '%s'", col, val);
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(query);
		while (resultSet.next()) {
			rowCount = resultSet.getInt("count(*)");
		}
		resultSet.close();
		statement.close();
		return rowCount > 0 ? true : false;

	}

	@Override
	public boolean activateDeactivateUser(String nick) {
		boolean result = false;
		try {
			if(isRow("nickname", nick)) {
				PreparedStatement preparedStatement = connection.prepareStatement("update auth_data set isActive = ? where nickName = ?");
				preparedStatement.setString(2, nick);
				if(!isActive(nick)) {
					preparedStatement.setInt(1, 1);
					System.out.println("Пользователь " + nick + " активатован");
				} else {
					preparedStatement.setInt(1, 0);
					System.out.println("Пользователь " + nick + " деактиватован");
				}
				preparedStatement.executeUpdate();
				result = true;
			} else {
				System.out.println("Записи с ником " + nick + "нет в базе данных");
				result = false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(!isActive(nick)) {
			
		}
		return result;
	}
	
	/**
	 * Проверка активации аккаунта по нику
	 * @param nick
	 * @return
	 */
	public boolean isActive(String nick) {
		boolean result = false;
	
		try {
			if(isRow("nickName", nick)) {
				PreparedStatement preparedStatement = connection.prepareStatement("select isActive from auth_data where nickname = ?");
				preparedStatement.setString(1, nick);
				ResultSet resultSet = preparedStatement.executeQuery();
				while(resultSet.next()) {
					result = resultSet.getBoolean("isActive");
				}
				resultSet.close();
				preparedStatement.close();
			} else {
				System.out.println("Записи с ником " + nick + "нет в базе данных");
				//TODO some logic
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean changeNick(String currentNick, String newNick) {
		boolean result = true;
		try {
			if(isRow("nickName", currentNick)) {
				PreparedStatement preparedStatement = connection.prepareStatement("update auth_data set nickName = ? where nickname = ?");
				preparedStatement.setString(1, newNick);
				preparedStatement.setString(2, currentNick);
				preparedStatement.executeUpdate();
				preparedStatement.close();
			} else {
				System.out.println("Записи с ником " + currentNick + " не существует");
				result = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}	

}
