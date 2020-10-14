package hw1;

public class FullPlannerException extends Exception {
	public FullPlannerException() {
		System.out.println("ERROR: Planner is already full.");
	}
}
