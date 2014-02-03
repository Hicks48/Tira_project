
package data_structures;

import java.util.Comparator;

/**
 *
 * @author Henri Korpela
 */
public class AVL_Tree<Key_Type,Value_Type> implements Set {
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
        AVL_Tree_Node<Key_Type,Value_Type> new_node = this.insert((Key_Type)key, (Value_Type)value);
        AVL_Tree_Node<Key_Type,Value_Type> parent = new_node.parent;
        while(parent != null)
        {
            
        }
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
        AVL_Tree_Node<Key_Type,Value_Type> node_2 = node.right_child;
        node_2.parent = node.parent;
        node.parent = node_2;
        node.right_child = node_2.left_child;
        node_2.left_child = node;
        if(node.right_child != null)
        {
            node.right_child.parent = node;
        }
        node.heigth = this.max(this.heigth(node.left_child), this.heigth(node.right_child)) + 1;
        node_2.heigth = this.max(this.heigth(node_2.left_child), this.heigth(node_2.right_child)) + 1;
        return node_2;
    }
    
    private AVL_Tree_Node<Key_Type,Value_Type> rigth_rotate(AVL_Tree_Node<Key_Type,Value_Type> node)
    {
        AVL_Tree_Node<Key_Type,Value_Type> node_2 = node.left_child;
        node_2.parent = node.parent;
        node.parent = node_2;
        node.left_child = node_2.right_child;
        node_2.right_child = node;
        if(node.left_child != null)
        {
            node.left_child.parent = node;
        }
        node.heigth = this.max(this.heigth(node.left_child), this.heigth(node.right_child)) + 1;
        node_2.heigth = this.max(this.heigth(node_2.left_child), this.heigth(node_2.right_child)) + 1;
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
    
    private AVL_Tree_Node<Key_Type,Value_Type> insert(Key_Type key, Value_Type value)
    {
        AVL_Tree_Node<Key_Type,Value_Type> new_node = new AVL_Tree_Node<Key_Type,Value_Type>(key,value);
        
        if(this.root == null)
        {
            this.root = new_node;
            return new_node;
        }
        
        AVL_Tree_Node<Key_Type,Value_Type> node = this.root;
        while(true)
        {
            int compare = this.comparator.compare(new_node.key,node.key);
            if(compare == 0)
            {
                node.data = new_node.data;
                return null;
            }
            
            if(compare < 0)
            {
                if(node.left_child == null)
                {
                    node.left_child = new_node;
                    new_node.parent = node;
                    return new_node;
                }
                node = node.left_child;
                continue;
            }
            
            if(compare > 0)
            {
                if(node.right_child == null)
                {
                    node.right_child = new_node;
                    new_node.parent = node;
                    return new_node;
                }
                node = node.right_child;
                continue;
            }
        }
    }
    
    private boolean balance(AVL_Tree_Node<Key_Type,Value_Type> node)
    {
        if(this.abs(this.heigth(node.left_child) - this.heigth(node.right_child)) <= 1)
        {
            return true;
        }
        return false;
    }
    
    private int abs(int a)
    {
        if(a < 0)
        {
            return (-1)*a;
        }
        else
        {
            return a;
        }
    }
}
