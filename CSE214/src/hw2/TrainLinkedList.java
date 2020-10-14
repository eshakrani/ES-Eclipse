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
public class TrainLinkedList {
	private TrainCarNode head;
	private TrainCarNode tail;
	private TrainCarNode cursor;
	private int size;
	private double totalLength;
	private double totalValue;
	private double totalWeight;
	private boolean isDangerous;
	private int numDangerous;
	
	/**
	 *  no-arg constructor
	 *  
	 *  @postcondition new TrainLinkedList initialized
	 *  	to an empty linked list
	 *  
	 *  @postcondition head, tail, cursor set to null
	 *  
	 *  @postcondition initializes size to 0 - empty
	 */
	public TrainLinkedList() {
		this.head = null;
		this.tail = null;
		this.cursor = null;
		this.size = 0;
		this.totalLength = 0;
		this.totalValue = 0;
		this.totalWeight = 0;
		this.isDangerous = false;
		this.numDangerous = 0;
	}
	
	/**
	 * getCursorData method
	 * 
	 * @precondition the list is not empty 
	 *  (the cursor is not null) 
	 *  
	 * @return the reference to the TrainCar at the node
	 *  currently referenced by the cursor
	 */
	public TrainCar getCursorData() {
		if (this.cursor != null) {
			return this.cursor.getCar();
		}
		else {
			System.out.println("ERROR: Cursor is null.\n");
			return null;
		}
	}

	/**
	 * setCursorData method
	 * 
	 * places car in the node currently referenced
	 * 	by the cursor
	 * 
	 * @param car of type TrainCar
	 * 
	 * @precondiiton the list is not empty
	 *  (cursor is not null) 
	 *  
	 * @postcondition the cursor node now contains
	 *  a reference to car as its data
	 */
	public void setCursorData(TrainCar car) {
		if (cursor != null) {
			this.cursor.setCar(car);
		}
		else {
			System.out.println("ERROR: List is empty.\n");
		}
	}
	
	/**
	 * cursorForward method
	 * 
	 * moves the cursor to point at 
	 *  the next TrainCarNode
	 *  
	 * @precondition the list is not empty (cursor
	 *  is not null)
	 *  
	 * @precondition cursor does not currently
	 *  reference the tail of the list
	 *  
	 * @postcondition the cursor has been advanced
	 *  to the next TrainCarNode, or has remained
	 *  at the tail of the list
	 */
	public void cursorForward() {
		if (cursor != tail) {
			cursor = cursor.getNext();
			System.out.println("Cursor moved forward.");
		}
		else {
			System.out.println("No next car, "
					+ "cannot move cursor forward.");
		}
	}
	
	/**
	 *  cursorBackward method
	 *  
	 *  moves the cursor to point at 
	 *   the previous TrainCarNode
	 *   
	 * @precondition the list is not empty (cursor
	 *  is not null)
	 *  
	 * @precondition cursor does not currently
	 *  reference the head of the list
	 *  
	 * @postcondition the cursor has been moved back
	 *  to the previous TrainCarNode, or has remained
	 *  at the head of the list
	 */
	public void cursorBackward() {
		if (cursor != head) {
			cursor = cursor.getPrev();
			System.out.println("Cursor moved backward.");
		}
		else {
			System.out.println("No previous car, "
					+ "cannot move cursor backward.");
		}
	}
	
