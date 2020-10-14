package hw3;

/**
 * @author Eshan Shakrani
 * @ID: 112802596
 * @email: eshan.shakrani@stonybrook.edu
 * @Assignment #3
 * @Class: CSE 214
 * @Recitation: R03
 * @Instructor: Dylan Andres
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
public class PythonTracer {
	
	static final int SPACE_COUNT = 4;
	
	/**
	 * traceFile method
	 * 
	 * opens the indicated file and traces through
	 *  the code of the Python function contained
	 *  within the file, returning the Big-Oh order
	 *  of complexity of the function
	 *  
	 * @param filename - type String
	 *  name of the file to be opened and traced
	 *  
	 * @precondition filename is not null
	 * 
	 * @precondition the file within filename
	 *  contains a single Python function
	 *  with valid syntax
	 *  
	 * @return object of type Complexity - represents
	 *  the total order of complexity of the Python
	 *  code contained within the file
	 */
	public static Complexity traceFile(String filename) {
		BlockStack b = new BlockStack();
		ArrayList<String> list = new ArrayList<String>();
		int spaces, indents, inc = 1, n;
		CodeBlock temp, oldTop;
		Complexity oldTopComplexity;
		String word = "", words[], loopVariable = "";
		String name = "1", newName = "";
		
		list.add(name);
		
		try {
			FileReader fileReader = new FileReader(filename);
			BufferedReader br = new BufferedReader(fileReader);
			String s;
			while ((s = br.readLine()) != null) {
				
				// if current line is empty then ignore
				if (s.trim().equals("")) continue;
				
				// if current line is a comment then ignore
				else if (s.trim().charAt(0) == '#') continue;
				
				else {
					spaces = 0;
					for (int i = 0; i < s.length(); i++) {
						if (s.charAt(i) == ' ') {
							spaces++;
						}
						else {
							break;
						}
					}
					
					indents = spaces / SPACE_COUNT;

					while (indents < b.size()) {
						if (indents == 0) {
							
							br.close();
							
							return b.peek().getBlockComplexity();
						
						}
						else {
							
							oldTop = b.pop();
							oldTopComplexity = 
									oldTop.multComplexity();
			
							if (oldTopComplexity.isHigherOrder(b.peek().
									getHighestSubComplexity()) ) {
								
								temp = b.pop();
								
								temp.setHighestSubComplexity
									(oldTopComplexity);
								b.push(temp);
								System.out.println("Leaving block " + 
									oldTop.getName() + ", updating block " 
										+ temp.getName());
								System.out.println(temp);
								System.out.println();


							}
							
							else {
								
								System.out.println("Leaving block " + 
									oldTop.getName() + ", nothing to "
											+ "update.");
								
								if (b.peek() != null) {
									System.out.println(b.peek());
									System.out.println();
								}
							}
						}
					} // while
					
					words = s.trim().split(" ");
					word = words[0];
					
					if (checkKeyword(word)) {
						
						if (word.equals("for")) {
							
							CodeBlock block = new CodeBlock();
							Complexity sub = new Complexity();
							Complexity comp = new Complexity();
							
							if (words[words.length - 1].
									equals("N:")) {
								comp.setNPower(1);
							}
							
							else if (words[words.length - 1].
										equals("log_N:")) {
								comp.setLogPower(1);
							}
							
							block.setBlockComplexity(comp);
							block.setHighestSubComplexity(sub);

							newName = b.peek().getName() + "." + inc;
							n = inc;
							
							
							while (checkList(list, newName)) {
								n++;
								newName = b.peek().getName() + "." + n;
							}
							
							list.add(newName);
							
							block.setName(newName);
							b.push(block);
							
							System.out.println("Entering block " + 
									block.getName() + " 'for':");
							System.out.println(block);
							System.out.println();
							
						}
						
						else if (word.equals("while")) {
							
							loopVariable = words[1];
							CodeBlock block = new CodeBlock();
							block.setLoopVariable(loopVariable);
							
							newName = b.peek().getName() + "." + inc;
							n = inc;
							
							while (checkList(list, newName)) {
								n++;
								newName = b.peek().getName() + "." + n;
							}
							
							list.add(newName);
							
							
							block.setName(newName);
							b.push(block);
							System.out.println("Entering block " + 
									block.getName() + " 'while':");
							System.out.println(block);
							System.out.println();
							
						}
						
						else {
							CodeBlock block = new CodeBlock();
							
							if (b.isEmpty()) {
								block.setName(name);
								b.push(block);
								System.out.println("Entering "
										+ "block " + 
										name + " '" + word + "':");
								System.out.println(block);
								System.out.println();
							}
							else {
								newName = b.peek().getName() 
										+ "." + inc;
								n = inc;
								
								while (checkList(list, newName)) {
									n++;
									newName = b.peek().getName() 
											+ "." + n;
								}
								
								list.add(newName);
								
								block.setName(newName);
								b.push(block);
								
								if (word.equals("else:")) {
									word = "else";
								}
								
								System.out.println("Entering "
										+ "block " + 
										block.getName() + 
										" '" + word + "':");
								System.out.println(block);
								System.out.println();
							}
						}
						
					}
					else {
						
						// if top of stack is a while loop
						if (b.peek().getLoopVariable() != null) {
							
							// if first word in line is the
							// loop variable
							if (word.equals
									(b.peek().getLoopVariable())) {
								
								temp = b.pop();
								
								// if loop variable is dividing
								if (words[1].equals("/=")) {
									temp.setBlockComplexity
										(new Complexity(0, 1));
									
									b.push(temp);
									System.out.println("Found update "
										+ "statement, updating block " 
											+ temp.getName() + ":");
									System.out.println(temp);
									System.out.println();
								}
								
								// if loop variable is decrementing
								else if (words[1].equals("-=")) {
									temp.setBlockComplexity
										(new Complexity(1, 0));
									
									b.push(temp);
									System.out.println("Found update "
										+ "statement, updating block " 
											+ temp.getName() + ":");
									System.out.println(temp);
									System.out.println();
								}
	
							}
						}
					}
				}
			}
			while (b.size() > 1) {
				oldTop = b.pop();
				oldTopComplexity = oldTop.multComplexity();
				if (oldTopComplexity.isHigherOrder(b.peek().
						getHighestSubComplexity()) ) {
					temp = b.pop();
					temp.setHighestSubComplexity(oldTopComplexity);
					b.push(temp);

				}
			}
		}
		catch (FileNotFoundException e) {
			System.out.println("Input file not found.\n");
			System.exit(2);
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		temp = b.pop();
		System.out.println("Leaving block " + temp.getName() + ".\n");
		return temp.multComplexity();
	}
	
	/**
	 * checkKeyword method
	 * 
	 * @param word - type String
	 * 
	 * @return boolean - 
	 * 	true if the word is a keyword
	 * 	false otherwise
	 */
	public static boolean checkKeyword(String word) {
		if (word.equals("for") || word.equals("def") 
				|| word.equals("while")
				|| word.equals("if")
				|| word.equals("elif")
				|| word.equals("else:")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * checkList method
	 * 
	 * @param a - ArrayList of Strings to be checked
	 * 
	 * @param name - String to search for in ArrayList
	 * 
	 * @return boolean - 
	 * 	true if name is in a
	 *  false otherwise
	 */
	public static boolean checkList(ArrayList<String> a, String name) {
		int size = a.size();
		for (int i = 0; i < size; i++) {
			if (name.equals(a.get(i))) {
				return true;
			}
		}
		return false;
	}
	
	
	public static void main (String[] args) 
			throws FileNotFoundException {
		Scanner stdin = new Scanner(System.in);
		System.out.print("Please enter a file name (or 'quit' to quit): ");
		String input = stdin.nextLine();
		if (input.equalsIgnoreCase("quit")) {
			System.out.println("Program terminating successfully...");
			System.exit(1);
		}
		else {
			
			Complexity ans = traceFile(input);
			System.out.print("Overall complexity of " + 
				input.substring(0, input.length() - 3) + ": ");
			System.out.println(ans);
		}
	}
}








