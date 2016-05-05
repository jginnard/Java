import java.io.IOException;
import java.util.Iterator;

//--------------------------------------------------------------------
//
//  Laboratory 11                                       ExprTree.jshl
//
//  (Shell) Class definitions for the linked implementation of the
//  Expression Tree ADT -- including the recursive partners of the
//  public methods
//
//  The student is to complete all missing or incomplete method 
//     implementations for this class
//
//--------------------------------------------------------------------

class LogiTree implements Cloneable{
    // Data member
    private TreeNode root;       // Reference to the root node
    

    // Constructor
    public LogiTree (){          
    	this.root = null;
    }
    
    //In-lab 1 
    //Remove the surrounding comment markers when ready to implement
    
    public LogiTree ( LogiTree valueTree ){  // Copy constructor. 
    	this.root = valueTree.root;
    }
    public Object clone( ){		// clone the ExprTree
    	                   
    	try
        {
            return super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            // This should never happen
            throw new InternalError
                ("\nThis class does not implement Cloneable");
        }
    }
    
    
    // Expression tree manipulation operations
    String exp = "";
    int index = 0;
    public void build (String exp) throws IOException { // Build tree from prefix expression
    	this.exp = exp;
    	index = 0;
    	root = buildSub();
    }
    public void expression () {               // Output expression in infix form
    	inOrder(root);
    	System.out.println();
    }
    public boolean evaluate () {              // Evaluate expression   
        return (eval(root)==1)? true : false;
    	//return calculate('+', 2, 5);
	}
    public void clear ( ){                    // Clear tree
    	this.root = null;
    }

    public void showStructure ( )
    // Outputs an expression tree. The tree is output rotated counter-
    // clockwise 90 degrees from its conventional orientation using a
    // "reverse" inorder traversal. This operation is intended for testing
    // and debugging purposes only.
    {
        if ( root == null )
            System.out.println("Empty tree");
        else
        {
            System.out.println( );
            showSub(root, 1);
            System.out.println( );
        }
    }

    // In-lab 2
    // Remove the surrounding comment markers when ready to implement
    public void commute ( ){          // Commute all subexpr.
    	swap(root);
    }
    
    
    // Recursive partners of the public member methods ....
    // -- insert/complete the definition of these methods here.

    private void showSub ( TreeNode p, int level )
    // Recursive partner of the showStructure() method. Outputs the
    // subtree whose root node is pointed to by p. Parameter level is the
    // level of this node within the expression tree.
    {
        int j;                                      // Loop counter
        TreeNode right,
                 left;                                  

        if ( p != null ){
            // For efficiency, calculate right and left only once
            right = p.getRight( );
            left = p.getLeft( );
            
            showSub(right, level+1);                // Output right subtree
            for ( j = 0 ; j < level ; j++ )         // Tab over to level
                System.out.print("\t");
            System.out.print(" " + p.getElement( ));// Output element
            if ( ( left != null ) &&                // Output "connector"
                 ( right != null ) )
                System.out.print("<");
            else if ( right != null )
                System.out.print("/");
            else if ( left != null )
                System.out.print("\\");
            System.out.println( );
            showSub(left, level+1);                 // Output left subtree
        }
    }
    
    private TreeNode buildSub ()  throws IOException
    // Recursive partner of the build() method. Builds a subtree and
    // sets p to point to its root.
    {                   
    
    	char first = exp.charAt(index);
    	index++;
    	
    	ExprTreeNode p = new ExprTreeNode(first, null, null);
    	
    	if(isDigit(first)){
    		return p;
    	}
    	else if(first=='-'){
    		p.setLeft(null);
    		p.setRight(buildSub());
    		return p;
    	}
    	else{
    		p.setLeft(buildSub());
    		p.setRight(buildSub());
    		return p;
    	}
    }
    
    private void inOrder(TreeNode node){
    	if(node == null){
    		return;
    	}
    	if(isOperator(node.getElement()))
    		System.out.print('(');
    	inOrder(node.getLeft());
    	
    	System.out.print(node.getElement());
    	
    	inOrder(node.getRight());  
    	if(isOperator(node.getElement()))
    		System.out.print(')');
    }

    private int calculate(char operator, int n1, int n2){
		int result = 0;
		switch(operator){
		case '+': result = (n1 == 1 || n2 == 1)? 1 : 0;
			break;
		case '-': result = (n2 == 0)? 1 : 0;
			break;
		case '*': result = (n1 == 1 && n2 == 1)? 1 : 0;
			break;
		}
    	return result;
    }
    
    private int eval(TreeNode node){
    	if (node != null){
            if (isDigit(node.getElement()))  // n is a node with a number
                return Character.getNumericValue(node.getElement());
            
            
            else if (node.getLeft()==null){
            	int right = eval(node.getRight());
                return calculate(node.getElement(), 0, right);
            }
            
            
            else
            {
                int left = eval(node.getLeft());
                int right = eval(node.getRight());
                return calculate(node.getElement(), left, right);
            } //end else
        } //end if
        return 0;
    }
    
    private void swap(TreeNode node){
    	if(node.getLeft() != null && node.getRight() != null && commutable(node)){
    	TreeNode temp = new ExprTreeNode(node.getElement(),node.getLeft(), node.getRight());
    	node.setRight(temp.getLeft());
    	node.setLeft(temp.getRight());
    	swap(node.getLeft());
    	swap(node.getRight());
    	}
    }
    
    private boolean commutable(TreeNode node){
    	if(node.getElement() == '+' || node.getElement() == '*')
    		return true;
    	else
    		return false;
    }
    // -- Insert the definition of the rest of 
    // -- the recursive partners of the public methods below.
    // -- These recursive (helper) methods will be private.
    
    
    
    private boolean isOperator(char a){
		if(a=='+'||a=='-'||a=='*'||a=='/')
			return true;
		else
			return false;
    }
    
    private boolean isDigit(char n){
    	int a = Character.getNumericValue(n);
		if(a >=0 && a<=9)
			return true;
		else
			return false;
    }
        
} // class ExprTree