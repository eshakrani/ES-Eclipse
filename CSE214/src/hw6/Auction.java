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

import java.io.Serializable;
import big.data.DataSource;
@SuppressWarnings("all")
public class Auction implements Serializable {
	
	private String auctionID;
	private String sellerName;
	private String buyerName;
	private String itemInfo;
	private int timeRemaining;
	private double currentBid;
	
	/**
	 * @return String - auctionID of current Auction
	 */
	public String getAuctionID() {
		return this.auctionID;
	}
	
	/**
	 * @return String - sellerName of current Auction
	 */
	public String getSellerName() {
		return this.sellerName;
	}
	
	/**
	 * @return String - buyerName of current Auction
	 */
	public String getBuyerName() {
		return this.buyerName;
	}
	
	/**
	 * @return String - itemInfo of current Auction
	 */
	public String getItemInfo() {
		return this.itemInfo;
	}
	
	/**
	 * @return int - timeRemaining of current Auction
	 */
	public int getTimeRemaining() {
		return this.timeRemaining;
	}
	
	/**
	 * @return double - currentBid of current Auction 
	 */
	public double getCurrentBid() {
		return this.currentBid;
	}
	
	/**
	 * default Auction constructor
	 * 
	 * @postcondition creates a new Auction object
	 * 
	 * @postcondition auctionID set to "N/A"
	 * 
	 * @postcondition sellerName set to "N/A"
	 * 
	 * @postcondition buyerName set to "N/A"
	 * 
	 * @postcondition itemInfo set to "N/A"
	 * 
	 * @postcondition timeRemaining set to 0
	 * 
	 * @postcondition currentBid set to 0
	 */
	public Auction() {
		this.auctionID = "N/A";
		this.sellerName = "N/A";
		this.buyerName = "N/A";
		this.itemInfo = "N/A";
		this.timeRemaining = 0;
		this.currentBid = 0;
	}
	
	/**
	 * parameterized Auction constructor
	 * 
	 * @param auctionID - String
	 * 
	 * @param sellerName - String
	 * 
	 * @param buyerName - String
	 * 
	 * @param itemInfo - String
	 * 
	 * @param timeRemaining - int
	 * 
	 * @param currentBid - double
	 * 
	 * @postcondition creates a new Auction object
	 * 
	 * @postcondition auctionID set to param auctionID
	 * 
	 * @postcondition sellerName set to param sellerName
	 * 
	 * @postcondition buyerName set to param buyerName
	 * 
	 * @postcondition itemInfo set to param itemInfo
	 * 
	 * @postcondition timeRemaining set to param timeRemaining
	 * 
	 * @postcondition currentBid set to param currentBid
	 */
	public Auction(String auctionID, String sellerName, String buyerName, 
			String itemInfo, int timeRemaining, double currentBid) {
		this.auctionID = auctionID;
		this.sellerName = sellerName;
		this.buyerName = buyerName;
		this.itemInfo = itemInfo;
		this.timeRemaining = timeRemaining;
		this.currentBid = currentBid;
	}
	
	/**
	 * decreases the time remaining for this auction by the specified amount
	 * if time is greater than the current remainign time for the auction, then 
	 *  the time remaining is set to 0
	 *  
	 * @param time - int
	 * 
	 * @postcondition timeRemaining has been decremented by the indicated amount
	 *  and is greater than or equal to 0
	 */
	public void decrementTimeRemaining (int time) {
		this.timeRemaining -= time;
		if (this.timeRemaining < 0) {
			this.timeRemaining = 0;
		}
	}
	
	/**
	 * makes a new bid on this auction
	 * If bidAmt is larger than currentBid, then the value of currentBid is
	 *  replaced by bidAmt and buyerName is replaced by bidderName
	 *  
	 * @param bidderName - String
	 * 
	 * @param bidAmt - double 
	 * 
	 * @precondition the auction is not closed (timeRemaining > 0)
	 * 
	 * @postcondition currentBid reflects the largest bid placed on this object
	 *	If the auction is closed, throw a ClosedAuctionException 
	 *
	 * @throws ClosedAuctionException if the auction is closed and no more bids
	 *  can be placed (timeRemaining = 0)
	 */
	public void newBid (String bidderName, double bidAmt) throws 
		ClosedAuctionException {
		if (timeRemaining == 0) {
			throw new ClosedAuctionException();
		}
		if (bidAmt > this.getCurrentBid()) {
			this.buyerName = bidderName;
			this.currentBid = bidAmt;
		}
	}
	
	/**
	 * @return String representation of auction object with 
	 *  proper spacing
	 */
	public String toString() {
		String s = "";
		s += String.format("%10s | $", this.getAuctionID());
		if (this.getCurrentBid() == 0) {
			s += String.format("%9s |", " ");
		}
		else {
			s += String.format("%,9.2f |", this.getCurrentBid());
		}
		s += String.format(" %-20s |", this.getSellerName());
		s += String.format(" %-22s |", this.getBuyerName());
		s += String.format(" %3d hours |", this.getTimeRemaining());
//		s += String.format(" %s", this.getItemInfo().substring(0, 42));
		s += String.format(" %s", (this.getItemInfo().length() >= 50 ? 
				this.getItemInfo().substring(0, 50) : this.getItemInfo()));
		return s;
	}

}
