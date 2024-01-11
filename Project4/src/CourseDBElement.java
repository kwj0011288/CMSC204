/*
 * @author Wonjae Kim
 */
/**
 * This is the Course Database Element Class.
 */

public class CourseDBElement implements Comparable<CourseDBElement> {
	private String courseID;
	private int CRN;
	private int numberOfCredit;
	private String roomNumber;
	private String name;

	// Default Constructor
	public CourseDBElement() {

	}

	// Constructor with elements
	public CourseDBElement(String id, int crn, int numberOfCredit, String roomNum, String name) {
		this.courseID = id;
		this.CRN = crn;
		this.numberOfCredit = numberOfCredit;
		this.roomNumber = roomNum;
		this.name = name;
	}

	/*
	 * getters
	 */
	// get course ID
	public String getCourseID() {
		return courseID;
	}

	// get CRN
	public int getCRN() {
		return CRN;
	}

	// get number of credit
	public int getNumberOfCredit() {
		return numberOfCredit;
	}

	// get room number
	public String getRoomNumber() {
		return roomNumber;
	}

	// get instructor name
	public String getName() {
		return name;
	}

	/*
	 * setters
	 */
	// set course ID
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	// set CRN
	public void setCRN(int cRN) {
		CRN = cRN;
	}

	// set number of credit
	public void setNumberOfCredit(int numberOfCredit) {
		this.numberOfCredit = numberOfCredit;
	}

	// set room number
	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	// set instructor name
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * Compare the CRN
	 */
	@Override
	public int compareTo(CourseDBElement o) {
		return Integer.compare(this.CRN, o.CRN);
	}

	@Override
	public String toString() {
		String str = "Course:" + getCourseID() + " " + "CRN:" + getCRN() + " " + "Credits:" + getNumberOfCredit() + " "
				+ "Instructor:" + getName() + " " + "Room:" + getRoomNumber();
		return str;
	}

}
