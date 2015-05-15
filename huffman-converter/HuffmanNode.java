//////////////////////////////////////////////////////////////////////////
// Author: Chris Jimenez
// Email: caj303@nyu.edu
// Lecturer's Name: Evan Korth
// Semester: CS102 Fall 2012
//
// ***********************************************************************
// Assignment: Assignment #4
// Title: HuffmanNode.java
// ***********************************************************************
// Purpose: HuffmanNode class represents the Huffman nodes for the 
// Huffman Tree
//
//////////////////////////////////////////////////////////////////////////



public class HuffmanNode implements Comparable{
	
	public String letter; 
   	public Double frequency;
   	public HuffmanNode left, right;	//children of the node
    
    private static int counter;


	/*[HuffmanNode constructor]*******************************************
	* This constructor creates a new HuffmanNode where letter is set to l, 
	* frequency is set to f, and left and right are set to null.
	*********************************************************************/
	public HuffmanNode( String l, Double f){
		frequency = f;
		letter = l;
		
		left = null;
		right = null;        
	}
	
	/*[HuffmanNode constructor]*******************************************
	* This constructor creates a new HuffmanNode from its two children.
	*********************************************************************/
	public HuffmanNode(HuffmanNode left, HuffmanNode right){
		this.left = left;
		this.right = right;
		
		letter = left.letter + right.letter;
		frequency = left.frequency + right.frequency;

	}
	
	/*[toString() method]************************************************
	* Returns a string of form "<"+letter+", "+frequency+">".
	*********************************************************************/
	public String toString(){
        while(frequency != 0){
            counter++;
            return counter+": [<"+letter+", "+frequency+">] \n";
        }
        return "";
	}
	
	
	/*[compareTo() method]************************************************
	* This method will compare the frequencies.
	*********************************************************************/
	public int compareTo(Object o){
		
		HuffmanNode huff = (HuffmanNode) o;
		return this.frequency.compareTo(huff.frequency);
	}
	
	
}