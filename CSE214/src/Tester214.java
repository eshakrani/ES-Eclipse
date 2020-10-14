import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Tester214 {
	public static void main (String[] args){
		Scanner stdin = new Scanner(System.in);
		Test a = new Test();
		System.out.println(a.t);
		change(a);
		System.out.println(a.t);
	}
	public static void fun(int[] a) {
		for (int i = 0; i < a.length; i++) {
			a[i]++;
		}
	}
	public static void change(Test t) {
		t.t = 5;
	}
}

class Test {
	public int t;
	public Test() {
		t = 0;
	}
	public Test(int a) {
		t = a;
	}
}
 