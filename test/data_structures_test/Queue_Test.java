package data_structures_test;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import data_structures.Queue;
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
public class Queue_Test {
    
    public Queue<Integer> que;
    
    public Queue_Test() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.que = new Queue();
        for(int i = 1;i <= 8;i ++)
        {
            this.que.add_behind(i);
        }
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
         Queue<Integer> que_first_add = new Queue<Integer>();
         Queue<Integer> que_last_add = new Queue<Integer>();
         
         for(int i = 1;i <= 8;i ++)
         {
             que_first_add.add_front(i);
             que_last_add.add_behind(i);
         }
         
         assertEquals(que_first_add.toString()," 8 7 6 5 4 3 2 1 ");
         assertEquals(que_last_add.toString()," 1 2 3 4 5 6 7 8 ");
     }
     
     @Test
     public void remove_test()
     {
         this.que.pop_first();
         assertEquals(" 2 3 4 5 6 7 8 ",this.que.toString());
         this.que.pop_last();
         assertEquals(" 2 3 4 5 6 7 ",this.que.toString());
     }
     
     @Test
     public void remove_from_empty_test()
     {
         for(int i = 0;i < 8;i ++)
         {
             this.que.pop_first();
         }
         assertEquals(this.que.pop_first(),null);
         assertEquals(this.que.pop_last(),null);
     }
     
     @Test
     public void contains_test()
     {
         assertEquals(this.que.contains(1),0);
         assertEquals(this.que.contains(5),4);
         assertEquals(this.que.contains(8),7);
         
         assertEquals(this.que.contains(0),-1);
         assertEquals(this.que.contains(-1),-1);
         assertEquals(this.que.contains(9),-1);
     }
     
     @Test
     public void empty_test()
     {
         assertEquals(this.que.is_empty(),false);
         for(int i = 0;i < 8;i ++)
         {
             this.que.pop_first();
         }
         assertEquals(this.que.toString()," ");
         assertEquals(this.que.size(),0);
         assertEquals(this.que.is_empty(),true);
     }
     
     @Test
     public void size_test()
     {
         assertEquals(this.que.size(),8);
         for(int i = 0;i < 8;i ++)
         {
             this.que.pop_first();
             assertEquals(this.que.size(),8-i-1);
         }
         assertEquals(this.que.size(),0);
     }
}
