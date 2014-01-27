/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import data_structures.List;
import java.util.Scanner;
import tester.Tester;
import tester.Window;

/**
 *
 * @author henrikorpela
 */
public class Controller {
    private int line_number;
    public boolean game_on;
    private Tester tester;
    private List<Window> wins;
    private Scanner reader;
    private final Command[] coms;
    
    public Controller(Tester tester)
    {
        this.game_on = true;
        this.line_number = 0;
        this.wins = new List();
        this.reader = new Scanner(System.in);
        this.coms = new Command[3];
        this.init_commands();
        this.tester = tester;
    }
    
    public void run()
    {
        while(this.game_on)
        {
            this.new_commmand_line();
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
    
    public void register_window(Window win)
    {
        this.wins.add_front(win);
    }
    
    public void new_commmand_line()
    {
        System.out.print("\n" + this.line_number + ">>>: ");
        this.line_number ++;
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
        Register r = new Register(this);
        this.coms[0] = new Run(this.tester,r,this);
        this.coms[1] = r;
    }
    
    private void quit()
    {
        while(!this.wins.is_empty())
        {
            this.wins.pop_first().dispose();
        }
    }
}
