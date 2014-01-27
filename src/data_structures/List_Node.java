
package data_structures;

import java.util.Objects;

/**
 * Node that list class uses as its node.
 * Node stores data and holds references to
 * its 	neighbor nodes.
 * @author Henri Korpela
 */
public class List_Node<Type> {
    /**
     * Reference to next node.
     */
    public List_Node<Type> next;
    /**
     * Reference to previous node.
     */
    public List_Node<Type> prev;
    /**
     * Data that node holds.
     */
    public Type data;
    
    /**
     * Creates new list node object which holds given data.
     * @param data Data that node contains.
     */
    public List_Node(Type data)
    {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
    
    /**
     * Checks are two nodes equals by comparing data they contain.
     * @param o Object that node is compared to.
     * @return True if two nodes hold equal data.
     */
    @Override
    public boolean equals(Object o)
    {
        if(o.getClass() != this.getClass())
        {
            return false;
        }
        
        List_Node<Type> another_node = (List_Node<Type>)o;
         
        if(another_node.data.equals(this.data))
        {
            return true;
        }
        return false;
    }
    
    /**
     * Generates hash code using nodes data.
     * @return Hash code.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.data);
        return hash;
    }
}
