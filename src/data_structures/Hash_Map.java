/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data_structures;

/**
 *
 * @author henrikorpela
 */
public class Hash_Map<Key_Type,Value_Type> {
    private Object[] hash_table;
    Queue<Hash_Map_Node<Key_Type,Value_Type>> all_nodes;
    
    public Hash_Map(int hash_table_size)
    {
        this.all_nodes = new Queue();
        this.hash_table = new Object[hash_table_size];
        this.init_hahs_table();
    }
    
    public void put(Key_Type key, Value_Type value)
    {
        int hash = this.hash(key);
        Hash_Map_Node<Key_Type,Value_Type> node = this.get_node(key,value);
        
        Queue<Hash_Map_Node<Key_Type,Value_Type>> overflow_list = this.get_overflow_list(hash);
        
        int contains = overflow_list.contains(node);
        
        if(contains != -1)
        {
            Hash_Map_Node<Key_Type,Value_Type> exists = overflow_list.get(contains);
            exists.value = value;
        }
        else
        {
            overflow_list.add_front(node);
        }
    }
    
    public Value_Type get(Key_Type key)
    {
        int hash = this.hash(key);
        Hash_Map_Node<Key_Type,Value_Type> node = this.get_node(key,null);
        
        Queue<Hash_Map_Node<Key_Type,Value_Type>> overflow_list = this.get_overflow_list(hash);
        
        int contains = overflow_list.contains(node);
        if(contains != -1)
        {
            return overflow_list.get(contains).value;
        }
        return null;
    }
    
    public void clear(int new_hash_table_size)
    {
        this.all_nodes.clear();
        this.hash_table = new Object[new_hash_table_size];
        this.init_hahs_table();
    }
    
    private void init_hahs_table()
    {
        for(int i = 0;i < this.hash_table.length;i ++)
        {
            Queue<Hash_Map_Node<Key_Type,Value_Type>> overflow_list = new Queue();
            this.hash_table[i] = overflow_list;
        }
    }
    
    private int hash(Key_Type key)
    {
        return key.hashCode() % this.hash_table.length;
    }
    
    private Queue<Hash_Map_Node<Key_Type,Value_Type>> get_overflow_list(int hash)
    {
        return (Queue<Hash_Map_Node<Key_Type,Value_Type>>)this.hash_table[hash];
    }
    
    private Hash_Map_Node<Key_Type,Value_Type> get_node(Key_Type key, Value_Type value)
    {
        return new Hash_Map_Node(key,value);
    }
}
