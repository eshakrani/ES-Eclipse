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
public class Router {
	// queue implemented as Linked List
	
	private RouterNode front;
	private RouterNode rear;
	private int size;
	private static int maxBufferSize;
	
	/**
	 * getMaxBufferSize method
	 * 
	 * @return - int 
	 *  the maximum buffer size of 
	 *  the Routers in this sim.
	 */
	public int getMaxBufferSize() {
		return this.maxBufferSize;
	}
	
	/**
	 * setMaxBufferSize method
	 * 
	 * @param maxBufferSize - int
	 * 
	 * @postcondition maximum buffer size of
	 *  the Routers has been set to param
	 *  maxBufferSize
	 */
	public void setMaxBufferSize(int maxBufferSize) {
		this.maxBufferSize = maxBufferSize;
	}
	
	/**
	 * no-arg constructor
	 * 
	 * @postcondition new Router object
	 *  has been created
	 *  
	 * @postcondition front and rear of
	 *  the Router have been set to null
	 *  
	 * @postcondition size of the Router
	 *  has been set to 0
	 */
	public Router() {
		this.front = null;
		this.rear = null;
		this.size = 0;
	}
	
	/**
	 * enqueue method
	 * 
	 * @param packet - Packet object
	 * 
	 * @postcondition packet has been added
	 *  to the rear of the Router
	 *  
	 * @postcondition size of the Router 
	 *  has been incremented by 1
	 */
	public void enqueue(Packet p) throws FullRouterException {
		RouterNode newNode = new RouterNode(p);
		
		// if list is empty
		if (this.front == null) {
			this.front = newNode;
			this.rear = front;
			this.size++;
		}
		
		
		// if list is at max size
		else if (this.size == this.getMaxBufferSize()) {
			throw new FullRouterException("This Router is full.\n");
		}
		
		else {
			this.rear.setNext(newNode);
			this.rear = newNode;
			this.size++;
		}
	}
	
	/**
	 * dequeue method 
	 * 
	 * @return Packet object - 
	 * 	the Packet in the RouterNode
	 *  at the front of the Router
	 *  
	 * @throws EmptyRouterException if used on 
	 *  an empty Router object
	 *  
	 * @postcondition the RouterNode at the front
	 *  of the Router has been removed
	 *  
	 * @postcondition (if applicable) size of the Router
	 *  has been decremented by 1
	 */
	public Packet dequeue() throws EmptyRouterException {
		Packet p;
		
		// if Router is empty
		if (this.front == null) {
			throw new EmptyRouterException("EMPTY ROUTER");
		}
		
		p = this.front.getData();
		this.front = this.front.getNext();
		
		// if Router is now empty
		if (this.front == null) {
			this.rear = null;
		}
		
		this.size--;
		
		return p;
	}
	
	/**
	 * peek method
	 * 
	 * @return Packet object - 
	 *  the Packet in the RouterNode
	 *  at the front of the Router
	 *  
	 * @throws EmptyRouterException if used on an
	 *  empty Router object
	 */
	public Packet peek() throws EmptyRouterException {
		
		// if Router is empty
		if (this.front == null) {
			throw new EmptyRouterException("EMPTY ROUTER");
		}
		
		return this.front.getData();
	}
	
	/**
	 * size method
	 * 
	 * @return - int 
	 *  number of Packets in the Router
	 */
	public int size() {
		return this.size;
	}
	
	/**
	 * isEmpty method
	 * 
	 * @return boolean -
	 *  true if Router is empty (head is null)
	 *  false otherwise
	 */
	public boolean isEmpty() {
		return (front == null);
	}
	
	/**
	 * toString method
	 * 
	 * @return String - 
	 *  String representation of the Router
	 */
	public String toString() {
		String s = "{";
		if (this.front == null) {}
		
		else if (this.front == this.rear) {
			s += this.front.getData();
		}
		
		else {
			RouterNode temp = this.front;
			while (temp != this.rear) {
				s += temp.getData() + ", ";
				temp = temp.getNext();
			}
			s += this.rear.getData();
		}
		
		s += "}";
		return s;
	}
	
