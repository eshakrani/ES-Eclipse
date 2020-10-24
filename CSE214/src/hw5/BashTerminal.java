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

import java.util.Scanner;
@SuppressWarnings("all")
public class BashTerminal {
	
	/**
	 * containsSlash method
	 * 
	 * evaluates whether a parameter String contains forward slash character
	 * 
	 * @param name - String
	 * 
	 * @return boolean - 
	 *  - true if param name contains a '/'
	 *  - false otherwise
	 */
	public static boolean containsSlashAndAlpha(String name) {
		boolean alpha = false;
		boolean slash = false;
		for (int i = 0; i < name.length(); i++) {
			if (name.charAt(i) == '/') {
				slash = true;
				break;
			}
		}
		for (int i = 0; i < name.length(); i++) {
			if (name.charAt(i) >= 'a' && name.charAt(i) <= 'z') {
				alpha = true;
				break;
			}
		}
		if (alpha && slash) {
			return true;
		}
		return false;
	}
	
	/**
	 * validPath method
	 * 
	 * @param name - String
	 * 
	 * @return boolean - 
	 * 	- true if param name is a valid path 
	 *  - false otherwise
	 */
	public static boolean validPath(String name) {
		if (containsSlashAndAlpha(name) || name.equals("root")) {
			return true;
		}
		return false;
	}
	
