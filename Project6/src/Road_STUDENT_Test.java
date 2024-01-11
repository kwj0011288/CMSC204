/*
 * @author Wonjae Kim
 */
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class Road_STUDENT_Test {
	private Road road;
	private Town source;
	private Town destination;

	@Before
	public void setUp() {
		source = new Town("Seoul");
		destination = new Town("Busan");
		road = new Road(source, destination, 500, "StoB");
	}

	@Test
	public void testGetSource() {
		assertEquals(source, road.getSource());
	}

	@Test
	public void testSetSource() {
		Town sour = new Town("Daegu");
		road.setSource(sour);
		assertEquals(sour, road.getSource());
	}

	@Test
	public void testGetDestination() {
		assertEquals(destination, road.getDestination());
	}

	@Test
	public void testSetDestination() {
		Town destin = new Town("Yangyang");
		road.setDestination(destin);
		assertEquals(destin, road.getDestination());
	}

	@Test
	public void testGetWeight() {
		assertEquals(500, road.getWeight());
	}
	
	@Test
	public void testGetName() {
		assertEquals("StoB", road.getName());
	}
	
	@Test
	public void testSetName() {
		String name = "New Road";
		road.setName(name);
		assertEquals(name, road.getName());
	}
	
	@Test
	public void testContains() {
		assertTrue(road.contains(source));
		assertTrue(road.contains(destination));
		
	}
	
	@Test
	public void testToString() {
		assertEquals("StoB connects Seoul and Busan and is 500 miles long", road.toString());
	}
	@Test
	public void testCompareTo() {
		
		Road road1 = new Road(source, destination, 500, "StoB");
		Road road2 = new Road(source, destination, 500, "StoB2");
		
		assertTrue(road1.compareTo(road2) < 0);
		
	}
	@Test
	public void testEquals() {
		Road newRoad = new Road(source, destination, 500, "StoB");
		assertEquals(road, newRoad);
	}

}
