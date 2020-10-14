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
public class TrainCar {
	private double CAR_LENGTH;
	private double CAR_WEIGHT;
	private ProductLoad load;
	
	/**
	 * getCarLength method
	 * 
	 * @return length of current
	 *  TrainCar
	 */
	public double getCarLength() {
		return this.CAR_LENGTH;
	}
	
	/**
	 * getCarWeight method
	 * 
	 * @return weight of the current
	 *  TrainCar
	 */
	public double getCarWeight() {
		return this.CAR_WEIGHT;
	}
	
	/**
	 * getProductLoad method
	 * 
	 * @return the productLoad set
	 *  to the current TrainCar
	 */
	public ProductLoad getProductLoad() {
		return this.load;
	}
	
	/**
	 * setProductLoad method
	 * 
	 * @param load - type ProductLoad
	 *  to be set as the load for the
	 *  current TrainCar
	 *  
	 * @postcondition productLoad of current
	 *  TrainCar has been set to load
	 */
	public void setProductLoad(ProductLoad load) {
		this.load = load;
	}
	
	/**
	 * isEmpty method
	 * 
	 * @return true if the TrainCar is
	 *  empty (if its load is null); 
	 *  false otherwise
	 */
	public boolean isEmpty() {
		return (this.load == null);
	}
	
	/**
	 * no-arg constructor
	 * 
	 * @postcondition creates a new 
	 *  TrainCar object
	 *  
	 * @postcondition initializes the car's 
	 *  length and weight to 0; productLoad
	 *  set to default productLoad
	 */
	public TrainCar() {
		this.CAR_LENGTH = 0;
		this.CAR_WEIGHT = 0;
		this.load = new ProductLoad();
	}
	
	/**
	 * arg-constructor
	 * 
	 * @param CAR_WEIGHT - type double
	 *  
	 * @param CAR_LENGTH - type double
	 * 
	 * @postcondition creates a new 
	 *  TrainCar object
	 *  
	 * @postcondition initializes the car's weight 
	 *  to parameter CAR_WEIGHT, length to parameter
	 *  CAR_LENGTH; productLoad set to default 
	 *  productLoad
	 */
	public TrainCar(double CAR_WEIGHT, double CAR_LENGTH) {
		this.CAR_LENGTH = CAR_LENGTH;
		this.CAR_WEIGHT = CAR_WEIGHT;
		this.load = new ProductLoad();
	}
	
	/**
	 * clone method
	 * 
	 * @return a copy of the specified 
	 *  train car object
	 */
	public Object clone() {
		TrainCar newCar = new TrainCar();
		newCar.CAR_LENGTH = this.CAR_LENGTH;
		newCar.CAR_WEIGHT = this.CAR_WEIGHT;
		newCar.setProductLoad((ProductLoad)
				(this.getProductLoad().clone()));
		return newCar;
	}
	
	
	public String toString() {
		String s = "";
		s += this.getProductLoad();
		return s;
	}
	public static void main (String[] args) {}
}
