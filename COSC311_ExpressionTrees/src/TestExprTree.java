//--------------------------------------------------------------------
//
//  Laboratory 11                                   TestExprTree.java
//
//  Test program for the operations in the Expression Tree ADT
//
//--------------------------------------------------------------------

import java.io.*;
import java.util.Scanner;

class TestExprTree
{
    public static void main(String args[]) throws IOException 
    {
        ExprTree testExpression = new ExprTree( );    // Test expression
        ExprTree assignExpression = new ExprTree( );  // Test copying ExprTree
        Scanner input = new Scanner(System.in);
        String exp ="";
        System.out.println( );
        while(exp.length()<3){
        System.out.print("Enter an expression in prefix form : ");
        exp = input.nextLine();
        }
        
        
        testExpression.build(exp);
        testExpression.showStructure();
        testExpression.expression();
        System.out.println(" = " + testExpression.evaluate());

     // Test the copy constructor.   (In-lab Exercise 1)
     System.out.println("Copy using a Copy Constructor");
     assignExpression = new ExprTree(testExpression);
     dummy(assignExpression);
     System.out.println("Original ExprTree is:");
     testExpression.showStructure();
                 
     System.out.println("\nversus Cloning");
     assignExpression = (ExprTree)testExpression.clone();
     dummy(assignExpression);
     System.out.println("Original ExprTree is:");
     testExpression.showStructure();

     // Test the commute operation.  (In-lab Exercise 2)
     testExpression.commute();
     System.out.println( );
     System.out.println("Fully commuted tree: ");
     testExpression.showStructure();
     testExpression.expression();
     System.out.println(" = " + testExpression.evaluate());
        
        System.out.println( );
        System.out.println("Clear the tree");
        testExpression.clear( );
        testExpression.showStructure( );
        
        
        
        
    } // main

    static void dummy ( ExprTree copyTree )
    // Dummy routine that is passed an expression tree which (preferably) 
    // should not change the original expression tree.
    // Outputs copyTree and clears it.

    {
        System.out.println( );
        System.out.println("Copy of tree:  ");
        copyTree.showStructure( );
        copyTree.clear( );
        System.out.println("Copy cleared:   ");
        copyTree.showStructure( );
        System.out.println( );
    }

} // class TestExprTree