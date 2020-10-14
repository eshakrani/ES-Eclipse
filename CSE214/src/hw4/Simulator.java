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
import java.util.ArrayList;
import java.util.Scanner;
public class Simulator {
	
	// Level 1 router
	private Router dispatcher;
	
	// Level 2 routers
	private ArrayList<Router> routers;
	
	/* contains the running sum of the total time
	 * each packet is in the network
	 * 
	 * service time per packet = 
	 *  time of arrival - time of packet creation
	 */
	private int totalServiceTime;
	
	// total number of packets that has been 
	// successfully forwarded to the destination
	private int totalPacketsArrived;
	
	// the number of packets that have been dropped
	// due to a congested network
	private int packetsDropped;
	// happens when sendPacketTo throws Exception
	
	// probability of a new packet arriving at Dispatcher
	private double arrivalProb;
	
	// number of Intermediate routers in the network
	private int numIntRouters;
	
	// max. number of Packets a Router can acccommodate
	private int maxBufferSize;
	
	// min. size of a Packet
	private int minPacketSize;
	
	// max. size of a Packet
	private int maxPacketSize;
	
	// max. number of Packets the Destination router can
	// accept at a given simulation unit
	private int bandwidth;
	
	// number of simulation units
	private int duration;
	
	/**
	 * getDispatcher method 
	 * 
	 * @return - Router object
	 *  the dispatcher Router of 
	 *  the Simulator
	 */
	public Router getDispatcher() {
		return this.dispatcher;
	}
	
	/**
	 * setDispatcher method 
	 * 
	 * @param dispatcher - Router object
	 * 
	 * @postcondition the dispatcher Router of 
	 *  the Simulator has been set to 
	 *  param dispatcher
	 */
	public void setDispatcher(Router dispatcher) {
		this.dispatcher = dispatcher;
	}
	
	/**
	 * getRouters method 
	 * 
	 * @return - ArrayList of Router objects
	 *  the list of Level 2 Routers in
	 *  the Simulator
	 */
	public ArrayList<Router> getRouters() {
		return this.routers;
	}
	
	/**
	 * setRouters method
	 * 
	 * @param routers - ArrayList of Router objects
	 * 
	 * @postcondition List of Level 2 Routers in the
	 *  Simulator has been set to param routers
	 */
	public void setRouters(ArrayList<Router> routers) {
		this.routers = routers;
	}
	
	/**
	 * getTotalServiceTime method
	 * 
	 * @return int - the totalServiceTime
	 *  of the Simulator
	 */
	public int getTotalServiceTime() {
		return this.totalServiceTime;
	}
	
	/**
	 * setTotalServiceTime method
	 * 
	 * @param totalServiceTime - int
	 * 
	 * @postcondition totalServiceTime of the 
	 *  Simulator has been set to param totalServiceTime
	 */
	public void setTotalServiceTime(int totalServiceTime) {
		this.totalServiceTime = totalServiceTime;
	}
	
	/**
	 * getTotalPacketsArrived method
	 * 
	 * @return int - total number of packets that
	 *  has been successfully sent to destination
	 */
	public int getTotalPacketsArrived() {
		return this.totalPacketsArrived;
	}
	
	/**
	 * setTotalPacketsArrived method
	 * 
	 * @param totalPacketsArrived - int
	 * 
	 * @postcondition totalPacketsArrived of the 
	 *  Simulator has been set to param totalPacketsArrived
	 */
	public void setTotalPacketsArrived(int totalPacketsArrived) {
		this.totalPacketsArrived = totalPacketsArrived;
	}
	
	/**
	 * getPacketsDropped method
	 * 
	 * @return int - number of packets that have
	 *  been dropped in the Simulator
	 */
	public int getPacketsDropped() {
		return this.packetsDropped;
	}
	
	/**
	 * setPacketsDropped method
	 * 
	 * @param packetsDropped - int 
	 * 
	 * @postcondition packetsDropped of the Simulator
	 *  has been set to param packetsDropped
	 */
	public void setPacketsDropped(int packetsDropped) {
		this.packetsDropped = packetsDropped;
	}
	
	/**
	 * getArrivalProb method
	 * 
	 * @return int - probability of a new 
	 *  packet arriving at the Dispatcher
	 */
	public double getArrivalProb() {
		return this.arrivalProb;
	}
	
