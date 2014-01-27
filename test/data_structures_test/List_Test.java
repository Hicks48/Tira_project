package data_structures_test;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import data_structures.List;
import java.util.Random;
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
public class List_Test {
    private Random random;
    public List<Integer> list;
    
    public List_Test() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.list = new List();
        this.list.set_comparator(new Integer_Comparator());
        for(int i = 1;i <= 8;i ++)
        {
            this.list.add_behind(i);
        }
    }
    
    private void shfuled_list()
    {
        this.random = new Random();
        this.list.clear();
        for(int i = 0; i < 10;i ++)
        {
            if(i == 3)
            {
                this.list.add_front(1);
            }
            if(i == 7)
            {
                this.list.add_front(8);
            }
            this.list.add_front(this.random.nextInt(8) + 1);
        }
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void add_test() 
    {
        List<Integer> que_first_add = new List<Integer>();
        List<Integer> que_last_add = new List<Integer>();
        
        for(int i = 1;i <= 8;i ++)
        {
            que_first_add.add_front(i);
            que_last_add.add_behind(i);
        }
         
         assertEquals(que_first_add.toString()," 8 7 6 5 4 3 2 1 ");
         assertEquals(que_last_add.toString()," 1 2 3 4 5 6 7 8 ");
     }
     
     @Test
     public void pop_test()
     {
         this.list.pop_first();
         assertEquals(" 2 3 4 5 6 7 8 ",this.list.toString());
         this.list.pop_last();
         assertEquals(" 2 3 4 5 6 7 ",this.list.toString());
     }
     
     @Test
     public void remove_from_empty_test()
     {
         for(int i = 0;i < 8;i ++)
         {
             this.list.pop_first();
         }
         assertEquals(this.list.pop_first(),null);
         assertEquals(this.list.pop_last(),null);
     }
     
     @Test
     public void index_of_test()
     {
         assertEquals(this.list.index_of(1),0);
         assertEquals(this.list.index_of(5),4);
         assertEquals(this.list.index_of(8),7);
         
         assertEquals(this.list.index_of(0),-1);
         assertEquals(this.list.index_of(-1),-1);
         assertEquals(this.list.index_of(9),-1);
     }
     
     @Test
     public void empty_test()
     {
         assertEquals(this.list.is_empty(),false);
         for(int i = 0;i < 8;i ++)
         {
             this.list.pop_first();
         }
         assertEquals(this.list.toString()," ");
         assertEquals(this.list.size(),0);
         assertEquals(this.list.is_empty(),true);
     }
     
     @Test
     public void size_test()
     {
         assertEquals(this.list.size(),8);
         for(int i = 0;i < 8;i ++)
         {
             this.list.pop_first();
             assertEquals(this.list.size(),8-i-1);
         }
         assertEquals(this.list.size(),0);
     }
     
     @Test
     public void remove_test()
     {
         this.list.remove(5);
         assertEquals(this.list.toString()," 1 2 3 4 5 7 8 ");
         this.list.remove(0);
         assertEquals(this.list.toString()," 2 3 4 5 7 8 ");
         this.list.remove(5);
         assertEquals(this.list.toString()," 2 3 4 5 7 ");
         
         this.list.remove(this.list.index_of(4));
         assertEquals(this.list.toString()," 2 3 5 7 ");
         
         assertEquals(this.list.size(),4);
         this.list.remove(0);
         this.list.remove(0);
         this.list.remove(0);
         this.list.remove(0);
         assertEquals(this.list.is_empty(),true);
     }
     
     @Test
     public void iterator_test()
     {
         int i = 1;
         while(this.list.itr_has_next())
         {
             assertEquals((int)i,(int)this.list.itr_next());
             i ++;
         }
         this.list.itr_reset();
         while(this.list.itr_has_next())
         {
             int itr = this.list.itr_get();
             if(itr == 6)
             {
                 this.list.itr_remove();
             }
             else
             {
                this.list.itr_next();
             }
         }
         assertEquals(" 1 2 3 4 5 7 8 ",this.list.toString());
     }
     
     @Test
     public void max_min_test()
     {
         assertEquals((int)this.list.max(),(int)8);
         assertEquals((int)this.list.min(),(int)1);
         
         this.list.remove(7);
         this.list.remove(0);
         this.list.remove(3);
         
         assertEquals((int)this.list.max(),(int)7);
         assertEquals((int)this.list.min(),(int)2);
         
         this.shfuled_list();
         assertEquals((int)this.list.max(),(int)8);
         assertEquals((int)this.list.min(),(int)1);
     }
     
     @Test
     public void succ_pred_test()
     {
         assertEquals((int)this.list.successor(this.list.index_of(4)),(int)5);
         assertEquals((int)this.list.predecessor(this.list.index_of(4)),(int)3);
     }
     
     @Test
     public void contains_test()
     {
         assertEquals(this.list.contains(1),true);
         assertEquals(this.list.contains(4),true);
         assertEquals(this.list.contains(8),true);
         
         assertEquals(this.list.contains_key(0),true);
         assertEquals(this.list.contains_key(3),true);
         assertEquals(this.list.contains_key(7),true);
         
         assertEquals(this.list.contains_key(-1),false);
         assertEquals(this.list.contains_key(8),false);
         assertEquals(this.list.contains(0),false);
         assertEquals(this.list.contains(9),false);
     }
}
