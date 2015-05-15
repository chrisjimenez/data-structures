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
// Title: ProducerCOnsumer.java
// **************************************************************************
// Purpose:The purpose of this program is to determine the number of succesful
// consumers while increasing the head probability and determien the ratio 
// using said values. ranges 0 to 1 indicate the progress.
/////////////////////////////////////////////////////////////////////////////

import ch05.queues.*;
import java.lang.*;
public class ProducerConsumer {
    	private static final int N = 1000000;
    	private static final int QUEUESIZE = 100;
    	private static double headProbability = 0;

	private static double numOfSuccessfulCons = 0;
	private static double ratio;

    	private static BoundedQueueInterface<Integer> queue = new ArrayBndQueue<Integer>(QUEUESIZE);
        
    	public static void main (String[] args) {
	
	//Table which will list all the information 
	System.out.println();
	System.out.printf("%-10s %-15s %-20s %-25s", "N","QUEUESIZE","headProbability", "Ratio");
	System.out.println("\n___________________________________________________________");
		for(int i = 0; i < 11; i++){		
			for(int j=0; j<N; j++){
            		if (coinFlipIsHeads())
                		produce();
            		else
                		consume();
    			}

		ratio = (2 * numOfSuccessfulCons)/N;
		
		System.out.printf("%-13d %-15d %-16f %f", N, QUEUESIZE, headProbability, ratio);
		System.out.println();

		headProbability = headProbability + 0.1;
		numOfSuccessfulCons = 0;

		}
	}
	//produce() method
    	public static void produce() {
        	if (!queue.isFull()) 
            	queue.enqueue( (int)(10*Math.random()) );

    	}

	//consume() method
    	public static void consume() {
        	if (!queue.isEmpty()){
			queue.dequeue();
			numOfSuccessfulCons++;
			
        	}
    	}
	//coinFLipIsHEads() method
    	public static boolean coinFlipIsHeads() {
        	return Math.random() < headProbability;
    	}
}