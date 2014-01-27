/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data_structures_test;

import java.util.Comparator;

/**
 *
 * @author henrikorpela
 */
public class Integer_Comparator implements Comparator<Integer>{

    @Override
    public int compare(Integer o1, Integer o2) {
        return o1 - o2;
    }
    
}
