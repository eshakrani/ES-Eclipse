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

import java.util.Collection;
import java.util.Collections;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@SuppressWarnings("all")
public class WebGraph {
	
	public static final int MAX_PAGES = 40;
	
	private Collection<WebPage> pages;
	private ArrayList<ArrayList<Integer>> links;
	
	/**
	 * no-arg WebGraph constructor
	 */
	public WebGraph() {
		this.pages = new ArrayList<WebPage>();
		this.links = new ArrayList<ArrayList<Integer>>();
	}
	
	/**
	 * constructs a WebGraph object using the indicated files as the source
	 *  for pages and edges
	 *  
	 * @param pagesFile - String of the relative path to the file containing
	 *  the page information
	 *  
	 * @param linksFile - String of the relative path to the file containing
	 *  the link information
	 * 
	 * @precondition both parameters reference text files which exist
	 * 
	 * @precondition the files follow proper format 
	 * 
	 * @postcondition a WebGraph has been constructed and initialized based
	 *  on the text files
	 *  
	 * @return the WebGraph constructed from the text files
	 * 
	 * @throws IllegalArgumentException if either of the files does not 
	 *  reference a valid text file, or if the files are not formatted correctly
	 */
	public static WebGraph buildFromFiles(String pagesFile, String linksFile) 
		throws IllegalArgumentException {
		
		// create a new WebGraph object
		WebGraph w = new WebGraph();
		String s;
		String filetype = "";
		try {
			filetype = "Pages";
			FileReader pageReader = new FileReader(pagesFile);
			BufferedReader pb = new BufferedReader(pageReader);
			
			String[] pageSplit;
			ArrayList<String> words;
			WebPage page;
			int in = 0;
			int sIn = 0;
			int dIn = 0;
			// WebPages
			while ((s = pb.readLine()) != null) {
				
				// if line is empty then continue
				if (s.trim().equals("")) continue;
				
				// separate the line by name and keywords
				pageSplit = s.split(" ");
				
				// set up the WebPage's keyword ArrayList
				words = new ArrayList<String>();
				for (int i = 1; i < pageSplit.length; i++) {
					words.add(pageSplit[i]);
				}
				
				// make a new WebPage 
				page = new WebPage();
				
				// set the attributes of the WebPage
				page.setUrl(pageSplit[0]);
				page.setKeywords(words);
				page.setIndex(in++);
				
				// add the WebPage to the WebGraph
				w.pages.add(page);
			}
			
			// links
			filetype = "Links";
			FileReader linkReader = new FileReader(linksFile);
			BufferedReader lr = new BufferedReader(linkReader);
			
			int size = w.pages.size();
			for (int i = 0; i < size; i++) {
				w.links.add(new ArrayList<Integer>());
			}
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					w.links.get(i).add(0);
				}
			}
			
