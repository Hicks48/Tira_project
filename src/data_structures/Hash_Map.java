
package data_structures;

import java.util.Comparator;

/**
 * Hash map that has also many like set properties.
 * Hash Map implements Set and iterator interface.
 * For many of the set operations user must provide
 * comparator that can compare keys. Iterator can
 * be used to iterate throw hash maps keys. Hash map
 * uses overflow lists to handle collisions.
 * User determines hash tables size when he or she
 * creates hash map. Size can also be changed when
 * hash map is cleared. Hash map uses keys hash code to map data.
 * @author Henri Korpela
 */
public class Hash_Map<Key_Type,Value_Type> implements Set<Key_Type,Value_Type>, Iterator<Key_Type> {
    /**
     * Hash Maps hash table.
     * All nodes are stored in hash table.
     */
    private Object[] hash_table;
    
    /**
     * List that contains all keys in the hash map.
     */
    private List<Key_Type> keys;
    
    /**
     * Creates new hash map thats hash tables size is the given size.
     * @param hash_table_size Size of hash maps hash table.
     */
    public Hash_Map(int hash_table_size)
    {
        this.hash_table = new Object[hash_table_size];
        this.init_hahs_table();
        this.keys = new List();
    }
    
    /**
     * Adds new node with given key to the hash map.
     * If map already contains node with given key
     * replaces nodes data with new data.
     * @param key New nodes key.
     * @param value Value for new node.
     */
    public void put(Key_Type key, Value_Type value)
    {
        int hash = this.hash(key);
        Hash_Map_Node<Key_Type,Value_Type> node = this.create_node(key,value);
        
        List<Hash_Map_Node<Key_Type,Value_Type>> overflow_list = this.get_overflow_list(hash);
        
        int contains = overflow_list.index_of(node);
        
        if(contains != -1)
        {
            Hash_Map_Node<Key_Type,Value_Type> exists = overflow_list.get(contains);
            exists.value = value;
        }
        else
        {
            this.keys.add_front(key);
            overflow_list.add_front(node);
        }
    }
    
    /**
     * Clears hash maps and creates new hash table
     * that is of given size.
     * @param new_hash_table_size New size for hash table.
     */
    public void clear(int new_hash_table_size)
    {
        this.hash_table = new Object[new_hash_table_size];
        this.init_hahs_table();
        this.keys.clear();
    }
    
    /**
     * Return list that contains all keys in the hash map.
     * @return list that contains all keys in the hash map.
     */
    public List<Key_Type> keys()
    {
        return this.keys;
    }
    
    /**
     * Set comparator that can compare keys.
     * After comparator has been provided
     * set functions can be used.
     * @param comparator Comparator that set functions use.
     * Comparator compares keys.
     */
    public void set_comparator(Comparator<Key_Type> comparator)
    {
        this.keys.set_comparator(comparator);
    }
    
    // Testable Data Structure
    
    /**
     * Returns value that 
     * corresponds to given key.
     * @param key Key which value is retrieved.
     * @return Value that corresponds given key.
     * Null if key is not found.
     */
    @Override
    public Value_Type get(Key_Type key)
    {
        int hash = this.hash(key);
        Hash_Map_Node<Key_Type,Value_Type> node = this.create_node(key,null);
        
        List<Hash_Map_Node<Key_Type,Value_Type>> overflow_list = this.get_overflow_list(hash);
        
        int contains = overflow_list.index_of(node);
        if(contains != -1)
        {
            return overflow_list.get(contains).value;
        }
        return null;
    }
    
    /**
     * Adds new node to data structure
     * with given key and value.
     * @param key Key for new node.
     * @param value Value for new node.
     */
    @Override
    public void add(Key_Type key, Value_Type value) {
        this.put(key, value);
    }
    
    /**
     * Removes node with given key from the hash map.
     * If key is not found from the hash map does nothing.
     * @param key Key to be removed.
     */
    @Override
    public void remove(Key_Type key) {
        int hash = this.hash(key);
        List<Hash_Map_Node<Key_Type,Value_Type>> overflow_list = this.get_overflow_list(hash);
        Hash_Map_Node<Key_Type,Value_Type> node = this.find_node(hash, key, overflow_list);
        if(node != null)
        {
            overflow_list.remove(overflow_list.index_of(node));
            this.keys.remove(this.keys.index_of(key));
        }
    }
    
