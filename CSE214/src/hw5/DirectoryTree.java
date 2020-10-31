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
public class DirectoryTree {
	private DirectoryNode root;
	private DirectoryNode cursor;
	
	/**
	 * getRoot method
	 * 
	 * @return DirectoryNode - root of current DirectoryTree
	 */
	public DirectoryNode getRoot() {
		return this.root;
	}
	
	/**
	 * setRoot method
	 * 
	 * @param root - DirectoryNode
	 * 
	 * @postcondition root of current DirectoryTree has been set to param root
	 */
	public void setRoot(DirectoryNode root) {
		this.root = root;
	}
	
	/**
	 * getCursor method
	 * 
	 * @return DirectoryNode - cursor of current DirectoryTree
	 */
	public DirectoryNode getCursor() {
		return this.cursor;
	}
	
	/**
	 * setCursor method
	 * 
	 * @param cursor - DirectoryNode
	 * 
	 * @postcondition cursor of current DirectoryTree has been set to 
	 *  param cursor
	 */
	public void setCursor(DirectoryNode cursor) {
		this.cursor = cursor;
	}
	
	/**
	 * no-arg constructor
	 * 
	 * initializes a DirectoryTree object with a single DirectoryNode
	 *  named "root"
	 *  
	 * @postcondition the tree contains a single DirectoryNode named "root", 
	 *  referenced by both cursor and root references
	 */
	public DirectoryTree() {
		this.root = new DirectoryNode("root");
		this.cursor = this.root;
	}
	
	/**
	 * resetCursor method
	 * 
	 * moves the cursor to the root node of the tree
	 * 
	 * @postcondition the cursor now references the root node of the tree
	 */
	public void resetCursor() {
		this.cursor = this.root;
	}
	
	/**
	 * changeDirectory method
	 * 
	 * moves the cursor to the directory with the name indicated by param name
	 * 
	 * @param name - String
	 * 
	 * @precondition param name references a valid directory (not a file)
	 * 
	 * @postcondition the cursor now references the directory with the name
	 *  indicated by param name
	 * - if a child could not be found with param name, then the user is 
	 *  	prompted to enter a different directory name
	 *  
	 * @throws NotADirectoryException when param name references a node with 
	 *  a file instead of a directory
	 */
	public void changeDirectory(String name) throws NotADirectoryException {
		
		
		for (int i = 0; i < 10; i++) {
			if (this.getCursor().getChildren()[i] != null) {
				if (this.getCursor().getChildren()[i].getName().equals(name)) {
					if (this.getCursor().getChildren()[i].getIsFile()) {
						throw new NotADirectoryException();
					}
					else {
						this.setCursor(this.getCursor().getChildren()[i]);
						return;
					}
				}
			}
		}
		
		/*
		// if left child is not null
		if (this.getCursor().getLeft() != null) {
		
			// if left child has the correct name
			if (this.getCursor().getLeft().getName().equals(name)) {
				
				// if left child is a file
				if (this.getCursor().getLeft().getIsFile()) {
					throw new NotADirectoryException("");
				}
				else {
					this.setCursor(this.getCursor().getLeft());
					return;
				}
			}
		}
		
		// if middle child is not null
		if (this.getCursor().getMiddle() != null) {
			
			// if middle child has the correct name
			if (this.getCursor().getMiddle().getName().equals(name)) {
				
				// if middle child is a file
				if (this.getCursor().getMiddle().getIsFile()) {
					throw new NotADirectoryException("");
				}
				else {
					this.setCursor(this.getCursor().getMiddle());
					return;
				}
			}
		}
		
		// if right child is not null
		if (this.getCursor().getRight() != null) {
			
			// if right child has the correct name
			if (this.getCursor().getRight().getName().equals(name)) {
				
				// if right child is a file
				if (this.getCursor().getRight().getIsFile()) {
					throw new NotADirectoryException("");
				}
				else {
					this.setCursor(this.getCursor().getRight());
					return;
				}
			}
		}
		*/
		
		System.out.printf("ERROR: No such directory named '%s'.\n", name);
	}
	
