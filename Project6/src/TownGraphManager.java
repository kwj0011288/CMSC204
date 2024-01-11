/*
 * @author Wonjae Kim
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Set;

public class TownGraphManager implements TownGraphManagerInterface {
	private Graph graph; 

	public TownGraphManager() {
		graph = new Graph();
	}

	/**
	 * Adds a road with 2 towns and a road name
	 * 
	 * @param town1    name of town 1 (lastname, firstname)
	 * @param town2    name of town 2 (lastname, firstname)
	 * @param roadName name of road
	 * @return true if the road was added successfully
	 */
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		Town source = getTown(town1);
		Town destination = getTown(town2);

		return graph.addEdge(source, destination, weight, roadName) != null;
	}

	/**
	 * Returns the name of the road that both towns are connected through
	 * 
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return name of road if town 1 and town2 are in the same road, returns null
	 *         if not
	 */
	public String getRoad(String town1, String town2) {
		Town source = getTown(town1);
		Town destination = getTown(town2);

		if (source != null && destination != null) {
			Road road = graph.getEdge(source, destination);
			if (road != null) {
				return road.getName();
			}
		}
		return null;
	}

	/**
	 * Adds a town to the graph
	 * 
	 * @param v the town's name (lastname, firstname)
	 * @return true if the town was successfully added, false if not
	 */
	public boolean addTown(String v) {

		return graph.addVertex(new Town(v));

	}

	/**
	 * Gets a town with a given name
	 * 
	 * @param name the town's name
	 * @return the Town specified by the name, or null if town does not exist
	 */
	public Town getTown(String name) {
		for (Town town : graph.vertexSet()) {
			if (town.getName().equals(name)) {
				return town;
			}
		}
		return null;
	}

	/**
	 * Determines if a town is already in the graph
	 * 
	 * @param v the town's name
	 * @return true if the town is in the graph, false if not
	 */
	public boolean containsTown(String v) {
		return getTown(v) != null;

	}

	/**
	 * Determines if a road is in the graph
	 * 
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return true if the road is in the graph, false if not
	 */
	public boolean containsRoadConnection(String town1, String town2) {
		Town source = getTown(town1);
		Town destination = getTown(town2);

		return source != null && destination != null && graph.containsEdge(source, destination);

	}

	/**
	 * Creates an arraylist of all road titles in sorted order by road name
	 * 
	 * @return an arraylist of all road titles in sorted order by road name
	 */
	public ArrayList<String> allRoads() {
		ArrayList<String> roadNames = new ArrayList<>();
		for (Road road : graph.edgeSet()) {
			roadNames.add(road.getName());
		}

		roadNames.sort(new Comparator<String>() {
			@Override
			public int compare(String a, String b) {
				return a.compareTo(b);
			}
		});

		return roadNames;
	}

	/**
	 * Deletes a road from the graph
	 * 
	 * @param town1    name of town 1 (lastname, firstname)
	 * @param town2    name of town 2 (lastname, firstname)
	 * @param roadName the road name
	 * @return true if the road was successfully deleted, false if not
	 */
	public boolean deleteRoadConnection(String town1, String town2, String roadName) {
	    Town source = getTown(town1);
	    Town destination = getTown(town2);

	    if (source != null && destination != null) {
	        Road road = graph.getEdge(source, destination);
	        if (road != null && road.getName().equals(roadName)) {
	            int weight = road.getWeight();
	            return graph.removeEdge(source, destination, weight, roadName) != null;
	        }
	    }
	    return false;
	}


	/**
	 * Deletes a town from the graph
	 * 
	 * @param v name of town (lastname, firstname)
	 * @return true if the town was successfully deleted, false if not
	 */
	public boolean deleteTown(String v) {
		Town town = getTown(v);
		if (town != null) {
			return graph.removeVertex(town);
		}
		return false;

	}

	/**
	 * Creates an arraylist of all towns in alphabetical order (last name, first
	 * name)
	 * 
	 * @return an arraylist of all towns in alphabetical order (last name, first
	 *         name)
	 */
	public ArrayList<String> allTowns() {
		ArrayList<String> townNames = new ArrayList<>();
		for (Town town : graph.vertexSet()) {
			townNames.add(town.getName());
		}

		townNames.sort(new Comparator<String>() {
			@Override
			public int compare(String a, String b) {
				return a.compareTo(b);
			}
		});

		return townNames;
	}

	/**
	 * Returns the shortest path from town 1 to town 2
	 * 
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return an Arraylist of roads connecting the two towns together, null if the
	 *         towns have no path to connect them.
	 */
	@Override
	public ArrayList<String> getPath(String town1, String town2) {

		Town source = getTown(town1);
		Town destination = getTown(town2);

		if (source == null || destination == null) {
			return null;
		}

		graph.dijkstraShortestPath(source);

		ArrayList<String> path = new ArrayList<>();
		Town current = destination;

		while (current != null) {
			Town previous = graph.getPreviousTown().get(current);
			if (previous != null) {
				Road road = graph.getEdge(previous, current);
				path.add(0, previous.getName() + " via " + road.getName() + " to " + current.getName() + " "
						+ road.getWeight() + " mi");
				current = previous;
			} else {
				break;
			}
		}

		return path.isEmpty() ? null : path;

	}
	//read file
	public void populateTownGraph(File file) throws FileNotFoundException, IOException {
	    try (Scanner sc = new Scanner(file)) {
	        String line;
	        while (sc.hasNextLine()) {
	            line = sc.nextLine();
	            String[] splitLine = line.split("[,;]");

	            if (splitLine.length >= 4) {
	                String roadName = splitLine[0];
	                int weight = Integer.parseInt(splitLine[1]);
	                String town1Name = splitLine[2];
	                String town2Name = splitLine[3];

	                Town town1 = getTown(town1Name);
	                if (town1 == null) {
	                    town1 = new Town(town1Name);
	                    graph.addVertex(town1);
	                }

	                Town town2 = getTown(town2Name);
	                if (town2 == null) {
	                    town2 = new Town(town2Name);
	                    graph.addVertex(town2);
	                }

	                graph.addEdge(town1, town2, weight, roadName);
	            }
	        }
	    }
	}

	

}
