/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import data_structures.List;
import data_structures.Testable_Data_Structure;

/**
 *
 * @author henrikorpela
 */
public class Register extends Command{
    private List<Testable_Data_Structure> data;
    
    public Register(Controller master)
    {
        super("register",new String[1],master);
    }
    
    public List<Testable_Data_Structure> get_register()
    {
        return this.data;
    }
    
    public boolean register_empty()
    {
        return this.data.is_empty();
    }
    
    @Override
    public void execute_command(String msg) {
        msg = msg.substring(super.call.length() - 1);
        msg = super.syntax(msg);
        if(msg == null)
        {
            Error_Handeler.print_error(super.master,"Invalid syntax");
            return;
        }
        if(!super.read_params(msg))
        {
            Error_Handeler.print_error(super.master,"Ivalid parameters");
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
            Error_Handeler.print_error(super.master, "Given class not found");
        }
    }
    
    
    
}
