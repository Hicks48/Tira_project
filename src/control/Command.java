/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

/**
 *
 * @author henrikorpela
 */
public abstract class Command {
    protected final String params[];
    protected final String call;
    
    public Command(String command, String params[])
    {
        this.call = command;
        this.params = params;
    }
    
    public abstract void execute_command(String command);
    
    public boolean is_call(String command)
    {
        String com = command.substring(0,this.call.length() - 1);
        return com.equals(this.call);
    }
    
    protected String syntax(String params)
    {
        if(params.charAt(0) != '(' || params.charAt(params.length() - 1) != ')')
        {
            return params.substring(1,params.length() - 2);
        }
        return null;
    }
    
    protected boolean read_params(String params)
    {
        params = params.trim();
        
        String array[] = params.split(",");
        
        if(this.params.length != array.length)
        {
            return false;
        }
        
        for(int i = 0;i < array.length;i ++)
        {
            this.params[i] = array[i];
        }
        
        return true;
    }
}
