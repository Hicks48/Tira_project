
package tira_project;

import data_structures.List;



/**
 * Program starting point.
 * @author Henri Korpela
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        List<Integer> list = new List();
        System.out.print("Canonial name: " + list.getClass().getCanonicalName() + "\n");
        System.out.print("Name: " + list.getClass().getName());
        
        /*
         * Controller controller = new Controller();
         * controller.run();
         */
        
    }
}
