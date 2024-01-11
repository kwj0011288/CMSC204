package project1;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * STUDENT tests for the methods of PasswordChecker
 * 
 * @WonjaeKim
 *
 */
public class PasswordCheckerTest_STUDENT {

	String password;
	ArrayList<String> pw;

	@Before
	public void setUp() throws Exception {
		String[] answer = { "a1A#b1Bc1Cd1D", "334455BB#", "Im2Cool4U#", "george2ZZZ#", "4Sale#", "bertha22",
				"4wardMarch#", "august30", "a2cDe", "ApplesxxYYzz#", "aa11bb", "pilotProject", "myPassword",
				"myPassword2", "myPassword2#" };
		pw = new ArrayList<>();
		pw.addAll(Arrays.asList(answer));
	}

	@After
	public void tearDown() throws Exception {
		pw = null;
	}

	/**
	 * Test if the password is less than 6 characters long. This test should throw a
	 * LengthException for second case.
	 * @throws Exception 
	 */
	@Test
	public void testIsValidPasswordTooShort() throws Exception {

		try {
			boolean answer = PasswordCheckerUtility.isValidPassword("Abc123!@");
			assertTrue(answer);
		}catch (LengthException e) {
			assertTrue("Exception Thrown", false);
		}

		try {
			boolean answer = PasswordCheckerUtility.isValidPassword("Abc1");
			assertTrue(answer);
		}catch (LengthException e) {
			assertTrue("Exception Thrown", true);
		}
	}

	/**
	 * Test if the password has at least one uppercase alpha character This test
	 * should throw a NoUpperAlphaException for second case
	 * @throws InvalidSequenceException 
	 * @throws NoSpecialCharacterException 
	 * @throws NoDigitException 
	 * @throws NoLowerAlphaException 
	 * @throws LengthException 
	 */
	@Test
	public void testIsValidPasswordNoUpperAlpha() throws LengthException, NoLowerAlphaException, NoDigitException, NoSpecialCharacterException, InvalidSequenceException {

		try {
			boolean answer = PasswordCheckerUtility.isValidPassword("Abc123!@");
			assertTrue(answer);
		}catch (NoUpperAlphaException e) {
			assertTrue("Exception Thrown", false);
		}

		try {
			boolean answer = PasswordCheckerUtility.isValidPassword("abc123!@");
			assertTrue(answer);
		}catch (NoUpperAlphaException e) {
			assertTrue("Exception Thrown", true);
		}
	}

	/**
	 * Test if the password has at least one lowercase alpha character This test
	 * should throw a NoLowerAlphaException for second case
	 * @throws InvalidSequenceException 
	 * @throws NoSpecialCharacterException 
	 * @throws NoDigitException 
	 * @throws NoUpperAlphaException 
	 * @throws LengthException 
	 */
	@Test
	public void testIsValidPasswordNoLowerAlpha() throws LengthException, NoUpperAlphaException, NoDigitException, NoSpecialCharacterException, InvalidSequenceException {
		try {
			boolean answer = PasswordCheckerUtility.isValidPassword("ABC123!@");
			assertTrue(answer);
		}catch (NoLowerAlphaException e) {
			assertTrue("Exception Thrown", true);
		}

		try {
			boolean answer = PasswordCheckerUtility.isValidPassword("Abc123!@");
			assertTrue(answer);
		}catch (NoLowerAlphaException e) {
			assertTrue("Exception Thrown", false);
		}
	}

