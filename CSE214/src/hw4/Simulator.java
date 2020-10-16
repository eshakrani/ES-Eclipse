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
import java.util.Collection;
import java.util.Scanner;

@SuppressWarnings("all")
public class Simulator {

	// max. number of packets that can arrive per time unit
	static final int MAX_PACKETS = 3;

	// Level 1 router
	private Router dispatcher;

	// Level 2 routers
	private Collection<Router> routers;

	/*
	 * contains the running sum of the total time each packet is in the network
	 * 
	 * service time per packet = time of arrival - time of packet creation
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
	 * @return - Router object the dispatcher Router of the Simulator
	 */
	public Router getDispatcher() {
		return this.dispatcher;
	}

	/**
	 * setDispatcher method
	 * 
	 * @param dispatcher - Router object
	 * 
	 * @postcondition the dispatcher Router of the Simulator has been set to
	 *                param dispatcher
	 */
	public void setDispatcher(Router dispatcher) {
		this.dispatcher = dispatcher;
	}

	/**
	 * getRouters method
	 * 
	 * @return - ArrayList of Router objects the list of Level 2 Routers in the
	 *         Simulator
	 */
	public Collection<Router> getRouters() {
		return this.routers;
	}

	/**
	 * setRouters method
	 * 
	 * @param routers - ArrayList of Router objects
	 * 
	 * @postcondition List of Level 2 Routers in the Simulator has been set to
	 *                param routers
	 */
	public void setRouters(Collection<Router> routers) {
		this.routers = routers;
	}

	/**
	 * getTotalServiceTime method
	 * 
	 * @return int - the totalServiceTime of the Simulator
	 */
	public int getTotalServiceTime() {
		return this.totalServiceTime;
	}

	/**
	 * setTotalServiceTime method
	 * 
	 * @param totalServiceTime - int
	 * 
	 * @postcondition totalServiceTime of the Simulator has been set to param
	 *                totalServiceTime
	 */
	public void setTotalServiceTime(int totalServiceTime) {
		this.totalServiceTime = totalServiceTime;
	}

	/**
	 * getTotalPacketsArrived method
	 * 
	 * @return int - total number of packets that has been successfully sent to
	 *         destination
	 */
	public int getTotalPacketsArrived() {
		return this.totalPacketsArrived;
	}

	/**
	 * setTotalPacketsArrived method
	 * 
	 * @param totalPacketsArrived - int
	 * 
	 * @postcondition totalPacketsArrived of the Simulator has been set to param
	 *                totalPacketsArrived
	 */
	public void setTotalPacketsArrived(int totalPacketsArrived) {
		this.totalPacketsArrived = totalPacketsArrived;
	}

	/**
	 * getPacketsDropped method
	 * 
	 * @return int - number of packets that have been dropped in the Simulator
	 */
	public int getPacketsDropped() {
		return this.packetsDropped;
	}

	/**
	 * setPacketsDropped method
	 * 
	 * @param packetsDropped - int
	 * 
	 * @postcondition packetsDropped of the Simulator has been set to param
	 *                packetsDropped
	 */
	public void setPacketsDropped(int packetsDropped) {
		this.packetsDropped = packetsDropped;
	}

	/**
	 * getArrivalProb method
	 * 
	 * @return int - probability of a new packet arriving at the Dispatcher
	 */
	public double getArrivalProb() {
		return this.arrivalProb;
	}

	/**
	 * setArrivalProb method
	 * 
	 * @param arrivalProb - int
	 * 
	 * @postcondition arrivalProb of the Simulator has been set to param
	 *                arrivalProb
	 */
	public void setArrivalProb(int arrivalProb) {
		this.arrivalProb = arrivalProb;
	}

	/**
	 * getNumIntRouter method
	 * 
	 * @return int - number of Intermediate routers in the network
	 */
	public int getNumIntRouters() {
		return this.numIntRouters;
	}

	/**
	 * setNumIntRouters method
	 * 
	 * @param numIntRouters - int
	 * 
	 * @postcondition numIntRouters of the Simulator has been set to param
	 *                numIntRouters
	 */
	public void setNumIntRouters(int numIntRouters) {
		this.numIntRouters = numIntRouters;
	}

	/**
	 * getMaxBufferSize method
	 * 
	 * @return int - max. number of Packets a Router can accommodate for
	 */
	public int getMaxBufferSize() {
		return this.maxBufferSize;
	}

