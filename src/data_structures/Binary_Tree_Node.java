/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data_structures;

/**
 *
 * @author henrikorpela
 */
public class Binary_Tree_Node<Key_Type,Value_Type> {
    public Binary_Tree_Node<Key_Type,Value_Type> left_child;
    public Binary_Tree_Node<Key_Type,Value_Type> right_child;
    public Binary_Tree_Node<Key_Type,Value_Type> parent;
    public Key_Type key;
    public Value_Type data;
    
    public Binary_Tree_Node(Key_Type key,Value_Type data)
    {
        this.key = key;
        this.data = data;
        this.left_child = null;
        this.right_child = null;
        this.parent = null;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if(o == null)
        {
            return false;
        }
        if(o.getClass() != this.getClass())
        {
            return false;
        }
        Binary_Tree_Node<Key_Type,Value_Type> node = (Binary_Tree_Node<Key_Type,Value_Type>)o;
        if(node.key.equals(this.key))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
