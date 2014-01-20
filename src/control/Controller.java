/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import data_structures.Queue;
import java.util.Scanner;
import tester.Window;

/**
 *
 * @author henrikorpela
 */
public class Controller {
    public boolean game_on;
    public static int line_number;
    private Queue<Window> wins;
    private Scanner reader;
    private final Command[] coms;
    
    public Controller()
    {
        this.game_on = true;
        this.line_number = 0;
        this.wins = new Queue();
        this.reader = new Scanner(System.in);
        this.coms = new Command[3];
    }
    
    public void run()
    {
        while(this.game_on)
        {
            this.new_commmand_line(this.line_number);
            String com = this.read_commmand();
            int com_index = this.find_command(com);
            if(com_index == -1)
            {
                this.inform_invalid_command(com);
            }
            else
            {
                this.coms[com_index].execute_command(com);
            }
            System.out.print("\n");
        }
        this.quit();
    }
    
    public static void new_commmand_line(int line_number)
    {
        System.out.print("\n" + line_number + ">>>: ");
    }
    
    private String read_commmand()
    {
        return this.reader.nextLine();
    }
    
    private int find_command(String com)
    {
        for(int i = 0;i < this.coms.length;i ++)
        {
            if(this.coms[i].is_call(com))
            {
                return i;
            }
        }
        return -1;
    }
    
    private void inform_invalid_command(String command)
    {
        System.out.print("Invalid command");
    }
    
    private void init_commands()
    {
        this.coms[0] = new Run();
        this.coms[1] = new Register();
    }
    
    private void quit()
    {
        for(int i = 0;i < this.wins.size();i ++)
        {
            this.wins.get(i).dispose();
        }
    }
}
