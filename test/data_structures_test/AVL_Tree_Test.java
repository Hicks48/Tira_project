/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data_structures_test;

import algorithms.Random_Numbers;
import data_structures.AVL_Tree;
import java.util.Random;
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
         assertEquals((int)this.tree.size(),(int)11);
         assertEquals((int)7,(int)this.tree.get(7));
         assertEquals((int)1,(int)this.tree.get(1));
         assertEquals((int)14,(int)this.tree.get(14));
         
         this.tree.remove(7);
         this.tree.remove(1);
         this.tree.remove(14);
         this.tree.remove(10);
         this.tree.remove(6);
         
         if(this.tree.get(7) != null || this.tree.get(1) != null || this.tree.get(14) != null)
         {
             assertEquals(false,true);
         }
         
         assertEquals((int)8,(int)this.tree.get(8));
         assertEquals((int)2,(int)this.tree.get(2));
         assertEquals((int)3,(int)this.tree.get(3));
         assertEquals(null,this.tree.get(10));
         assertEquals((int)12,(int)this.tree.get(12));
         assertEquals(null,this.tree.get(6));
         assertEquals((int)5,(int)this.tree.get(5));
         assertEquals((int)this.tree.size(),(int)6);
     }
     
     @Test
     public void big_numbers()
     {
         this.tree.clear();
         int big_number = 100000;
         Random random = new Random();
         for(int i = 0;i < big_number;i ++)
         {
             this.tree.add(i, i);
         }
         for(int i = 0;i < big_number/2;i ++)
         {
             this.tree.remove(i);
         }
         this.tree.clear();
         for(int i = 0;i < big_number;i ++)
         {
             int a = random.nextInt();
             this.tree.add(a, a);
         }
         for(int i = 0;i < big_number;i ++)
         {
             int a = random.nextInt();
             this.tree.remove(a);
         }
     }
     
     @Test
     public void min_and_max_test()
     {
         
     }
     
     @Test
     public void console_test()
     {
         this.tree.clear();
         Random_Numbers random = new Random_Numbers(10000);
         random.shuffle();
         for(int i = 10000;i > 0;i --)
         {
             int a = random.get_number(i);
             this.tree.add(a, a);
         }
         for(int i = 10000;i > 0;i --)
         {
             int a = random.get_number(i);
             this.tree.remove(a);
         }
     }
     
     @Test
     public void heigth_test()
     {
         this.tree.clear();
         int big_number = 1000;
         for(int i = 0;i < big_number;i ++)
         {
             this.tree.add(i, i);
         }
         assertEquals((int)this.tree.get_heigth(),(int)9);
         for(int i = 0;i < big_number-200;i ++)
         {
             this.tree.remove(i);
         }
         assertEquals((int)this.tree.size(),(int)200);
         assertEquals((int)this.tree.get_heigth(),(int)7);
     }
     
     @Test
     public void structure_Test()
     {
         this.tree.clear();
         
         this.tree.add(12,12);
         this.tree.add(18,18);
         this.tree.add(22,22);
         this.tree.add(20,20);
         this.tree.add(2,2);
         this.tree.add(9,9);
         this.tree.add(1,1);
         this.tree.add(137,137);
         
         assertEquals(this.tree.prerder()," 18 9 2 1 12 22 20 137 ");
         
         this.tree.add(80,80);
         this.tree.add(47,47);
         this.tree.add(56,56);
         this.tree.add(33,33);
         this.tree.add(3,3);
         this.tree.add(4,4);
         this.tree.add(29,29);
         this.tree.add(11,11);
         this.tree.add(123,123);
         this.tree.add(180,180);
         
         assertEquals(this.tree.prerder()," 18 3 2 1 9 4 12 11 47 22 20 33 29 80 56 137 123 180 ");
         
         this.tree.remove(80);
         
         assertEquals(this.tree.prerder()," 18 3 2 1 9 4 12 11 47 22 20 33 29 137 56 123 180 ");
     }
}
