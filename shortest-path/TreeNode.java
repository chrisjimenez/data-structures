///////////////////////////////////////////////////////////////////////////////
// Author: Dale edited by Chris Jimenez
// Email: caj303@nyu.edu
// Lecturer's Name: Evan Korth
// Semester: CS102 Fall 2012
//
// Class Name: TreeNode
// Edited for Assignment #6
// ****************************************************************************
// Purpose: class is for a tree node of a binary tree. Added distance from root
// field
//
///////////////////////////////////////////////////////////////////////////////



public class TreeNode<E> implements Comparable<TreeNode<E>> {
	private double distance;
	public E element;
    	TreeNode<E> left;
    	TreeNode<E> right;
    
    	//constructor
    	public TreeNode(E e) {
        	element = e;
        	distance = 0;
    	}

	public TreeNode( E e, double d){
		element = e;
		distance = d;
	}
	
    	//compareTo method
	public int compareTo(TreeNode<E> tNode){
		if(tNode.getDistance() > distance)
            	{return -1;}
		else if(tNode.getDistance() < distance)
			{return 1;}
		else
			{return 0;}
		
	}
	
	public String toString(){
        String s = Double.toString(distance) + "   " + element;
        return s;
	}
	
	public E getInfo(){
		return element;
	}
	
	public TreeNode<E> getLeft(){
		return left;
	}
	public void setLeft(TreeNode<E> l){
		left = l;
	}
	
	public TreeNode<E> getRight(){
		return right;
	}
	
	public void setRight(TreeNode<E> r){
		right = r;
	}
	
	//encapsulation of distance variable
	public void setDistance(double d){
		distance = d;
	}
	
	public double getDistance(){
		return distance;
	}
}
