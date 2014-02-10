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
        tree = new Binary_Tree(new Integer_Comparator());
        tree.tree_add(3, "Tree");
        tree.tree_add(5, "Five");
        tree.tree_add(1, "One");
        tree.tree_add(8, "Eigth");
        tree.tree_add(6, "Six");
        tree.tree_add(2, "Two");
        tree.tree_add(10, "Ten");
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
        assertEquals(tree.get(5),null);
        assertEquals(tree.get(6),"Six");
        assertEquals(tree.get(3),"Tree");
        assertEquals(tree.get(10),null);
        
        assertEquals(tree.get(2),null);
        assertEquals(tree.get(6),"Six");
        assertEquals(tree.get(3),"Tree");
    }
    
    @Test
    public void preorder_tree_structure_test()
    {
        assertEquals(" Tree One Two Five Eigth Six Ten ",this.tree.prerder());
        
        this.secondary_tree();
        
        assertEquals(this.tree.prerder()," Five Two Three Seven Six Eigth ");
        
        this.tree.remove(8);
        
        assertEquals(this.tree.prerder()," Five Two Three Seven Six ");
        
        this.tree.add(8, "Eigth");
        
        assertEquals(this.tree.prerder()," Five Two Three Seven Six Eigth ");
        
        this.tree.remove(5);
        
        assertEquals(this.tree.prerder()," Six Two Three Seven Eigth ");
        
        this.tree.add(5, "Five");
        
        assertEquals(this.tree.prerder()," Six Two Three Five Seven Eigth ");
        
        this.tree.remove(7);
        
        assertEquals(this.tree.prerder()," Six Two Three Five Eigth ");
        
        this.tree.add(7, "Seven");
        this.tree.add(9, "Nine");
        
        assertEquals(this.tree.prerder()," Six Two Three Five Eigth Seven Nine ");
        
        this.tree.remove(8);
        
        assertEquals(this.tree.prerder()," Six Two Three Five Nine Seven ");
    }
    
    @Test
    public void max_min_test()
    {
        assertEquals(this.tree.min(),"One");
        assertEquals(this.tree.max(),"Ten");
        
        this.tree.remove(1);
        this.tree.remove(10);
        
        assertEquals(this.tree.min(),"Two");
        assertEquals(this.tree.max(),"Eigth");
        
        this.secondary_tree();
        
        assertEquals(this.tree.min(),"Two");
        assertEquals(this.tree.max(),"Eigth");
    }
    
    @Test
    public void remove_root__and_empty_test()
    {
        this.tree.remove(1);
        assertEquals(this.tree.get(1),null);
        
        this.tree.clear();
        this.tree.remove(1);
    }
    
    @Test
    public void size_test()
    {
        assertEquals(this.tree.size(),(int)7);
        
        this.tree.remove(1);
        this.tree.remove(3);
        
        assertEquals(this.tree.size(),(int)5);
        
        this.tree.remove(11);
        this.tree.remove(1);
        this.tree.remove(9);
        this.tree.remove(0);
        
        assertEquals(this.tree.size(),(int)5);
    }
    
    @Test
    public void successor_test()
    {
        assertEquals(this.tree.succ(3),"Five");
        assertEquals(this.tree.succ(8),"Ten");
        assertEquals(this.tree.succ(10),null);
        
        assertEquals((int)this.tree.succ_key(3),(int)5);
        assertEquals((int)this.tree.succ_key(8),(int)10);
        assertEquals(this.tree.succ_key(10),null);
        
        this.tree.remove(5);
        this.tree.remove(10);
        
        assertEquals(this.tree.succ(3),"Six");
        assertEquals(this.tree.succ(8),null);
        assertEquals(this.tree.succ(10),null);
    }
    
    @Test
    public void predecessor_test()
    {
        assertEquals(this.tree.predecessor(3),"Two");
        assertEquals(this.tree.predecessor(8),"Six");
        assertEquals(this.tree.predecessor(10),"Eigth");
        assertEquals(this.tree.predecessor(1),null);
        assertEquals(this.tree.predecessor(11),null);
        assertEquals(this.tree.predecessor(0),null);
        
        this.tree.remove(8);
        this.tree.remove(2);
        
        assertEquals(this.tree.predecessor(3),"One");
        assertEquals(this.tree.predecessor(8),null);
        assertEquals(this.tree.predecessor(10),"Six");
    }
    
    private void secondary_tree()
    {
        this.tree.clear();
        
        this.tree.add(5, "Five");
        this.tree.add(7, "Seven");
        this.tree.add(8, "Eigth");
        this.tree.add(6, "Six");
        this.tree.add(2, "Two");
        this.tree.add(3, "Three");
    }
}
