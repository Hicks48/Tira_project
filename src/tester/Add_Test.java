/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import data_structures.Hash_Map;
import data_structures.List;
import data_structures.Testable_Data_Structure;

/**
 *
 * @author henrikorpela
 */
public class Add_Test extends Test{

    @Override
    public Hash_Map<Testable_Data_Structure,List<Point>> test(List<Testable_Data_Structure> data_structs) {
        Hash_Map<Testable_Data_Structure,List<Point>> plots = new Hash_Map(data_structs.size() * 10);
        while(!data_structs.is_empty())
        {
            Testable_Data_Structure data_struct = data_structs.pop_first();
            List<Point> plot = this.test_data_struct(data_struct);
            plots.put(data_struct,plot);
        }
        return plots;
    }
    
    private List<Point> test_data_struct(Testable_Data_Structure data_struct)
    {
        List<Point> plot = new List();
        for(int i = 1;i <= 100;i ++)
        {
            plot.add_front(this.run_one_test(i * 100,data_struct));
        }
        return plot;
    }
    
    private Point run_one_test(int adds, Testable_Data_Structure data_struct)
    {
        Clock.start_clock();
        for(int i = 0;i < adds;i ++)
        {
            data_struct.add(super.random.nextInt(), super.random.nextInt());
        }
        long end_time = Clock.stop();
        this.update_max(adds, end_time);
        return new Point(adds,end_time);
    }
    
    private void update_max(int adds,long time)
    {
        if(adds > super.max_x_result)
        {
            super.max_x_result = adds;
        }
        if(time > super.max_y_result)
        {
            super.max_y_result = time;
        }
    }
}