    /**
     * Return value of predecessor of node with given key.
     * Requires comparator!
     * @param key Key which predecessors value is retrieved.
     * * If key is not found return null.
     * @return Value of predecessor.
     */
    @Override
    public Value_Type predecessor(Key_Type key) {
        int key_index = this.keys.index_of(key);
        Key_Type pred_key = this.keys.predecessor(key_index);
        return this.get(pred_key);
    }
    
    /**
     * Return value of successor of node with given key.
     * Requires comparator!
     * @param key Key which successor value is retrieved.
     * If key is not found return null.
     * @return Value of successor.
     */
    @Override
    public Value_Type successor(Key_Type key) {
        int key_index = this.keys.index_of(key);
        Key_Type succ_key = this.keys.successor(key_index);
        return this.get(succ_key);
    }
    
    /**
     * Adds new node with given given key and
     * value that is same as the key.
     * Only works if key- and value types are the same.
     * If key already exists in the set replaces old entry
     * with new value.
     * @param key Key that is added also determines value.
     */
    @Override
    public void add_identical(Key_Type key) {
        this.add(key,(Value_Type)key);
    }
    
    /**
     * Return value of node that has smallest key in the hash map.
     * Requires comparator!
     * @return value of node that has smallest key in the hash map.
     */
    @Override
    public Value_Type min() {
        Key_Type min = this.keys.min();
        return this.get(min);
    }

    /**
     * Return value of node that has largest key in the hash map.
     * Requires comparator!
     * @return value of node that has largest key in the hash map.
     */
    @Override
    public Value_Type max() {
        Key_Type max = this.keys.max();
        return this.get(max);
    }
    
    /**
     * Sets root to null. This resets the hash map.
     */
    @Override
    public void clear() {
        this.clear(100);
    }
    
    /**
     * Return size of the hash map.
     * Requires comparator!
     * @return size of the hash map.
     */
    @Override
    public int size() {
        return this.keys.size();
    }
    
    /**
     * Checks whether hash map contains node that has given key.
     * Requires comparator!
     * @param key Key that is checked.
     * @return True if hash map contains key and false if doesn't.
     */
    @Override
    public boolean contains_key(Key_Type key) {
        return this.keys.contains(key);
    }
    
    /**
     * Checks whether hash map contains node that has given value.
     * Requires comparator!
     * @param value Value that is checked.
     * @return True if hash map contains node with given value
     * and false if doesn't.
     */
    @Override
    public boolean contains(Value_Type value) {
        this.keys.itr_reset();
        while(this.keys.itr_has_next())
        {
            if(value.equals(this.get(this.keys.itr_get())))
            {
                return true;
            }
            this.keys.itr_next();
        }
        return false;
    }
    
    // Iterator

    @Override
    public Key_Type itr_next() {
        return this.keys.itr_next();
    }

    @Override
    public void itr_remove() {
        this.remove(this.keys.itr_get());
    }

    @Override
    public boolean itr_has_next() {
        return this.keys.itr_has_next();
    }

    @Override
    public void itr_reset() {
        this.keys.itr_reset();
    }

    @Override
    public Key_Type itr_get() {
        return this.keys.itr_get();
    }
    
    // Private methods
    
    private void init_hahs_table()
    {
        for(int i = 0;i < this.hash_table.length;i ++)
        {
            List<Hash_Map_Node<Key_Type,Value_Type>> overflow_list = new List();
            this.hash_table[i] = overflow_list;
        }
    }
    
    private int hash(Key_Type key)
    {
        return key.hashCode() % this.hash_table.length;
    }
    
    private List<Hash_Map_Node<Key_Type,Value_Type>> get_overflow_list(int hash)
    {
        return (List<Hash_Map_Node<Key_Type,Value_Type>>)this.hash_table[hash];
    }
    
    private Hash_Map_Node<Key_Type,Value_Type> create_node(Key_Type key, Value_Type value)
    {
        return new Hash_Map_Node(key,value);
    }
    
    private Hash_Map_Node<Key_Type,Value_Type> find_node(int hash, Key_Type key, List<Hash_Map_Node<Key_Type,Value_Type>> overflow_list)
    {   
        int contains = overflow_list.index_of(this.create_node(key,null));
        if(contains != -1)
        {
            return overflow_list.get(contains);
        }
        return null;
    }
}
