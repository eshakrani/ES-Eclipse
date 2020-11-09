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

import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
@SuppressWarnings("all")
public class AuctionSystem implements Serializable {
	
	private AuctionTable table;
	private String username;
	
	/**
	 * first prompt the user for a username to be stored in var. username
	 * execute the rest of the program on behalf of this user
	 * 
	 * @throws FileNotFoundException 
	 * 
	 * @throws IOException 
	 * 
	 * @throws ClassNotFoundException 
	 */
	public static void main (String[] args) throws FileNotFoundException, 
		IOException, ClassNotFoundException {
		
		AuctionSystem auc = new AuctionSystem();
		Auction temp;
		
		Scanner stdin = new Scanner(System.in);
		
		boolean fileFound = true;
		String url = "";
		String auctionID = "";
		int auctionTime = 0;
		String itemInfo = "";
		double newBid = 0;
		int hoursPassed = 0;
		
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		
		System.out.println("Starting...");
		
		try {
			fis = new FileInputStream("auctions.obj");
			ois = new ObjectInputStream(fis);
			auc.table = (AuctionTable) ois.readObject();
		}
		catch (FileNotFoundException e) {
			fileFound = false;
			auc.table = new AuctionTable();
		}
		
		if (fileFound) {
			System.out.println("Loading previous Auction Table...");
		}
		else {
			System.out.println("No previous auction table detected.");
			System.out.println("Creating new table...");
		}
		
		System.out.print("\nPlease select a username: ");
		auc.username = stdin.nextLine();
		
		String input = " ";
		while (!(input.equals("Q") || (input.equals("q")))) {
			System.out.println("\nMenu:");
			System.out.println("    (D) - Import Data from URL");
			System.out.println("    (A) - Create a new Auction");
			System.out.println("    (B) - Bid on an Item");
			System.out.println("    (I) - Get Info on Auction");
			System.out.println("    (P) - Print All Auctions");
			System.out.println("    (R) - Remove Expired Auctions");
			System.out.println("    (T) - Let Time Pass");
			System.out.println("    (Q) - Quit");
			System.out.println("\n");
			System.out.print("Please select an option: ");
			input = stdin.nextLine();
			
			switch (input) {
				case "D": case "d":
					System.out.print("Please enter a URL: ");
					url = stdin.nextLine();
					try {
						auc.table = AuctionTable.buildFromURL(url);
						System.out.println();
						System.out.println("Loading...");
						System.out.println("Auction data loaded successfully!");
					}
					catch (IllegalArgumentException e) {
						System.out.println("Invalid URL.");
					}
					break;
					
				case "A": case "a": 
					System.out.println("\nCreating new Auction as " + 
							auc.username + ".");
					System.out.print("Please enter an Auction ID: ");
					auctionID = stdin.nextLine();
					
					System.out.print("Please enter an Auction time (hours): ");
					auctionTime = stdin.nextInt();
					stdin.nextLine();
					
					System.out.print("Please enter some Item Info: ");
					itemInfo = stdin.nextLine();
					
					auc.table.putAuction(auctionID, new Auction(auctionID,
							auc.username, "", itemInfo, auctionTime, 0));
					System.out.println("\nAuction " + auctionID + 
							" inserted into table.");
					break;
					
				case "B": case "b": 
					System.out.print("Please enter an Auction ID: ");
					auctionID = stdin.nextLine();
					System.out.println();
					
					if (auc.table.containsKey(auctionID)) {
						temp = auc.table.getAuction(auctionID);
						if (temp.getTimeRemaining() == 0) {
							
							System.out.println("Auction " + auctionID + 
									" is CLOSED");
							
							System.out.println("    Current Bid: $ " + 
									String.format("%,.2f ", 
											temp.getCurrentBid()));
							
							System.out.println();
							System.out.println("You can no longer bid on "
									+ "this item.");
						} // if present but closed
						
						else {
							
							System.out.println("Auction " + auctionID + 
									" is OPEN");
							
							System.out.print("    Current Bid: ");
							
							System.out.println((temp.getCurrentBid() > 0) 
									? String.format("$ %,.2f ", 
											temp.getCurrentBid()) 
											: "NONE");
							System.out.println();
							System.out.print("What would you like to bid?: ");
							newBid = stdin.nextDouble();
							stdin.nextLine();
							if (newBid > temp.getCurrentBid()) {
								try {
									temp.newBid(auc.username, newBid);
									System.out.println("Bid accepted.");
								}
								catch (ClosedAuctionException e) {}
							} // if proposed bid is greater than current
							
							else {
								System.out.println("Proposed bid is not "
										+ "greater than current bid.");
								System.out.println("Bid not accepted.");
							} // if proposed bid is less than / equal to current
						
						} // if present and open
					} // if present
					
					else {
						System.out.println("An auction with ID " + 
							auctionID + " was not found.");
					}
					break;
					
				case "I": case "i": 
					System.out.print("Please enter an Auction ID: ");
					auctionID = stdin.nextLine();
					System.out.println();
					temp = auc.table.getAuction(auctionID);
					
					if (temp == null) {
						System.out.println("An auction with ID " + 
								auctionID + " was not found.");
					} // if getAuction function returned null
					else {
						System.out.println("Auction " + auctionID + ":");
						System.out.println("    Seller: " + 
								temp.getSellerName());
						System.out.println("    Buyer: " + temp.getBuyerName());
						System.out.println("    Time: " +  
								temp.getTimeRemaining() + " hours");
						System.out.println("    Info: " + temp.getItemInfo());
					}
					break;
					
				case "P": case "p": 
					if (auc.table == null) {
						System.out.println("No table to print!");
					}
					else {
						auc.table.printTable();
					}
					break;
					
				case "R": case "r": 
					System.out.println("\nRemoving expired auctions...");
					auc.table.removeExpiredAuctions();
					System.out.println("All expired auctions removed.");
					break;
					
				case "T": case "t": 
					System.out.print("How many hours should pass: ");
					hoursPassed = stdin.nextInt();
					stdin.nextLine();
					System.out.println();
					System.out.println("Time passing...");
					auc.table.letTimePass(hoursPassed);
					System.out.println("Auction times updated.");
					break;
					
				case "Q": case "q": 
					try {
						fos = new FileOutputStream("auctions.obj");
						oos = new ObjectOutputStream(fos);
						oos.writeObject(auc.table);
						System.out.println("\nWriting Auction Table to file...");
						System.out.println("Done!");
						System.out.println("\nGoodbye.");
						System.exit(9001);
					}
					catch (FileNotFoundException e) {
						System.out.println("Exception.");
					}
					
					System.out.println();
					System.out.println("Writing Auction Table to file...");
					System.out.println("Done!");
					System.out.println("\nGoodbye.");
					break;
					
				default: System.out.println("Invalid input."); break;
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
