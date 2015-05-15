/////////////////////////////////////////////////////////////////////////////
// Author: Dale/Joyce/Weems edited by Chris Jimenez
// Email: caj303@nyu.edu
// Lecturer's Name: Evan Korth
// Semester: CS102 Fall 2012
// Class Name: ArrayStack
// **************************************************************************
// Purpose: Implements BoundedStackInterface using an array to hold the 
// stack elements. Two constructors are provided: one that creates an array 
// of a default size and one that allows the calling program to 
// specify the size.
//
/////////////////////////////////////////////////////////////////////////////


public class ArrayStack<T> implements BoundedStackInterface<T> {
	protected final int DEFCAP = 100; // default capacity
  	protected T[] stack;              // holds stack elements
  	protected int topIndex = -1;      // index of top element in stack


	/*[constructor method]*************************************************
     * Construct the array stack.
     ***********************************************************************/
  	public ArrayStack() {
		stack = (T[]) new Object[DEFCAP];
  	}

	/*[constructor method]**************************************************
     * Construct the array stack.
     ************************************************************************/
  	public ArrayStack(int maxSize) {
    		stack = (T[]) new Object[maxSize];
  	}	

	/*[push() method]********************************************************
     * Throws StackOverflowException if this stack is full, otherwise 
	* places element at the top of this stack.
     ************************************************************************/
  	public void push(T element){      
    		if (!isFull()){
      		topIndex++;
      		stack[topIndex] = element;
    		}else
      		throw new StackOverflowException("Push attempted on a full stack.");
  	}


	/*[pop() method]********************************************************
     * Throws StackUnderflowException if this stack is empty,
	* otherwise removes top element from this stack.
     ************************************************************************/
  	public void pop(){                  
    		if (!isEmpty()){
      		stack[topIndex] = null;
      		topIndex--;
    		}else
      		throw new StackUnderflowException("Pop attempted on an empty stack.");
  	}


	/*[top() method]********************************************************
     * Throws StackUnderflowException if this stack is empty,
	* otherwise returns top element from this stack.
     ************************************************************************/
  	public T top(){                 
    		T topOfStack = null;

    		if (!isEmpty())
      		topOfStack = stack[topIndex];
    		else
      		throw new StackUnderflowException("Top attempted on an empty stack.");

    		return topOfStack;
  	}

	
	/*[isEmpty() method]****************************************************
     * Returns true if this stack is empty, otherwise returns false.
     ************************************************************************/
  	public boolean isEmpty(){ 
    		if (topIndex == -1) 
      		return true;
    		else
      		return false;
  	}


	/*[isFull() method]*****************************************************
     * Returns true if this stack is full, otherwise returns false.
     ************************************************************************/
  	public boolean isFull(){              
    		if (topIndex == (stack.length - 1)) 
      		return true;
    		else
      		return false;
  	}
}