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
	
	public DirectoryNode getMiddle() {
		return this.middle;
	}
	
	public DirectoryNode getRight() {
		return this.right;
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
}
