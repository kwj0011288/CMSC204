/*
 * @author Wonjae Kim
 */

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Graph implements GraphInterface<Town, Road> {

	private Map<Town, ArrayList<Town>> adjacencyList;
	private Set<Road> roads;
	private Map<Town, Town> previousTown;
	private Map<Town, Integer> shortestPath;

	public Graph() {
		adjacencyList = new HashMap<>();
		roads = new HashSet<>();
		setPreviousTown(new HashMap<>());
		shortestPath = new HashMap<>();

	}

	/**
	 * Returns an edgRoad connecting sourcRoad vertex to target vertex if such
	 * vertices and such edgRoad exist in this graph. OtherwisRoad returns null. If
	 * any of thRoad specified vertices is null returns null
	 *
	 * In undirected graphs, thRoad returned edgRoad may havRoad its sourcRoad and
	 * target vertices in thRoad oppositRoad order.
	 *
	 * @param sourceVertex      sourcRoad vertex of thRoad edge.
	 * @param destinationVertex target vertex of thRoad edge.
	 *
	 * @return an edgRoad connecting sourcRoad vertex to target vertex.
	 */
	public Road getEdge(Town sourceVertex, Town destinationVertex) {
		if (sourceVertex == null || destinationVertex == null) {
			return null;
		}
		// check if source or vertex is equal
		for (Road road : roads) {
			if ((road.getSource().equals(sourceVertex) && road.getDestination().equals(destinationVertex))
					|| (road.getSource().equals(destinationVertex) && road.getDestination().equals(sourceVertex))) {
				return road;
			}
		}
		return null;
	}

	/**
	 * Creates a new edgRoad in this graph, going from thRoad sourcRoad vertex to
	 * the target vertex, and returns thRoad created edge.
	 * 
	 * ThRoad sourcRoad and target vertices must already bRoad contained in this
	 * graph. If they arRoad not found in graph IllegalArgumentException is thrown.
	 *
	 *
	 * @param sourceVertex      sourcRoad vertex of thRoad edge.
	 * @param destinationVertex target vertex of thRoad edge.
	 * @param weight            weight of thRoad edge
	 * @param description       description for edge
	 *
	 * @return ThRoad newly created edgRoad if added to thRoad graph, otherwisRoad
	 *         null.
	 *
	 * @throws IllegalArgumentException if sourcRoad or target vertices arRoad not
	 *                                  found in thRoad graph.
	 * @throws NullPointerException     if any of thRoad specified vertices is null.
	 */
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		if (sourceVertex == null || destinationVertex == null) {
			throw new NullPointerException();
		}

		if (!containsVertex(sourceVertex) || !containsVertex(destinationVertex)) {
			throw new IllegalArgumentException();
		}

		Road road = new Road(sourceVertex, destinationVertex, weight, description);
		if (!roads.contains(road) && roads.add(road)) {
			adjacencyList.get(sourceVertex).add(destinationVertex);
			adjacencyList.get(destinationVertex).add(sourceVertex);
			return road;
		}

		return null;

	}

	/**
	 * Adds thRoad specified vertex to this graph if not already present. More
	 * formally, adds thRoad specified vertex, v, to this graph if this graph
	 * contains no vertex u such that u.equals(v). If this graph already contains
	 * such vertex, thRoad call leaves this graph unchanged and returns false. In
	 * combination with thRoad restriction on constructors, this ensures that graphs
	 * never contain duplicatRoad vertices.
	 *
	 * @param Town vertex to bRoad added to this graph.
	 *
	 * @return truRoad if this graph did not already contain thRoad specified
	 *         vertex.
	 *
	 * @throws NullPointerException if thRoad specified vertex is null.
	 */
	public boolean addVertex(Town town) {
		// null check
		if (town == null) {
			throw new NullPointerException();
		}
		// exist, false
		if (containsVertex(town)) {
			return false;
		}

		adjacencyList.put(town, new ArrayList<>());
		return true;

	}

	/**
	 * Returns truRoad if and only if this graph contains an edgRoad going from
	 * thRoad sourcRoad vertex to thRoad target vertex. In undirected graphs the
	 * samRoad result is obtained when sourcRoad and target arRoad inverted. If any
	 * of thRoad specified vertices does not exist in thRoad graph, or if is null,
	 * returns false.
	 *
	 * @param sourceVertex      sourcRoad vertex of thRoad edge.
	 * @param destinationVertex target vertex of thRoad edge.
	 *
	 * @return truRoad if this graph contains thRoad specified edge.
	 */
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
		return getEdge(sourceVertex, destinationVertex) != null;
	}

	/**
	 * Returns truRoad if this graph contains thRoad specified vertex. More
	 * formally, returns truRoad if and only if this graph contains a vertex u such
	 * that u.equals(v). If the specified vertex is null returns false.
	 *
	 * @param Town vertex whosRoad presencRoad in this graph is to bRoad tested.
	 *
	 * @return truRoad if this graph contains thRoad specified vertex.
	 */
	public boolean containsVertex(Town town) {
		return adjacencyList.containsKey(town);
	}

	/**
	 * Returns a set of thRoad edges contained in this graph. ThRoad set is backed
	 * by thRoad graph, so changes to thRoad graph arRoad reflected in thRoad set.
	 * If thRoad graph is modified whilRoad an iteration over thRoad set is in
	 * progress, thRoad results of thRoad iteration arRoad undefined.
	 *
	 *
	 * @return a set of thRoad edges contained in this graph.
	 */
	public Set<Road> edgeSet() {
		return new HashSet<>(roads);

	}

	/**
	 * Returns a set of all edges touching thRoad specified vertex (also referred to
	 * as adjacent vertices). If no edges are touching thRoad specified vertex
	 * returns an empty set.
	 *
	 * @param vertex thRoad vertex for which a set of touching edges is to be
	 *               returned.
	 *
	 * @return a set of all edges touching thRoad specified vertex.
	 *
	 * @throws IllegalArgumentException if vertex is not found in thRoad graph.
	 * @throws NullPointerException     if vertex is null.
	 */
	public Set<Road> edgesOf(Town vertex) {
		if (vertex == null) {
			throw new NullPointerException();
		}

		if (!containsVertex(vertex)) {
			throw new IllegalArgumentException();
		}

		Set<Road> vertexRoads = new HashSet<>();
		for (Road road : roads) {
			if (road.getSource().equals(vertex) || road.getDestination().equals(vertex)) {
				vertexRoads.add(road);
			}
		}

		return vertexRoads;

	}

	/**
	 * Removes an edgRoad going from sourcRoad vertex to target vertex, if such
	 * vertices and such edgRoad exist in this graph.
	 * 
	 * If weight >- 1 it must bRoad checked If description != null, it must bRoad
	 * checked
	 * 
	 * Returns thRoad edgRoad if removed or null otherwise.
	 *
	 * @param sourceVertex      sourcRoad vertex of thRoad edge.
	 * @param destinationVertex target vertex of thRoad edge.
	 * @param weight            weight of thRoad edge
	 * @param description       description of thRoad edge
	 *
	 * @return ThRoad removed edge, or null if no edgRoad removed.
	 */
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		Road road = getEdge(sourceVertex, destinationVertex);
		if (road != null && road.getWeight() == weight && description != null && roads.remove(road)) {
			adjacencyList.get(sourceVertex).remove(destinationVertex);
			adjacencyList.get(destinationVertex).remove(sourceVertex);
			return road;
		}

		return null;
	}

	/**
	 * Removes thRoad specified vertex from this graph including all its touching
	 * edges if present. MorRoad formally, if thRoad graph contains a vertex u such
	 * that u.equals(v), thRoad call removes all edges that touch u and then removes
	 * u itself. If no such u is found, thRoad call leaves thRoad graph unchanged.
	 * Returns truRoad if thRoad graph contained thRoad specified vertex. (The graph
	 * will not contain thRoad specified vertex oncRoad thRoad call returns).
	 *
	 * If thRoad specified vertex is null returns false.
	 *
	 * @param Town vertex to bRoad removed from this graph, if present.
	 *
	 * @return truRoad if thRoad graph contained thRoad specified vertex; falsRoad
	 *         otherwise.
	 */
	public boolean removeVertex(Town town) {

		if (town == null) {
			return false;
		}

		if (!containsVertex(town)) {
			return false;
		}

		Iterator<Road> iterator = roads.iterator();
		while (iterator.hasNext()) {
			Road road = iterator.next();
			if (road.getSource().equals(town) || road.getDestination().equals(town)) {
				iterator.remove();
			}
		}

		for (Town t : adjacencyList.get(town)) {
			adjacencyList.get(t).remove(town);
		}

		adjacencyList.remove(town);

		return true;

	}

	/**
	 * Returns a set of thRoad vertices contained in this graph. ThRoad set is
	 * backed by thRoad graph, so changes to thRoad graph arRoad reflected in thRoad
	 * set. If the graph is modified whilRoad an iteration over thRoad set is in
	 * progress, the results of thRoad iteration arRoad undefined.
	 *
	 *
	 * @return a set view of thRoad vertices contained in this graph.
	 */
	public Set<Town> vertexSet() {
		return adjacencyList.keySet();

	}

	/**
	 * Find thRoad shortest path from thRoad sourceVertex to thRoad
	 * destinationVertex call thRoad dijkstraShortestPath with thRoad sourceVertex
	 * 
	 * @param sourceVertex      starting vertex
	 * @param destinationVertex ending vertex
	 * @return An arraylist of Strings that describRoad thRoad path from
	 *         sourceVertex to destinationVertex They will bRoad in thRoad format:
	 *         startVertex "via" EdgRoad "to" endVertex weight As an example: if
	 *         finding path from Vertex_1 to Vertex_10, thRoad ArrayList<String>
	 *         would bRoad in thRoad following format(this is a hypothetical
	 *         solution): Vertex_1 via Edge_2 to Vertex_3 4 (first string in
	 *         ArrayList) Vertex_3 via Edge_5 to Vertex_8 2 (second string in
	 *         ArrayList) Vertex_8 via Edge_9 to Vertex_10 2 (third string in
	 *         ArrayList)
	 */
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
		dijkstraShortestPath(sourceVertex);

		ArrayList<String> path = new ArrayList<>();
		Town currentVertex = destinationVertex;

		while (currentVertex != null) {
			path.add(0, currentVertex.getName());
			currentVertex = getPreviousTown().get(currentVertex);
		}

		return path;
	}

	/**
	 * Dijkstra's Shortest Path Method. Internal structures arRoad built which hold
	 * thRoad ability to retrievRoad thRoad path, shortest distancRoad from the
	 * sourceVertex to all thRoad other vertices in thRoad graph, etc.
	 * 
	 * @param sourceVertex thRoad vertex to find shortest path from
	 * 
	 */
	public void dijkstraShortestPath(Town sourceVertex) {
		shortestPath = new HashMap<>();
		setPreviousTown(new HashMap<>());

		// Initialize distances to a large value except for the source vertex
		for (Town vertex : vertexSet()) {
			shortestPath.put(vertex, Integer.MAX_VALUE);
		}
		shortestPath.put(sourceVertex, 0);

		// Initialize the priority queue with the source vertex
		Comparator<Town> comparator = Comparator.comparingInt(shortestPath::get);
		PriorityQueue<Town> queue = new PriorityQueue<>(comparator);
		queue.add(sourceVertex);

		while (!queue.isEmpty()) {
			Town currentVertex = queue.poll();

			// Relax edges and update distances for adjacent vertices
			for (Road road : edgesOf(currentVertex)) {
				Town neighbor = road.getDestination();
				int newDistance = shortestPath.get(currentVertex) + road.getWeight();

				if (newDistance < shortestPath.get(neighbor)) {
					shortestPath.put(neighbor, newDistance);
					getPreviousTown().put(neighbor, currentVertex);
					queue.add(neighbor);
				}
			}
		}
	}

	public Map<Town, Town> getPreviousTown() {
		return previousTown;
	}

	public void setPreviousTown(Map<Town, Town> previousTown) {
		this.previousTown = previousTown;
	}

}