	/**
	 * insertAfterCursor method
	 * 
	 * inserts a car into the train after 
	 *  the cursor position
	 * 
	 * @param newCar of type TrainCar - the new
	 *  TrainCar to be inserted into the train
	 *  
	 * @precondition this TrainCar object has
	 *  been instantiated
	 *  
	 * @postcondition the new TrainCar has been 
	 * 	inserted into the train after the position
	 *  of the cursor
	 *  
	 * @postcondition all TrainCar objects previously 
	 *  on the train are still on the train, and their
	 *  order has been preserved
	 *  
	 * @postcondition the cursor now points to the
	 *  inserted car
	 *  
	 * @throws IllegalArgumentException - indicates
	 *  that newCar is null
	 */
	public void insertAfterCursor (TrainCar newCar) 
		throws IllegalArgumentException {
		if (newCar == null) {
			throw new IllegalArgumentException("ERROR: Car is null.\n");
		}
		else {
			
			// create a new node that holds the parameter car
			TrainCarNode newNode = new TrainCarNode(newCar);
			
			// increase the total weight and length of
			// the car accordingly
			this.totalLength += newCar.getCarLength();
			this.totalWeight += newCar.getCarWeight();

			// if the list is empty then cursor is null
			if (cursor == null) {
				head = newNode;
				tail = newNode;
			}
			
			// if the cursor is the tail / last node
			else if (cursor.getNext() == null) {
				if (tail == null) {
					tail = newNode;
				}
				else {
					tail.setNext(newNode);
					newNode.setPrev(tail);
					tail = newNode;
				}
			}
			
			// if cursor is not null and not last node
			else {
				newNode.setPrev(cursor);
				newNode.setNext(cursor.getNext());
				cursor.setNext(newNode);
				newNode.getNext().setPrev(newNode);
			}
			cursor = newNode;
			this.size++;
		}
	}
	
	/**
	 * removeCursor method
	 * 
	 * removes the TrainCarNode referenced by the cursor
	 *  and returns the TrainCar contained within the node
	 *  
	 * @precondition the cursor is not null
	 * 
	 * @postcondition the TrainCarNode referenced by the 
	 *  cursor has been removed from the train
	 *  
	 * @postcondition the cursor now references the next
	 *  node, or the previous node if no next node exists
	 *  
	 * @return TrainCar that was in the cursor node
	 */
	public TrainCar removeCursor() {
		if (this.cursor == null) {
			return null;
		}
		else {
			
			//create a copy of the cursor node so it can 
			// be freely removed from the linkedlist
			TrainCar temp = 
					(TrainCar)this.getCursorData().clone();
			
			// decrease the appropriate variables by values
			// corresponding to the properties of the 
			// car that was removed with the cursor
			this.totalLength -= 
					this.getCursorData().getCarLength();
			this.totalValue -= this.getCursorData().
					getProductLoad().getValue();
			this.totalWeight -= 
					this.getCursorData().getCarWeight();
			this.totalWeight -= this.getCursorData().
					getProductLoad().getWeight();
			
			// if the productLoad at the cursor is
			// dangerous, then decrement the number
			// of dangerous cars
			if (this.getCursorData().
					getProductLoad().isDangerous()) {
				this.numDangerous--;
			}
			
			// if the number of dangerous cars is 0, 
			// then boolean isDangerous for the train
			// will now be false
			if (numDangerous == 0) {
				this.isDangerous = false;
			}
			
			// if the cursor is the only node (size = 1)
			if ((this.cursor == this.head) && 
					(this.cursor == this.tail)) {
				this.head = null;
				this.tail = null;
				this.cursor = null;
				this.size--;
				return temp;
			}
			
			// if the cursor is the head node
			else if (this.cursor == this.head) {
				this.cursor = this.cursor.getNext();
				this.head = this.head.getNext();
				this.head.setPrev(null);
				this.size--;
				return temp;
			}
			
			// if the cursor is the tail node
			else if (this.cursor == this.tail) {
				this.cursor = this.cursor.getPrev();
				this.tail = this.tail.getPrev();
				this.tail.setNext(null);
				this.size--;
				return temp;
			}
			
			else {
				this.cursor.getPrev().
					setNext(this.cursor.getNext());
				this.cursor.getNext().
					setPrev(this.cursor.getPrev());
				this.cursor = this.cursor.getNext();
				this.size--;
				return temp;
			}
		}
	}

	/**
	 * getSize method
	 *
	 * @return int size of the list
	 */
	public int size() {
		return this.size;
	}
	
