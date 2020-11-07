package hw6;

/**
 * @author Eshan Shakrani
 * @ID: 112802596
 * @email: eshan.shakrani@stonybrook.edu
 * @Assignment #6
 * @Class: CSE 214
 * @Recitation: R03
 * @Instructor: Dylan Andres
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import big.data.DataSource;
import java.util.Hashtable;

public class AuctionTable extends Hashtable implements Serializable {
	
	/**
	 * default AuctionTable constructor
	 * 
	 * @postconditions creates a new AuctionTable object
	 */
	public AuctionTable() {}
	
	/**
	 * uses the BigData library to construct an AuctionTable from a remote
	 *  data source
	 *  
	 * @param URL - String representing the URL to the remote data source
	 * 
	 * @precondition URL represents a data source which can be connected to 
	 *  using the BigData library
	 * 
	 * @precondition the data source has proper syntax
	 * 
	 * @return the AuctionTable constructed from the remote data source
	 * 
	 * @throws IllegalArgumentException if the URL does not represent a valid
	 *  data source (can't connect or invalid syntax)
	 */
	public static AuctionTable buildFromURL (String URL) throws 
		IllegalArgumentException {
		DataSource ds = DataSource.connect(URL).load();
		if (ds.equals(null)) {
			throw new IllegalArgumentException();
		}
		else {
			try {
			
				// create new AuctionTable
				AuctionTable table = new AuctionTable();
				
				// Auction IDs
				String id[] = 
						ds.fetchStringArray("listing/auction_info/id_num");

				
				// Sellers
				String sellers[] = 
						ds.fetchStringArray("listing/seller_info/seller_name");
			

				// Bids
				String stBids[] = ds.fetchStringArray
						("listing/auction_info/current_bid");
				double bids[] = new double[stBids.length];
				for (int i = 0; i < bids.length; i++) {
					bids[i] = convertToDouble(stBids[i]);
				}

				
				// Buyers
				String buyers[] = ds.fetchStringArray
						("listing/auction_info/high_bidder/bidder_name");
				
				
				// Item Info
				String mem[] = 
						ds.fetchStringArray("listing/item_info/memory");
				String hdd[] = 
						ds.fetchStringArray("listing/item_info/hard_drive");
				String cpu[] = 
						ds.fetchStringArray("listing/item_info/cpu");
				String info[] = new String[mem.length];
				for (int i = 0; i < info.length; i++) {
					info[i] = cpu[i] + " - " + mem[i] + " - " + hdd[i];
				}
				
				
				// Time 
				String stTime[] = 
						ds.fetchStringArray("listing/auction_info/time_left");
				int times[] = new int[stTime.length];
				for (int i = 0; i < times.length; i++) {
					times[i] = stringToHours(stTime[i]);
				}
				
				for (int i = 0; i < bids.length; i++) {
					table.putAuction(id[i], new Auction(id[i], sellers[i], 
							buyers[i], info[i], times[i], bids[i]));
				}
				
				return table;
				
			}
			catch (Exception e) {}
			return null;
		}	
	}
	
	/**
	 * converts a double represented by a String into a true double
	 * 
	 * @param s - String representation of double value
	 * 
	 * @return true double representation of the value
	 */
	public static double convertToDouble(String s) {
		String newS = "";
		for (int i = 0; i < s.length(); i++) {
			if ((s.charAt(i) >= '0' && s.charAt(i) <= '9') ||
					s.charAt(i) == '.') {
				newS += s.charAt(i);
			}
		}
		return Double.parseDouble(newS);
	}
	
	/**
	 * converts time string (days, hours) to an int value with total hours
	 * 
	 * @param time - time String to convert to int 
	 * 
	 * @return int - total hours counting both days and hours
	 */
	public static int stringToHours(String time) {
		String dayString = "";
		String hourString = "";
		String temp = "";
		int days = 0;
		int hours = 0;
		for (int i = 0; i < time.length(); i++) {
			if (time.charAt(i) >= '0' && time.charAt(i) <= '9') {
				temp += time.charAt(i);
			}
			if (time.charAt(i) == 'd') {
				days = Integer.parseInt(temp);
				temp = "";
			}
			if (time.charAt(i) == 'h') {
				hours = Integer.parseInt(temp);
				temp = "";
			}
		}
		hours = hours + (days * 24);
		return hours;
	}
	
	/**
	 * manually posts an auction and adds it into the table
	 * 
	 * @param auctionID - the unique key for this object
	 * 
	 * @param auction - the auction to insert into the table with the 
	 *  corresponding auctionID
	 * 
	 * @postcondition the item will be added to the table if all given 
	 *  parameters are correct 
	 *  
	 * @throws IllegalArgumentException if the given auctionID is already 
	 *  stored in the table
	 */
	public void putAuction (String auctionID, Auction auction) throws 
		IllegalArgumentException {
		if (this.containsKey(auctionID)) {
			throw new IllegalArgumentException();
		}
		else {
			this.put(auctionID, auction);
		}
	}
	
	/**
	 * get the information of an Auction that contains the given ID as key
	 * 
	 * @param auctionID - the unique key for this object
	 * 
	 * @return an Auction object with the given key, null otherwise
	 */
	public Auction getAuction (String auctionID) {
		if (this.containsKey(auctionID)) {
			return (Auction)this.get(auctionID);
		}
		return null;
	}
	
	/**
	 * simulates the passing of time
	 * Decrease the timeRemaining of all Auction obejcts by the amount 
	 *  specified
	 * The value cannot go below 0
	 * 
	 * @param numHours - the number of hours to decrease the timeRemaining
	 *  value by
	 *  
	 * @postcondition all Auctions in the table have their timeRemaining timer
	 *  decreased
	 * If the original value is less than the decreased value, set the value
	 *  to 0
	 *  
	 * @throws IllegalArgumentException if the given numHours is non positive
	 */
	public void letTimePass (int numHours) throws IllegalArgumentException {
		if (numHours < 0) {
			throw new IllegalArgumentException();
		}
		else {
			for (Object ob : this.keySet()) {
				this.getAuction((String)ob).decrementTimeRemaining(numHours);
			}
		}
	}
	
	/**
	 * iterates over all Auction objects in the table and removes them if they
	 *  are expired (timeRemaining == 0)
	 *  
	 * @postcondition only open Auctions remain in the table
	 */
	public void removeExpiredAuctions() {
		for (Object ob : this.keySet().toArray()) {
			if (this.getAuction((String)ob).getTimeRemaining() == 0) {
				this.remove((String)ob);
			}
		}
	}
	
	/**
	 * prints the AuctionTable in tabular form
	 */
	public void printTable() {
		String header = "";
		header += "Auction ID |";
		header += "     Bid    |"; 
		header += "        Seller           |";
		header += "          Buyer             |";
		header += "   Time    |";
		header += "  Item Info";
		
		String line = "=============================================" + 
				"=======================================================" +
				"=====================================";

		System.out.println(header);
		System.out.println(line);
		
		for (Object ob : this.keySet()) {
			System.out.println(this.getAuction((String)ob));
		}
		
//		for (Object ob : this.keySet()) {
//			System.out.println(this.getAuction((String)ob).getSellerName());
//		}
		
	}
	
	
	
	public static void main (String[] args) {
		AuctionTable a = buildFromURL("http://tinyurl.com/p7vub89");
		a.printTable();
//		DataSource d = DataSource.connect("sample_file.xml").load();
//		String str = d.fetchString("listing/seller_info/seller_name");
//		System.out.println(str);
	}
	
}
