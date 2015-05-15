///////////////////////////////////////////////////////////////////////////////
// Author: Chris Jimenez
// Email: caj303@nyu.edu
// Lecturer's Name: Evan Korth
// Semester: CS102 Fall 2012
//
// ****************************************************************************
// Assignment: Assignment #4
// Title: HuffmanTree.java
// Files: HuffmanNode.java, BinaryHeap.java, UnderflowException.java, huff.txt
//
// ****************************************************************************
// Purpose: This class will practice the efficiency of Huffman coding
// for compressing the file. Huffman coding will produce an optimal coding
// scheme and uses binary trees for encoding.
//
///////////////////////////////////////////////////////////////////////////////
import java.util.*;
import java.io.*;

public class HuffmanTree{
	HuffmanNode root;
	
	/*[HuffmanTree constructor]*****************************************
	* Construct the binary heap.
	********************************************************************/
	public HuffmanTree(HuffmanNode huff){
		this.root = huff;
	}
	
	/*[printLegend() method]********************************************
	* prints the legend for the Huffman tree by calling the private
	* method printLegend().
	********************************************************************/
	public void printLegend(){
		printLegend(root, "");
		
	}
	
	/*[printLegend() method]********************************************
	* prints the legend for the Huffman tree by printing the letter and
	* its corresponding binary digits.
	*******************************************************************/
	private void printLegend(HuffmanNode t, String s){
		if( t.letter.length() > 1){
			printLegend(t.left, s+"0");
			printLegend(t.right, s+"1");
		}else
			System.out.println(t.letter + " = "+ s);
	
	}
	
	/*[fileToHeap() method]*********************************************
	* Takes in the file name, retrieves file and tokenizes it. Once
	* it is tokenized, each token and its proceeding token is taken as
	* letter and frequency and both will create a HuffmanNode which will
	* be inserted in to a binary heap.
	*******************************************************************/
	public static BinaryHeap fileToHeap(String filename){
		
		BinaryHeap bheap = new BinaryHeap();
		
		String line = null;
		
		try{
			BufferedReader br;
		   	br = new BufferedReader(new FileReader(filename));

			line = br.readLine();
			
		}catch(Exception e){
			System.out.println("Error! File doesn't exist!");
		}
		
				
		Scanner tokenizer = new Scanner(line);
		
		while(tokenizer.hasNext()){
			String letter = tokenizer.next();
			Double frequency = Double.valueOf(tokenizer.next());
			
			HuffmanNode hn = new HuffmanNode(letter, frequency);
			bheap.insert(hn);		
		}
		
		return bheap;
		
	}
	
	
	/*[createFromHeap() method]*****************************************
	* A Huffman Tree is created by the passed binary heap.
	*******************************************************************/
	public static HuffmanTree createFromHeap(BinaryHeap b){
		
		 while (b.getSize() > 1){
			HuffmanNode tree1 = (HuffmanNode) b.deleteMin();
		   	HuffmanNode tree2 = (HuffmanNode) b.deleteMin();

		   	HuffmanNode merged = new HuffmanNode(tree1, tree2);
		    	b.insert(merged);
		}
		HuffmanNode finalNode = (HuffmanNode) b.deleteMin();
		
		HuffmanTree hf = new HuffmanTree(finalNode);
		
		return hf;
		
	}
	
	
	
	
	/*[main method]*****************************************************
	* Main method that will implement all the classes in this assignment.
	********************************************************************/
	public static void main(String[] args){
				
		//Create a binary heap using the file the user typed in.
		BinaryHeap bheap = fileToHeap(args[0]);
		
		//Print binary heap using the toString() for all elements in bheap
		bheap.printHeap();
		
		//Run Huffman algorithm and return a Huffman tree
		HuffmanTree htree = createFromHeap(bheap);
		
		System.out.println();
		
		//Print Huffman Tree legend
		htree.printLegend();
		
	}


}
 