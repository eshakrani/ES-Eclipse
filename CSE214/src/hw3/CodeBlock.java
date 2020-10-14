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
public class CodeBlock {
	static final String[] BLOCK_TYPES = {
			"def", "for", "while", "if", "elif", "else"
	};
	static final int DEF = 0;
	static final int FOR = 1;
	static final int WHILE = 2;
	static final int IF = 3;
	static final int ELIF = 4;
	static final int ELSE = 5;
	
	// keep track of Big-Oh complexity of this block
	private Complexity blockComplexity;
	
	// keep track of the Big-Oh complexity of the 
	// highest-order block nested within this block
	private Complexity highestSubComplexity;
	
	// keep track of the nested structure 
	// of the blocks
	private String name;
	
	private String loopVariable;
	
	/**
	 * getBlockComplexity method
	 * 
	 * @return Complexity object - 
	 *  the blockComplexity of the
	 *  CodeBlock
	 */
	public Complexity getBlockComplexity() {
		return this.blockComplexity;
	}
	
	/**
	 * setBlockComplexity method
	 * 
	 * @param blockComplexity - Object
	 *  of type Complexity
	 *  
	 * @postcondition sets the 
	 *  blockComplexity of the 
	 *  CodeBlock to param 
	 *  blockComplexity
	 */
	public void setBlockComplexity(
			Complexity blockComplexity) {
		this.blockComplexity = 
				blockComplexity;
	}
	
	/**
	 * getHighestSubComplexity method
	 * 
	 * @return Complexity object - 
	 *  the highestSubComplexity of 
	 *  the CodeBlock
	 */
	public Complexity getHighestSubComplexity() {
		return this.highestSubComplexity;
	}
	
	/**
	 * setHighestSubComplexity method
	 * 
	 * @param highestSubComplexity - Object
	 *  of type Complexity
	 *  
	 * @postcondition sets the 
	 *  highestSubComplexity of the 
	 *  CodeBlock to param 
	 *  highestSubComplexity
	 */
	public void setHighestSubComplexity(
			Complexity highestSubComplexity) {
		this.highestSubComplexity = 
				highestSubComplexity;
	}
	
	/**
	 * getName method
	 * 
	 * @return String - 
	 *  the name of the CodeBlock
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * setName method
	 * 
	 * @param name - type String
	 *  
	 * @postcondition sets the 
	 *  name of the CodeBlock 
	 *  to param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * getLoopVariable method
	 * 
	 * @return String - 
	 *  the loopVariable of the CodeBlock
	 */
	public String getLoopVariable() {
		return this.loopVariable;
	}
	
	/**
	 * setLoopVariable method
	 * 
	 * @param loopVariable - type String
	 *  
	 * @postcondition sets the loopVariable 
	 * of the CodeBlock to param loopVariable
	 */
	public void setLoopVariable(String loopVariable) {
		this.loopVariable = loopVariable;
	}
	
	/**
	 * totalComplexity method
	 * 
	 * @return combination of block complexity
	 *  and highestSub complexity for a CodeBlock
	 */
	public Complexity multComplexity() {
		Complexity block = this.getBlockComplexity();
		Complexity sub = this.getHighestSubComplexity();
		if (block.toString().equals("O(1)")) {
			return sub;
		}
		else if (sub.toString().equals("O(1)")) {
			return block;
		}
		else {
			Complexity temp = new Complexity();
			temp.setNPower(block.getNPower() + sub.getNPower());
			temp.setLogPower((block.getLogPower() + sub.getLogPower()));
			return temp;
		}
	}
	
	/**
	 * no-arg constructor
	 * 
	 * @postcondition creates a new
	 *  CodeBlock object
	 * 
	 * @postcondition sets the 
	 *  blockComplexity of the object
	 *  to a default Complexity object
	 *  
	 * @postcondition sets the 
	 *  highestSubComplexity of the 
	 *  object to a default Complexity
	 *  object
	 *  
	 * @postcondition sets the name of
	 *  the object to an empty string
	 *  
	 * @postcondition sets the loopVariable
	 *  of the object to an empty string 
	 */
	public CodeBlock() {
		this.blockComplexity = new Complexity();
		this.highestSubComplexity = new Complexity();
		this.name = "";
		this.loopVariable = null;
	}
	
	/**
	 * arg constructor
	 * 
	 * @param blockComplexity - type Complexity
	 * 
	 * @param highestSubComplexity - type Complexity
	 * 
	 * @param name - type String
	 * 
	 * @param loopVariable - type String
	 * 
	 * @postcondition creates a new CodeBlock
	 *  object
	 *  
	 * @postcondition sets the blockComplexity
	 *  of the object to param blockComplexity
	 *  
	 * @postcondition sets the 
	 *  highestSubComplexity of the object to 
	 *  param highestSubComplexity
	 *  
	 * @postcondition sets the name of the 
	 *  object to param name
	 *  
	 * @postcondition sets the loopVariable of 
	 *  the object to param loopVariable
	 */
	public CodeBlock(Complexity blockComplexity,
			Complexity highestSubComplexity,
			String name, String loopVariable) {
		this.blockComplexity = blockComplexity;
		this.highestSubComplexity = 
				highestSubComplexity;
		this.name = name;
		this.loopVariable = loopVariable;
	}
	
	/**
	 * toString method
	 * 
	 * @return String representation of the
	 *  CodeBlock
	 */
	public String toString() {
		String s = "    ";
		s += "BLOCK ";
		s += String.format("%-12s", this.getName() + ":");
		s += "block complexity = ";
		s += String.format("%-20s", this.blockComplexity);
		s += "highest sub-complexity = ";
		s += this.highestSubComplexity;
		return s;
	}
}
