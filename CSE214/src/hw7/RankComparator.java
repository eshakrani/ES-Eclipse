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

import java.util.Comparator;

@SuppressWarnings("all")
public class RankComparator implements Comparator {
	public int compare(Object o1, Object o2) {
		WebPage w1 = (WebPage) o1;
		WebPage w2 = (WebPage) o2;
		if (w1.getRank() == w2.getRank()) {
			return 0;
		}
		else if (w1.getRank() < w2.getRank()) {
			return 1;
		}
		else {
			return -1;
		}
	}
}
