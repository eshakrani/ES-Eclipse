import big.data.*;
import hw6.AuctionTable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Hashtable;
import java.util.Scanner;
public class Tester214 implements Serializable {
	int data = 5;
	public Tester214(int data) {this.data = data;}
	public static void main (String[] args) throws FileNotFoundException, 
		IOException, ClassNotFoundException, IllegalArgumentException {
		Scanner stdin = new Scanner(System.in);
		
		AuctionTable a;
		
		
		
//		for (int i = 0; i < 10; i++) {
//			System.out.println(fib(i));
//		}
		
		
		int[] b = {4, 2, 9, 1, 8, 5, 3, 10, 6, 7};
		sort(b, 0, b.length - 1);
		System.out.println(b.length);
		for (int i = 0; i < b.length; i++) {
			System.out.print(b[i] + " ");
		}
	}
	
	public static void mergeSort(int[] data, int first, int n) {
		int n1, n2;
		if (n > 1) {
			n1 = n / 2;
			n2 = n - n1;
			mergeSort(data, first, n1);
			mergeSort(data, first + n1, n2);
			merge(data, first, n1, n2);
		}
	}
	public static void sort(int[] arr, int l, int r) {
		if (l < r) {
			// find the middle point
			int m = (l + r) / 2;
			
			// sort first and second halves
			sort(arr, l, m);
			sort(arr, m + 1, r);
			
			// merge the sorted halves
			merge(arr, l, m, r);
		}
	}
	
	/*
	 * merges two subarrays of arr[]
	 * first subarray is arr[l...m]
	 * second subarray is arr[m+1...r]
	 */
	public static void merge(int arr[], int l, int m, int r) {
		
		// find sizes of the two subarrays to be merged
		int size1 = m - l + 1;
		int size2 = r - m;
		
		// create temp arrays
		int[] ltemp = new int[size1];
		int[] rtemp = new int[size2];
		
		// copy data to temp arrays
		for (int i = 0; i < size1; i++) {
			ltemp[i] = arr[l + i];
		}
		for (int j = 0; j < size2; j++) {
			rtemp[j] = arr[m + 1 + j];
		}
		
		// merge the temp arrays
		
		// initial indices of first and second subarrays
		int i = 0; 
		int j = 0;
		
		// initial index of merged subarray array
		int k = l;
		while (i < size1 && j < size2) {
			if (ltemp[i] <= rtemp[j]) {
				arr[k] = ltemp[i];
				i++;
			}
			else {
				arr[k] = rtemp[j];
				j++;
			}
			k++;
		}
		
		// copy remaining elements of ltemp if any
		while (i < size1) {
			arr[k] = ltemp[i];
			i++;
			k++;
		}
		
		// copy remaining elements of rtemp if any
		while (j < size2) {
			arr[k] = rtemp[j];
			j++;
			k++;
		}
	}
	
	public static int product (int m, int k) {
		if (k == 1) return m;
		return m + product(m, k - 1);
	}
	
	public static int fib (int n) {
		if (n == 0 || n == 1) return n;
		else return fib(n - 1) + fib(n - 2);
	}
	
	
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
	
	public static int stringToHours(String time) {
//		String monthString = "";
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

	public String toString() {
		String s = "";
		s += "Data: " + this.data;
		return s;
	}
}

 