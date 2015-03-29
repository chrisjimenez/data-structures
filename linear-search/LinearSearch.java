/***************************************************************************
* By: Chris Jimenez
*
* This program performs a linear search
*
****************************************************************************/

import java.util.*;
import java.lang.*;

public class LinearSearch {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    
    //Prompt user for an integer N
    int N;
    System.out.println("Please provide an integer N.");
    N = input.nextInt();
    
    //array which will hold all N integers
    int[] items = new int[N];
    
    System.out.println("Please provide " + N + " more integers which will be considered items in a list.");
    for (int i = 0; i < items.length; i++){
      items[i] = input.nextInt();
      
      
    }
    
    //Ask user for a value to see if it is in the item list
    System.out.println("Please type in a value to verify that it is an item in the item list. The value 0 will terminate the program.");
    
    int value;
    
    //a do while loop that loops until the value given form the user is 0, which terminates the program
    do{
      value = input.nextInt();
      
      //call vlaueInItem method to determine if value is in item list
      if(value == 0){
        System.out.println("End of Program.");
      } else if(valueInItem(value, items) == true){
        System.out.println(value +" yes");
      } else {
        System.out.println(value +" no");
      }
    
    }while(value != 0);
    
  }
  
  //method which determines if given value is in the given array list
  public static boolean valueInItem(int currentValue, int[] itemsArr){
    boolean result = false;
    for (int i = 0; i<itemsArr.length; i++){
      if(currentValue == itemsArr[i]){
        result = true;
      }
    }
    
    return result;
  }
}