package hw1;

/**
 * Eshan Shakrani
 * ID: 112802596
 * Email: eshan.shakrani@stonybrook.edu
 * Assignment #1
 * CSE 214
 * Recitation: R03
 * Instructor: Dylan Andres
 */
import java.util.Scanner;

/**
 * Used to implement Course and Planner classes
 * @author eshak
 *
 */
public class PlannerManager {
	public static void main (String[] args) {
		Scanner stdin = new Scanner(System.in);
		Planner p1 = new Planner();
		Planner backup = new Planner();
		String courseName = "", 
				department = "", 
				instructor = "";
		int code = 0, position = 0;
		byte section;
		boolean flag = false;
		String s = "";
		while (!(s.equalsIgnoreCase("q"))) {	
			printMenu();
			System.out.print("Enter a selection: ");
			s = stdin.nextLine().toUpperCase();
			System.out.println();
			switch (s) {
				case "A": 
					System.out.print("Enter course name: ");
					courseName = stdin.nextLine();
					while (courseName.length() > 25) {
						System.out.println("ERROR: Invalid name.");
						System.out.print("\nEnter course name: ");
						courseName = stdin.nextLine();
					}
					System.out.print("Enter department: ");
					department = stdin.nextLine().toUpperCase();
					while (department.length() != 3) {
						System.out.println("ERROR: "
								+ "Invalid department.");
						System.out.print("\nEnter department: ");
						department = stdin.nextLine().toUpperCase();
						while (!onlyLetters(department)) {
							System.out.println("ERROR: "
									+ "Invalid department.");
							System.out.print("\nEnter department: ");
							department = stdin.nextLine().toUpperCase();
						}
					}
					System.out.print("Enter course code: ");
					code = stdin.nextInt();
					while (code < 100 || code > 999) {
						System.out.println("ERROR: Invalid code.");
						System.out.print("\nEnter code: ");
						code = stdin.nextInt();
					}
					System.out.print("Enter course section: ");
					section = stdin.nextByte();
					while (section < 1 || section > 127) {
						System.out.println("ERROR: Invalid section.");
						System.out.print("\nEnter section: ");
						section = stdin.nextByte();
					}
					stdin.nextLine();
					System.out.print("Enter instructor: ");
					instructor = stdin.nextLine();
					System.out.print("Enter position: ");
					position = stdin.nextInt();
					stdin.nextLine();
					Course newCourse = new Course(courseName, 
							department, code, section, instructor);
					try {
						p1.addCourse(newCourse, position);
					}
					catch (FullPlannerException e) {}
					break;
				case "G":
					System.out.print("Enter position: ");
					position = stdin.nextInt();
					stdin.nextLine();
					try {
						p1.getCourse(position);
						System.out.println();
						System.out.print(String.format("%-4s"
							+ "%-26s%-11s%-5s%-9s%-11s\n", 
							"No.", "Course Name", "Department", 
							"Code", "Section", "Instructor"));
						System.out.println("-------------------------"
							+ "--------------------------------------"
							+ "----------");
						System.out.print(String.format("%-4d%-26s%-11s"
							+ "%-10d%-4s%-11s\n", position, 
							p1.planner[position].getCourseName(), 
							p1.planner[position].getDepartment(),
							p1.planner[position].getCode(),
							String.format("%02d", 
								p1.planner[position].getSection()),
							p1.planner[position].getInstructor()));
					}
					catch (IllegalArgumentException e) {
						System.out.println("\nERROR: Invalid position.");
					}
					break;
				case "R": 
					System.out.print("Enter position: ");
					position = stdin.nextInt();
					stdin.nextLine();
					p1.removeCourse(position);
					break;
				case "P": 
					p1.printAllCourses();
					break;
				case "F":
					System.out.print("Enter department code: ");
					department = stdin.nextLine().toUpperCase();
					System.out.println();
					Planner.filter(p1, department);
					break;
				case "L":
					System.out.print("Enter course name: ");
					courseName = stdin.nextLine();
					System.out.print("Enter department: ");
					department = stdin.nextLine().toUpperCase();
					System.out.print("Enter course code: ");
					code = stdin.nextInt();
					System.out.print("Enter course section: ");
					section = stdin.nextByte();
					stdin.nextLine();
					System.out.print("Enter instructor: ");
					instructor = stdin.nextLine();
					Course newCourse1 = new Course
							(courseName, department, code,
									section, instructor);
					flag = false;
					for (int i = 1; i <= p1.size(); i++) {
						if (newCourse1.equals(p1.planner[i])) {
							flag = true;
							position = i;
							break;
						}
					}
					if (flag) {
						System.out.printf("\n%s %d.%02d is "
							+ "found in the planner at "
							+ "position %d.\n", department,
							code, section, position);
					}
					else {
						System.out.printf("\n%s %d.%02d is "
							+ "not found in the planner.\n", 
							department, code, section);
					}
					break;
				case "S": 
					System.out.println("There are " + p1.size() + 
						" course(s) in the planner.");
					break;
				case "B":
					backup = (Planner)p1.clone();
					System.out.println("Created a backup of "
						+ "the current planner.");
					break;
				case "PB":
					System.out.println("\nPlanner (Backup):");
					backup.printAllCourses();
					break;
				case "RB":
					p1 = (Planner)backup.clone();
					System.out.println("Planner successfully"
						+ " reverted to the backup copy.");
					break;
				case "Q": 
					System.out.println("Programming terminating"
						+ " successfully...");
					break;
				default:
					System.out.println("\nERROR: Invalid input.");
					break;
			} // switch
		} // while
		stdin.close();
	} // main

	/**
	 * printMenu method
	 * 
	 * displays the options to choose from
	 */
	public static void printMenu() {
		System.out.println();
		System.out.println("(A) Add Course");
		System.out.println("(G) Get Course");
		System.out.println("(R) Remove Course");
		System.out.println("(P) Print Courses in Planner");
		System.out.println("(F) Filter by Department Code");
		System.out.println("(L) Look For Course");
		System.out.println("(S) Size");
		System.out.println("(B) Backup");
		System.out.println("(PB) Print Courses in Backup");
		System.out.println("(RB) Revert to Backup");
		System.out.println("(Q) Quit");
		System.out.println();
	}
	
	/**
	 * onlyLetters method
	 * 
	 * checks if a String has only alphabetic characters
	 * 
	 * @param String s to be checked 
	 * 
	 * @return boolean true if String s is only letters, 
	 * 			false if not
	 */
	public static boolean onlyLetters(String s) {
		boolean let = true;
		for (int i = 0; i < s.length(); i++) {
			if (!((s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') ||
					(s.charAt(i) >= 'a' && s.charAt(i) <= 'z'))) {
				let = false;
			}
		}
		return let;
	}
}
