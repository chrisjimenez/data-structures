///////////////////////////////////////////////////////////////////////////////
// Author: Chris Jimenez
// Email: caj303@nyu.edu
// Lecturer's Name: Evan Korth
// Semester: CS102 Fall 2012
//
// ****************************************************************************
// Assignment: Assignment #5
// Title: HuffmanConverter.java
// Files:HuffmanTree.java, HuffmanNode.java, BinaryHeap.java,
// UnderflowException.java, love_poem_58.txt, love_poem_80.txt
//
// ****************************************************************************
// Purpose: This class will convert a message in to Huffman code.
// This class will practice the efficiency of Huffman coding
// for compressing the file. Huffman coding will produce an optimal coding
// scheme and uses binary trees for encoding. 
//
///////////////////////////////////////////////////////////////////////////////

import java.io.*;
import java.util.*;

public class HuffmanConverter{ 
	// The # of chars in the ASCII table dictates
  	// the size of the count[] & code[] arrays.
  	public static final int NUMBER_OF_CHARACTERS = 256;
 
  	// the contents of our message...
  	private static String contents = null;
 
 	 // the tree created from the msg
  	private HuffmanTree huffmanTree;
 
  	// tracks how often each character occurs
  	private int count[];
 
  	// the huffman code for each character
  	public static String code[];
 
  	/*HuffmanConverter()***************************************************
   	* constructor taking input string as contents. Calls recordFrequencies()
	* method torecord then number of time a specific character occurs in the
	* input
   	************************************************************************/
  	public HuffmanConverter(String input){
		this.contents = input;
    		this.count = new int[NUMBER_OF_CHARACTERS];
    		this.code = new String[NUMBER_OF_CHARACTERS];

		//Call to recordFrequencies method
		recordFrequencies();
  	}
 
  	/*recordFrequencies()***************************************************
   	* Records the frequencies of each character of our message occurs and 
	* uses the contents to fill up the count[] list. Calls
	* frequenciesToTree() method which converts count[] in to a Huffman Tree
   	************************************************************************/
  	public void recordFrequencies(){
		//for each character in contents
		for(int i = 0; i < contents.length(); i++){
			char currentChar = contents.charAt(i);
			
			//increase the frequency 
			count[(int)currentChar]++;
		}
		
		//Call to frequenciesToTree()
		frequenciesToTree();
  	}
 
  	/*frequenciesToTree()***************************************************
   	* Converts our frequency list into a Huffman Tree. We do this by
   	* taking our count[] list of frequencies, and creating a binary
   	* heap. Then the heap is printed a call to HuffmanTree.createFromHeap()
	* to get our much desired HuffmanTree object, which we store as a
	* huffmanTree. Finally, the treeToCode() method is called 
   	************************************************************************/
  	public void frequenciesToTree()  {
		//Instantiate a new binary heap object
		BinaryHeap bheap = new BinaryHeap();
		Double frequency;
		String letter;
		
		//for each element in count[]
		for(int i = 0; i < count.length; i++){
			frequency = (double)count[i];
			
			//if character is equal tp '\n', letter will equal to "\\n" so that
			// a new line operation can be avoided. Otherwise, letter is equal
			// to the charcter, converted to string, of i
			if((char)i == '\n') {
				letter = "\\n";
			}else{
				letter = Character.toString((char) i);
			}
			//create a huffman node and insert it in to bheap
			HuffmanNode hn = new HuffmanNode(letter, frequency);
			bheap.insert(hn);
		}
		
		//Binary heap is printed
		bheap.printHeap();
		
		//Using createFromHeap(), huffman tree is created from bheap
		huffmanTree = HuffmanTree.createFromHeap(bheap);
		
		//Call to treeToCode() method
		treeToCode();
	}   
 
  	/*treeToCode()********************************************************
   	* Iterates over the huffmanTree to get the code for each letter.
   	* The code for letter i gets stored as code[i].
   	**********************************************************************/
  	public void treeToCode(){
		//Initializing all elements to ""
		for(int i = 0; i < code.length; i++){
			code[i] = "";	
		}
		
		//call to recursive form of treeToCode()
		treeToCode(huffmanTree.root,"");
	}
 
