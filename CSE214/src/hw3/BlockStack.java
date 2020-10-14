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
import java.util.EmptyStackException;
import java.util.Stack;
public class BlockStack {	
	
	private BlockStackNode top;
	private int size;
	
	/**
	 * BlockStack constructor
	 * 
	 * @postcondition initializes BlockStack
	 *  to an empty stack
	 *  
	 * @postcondition initializes size to 0
	 */
	public BlockStack() {
		top = null;
		size = 0;
	}
	
	/**
	 * isEmpty method
	 * 
	 * @return boolean -
	 * 	true if BlockStack is empty
	 * 	false otherwise
	 */
	public boolean isEmpty() {
		return (top == null);
	}
	
	/**
	 * push method
	 * 
	 * @param c - CodeBlock Object
	 * 
	 * @postcondition CodeBlock c
	 *  has been pushed onto BlockStack
	 * 
	 * @postcondition size increased by 1
	 */
	public void push (CodeBlock c) {
		BlockStackNode newNode = new BlockStackNode(c);
		newNode.setNext(this.top);
		this.top = newNode;
		this.size++;
	}
	
	/**
	 * pop method
	 * 
	 * @return the CodeBlock object 
	 *  at the top of BlockStack
	 *  
	 * @postconditon this CodeBlock 
	 *  object has been removed from 
	 *  BlockStack
	 *  
	 * @postcondition size decreased by 1
	 */
	public CodeBlock pop() throws EmptyStackException {
		try {
			CodeBlock block;
			if (this.top == null) {
				throw new EmptyStackException();
			}
			block = this.top.getCodeBlock();
			this.top = this.top.getNext();
			this.size--;
			return block;
		}
		catch (EmptyStackException e) {
			System.out.println("ERROR: Stack is empty.\n");
		}
		return null;
	}
	
	/**
	 * peek method
	 * 
	 * @return the CodeBlock object
	 *  at the top of BlockStack without
	 *  removing it
	 */
	public CodeBlock peek() throws EmptyStackException {
		try {
			CodeBlock block;
			if (this.top == null) {
				throw new EmptyStackException();
			}
			block = this.top.getCodeBlock();
			return block;
		}
		catch (EmptyStackException e) {
			System.out.println("ERROR: Stack is empty.\n");
		}
		return null;
	}
	
	/**
	 * size method
	 * 
	 * @return int - the size of
	 *  BlockStack
	 */
	public int size() {
		return this.size;
	}

	/**
	 * print function
	 * 
	 * prints each item in the BlockStack
	 */
	public void print() {
		BlockStack temp = new BlockStack();
		
		while (this.size() != 0) {
			temp.push(this.pop());
		}
		while (temp.size() != 0) {
			System.out.println(temp.pop());
		}
	}
	
	/**
	 * BlockStackNode class
	 * 
	 * @author eshak
	 *
	 * represents a single node of the 
	 *  BlockStack class
	 */
	public class BlockStackNode {
		private CodeBlock block;
		private BlockStackNode next;
		
		/**
		 * BlockStackNode constructor
		 * 
		 * @param block - type CodeBlock
		 * 
		 * @postcondition a new BlockStackNode
		 *  object has been created
		 *  
		 * @postcondition this object's block
		 *  has been initialized to param block
		 */
		public BlockStackNode (CodeBlock block) {
			this.block = block;
		}
		
		/**
		 * getNext method
		 * 
		 * @return BlockStackNode - the next link
		 *  of the current BlockStackNode
		 */
		public BlockStackNode getNext() {
			return this.next;
		}
		
		/**
		 * setNext method
		 * 
		 * @param next - type BlockStackNode
		 * 
		 * @postcondition the next link of 
		 *  the current BlockStackNode has been
		 *  set to param next
		 */
		public void setNext(BlockStackNode next) {
			this.next = next;
		}
		
		/**
		 * getCodeBlock method
		 * 
		 * @return - type CodeBlock
		 *  the CodeBlock associated with the 
		 *  current BlockStackNode
		 */
		public CodeBlock getCodeBlock() {
			return this.block;
		}
		
		/**
		 * setCodeBlock method
		 * 
		 * @param block - type CodeBlock
		 * 
		 * @postcondition the block of the current
		 * 	BlockStackNode has been set to param 
		 *  block
		 */
		public void setCodeBlock(CodeBlock block) {
			this.block = block;
		}
	}
}


