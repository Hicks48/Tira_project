/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import data_structures.Queue;
import data_structures.Testable_Data_Structure;

/**
 *
 * @author henrikorpela
 */
public class Register extends Command{
    private Queue<Testable_Data_Structure> data;
    
    public Register()
    {
        super("register",new String[1]);
    }
    
    public Queue<Testable_Data_Structure> get_register()
    {
        return this.data;
    }
    
    @Override
    public void execute_command(String msg) {
        msg = msg.substring(super.call.length() - 1);
        msg = super.syntax(msg);
        if(msg == null)
        {
            // Do error
            return;
        }
        if(!super.read_params(msg))
        {
            // Do error
            return;
        }
        try
        {
            Class clazz = Class.forName(super.params[0]);
            Testable_Data_Structure instance = (Testable_Data_Structure)clazz.newInstance();
            this.data.add_front(instance);
        }
        catch(Exception e)
        {
            Controller.new_commmand_line(Controller.line_number);
        }
    }
    
    
    
}
