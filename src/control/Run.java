/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import tester.Tester;

/**
 *
 * @author henrikorpela
 */
public class Run extends Command{
    private Tester tester;
    private Register register;
    
    public Run(Tester tester, Register register,Controller master)
    {
        super("run",new String[2],master);
        this.tester = tester;
        this.register = register;
    }
    
    @Override
    public void execute_command(String msg) {
        msg = msg.substring(super.call.length() - 1);
        msg = super.syntax(msg);
        if(msg == null)
        {
            Error_Handeler.print_error(super.master,"Invalid syntax");
        }
        if(!super.read_params(msg))
        {
            Error_Handeler.print_error(super.master,"Invalid parameters");
        }
        if(this.register.register_empty())
        {
            Error_Handeler.print_error(super.master,"Empty register");
        }
        super.master.register_window(this.tester.run(super.call,this.register.get_register()));
    }
}