	/**
	 * getLength method
	 * 
	 * returns the total legnth of the train in meters
	 * 
	 * @return sum of the lengths of each TrainCar
	 *  in the train
	 */
	public double getLength() {
		return this.totalLength;
	}
	
	/**
	 * getValue method
	 * 
	 * returns the total value of product carried
	 *  by the train
	 *  
	 * @return the sum of the values of each TrainCar
	 *  in the train
	 */
	public double getValue() {
		return this.totalValue;
	}
	
	/**
	 * addValue method
	 * 
	 * increments the total value of the train by
	 *  the value of the product load that just
	 *  got added to the train
	 *  
	 * @param increment - type double
	 *  the value of the added product load
	 *  
	 * @postcondition the total value of the train
	 *  will have increased by an amount equal to 
	 *  the value of the product load that was added
	 */
	public void addValue(double increment) {
		this.totalValue += increment;
	}
	
	/**
	 * subtractValue method
	 * 
	 * decrements the total value of the train by
	 *  the value of the product load that got 
	 *  removed from the train
	 * 
	 * @param amount - type double
	 * 	the value of the removed product load
	 * 
	 * @postcondition the total value of the train
	 *  will have decreased by an amount equal to 
	 *  the value of the product load that was removed
	 */
	public void subtractValue(double amount) {
		this.totalValue -= amount;
	}
	
	/**
	 * getWeight method
	 * 
	 * returns the total weight in tons of the train
	 * 
	 * @return the sum of the weight of each TrainCar plus
	 *  the sum of the ProductLoad carried by that car
	 */
	public double getWeight() {
		return this.totalWeight;
	}
	
	/**
	 * addWeight method
	 * 
	 * increments the total weight of the train by
	 *  the weight of a product load added
	 *  
	 * @param increment - type double
	 *  weight of product load
	 *  
	 * @postcondition the total weight of the train
	 *  will have increased by an amount equal to 
	 *  the weight of the added productload
	 */
	public void addWeight(double increment) {
		this.totalWeight += increment;
	}
	
	/**
	 * subtractWeight method
	 * 
	 * decrements the total weight of the train by
	 *  the weight of a product load added
	 *  
	 * @param amount - type double
	 * 	weight of produt load
	 * 
	 * @postcondition the total weight of the train
	 *  will have decreased by an amount equal to
	 *   the weight of the removed productLoad
	 */
	public void subtractWeight(double amount) {
		this.totalWeight -= amount;
	}
	
	/**
	 * isDangerous method
	 * 
	 * whether or not there is a dangerous product on one
	 *  of the TrainCar objects on the train
	 *  
	 * @return true if the train contains at least one TrainCar
	 *  carrying a dangerous ProductLoad, false otherwise
	 */
	public boolean isDangerous() {
		return this.isDangerous;
	}
	
	/**
	 * findProduct method
	 * 
	 * searches the list for all ProductLoad objects with the 
	 *  indicated name and sums together their weight and value
	 *  (also keeps track of whether the product is dangerous or
	 *  not), then prints a single ProductLoad record to the console
	 * 
	 * @param name - the name of the ProductLoad to find on the train
	 */
	public void findProduct(String name) {
		double totalWeight = 0;
		double totalValue = 0;
		int count = 0;
		boolean dangerous = false;;
		TrainCarNode temp = this.head;
		while (temp != null) {
			if (temp.getCar().getProductLoad().
					getName().equalsIgnoreCase(name)) {
				count++;
				totalWeight += temp.getCar().
						getProductLoad().getWeight();
				totalValue += temp.getCar().
						getProductLoad().getValue();
				dangerous = temp.getCar().getProductLoad().isDangerous();
			}
			temp = temp.getNext();
		}
		if (count == 0) {
			System.out.println("No record of " + name + 
					" on board train.");
		}
		else {
			ProductLoad tempLoad = new ProductLoad(name, totalWeight, 
					totalValue, dangerous);
			System.out.println("The following products were "
					+ "found on " + count + " cars: \n");
			System.out.print(String.format
					("%-11s%-14s%-14s%s\n", "Name",
						"Weight (t)", "Value ($)", "Dangerous"));
			System.out.println("============================="
					+ "====================");
			System.out.println(tempLoad);
			System.out.println();
		}
		
	}
	
