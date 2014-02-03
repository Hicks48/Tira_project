
package controller;

import algorithms.Random_Numbers;
import data_structures.Binary_Tree;
import data_structures.List;
import data_structures.Set;
import java.util.Scanner;

/**
 * Controller that is used to run test to data structures.
 * Is programs console based user interface.
 * @author Henri Korpela
 */
public class Controller {
    private boolean game_on;
    private boolean error_occured;
    private int line_number;
    private Scanner reader;
    private Set[] set_structures;
    private String[] commands;
    
    public Controller()
    {
        this.game_on = true;
        this.error_occured = false;
        this.reader = new Scanner(System.in);
        this.set_structures = new Set[8];
        this.commands = new String[4];
        this.initialize_data_structure();
    }
    
    public void run()
    {
        String command;
        while(this.game_on)
        {
            this.error_occured = false;
            this.print_command_line();
            command = this.ask_command();
            this.proses_command(command);
            this.line_number ++;
        }
    }
    
    private void print_command_line()
    {
        System.out.print(this.line_number + ">>: ");
    }
    
    private String ask_command()
    {
        return this.reader.nextLine();
    }
    
    private void proses_command(String command)
    {
        String all[] = command.split(",");
        String call = all[0];
        Set data_struct = this.get_struct(all[1]);
        String params[] = all[2].split(" ");
        
        if(this.error_occured)
        {
            return;
        }
        
        this.do_operation(call, data_struct, params);
    }
    
    private void print_error(String message)
    {
        this.error_occured = true;
        this.print_command_line();
        System.out.print("Error: " + message);
    }
    
    private void initialize_data_structure()
    {
        List<Integer> list = new List();
        list.set_comparator(new Integer_Comparator());
        Binary_Tree<Integer,Integer> binary_tree = new Binary_Tree(new Integer_Comparator());
        
        this.set_structures[0] = list;
        this.set_structures[1] = binary_tree;
    }
    
    private void add_struct(int ammount,Set struct)
    {   
        Random_Numbers numbers = new Random_Numbers(ammount);
        Timer timer = new Timer();
        
        for(int i = 0;i < ammount;i ++)
        {
            numbers.add_number(i);
        }
        numbers.shuffle();
        
        timer.start();
        for(int i = 0;i < numbers.size();i ++)
        {
            struct.add(numbers.get_number(i), i);
            if(i % 100 == 0)
            {
                this.print_result(Long.toString(timer.runnig_time()));
            }
        }
        this.print_command_line();
        System.out.print("total time: " + Long.toString(timer.runnig_time()));
    }
    
    private void print_result(String result)
    {
        this.print_command_line();
        System.out.print("result: " + result);
    }
    
    private Set get_struct(String struct)
    {
        switch (struct) {
            case "List":
                return this.set_structures[0];
            case "Binary_Tree":
                return this.set_structures[1];
        }
        this.print_error("Invalid data structure");
        return null;
    }
    
    private void do_operation(String call, Set data_struct, String params[])
    {
        switch(call)
        {
            case "add":
                    try
                    {
                        this.add_struct(Integer.parseInt(params[0]), data_struct);
                    }
                    catch(Exception e)
                    {
                        this.print_error("Invalid parameters.");
                        return;
                    }
                    break;
        }
    }
}
