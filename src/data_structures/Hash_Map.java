
package data_structures;

import java.util.Comparator;

/**
 * Hash map that has also many like set properties.
 * 
 * @author Henri Korpela
 */
public class Hash_Map<Key_Type,Value_Type> 
implements Testable_Data_Structure<Key_Type,Value_Type>, Iterator<Key_Type> {
    private Object[] hash_table;
    private List<Key_Type> keys;
    
    public Hash_Map(int hash_table_size)
    {
        this.hash_table = new Object[hash_table_size];
        this.init_hahs_table();
        this.keys = new List();
    }
    
    public void put(Key_Type key, Value_Type value)
    {
        int hash = this.hash(key);
        Hash_Map_Node<Key_Type,Value_Type> node = this.create_node(key,value);
        
        List<Hash_Map_Node<Key_Type,Value_Type>> overflow_list = this.get_overflow_list(hash);
        
        int contains = overflow_list.index_of(node);
        
        if(contains != -1)
        {
            this.keys.add_front(key);
            Hash_Map_Node<Key_Type,Value_Type> exists = overflow_list.get(contains);
            exists.value = value;
        }
        else
        {
            overflow_list.add_front(node);
        }
    }
    
    public void clear(int new_hash_table_size)
    {
        this.hash_table = new Object[new_hash_table_size];
        this.init_hahs_table();
        this.keys.clear();
    }
    
    public List<Key_Type> keys()
    {
        return this.keys;
    }
    
    public void set_comparator(Comparator<Key_Type> comparator)
    {
        this.keys.set_comparator(comparator);
    }
    
    // Testable Data Structure
    
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
    
    @Override
    public void add(Key_Type key, Value_Type value) {
        this.put(key, value);
    }

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

    @Override
    public Value_Type predecessor(Key_Type key) {
        int key_index = this.keys.index_of(key);
        Key_Type pred_key = this.keys.predecessor(key_index);
        return this.get(pred_key);
    }

    @Override
    public Value_Type successor(Key_Type key) {
        int key_index = this.keys.index_of(key);
        Key_Type succ_key = this.keys.successor(key_index);
        return this.get(succ_key);
    }

    @Override
    public Value_Type min() {
        Key_Type min = this.keys.min();
        return this.get(min);
    }

    @Override
    public Value_Type max() {
        Key_Type max = this.keys.max();
        return this.get(max);
    }

    @Override
    public void clear() {
        this.clear(100);
    }

    @Override
    public int size() {
        return this.keys.size();
    }

    @Override
    public boolean contains_key(Key_Type key) {
        return this.keys.contains(key);
    }

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
