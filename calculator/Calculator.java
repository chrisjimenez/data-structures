/****************************************************************************
* By: Chris Jimenez
*
* Title: Calculator.java
* Files: Converter, PostfixEvaluator, PostfixConverter, ArrayStack,
* BoundedStackInterface, StackInterface, PostfixException,
* StackOverflowException, StackUnderflowException
*
* *Some files were retrieved from the book
*
* A calculator that takes an infix expression, converts
* it to a postfix expression and evaluates it.
******************************************************************************/

import java.util.*;
import java.lang.*;

public class Calculator {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
        
    String line;          // string to be evaluated
    String more;          // used to stop or continue processing
    double result;        // result of evaluation
    
    
    do{
      // Initializing both the infix and postfix expression to be emtpy strings
      String infix = "";
      String postFix = "";
            
      // Prompt user for infix expression
      System.out.println("Enter a infix expression to be evaluated: ");
      line = input.nextLine();
            
            
      // This for loop will evaluate an infix expression with or without spaces and create an
      // infix expression with spaces that can be evaluated by the converter
      for( int i = 0; i < line.length(); i++){
        char c = line.charAt(i);
                
        if(!Character.isDigit(c)){
          infix = infix + " " + c + " ";          
        }else {
          infix += c;
        }
      }
            
            
      // Obtain and output the result
      try{
        Converter c = new Converter(infix); // instantiate a converter object
        postFix = c.toPostFix();//postfix expression retrieved form toPostFix() method
                
                
        // Displaying the converted infix expression.
        System.out.println("Converted to a postfix expression: \n" + postFix);
                
        // Result of the evalute method after an postfix expression was passed
        result = evaluate(postFix);
                
        // Output the result
        System.out.println();
        System.out.println("Result: " +result);
                
      } catch (PostFixException error) {
          // Output error message
          System.out.println();
          System.out.println(error.getMessage());
      }
            
      // Determine if there is another expression to process
      System.out.println();
      System.out.print("Would you like to evaluate another expression?");
      more = input.nextLine();
      System.out.println();

    } while (more.equalsIgnoreCase("y"));
        
    System.out.println("Calculation compeleted.");
  }
  

  /**
  * Evalute the postfix expression and returns a result of type double.
  */
  public static double evaluate(String expression){
    BoundedStackInterface<Double> stack = new ArrayStack<Double>(50);
    String operator;
        
    double operand1;
    double operand2;
    
    // Initialize the result
    double result = 0.0;
        
    Scanner tokenizer = new Scanner(expression);
        
    while (tokenizer.hasNext()){

      if (tokenizer.hasNextInt()){
        // Process operand
        double value = tokenizer.nextInt();
        if (stack.isFull()){
            throw new PostFixException("There are too many operands.");
        }
        
        stack.push(value);
                
      } else {
        // Process operator
        operator = tokenizer.next();
        
        // Obtain the second operand by popping the stack
        if (stack.isEmpty()){
          throw new PostFixException("There are not enough operands");
        }

        operand2 = stack.top();
        stack.pop();
                
        // Obtain the first operand by popping the stack again
        if (stack.isEmpty()){
          throw new PostFixException("There are not enough operands");
        }

        operand1 = stack.top();
        stack.pop();
                
        // Perform the operations including the power operation
        if (operator.equals("/"))
          result = operand1 / operand2;
        else if(operator.equals("*"))
          result = operand1 * operand2;
        else if(operator.equals("+"))
          result = operand1 + operand2;
        else if(operator.equals("-"))
          result = operand1 - operand2;
        else if(operator.equals("^"))
          result = Math.pow(operand1,operand2);
        else
          throw new PostFixException("Cant use the following symbol: " + operator);
                
        // Push result of operation onto the stack
        stack.push(result);
      }
    }
        
    // Obtain final result from stack 
    if (stack.isEmpty()){
      throw new PostFixException("There are not enough operands.");
    }

    result = stack.top();
    stack.pop();
    
    // Stack should now be empty but if it isnt, then there are operands leftover
    if (!stack.isEmpty()){
      throw new PostFixException("There are too many operands.");
    }
        
    // Return the final result
    return result;
  }
    
}