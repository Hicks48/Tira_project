
package data_structures;

/**
 * Interface that is used to iterate through data structure.
 * @author Henri Korpela
 */
public interface Iterator<Type> {
    /**
     * Return next element.
     * @return Next element.
     */
    public Type itr_next();
    
    /**
     * Remove current element and move to next element.
     */
    public void itr_remove();
    
    /**
     * Checks whether iterator has next element.
     * @return True if iterator has next element and false if hasn't.
     */
    public boolean itr_has_next();
    
    /**
     * Resets iterator to start
     * of the current data structure.
     */
    public void itr_reset();
    
    /**
     * Return element that iterator is currently
     * in but doesn't move to next element.
     * @return Element that iterator is currently in.
     */
    public Type itr_get();
}
