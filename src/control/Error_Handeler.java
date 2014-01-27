
package control;

/**
 * Prints errors in the controller.
 * @author Henri Korpela
 */
public class Error_Handeler {
    /**
     * Print error on new line of the controller.
     * @param controller Controller that error is printed to.
     * @param error_msg Error message to be printed.
     */
    public static void print_error(Controller controller, String error_msg)
    {
        controller.new_commmand_line();
        System.out.print("Error: " + error_msg);
    }
}
