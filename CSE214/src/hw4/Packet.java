package hw4;

/**
 * @author Eshan Shakrani
 * @ID: 112802596
 * @email: eshan.shakrani@stonybrook.edu
 * @Assignment #4
 * @Class: CSE 214
 * @Recitation: R03
 * @Instructor: Dylan Andres
 */
@SuppressWarnings("all")
public class Packet {
	private static int packetCount = 1;
	private int id;
	private int packetSize;
	private int timeArrive;
	private int timeToDest;

	/**
	 * getPacketCount method
	 * 
	 * @return int - current packetCount
	 */
	public int getPacketCount() {
		return this.packetCount;
	}

	/**
	 * setPacketCount method
	 * 
	 * @param packetCount - int
	 * 
	 * @postcondition packetCount variable set to param packetCount
	 */
	public void setPacketCount(int packetCount) {
		this.packetCount = packetCount;
	}

	/**
	 * getId method
	 * 
	 * @return int - id of current Packet object
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * setId method
	 * 
	 * @param id - int
	 * 
	 * @postcondition current Packet object's id set to param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * getPacketSize method
	 * 
	 * @return int - packetSize of current Packet object
	 */
	public int getPacketSize() {
		return this.packetSize;
	}

	/**
	 * setPacketSize method
	 * 
	 * @param packetSize - int
	 * 
	 * @postcondition current Packet object's packetSize set to param packetSize
	 */
	public void setPacketSize(int packetSize) {
		this.packetSize = packetSize;
	}

	/**
	 * getTimeArrive method
	 * 
	 * @return int - timeArrive of current Packet object
	 */
	public int getTimeArrive() {
		return this.timeArrive;
	}

	/**
	 * setTimeArrive method
	 * 
	 * @param timeArrive - int
	 * 
	 * @postcondition current Packet object's timeArrive set to param timeArrive
	 */
	public void setTimeArrive(int timeArrive) {
		this.timeArrive = timeArrive;
	}

	/**
	 * getTimeToDest method
	 * 
	 * @return int - timeToDest of current Packet object
	 */
	public int getTimeToDest() {
		return this.timeToDest;
	}

	/**
	 * setTimeToDest method
	 * 
	 * @param timeToDest - int
	 * 
	 * @postcondition current Packet object's timeToDest set to param timeToDest
	 */
	public void setTimeToDest(int timeToDest) {
		this.timeToDest = timeToDest;
	}

	/**
	 * no-arg constructor
	 * 
	 * @postcondition new Packet object created
	 * 
	 * @postcondition Packet id set to current packetCount; packetCount
	 *                incremented
	 */
	public Packet() {
		this.id = packetCount++;
	}
	
	/**
	 * first arg constructor
	 * 
	 * @param packetSize - int
	 * 
	 * @param timeArrive - int
	 * 
	 * @postcondition new Packet object created
	 * 
	 * @postcondition Packet id set to current packetCount; packetCount
	 *                incremented
	 *                
	 * @postcondition timeArrive set to param timeArrive
	 * 
	 * @postcondition packetSize set to param packetSize
	 * 
	 * @postcondition timeToDest set to 1/100 of packetSize
	 */
	public Packet(int packetSize, int timeArrive) {
		this.id = packetCount++;
		this.timeArrive = timeArrive;
		this.packetSize = packetSize;
		this.timeToDest = this.packetSize / 100;
	}

	/**
	 * second arg-constructor
	 * 
	 * @param packetSize - int
	 * 
	 * @param timeArrive - int
	 * 
	 * @param timeToDest - int
	 * 
	 * @postcondition new Packet object created
	 * 
	 * @postcondition Packet id set to current packetCount; packetCount
	 *                incremented
	 * 
	 * @postcondition packetSize set to param packetSize
	 * 
	 * @postcondition timeArrive set to param timeArrive
	 * 
	 * @postcondition timeToDest set to param timeToDest
	 */
	public Packet(int packetSize, int timeArrive, int timeToDest) {
		this.id = packetCount++;
		this.packetSize = packetSize;
		this.timeArrive = timeArrive;
		this.timeToDest = timeToDest;
	}

	/**
	 * toString method
	 * 
	 * returns String representation of the Packet object
	 */
	public String toString() {
		String s = "";
		s += String.format("[%d, %d, %d]", this.id, this.timeArrive,
				this.timeToDest);
		return s;
	}
}
