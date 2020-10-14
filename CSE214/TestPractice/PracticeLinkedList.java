
public class PracticeLinkedList {
	public static void main(String[] args) throws EmptyListException {
		DLL d = new DLL();
		
	
		
		d.display();
		System.out.println("Head: " + d.head);
		System.out.println("Tail: " + d.tail);
		System.out.println("Cursor: " + d.cursor);
	}
}

class DLL {
	DNode head;
	DNode cursor;
	DNode tail;
	int size;
	public DLL() {
		this.head = null;
		this.tail = null;
		this.cursor = null;
		this.size = 0;
	}
	public int size() {
		return this.size;
	}
	public boolean isEmpty() {
		return this.head == null;
	}
	
	
	
	// methods
	
	

	public void display() {
		DNode temp = this.head;
		while (temp != null) {
			System.out.println(temp);
			temp = temp.getNext();
		}
	}
}

class DNode {
	private Object data;
	private DNode next;
	private DNode prev;
	
	public Object getData() {
		return this.data;	
	}
	public void setData(Object data) {
		this.data = data;
	}
	public DNode getNext() {
		return this.next;
	}
	public void setNext(DNode next) {
		this.next = next;
	}
	public DNode getPrev() {
		return this.prev;
	}
	public void setPrev(DNode prev) {
		this.prev = prev;
	}
	public DNode() {
		this.data = null;
		this.next = null;
		this.prev = null;
	}
	public DNode(Object data) {
		this.data = data;
		this.next = null;
		this.prev = null;
	}
	public String toString() {
		String s = "";
		s += "Object: " + this.getData();
		return s;
	}
}

class EmptyListException extends Exception {
	public EmptyListException() {
		System.out.println("ERROR: List is empty.\n");
	}
}
