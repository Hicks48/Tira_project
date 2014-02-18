
package data_structures;

/**
 * Interface which is used in the performance tests.
 * Interface contains many basic data structure operations.
 * @author Henri Korpela
 */
public interface Set<Key_Type, Value_Type> {
    /**
     * Returns value that 
     * corresponds to given key.
     * @param key Key which value is retrieved.
     * @return Value that corresponds given key.
     * Null if key is not found.
     */
    public Value_Type get(Key_Type key);
    
    /**
     * Adds new node to data structure
     * with given key and value.
     * @param key Key for new node.
     * @param value Value for new node.
     */
    public void add(Key_Type key,Value_Type value);
    
    /**
     * Adds new node with given given key and
     * value that is same as the key.
     * Only works if key- and value types are the same.
     * If key already exists in the set replaces old entry
     * with new value.
     * @param key Key that is added also determines value.
     */
    public void add_identical(Key_Type key);
    
    /**
     * Removes node with given key from the set.
     * If key is not found from the set does nothing.
     * @param key Key to be removed.
     */
    public void remove(Key_Type key);
    
    /**
     * Return value of predecessor of node with given key.
     * @param key Key which predecessors value is retrieved.
     * * If key is not found return null.
     * @return Value of predecessor.
     */
    public Value_Type predecessor(Key_Type key);
    
    /**
     * Return value of successor of node with given key.
     * @param key Key which successor value is retrieved.
     * If key is not found return null.
     * @return Value of successor.
     */
    public Value_Type successor(Key_Type key);
    
    /**
     * Return value of node that has smallest key in the set.
     * @return value of node that has smallest key in the set.
     */
    public Value_Type min();
    
    /**
     * Return value of node that has largest key in the set.
     * @return value of node that has largest key in the set.
     */
    public Value_Type max();
    
    /**
     * Checks whether set contains node that has given key.
     * @param key Key that is checked.
     * @return True if set contains key and false if doesn't.
     */
    public boolean contains_key(Key_Type key);
    
    /**
     * Checks whether set contains node that has given value.
     * @param value Value that is checked.
     * @return True if set contains node with given value
     * and false if doesn't.
     */
    public boolean contains(Value_Type value);
    
    /**
     * Sets root to null. This resets the set.
     */
    public void clear();
    
    /**
     * Return size of the set.
     * @return size of the set.
     */
    public int size();
}
