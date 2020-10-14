package hw1;


/**
 * Represents a Course
 * @author eshak
 *
 */
public class Course {
	private String courseName;
	private String department;
	private int code;
	private byte section;
	private String instructor;
	
	/**
	 * getCourseName method
	 * 
	 * @return String name of the specified course
	 */
	public String getCourseName() {
		return this.courseName;
	}
	
	/**
	 * setCourseName method
	 * 
	 * @param String courseName to be set as new course name
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	/**
	 * getDepartment method
	 * 
	 * @return String department of specified course
	 */
	public String getDepartment() {
		return this.department;
	}
	
	/**
	 * setDepartment method
	 * 
	 * @param String department to be set as new department
	 */
	public void setDepartment(String department) {
		this.department = department;
	}
	
	/**
	 * getCode method 
	 * 
	 * @return int code for specified course
	 */
	public int getCode() {
		return this.code;
	}
	
	/**
	 * setCode method
	 * 
	 * @param int code to be set as new course code
	 * 
	 * @throws Exception when new code is out of bounds
	 */
	public void setCode(int code) throws Exception {
		if (code < 100 || code > 999) {
			System.out.println("ERROR: Invalid code.");
		}
		else {
			this.code = code;
		}
	}
	
	/**
	 * getSection method 
	 * 
	 * @return byte section for specified course
	 */
	public byte getSection() {
		return this.section;
	}
	
	/**
	 * setSection  method
	 * 
	 * @param byte section to be set as new course section
	 * 
	 * @throws Exception when course section is out of bounds
	 */
	public void setSection(byte section) throws Exception {
		if (section < 1 || section > 999) {
			System.out.println("ERROR: Invalid section.");
		}
		else {
			this.section = section;
		}
	}
	
	/**
	 * getInstructor method
	 * 
	 * @return String instructor for specified course
	 */
	public String getInstructor() {
		return this.instructor;
	}
	
	/**
	 * setInstructor method
	 * 
	 * @param String instructor to be set as new course instructor
	 */
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	
	public Course() {
	} // no-arg constructor
	
	public Course(String courseName, String department, 
			int code, byte section, String instructor) {
		this.courseName = courseName;
		this.department = department;
		this.code = code;
		this.section = section;
		this.instructor = instructor;
	} // arg constructor
	
	/**
	 *  object clone method
	 *  
	 *  @return Course object that is a copy of the original Course
	 */
	public Object clone() {
		Course newCourse = new Course(this.courseName, this.department,
				this.code, this.section, this.instructor);
		return newCourse;
	}

	
	/**
	 * object equals method
	 * 
	 * checks if a course is equal to an object obj
	 * 
	 * @param obj Object to be checked for equality
	 * 
	 * @return true if objects are equal, false otherwise
	 */
	public boolean equals(Object obj) {
		if (obj instanceof Course) {
			Course c = (Course) obj;
			if ((this.courseName.equalsIgnoreCase(c.courseName)) && 
					(this.department.equalsIgnoreCase(c.department)) &&
					(this.code == c.code) &&
					(this.section == c.section) &&
					(this.instructor.equalsIgnoreCase(c.instructor))) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 *  toString method
	 *  
	 *  creates specified String representation of object
	 *  
	 *  @return String representation of Course
	 */
	public String toString() {
		String st = "";
		st += String.format("%-26s%-11s%-10d%-4s%-11s\n",  
			this.getCourseName(), this.getDepartment(),
			this.getCode(),
			String.format("%02d", this.getSection()),
			this.getInstructor());
		return st;
	}
	public static void main (String[] args) {}
}


