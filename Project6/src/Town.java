/*
 * @author Wonjae Kim
 */

import java.util.ArrayList;
import java.util.Objects;

public class Town implements Comparable<Town> {

	private String name;
	private ArrayList<Town> adjacentTown; // list of towns

	// constructor
	public Town(String name) {
		this.name = name;
		adjacentTown = new ArrayList<>();

	}

	// get name
	public String getName() {
		return name;
	}

	// set name
	public void setName(String name) {
		this.name = name;
	}

	// get AdjacentTown list
	public ArrayList<Town> getAdjacentTown() {
		return adjacentTown;
	}

	// set AdjacentTown list
	public void setAdjacentTown(ArrayList<Town> adjacentTown) {
		this.adjacentTown = adjacentTown;
	}

	// compare the town. if they are equals, return 0, else return int
	public int compareTo(Town town) {
		if (name.equals(town.getName())) {
			return 0;
		} else {
			return name.compareTo(town.getName());
		}
	}

	// separate by ,
	public String toString() {
		StringBuffer result = new StringBuffer();
		for (Town town : adjacentTown) {
			result.append(town.getName()).append(",");
		}
		return result.toString();
	}

	// hascode with name
	public int hashCode() {
		return Objects.hash(name);
	}

	// equals metho with obj
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Town town = (Town) obj;
		return name.equals(town.name);
	}
}