			String[] linkSplit;
			String source = "";
			ArrayList<Integer> tempList;
			while ((s = lr.readLine()) != null) {
				
				// split the line by source and destination
				linkSplit = s.split(" ");
				
				// get the index of the matching source page
				for (WebPage web : w.pages) {
					if (web.getUrl().equals(linkSplit[0])) {
						sIn = web.getIndex();
						break;
					}
				}
				
				// grab the corresponding source row in the adjacency matrix
				tempList = w.links.get(sIn);
				
				// get the index of the matching destination page
				for (WebPage web : w.pages) {
					if (web.getUrl().equals(linkSplit[1])) {
						dIn = web.getIndex();
						break;
					}
				}
				
				// set the corresponding destination index to 1
				tempList.set(dIn, 1);
				
				// update the adjacency matrix
				w.links.set(sIn, tempList);
			}
			
		}
		catch (FileNotFoundException e) {
			throw new IllegalArgumentException(filetype + " "
					+ "file not found.\n");
		}
		catch (IOException e) {}
		
		// get the correct ranks for each page
		w.updatePageRanks();
		
		return w;
	}
	
	/**
	 * @return Collection<WebPage> - set of pages for this WebGraph
	 */
	public Collection<WebPage> getPages() {
		return this.pages;
	}
	
	/**
	 * @param pages - Collection<WebPage>
	 * 
	 * @postcondition the set of pages for this WebGraph has been set to 
	 *  param pages
	 */
	public void setPages(Collection<WebPage> pages) {
		this.pages = pages;
	}
	
	/**
	 * @return ArrayList<ArrayList<Integer>> - links for this WebGraph
	 */
	public ArrayList<ArrayList<Integer>> getLinks() {
		return this.links;
	}
	
	/**
	 * @param links - ArrayList<ArrayList<Integer>>
	 * 
	 * @postcondition the links for this WebGraph have been set to param links
	 */
	public void setLinks(ArrayList<ArrayList<Integer>> links) {
		this.links = links;
	}
	
	/**
	 * adds a page to the WebGraph
	 * 
	 * @param url - String - the URL of the webpage (must not already exist 
	 *  in the WebGraph)
	 *  
	 * @param keywords - Collection - the keywords associated with the WebPage
	 * 
	 * @precondition url is unique and does not exist as the URL of a WebPage
	 *  already in the graph
	 * 
	 * @precondition url and keywords are not null
	 * 
	 * @postcondition the page has been added to pages at index 'i' and 
	 *  links has been logically extended to include the new row and column
	 *  indexed by 'i'
	 *  
	 * @throws IllegalArgumentException if url is not unique and already exists
	 *  in the graph, or if either argument is null
	 */
	public void addPage(String url, Collection<String> keywords) throws 
		IllegalArgumentException {
		if (this.pages.size() == MAX_PAGES) {
			System.out.println("Max. capacity reached. Cannot add any "
					+ "more pages.\n");
		}
		else if (url == null) {
			throw new IllegalArgumentException("Invalid URL.");
		}
		else if (keywords == null) {
			throw new IllegalArgumentException("Invalid set of keywords.");
		}
		else {
			if (this.urlExists(url)) {
				throw new IllegalArgumentException("Error: " + url + 
					" already exists in the WebGraph. "
					+ "Could not add new WebPage.");
			}
			WebPage page = new WebPage();
			page.setUrl(url);
			page.setKeywords(keywords);
			page.setIndex(this.pages.size());
			
			// add to pages
			this.pages.add(page);
			
			// add to adjacency matrix
			// new row
			this.links.add(new ArrayList<Integer>());
			
			// fill up the row with all 0s by default
			for (int i = 0; i < this.links.get(0).size(); i++) {
				this.links.get(this.links.size() - 1).add(0);
			}
			
			// new column to each row
			for (int i = 0; i < this.links.size(); i++) {
				this.links.get(i).add(0);
			}
			this.updatePageRanks();
			System.out.println(url + " successfully added to the WebGraph!");
		}
	}
	
	/**
	 * adds a link from the WebPage with the URL indicated by source to the 
	 *  WebPage with the URL indicated by destination 
	 *  
	 * @param source - String - the URL of the page which contains the 
	 *  hyperlink to destination
	 *  
	 * @param destination - String - the URL of the page which the hyperlink
	 *  points to
	 *  
	 * @precondition both parameters reference WebPages which exist in the graph
	 * 
	 * @throws IllegalArgumentException if either of the URLs are null or could
	 *  not be found in pages
	 */
	public void addLink(String source, String destination) throws 
		IllegalArgumentException {
		
		// if either URL is null
		if (source == null) {
			throw new IllegalArgumentException("Invalid source URL.");
		}
		if (destination == null) {
			throw new IllegalArgumentException("Invalid destination URL.");
		}
		
		// find the corresponding indices for source and destination
		int i1 = this.indexOfURL(source);
		int i2 = this.indexOfURL(destination);
		
		// if either url does not exist in the set of pages
		if (i1 == -1) {
			throw new IllegalArgumentException("Error: " + source + 
					" could not be found in the WebGraph.");
		}
		if (i2 == -1) {
			throw new IllegalArgumentException("Error: " + destination + 
					" could not be found in the WebGraph.");
		}
		
		if (this.links.get(i1).get(i2) == 0) {
			ArrayList<Integer> temp = this.links.get(i1);
			temp.set(i2, 1);
			this.links.set(i1, temp);
			System.out.println("Link successfully added from " + source + 
					" to " + destination + "!");
		}
		else {
			System.out.println("There is already a link between " + 
					source + " and " + destination);
		}
	}
	
	/**
	 * removes the WebPage from the graph with the given URL
	 * 
	 * @param url - String - the URL of the page to remove from the graph
	 * 
	 * @postcondition the WebPage with the indicated URL has been removed
	 *  from the graph and its corresponding row and column has been removed
	 *  from the adjacency matrix
	 * 
	 * @postcondition all pages that have an index greater than the index
	 *  that was removed should decrease their index value by 1
	 *  
	 * @postcondition if url is null or could not be found in pages, the method
	 *  ignores the input and does nothing
	 */
	public void removePage(String url) {
		
		// if the URL is not null then continue
		if (url != null) {
			
			// if the URL exists in the set of pages
			if (this.urlExists(url)) {
				
				// get the index of the page with the matching URL
				int ind = this.indexOfURL(url);
				
				WebPage t = null;
				
				// find the url in the set of pages and remove it
				for (WebPage w : this.pages) {
					if (w.getUrl().equals(url)) {
						t = w;
						break;
					}
				}
				this.pages.remove(t);
				
				// remove from the adjacency matrix accordingly
				this.links.remove(ind);
				for (int i = 0; i < this.links.size(); i++) {
					this.links.get(i).remove(ind);
				}
				
				// update the index for each page higher than the removed one
				for (WebPage w : this.pages) {
					if (w.getIndex() > ind) {
						w.setIndex(w.getIndex() - 1);
					}
				}
				
				this.updatePageRanks();
				System.out.println(url + " has been removed from the graph!");
			}
			else {
				System.out.println("Error: " + url + 
						" could not be found in the WebGraph.");
			}
		}
	}
	
	/**
	 * removes the link from the WebPage with the URL indicated by source to
	 *  the WebPage indicated by destination
	 *  
	 * @param source - String - the URL of the WebPage to remove the link
	 * 
	 * @param destination - String - the URL of the link to be removed
	 * 
	 * @postcondition the entry in links for the specified hyperlink has been
	 *  set to 0 (no link)
	 *  
	 * @postcondition if either of the URLs could not be found, the input is 
	 *  ignored and the method does nothing
	 */
	public void removeLink(String source, String destination) {
		int s = this.indexOfURL(source);
		int d = this.indexOfURL(destination);
		
		// if both URLs were found in the pages
		if (s != -1 && d != -1) {
			ArrayList<Integer> temp = this.links.get(s);
			temp.set(d, 0);
			this.links.set(s, temp);
			this.updatePageRanks();
			System.out.println("Link removed from " + source + 
					" to " + destination + "!");
		}
		else if (s == -1) {
			System.out.println("Error: " + source + 
					" could not be found in the WebGraph.");
		}
		else if (d == -1) {
			System.out.println("Error: " + destination + 
					" could not be found in the WebGraph.");
		}
	}
	
	/**
	 * @param url - String - url to be checked
	 * 
	 * @return boolean - true if param url exists in pages, false otherwise
	 */
	public boolean urlExists(String url) {
		for (WebPage w : this.pages) {
			if (w.getUrl().equals(url)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @param url - String - url of page to get index of
	 * 
	 * @return int - index of URL in pages, -1 if URL was not found
	 */
	public int indexOfURL(String url) {
		int i = -1;
		for (WebPage w : this.pages) {
			if (w.getUrl().equals(url)) {
				i = w.getIndex();
			}
		}
		return i;
	}
	
	/**
	 * corrects the links for each WebPage based on the adjacency matrix
	 * 
	 * @postcondition each WebPage will have the correct set of links
	 */
	public void correctLinks() {
		for (WebPage w : this.pages) {
			for (int i = 0; i < this.links.get(w.getIndex()).size(); i++) {
				if (this.links.get(w.getIndex()).get(i) == 1) {
					if (!w.getLinks().contains(new Integer(i))){
						w.plusLink(i);
					}
				}
				else {
					w.getLinks().remove(new Integer(i));
				}
			}
			for (int i : w.getLinks()) {
				if (i >= this.links.size()) {
					w.getLinks().remove(new Integer(i));
				}
			}
			w.getLinks().sort(null);
		}
	}
	
	/**
	 * calculates and assigns the PageRank for every page in the WebGraph
	 * 
	 * @postcondition all WebPages in the graph have been assigned their
	 *  proper PageRank
	 */
	public void updatePageRanks() {
		String source = "";
		int count = 0;
		int ind = 0;
		for (WebPage w : this.pages) {
			count = 0;
			ind = w.getIndex();
			for (int i = 0; i < this.links.size(); i++) {
				if (this.links.get(i).get(ind) == 1) {
					count++;
				}
			}
			w.setRank(count);
		}
	}
	
	/**
	 * prints the WebGraph in tabular form
	 */
	public void printTable() {
		String header = String.format("%-10s%-18s%-10s%-23s%s", 
				"Index", "URL", "PageRank", "Links", "Keywords");
		System.out.println(header);
		for (int i = 0; i < 27; i++) {
			System.out.print("----");
		}
		System.out.println();
		for (WebPage w : this.pages) {
			System.out.println(w);
		}
	}
	
	/**
	 * sorts the pages in the WebGraph in ascending order by index
	 * 
	 * @postcondition the pages in the WebGraph are sorted in ascending order 
	 * 	based on index
	 */
	public void sortByIndex() {
		Collections.sort((ArrayList<WebPage>)this.pages, new IndexComparator());
	}
	
	/**
	 * sorts the pages in the WebGraph in descending order by index
	 * 
	 * @postcondition the pages in the WebGraph are sorted in descending order 
	 * 	based on index
	 */
	public void decIndex() {
		this.sortByIndex();
		Collections.reverse((ArrayList<WebPage>)this.pages);
	}
	
	/**
	 * sorts the pages in the WebGraph in lexicographical order by URL
	 * 
	 * @postcondition the pages in the WebGraph are sorted in alphabetical order 
	 * 	based on URL
	 */
	public void sortByURL() {
		Collections.sort((ArrayList<WebPage>)this.pages, new URLComparator());
	}
	
	/**
	 * sorts the pages in the WebGraph in descending lexicographical URL order
	 * 
	 * @postcondition the pages in the WebGraph are sorted in reverse 
	 *  alphabetical order based on URL
	 */
	public void decURL() {
		this.sortByURL();
		Collections.reverse((ArrayList<WebPage>)this.pages);
	}
	
	/**
	 * sorts the pages in the WebGraph in ascending order by rank
	 * 
	 * @postcondition the pages in the WebGraph are sorted in ascending order 
	 * 	based on rank
	 */
	public void sortByRank() {
		Collections.sort((ArrayList<WebPage>)this.pages, new RankComparator());
	}
	
	/**
	 * sorts the pages in the WebGraph in descending order by rank
	 * 
	 * @postcondition the pages in the WebGraph are sorted in descending order 
	 * 	based on rank
	 */
	public void decRank() {
		this.sortByRank();
		Collections.reverse((ArrayList<WebPage>)this.pages);
	}
	
	/**
	 * searches for the keyword param s within the set of pages and displays
	 *  the pages that contain keyword s in order of decreasing rank
	 *  
	 * @param s - String - the keyword to search for
	 * 
	 * @postcondition the set of applicable pages has been displayed to the 
	 *  console in descending order by rank
	 *  - if no pages were found, then an error message is displayed instead
	 */
	public void searchAndDisplay(String s) {
		String header = String.format("%-7s%-12s%s", "Rank", "PageRank", "URL");
		header += "\n---------------------------------------------";
		int rank = 1;
		ArrayList<String> entries = new ArrayList<String>();
		this.sortByRank();
		for (WebPage w : this.pages) {
			if (w.getKeywords().contains(s)) {
				entries.add(String.format("  %-3s|    %-6s| %s", rank++, 
						w.getRank(), w.getUrl()));
			}
		}
		if (rank == 1) {
			System.out.println("The keyword " + s + " was not found.");
		}
		else {
			System.out.println(header);
			for (String st : entries) {
				System.out.println(st);
			}
		}
	}
}
