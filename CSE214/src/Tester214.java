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
		
		
		System.out.println(product(3, 5));
		
	
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

 