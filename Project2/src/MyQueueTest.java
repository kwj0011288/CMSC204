/**
 * @author Wonjae Kim
 */
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MyQueueTest {
	public MyQueue<String> stringQ;
	public String a = "a", b = "b", c = "c", d = "d", e = "e", f = "f";
	public ArrayList<String> fill = new ArrayList<String>();

	// STUDENT: student tests will use the doubleQ
	public MyQueue<Double> doubleQ;
	public double one = 1.1, two = 2.2, three = 3.3, four = 4.4, five = 5.5, six = 6.6, seven = 7.7;
	// STUDENT: add variables as needed for your student tests

	@Before
	public void setUp() throws Exception {
		stringQ = new MyQueue<String>(5);
		stringQ.enqueue(a);
		stringQ.enqueue(b);
		stringQ.enqueue(c);
		doubleQ = new MyQueue<Double>(6);
		doubleQ.enqueue(one);
		doubleQ.enqueue(two);
		doubleQ.enqueue(three);
		doubleQ.enqueue(four);

		// STUDENT: add setup for doubleQ for student tests
	}

	@After
	public void tearDown() throws Exception {
		stringQ = null;
		doubleQ = null;
	}

	@Test
	public void testIsEmpty() throws QueueUnderflowException {
		assertEquals(false, stringQ.isEmpty());
		stringQ.dequeue();
		stringQ.dequeue();
		stringQ.dequeue();
		assertEquals(true, stringQ.isEmpty());
	}

	@Test
	public void testDequeue() {
		try {
			assertEquals(a, stringQ.dequeue());
			assertEquals(b, stringQ.dequeue());
			assertEquals(c, stringQ.dequeue());
			// Queue is empty, next statement should cause QueueUnderFlowException
			stringQ.dequeue();
			assertTrue("This should have caused an QueueUnderflowException", false);
		} catch (QueueUnderflowException e) {
			assertTrue("This should have caused an QueueUnderflowException", true);
		} catch (Exception e) {
			assertTrue("This should have caused an QueueUnderflowException", false);
		}
	}

	@Test
	public void testDequeueStudent() {
		try {
			assertEquals(one, doubleQ.dequeue(), .0001);
			assertEquals(two, doubleQ.dequeue(), .0001);
			assertEquals(three, doubleQ.dequeue(), .0001);
			assertEquals(four, doubleQ.dequeue(), .0001);
			// Queue is empty, next statement should cause QueueUnderFlowException
			doubleQ.dequeue();
			assertTrue("This should have caused an QueueUnderflowException", false);
		} catch (QueueUnderflowException e) {
			System.out.println(e);
		}
	}

	@Test
	public void testSize() throws QueueOverflowException, QueueUnderflowException {
		assertEquals(3, stringQ.size());
		stringQ.enqueue(d);
		assertEquals(4, stringQ.size());
		stringQ.dequeue();
		stringQ.dequeue();
		assertEquals(2, stringQ.size());
	}

	@Test
	public void testEnqueue() {
		try {
			assertEquals(3, stringQ.size());
			assertEquals(true, stringQ.enqueue(d));
			assertEquals(4, stringQ.size());
			assertEquals(true, stringQ.enqueue(e));
			assertEquals(5, stringQ.size());
			// Queue is full, next statement should cause QueueOverFlowException
			stringQ.enqueue(f);
			assertTrue("This should have caused an QueueOverflowException", false);
		} catch (QueueOverflowException e) {
			assertTrue("This should have caused an QueueOverflowException", true);
		} catch (Exception e) {
			assertTrue("This should have caused an QueueOverflowException", false);
		}
	}

	@Test
	public void testEnqueueStudent() {
		try {
			assertEquals(4, doubleQ.size());
			assertEquals(true, doubleQ.enqueue(five));
			assertEquals(5, doubleQ.size());
			assertEquals(true, doubleQ.enqueue(six));
			assertEquals(6, doubleQ.size());
			// Queue is full, next statement should cause QueueOverFlowException
			doubleQ.enqueue(seven);
			assertTrue("This should have caused an QueueOverflowException", false);
		} catch (QueueOverflowException e) {
			System.out.println(e);
		}
	}

	@Test
	public void testIsFull() throws QueueOverflowException {
		assertEquals(false, stringQ.isFull());
		stringQ.enqueue(d);
		stringQ.enqueue(e);
		assertEquals(true, stringQ.isFull());
	}

	@Test
	public void testToString() throws QueueOverflowException {
		assertEquals("abc", stringQ.toString());
		stringQ.enqueue(d);
		assertEquals("abcd", stringQ.toString());
		stringQ.enqueue(e);
		assertEquals("abcde", stringQ.toString());
	}

	@Test
	public void testToStringStudent() throws QueueOverflowException {
		assertEquals("1.12.23.34.4", doubleQ.toString());
		assertEquals("1.1,2.2,3.3,4.4", doubleQ.toString(","));
		doubleQ.enqueue(five);
		assertEquals("1.12.23.34.45.5", doubleQ.toString());
		assertEquals("1.1,2.2,3.3,4.4,5.5", doubleQ.toString(","));
		doubleQ.enqueue(six);
		assertEquals("1.12.23.34.45.56.6", doubleQ.toString());
		assertEquals("1.1,2.2,3.3,4.4,5.5,6.6", doubleQ.toString(","));
	}

	@Test
	public void testToStringDelimiter() throws QueueOverflowException {
		assertEquals("a%b%c", stringQ.toString("%"));
		stringQ.enqueue(d);
		assertEquals("a&b&c&d", stringQ.toString("&"));
		stringQ.enqueue(e);
		assertEquals("a/b/c/d/e", stringQ.toString("/"));
	}

	@Test
	public void testFill() throws QueueOverflowException, QueueUnderflowException {
		fill.add("apple");
		fill.add("banana");
		fill.add("carrot");
		// start with an empty queue
		stringQ = new MyQueue<String>(5);
		// fill with an ArrayList
		stringQ.fill(fill);
		assertEquals(3, stringQ.size());
		assertEquals("apple", stringQ.dequeue());
		assertEquals("banana", stringQ.dequeue());
		assertEquals("carrot", stringQ.dequeue());
	}

}
