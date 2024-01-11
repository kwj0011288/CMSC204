package project1;
import java.util.ArrayList;
/**
 * @author Wonjae Kim
 */
public class PasswordCheckerUtility {


	public PasswordCheckerUtility() {
	}

	public static void comparePasswords(String password, String passwordConfirm) throws UnmatchedException{
		if (!password.equals(passwordConfirm)) {
			throw new UnmatchedException("Passwords do not match");
		}
	}

	public static boolean comparePasswordsWithReturn(String password, String passwordConfirm) {
		if (password.equals(passwordConfirm)) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isValidLength(String password) throws LengthException{

		if (password.length() < 6) {
			throw new LengthException ("The password must be at least 6 characters long");
		}
		return true;
	}

	public static boolean hasUpperAlpha(String password) throws NoUpperAlphaException {

		for(int i = 0; i < password.length(); i++) {
			char c = password.charAt(i);
			if (Character.isUpperCase(c)) {
				return true;
			}
		}
		throw new NoUpperAlphaException ("Password doesn’t contain an uppercase alpha character");
	}

	public static boolean hasLowerAlpha​(String password) throws NoLowerAlphaException {

		for(int i = 0; i < password.length(); i++) {
			char c = password.charAt(i);
			if (Character.isLowerCase(c)) {
				return true;
			}
		}
		throw new NoLowerAlphaException ("The password must contain at least one lowercase alphabetic character");
	}

	public static boolean hasDigit​(String password) throws NoDigitException {

		for(int i = 0; i < password.length(); i++) {
			char c = password.charAt(i);
			if (Character.isDigit(c)) {
				return true;
			}
		}
		throw new NoDigitException ("The password must contain at least one digit");
	}

	public static boolean hasSpecialChar​(String password) throws NoSpecialCharacterException {
		String special = ".*[^a-zA-Z0-9].*";
		if (password.matches(special)) {
			return true;
		} else {
			throw new NoSpecialCharacterException ("The password must contain at least one special character");
		}

	}

	public static boolean NoSameCharInSequence​(String password) throws InvalidSequenceException {
		char[] pw = password.toCharArray();
		for(int i = 0; i < pw.length; i++) {
			int counter = 0;
			for(int j = 0; j < pw.length; j++) {
				if (pw[i] == pw[j] && i != j) {
					counter++;
				}
				if (counter >=2) {
					throw new InvalidSequenceException ("Password contains more than 2 of the same character in sequence");
				}
			}
		}
		return true;
	}

	public static boolean isValidPassword(String password) throws LengthException, NoUpperAlphaException,
	NoLowerAlphaException, NoDigitException, NoSpecialCharacterException, InvalidSequenceException {

		if (!isValidLength(password)) {
			throw new LengthException ("The password must be at least 6 characters long");
		} else if (!hasUpperAlpha(password)) {
			throw new NoUpperAlphaException("no upper case");
		} else if (!hasLowerAlpha​(password)) {
			throw new NoLowerAlphaException("no lower case");
		} else if (!hasDigit​(password)) {
			throw new NoDigitException("no digit");
		}else if (!hasSpecialChar​(password)) {
			throw new NoSpecialCharacterException("no special char");
		} else if (!NoSameCharInSequence​(password)) {
			throw new InvalidSequenceException("sequence");
		} else {
			return true;
		}
	}

	public static boolean hasBetweenSixAndNineChars​(String password) {
		if (password.length() >= 6 && password.length() <=9) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isWeakPassword(String password) throws WeakPasswordException {

		if (!hasBetweenSixAndNineChars​(password) && (password.length() <= 10)) {
			return false;
		}
		throw new WeakPasswordException("The password is OK but weak - it contains fewer than 10 characters");
	}

	public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords) {

		ArrayList<String> invalidPassword = new ArrayList<>();

		for(String password : passwords) {
			try {
				isValidPassword(password);
			} catch (Exception e) {
				invalidPassword.add(password + " " + e.getMessage());
			}
		}
		return invalidPassword;
	}
}