	/**
	 * printManifest method
	 * 
	 * prints a neatly formatted table of the car number, car length,
	 *  car weight, load name, load weight, load value, and load
	 *  dangerousness for all of the cars on the train
	 */
	public void printManifest() {
		String s = "";
		s += String.format("%-3s%-35s%-10s\n", "", "CAR:", "LOAD:");
		s += String.format("%-4s%-7s%-13s%-12s%-3s%-11s%-14s%-14s%s\n", 
				"", "Num.", "Length (m)", "Weight (t)", "|", "Name",
				"Weight (t)", "Value ($)", "Dangerous");
		s += "====================================+==========="
				+ "=========================================\n";
		int i = 1;
		TrainCarNode newNode = this.head;
		while (newNode != null) {
			if (newNode == this.cursor) {
				s += String.format("%-5s%-6d%-13s%-15s%s\n", "->", i, 
						String.format("%.2f", newNode.getCar().getCarLength()), 
						String.format("%.2f", newNode.getCar().getCarWeight()),
						newNode.getCar().getProductLoad());
			}
			else {
				s += String.format("%-5s%-6d%-13s%-15s%s\n", "", i, 
						String.format("%.1f", newNode.getCar().getCarLength()), 
						String.format("%.1f", newNode.getCar().getCarWeight()),
						newNode.getCar().getProductLoad());
			}
			newNode = newNode.getNext();
			i++;
		}
		System.out.println(s);
	}
	
	/**
	 * addDangerousCar method
	 * 
	 * increments the number of dangerous cars
	 *  in the train by 1 when a dangerous 
	 *  product load is added to the train
	 *  
	 * @postcondition the value of numDangerous
	 *  (number of dangerous cars in the train) 
	 *  is incremented 
	 *  
	 * @postcondition the value of the 
	 *  isDangerous boolean variable is
	 *  set to true
	 */
	public void addDangerousCar() {
		this.numDangerous++;
		this.isDangerous = true;
	}
	
	/**
	 * removeDangerousCars method
	 * 
	 * removes all dangerous cars from the train, maintaining the order
	 *  of the cars in the train
	 * 
	 * @postcondition all dangerous cars have been removed from this train
	 * 
	 * @postcondition the order of all non-dangerous cars must be maintained
	 * 	upon the completion of this method
	 */
	public void removeDangerousCars() {
		this.cursor = this.head;
		boolean b = false;
		if (this.cursor.getNext() == null && this.getCursorData().
				getProductLoad().isDangerous()) {
			this.removeCursor();
			b = true;
		}
		else {
			while (this.cursor != null) {
				if (this.getCursorData().
						getProductLoad().isDangerous()) {
					this.removeCursor();
					b = true;
				}
				this.cursor = this.cursor.getNext();
			}
			this.cursor = this.tail;
		}
		this.isDangerous = false;
		this.numDangerous = 0;
		if (b) {
			System.out.println("Dangerous cars successfully "
					+ "removed from the train.\n");
		}
		else {
			System.out.println("No dangerous cars found. Train has" + 
					" been left unchanged.\n");
		}
	}
	
	/**
	 * toString method
	 * 
	 * returns a neatly formatted String representation of the train
	 */
	public String toString() {
		String d;
		String s = "";
		s += "Train: " + this.size() + " cars, ";
		s += String.format("%,.2f", this.getLength()) + " meters, ";
		s += String.format("%,.2f", this.getWeight()) + " tons, ";
		s += "$" + String.format("%,.2f", this.getValue()) + " value, ";
		if (this.isDangerous()) {
			d = "DANGEROUS";
		}
		else {
			d = "not dangerous";
		}
		s += d + ".";
		return s;
	}
	
	public static void main (String[] args) {}
}
