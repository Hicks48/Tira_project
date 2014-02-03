
package data_structures;

import java.util.Objects;

/**
 * Node that AVL_Tree uses to store data.
 * Node contains references to its child nodes
 * and parent node. Node also knows its own height.
 * @author Henri Korpela
 */
public class AVL_Tree_Node<Key_Type,Value_Type> {
    /**
     * height of the node in the tree.
     */
    public int heigth;
    /**
     * Nodes key.
     */
    public Key_Type key;
    /**
     * Nodes value.
     */
    public Value_Type data;
    /**
     * Reference to nodes left child node.
     */
    public AVL_Tree_Node<Key_Type,Value_Type> left_child;
    /**
     * Reference to nodes right child node.
     */
    public AVL_Tree_Node<Key_Type,Value_Type> right_child;
    /**
     * Reference to node parent node.
     */
    public AVL_Tree_Node<Key_Type,Value_Type> parent;
    
    /**
     * Creates new node.
     * @param key Key for new node.
     * @param value Value for new node.
     */
    public AVL_Tree_Node(Key_Type key, Value_Type value)
    {
        this.key = key;
        this.data = value;
    }
    
    /**
     * Checks whether two nodes are equals.
     * Comparison is done using nodes keys.
     * @param o Node that this node is compared to.
     * @return True if nodes equal and false if aren't.
     */
    @Override
    public boolean equals(Object o)
    {
        if(o.getClass() != this.getClass())
        {
            return false;
        }
        AVL_Tree_Node<Key_Type,Value_Type> compare = (AVL_Tree_Node<Key_Type,Value_Type>)o;
        if(compare.key.equals(this.key))
        {
            return true;
        }
        return false;
    }
    
    /**
     * Generates hash code for
     * node using nodes key.
     * @return Hash code for node.
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.key);
        return hash;
    }
}
