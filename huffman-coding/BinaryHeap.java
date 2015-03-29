/******************************************************************************
* By: Chris Jimenez
*
* BinaryHeap.java
*
* Class represents a Binary Heap. 
******************************************************************************/


public class BinaryHeap<AnyType extends Comparable<? super AnyType>>{
    private static final int DEFAULT_CAPACITY = 10;
    
    // number of elements in the heap
    private int currentSize;      

    // the heap array
    private AnyType [ ] array; 
    
    
    /**
    * CONSTRUCTOR
    */
    public BinaryHeap( ){
      this(DEFAULT_CAPACITY);
    }


    /**
    * CONSTRUCTOR
    * Creates binary heap given the capacity
    */
    public BinaryHeap( int capacity ){
      currentSize = 0;
      array = (AnyType[]) new Comparable[ capacity + 1 ];
    }
        

    /**
    * CONSTRUCTOR
    * Creates the binary heap given an array of items
    */
    public BinaryHeap( AnyType [ ] items ){
      currentSize = items.length;
      array = (AnyType[]) new Comparable[ (currentSize + 2) * 11 / 10 ];
  
      int i = 1;
      for( AnyType item : items ){
          array[ i++ ] = item;
      }
      buildHeap();
    }
    
    
    /**
    * Returns the current size of the binary heap 
    */
    public int getSize(){
      return currentSize;
    }
    

    /**
    * Prints the elements in the array
    */
    public void printHeap(){
      for (AnyType element: array){
        if( element != null ){
          System.out.println(element);
        }
      }    
    }


    /**
    * Insert item x into the priority queue, maintaining heap order.
    * Duplicates are allowed.
    */
    public void insert( AnyType x ){
      if( currentSize == array.length - 1 ){
          enlargeArray( array.length * 2 + 1 );
      }

      // Percolate up
      for(int hole = ++currentSize; hole > 1 && x.compareTo(array[ hole / 2 ]) < 0; hole /= 2 ){
          array[ hole ] = array[ hole / 2 ];
          array[ hole ] = x;
      }
    }

    /**
    * Enlarges the size of the array
    */
    private void enlargeArray( int newSize ){
      AnyType [] old = array;
      array = (AnyType []) new Comparable[ newSize ];

      for( int i = 0; i < old.length; i++ ){
         array[ i ] = old[ i ];    
      }
    }

    /**
    * Returns the smallest item in the priority queue. Throws
    * UnderflowException if empty.
    */
    public AnyType findMin(){
      if(isEmpty()){
        throw new UnderflowException();
      } else { 
        return array[ 1 ];
      }
    }


    /**
    *  Removes the smallest item from the heap and returns it. Throws
    *  UnderflowException if empty.
    */
    public AnyType deleteMin(){
      if( isEmpty() ){
        throw new UnderflowException( );
      }

      AnyType minItem = findMin();
      array[1] = array[ currentSize-- ];
      percolateDown(1);

      return minItem;
    }


    /**
    * Builds heap order property from an arbitrary
    * arrangement of items. Runs in O(n) time.
    */
    private void buildHeap(){
      for(int i = currentSize/2; i > 0; i-- ){
        percolateDown(i);
      }
    }

    /**
    * Returns if the heap is empty.
    */
    public boolean isEmpty(){
      return currentSize == 0;
    }

    /**
    *  Make heap empty.
    */
    public void makeEmpty(){
      currentSize = 0;
    }

    /**
    * Percolate down the heap.
    * hole - the index at which the percolate begins
    */
    private void percolateDown( int hole ){
    int child;
    AnyType tmp = array[ hole ];

    for( ; hole * 2 <= currentSize; hole = child ){
      child = hole * 2;

      if( child != currentSize && array[ child + 1 ].compareTo( array[ child ] ) < 0 ){
        child++;
      }
      
      if( array[ child ].compareTo( tmp ) < 0 ){
        array[ hole ] = array[ child ];
      } else {
        break;
      }
    }
    array[ hole ] = tmp;
  }
}