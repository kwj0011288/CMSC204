import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Hashtable;

/*
 * @author Wonjae Kim
 */
/**
 * This is the Course Database Structure Class.
 */
public class CourseDBStructure implements CourseDBStructureInterface {
	Hashtable<Integer, LinkedList<CourseDBElement>> table;
	int tableSize;

	// Constructor
	public CourseDBStructure(int numberOfCourse) {
		this.table = new Hashtable<>(tableSize);
		this.tableSize = getTableSize(numberOfCourse); // use getTableSize method to get a size

	}

	// Constructor for testing
	public CourseDBStructure(String testing, int numberOfCourse) {
		this.table = new Hashtable<>(tableSize);
		this.tableSize = numberOfCourse;
		

	}

	/**
	 * Adds a CourseDBElement object to the CourseDBStructure using the hashcode of
	 * the CourseDatabaseElemen object's crn value. If the CourseDatabaseElement
	 * already exists, exit quietly
	 * 
	 * @param element the CourseDBElement to be added to CourseDBStructure
	 */
	@Override
	public void add(CourseDBElement element) {
		int index = element.getCRN() % tableSize;
		LinkedList<CourseDBElement> bucket = table.get(index);
		// if bucket is null, create a LinkedList with the first element being the
		// CourseDBElement object
		if (bucket == null) {
			bucket = new LinkedList<>();
			table.put(index, bucket);
		}

		boolean check = false;
		for (CourseDBElement exist : bucket) {
			// update the details if the CRNs are the same
			if (exist.getCRN() == element.getCRN()) {
				exist.setCourseID(element.getCourseID());
				exist.setName(element.getName());
				exist.setNumberOfCredit(element.getNumberOfCredit());
				exist.setRoomNumber(element.getRoomNumber());
				check = true;
				break;
			}
		}
		// add element to the bucket
		if (!check) {
			bucket.add(element);
		}

	}

	/**
	 * Find a courseDatabaseElement based on the key (crn) of the
	 * courseDatabaseElement If the CourseDatabaseElement is found return it If not,
	 * throw an IOException
	 * 
	 * @param crn crn (key) whose associated courseDatabaseElement is to be returned
	 * @return a CourseDBElement whose crn is mapped to the key
	 * @throws IOException if key is not found
	 */
	@Override
	public CourseDBElement get(int crn) throws IOException {
		int index = crn % tableSize; // index calculation
		LinkedList<CourseDBElement> bucket = table.get(index); // retrieve the value
		if (bucket != null) {
			for (CourseDBElement element : bucket) { // find the matching CRN by loop it
				if (element.getCRN() == crn) {
					return element;
				}
			}
		}
		throw new IOException();// if CRN is not exist, throw exception
	}

	/**
	 * @return an array list of string representation of each course in the data
	 *         structure separated by a new line. Refer to the following example:
	 *         Course:CMSC500 CRN:39999 Credits:4 Instructor:Nobody InParticular
	 *         Room:SC100 Course:CMSC600 CRN:4000 Credits:4 Instructor:Somebody
	 *         Room:SC200
	 */
	@Override
	public ArrayList<String> showAll() {
		ArrayList<String> list = new ArrayList<>(); // make a list

		ArrayList<Integer> keyList = new ArrayList<>(table.keySet()); // make a key list from table
		Collections.sort(keyList); // sort it

		for (int key : keyList) { // loop to get the data info.
			LinkedList<CourseDBElement> bucket = table.get(key);
			if (bucket != null) {
				for (CourseDBElement element : bucket) {
					String output = "\nCourse:" + element.getCourseID() + " " + "CRN:" + element.getCRN() + " "
							+ "Credits:" + element.getNumberOfCredit() + " " + "Instructor:" + element.getName() + " "
							+ "Room:" + element.getRoomNumber();
					list.add(output);
				}
			}
		}
		return list;
	}

	/**
	 * Returns the size of the ConcordanceDataStructure (number of indexes in the
	 * array)
	 */
	@Override
	public int getTableSize() {
		return tableSize;
	}

	// get tableSize
	public int getTableSize(int numCourse) {
		double loadFactor = 1.5;
		int size = (int) (numCourse / loadFactor); // get size which is number of courses divided by the load Factor
													// which is 1.5
		while (!isPrime(size)) { // if size is not the prime number, increment the size to make it prime number
			size++;
		}
		return size;
	}

	// check if the size is prime
	public boolean isPrime(int num) {
		// if number is less or equals to 1, return false
		if (num <= 1) {
			return false;
		}
		// if number is not prime, return false
		for (int i = 2; i <= num / 2; i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}

}
