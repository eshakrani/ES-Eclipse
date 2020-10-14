package hw1;


/**
 * Represents a Planner
 * @author eshak
 *
 */
public class Planner {
	final static int MAX_COURSES = 50;
	Course planner[] = new Course[MAX_COURSES + 1];
	int size;
	
	/**
	 * @postcondition the Planner has been initialized
	 * 		to an empty list of Courses
	 */
	public Planner() {
		Course planner[] = new Course[MAX_COURSES + 1];
		this.size = 0;
	} // Planner constructor
	
	/**
	 * size method
	 * 
	 * @precondition the Planner has been instantiated
	 * 
	 * @return int number of Courses in the Planner
	 */
	public int size() {
		if (this.planner != null) {
			return this.size;
		}
		else {
			System.out.println("Planner not instantiated yet.");
			return -1;
		}
	}
	
	/**
	 * addCourse method w/ specified position
	 * 
	 * adds a Course object to the planner in a 
	 * 	specific position
	 * 
	 * @param newCourse - the new course to add to the list
	 * 
	 * @param int position - the position of this course on 
	 * 		the list
	 * 
	 * @precondition this Course object has been instantiated
	 * 
	 * @precondition 1 <= position <= (number of items in list) + 1
	 * 
	 * @precondition number of items in the Planner < MAX_COURSES
	 * 
	 * @postcondition the new Course is now listed in the correct
	 * 	position on the list
	 * 
	 * @postcondition all Courses that were originally greater than 
	 * 	or equal to specified position are moved back one position
	 * 
	 * @throws IllegalArgumentException when position is 
	 * 			out of bounds
	 * 
	 * @throws FullPlannerException when the planner is 
	 * 			already full of Course objects
	 */
	public void addCourse(Course newCourse, int position) throws 
		IllegalArgumentException, FullPlannerException {
		if (newCourse != null) {
			try {
				if (size >= MAX_COURSES) {
					throw new FullPlannerException();
				}
				if (position <= 0 || position > this.size() + 1) {
					throw new IllegalArgumentException();
				}
				else {
					for (int i = this.size(); i >= position; i--) {
						this.planner[i + 1] = 
								(Course)(this.planner[i].clone());
					}
					this.planner[position] = newCourse;
					size++;
					System.out.printf("\n%s %d.%02d successfully "
						+ "added to planner.\n", 
						newCourse.getDepartment(), newCourse.getCode(), 
						newCourse.getSection());
				}
			}
			catch (IllegalArgumentException e) {
				System.out.println("ERROR: Invalid position.");
			}
			catch (Exception e) {}
		}
		else {
			System.out.println("Course object is not instantiated yet.");
		}
		
	} // addCourse - 2 arg
	
	/**
	 * addCourse method - no position
	 * 
	 * adds Course object to end of the planner
	 * 
	 * @param newCourse Course object to be added to planner
	 * 
	 * @throws IllegalArgumentException when position is
	 * 			out of bounds
	 * 
	 * @throws FullPlannerException when planner is already 
	 * 			of Course objects
	 */
	public void addCourse (Course newCourse) throws
		IllegalArgumentException, FullPlannerException {
		addCourse(newCourse, this.size() + 1);
	} // addCourse
	
	/**
	 * removeCourse method
	 * 
	 * removes Course object from specified position in 
	 * 	the planner
	 * 
	 * @param int position where Course object will
	 * 			be removed from
	 * 
	 * @postcondition the Course at the desired position has
	 * 	been removed
	 * 
	 * @postcondition all Courses that were originally greater
	 * 	than or equal to position are moved back one position
	 * 
	 * @throws IllegalArgumentException when position
	 * 			is out of bounds
	 */
	public void removeCourse (int position) throws IllegalArgumentException {
		if (this.planner != null) {
			try {
				if (position <= 0 || position > this.size()) {
					throw new IllegalArgumentException();
				}
				else {
					System.out.printf("\n%s %d.%02d has been "
						+ "successfully removed from the planner.\n", 
						this.planner[position].getDepartment(), 
						this.planner[position].getCode(), 
						this.planner[position].getSection());
					
					for (int i = position; i < size; i++) {
						this.planner[i] = 
							(Course)(this.planner[i + 1].clone());
					}
					size--;
				}	
			}
			catch (IllegalArgumentException e) {
				System.out.println("\nERROR: invalid position.");
			}
			catch (Exception e) {}
		}
		else {
			System.out.println("Planner not instantiated yet.");
		}
	} // removeCourse
	
	/**
	 * getCourse method
	 * 
	 * fetches the Course object at a specific position in planner
	 * 
	 * @param int position where the Course object is in planner
	 * 
	 * @precondition the Planner object has been instantiated
	 * 
	 * @precondition 1 <= position <= (number of items in list)
	 * 
	 * @return Course object at specified position
	 * 
	 * @throws IllegalArgumentException when position is 
	 * 			out of bounds
	 */
	public Course getCourse (int position) throws IllegalArgumentException {
		if (this.planner != null) {
			if (position <= 0 || position > this.size()) {
				throw new IllegalArgumentException("ERROR: "
					+ "Invalid position.");
			}
			return this.planner[position];
		}
		else {
			System.out.println("Planner not instantiated yet.");
			return null;
		}
	} // getCourse
	
