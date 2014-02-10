
package tira_project;

import controller.Controller;
import data_structures.List;
import data_structures.Set;
import java.lang.reflect.Method;



/**
 * Program starting point.
 * @author Henri Korpela
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.run();
        /*
        List<Integer> list = new List();
        //System.out.print("Canonial name: " + list.getClass().getCanonicalName() + "\n");
        //System.out.print("Name: " + list.getClass().getName());
        
        
        Class clazz = Set.class;
        Method methods[] = clazz.getDeclaredMethods();
        for(int i = 0;i < methods.length;i ++)
        {
            System.out.print(methods[i].getName() + "\n");
        }
        try
        {
            methods[2].invoke(list,1,1);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        System.out.print(list.toString() + " List done. :D Success!!!");
        /*
         * Controller controller = new Controller();
         * controller.run();
         */
        
    }
}
