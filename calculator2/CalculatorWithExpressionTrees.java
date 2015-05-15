///////////////////////////////////////////////////////////////////////////////
// Author: Chris Jimenez
// Email: caj303@nyu.edu
// Lecturer's Name: Evan Korth
// Semester: CS102 Fall 2012
//
// ****************************************************************************
// Assignment: Assignment #3
// Title: CalculatorWithExpressionTrees.java
// Files: Converter.java, ExpTreeNode.java, NumberNode.java, OperatorNode.java
// ArrayStack.java, BoundedStackInterface.java, StackInterface.java,
// StackUnderflowException.java, StackOverflowException
//
// ****************************************************************************
// Problem Statement: To extend the Calculator built in assignment 1. This time,
// the postfix expression will be convert into an expression tree before it is
// evaluated.
//
///////////////////////////////////////////////////////////////////////////////

import java.util.*;
import java.lang.*;

public class CalculatorWithExpressionTrees {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
        
        String line;          // string to be evaluated
        String repeat;          // used to stop or continue processing
        double result;               // result of evaluation
        
		do{
			//Initializing both the infix and postfix expression to be emtpy strings
			String infix = "";
			String postFix = "";
			
			//Prompt user for infix expression
			System.out.println("Enter a infix expression to be evaluated: ");
	      	line = input.nextLine();
            
            
			// This for loop will evaluate an infix expression with or without spaces and create an
			// infix expression with spaces that can be evaluated by the converter
			for( int i = 0; i < line.length(); i++){
				char c = line.charAt(i);
				
				if(!Character.isDigit(c)){
					infix = infix + " " + c + " ";
                    
				}else
					infix += c;
			}
            
            
			// instantiate a converter object
			Converter c = new Converter(infix);
 	   		
			//postfix expression retrieved form toPostFix() method
			postFix = c.toPostFix();
            
	   		//result of the evalute method after an postfix expressisn was passed
	   		result = evaluate(postFix);
            
            // Output the result
            System.out.println();
            System.out.println("Result = " +result);
            
            
	      	// Determine if there is another expression to process.
			System.out.println();
	      	System.out.print("Would you like to evaluate another expression?");
			repeat = input.nextLine();
			System.out.println();
		}while (repeat.equalsIgnoreCase("y"));
        
		System.out.println("Program completed.");
	}
	
	/*[evaluate() method]********************************************************************
     
     This function will evalute the postfix expression and return a result of type double.
     Certain methodologies were retrieved from the book as instructed by the assignment manual
     
     ****************************************************************************************/
	public static double evaluate(String expression){
		//Creating an empty stack of type ExpTreeNode
		BoundedStackInterface<ExpTreeNode> stack = new ArrayStack<ExpTreeNode>(100);
        
        double value;
        String operator;
        
		Scanner tokenizer = new Scanner(expression);
        
		while (tokenizer.hasNext()){
			if (tokenizer.hasNextInt()){
				// Process operand.
                value = tokenizer.nextInt();
				
		   		NumberNode node = new NumberNode(value);
				stack.push(node);
	      	}else{// Process operator.
				operator = tokenizer.next();
                
				ExpTreeNode expNode1 = stack.top();
				stack.pop();
                
				ExpTreeNode expNode2 = stack.top();
				stack.pop();
                
				OperatorNode node = new OperatorNode(operator, expNode2, expNode1);
                
                //Push result of operation onto stack.
                stack.push(node);
	      	}
		}
		
		
		//Display the infix, postfix, and prefix expression of the input expression
		System.out.println("Infix Expression: " );
		stack.top().infixOrderTraverse();
		System.out.println();
		
		System.out.println("Postfix Expression: ");
		stack.top().postOrderTraverse();
		System.out.println();
        
		System.out.println("Prefix Expression: " ); 
		stack.top().preOrderTraverse();
        
        
        
		return stack.top().value();
	}
    
}