	/**
	 * setMaxBufferSize method
	 * 
	 * @param maxBufferSize - int
	 * 
	 * @postcondition maxBufferSize of the Simulator has been set to param
	 *                maxBufferSize
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
	 * @postcondition minPacketSize of the Simulator has been set to param
	 *                minPacketSize
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
	 * @postcondition maxPacketSize of the Simulator has been set to apram
	 *                maxPacketSize
	 */
	public void setMaxPacketSize(int maxPacketSize) {
		this.maxPacketSize = maxPacketSize;
	}

	/**
	 * getBandwidth method
	 * 
	 * @return int - max. number of Packets the Destination router can accept at
	 *         a given simulation unit
	 */
	public int getBandwidth() {
		return bandwidth;
	}

	/**
	 * setBandwidth method
	 * 
	 * @param bandwidth - int
	 * 
	 * @postcondition bandwidth of the Simulator has been set to param bandwidth
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
	 * @postcondition duration of the Simulator has been set to param duration
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * no-arg Simulator constructor
	 * 
	 * @postcondition a new Simulator object is created
	 * 
	 * @postcondition dispatcher Router set to new Router
	 * 
	 * @postcondition routers ArrayList set to new ArrayList of Router objects
	 * 
	 * @postcondition duration set to 1 to representation beginning of sim.
	 * 
	 * @postcondition all other numerical data fields set to 0 by default
	 */
	public Simulator() {
		this.setDispatcher(new Router());
		this.setRouters(new ArrayList<Router>());
		this.setDuration(1);
	}
	
