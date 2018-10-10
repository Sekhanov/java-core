package skhanov.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import coreProfHwLesson6.ArrayUtils;

public class ArrayTests {

	private ArrayUtils arrayUtils;

	@Before
	public void Init() {
		arrayUtils = new ArrayUtils();
	}

	@Test
	public void testNewArrayFromFour1() {
		int[] actual = { 5, 6, 7, 4, 1, 2, 1, 4, 1, 3, 5 };
		int[] expected = { 1, 3, 5 };
		assertArrayEquals(expected, arrayUtils.newArrayFormFour(actual));
	}
	
	@Test
	public void testNewArrayFromFour2() {
		int[] actual = { 5, 6, 4 };
		int[] expected = {};
		assertArrayEquals(expected, arrayUtils.newArrayFormFour(actual));
	}

	@Test(expected = RuntimeException.class)
	public void testNewArrayFromFourException() throws RuntimeException {
		int[] actual = { 1, 3, 5 };
		arrayUtils.newArrayFormFour(actual);
	}
	
	@Test
	public void testCheckEqualNumbers() {
		int[] arr1 = {4,1,4,1,4};
		assertFalse(arrayUtils.checkEqualNumbers(arr1));
	}
	
	@Test
	public void testCheckEqualNumbers2() {
		int[] arr1 = {1,4,4,1};
		assertTrue(arrayUtils.checkEqualNumbers(arr1));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCheckEqualNumbersException() throws IllegalArgumentException {
		int[] arr1 = {1,4,4,6};
		assertTrue(arrayUtils.checkEqualNumbers(arr1));
	}
}
