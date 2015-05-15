/***************************************************************************
* By: Chris Jimenez
*
* This program calculates the shortest path to a target from a root.
*
***************************************************************************/


import java.util.*;
import java.io.*;

public class ShortestPathMain {
  /**
  * MAIN
  */
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
      
    BinaryTree bt = fileToTree(args[0]);
    
    System.out.println("Is there a particular character you are looking for? ");
    String target = input.nextLine();
    
    double shortestPathDistance = bt.findClosest(bt.root, target);
    
    //-1 indicates that the target could not be found
    if(shortestPathDistance != -1) {
      System.out.println("Found "+ target + " at distance " + shortestPathDistance);
    } else {
      System.out.println("Couldnt find that target.");
    }
  }
    
  
  /**
  * Takes input file and converts it to a binary tree.
  */
  public static BinaryTree fileToTree(String filename){
    BinaryHeap bheap = new BinaryHeap();
    String line = null;
    
    //Get the text file with the input
    try{
      BufferedReader br;
        br = new BufferedReader(new FileReader(filename));

      line = br.readLine();
      
    } catch(Exception e){
      System.out.println("Error! File doesn't exist!");
    }
    
        
    Scanner tokenizer = new Scanner(line);
    
    //create a stack
    BoundedStackInterface<TreeNode> stack = new ArrayStack<TreeNode>(100); 
    
    while(tokenizer.hasNext()){
      TreeNode left_child;
      TreeNode right_child;
      TreeNode parent;
      String temp = tokenizer.next();

      if(temp.equals("(")){}//do nothing
      //pop off last three nodes and set the first two as children of the last popped node
      //then puch parent back
      else if( temp.equals(")")){
        left_child = stack.top();
        stack.pop();
        right_child = stack.top();
        stack.pop();
        parent = stack.top();
        stack.pop();
        parent.setLeft(left_child);
        parent.setRight(right_child);
        stack.push(parent);

      } else {//push new tree node
        double distance = Double.parseDouble(tokenizer.next());
        stack.push(new TreeNode(temp, distance));
      }     
    }
    
    //get final node and create a binary tree object with said node
    TreeNode finalNode = stack.top();
    stack.pop();
    
    BinaryTree bt = new BinaryTree(finalNode);

    return bt;
  }
}