	/**
	 * Test if the password has more than 2 of the same character in sequence This
	 * test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsWeakPassword() {

		//String expectedAnswer = "The password is OK but weak - it contains fewer than 10 characters";
		try {
			boolean answer = PasswordCheckerUtility.isWeakPassword("Abc123!@159");
			assertEquals(answer, false);
		}catch (WeakPasswordException e) {
			assertTrue("Exception Thrown", true);
		}

		try {
			boolean answer = PasswordCheckerUtility.isWeakPassword("Abc123!@");
			assertEquals(answer, true);
		}catch (WeakPasswordException e) {
			assertTrue("Exception Thrown", true);
		}
	}

	/**
	 * Test if the password has more than 2 of the same character in sequence This
	 * test should throw a InvalidSequenceException for second case
	 * @throws InvalidSequenceException 
	 * @throws NoSpecialCharacterException 
	 * @throws NoDigitException 
	 * @throws NoUpperAlphaException 
	 * @throws LengthException 
	 * @throws NoLowerAlphaException 
	 */
	@Test
	public void testIsValidPasswordInvalidSequence() throws LengthException, NoUpperAlphaException, NoDigitException, NoSpecialCharacterException, InvalidSequenceException, NoLowerAlphaException {
		try {
			boolean answer = PasswordCheckerUtility.isValidPassword("george2ZZZ@");
			assertTrue(answer);
		}catch (InvalidSequenceException e) {
			assertTrue("Exception Thrown", true);
		}

		try {
			boolean answer = PasswordCheckerUtility.isValidPassword("Abc123!@");
			assertTrue(answer);
		}catch (InvalidSequenceException e) {
			assertTrue("Exception Thrown", false);
		}
	}
	/**
	 * Test if the password has at least one digit One test should throw a
	 * NoDigitException
	 * @throws InvalidSequenceException 
	 * @throws NoSpecialCharacterException 
	 * @throws NoLowerAlphaException 
	 * @throws NoUpperAlphaException 
	 * @throws LengthException 
	 */
	@Test
	public void testIsValidPasswordNoDigit() throws LengthException, NoUpperAlphaException, NoLowerAlphaException, NoSpecialCharacterException, InvalidSequenceException {
		try {
			boolean answer = PasswordCheckerUtility.isValidPassword("georgeZz3@");
			assertTrue(answer);
		}catch (NoDigitException e) {
			assertTrue("Exception Thrown", true);
		}

		try {
			boolean answer = PasswordCheckerUtility.isValidPassword("Abc123!@");
			assertTrue(answer);
		}catch (NoDigitException e) {
			assertTrue("Exception Thrown", false);
		}
	}

	/**
	 * Test correct passwords This test should not throw an exception
	 * @throws InvalidSequenceException 
	 * @throws NoSpecialCharacterException 
	 * @throws NoDigitException 
	 * @throws NoLowerAlphaException 
	 * @throws NoUpperAlphaException 
	 * @throws LengthException 
	 */
	@Test
	public void testIsValidPasswordSuccessful() throws LengthException, NoUpperAlphaException, NoLowerAlphaException, NoDigitException, NoSpecialCharacterException, InvalidSequenceException {

		boolean answer1 = PasswordCheckerUtility.isValidPassword("georgeZz3@");
		boolean answer2 = PasswordCheckerUtility.isValidPassword("Apple2%z");
		boolean answer3 = PasswordCheckerUtility.isValidPassword("Rladnjswo@13");
		boolean answer4 = PasswordCheckerUtility.isValidPassword("rnalsTn*1d");

		try {
			assertEquals(true, answer1);
			assertEquals(true, answer2);
			assertEquals(true, answer3);
			assertEquals(true, answer4);
		} catch (Exception e) {
			System.out.print(e.getMessage());
			assertTrue("Exception Thrown", false);
		}
	}

	/**
	 * Test the invalidPasswords method Check the results of the ArrayList of
	 * Strings returned by the validPasswords method
	 * @throws InvalidSequenceException 
	 * @throws NoSpecialCharacterException 
	 * @throws NoDigitException 
	 * @throws NoLowerAlphaException 
	 * @throws NoUpperAlphaException 
	 * @throws LengthException 
	 */
	@Test
	public void testInvalidPasswords() throws LengthException, NoUpperAlphaException, NoLowerAlphaException, NoDigitException, NoSpecialCharacterException, InvalidSequenceException {

		try {
			ArrayList<String> passwords = PasswordCheckerUtility.getInvalidPasswords(pw);
			for (String password : passwords) {
				boolean answer = PasswordCheckerUtility.isValidPassword(password);
				assertTrue(answer);
			}
		} catch (LengthException | NoUpperAlphaException | NoLowerAlphaException | NoDigitException | NoSpecialCharacterException | InvalidSequenceException e) {
			assertTrue("Exception Thrown", true);
		}
	}
}