	/**
	 * filter method
	 * 
	 * finds Course objects within the planner that have the 
	 *  specified department
	 *  
	 * @param Planner planner being searched for Courses
	 * 
	 * @param String department used to search in planner
	 * 
	 * @precondition the Planner object has been instantiated
	 * 
	 * @postcondition displays a neatly formatted table of 
	 * 	each course filtered from the Planner
	 */
	public static void filter (Planner planner, String department) {
		if (planner != null) {
			System.out.print(String.format("%-4s%-26s%-11s%-5s%"
				+ "-9s%-11s\n", "No.", "Course Name", 
				"Department", "Code", "Section", "Instructor"));
			System.out.println("----------------------------"
				+ "---------------------------------------------");
			for (int i = 1; i <= planner.size(); i++) {
				if (planner.planner[i].
						getDepartment().equalsIgnoreCase(department)) {
					System.out.print(String.format("%-4d%-26s%-11s"
						+ "%-10d%-4s%-11s\n", i, 
						planner.planner[i].getCourseName(), 
						planner.planner[i].getDepartment(),
						planner.planner[i].getCode(),
						String.format("%02d", 
								planner.planner[i].getSection()),
						planner.planner[i].getInstructor()));
				}
			}
		}
		else {
			System.out.println("Planner not instantiated yet.");
		}
	} // filter
	
	/**
	 * exists method
	 * 
	 * checks whether a certain course exists in the planner
	 * 
	 * @param Course course to verify
	 * 
	 * @precondition this Planner and Course have both 
	 * 	been instantiated
	 * 
	 * @return boolean true if the Planner contains this
	 * 	Course, false otherwise
	 */
	public boolean exists (Course course) {
		if (this.planner != null) {
			if (course != null) {
				boolean ex = false;
				for (int i = 1; i <= this.size(); i++) {
					if (this.planner[i].equals(course)) {
						ex = true;
						break;
					}
				}
				return ex;
			}
			else {
				System.out.println("Course not instantiated yet.");
				return false;
			}
		}
		else {
			System.out.println("Planner not instantiated yet.");
			return false;
		}
	} // exists
	
	/**
	 * clone method
	 * 
	 * makes a copy of a Planner object
	 * 
	 * @precondition this Planner object has been instantiated
	 * 
	 * @return Planner object copy (backup) of this Planner
	 */
	public Object clone() {
		if (this.planner != null) {
			Planner newPlanner = new Planner();
			for (int i = 1; i <= this.size(); i++) {
				newPlanner.planner[i] = 
						(Course)this.planner[i].clone();
			}
			newPlanner.size = this.size;
			return newPlanner;
		}
		else {
			System.out.println("Planner not instantiated yet.");
			return null;
		}
	} // clone
	
	/**
	 * printAllCourses method
	 * 
	 * displays all courses in the Planner
	 * 
	 * @precondition this Planner has been instantiated
	 * 
	 * @postcondition displays a neatly formatted table of 
	 * 	each course from the Planner
	 */
	public void printAllCourses() {
		if (this.planner != null) {
			System.out.print(String.format("%-4s%-26s%-11s%-5s%"
				+ "-9s%-11s\n", "No.", "Course Name", 
				"Department", "Code", "Section", "Instructor"));
			System.out.println("--------------------------------"
				+ "-----------------------------------------");
			System.out.print(this.toString());
		}
		else {
			System.out.println("Planner not instantiated yet.");
		}
	} // printAllCourses
	
	/**
	 * toString method
	 * 
	 * @return String representation of this Planner object
	 */
	public String toString() {
		String st = "";
		for (int i = 1; i <= this.size(); i++) {
			st += String.format("%-4d%-26s%-11s"
				+ "%-9d%-5s%-11s\n", i, 
				this.planner[i].getCourseName(), 
				this.planner[i].getDepartment(),
				this.planner[i].getCode(),
				String.format("%02d", this.planner[i].getSection()),
				this.planner[i].getInstructor());
		}
		return st;
	} // toString
	
	/**
	 * equals method
	 * 
	 * determines if two objects are equal to each other
	 * 
	 * @param Object obj to be checked for equality
	 * 
	 * @return boolean true if objects are equal, 
	 * 			false if not
	 */
	public boolean equals (Object obj) { 
		if (obj instanceof Planner) {
			Planner p = (Planner) obj;
			return this.plannerEquals(p);
		}
		return false;
	}
	
	/**
	 * plannerEquals method
	 * 
	 * checks if two Planner objects are equal 
	 * 	by checking size and each element
	 * 
	 * @param Planner p2 to check equality
	 * 
	 * @return boolean true if Planner objects are
	 * 			equal, false if not
	 */
	public boolean plannerEquals (Planner p2) {
		boolean eq = true;
		if (this.size() != p2.size()) {
			return false;
		}
		else {
			for (int i = 1; i <= this.size(); i++) {
				if (!(this.planner[i].equals(p2.planner[i]))) {
					eq = false;
					break;
				}
			}
			return eq;
		}
	}
	public static void main (String[] args) {}
}
