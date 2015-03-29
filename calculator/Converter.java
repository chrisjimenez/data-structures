/***************************************************************************
* Author: Chris Jimenez
*
* Converter.java
*
* This class is to convert an infix expression in to a
* postfix expression and set precedence for evaluations
****************************************************************************/

import java.util.*;
import java.io.*;
import java.lang.*;

public class Converter {        
    String infixExp;
    
    
    /**
    * CONSTRUCTOR
    */
    public Converter(String infix){
        infixExp = infix;
    }
    

    /**
    * Determines if the evaluated string is an operand that could be evaluated. 
    */
    public boolean isOperand(String s){
        double a = 0;
        
        //If the string cannot be parsed in ot a double, then return false
        try{
            a = Double.parseDouble(s); 
        } catch (Exception ignore){
            return false;
        }
    
        return true;
    }
    
    /**
    * Determines wether a string is na operator that could be used.
    */
    public static boolean isOperator(String s){
        //Anything in this list will be considered an operator and will be later used
        // in the toPostFix() method
        String opList="+-*/^";
        
        
        return (opList.indexOf(s) >= 0);
    }
    
    
    /**
    * sets the precedence for the operators that are being pulled from the tokenizer. 
    * Returns an interger which indicates the precedence
    */
    public int precedence(String operator){
        int precedence = 0;
        
        if (operator.equals("+"))
            precedence = 1;
        else if (operator.equals("-"))
            precedence = 1;
        else if (operator.equals("*"))
            precedence = 2;
        else if (operator.equals("/"))
            precedence = 2;
        else if(operator.equals("^"))
            precedence = 3;
        
        return precedence;
    }
    

    /**
    * Converts the infix expression in to a postfix espression
    */
    public String toPostFix(){
        
        //create a stack
        BoundedStackInterface<String> stack = new ArrayStack<String>(100);
        
        //initialize postfix expression
        String postfix = "";
        Scanner tokenizer = new Scanner(infixExp);
        
        //while there is a next token
        while (tokenizer.hasNext()){
            String token = tokenizer.next();
            if (isOperand(token))
                postfix += token + " ";
            
            //if the next token is an open parenthesis push token
            else if (token.equals("("))
                stack.push(token);
            else if (isOperator(token)){
                while (!stack.isEmpty() && precedence(stack.top()) >= precedence(token)){
                    postfix += stack.top() + " ";
                    stack.pop();
                }
                stack.push(token);
            }
            
            // If the token is equal ot a close parenthesis, while the value on top of the stack
            // is not a close parenthesis, add to postfix and pop stack
            else if (token.equals(")")){
                while (!stack.top().equals("(")){
                    postfix += stack.top() + " ";
                    stack.pop();
                }
                stack.pop();  // pop the left parenthesis
            }
        }
        
        while (!stack.isEmpty()){
            postfix += stack.top() + " ";
            stack.pop();
        }
        
        return postfix;
    }   
}
