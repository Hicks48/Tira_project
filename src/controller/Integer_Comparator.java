
package controller;

import java.util.Comparator;

/**
 * Class implements Comparator<Integer> interface
 * and is used to compare two integers using
 * Comparator interfaces compare method.
 * @author Henri Korpela
 */
public class Integer_Comparator implements Comparator<Integer>{
    
    /**
     * Compares two integers. Comparison is done
     * by subtracting second parameter from 
     * first parameter.
     * @param o1 Integer that another integer
     * is compared to.
     * @param o2 Integer that another integer
     * is compared to.
     * @return Fist parameter subtracted
     * from second parameter.
     */
    @Override
    public int compare(Integer o1, Integer o2) {
        return o1 - o2;
    }
    
}
