
package controller;

import algorithms.Random_Numbers;
import data_structures.AVL_Tree;
import data_structures.Binary_Tree;
import data_structures.Hash_Map;
import data_structures.List;
import data_structures.Red_Black_Tree;
import data_structures.Set;
import java.lang.reflect.Method;
import java.util.Scanner;

/**
 * Controller that is used to run test to data structures.
 * Is programs console based user interface.
 * @author Henri Korpela
 */
public class Controller {
    private boolean game_on;
    private int line_number;
    private Scanner reader;
    private Set[] set_structures;
    private Method methods[];
    
    /**
     * Creates new controller.
     */
    public Controller()
    {
        this.game_on = true;
        this.reader = new Scanner(System.in);
        this.set_structures = new Set[5];
        
        Class clazz = Set.class;
        this.methods = clazz.getDeclaredMethods();
                
        this.initialize_data_structure();
    }
    
    /**
     * Runs controller command line.
     */
    public void run()
    {
        this.print_welcome_message();
        String command;
        while(this.game_on)
        {
            this.print_command_line();
            command = this.ask_command();
            this.process_com(command);
            this.line_number ++;
        }
        this.print_goodbye_message();
    }
    
    private void print_welcome_message()
    {
        System.out.print("---------------------------------------------\n"
                + "Welcome to dynamic set operation tester!!! :D \n"
                + "---------------------------------------------\n\n");
    }
    
    private void print_goodbye_message()
    {
        System.out.print("\n----------------------------------\n"
                + "Goodbye hope to see you again! :D"
                + "\n----------------------------------\n");
    }
    
    private void print_command_line()
    {
        System.out.print(this.line_number + " >>>: ");
    }
    
    private String ask_command()
    {
        return this.reader.nextLine();
    }
    
    private void print_error(String message)
    {
        this.print_command_line();
        System.out.print("\tError: " + message + "\n");
    }
    
    private void initialize_data_structure()
    {
        Integer_Comparator comparator = new Integer_Comparator();
        
        List<Integer> list = new List();
        list.set_comparator(comparator);
        
        Hash_Map<Integer,Integer> hash_map = new Hash_Map(100);
        hash_map.set_comparator(comparator);
        
        Binary_Tree<Integer,Integer> binary_tree = new Binary_Tree(comparator);
        AVL_Tree<Integer,Integer> avl_tree = new AVL_Tree(comparator);
        Red_Black_Tree<Integer,Integer> red_black_tree = new Red_Black_Tree(comparator);
        
        this.set_structures[0] = list;
        this.set_structures[1] = hash_map;
        this.set_structures[2] = binary_tree;
        this.set_structures[3] = avl_tree;
        this.set_structures[4] = red_black_tree;
    }
    
    private void print_result(String result)
    {
        this.print_command_line();
        System.out.print("result: " + result + "\n");
    }
    
    private Set get_struct(String struct)
    {
        switch (struct) {
            case "List":
                return this.set_structures[0];
            case "Hash_Map":
                return this.set_structures[1];
            case "Binary_Tree":
                return this.set_structures[2];
            case "AVL_Tree":
                return this.set_structures[3];
            case "Red_Black_Tree":
                return this.set_structures[4];
        }
        this.print_error("Invalid data structure");
        return null;
    }
    
    private void process_com(String com)
    {
        if(com.equals("Available methods?"))
        {
            this.print_available_methods();
            return;
        }
        
        if(com.equals("Available data structures?"))
        {
            this.print_available_data_structures();
            return;
        }
        
        if(com.equals("Quit!"))
        {
             this.game_on = false;
             return;
        }
        
        String splitted_com[] = com.split(" ");
        
        try
        {
            Object data_struct = this.get_struct(splitted_com[0]);
            if(data_struct == null)
            {
                return;
            }
            Method method = this.get_method(splitted_com[1]);
            if(method == null)
            {
                return;
            }
            Object params[] = this.collect_params(splitted_com);
            int repeats = this.get_ammount_of_repeats(splitted_com);
        
            this.execute_command(repeats, method, data_struct, params);
        }
        catch(Exception e)
        {
            this.print_error("Ivalid command!!!");
        }
    }
    
    private void print_available_methods()
    {
        for(int i = 0;i < this.methods.length;i ++)
        {
            System.out.print("\t" + this.methods[i].getName() + "\n");
        }
    }
    
    private void print_available_data_structures()
    {
        for(int i = 0;i < this.set_structures.length;i ++)
        {
            System.out.print("\t" +this.set_structures[i].getClass().getName() + "\n");
        }
    }
    
    private int get_ammount_of_repeats(String splitted_coms[])
    {
        return Integer.parseInt(splitted_coms[splitted_coms.length - 1]);
    }
    
    private Method get_method(String name)
    {
        for(int i = 0;i < this.methods.length;i ++)
        {
            if(this.methods[i].getName().equals(name))
            {
                return this.methods[i];
            }
        }
        this.print_error("Invalid method");
        return null;
    }
    
    private Object[] collect_params(String splitted_com[])
    {
        if(splitted_com.length < 3)
        {
            return null;
        }
        Object params[] = new Object[splitted_com.length - 3];
        int n = 0;
        for(int i = 2;i < splitted_com.length - 1;i ++)
        {
            params[n] = Integer.parseInt(splitted_com[i]);
            n ++;
        }
        return params;
    }
    
    private void execute_command(int repeats, Method method, Object data_struct, Object params[])
    {
        Random_Numbers default_params = null;
        Object return_obj = null;
        Timer timer = new Timer();
        
        if(params.length == 0)
        {
            default_params = new Random_Numbers(repeats);
            default_params.default_ini();
            default_params.shuffle();
        }
        
        timer.start();
        
        for(int i = 0;i < repeats;i ++)
        {
            try
            {
                if(default_params == null)
                {
                    return_obj = method.invoke(data_struct, params);
                }
                else
                {
                    
                    return_obj = method.invoke(data_struct, this.get_default_params(method.getName(), default_params, i));
                }
            }
            catch(Exception e)
            {
                this.print_error("Error in execution!");
                return;
            }
        }
        
        long time = timer.reset();
        String return_obj_string = "void";
        if(return_obj != null)
        {
            return_obj_string = return_obj.toString();
        }
        
        this.print_result("\tlast return: " + return_obj_string + "\n\t" + "time consumption: " + Long.toString(time) + " milliseconds");
    }
    
    private Object[] get_default_params(String method, Random_Numbers random, int lap_counter)
    {
        int params_ammount = 0;
        switch(method)
        {
            case "size":
            case "min":
            case "max":
            case "clear":
                params_ammount = 0;
                break;
            case "get":
            case "contains_key":
            case "contains":
            case "remove":
            case "predecessor":
            case "successor":
            case "add_identical":
                params_ammount = 1;
                break;
            case "add":
                params_ammount = 2;
                break;
        }
        Object params[] = new Object[params_ammount];
        for(int i = 0;i < params.length;i ++)
        {
            params[i] = random.get_number(lap_counter + i);
        }
        return params;
    }
}
