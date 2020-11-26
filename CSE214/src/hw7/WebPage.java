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
import java.util.ArrayList;

@SuppressWarnings("all")
public class WebPage {
	
	private String url;
	private int index;
	private int rank;
	private Collection<String> keywords;
	private ArrayList<Integer> links;
	
	/**
	 * default no-arg constructor
	 */
	public WebPage() {
		this.url = "";
		this.index = 0;
		this.rank = 0;
		this.keywords = new ArrayList<String>();
		this.links = new ArrayList<Integer>();
	}
	
	/**
	 * @return url of this WebPage
	 */
	public String getUrl() {
		return this.url;
	}
	
	/**
	 * @param url - String
	 * 
	 * @postcondition url of this WebPage has been set to param url
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * @return index of this WebPage
	 */
	public int getIndex() {
		return this.index;
	}
	
	/**
	 * @param index - int
	 * 
	 * @postcondition index of this WebPage has been set to param index
	 */
	public void setIndex(int index) {
		this.index = index;
	}
	
	/**
	 * @return rank of this WebPage
	 */
	public int getRank() {
		return this.rank;
	}
	
	/**
	 * @param rank - int
	 * 
	 * @postcondition rank of this WebPage has been set to param rank
	 */
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	/**
	 * @return keywords of this WebPage
	 */
	public Collection getKeywords() {
		return this.keywords;
	}
	
	/**
	 * @param keywords - Collection
	 * 
	 * @postcondition keywords of this WebPage have been set to param keywords
	 */
	public void setKeywords(Collection keywords) {
		this.keywords = keywords;
	}
	
	/**
	 * @return ArrayList<Integer> - the set of links for this WebPage
	 */
	public ArrayList<Integer> getLinks() {
		return this.links;
	}
	
	/**
	 * @param links - ArrayList<Integer>
	 * 
	 * @postcondition links of this WebPage have been set to param links
	 */
	public void setLinks(ArrayList<Integer> links) {
		this.links = links;
	}
	
	/**
	 * adds a link param n to the links for this WebPage
	 * 
	 * @param n - int - index of page to add link for
	 * 
	 * @postcondition param n has been added to this WebPage's set of links
	 */
	public void plusLink(int n) {
		this.links.add(n);
	}
	
	/**
	 * @return string of WebPage data members in tabular form
	 */
	public String toString() {
		String s = "";
		s += "  " + String.format("%-4s", this.index);
		s += "| " + String.format("%-19s", this.url);
		s += "|    " + String.format("%-5s", this.rank);
		s += "| " + String.format("%-21s", this.linksAsString());
		s += "| ";
		for (int i = 0; i < this.keywords.size() - 1; i++) {
			s += this.keywords.toArray()[i] + ", ";
		}
		s += this.keywords.toArray()[this.keywords.size() - 1];
		return s;
	}
	
	/**
	 * @return String representation of this WebPage's links
	 * 	- "***" if there are no links for this WebPage
	 */
	public String linksAsString() {
		String s = "";
		if (this.links.size() == 0) {
			//s += "***";
		}
		else if (this.links.size() == 1) {
			s += this.links.get(0);
		}
		else {
			for (int i = 0; i < this.links.size() - 1; i++) {
				s += this.links.get(i) + ", ";
			}
			s += this.links.get(links.size() - 1);
		}
		return s;
	}
	
	public static void main(String[] args) {
		WebPage w = new WebPage();
		w.setUrl("google.com");
		w.keywords.add("search");
		w.keywords.add("knowledge");
		w.keywords.add("tech");
		System.out.print(w);
	}
}
