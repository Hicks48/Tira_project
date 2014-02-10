
package data_structures_test;

import data_structures.Hash_Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Henrikorpela
 */
public class Hash_Map_Test {
    public Hash_Map<String,String> map;
    
    public Hash_Map_Test() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.map = new Hash_Map(10);
        this.map.put("1","1");
        this.map.put("2","2");
        this.map.put("3","3");
        this.map.put("4","4");
        this.map.put("Dwayne","Hicks");
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void get_and_put_test() 
     {
         assertEquals(this.map.get("1"),"1");
         assertEquals(this.map.get("2"),"2");
         assertEquals(this.map.get("3"),"3");
         assertEquals(this.map.get("4"),"4");
         assertEquals(this.map.get("Dwayne"),"Hicks");
     }
     
     @Test
     public void remove_test()
     {
         this.map.remove("3");
         this.map.remove("4");
         
         assertEquals(this.map.get("3"),null);
         assertEquals(this.map.get("4"),null);
         
         assertEquals(this.map.get("1"),"1");
         assertEquals(this.map.get("2"),"2");
         assertEquals(this.map.get("Dwayne"),"Hicks");
     }
     
     @Test
     public void size_test()
     {
         assertEquals(this.map.size(),(int)5);
         
         this.map.remove("3");
         this.map.remove("3");
         
         assertEquals(this.map.size(),(int)4);
     }
}
