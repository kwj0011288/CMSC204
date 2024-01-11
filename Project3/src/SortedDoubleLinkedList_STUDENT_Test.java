
/*
 * @author Wonjae Kim
 */
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Comparator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SortedDoubleLinkedList_STUDENT_Test {
	SortedDoubleLinkedList<String> stringList;
	SortedDoubleLinkedList<Integer> integerList;

	Comparator<String> ComparatorS;
	Comparator<Integer> ComparatorI;

	@Before
	public void setUp() throws Exception {
		ComparatorS = new StringComparator();
		ComparatorI = new IntegerComparator();

		stringList = new SortedDoubleLinkedList<>(ComparatorS);
		integerList = new SortedDoubleLinkedList<>(ComparatorI);

	}

	@After
	public void tearDown() {
		stringList = null;
		integerList = null;
	}

	@Test
	public void addTest() {
		stringList.add("Apple");
		stringList.add("iPhone");
		stringList.add("iPad");
		stringList.add("Macbook");

		assertEquals(4, stringList.getSize());
		assertEquals("Apple", stringList.getFirst());
		assertEquals("iPhone", stringList.getLast());

		integerList.add(11);
		integerList.add(28);
		integerList.add(12);
		integerList.add(29);

		assertEquals(4, integerList.getSize());
		assertEquals(Integer.valueOf(11), integerList.getFirst());
		assertEquals(Integer.valueOf(29), integerList.getLast());

	}

	@Test
	public void addToFrontTest() {
		try {
			stringList.addToFront("Orange");
			assertTrue("UnsupportedOperationException not thrown", false);

		} catch (UnsupportedOperationException e) {
			System.out.println("Exception occur");
		}

		try {
			integerList.addToFront(0);
			assertTrue("UnsupportedOperationException not thrown", false);
		} catch (UnsupportedOperationException e) {
			System.out.println("Exception occur");
		}
	}

	@Test
	public void addToEnd() {
		try {
			stringList.addToEnd("Orange");
			assertTrue("UnsupportedOperationException not thrown", false);

		} catch (UnsupportedOperationException e) {
			System.out.println("Exception occur");
		}

		try {
			integerList.addToEnd(0);
			assertTrue("UnsupportedOperationException not thrown", false);
		} catch (UnsupportedOperationException e) {
			System.out.println("Exception occur");
		}
	}

	@Test
	public void remove() {
		stringList.add("VisionPro");
		stringList.add("MacMini");
		stringList.add("Pineapple");
		stringList.remove("VisionPro", ComparatorS);
		assertEquals("MacMini", stringList.getFirst());
		assertEquals("Pineapple", stringList.getLast());

		integerList.add(1);
		integerList.add(2);
		integerList.add(3);
		integerList.remove(3, ComparatorI);
		assertEquals(Integer.valueOf(1), integerList.getFirst());
		assertEquals(Integer.valueOf(2), integerList.getLast());

	}

	public class StringComparator implements Comparator<String> {
		public int compare(String a, String b) {
			return a.compareTo(b);
		}
	}

	public class IntegerComparator implements Comparator<Integer> {
		public int compare(Integer a, Integer b) {
			return Integer.compare(a, b);
		}
	}
}
