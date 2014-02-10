/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data_structures_test;

import data_structures.AVL_Tree;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Henri Korpela
 */
public class AVL_Tree_Test {
    private AVL_Tree<Integer,Integer> tree;
    
    public AVL_Tree_Test() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.tree = new AVL_Tree(new Integer_Comparator());
        
        this.tree.add(3,3);
        this.tree.add(2,2);
        this.tree.add(8,8);
        this.tree.add(1,1);
        this.tree.add(10,10);
        this.tree.add(5,5);
        this.tree.add(7,7);
        this.tree.add(6,6);
        this.tree.add(11,11);
        this.tree.add(12,12);
        this.tree.add(14,14);
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void add_test() 
     {
         AVL_Tree<Integer,Integer> addition_tree = new AVL_Tree(new Integer_Comparator());
         // Additions
         addition_tree.add(8,8);
         addition_tree.add(3,3);
         addition_tree.add(7,7);
         addition_tree.add(1,1);
         addition_tree.add(2,2);
         addition_tree.add(5,5);
         
         assertEquals((int)7,(int)addition_tree.get(7));
         assertEquals((int)1,(int)addition_tree.get(1));
         assertEquals((int)5,(int)addition_tree.get(5));
         assertEquals((int)3,(int)addition_tree.get(3));
         assertEquals((int)2,(int)addition_tree.get(2));
         
         if(addition_tree.get(10) != null || addition_tree.get(0) != null || addition_tree.get(6) != null)
         {
             assertEquals(true,false);
         }
         
         // Updates
         addition_tree.add(8, 80);
         addition_tree.add(5, 50);
         addition_tree.add(1, 10);
         addition_tree.add(2, 20);
         
         assertEquals((int)10,(int)addition_tree.get(1));
         assertEquals((int)20,(int)addition_tree.get(2));
         assertEquals((int)50,(int)addition_tree.get(5));
         assertEquals((int)80,(int)addition_tree.get(8));
     }
     
     @Test
     public void delete_test()
     {
         assertEquals((int)7,(int)this.tree.get(7));
         assertEquals((int)1,(int)this.tree.get(1));
         assertEquals((int)14,(int)this.tree.get(14));
         
         this.tree.remove(7);
         this.tree.remove(1);
         this.tree.remove(14);
         
         if(this.tree.get(7) != null || this.tree.get(1) != null || this.tree.get(14) != null)
         {
             assertEquals(false,true);
         }
         
         assertEquals((int)8,(int)this.tree.get(8));
         assertEquals((int)2,(int)this.tree.get(2));
         assertEquals((int)3,(int)this.tree.get(3));
         assertEquals((int)10,(int)this.tree.get(10));
         assertEquals((int)12,(int)this.tree.get(12));
         assertEquals((int)6,(int)this.tree.get(6));
         assertEquals((int)5,(int)this.tree.get(5));
     }
     
     @Test
     public void min_and_max_test()
     {
         
     }
}
