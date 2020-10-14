package hw3;

/**
 * @author Eshan Shakrani
 * @ID: 112802596
 * @email: eshan.shakrani@stonybrook.edu
 * @Assignment #3
 * @Class: CSE 214
 * @Recitation: R03
 * @Instructor: Dylan Andres
 */
public class Complexity {
	private int nPower;
	private int logPower;
	
	/**
	 * getNPower method
	 * 
	 * @return int - nPower of 
	 * 	current object
	 */
	public int getNPower() {
		return this.nPower;
	}
	
	/**
	 * setNPower method
	 * 
	 * @param nPower - type int
	 * 
	 * @postcondition nPower of 
	 *  current object is now
	 *  param nPower
	 */
	public void setNPower(int nPower) {
		this.nPower = nPower;
	}
	
	/**
	 * getLogPower method
	 * 
	 * @return int - logPower of
	 *  current object
	 */
	public int getLogPower() {
		return this.logPower;
	}
	
	/**
	 * setLogPower method
	 * 
	 * @param logPower - type int
	 * 
	 * @postcondition logPower of
	 * 	current object is now
	 *  param logPower
	 */
	public void setLogPower(int logPower) {
		this.logPower = logPower;
	}
	
	/**
	 * no-arg constructor
	 * 
	 * @postcondition creates a new
	 *  Complexity object
	 *  
	 * @postcondition sets the nPower
	 * 	of the object to 0
	 * 
	 * @postcondition sets the logPower
	 *  of the object to 0
	 */
	public Complexity() {
		this.nPower = 0;
		this.logPower = 0;
	}
	
	/**
	 * arg constructor
	 * 
	 * @param nPower - type int
	 * 
	 * @param logPower - type int
	 * 
	 * @postcondition creates a new
	 *  Complexity object
	 *  
	 * @postcondition sets the nPower 
	 *  of the object to param nPower
	 *  
	 * @postcondition sets the logPower
	 *  of the object to param logPower
	 */
	public Complexity(int nPower, int logPower) {
		this.nPower = nPower;
		this.logPower = logPower;
	}
	
	/**
	 * isHigherOrder method
	 * 
	 * compares the order of two complexity objects
	 * 
	 * @param com - Object of type Complexity
	 *  to be compared with current Complexity
	 *  
	 * @return boolean - 
	 *  true if current complexity is higher order than
	 *   parameter com
	 *  false otherwise
	 */
	public boolean isHigherOrder(Complexity com) {
		if (com.toString().equals("O(1)")) {
			return true;
		}
		else {
			if ((!this.toString().equals("O(1)")) && 
					com.toString().equals("O(log(n))")) {
				return true;
			}
			if (this.getNPower() > com.getNPower()) {
				return true;
			}
			if (this.getNPower() == 0 && com.getNPower() == 0) {
				if (this.getLogPower() > com.getLogPower()) {
					return true;
				}
				else {
					return false;
				}
			}
		}
		return false;
	}
	
	/**
	 * toString method
	 * 
	 * @return String - human-readable
	 *  Big-Oh notation
	 */
	public String toString() {
		String n = "";
		String log = "";
		String s = "";
		if (this.getNPower() == 0) {
			n = "1";
		}
		else if (this.getNPower() == 1) {
			n = "n";
		}
		else {
			n = "n^" + this.getNPower();
		}
		
		if (this.getLogPower() == 0) {
			log = "1";
		}
		else if (this.getLogPower() == 1) {
			log = "log(n)";
		}
		else {
			log = "log(n)^" + this.getLogPower();
		}
		
		if (n.equals("1") && log.equals("1")) {
			s += "O(1)";
		}
		else if (n.equals("1")) {
			s += "O(" + log + ")";
		}
		else if (log.equals("1")) {
			s += "O(" + n + ")";
		}
		else {
			s += String.format("O(%s * %s)", n, log);
		}
		return s;
	}
}
