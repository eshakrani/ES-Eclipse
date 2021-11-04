package HW2;

public class CSE214TreeSet<E extends Comparable<E>> extends BinaryTree implements CSE214Set<E> {
	
//	private E             value;
    private String        color;
//    private BinaryTree<E> left;
//    private BinaryTree<E> right;
    
    public static enum Color {
    	RED ("red"), 
    	BLACK ("black");
    	
    	private String color;
    	
    	private Color(String color) {
    		this.color = color;
    	}
    }
    
	public CSE214TreeSet(E e) {
		super(e);
//		this.color = Color.RED;
    	this.color = "red";
	}
	
	public CSE214TreeSet() {
		super();
	}
	
	public int size() {
		return size(this);
	}
	
	public int size(CSE214TreeSet t) {
		if (t == null) {
			return 0;
		}
		else {
			return (size((CSE214TreeSet) t.getLeft()) + 1 + size((CSE214TreeSet) t.getRight()));
		}
	}

	@Override
	public boolean contains(Object o) {
//		E tempE = (E)o;
		if (this.getValue() == null) {
			return false;
		}
		CSE214TreeSet n = this;
//		if (o instanceof String) {
//			while (n != null) {
//				int cmp = ((String) o).compareTo((String) n.getValue());
//				if (cmp == 0) {
//					return true;
//				}
//				else if (cmp < 0) {
//					n = (CSE214TreeSet)n.getLeft();
//				}
//				else {
//					n = (CSE214TreeSet)n.getRight();
//				}
//			}
//			return false;
//		}
//		else if (o instanceof Integer) {
//			while (n != null) {
//				int cmp = ((Integer) o).compareTo((Integer) n.getValue());
//				if (cmp == 0) {
//					return true;
//				}
//				else if (cmp < 0) {
//					n = (CSE214TreeSet)n.getLeft();
//				}
//				else {
//					n = (CSE214TreeSet)n.getRight();
//				}
//			}
//			return false;
//		}
//		else if (o instanceof Double) {
//			while (n != null) {
//				int cmp = ((Double) o).compareTo((Double) n.getValue());
//				if (cmp == 0) {
//					return true;
//				}
//				else if (cmp < 0) {
//					n = (CSE214TreeSet)n.getLeft();
//				}
//				else {
//					n = (CSE214TreeSet)n.getRight();
//				}
//			}
//			return false;
//		}
		while (n != null) {
//			int cmp = ((Comparable<? super E>) o).compareTo((E) n.getValue());
//			int cmp = (((Comparable<E>) o).compareTo((E) n.getValue()));
//			int cmp = ((E)o).compareTo((E) n.getValue());
			int cmp = ((E)n.getValue()).compareTo((E)o);
//			if (((Comparable<E>) o).compareTo((E) n.getValue()) < 0) {
//				n = (CSE214TreeSet) n.getLeft();
//			}
//			else if (((Comparable<E>) o).compareTo((E) n.getValue()) > 0) {
//				n = (CSE214TreeSet) n.getRight();
//			}
//			else {
//				return true;
//			}
			if (cmp < 0) {
				n = (CSE214TreeSet) n.getLeft();
			}
			else if (cmp > 0) {
				n = (CSE214TreeSet) n.getRight();
			}
			else {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean add(E e) {
		// if adding to an empty set
		if (this.getValue() == null) {
			this.setValue(e);
			this.color = "black";
			return true;
		}
		if (this.contains(e)) {
			return false;
		}
		this.addHelper(this, e);
		return true;
	}
	public CSE214TreeSet addHelper(CSE214TreeSet node, E e) {
		if (node == null) {
			return new CSE214TreeSet<>(e);
		}
		// move to the left if passed value is less than current node
		if (e.compareTo((E) node.getValue()) < 0) {
			node.setLeft(addHelper((CSE214TreeSet) node.getLeft(), e));
		}
		// move to the right if passed value is greater than current node
		else if (e.compareTo((E) node.getValue()) > 0) {
			node.setRight(addHelper((CSE214TreeSet) node.getRight(), e));
		}
		return node;
	}

//	@Override
	public int compareTo(E o) {
//		if (this.getValue() == null) {
//			return 1;
//		}
		E tempE = (E)this.getValue();
		int cmp = tempE.compareTo(o);
		return cmp;
	}
	public static void main(String[] args) {
		CSE214TreeSet<String> root = new CSE214TreeSet<>("D");
		
		CSE214TreeSet<String> node1 = new CSE214TreeSet<>("B");
		CSE214TreeSet<String> node2 = new CSE214TreeSet<>("F");
		CSE214TreeSet<String> node3 = new CSE214TreeSet<>("A");
		CSE214TreeSet<String> node4 = new CSE214TreeSet<>("C");
		CSE214TreeSet<String> node5 = new CSE214TreeSet<>("E");
		CSE214TreeSet<String> node6 = new CSE214TreeSet<>("G");
		root.setLeft(node1);
		root.setRight(node2);
		node1.setLeft(node3);
		node1.setRight(node4);
		node2.setLeft(node5);
		node2.setRight(node6);
		try {
			System.out.println(root.add("m"));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(root);
		CSE214TreeSet<String> find = new CSE214TreeSet<>("find");
		try {
			System.out.println(root.contains(find));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		String words[] = {"find", "A", "B", "C", "D", "E", "F", "G"};
		for (String word : words) {
			System.out.println(word + " " + root.contains(word));
		}
		
	}




}
