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
public class Tester {
    private Hash_Map<String,Test> tests;
    
    public Tester()
    {
        this.tests = new Hash_Map(10);
        this.init_tests();
    }
    
    public Window run(String test, List<Testable_Data_Structure> data_structs)
    {
        Test run = this.tests.get(test);
        if(run == null)
        {
            return null;
        }
        return this.create_window("Test " + test, run,data_structs);
    }
    
    private void init_tests()
    {
        this.tests.put("add", new Add_Test());
    }
    
    private Window create_window(String name, Test run, List<Testable_Data_Structure> data_structs)
    {
        Diagram diagram = new Diagram(run.max_x_result ,run.max_y_result, run.test(data_structs));
        return new Window(name,diagram);
    }
}
