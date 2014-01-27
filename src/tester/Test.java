/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import data_structures.Hash_Map;
import data_structures.List;
import data_structures.Testable_Data_Structure;
import java.util.Random;

/**
 *
 * @author henrikorpela
 */
public abstract class Test {
    protected long max_x_result;
    protected long max_y_result;
    protected Random random;
    
    public Test()
    {
        this.random = new Random();
    }
    
    public abstract Hash_Map<Testable_Data_Structure,List<Point>> test(List<Testable_Data_Structure> data_structs);
    
    public long get_max_x_result()
    {
        return this.max_x_result;
    }
    
    public long get_max_y_result()
    {
        return this.max_y_result;
    }
}
