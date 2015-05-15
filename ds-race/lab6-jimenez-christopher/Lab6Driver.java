/////////////////////////////////////////////////////////////////////////////
// Author: Chris Jimenez							
// Email: caj303@nyu.edu
// Lecturer's Name: Allan GottLieb
// Semester: CS102 Spring 2013
//
// **************************************************************************
// Assignment: Lab #6
// Title: Lab6Driver.java
// **************************************************************************
// Purpose: This program drives Lab 6, which races four data structures.
/////////////////////////////////////////////////////////////////////////////
import java.util.*;
import java.lang.Math;
import ch08.trees.*;
import support.BSTNode;

 
public class Lab6Driver{
	private static Random random = new Random(123999123L);
  	public static final int NUMBER_OF_NUMBERS = 100000;
  	public static final int NUMBER_OF_CHOICES = 1000;
	static Long[] numbers = new Long[NUMBER_OF_NUMBERS];
	static int[] choices  = new int[NUMBER_OF_CHOICES];
	
	//Creating the data structures to be searched
	static Long[] unsorted = new Long[NUMBER_OF_NUMBERS]; // copy of choices
	static Long[] sorted = new Long[NUMBER_OF_NUMBERS]; 
	static BinarySearchTree<Long> bst = new BinarySearchTree<Long>();
	static BinarySearchTree<Long> bstBalanced = new BinarySearchTree<Long>();
	
	
    /*********************************************************************
     * raceDataStructures() method
     * parameter- none
	* races the data structures 
     *********************************************************************/
	private static void raceDataStructures(){
		// long variables to keep track of time for each test
		long startTime, elapsedTime;	
		
		//Race the unsorted array
		System.out.println("For the unsorted array:");
		startTime = (new Date()).getTime();	// Initialized to now
		for(int i = 0; i < NUMBER_OF_CHOICES; i++){
			Long number = numbers[i];
			if(!Arrays.asList(unsorted).contains(number)){
				System.out.println(number + "was not found.");
			}
			if(Arrays.asList(unsorted).contains(123L)){
				System.out.println("123L was found.");
			}
		}
		elapsedTime = (new Date()).getTime() - startTime;
		System.out.println("The unsorted array took " + elapsedTime + " seconds.\n");
		

		//Race the sorted array
		System.out.println("For the sorted array:");
		startTime = (new Date()).getTime();	// Initialized to now
		for(int j = 0; j < NUMBER_OF_CHOICES; j++){
			Long number = numbers[j];
			if(!Arrays.asList(sorted).contains(number)){
				System.out.println(number + "was not found.");
			}
			if(Arrays.asList(sorted).contains(123L)){
				System.out.println("123L was found.");
			}
		}
		elapsedTime = (new Date()).getTime() - startTime;
		System.out.println("The sorted array took " + elapsedTime + " seconds.\n");


		//Race the unbalanced BST
		System.out.println("For the Binary Search Tree, bst:");
		startTime = (new Date()).getTime();	// Initialized to now
		for(int k = 0; k < NUMBER_OF_CHOICES; k++){
			Long number = numbers[k];
			if(!bst.contains(number)){
				System.out.println(number + "was not found.");
			}
			if(bst.contains(123L)){
				System.out.println("123L was found.");
			}
		}
		elapsedTime = (new Date()).getTime() - startTime;
		System.out.println("The unbalanced BST took " + elapsedTime + " seconds.\n");


		//Race the balanced BST
		System.out.println("For the balanced Binary Search Tree, bstBalanced:");
		startTime = (new Date()).getTime();	// Initialized to now
		for(int l = 0;l < NUMBER_OF_CHOICES; l++){
			Long number = numbers[l];
			if(!bstBalanced.contains(number)){
				System.out.println(number + "was not found.");
			}
			if(bstBalanced.contains(123L)){
				System.out.println("123L was found.");
			}
		}
		elapsedTime = (new Date()).getTime() - startTime;
		System.out.println("The balanced BST took " + elapsedTime + " seconds.\n");

	}
	
    /*********************************************************************
     * createDataStructures() method
     * parameter- none
	* creates the data structures that will be raced..
     *********************************************************************/
	private static void createDataStructures(){
		//fill in numbers[] and unsorted[]
		for(int i =0; i < NUMBER_OF_NUMBERS; i++){
			numbers[i] = random.nextLong();
			unsorted[i] = numbers[i];
			sorted[i] = numbers[i];
		}

		//sort the unsorted[] array and assign to sort
		sort(sorted);

		//fill choices[] array
		for(int i = 0; i < NUMBER_OF_CHOICES; i++){
			choices[i] = random.nextInt(NUMBER_OF_NUMBERS);
		}

		//fill in the bst and bstBalanced tree
		for(int i = 0; i < NUMBER_OF_NUMBERS; i++){
			bst.add(numbers[i]);
			bstBalanced.add(numbers[i]);
		}
		
		//now balance bstBalanced tree using balance method...
		balance(bstBalanced);
	}

    /*********************************************************************
     * getHeight() method(private recursive method)
     * parameter- root of the BST of type BSTNode
     * gets the height of the tree
     *********************************************************************/
	private static int getHeight(BSTNode<Long> bstNode){
	    if(bstNode == null){
	        return -1;
        }else{
            return Math.max(getHeight(bstNode.getLeft()), getHeight(bstNode.getRight()))+ 1;
        }
    }
	
    /*********************************************************************
     * sort() method
     * parameter- array of type Long
	* sort an array of type long in ascending order
     *********************************************************************/
	private static void sort(Long[] array) {
    		for (int i = 0; i < array.length-1; i++){
      		for (int j = i+1; j < array.length; j++){
        			if (array[i] > array[j]){
           			Long t = array[i];
           			array[i] = array[j];
          				array[j] = t;
        			}
    			}
  		}
	}

    /*********************************************************************
     * balance() method
     * parameter- a BST
	* balances an unbalanced BST
     *********************************************************************/
	private static void balance(BinarySearchTree<Long> tree) {
		//1 indicates INRODER traversal
    		int n = tree.reset(1);
		Long[] balancedTreeArray = new Long[n];

    		for(int i=0; i<n; i++){
      		balancedTreeArray[i] = tree.getNext(1);
      	}
    		tree = new BinarySearchTree<Long>();
   		insertTree(0, n-1, tree, balancedTreeArray);
   	}

    /*********************************************************************
     * insertTree() method
     * parameter- lo, hi(both ints) a BST and an array of type Long
	* Inserts a tree 
     *********************************************************************/
   	private static void insertTree(int lo, int hi, BinarySearchTree<Long> tree, Long[] array) {
		if (lo <= hi) {
	     	 	int mid = (lo+hi)/2;
	      	tree.add(array[mid]);
	      	insertTree(lo, mid-1, tree, array);
			insertTree(mid+1, hi, tree, array);
		}
	}
	
    /*********************************************************************
     * main() method
	* Driver of the class..
     *********************************************************************/
	public static void main(String[] args){
		createDataStructures();
		
		System.out.println("The height of the unbalanced Binary Search tree is " + getHeight(bst.root));
		System.out.println("The height of the balanced Binary Search tree is " + getHeight(bstBalanced.root));
		
		raceDataStructures();
	}
  }	