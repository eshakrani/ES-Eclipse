import java.util.InputMismatchException;
import java.util.Scanner;
public class PracticeStack {
	public static void main (String[] args) throws EmptyStackException {
		ListStack s = new ListStack();
		String input = " ";
		int item;
		Scanner stdin = new Scanner(System.in);
		while (!input.equals("q")) {
			System.out.println("\nP: Push");
			System.out.println("O: Pop");
			System.out.println("I: Peek");
			System.out.println("U: Print");
			System.out.println("Q: Quit");
			System.out.print("Input: ");
			
			try {
				input = stdin.nextLine();
				
				switch(input) {
					case "p": case "P": 
						System.out.print("Enter item: ");
						item = stdin.nextInt();
						stdin.nextLine();
	//					System.out.println(item);
						s.push(item);
						break;
					case "o": case "O":
						item = (int)s.pop();
						System.out.println(item);
						break;
					case "i": case "I": 
						item = (int)s.peek();
						System.out.println(item);
						break;
					case "u": case "U": 
						s.display();
						break;
					case "q": case "Q": 
						System.exit(2);
				}
			}
			catch (InputMismatchException e) {
				System.out.println("WRONG INPUT");
			}
		}
	}
}

class ListStack {
	lNode top;
	int size;
	public ListStack() {
		this.top = null;
		this.size = 0;
	}
	public int size() {
		return this.size;
	}
	public boolean isEmpty() {
		return (this.size == 0);
	}
	public void push(Object data) {
		lNode newNode = new lNode(data);
		newNode.setNext(this.top);
		this.top = newNode;
		this.size++;
	}
	public Object pop() throws EmptyStackException {
		if (this.top == null) {
			throw new EmptyStackException();
		}
		else {
			Object answer;
			answer = this.top.getData();
			this.top = this.top.getNext();
			this.size--;
			return answer;
		}
	}
	public Object peek() throws EmptyStackException {
		if (this.top == null) {
			throw new EmptyStackException();
		}
		else {
			return this.top.getData();
		}
	}
	public void display() throws EmptyStackException {
		ListStack temp = new ListStack();
		Object data;
		lNode n = this.top;
		while (this.top != null) {
			data = this.pop();
			temp.push(data);
		}
		n = temp.top;
		System.out.println("BOTTOM");
		while (temp.top != null) {
			data = temp.pop();
			System.out.println(data);
			this.push(data);
		}
		System.out.println("TOP\n");
	}
}

class lNode {
	private Object data;
	private lNode next;
	public lNode(Object data) {
		this.data = data;
		this.next = null;
	}
	public Object getData() {
		return this.data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public lNode getNext() {
		return this.next;
	}
	public void setNext(lNode next) {
		this.next = next;
	}
	public String toString() {
		String s = "";
		s += this.data;
		return s;
	}
}

class EmptyStackException extends Exception {
	public EmptyStackException() {
		System.out.println("Stack is empty.\n");
	}
}
