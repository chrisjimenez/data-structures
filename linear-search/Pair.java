/***************************************************************************
* By: Chris Jimenez
*
* Pair.java
*
* Pair class takes in two doubles as field variables and has an 
* instance method called linearCombination that takes in double paramters 
* and applies them to the local x and y values.
****************************************************************************/
public class Pair {
	private double x;
	private double y;

	/**
	* CONSTRUCTOR
	* assigns given x and y values to the field variables
	*/
	public Pair(double x, double y){
		this.x = x;
		this.y = y;
	}
 
	
	/**
	* instance method that takes in double variables 
	*/
	public double linearCombination(double a, double b){
		return (a*x + b*y);
	}	
}