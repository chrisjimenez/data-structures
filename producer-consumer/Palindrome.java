/////////////////////////////////////////////////////////////////////////////
// Author: Chris Jimenez							
// Email: caj303@nyu.edu
// Lecturer's Name: Allan GottLieb
// Semester: CS102 Spring 2013
//**This source code was initially written by professor Gottleib, edited by
// me, Chris Jimenez
//
// **************************************************************************
// Assignment: Lab #5
// Title: Palindrome.java
// **************************************************************************
// Purpose:The purpose of this program is to produce a palindrome and verify 
// that they are palindromes using two distinct methods. Eahc method owuld 
// be timed.
//
/////////////////////////////////////////////////////////////////////////////

import ch03.stacks.*;
import ch05.queues.*;
import java.util.Date;

public class Palindrome {
	
	private static final int N = 100;
	private static final int M = 5;
 
	//=====================================================================
	//isPalindromeFancy()
	//=====================================================================
    	public static boolean isPalindromeFancy(String s) {
        	char c;
        	BoundedQueueInterface<Character> queue = new ArrayBndQueue<Character>(s.length());
        	BoundedStackInterface<Character> stack = new ArrayStack<Character>(s.length());
        	for (int i=0; i<s.length(); i++) {
            	c = s.charAt(i);
            	queue.enqueue(c);
            	stack.push(c);
        	}
        	for (int i=0; i<s.length(); i++) {
            	c = queue.dequeue();
            	if (c != stack.top())
                	return false;

            	stack.pop();
        	}

        	return true;
    	}

	//=====================================================================
	//isPalindromePlain()
	//=====================================================================
    	public static boolean isPalindromePlain(String s) {
    		int lo = 0;
    		int hi = s.length()-1;
    		while (lo < hi) {
        		if (s.charAt(lo) != s.charAt(hi))
            		return false;
        		lo++;
        		hi--;
    		}
    		return true;
    	}

	//=====================================================================
	//genRandomPalindrome()
	//=====================================================================
	public static String genRandomPalindrome(int N){
		String s = getRandomLetters(N);
		String result = s;
		
		BoundedStackInterface<Character> palinStack = new ArrayStack<Character>(N);
		
		for(int i = 0; i < N; i++){
			palinStack.push(s.charAt(i));
		}
		
		while(!palinStack.isEmpty()){
			result = result + palinStack.top();
			palinStack.pop();
		}
		
		
		return result;
		
		
	}
	
	//=====================================================================
	//genRandomNonPalindrome()
	//=====================================================================
	public static String genRandomNonPalindrome(int N){
		
		String s = getRandomLetters(N);
		
		return s + s;
	}
	
	
	//=====================================================================
	//getRandomLetters()
	//=====================================================================
	private static String getRandomLetters(int N){
		
		final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
		
		String result = "";
		int randomNum; 
		
		for (int i = 0; i< N; i++){
			
			randomNum = (int)(Math.random() * 26);
			
			result = result + LOWERCASE.charAt(randomNum);
			
		}
		
		return result;
	}




	///=====================================================================
	//main mthod()
	//======================================================================
    	public static void main(String[] args) {
	
		// long variables to keep track of time for each test
		long startTime, elapsedTime;	
		
		//Original testing from original source code-written by Gottleib
		System.out.println("FROM ORIGINAL PROGRAM\n");
        	System.out.println ("Testing fancy palindrome");
        	System.out.println ("sss"  + " is " + isPalindromeFancy("sss"));
        	System.out.println ("stts" + " is " + isPalindromeFancy("stts"));
        	System.out.println ("sxs"  + " is " + isPalindromeFancy("sxs"));
        	System.out.println ("xxs"  + " is " + isPalindromeFancy("xxs"));
        	System.out.println (""     + " is " + isPalindromeFancy(""));
        	System.out.println ();
        	System.out.println ("Testing plain palindrome");
        	System.out.println ("sss"  + " is " + isPalindromePlain("sss"));
        	System.out.println ("stts" + " is " + isPalindromePlain("stts"));
        	System.out.println ("sxs"  + " is " + isPalindromePlain("sxs"));
        	System.out.println ("xxs"  + " is " + isPalindromePlain("xxs"));
        	System.out.println (""     + " is " + isPalindromePlain(""));
		System.out.println("_________________________________________\n");
		
		
		//Remainder of codei n this method was added on
		String palindrome = genRandomPalindrome(N);
		String nonPalindrome = genRandomNonPalindrome(N);
		
		//The following were test conducted on the nonPalindrome variable
		System.out.println("TESTS FOR NONPALINDROME");
		System.out.println("The nonPalinDrome is the following:\n" +nonPalindrome);
		System.out.println ("\nTesting fancy palindrome");
		System.out.println(isPalindromeFancy(nonPalindrome));
		System.out.println ("Testing plain palindrome");
		System.out.println(isPalindromePlain(nonPalindrome));
		
		//The following were test conductedo on the nonPalindrome variable
		System.out.println("_________________________________________\n");
		System.out.println("TESTS FOR PALINDROME");
		System.out.println("The palinDrome is the following:\n" + palindrome);

		//testing isPalindromeFancy() method
		System.out.println ("\nTesting fancy palindrome");
		
		startTime = (new Date()).getTime();	// Initialized to now
		for(int i = 0; i<M; i++){
			
			System.out.println("palindrome is " + isPalindromeFancy(palindrome));
		}
		
		elapsedTime = (new Date()).getTime() - startTime;
		System.out.println("Using the fancy palindrome method, it took " + elapsedTime + " millseconds");
		System.out.println ("to enqueue and dequeue "+ 2*N +" elements " + M + " times.");

		
		//testing isPalindromePlain() method
		System.out.println ("\nTesting plain palindrome");

		startTime = (new Date()).getTime();	// Initialized to now
		for(int i = 0; i<M; i++){
			
			System.out.println("palindrome is " +isPalindromePlain(palindrome));
		}
		
		elapsedTime = (new Date()).getTime() - startTime;
		System.out.println("Using the plain palindrome method, it took " + elapsedTime + " millseconds");
		System.out.println ("to enqueue and dequeue "+ 2*N +" elements " + M + " times.");

		

		





		
		
		

    	}
                            
}