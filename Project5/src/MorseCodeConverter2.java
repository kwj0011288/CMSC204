import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;

/*
 * @author Wonjae Kim
 * This is for extra credit
 */
public class MorseCodeConverter2 {

	private static HashMap<Character, String> EnToMorse;

	public MorseCodeConverter2() {
		EnToMorse = new HashMap<>();
		buildTree();

	}

	public static void buildTree() {
		EnToMorse.put(' ', "/");
		EnToMorse.put('a', ".-");
		EnToMorse.put('b', "-...");
		EnToMorse.put('c', "-.-.");
		EnToMorse.put('d', "-..");
		EnToMorse.put('e', ".");
		EnToMorse.put('f', "..-.");
		EnToMorse.put('g', "--.");
		EnToMorse.put('h', "....");
		EnToMorse.put('i', "..");
		EnToMorse.put('j', ".---");
		EnToMorse.put('k', "-.-");
		EnToMorse.put('l', ".-..");
		EnToMorse.put('m', "--");
		EnToMorse.put('n', "-.");
		EnToMorse.put('o', "---");
		EnToMorse.put('p', ".--.");
		EnToMorse.put('q', "--.-");
		EnToMorse.put('r', ".-.");
		EnToMorse.put('s', "...");
		EnToMorse.put('t', "-");
		EnToMorse.put('u', "..-");
		EnToMorse.put('v', "...-");
		EnToMorse.put('w', ".--");
		EnToMorse.put('x', "-..-");
		EnToMorse.put('y', "-.--");
		EnToMorse.put('z', "--..");

	}

	/*
	 * Converts a input of English into Morse code.
	 */
	public static String convertToMorseCode(String eng) {
		if (!eng.matches("^[a-zA-Z\\s]+$")) {
			throw new IllegalArgumentException("Exception");
		}

		String[] words = eng.toLowerCase().split("\\s+");
		StringBuffer bf = new StringBuffer();

		for (int i = 0; i < words.length; i++) {
			String word = words[i];
			char[] letters = word.toCharArray();
			for (int j = 0; j < letters.length; j++) {
				char ch = letters[j];
				String morse = EnToMorse.get(ch);
				if (morse != null) {
					bf.append(morse);
					// add space between letters
					if (j < letters.length - 1) {
						bf.append(" ");
					}
				}
			}
			// add the separation between words
			if (i < words.length - 1) {
				bf.append(" / ");
			}
		}
		return bf.toString().trim();
	}

	/*
	 * Converts a file of English into Morse code.
	 */
	public static String convertToMorseCode(File file) {
		try {
			Scanner sc = new Scanner(file); // get files
			StringBuffer bf = new StringBuffer();
			// if file has value, add line to the string buffer
			while (sc.hasNextLine()) {
				bf.append(sc.nextLine());
			}
			sc.close();
			// change it to string
			String en = bf.toString();
			return convertToMorseCode(en);
		} catch (FileNotFoundException e) {
			return e.getMessage();

		}
	}

}
