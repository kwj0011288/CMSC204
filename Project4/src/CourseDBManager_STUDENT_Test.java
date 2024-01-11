import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/*
 *@author Wonjae Kim
 */
public class CourseDBManager_STUDENT_Test {

	CourseDBManager test;
	File input;

	@Before
	public void setUp() {
		test = new CourseDBManager();
		input = new File("testing");
	}

	@After
	public void tearDown() {
		test = null;
		input = null;
	}

	@Test
	public void testAddAndGet() {

		test.add("CMSC200", 30504, 4, "SC450", "Joey Bag-O-Donuts");
		test.add("CMSC500", 40000, 4, "SC460", "Won Jae-Kim");

		CourseDBElement first = test.get(30504);
		CourseDBElement second = test.get(40000);

		assertEquals("CMSC200", first.getCourseID());
		assertEquals(30504, first.getCRN());
		assertEquals(4, first.getNumberOfCredit());
		assertEquals("SC450", first.getRoomNumber());
		assertEquals("Joey Bag-O-Donuts", first.getName());

		assertEquals("CMSC500", second.getCourseID());
		assertEquals(40000, second.getCRN());
		assertEquals(4, second.getNumberOfCredit());
		assertEquals("SC460", second.getRoomNumber());
		assertEquals("Won Jae-Kim", second.getName());
		try {
			test.add("CMSC2600", 30104, 4, "SC400", "Apple iPhone");
		} catch (Exception e) {
			fail("This should not have caused an Exception");
		}

	}

	@Test
	public void testReadFile() throws FileNotFoundException {
		test.readFile(input);
		ArrayList<String> list = test.showAll();

		assertEquals("\nCourse:CMSC200 CRN:40000 Credits:4 Instructor:Won Jae-Kim Room:SC260", list.get(0));
		assertEquals("\nCourse:CMSC400 CRN:10000 Credits:4 Instructor:two Room:SC560", list.get(1));
		assertEquals("\nCourse:CMSC300 CRN:50000 Credits:4 Instructor:one Room:SC360", list.get(2));
		assertEquals("\nCourse:CMSC100 CRN:30504 Credits:4 Instructor:Joey Bag-O-Donuts Room:SC450", list.get(3));
		assertEquals("\nCourse:CMSC500 CRN:20000 Credits:4 Instructor:three Room:SC660", list.get(4));
	}

	@Test
	public void testShowAll() {
		test.add("CMSC100", 30504, 4, "SC450", "Joey Bag-O-Donuts");
		test.add("CMSC200", 40000, 4, "SC260", "Won Jae-Kim");
		test.add("CMSC300", 50000, 4, "SC360", "one");
		test.add("CMSC400", 10000, 4, "SC560", "two");
		test.add("CMSC500", 20000, 4, "SC660", "three");

		ArrayList<String> list = test.showAll();

		assertEquals("\nCourse:CMSC200 CRN:40000 Credits:4 Instructor:Won Jae-Kim Room:SC260", list.get(0));
		assertEquals("\nCourse:CMSC400 CRN:10000 Credits:4 Instructor:two Room:SC560", list.get(1));
		assertEquals("\nCourse:CMSC300 CRN:50000 Credits:4 Instructor:one Room:SC360", list.get(2));
		assertEquals("\nCourse:CMSC100 CRN:30504 Credits:4 Instructor:Joey Bag-O-Donuts Room:SC450", list.get(3));
		assertEquals("\nCourse:CMSC500 CRN:20000 Credits:4 Instructor:three Room:SC660", list.get(4));
		
		assertTrue(list.contains("\nCourse:CMSC100 CRN:30504 Credits:4 Instructor:Joey Bag-O-Donuts Room:SC450"));
		assertTrue(list.contains("\nCourse:CMSC200 CRN:40000 Credits:4 Instructor:Won Jae-Kim Room:SC260"));
		assertTrue(list.contains("\nCourse:CMSC300 CRN:50000 Credits:4 Instructor:one Room:SC360"));
		assertTrue(list.contains("\nCourse:CMSC400 CRN:10000 Credits:4 Instructor:two Room:SC560"));
		assertTrue(list.contains("\nCourse:CMSC500 CRN:20000 Credits:4 Instructor:three Room:SC660"));
		
	}
}
