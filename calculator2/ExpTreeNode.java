abstract public class ExpTreeNode {
//Represents an expression tree node which could contain an operand or operator
	
    
    // returns the value of the node
    abstract double value();
    
    //The following methods outputs the postorder, preorder, and infix order traversal
    //of the expression tree respectively
    abstract void postOrderTraverse();
    
	abstract void preOrderTraverse();
    
	abstract void infixOrderTraverse();    
    

    
    
	//Driver method to test the ExpTreeNode class
	public static void main(String[] args){
        ExpTreeNode testTree =
            new OperatorNode("+",
                new NumberNode(9.0),
                    new OperatorNode("/",
                        new OperatorNode("*",
                            new NumberNode(6.0),
                                new NumberNode(2.0)),
                    new OperatorNode("^",
                        new NumberNode(8.0),
                            new NumberNode(3.0))))
		;

		testTree.postOrderTraverse();
		System.out.println();
		testTree.preOrderTraverse();
		System.out.println();
		testTree.infixOrderTraverse();


	}
}
