import java.io.File;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

/*
 * @author Wonjae Kim
 */
public class CourseDBManager implements CourseDBManagerInterface {
	CourseDBStructure cds;

	/*
	 * Constructor , set size to 100 which seems big enough
	 */
	public CourseDBManager() {
		cds = new CourseDBStructure(100);
	}

	/**
	 * Adds a course (CourseDBElement) with the given information to
	 * CourseDBStructure.
	 * 
	 * @param id         course id
	 * @param crn        course crn
	 * @param credits    number of credits
	 * @param roomNum    course room number
	 * @param instructor name of the instructor
	 */
	@Override
	public void add(String id, int crn, int credits, String instructor, String roomNum) {
		CourseDBElement element = new CourseDBElement(id, crn, credits, instructor, roomNum);
		cds.add(element);

	}

	/**
	 * finds CourseDBElement based on the crn key
	 * 
	 * @param crn course crn (key)
	 * @return a CourseDBElement object
	 * 
	 */
	@Override
	public CourseDBElement get(int crn) {
		try {
			return cds.get(crn);
		} catch (IOException e) {
			System.out.println("Can not be found");
			return null;
		}

	}

	/**
	 * Reads the information of courses from a test file and adds them to the
	 * CourseDBStructure data structure
	 * 
	 * @param input input file
	 * @throws FileNotFoundException if file does not exists
	 */
	// public CourseDBElement(String id, int crn, int numberOfCredit, String
	// roomNum, String name)
	@Override
	public void readFile(File input) throws FileNotFoundException {
		// if input is not exist, throw exception
		if (!input.exists()) {
			throw new FileNotFoundException();
		}
		Scanner sc = new Scanner(input); // get file
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			String[] data = line.split(" "); // data split by the space

			String id = data[0].trim(); // get id
			int crn = Integer.parseInt(data[1].trim()); // get CRN
			int numberOfCredit = Integer.parseInt(data[2].trim()); // get credit
			String roomNumber = data[3].trim(); // get room number
			// remaining element is the name of the instructor
			String name = "";
			for (int i = 4; i < data.length; i++) {
				name += data[i];
				if (i < data.length - 1) {
					name += " "; // add space between words.
				}
			}
			name = name.trim(); // get name

			add(id, crn, numberOfCredit, roomNumber, name);

		}
		sc.close();
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
		return cds.showAll();
	}
}
