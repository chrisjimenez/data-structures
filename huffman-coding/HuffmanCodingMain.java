/******************************************************************************
* By: Chris Jimenez
*
* HuffmanTree.java
*
* This program demonstrates the efficiency of Huffman coding
* for compressing a file. 
******************************************************************************/
import java.util.*;
import java.io.*;

public class HuffmanCodingMain {
  HuffmanNode root;
  
  /**
  * CONSTRUCTOR
  */
  public HuffmanTree(HuffmanNode huff){
    this.root = huff;
  }
  
  
  /**
  * prints the legend for the Huffman tree by calling the private method printLegend().
  */
  public void printLegend(){
    printLegend(root, "");
  }
  

  /**
  * prints the legend for the Huffman tree by printing the letter
  * and its corresponding binary digits.
  */
  private void printLegend(HuffmanNode t, String s){
    if( t.letter.length() > 1){
      printLegend(t.left, s + "0");
      printLegend(t.right, s + "1");
    } else {
      System.out.println(t.letter + " = "+ s);
    }
  
  }
  
  /**
  * Takes in the file name, retrieves file and tokenizes it. Once
  * it is tokenized, each token and its proceeding token is taken as
  * letter and frequency and both will create a HuffmanNode which will
  * be inserted in to a binary heap.
  */
  public static BinaryHeap fileToHeap(String filename){
    
    BinaryHeap bheap = new BinaryHeap();

    String line = null;
    
    try{
      BufferedReader br = new BufferedReader(new FileReader(filename));

      line = br.readLine(); 
    } catch(Exception e) {
      System.out.println("Error! File doesn't exist!");
    }
    
        
    Scanner tokenizer = new Scanner(line);
    
    while(tokenizer.hasNext()) {
      String letter = tokenizer.next();
      Double frequency = Double.valueOf(tokenizer.next());
      
      HuffmanNode hn = new HuffmanNode(letter, frequency);
      bheap.insert(hn);   
    }
    
    return bheap;
  }

  /**
  * Creats a Huffman tree using the passed in BinaryHeap b
  */
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
  
  
  /**
  * MAIN
  */
  public static void main(String[] args){
        
    Scanner input = new Scanner(System.in);
    
    String filename;

// Current commmented out, hard coded input file      
//    System.out.println("Please type in the name of files");
//    filename = input.nextLine();

        
    // create a binary heap using the file the user typed in.
    BinaryHeap bheap = fileToHeap("huff.txt");
    
    // print binary heap using the toString() for all elements in bheap
    bheap.printHeap();
    
    // run Huffman algorithm and return a Huffman tree
    HuffmanTree htree = createFromHeap(bheap);
    
    System.out.println();
    
    htree.printLegend();    
  }
}