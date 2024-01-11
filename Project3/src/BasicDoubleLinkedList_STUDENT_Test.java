
/*
 * @author Wonjae Kim
 */
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Comparator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BasicDoubleLinkedList_STUDENT_Test {
	BasicDoubleLinkedList<String> linkedString;
	BasicDoubleLinkedList<Character> linkedChar;
	BasicDoubleLinkedList<Double> linkedDouble;

	Comparator<String> comparatorS;
	Comparator<Character> comparatorC;
	Comparator<Double> comparatorD;

	@Before
	public void setUp() throws Exception {
		linkedString = new BasicDoubleLinkedList<String>();
		linkedString.addToEnd("Apple");
		linkedString.addToEnd("Watch");
		linkedString.addToEnd("iPad");

		comparatorS = new StringComparator();

		linkedChar = new BasicDoubleLinkedList<Character>();
		linkedChar.addToEnd('a');
		linkedChar.addToEnd('b');
		linkedChar.addToEnd('c');
		linkedChar.addToEnd('d');
		comparatorC = new CharacterComparator();

		linkedDouble = new BasicDoubleLinkedList<Double>();
		linkedDouble.addToEnd(1.1);
		linkedDouble.addToEnd(2.2);
		linkedDouble.addToEnd(3.3);
		linkedDouble.addToEnd(4.4);
		linkedDouble.addToEnd(5.5);

		comparatorD = new DoubleComparator();

	}

	@After
	public void tearDown() throws Exception {
		linkedString = null;
		linkedChar = null;
		linkedDouble = null;

	}

	@Test
	public void getSizeTest() {
		assertEquals(3, linkedString.getSize());
		assertEquals(4, linkedChar.getSize());
		assertEquals(5, linkedDouble.getSize());

	}

	@Test
	public void addToEndTest() {
		linkedString.addToEnd("iPhone");
		assertEquals("iPhone", linkedString.getLast());
		linkedChar.addToEnd('e');
		assertEquals(Character.valueOf('e'), linkedChar.getLast());
		linkedDouble.addToEnd(6.6);
		assertEquals(Double.valueOf(6.6), linkedDouble.getLast());

	}

	@Test
	public void addtoFrontTest() {
		linkedString.addToFront("iPhone");
		assertEquals("iPhone", linkedString.getFirst());
		linkedChar.addToFront('e');
		assertEquals(Character.valueOf('e'), linkedChar.getFirst());
		linkedDouble.addToFront(0.0);
		assertEquals(Double.valueOf(0.0), linkedDouble.getFirst());

	}

	@Test
	public void getFirstTest() {
		assertEquals("Apple", linkedString.getFirst());
		assertEquals(Character.valueOf('a'), linkedChar.getFirst());
		assertEquals(Double.valueOf(1.1), linkedDouble.getFirst());
	}

	@Test
	public void getLastTest() {
		assertEquals("iPad", linkedString.getLast());
		assertEquals(Character.valueOf('d'), linkedChar.getLast());
		assertEquals(Double.valueOf(5.5), linkedDouble.getLast());
	}

	@Test
	public void toArrayListTest() {
		assertEquals("[Apple, Watch, iPad]", linkedString.toArrayList().toString());
		assertEquals("[a, b, c, d]", linkedChar.toArrayList().toString());
		assertEquals("[1.1, 2.2, 3.3, 4.4, 5.5]", linkedDouble.toArrayList().toString());

	}

	@Test
	public void retrieveFirstTest() {
		assertEquals("Apple", linkedString.retrieveFirstElement());
		assertEquals(Character.valueOf('a'), linkedChar.retrieveFirstElement());
		assertEquals(Double.valueOf(1.1), linkedDouble.retrieveFirstElement());

	}

	@Test
	public void retrieveLastTest() {
		assertEquals("iPad", linkedString.retrieveLastElement());
		assertEquals(Character.valueOf('d'), linkedChar.retrieveLastElement());
		assertEquals(Double.valueOf(5.5), linkedDouble.retrieveLastElement());

	}

	@Test
	public void removeTest() {
		Comparator<String> stringComparator = new StringComparator();
		BasicDoubleLinkedList<String>.Node<String> removedStringNode = linkedString.remove("Watch", stringComparator);

		assertEquals("Watch", removedStringNode.getData());
		assertEquals(2, linkedString.getSize());
		assertEquals("Apple", linkedString.getFirst());
		assertEquals("iPad", linkedString.getLast());

		Comparator<Character> charComparator = new CharacterComparator();
		BasicDoubleLinkedList<Character>.Node<Character> removedCharNode = linkedChar.remove('b', charComparator);
		assertEquals(Character.valueOf('b'), removedCharNode.getData());
		assertEquals(3, linkedChar.getSize());
		assertEquals(Character.valueOf('a'), linkedChar.getFirst());
		assertEquals(Character.valueOf('d'), linkedChar.getLast());

		Comparator<Double> doubleComparator = new DoubleComparator();
		BasicDoubleLinkedList<Double>.Node<Double> removedDoubleNode = linkedDouble.remove(3.3, doubleComparator);
		assertEquals(Double.valueOf(3.3), removedDoubleNode.getData());
		assertEquals(4, linkedDouble.getSize());
		assertEquals(Double.valueOf(1.1), linkedDouble.getFirst());
		assertEquals(Double.valueOf(5.5), linkedDouble.getLast());

	}

	public class StringComparator implements Comparator<String> {
		public int compare(String a, String b) {
			return a.compareTo(b);
		}
	}

	public class CharacterComparator implements Comparator<Character> {
		public int compare(Character a, Character b) {
			return Character.compare(a, b);
		}
	}

	public class DoubleComparator implements Comparator<Double> {
		public int compare(Double a, Double b) {
			return Double.compare(a, b);
		}
	}

}
