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
public class TrainCarNode {
	private TrainCarNode prev;
	private TrainCarNode next;
	private TrainCar car;
	
	/**
	 * getPrev method
	 * 
	 * @return previous link of 
	 *  current TrainCarNode
	 */
	public TrainCarNode getPrev() {
		return this.prev;
	}
	
	/**
	 * setPrev method
	 * 
	 * @param prev - type TrainCarNode
	 * 	to be set as previous link for
	 *  the current TrainCarNode
	 *  
	 * @postcondition TrainCarNode prev has
	 *  been set as the previous link for the
	 *  current TrainCarNode
	 */
	public void setPrev(TrainCarNode prev) {
		this.prev = prev;
	}
	
	/**
	 * getNext method
	 * 
	 * @return next link of 
	 *  current TrainCarNode
	 */
	public TrainCarNode getNext() {
		return this.next;
	}
	
	/**
	 * setNext method 
	 * 
	 * @param next - type TrainCarNode
	 *  to be set as next link for
	 *  the current TrainCarNode
	 *  
	 * @postcondition TrainCarNode next has
	 *  been set as the next link for the 
	 *  current TrainCarNode
	 */
	public void setNext(TrainCarNode next) {
		this.next = next;
	}
	
	/**
	 * getCar method
	 * 
	 * @return the TrainCar wrapped within
	 *  the current TrainCarNode
	 */
	public TrainCar getCar() {
		return this.car;
	}
	
	/**
	 * setCar method
	 * 
	 * @param car - type TrainCar
	 *  to be set as the TrainCar wrapped 
	 *   within the current TrainCarNode
	 *   
	 * @postcondition the TrainCar
	 *  wrapped within the current 
	 *  TrainCarNode has been set to car
	 */
	public void setCar(TrainCar car) {
		this.car = car;
	}
	
	/**
	 * no-arg constructor
	 * 
	 * @postcondition creates a new 
	 *  TrainCarNode object
	 *  
	 * @postcondition initializes the 
	 *  node's previous link, next link,
	 *  and wrapped car to null
	 */
	public TrainCarNode() {
		this.prev = null;
		this.next = null;
		this.car = null;
	}
	
	/**
	 * arg constructor
	 * 
	 * @param car - type TrainCar
	 * 
	 * @postcondition creates a new 
	 *  TrainCarNode object
	 *  
	 * @postcondition initializes the node's
	 *  previous link and next link to null;
	 *  wrapped car takes the reference of 
	 *  parameter car
	 */
	public TrainCarNode(TrainCar car) {
		this.prev = null;
		this.next = null;
		this.car = car;
	}
	
	/**
	 * clone method
	 * 
	 * @return a copy of the specified
	 * 	train car node object
	 */
	public Object clone() {
		TrainCarNode newNode = new TrainCarNode();
		newNode.setPrev(this.getPrev());
		newNode.setNext(this.getNext());
		newNode.setCar((TrainCar)(this.getCar().clone()));
		return newNode;
	}
	
	/**
	 * toString method
	 * 
	 * @return a neatly formatted string representation 
	 *  of the train car node - including length, weight, 
	 *  and product load of the wrapped train car
	 */
	public String toString() {
		String s = "";
		s += "Length: " + this.getCar().getCarLength() + "\n";
		s += "Weight: " + this.getCar().getCarWeight() + "\n";
		s += "Product Load: " + this.getCar().getProductLoad() + "\n";
		return s;
	}
	public static void main (String[] args) {}
}