	/**
	 * main method
	 * 
	 * runs a program which takes user input and builds a DirectoryTree
	 *  using commands
	 * 
	 * @param args - String[]
	 */
	public static void main (String[] args) {
		
		Scanner stdin = new Scanner(System.in);
		
		// create a new DirectoryTree 
		DirectoryTree tree = new DirectoryTree();
		
		// misc. DirectoryNodes used throughout program
		DirectoryNode node;
		DirectoryNode node2;
		DirectoryNode tempCurs;
		DirectoryNode temp;
		
		// nodeArray used to store DirectoryNodes in 'find' block
		DirectoryNode nodeArray[];
		
		System.out.println("Starting bash terminal.");
		
		String header = "[eshakrani@NoobMaster69]: $ ";
		String input = "";
		
		// used to split up input strings
		String[] split;
		
		// used to split up paths
		String[] slash;
		
		
		while (true) {
			
			System.out.print(header);
			input = stdin.nextLine();
			String sub = "";
			split = input.split(" ");
			
			switch (split[0]) {
			case "exit":
				System.out.println("Program terminating normally");
				System.exit(9001);
			
			case "pwd":
				System.out.println(tree.presentWorkingDirectory());
				break;
			
			case "ls":
				
				// simple 'ls' command
				if (split.length == 1) {
					System.out.println(tree.listDirectory());
				}
				
				// 'ls -R' command
				else if (split.length == 2 && split[1].equals("-R")){
					tree.printDirectoryTree();
				}
				else {
					System.out.println("Invalid command.");
				}
				break;
			
			case "cd":
				
				// if only 'cd'
				if (split.length == 1) {
					System.out.println("Invalid command.");
				}
				
				else if (split.length == 2) {
					
					// 'cd /' command
					if (split[1].equals("/")) {
						tree.resetCursor();
					}
					
					// 'cd ..' command
					else if (split[1].equals("..")) {
						if (tree.getCursor() != tree.getRoot()) {
							tree.setCursor(tree.getCursor().getParent());
						}
						else {
							System.out.println("ERROR: Already at root "
									+ "directory.");
						}
					}
					else if (split[1].length() > 2 && 
							validPath(split[1])) {
						try {
							tree.changeDirectoryWithAbsPath(split[1]);
						}
						catch (NotADirectoryException e) {
							System.out.println("ERROR: Cannot change "
									+ "directory into a file.");
						}
					}
					else {
						try {
							tree.changeDirectory(split[1]);
						}
						catch (NotADirectoryException e) {
							System.out.println("ERROR: Cannot change "
									+ "directory into a file.");
						}
					}
				}
				
				else {
					System.out.println("Invalid command.");
				}
				break;
				
			case "mkdir":
				
				// if only 'mkdir ' without an argument
				if (input.length() < 7) {
					System.out.println("Invalid command.");
				}
				else {
					
					// sub is the part of the input string after 'mkdir '
					sub = input.substring(6, input.length());
					try {
						tree.makeDirectory(sub);
					}
					catch (IllegalArgumentException e) {
						System.out.println("ERROR: Illegal Name.");
					}
					catch (NotADirectoryException e) {
						System.out.println("ERROR: This structure is not a "
								+ "directory");
					}
					catch (FullDirectoryException e) {
						System.out.println("ERROR: Present directory is full.");
					}
				}
				break;
				
			case "touch":
				
				// if only 'touch ' without an argument
				if (input.length() < 7) {
					System.out.println("Invalid command.");
				}
				else {
					
					// sub is the part of the input string after 'touch '
					sub = input.substring(6, input.length());
					try {
						tree.makeFile(sub);
					}
					catch (IllegalArgumentException e) {
						System.out.println("ERROR: Illegal Name.");
					}
					catch (NotADirectoryException e) {
						System.out.println("ERROR: This structure is not a "
								+ "directory");
					}
					catch (FullDirectoryException e) {
						System.out.println("ERROR: Present directory is full.");
					}
				}
				break;
				
			case "find":
				
				// if only 'find ' without an argument
				if (input.length() < 6) {
					System.out.println("Invalid command.");
				}
				else {
					
					// sub is the part of the input string after 'find '
					sub = input.substring(5, input.length());
					if (DirectoryTree.isLegalArgument(sub)) {
						
						// confirms that the node does exist somewhere
						node = tree.findNode(sub);
						if (node == null) {
							System.out.println("ERROR: No such file exists.");
						}
						else {
							temp = tree.getCursor();
							
							tree.getRoot().findAllNodes(sub);
							nodeArray = tree.getCursor().getNodeArray();
							
							int i = 0;
							while (nodeArray[i] != null) {
								tree.setCursor(nodeArray[i]);
								System.out.println
									(tree.presentWorkingDirectory());
								i++;
							}
							
							// reset the node array for future use
							tree.getCursor().resetNodeArray(nodeArray);
							
							// put cursor back to position before method called
							tree.setCursor(temp);
						}
					}
				}
				break;
				
			case "mv":
				
				// if only 'mv ' without an argument
				if (input.length() < 4) {
					System.out.println("Invalid command.");
				}
				
				// if wrong number of arguments
				else if (split.length != 3) {
					System.out.println("Invalid command.");
				}
				else if (split[1].length() < 2 || split[2].length() < 2) {
					System.out.println("ERROR: Invalid arguments.");
				}
				else if (!validPath(split[1]) || !validPath(split[2])) {
					System.out.println("ERROR: Invalid arguments.");
				}
				else {
					
					tempCurs = tree.getCursor();
					
					// split up first argument path by "/"
					slash = split[1].split("/");
					
					node = tree.findNode(slash[slash.length - 1]);
					temp = tree.findNode(slash[slash.length - 1]);
					
					if (node == null) {
						System.out.println("ERROR: Could not find " + split[1]);
						break;
					}
					
					// split up second argument path by "/"
					slash = split[2].split("/");
				
					node2 = tree.findNode(slash[slash.length - 1]);
					if (node2 == null) {
						System.out.println("ERROR: Could not find " + split[2]);
						break;
					}
					
					try {
						if (node2.isFull()) {
							throw new FullDirectoryException();
						}
						else {
							node.deleteParentConnection();
							node2.addChild(node);
							tree.getRoot().correctLevels();
						}
					}
					catch (FullDirectoryException e) {
						System.out.println("ERROR: Present directory is full.");
					}
					catch (NotADirectoryException e) {}
					tree.setCursor(tempCurs);
				}
				break;
				
			default:
				System.out.println("Invalid command.");
				break;
			}
		}
	}
}