	/**
	 * simulate method 
	 * 
	 * @param numIntermediateRouters - int
	 * 	number of Intermediate Routers 
	 * 
	 * @param arrivalProbability - double
	 * 	probability of a new Packet being created
	 * 
	 * @param maxBufferSize - int
	 * 	max capacity of an Intermediate Router
	 * 
	 * @param minPacketSize - int
	 * 	minimum possible size of a Packet 
	 * 
	 * @param maxPacketSize - int 
	 * 	maximum possible size of a Packet
	 * 
	 * @param bandwidthSize - int
	 * 	number of Packets that can be sent to the dest. Router at once
	 * 
	 * @param simulationDuration - int
	 * 	number of time units the simulation will run for
	 * 
	 * @return double - average time each packet spends in the network
	 */
	public double simulate(int numIntermediateRouters,
			double arrivalProbability, int maxBufferSize, int minPacketSize,
			int maxPacketSize, int bandwidthSize, int simulationDuration) {

		double avgTimePerPacket = 0;	
		int timeInRouter = 0;
		int destRouterIndex = 0;
		boolean packetsSent = false;
		Packet temp = new Packet();
		temp.setPacketCount(1);
		ArrayList<Router> routerList = (ArrayList) (this.getRouters());
		
		// add the correct number of intermediate routers to the Collection
		for (int i = 0; i < numIntermediateRouters; i++) {
			routerList.add(new Router());
		}

		// set max. buffer size for all Intermediate Routers
		routerList.get(0).setMaxBufferSize(maxBufferSize);

		// run for as long as the user input specifies
		while (this.getDuration() <= simulationDuration) {

			System.out.println("\nTime: " + this.getDuration());
			
			// will become true if at least one Packet has been created
			packetsSent = false;
			
			// generate Packets and insert them in the dispatcher
			for (int i = 0; i < MAX_PACKETS; i++) {
				
				if (Math.random() < arrivalProbability) {
					
					packetsSent = true;
					int ran = this.randInt(minPacketSize, maxPacketSize);
					try {
						temp = new Packet(ran, this.getDuration());
						temp.setTimeArrive(this.getDuration());
						this.getDispatcher().enqueue(temp);
						System.out.printf("Packet %d arrives at dispatcher "
								+ "with size %d.\n", temp.getId(), ran);
					} 
					catch (FullRouterException e) {
						System.out.println(e.getMessage());
					}

				}
			}
			
			// packetsSent would still be false if no Packets were created
			if (!packetsSent) {
				System.out.println("No packets arrived.");
			}
			
			// send Packets from dispatcher to Intermediate Routers
			while (!this.getDispatcher().isEmpty()) {
				
				try {
					
					// get the front Packet in the dispatcher router
					temp = this.getDispatcher().dequeue();
					
					// figure out the Router in the collection with most space
					destRouterIndex = Router.sendPacketTo(routerList);
					
					// send the Packet to that Router
					routerList.get(destRouterIndex - 1).enqueue(temp);
					System.out.printf("Packet %d sent to Router %d.\n",
									temp.getId(), destRouterIndex);
				} 
				catch (EmptyRouterException e) {
					System.out.println(e.getMessage());
				} 
				catch (FullRouterException e) {
					System.out.println(e.getMessage());
				} 
				catch (AllRoutersFullException e) {
					this.setPacketsDropped(this.getPacketsDropped() + 1);
					System.out.println("Network is congested. Packet "
							+ temp.getId() + " is dropped.");
				}

			}
			
			// reset dest. Router bandwidth back to 0 for this time unit
			this.setBandwidth(0);
			
			// check first for packets that couldn't leave before - prioritize
			for (int i = 0; i < routerList.size(); i++) {
				
				if (!routerList.get(i).isEmpty()) {
					
					try {
						
						// if the first packet in the router had 0 time left
						// last time unit but couldn't go due to bandwidth
						if (routerList.get(i).peek().getTimeToDest() < 0) {
							
							// if the dest. router can still accept more 
							// packets during this time unit
							if (this.getBandwidth() < bandwidthSize) {
								
								temp = routerList.get(i).dequeue();
								
								// figure out total service time for the packet
								timeInRouter = this.getDuration() - 
										temp.getTimeArrive();
								System.out.printf("Packet %d "
										+ "has successfully reached its "
										+ "destination: +%d\n", temp.getId(), 
										timeInRouter);
								
								// increase total service time accordingly
								this.setTotalServiceTime
									(this.getTotalServiceTime() + timeInRouter);
								
								// increment total num. of packets that arrived
								this.setTotalPacketsArrived
									(this.getTotalPacketsArrived() + 1);
								
								// adjust dest. router's bandwidth accordingly
								this.setBandwidth(this.getBandwidth() + 1);
							}
							
						}
					}
					catch (EmptyRouterException e) {}
				}
			}
			
			// now we check for Packets about to leave for the first time
			for (int i = 0; i < routerList.size(); i++) {
				
				if (!routerList.get(i).isEmpty()) {
					
					try {
						
						// check for Packets with 0 time left
						if (routerList.get(i).peek().getTimeToDest() == 0) {
							
							// if the dest. router can still accept more 
							// packets during this time unit
							if (this.getBandwidth() < bandwidthSize) {
								
								temp = routerList.get(i).dequeue();
								
								// figure out total service time for the packet
								timeInRouter = this.getDuration() - 
										temp.getTimeArrive();
								System.out.printf("Packet %d "
										+ "has successfully reached its "
										+ "destination: +%d\n", temp.getId(), 
										timeInRouter);
								
								// increase total service time accordingly
								this.setTotalServiceTime
									(this.getTotalServiceTime() + timeInRouter);
								
								// increment total num. of packets that arrived
								this.setTotalPacketsArrived
									(this.getTotalPacketsArrived() + 1);
								
								// adjust dest. router's bandwidth accordingly
								this.setBandwidth(this.getBandwidth() + 1);
							}
						}
					}
					catch (EmptyRouterException e) {}
				}
			}
			
			printRouterList(this);
			
			// decrease time to dest. by 1 for first packet in 
			// each int. Router
			for (int i = 0; i < routerList.size(); i++) {
				
				if (!routerList.get(i).isEmpty()) {
					
					try {
						routerList.get(i).peek().setTimeToDest
							(routerList.get(i).peek().getTimeToDest() - 1);
					}
					catch (EmptyRouterException e) {
						System.out.println(e.getMessage());
					}
				}
			}
			
			// increase time unit by one
			this.setDuration(this.getDuration() + 1);
		}
		
		// compute average service time per packet 
		avgTimePerPacket = (double)(this.getTotalServiceTime()) / 
								this.getTotalPacketsArrived();
		
		System.out.println();
		System.out.println("Simulation ending...");
		System.out.println("Total service time: " + this.getTotalServiceTime());
		System.out.println("Total packets served: " + 
				this.getTotalPacketsArrived());
		System.out.printf("Average service time per packet "
				+ "(nearest hundredth): %.2f\n", avgTimePerPacket);
		System.out.println("Total packets dropped: " +this.getPacketsDropped());
		
		return avgTimePerPacket;
	}

	/**
	 * printRouterList method
	 * 
	 * @param s - Simulator object
	 * 
	 * prints out each Router in the routers Collection of param s
	 */
	public static void printRouterList(Simulator s) {
		ArrayList<Router> routerList = (ArrayList) (s.getRouters());
		for (int i = 0; i < routerList.size(); i++) {
			System.out.println(String.format("R%d: %s", (i + 1),
					routerList.get(i).toString()));
		}
	}

	/**
	 * randInt method
	 * 
	 * @param minVal - int
	 * 
	 * @param maxVal - int
	 * 
	 * @return int - random int value from param minVal to param maxVal,
	 *         inclusive
	 */
	private int randInt(int minVal, int maxVal) {
		return (int) (Math.random() * (maxVal - minVal + 1) + minVal);
	}

