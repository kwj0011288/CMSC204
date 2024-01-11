import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.*;

import org.junit.Before;
import org.junit.Test;

/*
 * @author Wonjae Kim
 */
public class Town_STUDENT_Test {
	private Town town;

	@Before
	public void setUp() {
		town = new Town("Gangnam");

	}

	@Test
	public void testGetName() {
		assertEquals("Gangnam", town.getName());
	}

	@Test
	public void testSetnNme() {
		town.setName("Seoul");
		assertEquals("Seoul", town.getName());
	}

	@Test
	public void testGetAdjacentTown() {
		ArrayList<Town> arr = town.getAdjacentTown();
		Town adj = new Town("Korea");
		arr.add(adj);
		assertEquals(1, arr.size());
		assertTrue(arr.contains(adj));

	}

	@Test
	public void testSetAdjacentTown() {
		ArrayList<Town> arr = new ArrayList<>();
		Town adj = new Town("Korea");

		arr.add(adj);
		town.setAdjacentTown(arr);

		assertEquals(arr, town.getAdjacentTown());
	}

	@Test
	public void testCompareTo() {

		
		Town town2 = new Town("Busan");
		assertTrue(town.compareTo(town2) > 0);

		Town town3 = new Town("Gangnam");
		assertEquals(0, town.compareTo(town3));
	}

	@Test
	public void testToString() {
		Town town2 = new Town("Busan");
		town.getAdjacentTown().add(town2);
		String expectedResult = "Busan,";
		assertEquals(expectedResult, town.toString());
	}

	@Test
	public void testHashCode() {
		Town town2 = new Town("Gangnam");
		assertEquals(town.hashCode(), town2.hashCode());
	}

	@Test
	public void testEquals() {
		Town town2 = new Town("Gangnam");
		assertTrue(town.equals(town2));

		assertFalse(town.equals(new Town("Busan")));
	}

}
