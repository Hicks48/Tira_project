
package data_structures;

import java.util.Comparator;

/**
 *
 * @author Henri Korpela
 */
public class AVL_Tree<Key_Type,Value_Type> implements Testable_Data_Structure {
    private AVL_Tree_Node<Key_Type,Value_Type> root;
    private Comparator<Key_Type> comparator;
    
    public AVL_Tree(Comparator<Key_Type> comparator)
    {
        this.comparator = comparator;
    }
    
    @Override
    public Object get(Object key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void add(Object key, Object value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void remove(Object key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object predecessor(Object key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object successor(Object key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object min() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object max() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean contains_key(Object key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean contains(Object value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    // Private methods
    
    private AVL_Tree_Node<Key_Type,Value_Type> left_rotate(AVL_Tree_Node<Key_Type,Value_Type> node)
    {
        AVL_Tree_Node<Key_Type,Value_Type> node_2 = node.rigth_child;
        node_2.parent = node.parent;
        node.parent = node_2;
        node.rigth_child = node_2.left_child;
        node_2.left_child = node;
        if(node.rigth_child != null)
        {
            node.rigth_child.parent = node;
        }
        node.heigth = this.max(this.heigth(node.left_child), this.heigth(node.rigth_child)) + 1;
        node_2.heigth = this.max(this.heigth(node_2.left_child), this.heigth(node_2.rigth_child)) + 1;
        return node_2;
    }
    
    private AVL_Tree_Node<Key_Type,Value_Type> rigth_rotate(AVL_Tree_Node<Key_Type,Value_Type> node)
    {
        AVL_Tree_Node<Key_Type,Value_Type> node_2 = node.left_child;
        node_2.parent = node.parent;
        node.parent = node_2;
        node.left_child = node_2.rigth_child;
        node_2.rigth_child = node;
        if(node.left_child != null)
        {
            node.left_child.parent = node;
        }
        node.heigth = this.max(this.heigth(node.left_child), this.heigth(node.rigth_child)) + 1;
        node_2.heigth = this.max(this.heigth(node_2.left_child), this.heigth(node_2.rigth_child)) + 1;
        return node_2;
    }
    
    private int max(int a, int b)
    {
        if(a > b)
        {
            return a;
        }
        else
        {
            return b;
        }
    }
    
    private int heigth(AVL_Tree_Node<Key_Type,Value_Type> node)
    {
        if(node == null)
        {
            return -1;
        }
        return node.heigth;
    }
    
}
