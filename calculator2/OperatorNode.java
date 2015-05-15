import java.lang.*;

public class OperatorNode extends ExpTreeNode{

    String op;        // The operator.
    ExpTreeNode left;   // The left operand.
    ExpTreeNode right;  // The right operand.
 
    OperatorNode( String op, ExpTreeNode left, ExpTreeNode right ) {
          // Constructor.  Create a node to hold the given data.
       this.op = op;
       this.left = left;
       this.right = right;
    }
 
    double value() {
          // To get the value, compute the value of the left and
          // right operands, and combine them with the operator.
        double leftVal = left.value();
        double rightVal = right.value();



        if (op.equals("/"))
          return leftVal / rightVal;
        else
        if(op.equals("*"))
          return leftVal * rightVal;
        else
        if(op.equals("+"))
          return leftVal + rightVal;
        else
        if(op.equals("-"))
          return leftVal - rightVal;
        else
        if(op.equals("^"))
          return Math.pow(leftVal,rightVal);
        else
          throw new PostFixException("Illegal symbol: " + op);

         }


	void preOrderTraverse(){
		System.out.print(" " + op +" ");
		left.preOrderTraverse();
		right.preOrderTraverse();
	}
	
	
	 void infixOrderTraverse(){
		left.infixOrderTraverse();
		System.out.print(" " + op + " ");
		right.infixOrderTraverse();
		
	}
	
	void postOrderTraverse(){
		left.postOrderTraverse();
		right.postOrderTraverse();
		System.out.print(" "+ op + " ");
	}



 } 