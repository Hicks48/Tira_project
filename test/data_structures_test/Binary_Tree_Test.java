/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data_structures_test;

import data_structures.Binary_Tree;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author henrikorpela
 */
public class Binary_Tree_Test {
    private Binary_Tree<Integer,String> tree;
    
    public Binary_Tree_Test() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        tree = new Binary_Tree();
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void basics() 
    {
        tree.add(1, "One");
        tree.add(3, "Tree");
        tree.add(5, "Five");
        tree.add(8, "Eigth");
        tree.add(6, "Six");
        tree.add(2, "Two");
        tree.add(10, "Ten");
        
        assertEquals(tree.get(8),"Eigth");
        assertEquals(tree.get(1),"One");
        assertEquals(tree.get(5),"Five");
        assertEquals(tree.get(10),"Ten");
        
        assertEquals(tree.max(),"Ten");
        assertEquals(tree.min(),"One");
        
        tree.delete(2);
        tree.delete(5);
        tree.delete(10);
        
        assertEquals(tree.get(2),null);
        assertEquals(tree.get(5),"Five");
        assertEquals(tree.get(6),"Six");
        assertEquals(tree.get(3),"Tree");
        assertEquals(tree.get(10),null);
        
        
        assertEquals(tree.get(2),null);
        assertEquals(tree.get(6),"Six");
        assertEquals(tree.get(3),"Tree");
    }
}
