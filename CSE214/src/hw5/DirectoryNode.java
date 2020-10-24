package hw5;

/**
 * @author Eshan Shakrani
 * @ID: 112802596
 * @email: eshan.shakrani@stonybrook.edu
 * @Assignment #5
 * @Class: CSE 214
 * @Recitation: R03
 * @Instructor: Dylan Andres
 */

@SuppressWarnings("all")
public class DirectoryNode {
	private String name;
	private DirectoryNode left;
	private DirectoryNode middle;
	private DirectoryNode right;
	private boolean isFile;
	private String prefix;
	private int level;
	
	private DirectoryNode parent;
	
	/**
	 * getName method
	 * 
	 * @return String - name of current DirectoryNode
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * setName method
	 * 
	 * @param name - String
	 * 
	 * @postcondition name of current DirectoryNode has been set to param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * getLeft method
	 * 
	 * @return DirectoryNode - left child of current DirectoryNode
	 */
	public DirectoryNode getLeft() {
		return this.left;
	}
	
	/**
	 * setLeft method
	 * 
	 * @param left - DirectoryNode
	 * 
	 * @postcondition left child of current DirectoryNode has been set to 
	 *  param left
	 */
	public void setLeft(DirectoryNode left) {
		this.left = left;
	}
	
	/**
	 * getMiddle method
	 * 
	 * @return DirectoryNode - middle child of current DirectoryNode
	 */
	public DirectoryNode getMiddle() {
		return this.middle;
	}
	
	/**
	 * setMiddle method
	 * 
	 * @param middle - DirectoryNode
	 * 
	 * @postcondition middle child of current DirectoryNode has been set to 
	 *  param middle
	 */
	public void setMiddle(DirectoryNode middle) {
		this.middle = middle;
	}
	
	/**
	 * getRight method
	 * 
	 * @return DirectoryNode - right child of current DirectoryNode
	 */
	public DirectoryNode getRight() {
		return this.right;
	}
	
	/**
	 * setRight method
	 * 
	 * @param right - DirectoryNode
	 * 
	 * @postcondition right child of current DirectoryNode has been set to 
	 *  param right
	 */
	public void setRight(DirectoryNode right) {
		this.right = right;
	}
	
	/**
	 * getIsFile method
	 * 
	 * @return boolean - 
	 * 	true if DirectoryNode is a file
	 * 	false otherwise
	 */
	public boolean getIsFile() {
		return this.isFile;
	}
	
	/**
	 * setIsFile method
	 * 
	 * @param isFile - boolean
	 * 
	 * @postcondition file status of current DirectoryNode set to param isFile
	 */
	public void setIsFile(boolean isFile) {
		this.isFile = isFile;
		if (isFile) {
			this.prefix = " - ";
		}
		else {
			this.prefix = "|- ";
		}
	}
	
	/**
	 * getParent method
	 * 
	 * @return DirectoryNode - parent of current DirectoryNode
	 */
	public DirectoryNode getParent() {
		return this.parent;
	}
	
	/**
	 * setParent method
	 * 
	 * @param parent - DirectoryNode 
	 * 
	 * @postcondition parent of current DirectoryNode has been set to 
	 *  param parent
	 */
	public void setParent(DirectoryNode parent) {
		this.parent = parent;
	}
	
	/**
	 * getPrefix method
	 * 
	 * @return String - prefix of the current DirectoryNode
	 */
	public String getPrefix() {
		return this.prefix;
	}
	
	/**
	 * setPrefix method
	 * 
	 * @param prefix - String
	 * 
	 * @postcondition prefix of the current DirectoryNode has been set 
	 *  to param prefix
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	/**
	 * getLevel method
	 * 
	 * @return int - level of the current DirectoryNode
	 */
	public int getLevel() {
		return this.level;
	}
	
	/**
	 * setLevel method
	 * 
	 * @param level - int
	 * 
	 * @postcondition the level of the current DirectoryNode has been set
	 *  to param level
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	
	/**
	 * no-arg constructor
	 * 
	 * @postcondition new DirectoryNode object has been created
	 * 
	 * @postcondition name is set to null
	 * 
	 * @postcondition all children nodes set to null
	 * 
	 * @postcondition parent node set to null
	 * 
	 * @postcondition level set to 0
	 * 
	 * @postcondition prefix set to Directory prefix ("|- ")
	 */
	public DirectoryNode() {
		this.name = null;
		this.left = null;
		this.middle = null;
		this.right = null;
		this.parent = null;
		this.level = 0;
		this.prefix = "|- ";
	}
	
	/**
	 * arg constructor
	 * 
	 * @postcondition new DirectoryNode object has been created
	 * 
	 * @postcondition name is set to param name
	 * 
	 * @postcondition all children nodes set to null
	 * 
	 * @postcondition parent node set to null
	 * 
	 * @postcondition level set to 0
	 * 
	 * @postcondition prefix set to Directory prefix ("|- ")
	 */
	public DirectoryNode(String name) {
		this.name = name;
		this.left = null;
		this.middle = null;
		this.right = null;
		this.parent = null;
		this.level = 0;
		this.prefix = "|- ";
	}
	
