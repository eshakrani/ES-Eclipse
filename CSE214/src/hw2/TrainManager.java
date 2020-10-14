package hw2;

/**
 * @author Eshan Shakrani
 * @ID: 112802596
 * @email: eshan.shakrani@stonybrook.edu
 * @Assignment #2
 * @Class: CSE 214
 * @Recitation: R03
 * @Instructor: Dylan Andres
 */
import java.util.Scanner;
public class TrainManager {

	public static void main (String[] args) {
		Scanner stdin = new Scanner(System.in);
		double weight, length;
		String name;
		double proWeight, proValue;
		boolean danger = false;;
		String input = "";
		char c = ' ';
		TrainLinkedList list = new TrainLinkedList();
		while (!(input.equals("Q"))) {
			displayMenu();
			System.out.print("Enter a selection: ");
			input = stdin.nextLine().toUpperCase();
			System.out.println();
			switch (input) {
				case "F":
					list.cursorForward();
					System.out.println();
					break;
				case "B":
					list.cursorBackward();
					System.out.println();
					break;
				case "I": 
					System.out.print("Enter car length in meters: ");
					length = stdin.nextDouble();
					while (length <= 0) {
						System.out.println("\nERROR: Invalid length.");
						System.out.print("Enter car length in meters: ");
						length = stdin.nextDouble();
					}
					System.out.print("Enter car weight in tons: ");
					weight = stdin.nextDouble();
					while (weight < 0) {
						System.out.println("\nERROR: Invalid weight.");
						System.out.print("Enter car weight in tons: ");
						weight = stdin.nextDouble();
					}
					stdin.nextLine();
					TrainCar car = new TrainCar(weight, length);
					try {
						list.insertAfterCursor(car);
					}
					catch (IllegalArgumentException e) {
						System.out.println("ERROR: Car is null.\n");
					}
					System.out.println();
					System.out.println("New train car " + length + 
						" meters " + weight + " tons inserted into train.\n");
					break;
				case "R":
					TrainCar temp = list.removeCursor();
					if (temp == null) {
						System.out.println("ERROR: Cursor is null.\n");
					}
					else {
						System.out.println("Car successfully unlinked. The"
								+ " following load has been removed from "
								+ "the train: \n");
						System.out.print(String.format
								("%-11s%-14s%-14s%s\n", "Name",
									"Weight (t)", "Value ($)", "Dangerous"));
						System.out.println("============================="
								+ "====================");
						System.out.println(temp);
						System.out.println();
					}
					break;
				case "L":
					if (!(list.getCursorData().getProductLoad()
							.getName().equals("Empty"))) {
						list.subtractValue(list.getCursorData().
								getProductLoad().getValue());
						list.subtractWeight(list.getCursorData().
								getProductLoad().getWeight());
					}
					System.out.print("Enter product name: ");
					name = stdin.nextLine();
					System.out.print("Enter product weight in tons: ");
					proWeight = stdin.nextDouble();
					while (proWeight < 0) {
						System.out.println("\nInvalid weight.");
						System.out.print("Enter product weight in tons: ");
						proWeight = stdin.nextDouble();
					}
					System.out.print("Enter product value in dollars: ");
					proValue = stdin.nextDouble();
					while (proValue < 0) {
						System.out.println("\nInvalid value.");
						System.out.print("Enter product value "
								+ "in dollars: ");
						proValue = stdin.nextDouble();
					}
					stdin.nextLine();
					System.out.print("Enter is product dangerous? (y/n): ");
					c = stdin.nextLine().charAt(0);
					while (c != 'n' && c != 'N' && c != 'y' && c != 'Y') {
						System.out.println("\nERROR: Invalid input.");
						System.out.print("Enter is product "
								+ "dangerous (y/n): ");
						c = stdin.nextLine().charAt(0);
					}
					if (c == 'n' || c == 'N') {
						danger = false;
					} 
					else if (c == 'y' || c == 'Y') {
						danger = true;
					}
					ProductLoad newLoad = new ProductLoad(name, proWeight, 
							proValue, danger);
					list.getCursorData().setProductLoad(newLoad);
					System.out.println("\n" + proWeight + " tons of " + 
							name + " added to the current car.");
					System.out.println();
					list.addWeight(proWeight);
					list.addValue(proValue);
					if (danger) {
						list.addDangerousCar();
					}
					break;
				case "S":
					System.out.print("Enter product name: ");
					name = stdin.nextLine();
					System.out.println();
					list.findProduct(name);
					System.out.println();
					break;
				case "T":
					System.out.println(list);
					System.out.println();
					break;
				case "M":
					list.printManifest();
					break;
				case "D":
					list.removeDangerousCars();
					break;
				case "Q": 
					System.out.println("Program terminating successfully...");
					break;
				default: 
					System.out.println("Invalid selection.\n");
					break;
			}
		}
		stdin.close();
	}
	
	/**
	 * displayMenu method
	 * 
	 * prints a list of all possible options 
	 *  for the user
	 */
	public static void displayMenu() {
		System.out.println("(F) Cursor Forward");
		System.out.println("(B) Cursor Backward");
		System.out.println("(I) Insert Car After Cursor");
		System.out.println("(R) Remove Car At Cursor");
		System.out.println("(L) Set Product Load");
		System.out.println("(S) Search For Product");
		System.out.println("(T) Display Train");
		System.out.println("(M) Display Manifest");
		System.out.println("(D) Remove Dangerous Cars");
		System.out.println("(Q) Quit");
		System.out.println();
	}
}
