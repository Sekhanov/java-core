package skhanov.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.Test;

import utility.DBAuthService;

public class DBTest {

	private DBAuthService dbAuthService;
	
	public DBTest() {
		this.dbAuthService = new DBAuthService();
	}
	
	@Test
	public void testAuthDB() {		
		String expected = "us1";
		String actual = dbAuthService.authNickByLogPass("user1", "pass1");
		assertEquals(expected, actual);
	}
	
	
//	@Test
//	public void testIsertUser() {
//		boolean condition = dbAuthService.addOrActivateUser("user10", "pass10", "us10");
//		assertTrue(condition);
//	}
	
	@Test
	public void testIsRowInDB() {
		boolean condition = false;
		try {
			condition = dbAuthService.isRow("login", "user1");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertTrue(condition);
	}
	
	@Test
	public void testIsActive() {
		boolean condition = dbAuthService.isActive("us1");
		assertTrue(condition);
	}
	
	@Test
	public void testActivateDeactivate() {
		boolean condition = dbAuthService.activateDeactivateUser("us1");
		assertTrue(condition);
	}

}
