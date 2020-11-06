import big.data.*;
import java.util.Hashtable;
public class Tester214 {
	public static void main (String[] args){
		
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
		
		
		System.out.println(stringToHours("2 days, 5 hours +"));
		
		
		
	
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
}

 