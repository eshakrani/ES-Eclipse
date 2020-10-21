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

public class DirectoryNode {
	private String name;
	private DirectoryNode left;
	private DirectoryNode middle;
	private DirectoryNode right;
	private boolean isFile;
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public DirectoryNode getLeft() {
		return this.left;
	}
	
	public void setLeft(DirectoryNode left) {
		this.left = left;
	}
	
	public DirectoryNode getMiddle() {
		return this.middle;
	}
	
	public void setMiddle(DirectoryNode middle) {
		this.middle = middle;
	}
	
	public DirectoryNode getRight() {
		return this.right;
	}
	
	public void setRight(DirectoryNode right) {
		this.right = right;
	}
	
	public boolean getIsFile() {
		return this.isFile;
	}
	
	public void setIsFile(boolean isFile) {
		this.isFile = isFile;
	}
	
	public DirectoryNode() {
		this.name = null;
		this.left = null;
		this.middle = null;
		this.right = null;
	}
	
	public DirectoryNode(String name) {
		this.name = name;
		this.left = null;
		this.middle = null;
		this.right = null;
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
			throw new NotADirectoryException("");
		}
		else {
			if (this.getLeft() != null) {
				this.setLeft(newChild);
			}
			else if (this.getMiddle() != null) {
				this.setMiddle(newChild);
			}
			else if (this.getRight() != null) {
				this.setRight(newChild);
			}
			else {
				throw new FullDirectoryException("");
			}
		}
	}
}
