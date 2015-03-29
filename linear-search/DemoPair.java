/****************************************************************************
* By: Chris Jimenez           
* DemoPair.java
*
* This program performs linear combination to a list of a pair of values
****************************************************************************/
import java.util.*;
import java.lang.*;

public class DemoPair{
  public static void main(String[] args){
    
    //array of size 100 of Pair objects
    Pair[] arrPair = new Pair[100];
    //Heading for the current element, x, y, and outpit
    System.out.printf("%4s %8s %8s %12s %n", "ELEMENT" ,"X", "Y", "OUTPUT");
    System.out.println("___________________________________________");
        
    for( int i = 0; i < arrPair.length; i++){
      //two random double values which will be used as paramters 
      //for the current Pair elements constructor
      double a = Math.random() * 100;
      double b = Math.random() * 100;
      
      arrPair[i] = new Pair(a, b);
      
      //computed value calculated using linearCombination method
      double result = arrPair[i].linearCombination(i+1 , arrPair.length-i);
      
      //Print out the current element, x, y, and result of the linearCombination method call
      System.out.printf("%-8d || %-6.3f || %-6.3f || %-6.3f %n", i, a, b, result);
    }
  } 
}