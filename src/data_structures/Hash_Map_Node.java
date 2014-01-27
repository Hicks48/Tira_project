
package data_structures;

import java.util.Objects;

/**
 * Node that hash map uses to store data.
 * @author Henri Korpela
 */
public class Hash_Map_Node<Key_Type,Value_Type> {
    /**
     * Nodes key.
     */
    public Key_Type key;
    /**
     * Nodes value.
     */
    public Value_Type value;
    
    /**
     * Creates new node that has given key and value.
     * @param key Key for new node.
     * @param value Value for new node.
     */
    public Hash_Map_Node(Key_Type key,Value_Type value)
    {
        this.key = key;
        this.value = value;
    }
    
    /**
     * Checks whether two nodes are equal using
     * nodes key.
     * @param o Node that this node is compared to.
     * @return True if nodes are equal and false if aren't.
     */
    @Override
    public boolean equals(Object o)
    {
        if(o.getClass() != this.getClass())
        {
            return false;
        }
        
        Hash_Map_Node<Key_Type,Value_Type> another_node = (Hash_Map_Node<Key_Type,Value_Type>)o;
        
        if(another_node.key.equals(this.key))
        {
            return true;
        }
        return false;
    }
    
    /**
     * Generates hash code for node
     * using nodes key.
     * @return Hash code for node.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.key);
        return hash;
    }
}