	/**
	 * addChild method
	 * 
	 * adds newChild to any of the open child positions of this node
	 * 
	 * @param newChild - DirectoryNode object
	 * 
	 * @precondition this node is not a file
	 * 
	 * @precondition there is at least one empty position in the children
	 *  of this node
	 *  
	 * @postcondition newChild has been added as a child of this node
	 *  (If no more room for a new node, throw FullDirectoryException)
	 * 
	 * @throws FullDirectoryException if all child references of this 
	 *  directory are occupied
	 * 
	 * @throws NotADirectoryException if the current node is a file - files 
	 *  cannot contain DirectoryNode references
	 */
	public void addChild(DirectoryNode newChild) throws FullDirectoryException, 
		NotADirectoryException {
		if (this.getIsFile()) {
			throw new NotADirectoryException();
		}
		else {
			if (this.getLeft() == null) {
				this.setLeft(newChild);
				newChild.setParent(this);
				newChild.setLevel(newChild.getParent().getLevel() + 1);
			}
			else if (this.getMiddle() == null) {
				this.setMiddle(newChild);
				newChild.setParent(this);
				newChild.setLevel(newChild.getParent().getLevel() + 1);
			}
			else if (this.getRight() == null) {
				this.setRight(newChild);
				newChild.setParent(this);
				newChild.setLevel(newChild.getParent().getLevel() + 1);
			}
			else {
				throw new FullDirectoryException();
			}
		}
	}
	
	/**
	 * printPreorder method
	 * 
	 * uses printHelper method to print in format with a specified base
	 * 
	 * used to assist printDirectoryTree() method in DirectoryTree class
	 */
	public void printPreorder() {
		int base = this.getLevel();
		this.printHelper(base);
	}	
	
	/**
	 * printHelper method
	 * 
	 * traverses a tree in pre-order starting from current DirectoryNode
	 *  and prints in format
	 * 
	 * used to assist printPreorder method
	 * 
	 * @param base - int
	 */
	public void printHelper(int base) {
		
		for (int i = base; i < this.getLevel(); i++) {
			System.out.print("    ");
		}
		
		System.out.println(this.getPrefix() + this.getName());
		
		if (this.getLeft() != null) {
			this.getLeft().printHelper(base);
		}
		if (this.getMiddle() != null) {
			this.getMiddle().printHelper(base);
		}
		if (this.getRight() != null) {
			this.getRight().printHelper(base);
		}
	}
	
	/**
	 * isFull method
	 * 
	 * @return - boolean 
	 *  - true if DirectoryNode has left, middle, and right children present
	 *  - false otherwise
	 */
	public boolean isFull() {
		if (this.getLeft() == null) {
			return false;
		}
		else if (this.getMiddle() == null) {
			return false;
		}
		else if (this.getRight() == null) {
			return false;
		}
		else {
			return true;
		}
	}
	
	/**
	 * correctLevels method
	 * 
	 * called after moving directories 
	 * 
	 * @postcondition sets all DirectoryNode levels to their correct values
	 */
	public void correctLevels() {
		if (this.getParent() == null) {
			this.setLevel(0);
		}
		else {
			this.setLevel(this.getParent().getLevel() + 1);
		}
		
		if (this.getLeft() != null) {
			this.getLeft().correctLevels();
		}
		if (this.getMiddle() != null) {
			this.getMiddle().correctLevels();
		}
		if (this.getRight() != null) {
			this.getRight().correctLevels();
		}
	}
	
	/**
	 * deleteParentConnection method
	 * 
	 * removes the connection between the current DirectoryNode and its parent
	 * 
	 * @postcondition the parent node has lost its connection to the current 
	 *  DirectoryNode 
	 *  
	 * @postcondition right most child set to null after other children are 
	 *  shifted all the way to the left, if applicable
	 */
	public void deleteParentConnection() {
		DirectoryNode parent = this.getParent();
		if (parent.getLeft().equals(this)) {
			parent.setLeft(parent.getMiddle());
			parent.setMiddle(parent.getRight());
			parent.setRight(null);
			return;
		}
		if (parent.getMiddle().equals(this)) {
			parent.setMiddle(parent.getRight());
			parent.setRight(null);
			return;
		}
		if (parent.getRight().equals(this)) {
			parent.setRight(null);
			return;
		}
	}

	static DirectoryNode nodeArray[] = new DirectoryNode[100];
	static int index = 0;
	
	/**
	 * resetNodeArray method
	 * 
	 * @param a - DirectoryNode array
	 * 
	 * @postcondition param a has had all elements set to null
	 * 
	 * @postcondition index has been set to 0
	 */
	public void resetNodeArray(DirectoryNode[] a) {
		for (int i = 0; i < a.length; i++) {
			a[i] = null;
		}
		index = 0;
	}
	
	/**
	 * getNodeArray method
	 * 
	 * @return DirectoryNode[] - nodeArray the DirectoryNodes
	 */
	public DirectoryNode[] getNodeArray() {
		return this.nodeArray;
	}
	
	/**
	 * findAllNodes method
	 * 
	 * finds all DirectoryNodes in the tree with param name
	 * 
	 * @param name - String
	 * 
	 * @postcondition nodeArray has been loaded with all applicable nodes
	 */
	public void findAllNodes(String name) {
		if (this.getName().equals(name)) {
			nodeArray[index++] = this;
		}
		if (this.getLeft() != null) {
			this.getLeft().findAllNodes(name);
		}
		if (this.getMiddle() != null) {
			this.getMiddle().findAllNodes(name);
		}
		if (this.getRight() != null) {
			this.getRight().findAllNodes(name);
		}
	}
	
	/**
	 * toString method
	 * 
	 * used whenever the node is being printed without calling getName method
	 * 
	 * @return String - name of current DirectoryNode
	 */
	public String toString() {
		return this.getName();
	}
}