	public static void main(String[] args) {
		Scanner stdin = new Scanner(System.in);
		String input = "";
		int numIntermediateRouters = 0;
		double arrivalProbability = 0.0;
		int maxBufferSize = 0;
		int minPacketSize = 0;
		int maxPacketSize = 0;
		int bandwidthSize = 0;
		int simulationDuration = 0;

		while (true) {

			System.out.println("Starting simulator...");

			// INTERMEDIATE ROUTER INPUT
			System.out.print("\nEnter the number of Intermediate routers: ");
			numIntermediateRouters = stdin.nextInt();

			while (numIntermediateRouters < 0) {
				System.out.println("\nERROR: INVALID INPUT.\n");
				System.out.print("Enter the number of Intermediate routers: ");
				numIntermediateRouters = stdin.nextInt();
			}
			// END OF INTERMEDIATE ROUTER INPUT

			stdin.nextLine();

			// ARRIVAL PROBABILITY INPUT
			System.out.print("\nEnter the arrival probability of a packet: ");
			arrivalProbability = stdin.nextDouble();

			while (arrivalProbability < 0) {
				System.out.println("\nERROR: INVALID INPUT.\n");
				System.out.print("Enter the arrival probability of a packet: ");
				arrivalProbability = stdin.nextDouble();
			}
			// END OF ARRIVAL PROBABILITY INPUT

			stdin.nextLine();

			// MAX. BUFFER SIZE INPUT
			System.out.print("\nEnter the maximum buffer size of a router: ");
			maxBufferSize = stdin.nextInt();

			while (maxBufferSize < 0) {
				System.out.println("\nERROR: INVALID INPUT.\n");
				System.out.print(
						"Enter the maximum buffer size " + "of a router: ");
				maxBufferSize = stdin.nextInt();
			}
			// END OF MAX. BUFFER SIZE INPUT

			stdin.nextLine();

			// MIN. PACKET SIZE INPUT
			System.out.print("\nEnter the minimum size of a packet: ");
			minPacketSize = stdin.nextInt();

			while (minPacketSize < 0) {
				System.out.println("\nERROR: INVALID INPUT.\n");
				System.out.print("Enter the minimum size of a packet: ");
				minPacketSize = stdin.nextInt();
			}
			// END OF MIN. PACKET SIZE INPUT

			stdin.nextLine();

			// MAX. PACKET SIZE INPUT
			System.out.print("\nEnter the maximum size of a packet: ");
			maxPacketSize = stdin.nextInt();

			while (maxPacketSize < 0) {
				System.out.println("\nERROR: INVALID INPUT.\n");
				System.out.print("Enter the maximum size of a packet: ");
				maxPacketSize = stdin.nextInt();
			}
			// END OF MAX. PACKET SIZE INPUT

			stdin.nextLine();

			// BANDWIDTH SIZE INPUT
			System.out.print("\nEnter the bandwidth size: ");
			bandwidthSize = stdin.nextInt();

			while (bandwidthSize < 0) {
				System.out.println("\nERROR: INVALID INPUT.\n");
				System.out.print("Enter the bandwidth size: ");
				bandwidthSize = stdin.nextInt();
			}
			// END OF BANDWIDTH SIZE INPUT

			stdin.nextLine();

			// SIMULATION DURATION INPUT
			System.out.print("\nEnter the simulation duration: ");
			simulationDuration = stdin.nextInt();

			while (simulationDuration < 0) {
				System.out.println("\nERROR: INVALID INPUT.\n");
				System.out.print("Enter the simulation duration: ");
				simulationDuration = stdin.nextInt();
			}
			// END OF SIMULATION DURATION INPUT

			stdin.nextLine();

			Simulator sim = new Simulator();

			sim.simulate(numIntermediateRouters, arrivalProbability,
					maxBufferSize, minPacketSize, maxPacketSize, bandwidthSize,
					simulationDuration);

			System.out.print("\nDo you want to try another " + 
					"simulation? (y/n): ");

			input = stdin.nextLine();
			
			// terminate if user inputs "N" or "n"
			if (input.equals("N") || input.equals("n")) {
				System.out.println("\nProgram terminating" + 
						" successfully...");
				System.exit(1);
			}
			
			// keep prompting if input is not "y" or "Y"
			while (!input.equals("Y") && !input.equals("y")) {
				
				System.out.println("\nERROR: INVALID INPUT\n");
				System.out.print("Do you want to try another " + 
						"simulation? (y/n): ");

				input = stdin.nextLine();

				if (input.equals("N") || input.equals("n")) {
					System.out.println("\nProgram terminating" + 
							" successfully...");
					System.exit(1);
				}

			}
			System.out.println();
		}
	}
}
