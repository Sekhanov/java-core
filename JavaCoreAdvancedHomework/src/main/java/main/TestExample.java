package main;

import myTests.AfterSuite;
import myTests.BeforeSuite;
import myTests.Test;

public class TestExample {
	
	private String testField;

	@BeforeSuite
	public void init() {
		this.testField = "someValueFromTestField";
		System.out.println("BeforeSuite executed");
	}
	
	@Test(priority = 5)
	public void test5() {
		System.out.println("test with priority 5 executed");
	}
	
	@Test(priority = 4)
	public void test4() {
		System.out.println("test with priority 4 executed");
	}
	
	@Test()
	public void test() {
		System.out.println("test with priority 1 executed");
	}
	
	@Test()
	public void test1() {
		System.out.println("test with priority 1 executed");
		System.out.println(testField);
	}
	
	@Test(priority = 2)
	public void test2() {
		System.out.println("test with priority 2 executed");
	}
	
	@AfterSuite
	public void destroy() {
		System.out.println("AfterSuite executed");
	}
	
}
