import java.util.NoSuchElementException;
public class SinglyLinkedList<T> implements CSE214List<T> {

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
		//////////////////////////////////
		public String toString() {
			return "" + element;
		}//////////////////////////////
	}
	
	private Node<T> first;
	private Node<T> cursor;
	
	public SinglyLinkedList() {
		this.first = null;
	}
	
	public SinglyLinkedList(T ... elements) {
		this.first = new Node<T>(elements[0]);
		this.cursor = first;
		Node<T> previous = first;
		for (int i = 1; i < elements.length; i++) {
			Node<T> current = new Node<T>(elements[i]);
			previous.next = current;
			previous = current;
		}
	}
	//////////////////////////////////////////////////
	public String toString() {
		String s = "";
		Node<T> temp = this.first;
		while(temp != null) {
			s += temp.element + " ";
			temp = temp.next;
		}
		return s;
	}
	////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////
	public static void main(String[] args) {
		SinglyLinkedList<String> strings = new SinglyLinkedList<>("a", "b", "c", "d", "e", "f");
//		strings.add("0", 2);
//		strings.remove("b");
//		strings.remove("d");
		System.out.println(strings.find("i"));
		System.out.println(strings.size());
	}
	//////////////////////////////////////////////////////////////////////////////
	@Override
	public int size() {
		int size = 0;
		Node<T> temp = this.first;
		while(temp != null) {
			size++;
			temp = temp.next;
		}
		return size;
	}

	
	@Override
	public void add(T elem, int index) {
		Node<T> n = new Node<T>(elem);
		if (index == 0) {
			this.add(elem);
		}
		int ind = 0;
		Node<T> temp = this.first;
		while(temp != null) {
			if(ind + 1 == index) {
				n.next = temp.next;
				temp.next = n;
				break;
			}
			temp = temp.next;
			ind++;
		}
	}

	@Override
	public void add(T elem) {
		Node<T> temp = new Node<T>(elem);
		temp.next = this.first;
		this.first = temp;
	}

	@Override
	public T remove(int index) {
		T res = null;
		if (index == 0) {
			res = this.first.element;
			this.first = this.first.next;
		}
		else {
			int ind = 0;
			Node<T> temp = this.first;
			while(temp != null) {
				if(ind + 1 == index) {
					res = temp.next.element;
					temp.next = temp.next.next;
					break;
				}
				temp = temp.next;
				ind++;
			}
		}
		return res;
	}

	@Override
	public void remove(T elem) throws NoSuchElementException {
		T res = null;
		if (this.first.element == elem) {
			res = this.first.element;
			this.first = this.first.next;
		}
		else {
			Node<T> temp = this.first;
			while(temp.next != null) {
				if (temp.next.element == elem) {
					res = temp.next.element;
					temp.next = temp.next.next;
					break;
				}
				temp = temp.next;
			}
		}
		if (res == null) {
			throw new NoSuchElementException("No such element " + elem + " in list.");
		}
	}

	@Override
	public boolean find(T elem) {
		Node<T> temp = this.first;
		while(temp != null) {
			if(temp.element == elem) {
				return true;
			}
			temp = temp.next;
		}
		return false;
	}

	

}
