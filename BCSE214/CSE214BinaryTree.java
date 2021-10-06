
public class CSE214BinaryTree<T> implements CSE214Tree<T> {
	
	class BinaryTreeNode<E> {
		E element;
		BinaryTreeNode<E> left;
		BinaryTreeNode<E> right;
		
		BinaryTreeNode(E elem, BinaryTreeNode<E> left, BinaryTreeNode<E> right) {
			this.element = elem;
			this.left = left;
			this.right = right;
		}
		
		BinaryTreeNode(E elem, BinaryTreeNode<E> left) {
			this(elem, left, null);
		}
		
		BinaryTreeNode(E elem) {
			this(elem, null, null);
		}
		
		String preorderHelper(String st) {
			st += "" + this.element;
			if (this.left != null) {
				st = this.left.preorderHelper(st);
			}
			if (this.right != null) {
				st = this.right.preorderHelper(st);
			}
			return st;
		}
		
		String postorderHelper(String st) {
			if (this.left != null) {
				st = this.left.postorderHelper(st);
			}
			if (this.right != null) {
				st = this.right.postorderHelper(st);
			}
			st += "" + this.element;
			return st;
		}
		
		String inorderHelper(String st) {
			if (this.left != null) {
				st = this.left.inorderHelper(st);
			}
			st += "" + this.element;
			if (this.right != null) {
				st = this.right.inorderHelper(st);
			}
			return st;
		}
		
		int numNodesHelper(BinaryTreeNode node) {
			if (node == null) {
				return 0;
			}
			else {
				return 1 + numNodesHelper(node.left) + numNodesHelper(node.right);
			}
		}
		
		int depthHelper(BinaryTreeNode node) {
			if (node == null) {
				return 0;
			}
			else {
				int leftDepth = depthHelper(node.left);
				int rightDepth = depthHelper(node.right);
				
				if (leftDepth > rightDepth) {
					return (leftDepth + 1);
				}
				else {
					return (rightDepth + 1);
				}
			}
		}
	}
	
	private BinaryTreeNode<T> root;
	
	public CSE214BinaryTree(T ... elements) {
		this.root = insertInOrder(elements, this.root, 0);
	}
	
	public BinaryTreeNode insertInOrder(T[] arr, 
			BinaryTreeNode node, int i) {
		if (i < arr.length) {
			BinaryTreeNode temp = new BinaryTreeNode(arr[i]);
			node = temp;
			
			node.left = insertInOrder(arr, node.left, 
					2 * i + 1);
			
			node.right = insertInOrder(arr, node.right,
					2 * i + 2);
		}
		return node;
	}
	
	public String preorder() {
		String pre = this.root.preorderHelper("");
		String result = "";
		for (int i = 0; i < pre.length(); i++) {
			result += pre.charAt(i) + ", ";
		}
		return result.substring(0, result.length() - 2);
	}

	public String postorder() {
		String post = this.root.postorderHelper("");
		String result = "";
		for (int i = 0; i < post.length(); i++) {
			result += post.charAt(i) + ", ";
		}
		return result.substring(0, result.length() - 2);
	}

	public String inorder() {
		String in = this.root.inorderHelper("");
		String result = "";
		for (int i = 0; i < in.length(); i++) {
			result += in.charAt(i) + ", ";
		}
		return result.substring(0, result.length() - 2);
	}

	public int numNodes() {
		return this.root.numNodesHelper(this.root);
	}

	public int depth() {
		return this.root.depthHelper(this.root);
	}

	

}