	DirectoryNode temp;
	public void changeDirectoryWithAbsPath(String path) 
			throws NotADirectoryException {
		String dir[] = path.split("/");
		boolean found = false;
		temp = this.getCursor();
		
		for (int i = 0; i < 10; i++) {
			
			if ((this.getCursor().getChildren()[i] != null) &&
				(!this.getCursor().getChildren()[i].getName().equals(dir[0]))) {
				if (i == 9) {
					if (!this.getRoot().getName().equals(dir[0])) {
						System.out.printf("ERROR: No such directory "
								+ "named '%s'.\n", path);
						return;
					}
				}
			}
			else {
				break;
			}
		}
		
		if (!this.getRoot().getName().equals(dir[0])) {
			
			for (int i = 0; i < 10; i++) {
				
				if ((this.getCursor().getChildren()[i] != null) &&
					(!this.getCursor().getChildren()[i].getName().
							equals(dir[0]))) {
					if (i == 9) {
						System.out.printf("ERROR2: No such directory "
								+ "named '%s'.\n", path);
						return;
					}
				}
				else {
					break;
				}
			}
		}		
		
		for (int i = 0; i < 10; i++) {
			if (this.getCursor().getChildren()[i] != null &&
				this.getCursor().getChildren()[i].getName().equals(dir[0])) {
				this.setCursor(this.getCursor().getChildren()[i]);
				break;
			}
		}
		
		if (this.getRoot().getName().equals(dir[0])) {
			this.resetCursor();
		}
		
		// else the cursor just remains where it currently is
		
		for (int j = 1; j < dir.length; j++) {
			
			for (int i = 0; i < 10; i++) {
				
				if (this.getCursor().getChildren()[i] != null) {
					if (this.getCursor().getChildren()[i].getName().
							equals(dir[j])) {
						found = true;
						if (this.getCursor().getChildren()[i].getIsFile()) {
							throw new NotADirectoryException();
						}
						else {
							this.setCursor(this.getCursor().getChildren()[i]);
							break;
						}
					}
					else {
						found = false;
					}
				}
				else {
					found = false;
					break;
				}
			}
			
			if (!found) {
				System.out.printf("ERROR: No such directory named "
						+ "'%s'.\n", path);
				this.setCursor(temp);
				return;
			}
		}
	}
	
	/**
	 * presentWorkingDirectory method
	 * 
	 * @postcondition the cursor remains at the same DirectoryNode
	 * 
	 * @return String containing the path of directory names from the root
	 *  node of the tree to the cursor, with each name separated by "/"
	 */
	public String presentWorkingDirectory() {
		String s = "";
		DirectoryNode temp = this.getCursor();
		while (temp != this.getRoot()) {
			s = "/" + temp.getName() + s;
			temp = temp.getParent();
		}
		s = "root" + s;
		return s;
	}
	
	/**
	 * listDirectory method
	 *
	 * @postcondition the cursor remains at the same DirectoryNode
	 * 
	 * @return String containing a space-separated list of names of all 
	 *  the child directories or files of the cursor
	 */
	public String listDirectory() {
		String s = "";
		
		for (int i = 0; i < 10; i++) {
			if (this.getCursor().getChildren()[i] != null) {
				s += this.getCursor().getChildren()[i].getName() + " ";
			}
		}

		if (s.equals("")) {
			return "No files or directories found in this directory.";
		}
		return s;
	}
	
	/** (ls -R command in I/O)
	 * printDirectoryTree method
	 * 
	 * prints a formatted nested list of names of all the nodes in 
	 *  the directory tree, starting from the cursor (**PRE-ORDER)
	 *  
	 * @postcondition the cursor remains at the same DirectoryNode
	 */
	public void printDirectoryTree() {
		DirectoryNode temp = this.getCursor();
		temp.printPreorder();
	}
	
