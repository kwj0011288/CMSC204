/*
 * @author Wonjae Kim
 */
public class Road implements Comparable<Road> {

	private Town source;
	private Town destination;
	private int degrees; // weight
	private String name; // road name

	// constructor
	public Road(Town source, Town destination, int degrees, String name) {
		this.source = source;
		this.destination = destination;
		this.degrees = degrees;
		this.name = name;
	}

	// constructor with weight 1
	public Road(Town source, Town destination, String name) {
		this(source, destination, 1, name);
	}

	// get source town
	public Town getSource() {
		return source;
	}

	// set source town
	public void setSource(Town source) {
		this.source = source;
	}

	// get destination
	public Town getDestination() {
		return destination;
	}

	// set destination
	public void setDestination(Town destination) {
		this.destination = destination;
	}

	// get weight
	public int getWeight() {
		return degrees;
	}

	// get name
	public String getName() {
		return name;
	}

	// set name
	public void setName(String name) {
		this.name = name;
	}

	// check if source or destination is in the town
	public boolean contains(Town town) {
		return (source.equals(town) || destination.equals(town));
	}

	public String toString() {
		return name + " connects " + source.getName() + " and " + destination.getName() + " and is " + degrees
				+ " miles long";
	}

	// compare the name and the name from road
	public int compareTo(Road road) {
		return name.compareTo(road.name);
	}

	// equals method with Object.
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Road road = (Road) obj;
		return name.equals(road.name);

	}

}
