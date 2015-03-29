/******************************************************************************
* By: Chris Jimenez
*
* HuffmanNode.java
*
* HuffmanNode class represents the Huffman nodes for a Huffman Tree
******************************************************************************/

public class HuffmanNode implements Comparable {
  
  public String letter; 
  public Double frequency;

  //  children of the node
  public HuffmanNode left, right; 


  /**
  *  CONSTRUCTOR
  *  Creates a new HuffmanNode where letter is set to l, 
  *  frequency is set to f, and left and right are set to null.
  */
  public HuffmanNode( String l, Double f){
    frequency = f;
    letter = l;
    
    left = null;
    right = null;
  }
  
  
  /**
  *  CONSTRUCTOR
  *  Constructs a new HuffmanNode from its two children.
  */
  public HuffmanNode(HuffmanNode left, HuffmanNode right){
    this.left = left;
    this.right = right;
    
    letter = left.letter + right.letter;
    frequency = left.frequency + right.frequency;
  }


  /**
  *  Returns a string of form "<"+letter+", "+frequency+">".
  */
  public String toString(){
    return " [ <"+letter+", "+frequency+"> ] \n";
  }
  
  
  /**
  * Compares current HuffmanNodes frequency with another
  */
  public int compareTo(Object o){
    HuffmanNode huff = (HuffmanNode) o;
    return this.frequency.compareTo(huff.frequency);
  }  
}