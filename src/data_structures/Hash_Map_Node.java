/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data_structures;

/**
 *
 * @author henrikorpela
 */
public class Hash_Map_Node<Key_Type,Value_Type> {
    public Key_Type key;
    public Value_Type value;
    
    public Hash_Map_Node(Key_Type key,Value_Type value)
    {
        this.key = key;
        this.value = value;
    }
    
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
}
