/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

/**
 *
 * @author henrikorpela
 */
public class Clock {
    private static long start_time;
    private static boolean clock_on;
    
    public static void start_clock()
    {
        start_time = System.currentTimeMillis();
        clock_on = true;
    }
    
    public static long interval()
    {
        if(clock_on == false)
        {
            return (-1);
        }
        return ((System.currentTimeMillis() - start_time));
    }
    
    public static long stop()
    {
        if(clock_on == false)
        {
            return (-1);
        }
        clock_on = false;
        return (System.currentTimeMillis() - start_time);
    }
}
