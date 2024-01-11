/*
 * @author Wonjae Kim
 */
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TownGraphManager_STUDENT_Test {
	private TownGraphManager graphManager;

	@Before
	public void setUp() {
		graphManager = new TownGraphManager();
	}

	@Test
	public void testAddRoad() {
		graphManager.addTown("TownA");
		graphManager.addTown("TownB");
		assertTrue(graphManager.addRoad("TownA", "TownB", 100, "AtoB"));

	}

	@Test
	public void testGetRoad() {
		graphManager.addTown("TownA");
		graphManager.addTown("TownB");
		assertTrue(graphManager.addRoad("TownA", "TownB", 100, "AtoB"));
		assertEquals("AtoB", graphManager.getRoad("TownA", "TownB"));
	}

	@Test
	public void testAddTown() {
		assertTrue(graphManager.addTown("A"));
		assertFalse(graphManager.addTown("A"));
	}

	@Test
	public void containsTown() {
		graphManager.addTown("TownA");
		assertTrue(graphManager.containsTown("TownA"));
		graphManager.addTown("TownB");
		assertFalse(graphManager.containsTown("TownC"));
		graphManager.addTown("TownC");
		assertTrue(graphManager.containsTown("TownC"));

	}

	@Test
	public void testcontainsRoadConnection() {
		graphManager.addTown("TownA");
		graphManager.addTown("TownB");
		graphManager.addRoad("TownA", "TownB", 100, "AtoB");

		assertTrue(graphManager.containsRoadConnection("TownA", "TownB"));
		assertFalse(graphManager.containsRoadConnection("TownA", "??"));

	}

	@Test
	public void testAllRoads() {
		graphManager.addTown("TownA");
		graphManager.addTown("TownB");
		graphManager.addRoad("TownA", "TownB", 100, "AtoB");
		ArrayList<String> expected = new ArrayList<>();
		expected.add("AtoB");
		assertEquals(expected, graphManager.allRoads());

	}

	@Test
	public void testDeleteRoadConnection() {
		graphManager.addTown("TownA");
		graphManager.addTown("TownB");
		graphManager.addRoad("TownA", "TownB", 100, "AtoB");

		assertTrue(graphManager.containsRoadConnection("TownA", "TownB"));
		assertTrue(graphManager.deleteRoadConnection("TownA", "TownB", "AtoB"));
		assertFalse(graphManager.containsRoadConnection("TownA", "TownB"));
	}

	@Test
	public void testDeleteTown() {
		graphManager.addTown("TownA");
		graphManager.addTown("TownB");
		graphManager.addTown("TownC");
		assertTrue(graphManager.deleteTown("TownA"));
		assertFalse(graphManager.deleteTown("TownA"));

	}

	@Test
	public void testAllTown() {
		graphManager.addTown("TownA");
		graphManager.addTown("TownB");
		graphManager.addTown("TownC");
		graphManager.addTown("TownD");
		graphManager.addTown("TownE");

		ArrayList<String> expected = new ArrayList<>();
		expected.add("TownA");
		expected.add("TownB");
		expected.add("TownC");
		expected.add("TownD");
		expected.add("TownE");

		assertEquals(expected, graphManager.allTowns());

	}

	@Test
	public void testGetPath() {
		graphManager.addTown("TownA");
		graphManager.addTown("TownB");
		graphManager.addTown("TownC");
		graphManager.addRoad("TownA", "TownB", 500, "AtoB");
		graphManager.addRoad("TownB", "TownC", 350, "BtoC");
		ArrayList<String> expectedPath = new ArrayList<>();
		expectedPath.add("TownA via AtoB to TownB 500 mi");
		expectedPath.add("TownB via BtoC to TownC 350 mi");

		assertEquals(expectedPath, graphManager.getPath("TownA", "TownC"));

	}

	@Test
	public void testPopulateTownGraph() throws FileNotFoundException, IOException {
		File file = new File("test.txt");
		graphManager.populateTownGraph(file);

		assertTrue(graphManager.containsTown("Frederick"));
		assertTrue(graphManager.containsTown("Clarksburg"));
		assertTrue(graphManager.containsRoadConnection("Frederick", "Clarksburg"));

	}

}
