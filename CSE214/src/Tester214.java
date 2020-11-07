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
		IOException, ClassNotFoundException {
		Scanner stdin = new Scanner(System.in);
		
		AuctionTable a;
		
		DataSource ds = DataSource.connect("http://tinyurl.com/p7vub89").load();
		String s[] = ds.fetchStringArray("listing/seller_info/seller_name");
		for (String st : s) {
			System.out.println(st + "\tLength: " + st.length());
		}
		
		System.out.println("\n" + s[9]);
		System.out.println("\n");
		String split[] = s[9].split(" ");
		System.out.println("\nSize: " + split.length);
		for (String st : split) {
			System.out.println(st);
		}
		for (String st : split) {
			System.out.println(st.isBlank());
		}
		
		System.out.println("\n");
		
		String fin = "";
		for (String st : split) {
			if (!st.isBlank()) {
				fin += " " + st;
			}
		}
		System.out.println("Fin: " + fin);
		
		fin = fin.trim();
		System.out.println("New fin: " + fin);
		System.out.println(split[0] + split[split.length - 1]);
		
//		String token = stdin.next();
//		System.out.println(token);
//		String nums[] = {
//				"1,011.234", "123.4", "1234.0"
//		};
//		
//		double d[] = new double[nums.length];
//		
//		for (int i = 0; i < nums.length; i++) {
//			d[i] = convertToDouble(nums[i]);
//		}
//		
//		for (double s : d) {
//			System.out.println(s);
//		}
	
	
//		Hashtable<Object, Object> h = new Hashtable<Object, Object>();
//		h.put("Key", "Value");
//		
//		h.put("Key2", "Value2");		
		
		
	
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

 