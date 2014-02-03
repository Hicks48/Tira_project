
package controller;

/**
 * Class provides methods that can be used to
 * measure time intervals.
 * @author Henri Korpela
 */
public class Timer {
    /**
     * Time that timer was started in milliseconds.
     */
    private long start_time;
    
    /**
     * Starts the clock.
     */
    public void start()
    {
        this.start_time = System.currentTimeMillis();
    }
    
    /**
     * Return time interval.
     * @return time interval.
     */
    public long runnig_time()
    {
        return System.currentTimeMillis() - this.start_time;
    }
    
    /**
     * Resets the clock.
     * @return Timer time before reset.
     */
    public long reset()
    {
        long time = System.currentTimeMillis() - this.start_time;
        this.start_time = System.currentTimeMillis();
        return time;
    }
}
