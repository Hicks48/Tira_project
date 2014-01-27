
package control;

/**
 * Base class for controller commands.
 * @author Henri Korpela
 */
public abstract class Command {
    /**
     * Array that contains parameters for specific command.
     */
    protected final String params[];
    /**
     * Name of the command.
     */
    protected final String call;
    /**
     * Controller that owns the command.
     */
    protected Controller master;
    
    /**
     * Creates new command.
     * @param command 
     * @param params
     * @param master 
     */
    public Command(String command, String params[], Controller master)
    {
        this.call = command;
        this.params = params;
        this.master = master;
    }
    
    /**
     * Execute specific command. Each command implements its own.
     * @param command Command that is executed.
     */
    public abstract void execute_command(String command);
    
    public boolean is_call(String command)
    {
        String com = command.substring(0,this.call.length() - 1);
        return com.equals(this.call);
    }
    
    protected String syntax(String params)
    {
        if(params.charAt(0) == '(' && params.charAt(params.length() - 1) == ')')
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
