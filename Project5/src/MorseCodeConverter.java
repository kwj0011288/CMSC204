import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * @author Wonjae Kim
 */
public class MorseCodeConverter {
	private MorseCodeTree tree;

	public MorseCodeConverter() {
		tree = new MorseCodeTree();
		tree.buildTree();
	}

	/*
	 * returns a string with all the data in the tree in LNR order with an space in
	 * between them
	 */
	public static String printTree() {
		MorseCodeConverter convert = new MorseCodeConverter();
		ArrayList<String> list = convert.tree.toArrayList();
		StringBuffer answer = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {

			if (list.get(i).isEmpty()) {
				answer.append(" "); // add space to the output
			} else {
				answer.append(list.get(i));
				// check if there are more characters in the list
				if (i < list.size() - 1) {
					answer.append(" "); // add space to separate the characters
				}
			}
		}
		return answer.toString().trim();// remove spaces

	}

	/*
	 * Converts Morse code into English. Each letter is delimited by a space (‘ ‘).
	 * Each word is delimited by a ‘/’.
	 */
	public static String convertToEnglish(String code) {
		MorseCodeConverter convert = new MorseCodeConverter();
		String[] data = code.trim().split("/"); // separated by slash
		StringBuffer bf = new StringBuffer();

		for (String element : data) {
			String[] letters = element.trim().split(" "); // morsecode separate by space
			for (String letter : letters) {
				String en = convert.tree.fetch(letter); // fetch english letter
				if (en != null) {
					bf.append(en);
				}
			}
			bf.append(" ");
		}
		return bf.toString().trim();

	}

	/*
	 * Converts a file of Morse code into English Each letter is delimited by a
	 * space (‘ ‘). Each word is delimited by a ‘/’.
	 */
	public static String convertToEnglish(File codeFile) throws FileNotFoundException {
		try {
			Scanner sc = new Scanner(codeFile); // get files
			StringBuffer bf = new StringBuffer();
			// if file has value, add line to the string buffer
			while (sc.hasNextLine()) {
				bf.append(sc.nextLine());
			}
			sc.close();
			// change it to string
			String code = bf.toString();
			return convertToEnglish(code);
		} catch (FileNotFoundException e) {
			return e.getMessage();
		}
	}
}
