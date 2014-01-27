
package data_structures;

/**
 * Interface which is used in the performance tests.
 * Interface contains many basic data structure operations.
 * @author Henri Korpela
 */
public interface Testable_Data_Structure<Key_Type, Value_Type> {
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
     * 
     * @param key 
     */
    public void remove(Key_Type key);
    
    /**
     * 
     * @param key
     * @return 
     */
    public Value_Type predecessor(Key_Type key);
    
    /**
     * 
     * @param key
     * @return 
     */
    public Value_Type successor(Key_Type key);
    
    /**
     * 
     * @return 
     */
    public Value_Type min();
    
    /**
     * 
     * @return 
     */
    public Value_Type max();
    
    /**
     * 
     * @param key
     * @return 
     */
    public boolean contains_key(Key_Type key);
    
    /**
     * 
     * @param value
     * @return 
     */
    public boolean contains(Value_Type value);
    
    /**
     * 
     */
    public void clear();
    
    /**
     * 
     * @return 
     */
    public int size();
}
