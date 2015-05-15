///////////////////////////////////////////////////////////////////////////////
// Author: Chris Jimenez
// Email: caj303@nyu.edu
// Lecturer's Name: Evan Korth
// Semester: CS102 Fall 2012
//
// ============================================================================
// Assignment: Assignment #3
// Title: NumberNode.java
// ============================================================================
// Purpose: This class represents the properties and behaviors of a number node
// in a expression tree
//
///////////////////////////////////////////////////////////////////////////////

public class NumberNode extends ExpTreeNode{
    // Represents a node that holds a number
    
    // The value in the node
    double number;  
    
    //The constructor which takes in a value that will be the value of the node
    NumberNode( double val ) {
       number = val;
    }
    
    // The value is just the number that the node holds.
    double value() {
        return number;
    }

    //method that outputs the the preorder traversal of the expression tree
	void preOrderTraverse(){
		System.out.print(" " + number +" ");
	}
    
    //method that outputs the the infix order traversal of the expression tree
	void infixOrderTraverse(){
		System.out.print(" "+ number + " ");
	}
    
    //method that outputs the the postorder traversal of the expression tree
	void postOrderTraverse(){
		System.out.print(" "+ number + " ");
	}



 } // end or NumberNode class