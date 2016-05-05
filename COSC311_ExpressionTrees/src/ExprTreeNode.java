//--------------------------------------------------------------------
//
//  Laboratory 11                                  ExprTreeNode.jshl
//
//  (Shell) Class definitions for the ExprTreeNode used in the 
//     linked implementation of the Expression Tree ADT 
//
//  Note:  the methods that implement an interface MUST BE 
//         declared public
//
//  The student is to complete all missing or incomplete method 
//     implementations for this class
//
//--------------------------------------------------------------------

class ExprTreeNode implements TreeNode
// Facilitator class for the ExprTree and LogiTree class
{
    // Data members
    private char element;      // Expression tree element
    private TreeNode left,     // Reference to the left child
                     right;    // Reference to the right child
 

    // Constructor
    public ExprTreeNode ( char elem, TreeNode leftPtr, TreeNode rightPtr ){
    	this.element = elem;
    	this.left = leftPtr;
    	this.right = rightPtr;
    }

    // Class Methods used by client class
    
    /**
	 * Sets the element of the node
	 * @param element The data value of the node
	 */
	public void setElement(char element){
		this.element = element;
	};

	/**
	 * @return The data value of the node
	 */
	public char getElement(){
		return element;
	};

	/**
	 * @return a reference to the root node of the left tree.  May be null.
	 */
	public TreeNode getLeft(){
		if(left != null)
			return left;
		else
			return null;
	};

	/**
	 * @return a reference to the root node of the right tree.  May be null.
	 */
	public TreeNode getRight(){
		if(right != null)
			return right;
		else 
			return null;
	};

    /**
     * Sets the left subtree to be that rooted by the given node
     * @param node  The new root of the left subtree 
     * @return  The root of the left subtree
     */
    public TreeNode setLeft(TreeNode node){
    	this.left = node;
    	return left;
    };

    /**
     * Sets the right subtree to be that rooted by the given node
     * @param node  The new root of the right subtree
     * @return The root of the right subtree
     */
    public TreeNode setRight(TreeNode node){
    	this.right = node;
    	return right;
    };

} // class ExprTreeNode