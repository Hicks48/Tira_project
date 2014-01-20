/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data_structures;

/**
 *
 * @author henrikorpela
 */
public interface Iterator<Type> {
    public Type next();
    public void remove();
    public boolean has_next();
}
