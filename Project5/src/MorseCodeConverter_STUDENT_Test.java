import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.io.FileNotFoundException;

/*
 * @author Wonjae Kim
 */
public class MorseCodeConverter_STUDENT_Test {
	private MorseCodeTree morseCodeTree;

	@Before
	public void setUp() {
		morseCodeTree = new MorseCodeTree();
		morseCodeTree.buildTree();

	}

	@Test
	public void testMorseCodeTree() {
		assertEquals("a", morseCodeTree.fetch(".-"));
		assertEquals("p", morseCodeTree.fetch(".--."));
		assertEquals("l", morseCodeTree.fetch(".-.."));
		assertEquals("e", morseCodeTree.fetch("."));
	}

	@Test
	public void testConvertToEnglishString1() {
		String converter1 = MorseCodeConverter.convertToEnglish(".--- .- ...- .- / .. ... / ..-. ..- -.");
		assertEquals("java is fun", converter1);
	}

	@Test
	public void testConvertToEnglishString2() {
		String converter2 = MorseCodeConverter.convertToEnglish(
				"-.-. .. - -.-- / --- ..-. / ... - .- .-. / - .... . .-. . / .. ... / ... --- / -- ..- -.-. ....");
		assertEquals("city of star there is so much", converter2);
	}

	@Test
	public void testConvertToEnglishFile1() {
		File file = new File("src/firstTest.txt");
		try {
			assertEquals("the love of money is the root of all evil", MorseCodeConverter.convertToEnglish(file));
		} catch (FileNotFoundException e) {
			assertTrue("Exception thrown", false);
		}
	}

	@Test
	public void testConvertToEnglishFile2() {
		File file = new File("src/secondTest.txt");
		try {
			assertEquals("the truth will set you free", MorseCodeConverter.convertToEnglish(file));
		} catch (FileNotFoundException e) {
			assertTrue("Exception thrown", false);
		}
	}

	/*
	 * FROM HERE, CONVERT ENGLISH TO MORSECODE
	 */
	@Test
	public void testConvertToMorse1() {
		MorseCodeConverter2 converter = new MorseCodeConverter2();
		String englishText = "hello world";
		String morseCode1 = converter.convertToMorseCode(englishText);
		// System.out.println(morseCode1);

		String converter1 = ".... . .-.. .-.. --- / .-- --- .-. .-.. -..";
		assertEquals(morseCode1, converter1);

	}

	@Test
	public void testConvertToMorse2() {
		MorseCodeConverter2 converter = new MorseCodeConverter2();
		String englishText = "the love of money is the root of all evil";
		String morseCode2 = converter.convertToMorseCode(englishText);
		// System.out.println(morseCode2);

		String converter2 = "- .... . / .-.. --- ...- . / --- ..-. / -- --- -. . -.-- / .. ... / - .... . / .-. --- --- - / --- ..-. / .- .-.. .-.. / . ...- .. .-..";
		assertEquals(morseCode2, converter2);

	}

	@Test
	public void testConvertToMorseFile1() {
		MorseCodeConverter2 converter = new MorseCodeConverter2();
		File file = new File("src/EntoMorse1.txt");
		try {
			assertEquals(
					"- .... . / .-.. --- ...- . / --- ..-. / -- --- -. . -.-- / .. ... / - .... . / .-. --- --- - / --- ..-. / .- .-.. .-.. / . ...- .. .-..",
					converter.convertToMorseCode(file));
		} catch (Exception e) {
			assertTrue("Exception thrown", false);
		}
	}
	
	@Test
	public void testConvertToMorseFile2() {
		MorseCodeConverter2 converter = new MorseCodeConverter2();
		File file = new File("src/EntoMorse2.txt");
		try {
			assertEquals(
					"- .... . / - .-. ..- - .... / .-- .. .-.. .-.. / ... . - / -.-- --- ..- / ..-. .-. . .",
					converter.convertToMorseCode(file));
		} catch (Exception e) {
			assertTrue("Exception thrown", false);
		}
	}

}
