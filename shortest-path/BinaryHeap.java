/////////////////////////////////////////////////////////////////////////////
// Author: Chris Jimenez
// Email: caj303@nyu.edu
// Lecturer's Name: Evan Korth
// Semester: CS102 Fall 2012
// Class Name: BinaryHeap.java
// **************************************************************************
// Purpose: BinaryHeap class retrieved form course website. In addition to 
// the given methods, the methods getSize() and printHeap() were added.
//
/////////////////////////////////////////////////////////////////////////////

public class BinaryHeap<AnyType extends Comparable<? super AnyType>>{
	private static final int DEFAULT_CAPACITY = 10;
    private int currentSize;      // Number of elements in heap
    private AnyType [ ] array; // The heap array
	
	
	/*[constructor method]***********************************************
     * Construct the binary heap.
     ********************************************************************/
	public BinaryHeap( ){
		this( DEFAULT_CAPACITY );
    }//end of constructor method
    
    
	/*[constructor method]***********************************************
     * Construct the binary heap.
     * @param capacity the capacity of the binary heap.
     ********************************************************************/
	public BinaryHeap( int capacity ){
		currentSize = 0;
        array = (AnyType[]) new Comparable[ capacity + 1 ];
	}//end of constructor method
    
    
	/*[constructor method]**********************************************
     * Construct the binary heap given an array of items.
     ********************************************************************/
	public BinaryHeap( AnyType [ ] items ){
		currentSize = items.length;
        array = (AnyType[]) new Comparable[ (currentSize + 2) * 11 / 10 ];
        
        int i = 1;
        for( AnyType item : items )
			array[ i++ ] = item;
        buildHeap();
	}// end of constructor method
	
	
	/*[getSize() method]************************************************
     * returns the current size of the binary heap or array.
     *******************************************************************/
	public int getSize(){
		return currentSize;
	}// end of getSize() method
	
	/*[printHeap() method]***********************************************
     * prints the elements in the array
     ********************************************************************/
	public void printHeap(){
		for (AnyType element: array){
			if( element != null)
				System.out.print(element);
		}
		
	}
    
	/*[insert() method]**************************************************
     * Insert into the priority queue, maintaining heap order.
     * Duplicates are allowed.
     * @param x the item to insert.
     ********************************************************************/
	public void insert( AnyType x ){
		if( currentSize == array.length - 1 )
			enlargeArray( array.length * 2 + 1 );
        
		// Percolate up
        int hole = ++currentSize;
        for( ; hole > 1 && x.compareTo(array[ hole / 2 ]) < 0; hole /= 2 )
			array[ hole ] = array[ hole / 2 ];
        array[ hole ] = x;
    }
    
	/*[enlargeArray() method]*******************************************
     * Enlarges the size of the array.
     ********************************************************************/
    private void enlargeArray( int newSize ){
		AnyType [] old = array;
		array = (AnyType []) new Comparable[ newSize ];
        for( int i = 0; i < old.length; i++ )
            array[ i ] = old[ i ];
    }
    
	/*[findMin() method]*************************************************
     * Find the smallest item in the priority queue.
     * @return the smallest item, or throw an UnderflowException if empty.
     *********************************************************************/
	public AnyType findMin(){
		if( isEmpty() )
			throw new UnderflowException( );
        
        return array[ 1 ];
    }
    
	/*[deleteMin() method]***********************************************
     * Remove the smallest item from the priority queue.
     * @return the smallest item, or throw an UnderflowException if empty.
     *********************************************************************/
	public AnyType deleteMin( ){
		if( isEmpty( ) )
			throw new UnderflowException( );
        
        AnyType minItem = findMin( );
        array[ 1 ] = array[ currentSize-- ];
        percolateDown( 1 );
        
        return minItem;
    }
    
	/*[buildHeap() method]************************************************
     * Establish heap order property from an arbitrary
     * arrangement of items. Runs in linear time.
     *********************************************************************/
    private void buildHeap( ){
		for( int i = currentSize / 2; i > 0; i-- )
			percolateDown( i );
    }
    
	/*[isEmpty() method]**************************************************
     * Test if the priority queue is logically empty.
     * @return true if empty, false otherwise.
     *********************************************************************/
    public boolean isEmpty( ){
        return currentSize == 0;
    }
    
	/*[makeEmpty() method]*******************************************
     * Make the priority queue logically empty.
     *********************************************************************/
    public void makeEmpty( ){
        currentSize = 0;
    }
    
	/*[percolateDown() method]*******************************************
     * Internal method to percolate down in the heap.
     * @param hole the index at which the percolate begins.
     **********************************************************************/
    private void percolateDown( int hole ){
        int child;
        AnyType tmp = array[ hole ];
        
        for( ; hole * 2 <= currentSize; hole = child )
        {
            child = hole * 2;
            if( child != currentSize &&
               array[ child + 1 ].compareTo( array[ child ] ) < 0 )
                child++;
            if( array[ child ].compareTo( tmp ) < 0 )
                array[ hole ] = array[ child ];
            else
                break;
        }
        array[ hole ] = tmp;
    }
    
}