	/**
	 * makeDirectory method
	 * 
	 * creates a directory with the indicated name and adds it to the children
	 * 	of the cursor node
	 * 
	 * @param name - String
	 * 
	 * @precondition param name is a legal argument (does not contain 
	 *  spaces " " or forward slashes "/")
	 *  
	 * @postcondition a new DirectoryNode has been added to the children 
	 *  of the cursor, or an exception has been thrown
	 *  
	 * @throws IllegalArgumentException if param name is an invalid argument
	 * 
	 * @throws FullDirectoryException if all child references of this 
	 *  directory are occupied
	 *  
	 * @throws NotADirectoryException if the called addChild method 
	 *  attempts to add a DirectoryNode that is not a directory (is a file)
	 */
	public void makeDirectory(String name) throws IllegalArgumentException, 
	 	FullDirectoryException, NotADirectoryException {
		boolean legal = isLegalArgument(name);
		if (!legal) {
			throw new IllegalArgumentException("ILLEGAL NAME");
		}
		
		DirectoryNode newNode = new DirectoryNode(name);
		try {
			this.getCursor().addChild(newNode);
		}
		catch (FullDirectoryException e) {
			throw new FullDirectoryException();
		}
		catch (NotADirectoryException e) {
			throw new NotADirectoryException();
		}
	}
	
	/**
	 * makeFile method
	 * 
	 * creates a file with the indicated name and adds it to the children
	 *  of the cursor node
	 * 
	 * @param name - String
	 * 
	 * @precondition param name is a legal argument (does not contain
	 *  spaces " " or forward slashes "/")
	 * 
	 * @postcondition a new DirectoryNode has been added to the children of
	 *  the cursor, or an exception has been thrown
	 *  
	 * @throws IllegalArgumentException if param name is an invalid argument
	 * 
	 * @throws FullDirectoryException if all child references of
	 *  this directory are occupied
	 *  
	 * @throws NotADirectoryException if the called addChild method 
	 *  attempts to add a DirectoryNode that is not a directory (is a file)
	 */
	public void makeFile(String name) throws IllegalArgumentException, 
		FullDirectoryException, NotADirectoryException {
		boolean legal = isLegalArgument(name);
		if (!legal) {
			throw new IllegalArgumentException("ILLEGAL NAME");
		}
		
		DirectoryNode newNode = new DirectoryNode(name);
		newNode.setIsFile(true);
		
		try {
			this.getCursor().addChild(newNode);
		}
		catch (FullDirectoryException e) {
			throw new FullDirectoryException();
		}
		catch (NotADirectoryException e) {
			throw new NotADirectoryException();
		}
	}
	
	static DirectoryNode searchNode = null;
	static DirectoryNode iter;
	
	/**
	 * resetParam method
	 * 
	 * @postcondition searchNode set to null
	 * 
	 * @postcondition iter set to current DirectoryTree object's root node
	 */
	public void resetParam() {
		searchNode = null;
		iter = this.root;
	}
	
	/**
	 * traverseSearch method
	 * 
	 * helper for findNode method
	 * 
	 * searches tree in pre-order
	 * 
	 * @param name - String
	 */
	public void traverseSearch(String name) {
		if (iter.getName().equals(name)) {
			searchNode = iter;
			return;
		}
		
		for (int i = 0; i < 10; i++) {
			if (iter.getChildren()[i] != null) {
				iter = iter.getChildren()[i];
				this.traverseSearch(name);
				iter = iter.getParent();
			}
		}
		
	}
	
	/**
	 * findNode method
	 * 
	 * @param name - String
	 * 
	 * @postcondition searchNode and iter DirectoryNode objects have been reset
	 * 
	 * @return the first DirectoryNode found in the tree that has the
	 *  given name
	 */
	public DirectoryNode findNode(String name) {
		this.resetParam();
		this.traverseSearch(name);
		return searchNode;
	}
	
	/**
	 * isLegalArgument method
	 * 
	 * returns a boolean value that represents whether a name is a legal
	 *  argument (does not contain spaces or forward slashes) or not
	 * 
	 * @param name - String
	 * 
	 * @return boolean - 
	 *  - true if param name is a legal argument
	 *  - false otherwise
	 */
	public static boolean isLegalArgument(String name) {
		boolean legal = true;
		for (int i = 0; i < name.length(); i++) {
			if (name.charAt(i) == ' ') {
				legal = false;
				break;
			}
			if (name.charAt(i) == '/') {
				legal = false;
				break;
			}
		}
		return legal;
	}

}