  	/*treeToCode()********************************************************
   	* A private method to iterate over a HuffmanNode t using s, which
   	* contains what we know of the HuffmanCode up to node t. This is
   	* called by treeToCode().
   	**********************************************************************/
  	private void treeToCode(HuffmanNode t, String s){
		//if the letter is "\\n", then use code for '\n', given that
		// earlier in the program, '\n' was changed to "\\n". Othewise
		// if letters length is greater than one, call treeToCode() 
		// for both of current node's children
		if( t.letter == "\\n"){
			code['\n'] = s;
		
		}else if(t.letter.length() > 1){
			treeToCode(t.left, s + "1");
			treeToCode(t.right, s + "0");
		}else{
			
			code[t.letter.charAt(0)] = s;
		}
	}			

  	/*printHuffCode()******************************************************
   	* Prints the huffcode for all the characters used in a table format
   	**********************************************************************/
  	public void printHuffCode(){
		System.out.println();
		System.out.printf("%-20s %-20s","Letter","Huffman Code");
		for(int i=0; i < NUMBER_OF_CHARACTERS; i++){
			if(count[i] != 0){
				char letter = (char)i;
				if(letter == '\n'){
					System.out.println();
					System.out.printf("%-20s %-20s %n","'\\n'", code['\n']);
				}else {
					System.out.printf("%-20s %-20s %n","'" + letter + "' ", code[i]);
				}
			}
		}
	
	}
			

  	/*encodeMessage()******************************************************
   	* Using the message stored in contents, and the huffman conversions
   	* stored in code[], the Huffman encoding for the content is created and
	* returned.
   	***********************************************************************/
  	public String encodeMessage(){
		String encodedMessage = "";
        	
        	for(int i = 0; i< contents.length(); i++){
			encodedMessage = encodedMessage + code[contents.charAt(i)];
        	}
		
		return encodedMessage;	
	}
 
  	/*readContents()******************************************************
   	* Reads in the contents of the file named filename and returns
   	* it as a String. The main method calls this method on args[0]
   	***********************************************************************/
  	public static String readContents(String filename){		
		try{
			
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			StringBuilder sb = new StringBuilder();

			String line = null;
			while ((line=reader.readLine()) != null) {
			  sb.append(line);
			  sb.append("\n");
			}
			
			contents = sb.toString();
		}catch(Exception e){
			System.out.println("Error! File doesn't exist!");
		}
		
		return contents;
	
	}
 
  	/*decodeMessage()******************************************************
   	* Using the encoded String argument, and the huffman codings,
   	* re-create the original message from our
   	* huffman encoding and return it...
   	**********************************************************************/
  	public String decodeMessage(String encodedStr){
	
        //current code that has been read form encodedStr
		String readCode = "";
				
        //message that will be returned
		String message = "";
        
        
		//for each character in encodedStr
		for( int i = 0; i < encodedStr.length(); i++){
			readCode = readCode + encodedStr.charAt(i);
            
			//for each element in code[]
			for(int j = 0; j < code.length; j++){
                //if readCode equals code at current element,
                //message will equal message + the character
                //representation of the current elements
                //position
				if(readCode.equals(code[j])){
					message = message + (char)j;
					
					readCode = "";
				}
			}
		}
		
		return message;
	}
 
  	/*main()*************************************************************
   	* main() mehtod uses args[0] as the filename, and reads in its 
    * contents. Then it instantiates a HuffmanConverter object, using 
    * its methods to obtain the huffcode for all the characters, 
    * encoded message, and decoded message.
   	*********************************************************************/
  	public static void main(String args[]){
		
		String message = readContents(args[0]);
        
		//Instantiate huffman converter object
		HuffmanConverter hc = new HuffmanConverter(message);
		
        //print the huffcode for all the characters in the message
		hc.printHuffCode();
        
        //print encoded message
		String encodedMessage = hc.encodeMessage();
		System.out.println("\nHuffman Encoding:\n" + encodedMessage);
        
		System.out.println("\nSize of the message using ASCII coding: " + message.length()*8);
		System.out.println("Size of the message using Huffman coding: " + encodedMessage.length());
        
		//print decoded message
		String decodedMessage = hc.decodeMessage(encodedMessage);
		System.out.println("\nThe encoded message decoded: \n" + decodedMessage);
	} 	
}