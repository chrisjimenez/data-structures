/***************************************************************************
* By: Chris Jimenez
*
* Class represents a binary tree.
***************************************************************************/

import java.lang.*;


public class BinaryTree<E extends Comparable<E>> implements Cloneable {
  protected TreeNode<E> root;
  protected int size = 0;
    
  /**
  * CONSTRUCTOR
  */
  public BinaryTree(TreeNode node) {
    this.root = node;
  }

  /**
  * CONSTRUCTOR
  * Create a binary tree from an array of objects
  */
  public BinaryTree(E[] objects) {
    for (int i = 0; i < objects.length; i++){
      insert(objects[i]);
    }
  }
    
  /**
  * Returns true is the element is in the tree.
  */
  public boolean search(E e) {
    TreeNode<E> current = root; // Start from the root
    
    while (current != null) {
      if (e.compareTo(current.element) < 0) {
        current = current.left;
      } else if (e.compareTo(current.element) > 0) {
        current = current.right;
      
      // element matches current.element
      } else {
        // element is found
        return true; 
      }
    }
    return false;
  }
    
  /**
  * Insert element o into the binary tree. Return true is the element is 
  * inserted successfully
  */
  public boolean insert(E e) {
    if (root == null){
      root = createNewNode(e); 
    } else {
      // Locate the parent node
      TreeNode<E> parent = null;
      TreeNode<E> current = root;

      while (current != null)
        if (e.compareTo(current.element) < 0) {
          parent = current;
          current = current.left;
        }else if (e.compareTo(current.element) > 0) {
          parent = current;
          current = current.right;
        }else{
          return false; // Duplicate node not inserted
        }
    
        // Create the new node and attach it to the parent node
        if (e.compareTo(parent.element) < 0){
          parent.left = createNewNode(e);
        } else {
          parent.right = createNewNode(e);
        }
      }
    }
        
    size++;
    return true; 
  }
    
  /**
  * Creates a new node of element E
  */
  protected TreeNode<E> createNewNode(E e) {
    return new TreeNode<E>(e);
  }
    
  /**
  * looks for an element and returns the shortest path if there are
  * multiple
  */ 
  public double findClosest(TreeNode root, E target) {
    TreeNode tNode;
    BinaryHeap minHeap = new BinaryHeap();//make new min-heap 
    
    minHeap.insert(root);
          
    do {
      tNode = (TreeNode) minHeap.deleteMin();// get min element
            
      //if minHeap element is not null
      if((tNode != null)){ 
        
        //Place elements children into heap and update the distance to distance from root.
        if (tNode.getLeft() != null){
          double updatedDistance = tNode.getDistance() + tNode.getLeft().getDistance();
          tNode.getLeft().setDistance(updatedDistance);
          
          minHeap.insert(tNode.getLeft());
        }
        
        if (tNode.getRight() != null){
          double updatedDistance = tNode.getDistance() + tNode.getRight().getDistance();
          tNode.getRight().setDistance(updatedDistance);
                    
          minHeap.insert(tNode.getRight());
        }

      }
    // while minHeap is not empty & tNode element does not equal the target element being searched for
    } while(!minHeap.isEmpty()  && !(tNode.element.equals(target)));
        
    //if the target was not found, return the arbitrary number -1, else return shortest path
    if(!tNode.element.equals(target)){
      return -1;
    } else {
      double shortestPath = tNode.getDistance();
      return shortestPath;
    }
  }
  
  /**
  * Inorder traversal from the root
  */
  public void inorder() {
    inorder(root);
  }
    
  protected void inorder(TreeNode<E> root) {
    if (root == null) return;
    inorder(root.left);
    System.out.print(root.element + " ");
    inorder(root.right);
  }

  /**
  * Postorder traversal from the root.
  */
  public void postorder() {
    postorder(root);
  }
    
  protected void postorder(TreeNode<E> root) {
    if (root == null) return;
    postorder(root.left);
    postorder(root.right);
    System.out.print(root.element + " ");
  }
    
  /**
  * Preorder traversal from the root.
  */
  public void preorder() {
    preorder(root);
  }
    
  
  /**
  * Returns size of the tree.
  */
  public int getSize() {
      return size;
  }
    

  /**
  * Returns true if the element is in the tree
  */
  public TreeNode<E> getRoot() {
    return root;
  }
    
 
    /*path()**************************************************************
     * Returns a path from the root leading to the specified element
     ************************************************************************/
  public java.util.ArrayList<TreeNode<E>> path(E e) {
    java.util.ArrayList<TreeNode<E>> list = new java.util.ArrayList<TreeNode<E>>();
    TreeNode<E> current = root; // Start from the root
        
    while (current != null) {

      list.add(current); // Add the node to the list
      if (e.compareTo(current.element) < 0) {
        current = current.left;
      } else if (e.compareTo(current.element) > 0) {
        current = current.right;
      } else {
        break;
      }

    }

    return list; // Return an array of nodes
  }
    

  /**
  * Deletes an element from the binary tree.
  * Return true if the element is deleted successfully
  * Return false if the element is not in the tree
  */
  public boolean delete(E e) {
    // Locate the node to be deleted and also locate its parent node
    TreeNode<E> parent = null;
    TreeNode<E> current = root;

    while (current != null) {
      if (e.compareTo(current.element) < 0) {
        parent = current;
        current = current.left;
      } else if (e.compareTo(current.element) > 0) {
        parent = current;
        current = current.right;
      }else{
        break; // Element is in the tree pointed by current
      }
    }
        
    if (current == null){
        return false; // Element is not in the tree
    }
      
    // Case 1: current has no left children
    if (current.left == null) {
      // Connect the parent with the right child of the current node
      if (parent == null) {
          root = current.right;
      } else {
          if (e.compareTo(parent.element) < 0) {
            parent.left = current.right;
          } else {
            parent.right = current.right;
          }
        }
    } else {
      // Case 2: The current node has a left child
      // Locate the rightmost node in the left subtree of
      // the current node and also its parent
      TreeNode<E> parentOfRightMost = current;
      TreeNode<E> rightMost = current.left;
            
      while (rightMost.right != null) {
          parentOfRightMost = rightMost;
          rightMost = rightMost.right; // Keep going to the right
      }
          
      // Replace the element in current by the element in rightMost
      current.element = rightMost.element;
        
      // Eliminate rightmost node
      if (parentOfRightMost.right == rightMost){
              parentOfRightMost.right = rightMost.left;
      }else{
              // Special case: parentOfRightMost == current
              parentOfRightMost.left = rightMost.left;
      }
    }
      
    size--;
    return true; // Element inserted
  }
}