	/**
	 * sendPacketTo method
	 * 
	 * @param routers - ArrayList of Router objects
	 * 
	 * @return Router object - the router in the
	 *  param routers with the smallest size / most
	 *  free buffer space
	 *  
	 * @throws AllRoutersFullException - when every Router
	 *  object in param Routers is at max capacity / has no
	 *  free space
	 */
	public static int sendPacketTo(ArrayList<Router> routers) 
		throws AllRoutersFullException {
		int minSize = routers.get(0).size();
		int index = 0;
		int numFull = 0;
		for (int i = 0; i < routers.size(); i++) {
			if (routers.get(i).size() < minSize) {
				minSize = routers.get(i).size();
				index = i;
			}
			if (routers.get(i).size() == maxBufferSize) {
				numFull++;
			}
		}
		
		if (numFull == routers.size()) {
			throw new AllRoutersFullException
				("All Routers in Collection are full.\n");
		}
		
		// index (starts at 0) is the spot in the 
		// list with the smallest size
		return index + 1;
		
	}
	
	public static void main(String[] args) {
		try {
			Router r = new Router();
			Packet p1 = new Packet(1, 5);
			Packet p2 = new Packet(1, 5);
			Packet p3 = new Packet(1, 6);
			r.enqueue(p1);
			r.enqueue(p2);
			Router r2 = new Router();
			r2.enqueue(p2);
			r2.enqueue(p3);
			r.setMaxBufferSize(3);
			ArrayList<Router> list = new ArrayList<>();
			list.add(r);
			list.add(r2);

			r.enqueue(p3);
			r2.enqueue(p1);
			
			System.out.println(Router.sendPacketTo(list));
			
			
		}
		catch (FullRouterException e) {
			System.out.println(e.getMessage());
		}
		catch (AllRoutersFullException e) {
			System.out.println(e.getMessage());
		}
	}
}

/**
 * RouterNode class
 * 
 * @author eshak
 *
 * represents a Node of the 
 *  Router queue
 */
class RouterNode {
	private Packet data;
	private RouterNode next;
	private RouterNode prev;
	
	/**
	 * getData method
	 * 
	 * @return - Packet object
	 *  the Packet data of the
	 *  current RouterNode
	 */
	public Packet getData() {
		return this.data;
	}
	
	/**
	 * setData method
	 * 
	 * @param data - Packet object
	 * 
	 * @postcondition the data of the 
	 *  current RouterNode has been
	 *  set to param data
	 */
	public void setData(Packet data) {
		this.data = data;
	}
	
	/**
	 * getNext method
	 * 
	 * @return - RouterNode object
	 *  next RouterNode to current
	 *  RouterNode
	 */
	public RouterNode getNext() {
		return this.next;
	}
	
	/**
	 * setNext method
	 * 
	 * @param next - RouterNode object
	 * 
	 * @postcondition current RouterNode's 
	 *  next RouterNode has been set to 
	 *  param next
	 */
	public void setNext(RouterNode next) {
		this.next = next;
	}
	
	/**
	 * getPrev method
	 * 
	 * @return - RouterNode object
	 *  previous RouterNode to current
	 *  RouterNode
	 */
	public RouterNode getPrev() {
		return this.prev;
	}
	
	/**
	 * setPrev method 
	 * 
	 * @param prev - RouterNode object
	 * 
	 * @postcondition current RouterNode's 
	 *  previous RouterNode has been set to
	 *  param prev
	 */
	public void setPrev(RouterNode prev) {
		this.prev = prev;
	}
	
	/**
	 * no-arg constructor
	 * 
	 * @postcondition new RouterNode object
	 *  has been created
	 *  
	 * @postcondition next, prev, data 
	 *  all set to null
	 */
	public RouterNode() {}
	
	/**
	 * arg-constructor
	 * 
	 * @param packet - Packet object\
	 * 
	 * @postcondition new RouterNode object
	 *  has been created
	 * 
	 * @postcondition next, prev set to null
	 * 
	 * @postcondition data of RouterNode has
	 *  been set to param packet
	 */
	public RouterNode(Packet packet) {
		this.next = null;
		this.prev = null;
		this.data = packet;
	}
}