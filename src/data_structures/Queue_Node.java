/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data_structures;

/**
 *
 * @author henrikorpela
 */
public class Queue_Node<Type> {
    public Queue_Node<Type> next;
    public Queue_Node<Type> prev;
    public Type data;
    
    public Queue_Node(Type data)
    {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if(o.getClass() != this.getClass())
        {
            return false;
        }
        
        Queue_Node<Type> another_node = (Queue_Node<Type>)o;
         
        if(another_node.data.equals(this.data))
        {
            return true;
        }
        return false;
    }
}
