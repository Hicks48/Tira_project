/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data_structures_test;

import data_structures.Red_Black;
import data_structures.Red_Black_Tree;
import data_structures.Red_Black_Tree_Node;
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
public class Red_Black_Tree_Test {
    private Red_Black_Tree<Integer,Integer> tree;
    
    public Red_Black_Tree_Test() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.tree = new Red_Black_Tree(new Integer_Comparator());
        
        this.tree.add(48, 48);
        this.tree.add(32, 32);
        this.tree.add(22, 22);
        this.tree.add(1, 1);
        this.tree.add(3, 3);
        this.tree.add(27, 27);
        this.tree.add(9, 9);
        this.tree.add(12, 12);
        this.tree.add(42, 42);
        this.tree.add(47, 47);
        this.tree.add(33, 33);
        this.tree.add(4, 4);
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
        this.tree.clear();
        
        this.tree.add(48, 48);
        this.tree.add(3, 3);
        this.tree.add(12, 12);
        this.tree.add(32, 32);
        this.tree.add(7, 7);
        
        assertEquals((int)this.tree.get(48),(int)48);
        assertEquals((int)this.tree.get(3),(int)3);
        assertEquals((int)this.tree.get(12),(int)12);
        assertEquals((int)this.tree.get(32),(int)32);
        assertEquals((int)this.tree.get(7),(int)7);
        
        assertEquals(this.tree.get(50),null);
        assertEquals(this.tree.get(0),null);
        assertEquals(this.tree.get(10),null);
    }
    
    @Test
    public void remove_test()
    {
        assertEquals((int)this.tree.get(4),(int)4);
        assertEquals((int)this.tree.get(42),(int)42);
        assertEquals((int)this.tree.get(27),(int)27);
        assertEquals((int)this.tree.get(9),(int)9);
        
        this.tree.remove(4);
        this.tree.remove(42);
        this.tree.remove(27);
        this.tree.remove(9);
        
        assertEquals(this.tree.get(4),null);
        assertEquals(this.tree.get(42),null);
        assertEquals(this.tree.get(27),null);
        assertEquals(this.tree.get(9),null);
    }
    
    @Test
    public void add_structure_test()
    {
        this.tree.clear();
        
        this.tree.add(8,8);
        this.tree.add(20,20);
        this.tree.add(12,12);
        this.tree.add(13,13);
        this.tree.add(9,9);
        this.tree.add(14,14);
        this.tree.add(7,7);
        this.tree.add(1,1);
        
        assertEquals(" 12 8 7 1 9 14 13 20 ",this.tree.prerder());
    }
    
    @Test
    public void node_test()
    {
        Red_Black_Tree_Node<Integer,Integer> node = new Red_Black_Tree_Node(1,1,Red_Black.RED,1);
        assertEquals(node.color,Red_Black.RED);
        Red_Black_Tree_Node.change_color(node);
        assertEquals(node.color,Red_Black.BLACK);
    }
    
    @Test
    public void pred_succ_test()
    {
        assertEquals((int)this.tree.successor(4),(int)9);
        assertEquals((int)this.tree.predecessor(4),(int)3);
    }
    
    @Test
    public void big_ammounts_test()
    {
        int big_number = 10000;
        this.tree.clear();
        for(int i = 0;i < big_number;i ++)
        {
            this.tree.add(i, i);
        }
        assertEquals(this.tree.size(),(int)big_number);
        for(int i = 0;i < big_number;i ++)
        {
            assertEquals((int)this.tree.get(i),(int)i);
        }
        
        for(int i = 0;i < big_number/2;i ++)
        {
            this.tree.remove(i);
        }
        assertEquals(this.tree.size(),(int)(big_number/2));
        for(int i = 0;i < big_number/2;i ++)
        {
            assertEquals(this.tree.get(i),null);
        }
    }
}
