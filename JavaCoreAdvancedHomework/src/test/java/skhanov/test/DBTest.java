package skhanov.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Ignore;
import org.junit.Test;

import utility.DBAuthService;

public class DBTest {

	private DBAuthService dbAuthService;
	
	public DBTest() {
		this.dbAuthService = new DBAuthService();
	}

	@Test @Ignore
	public void testAuthDB() {		
		String expected = "us1";
		String actual = dbAuthService.authNickByLogPass("user1", "pass1");
		assertEquals(expected, actual);
	}
	
	
	@Test 
	public void testIsUser() {
		boolean condition = dbAuthService.addOrActivateUser("user12", "pass10", "us10");
		assertTrue(condition);
	}
	
	@Test @Ignore
	public void testIsRowInDB() {
		boolean condition = false;
		try {
			condition = dbAuthService.isRow("nickname", "us1");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertTrue(condition);
	}
	
	@Test @Ignore
	public void testIsActive() {
		boolean condition = dbAuthService.isActive("us2");
		assertTrue(condition);
	}
	
	@Test @Ignore
	public void testActivateDeactivate() {
		boolean condition = dbAuthService.activateDeactivateUser("us2");
		assertTrue(condition);
	}
	
	@Test @Ignore
	public void testChaneNickName() {
		boolean condition = dbAuthService.changeNick("us1", "qwer");
		assertTrue(condition);		
	}

}
