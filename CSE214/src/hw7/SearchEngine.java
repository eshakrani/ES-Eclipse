package hw7;

/**
 * @author Eshan Shakrani
 * @ID: 112802596
 * @email: eshan.shakrani@stonybrook.edu
 * @Assignment #7
 * @Class: CSE 214
 * @Recitation: R03
 * @Instructor: Dylan Andres
 */

import java.util.Scanner;
import java.util.Collection;
import java.util.Collections;
import java.awt.EventQueue;
import java.util.ArrayList;

@SuppressWarnings("all")
public class SearchEngine {
	public static final String PAGES_FILE = "pages.txt";
	public static final String LINKS_FILE = "links.txt";
	
	private WebGraph web;
	
	public static void main (String[] args) {
		


		
		
		
		
		
		
		
		
		
		
		
		boolean flag = false;
		Scanner stdin = new Scanner(System.in);
		
		String input = "";
		String source = "";
		String destination = "";
		String url = "";
		String words = "";
		String[] wordSplit;
		Collection<String> c;
		
		SearchEngine se = new SearchEngine();
		try {
			se.web = WebGraph.buildFromFiles(PAGES_FILE, LINKS_FILE);
		}
		catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
		while (true) {
			se.web.sortByIndex();
			se.web.updatePageRanks();
			se.web.correctLinks();
			priMenu();
			input = stdin.nextLine().toUpperCase().trim();
			switch (input) {
				case "AP": 
					System.out.print("Enter a URL: ");
					url = stdin.nextLine().toLowerCase().trim();
					System.out.print("Enter keywords (space-separated): ");
					words = stdin.nextLine();
					wordSplit = words.split(" ");
					c = new ArrayList<String>();
					for (String s : wordSplit) {
						c.add(s);
					}
					try {
						System.out.println();
						se.web.addPage(url, c);
					}
					catch (IllegalArgumentException e) {
						System.out.println(e.getMessage());
					}
					System.out.println();
					break;
				case "RP": 
					System.out.print("Enter a URL: ");
					url = stdin.nextLine().toLowerCase().trim();
					System.out.println();
					se.web.removePage(url);
					System.out.println();
					break;
				case "AL": 
					System.out.print("Enter a source URL: ");
					source = stdin.nextLine().toLowerCase().trim();
					System.out.print("Enter a destination URL: ");
					destination = stdin.nextLine().toLowerCase().trim();
					System.out.println();
					try {
						se.web.addLink(source, destination);
					}
					catch (IllegalArgumentException e) {
						System.out.println(e.getMessage());
					}
					System.out.println();
					break;
				case "RL": 
					System.out.print("Enter a source URL: ");
					source = stdin.nextLine().toLowerCase().trim();
					System.out.print("Enter a destination URL: ");
					destination = stdin.nextLine().toLowerCase().trim();
					System.out.println();
					se.web.removeLink(source, destination);
					System.out.println();
					break;
				case "P": 
					secMenu();
					input = stdin.nextLine().toUpperCase().trim();
					switch (input) {
						case "I": 
							se.web.sortByIndex();
							break;
						case "U":
							se.web.sortByURL();
							break;
						case "R": 
							se.web.sortByRank();
							break;
						default: System.out.println("Invalid input."); break;
					}
				
					System.out.println();
					se.web.printTable();
					
					System.out.println();
					break;
				case "S": 
					System.out.print("Search keyword: ");
					words = stdin.nextLine().toLowerCase().trim();
					System.out.println();
					se.web.searchAndDisplay(words);
					System.out.println();
					break;
				case "Q": 
					System.out.println();
					System.out.println("Goodbye.");
					System.exit(9000);
					break;
				default: System.out.println("Invalid input.\n"); break;
			}
			
		}
	}
	
	/**
	 * prints the primary menu to the console
	 */
	public static void priMenu() {
		System.out.println("Menu: ");
		System.out.println("    (AP) - Add a new page to the graph.");
		System.out.println("    (RP) - Remove a page from the graph.");
		System.out.println("    (AL) - Add a link between pages in the graph.");
		System.out.println("    (RL) - Remove a link between "
				+ "pages in the graph.");
		System.out.println("    (P) - Print the graph.");
		System.out.println("    (S) - Search for pages with a keyword.");
		System.out.println("    (Q) - Quit.");
		System.out.println();
		System.out.print("Please select an option: ");
	}
	
	/**
	 * prints the secondary menu to the console
	 * 
	 * to be used after calling print function in primary menu
	 */
	public static void secMenu() {
		System.out.println("    (I) Sort based on index (ASC)");
		System.out.println("    (U) Sort based on URL (ASC)");
		System.out.println("    (R) Sort based on rank (DSC)");
		System.out.println();
		System.out.print("Please select an option: ");
	}
}
