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
public class ProductLoad {
	private String name;
	private double weight;
	private double value;
	private boolean isDangerous;
	
	/**
	 * getName method
	 * 
	 * @return name of the current
	 *  ProductLoad
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * setName method
	 * 
	 * @param name - type String
	 *  to be set as name of 
	 *  current ProductLoad 
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * getWeight method
	 * 
	 * @return weight of the 
	 *  current ProductLoad
	 */
	public double getWeight() {
		return this.weight;
	}
	
	/**
	 * setWeight method 
	 * 
	 * @param weight - type double 
	 *  to be set weight of the 
	 *  current ProductLoad
	 *  
	 * @throws IllegalArgumentException when 
	 *  a parameter weight less than 0 is entered
	 *  
	 * @postcondition the weight of the current
	 *  ProductLoad is set to weight
	 */
	public void setWeight(double weight) throws IllegalArgumentException {
		try {
			if (weight < 0) {
				throw new IllegalArgumentException("Invalid weight.\n");
			}
			else {
				this.weight = weight;
			}
		}
		catch (IllegalArgumentException e) {
			System.out.println("Invalid weight.\n");
		}
	}
	
	/**
	 * getValue method
	 * 
	 * @return value of the 
	 *  current ProductLoad
	 */
	public double getValue() {
		return this.value;
	}
	
	/**
	 * setValue method 
	 * 
	 * @param value - type double
	 *  to be set as new value of 
	 *  current ProductLoad 
	 *  
	 * @throws IllegalArgumentException when 
	 *  parameter value less than 0 is entered
	 *  
	 * @postcondition the value of the current
	 *  ProductLoad is set to value
	 */
	public void setValue(double value) throws IllegalArgumentException {
		try {
			if (value < 0) {
				throw new IllegalArgumentException("Invalid value.\n");
			}
			else {
				this.value = value;
			}
		}
		catch (IllegalArgumentException e) {
			System.out.println("Invalid value.\n");
		}
		
	}
	
	/**
	 * isDangerous method
	 * 
	 * @return boolean true if current
	 *  ProductLoad is dangerous, 
	 *  false otherwise
	 */
	public boolean isDangerous() {
		return this.isDangerous;
	}
	
	/**
	 * setDangerous method
	 * 
	 * @param isDangerous - type boolean
	 *  to be set as danger status for
	 *  current ProductLoad
	 *  
	 * @postcondition the danger status 
	 *  for the current ProductLoad is 
	 *  set to isDangerous
	 */
	public void setDangerous(boolean isDangerous) {
		this.isDangerous = isDangerous;
	}
	
	/**
	 * no-arg constructor
	 * 
	 * @postcondition creates a 
	 * "default" ProductLoad
	 * 
	 * @postcondition sets the
	 *  ProductLoad's name to "Empty",
	 *  weight to 0.0, value to 0.00, 
	 *  and danger status to false
	 */
	public ProductLoad() {
		this.name = "Empty";
		this.weight = 0.0;
		this.value = 0.00;
		this.isDangerous = false;
	}
	
	/**
	 * arg constructor 
	 * 
	 * @param name - type String
	 * 
	 * @param weight - type double
	 * 
	 * @param value - type double
	 * 
	 * @param isDangerous - type boolean 
	 * 
	 * @postcondition creates a new 
	 * 	ProductLoad object
	 * 
	 * @postcondition sets the ProductLoad's 
	 *  name to parameter name, weight to
	 *  parameter weight, value to parameter
	 *  value, and danger status to 
	 *  parameter isDangerous
	 */
	public ProductLoad(String name, double weight, 
			double value, boolean isDangerous) {
		this.name = name;
		this.weight = weight;
		this.value = value;
		this.isDangerous = isDangerous;
	}
	
	/**
	 * clone method
	 * 
	 * makes a copy of a ProductLoad object
	 * 
	 * @return ProductLoad object copy of this 
	 *  ProductLoad
	 */
	public Object clone() {
		ProductLoad newLoad = new ProductLoad();
		newLoad.name = this.name;
		newLoad.weight = this.weight;
		newLoad.value = this.value;
		newLoad.isDangerous = this.isDangerous;
		return newLoad;
	}
	
	/**
	 * toString method
	 * 
	 * @return formatted representation of
	 *  current ProductLoad object
	 */
	public String toString() {
		String s = "";
		String d;
		if (this.isDangerous() == true) {
			d = "YES";
		}
		else {
			d = "NO";
		}
		s += String.format("%-11s%-14s%-14s%s", this.getName(), 
				String.format("%,.1f", this.getWeight()), 
				String.format("%,.2f", this.getValue()), d);
				
		return s;
	}
	
	public static void main (String[] args) {
	}
}
