import java.util.NoSuchElementException;
public class SinglyLinkedStack<T> implements CSE214Stack<T> {

	class Node<E> {
		E element;
		Node<E> next;
		
		Node(E element, Node<E> next) {
			this.element = element;
			this.next = next;
		}
		
		Node(E element) {
			this(element, null);
		}
	}
	
	private Node<T> top;
	
	public SinglyLinkedStack() {
		this.top = null;
	}

	public SinglyLinkedStack(T ... elements) {
		this.top = new Node<T>(elements[0]);
		for (int i = 1; i < elements.length; i++) {
			Node<T> current = new Node<T>(elements[i]);
			current.next = this.top;
			this.top = current;
		}
	}
	
	public int size() {
		int size = 0;
		Node<T> temp = this.top;
		while(temp != null) {
			size++;
			temp = temp.next;
		}
		return size;
	}

	public T peek() throws NoSuchElementException {
		if (this.size() == 0) {
			throw new NoSuchElementException("No such element.");
		}
		return this.top.element;
	}	

	public T pop() throws NoSuchElementException {
		if (this.size() == 0) {
			throw new NoSuchElementException("No such element.");
		}
		T res = this.top.element;
		this.top = this.top.next;
		return res;
	}

	public void push(T elem) {
		Node<T> n = new Node<T>(elem);
		n.next = this.top;
		this.top = n;
	}

	public boolean isEmpty() {
		return (this.size() == 0);
	}
	
	
	public String toString() {
		Node<T> temp = this.top;
		String s = "";
		while (temp != null) {
			s += temp.element + " ";
			temp = temp.next;
		}
		return s;
	}
}