	/**
	 * setArrivalProb method
	 * 
	 * @param arrivalProb - int
	 * 
	 * @postcondition arrivalProb of the
	 *  Simulator has been set to param
	 *  arrivalProb
	 */
	public void setArrivalProb(int arrivalProb) {
		this.arrivalProb = arrivalProb;
	}
	
	/**
	 * getNumIntRouter method
	 * 
	 * @return int - number of Intermediate
	 *  routers in the network
	 */
	public int getNumIntRouters() {
		return this.numIntRouters;
	}
	
	/**
	 * setNumIntRouters method
	 * 
	 * @param numIntRouters - int
	 * 
	 * @postcondition numIntRouters of the Simulator
	 *  has been set to param numIntRouters
	 */
	public void setNumIntRouters(int numIntRouters) {
		this.numIntRouters = numIntRouters;
	}
	
	/**
	 * getMaxBufferSize method
	 * 
	 * @return int - max. number of Packets a
	 *  Router can accommodate for
	 */
	public int getMaxBufferSize() {
		return this.maxBufferSize;
	}
	
	/**
	 * setMaxBufferSize method
	 * 
	 * @param maxBufferSize - int
	 * 
	 * @postcondition maxBufferSize of the Simulator
	 *  has been set to param maxBufferSize
	 */
	public void setMaxBufferSize(int maxBufferSize) {
		this.maxBufferSize = maxBufferSize;
	}

	
	/**
	 * getMinPacketSize method
	 * 
	 * @return int - min. size of a Packet
	 */
	public int getMinPacketSize() {
		return this.minPacketSize;
	}

	
	/**
	 * setMinPacketSize method
	 * 
	 * @param minPacketSize - int
	 * 
	 * @postcondition minPacketSize of the Simulator
	 *  has been set to param minPacketSize
	 */
	public void setMinPacketSize(int minPacketSize) {
		this.minPacketSize = minPacketSize;
	}

	
	/**
	 * getMaxPacketSize method
	 * 
	 * @return int - max. size of a Packet
	 */
	public int getMaxPacketSize() {
		return this.maxPacketSize;
	}
	

	/**
	 * setMaxPacketSize method
	 * 
	 * @param maxPacketSize - int
	 * 
	 * @postcondition maxPacketSize of the Simulator
	 *  has been set to apram maxPacketSize
	 */
	public void setMaxPacketSize(int maxPacketSize) {
		this.maxPacketSize = maxPacketSize;
	}

	/**
	 * getBandwidth method
	 * 
	 * @return int - max. number of Packets 
	 *  the Destination router can accept
	 *  at a given simulation unit
	 */
	public int getBandwidth() {
		return bandwidth;
	}

	/**
	 * setBandwidth method
	 * 
	 * @param bandwidth - int
	 * 
	 * @postcondition bandwidth of the Simulator
	 *  has been set to param bandwidth
	 */
	public void setBandwidth(int bandwidth) {
		this.bandwidth = bandwidth;
	}

	/**
	 * getDuration method
	 * 
	 * @return int - number of simulation units
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * setDuration method
	 * 
	 * @param duration - int 
	 * 
	 * @postcondition duration of the Simulator 
	 *  has been set to param duration
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	/**
	 * randInt method
	 * 
	 * @param minVal - int 
	 * 
	 * @param maxVal - int
	 * 
	 * @return int - random int value from 
	 *  param minVal to param maxVal, inclusive
	 */
	private int randInt(int minVal, int maxVal) {
		return (int)(Math.random() * (maxVal - minVal + 1) + minVal);
	}
	
	
	
	
	
	
	
	
	// comment test
	
	
	public static void main(String[] args) {
		Scanner stdin = new Scanner(System.in);
		Simulator sim = new Simulator();
		String input = "";
		while (true) {
			
			
			
			
			
			
			
			System.out.print("Do you want to try another "
					+ "simulation? (y/n): ");
			
			input = stdin.nextLine();
			
			if (input.equals("N") || input.equals("n")) {
				System.out.println("\nProgram terminating"
						+ " successfully...");
				System.exit(1);
			}
			
			while (!input.equals("Y") && !input.equals("y")) {
				System.out.println("\nERROR: INVALID INPUT\n");
				System.out.print("Do you want to try another "
						+ "simulation? (y/n): ");
				
				input = stdin.nextLine();
				
				if (input.equals("N") || input.equals("n")) {
					System.out.println("\nProgram terminating"
							+ " successfully...");
					System.exit(1);
				}
				
			}
			
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
