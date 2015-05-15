//----------------------------------------------------------------------------
// BoundedStackInterface.java        by Dale/Joyce/Weems             Chapter 3
//
// Interface for a class that implements a stack of <T> with a bound
// on the size of the stack. A stack is a last-in, first-out structure.
//----------------------------------------------------------------------------

public interface BoundedStackInterface<T> extends StackInterface<T> {
  void push(T element) throws StackOverflowException;
  // Throws StackOverflowException if this stack is full,
  // otherwise places element at the top of this stack.

  boolean isFull();
  // Returns true if this stack is full, otherwise returns false.
}
