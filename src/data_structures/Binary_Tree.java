
package data_structures;

import java.util.Comparator;

/**
 * Binary search tree.
 * @author Henri Korpela
 */
public class Binary_Tree<Key_Type,Value_Type> 
implements Set<Key_Type,Value_Type>, data_structures.Iterator<Key_Type> {
    /**
     * Trees root node.
     */
    protected Binary_Tree_Node<Key_Type,Value_Type> root;
    /**
     * Comparator that compares keys.
     */
    protected Comparator<Key_Type> comparator;
    
    /**
     * Creates new binary search tree.
     * @param comparator Comparator that is used to comparator keys.
     */
    public Binary_Tree(Comparator<Key_Type> comparator)
    {
        this.root = null;
        this.comparator = comparator;
    }
    
    /**
     * Adds new node to tree with given key and value.
     * @param key Key for new node.
     * @param value Value for new node.
     */
    public void tree_add(Key_Type key,Value_Type value)
    {
        Binary_Tree_Node<Key_Type,Value_Type> new_node = new Binary_Tree_Node(key,value);
        
        if(this.root == null)
        {
            this.root = new_node;
            return;
        }
        
        Binary_Tree_Node<Key_Type,Value_Type> node = this.root;
        while(true)
        {
            int compare = this.comparator.compare(new_node.key,node.key);
            if(compare == 0)
            {
                node.data = new_node.data;
                return;
            }
            
            if(compare < 0)
            {
                if(node.left_child == null)
                {
                    node.left_child = new_node;
                    new_node.parent = node;
                    return;
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
                    return;
                }
                node = node.right_child;
                continue;
            }
        }
    }
    
    /**
     * Deletes node with given key from tree.
     * @param key Key to be deleted.
     */
    public void delete(Key_Type key)
    {
        Binary_Tree_Node<Key_Type,Value_Type> node = this.get_node(key);
        
        // Tree doesn't contain node with given key
        if(node == null)
        {
            return;
        }
        
        // Node doesn't have childs
        if(node.left_child == null && node.right_child == null)
        {
            Binary_Tree_Node<Key_Type,Value_Type> parent = node.parent;
            if(parent == null)
            {
                this.root = null;
                return;
            }
            if(node.equals(parent.left_child))
            {
                parent.left_child = null;
            }
            else
            {
                parent.right_child = null;
            }
        }
        
        // Node has one child
        else if(node.left_child == null || node.right_child == null)
        {
            Binary_Tree_Node<Key_Type,Value_Type> child;
            Binary_Tree_Node<Key_Type,Value_Type> parent;
            if(node. left_child != null)
            {
                child = node.left_child;
            }
            
            else
            {
                child = node.right_child;
            }
            parent = node.parent;
            child.parent = parent;
            
            if(parent == null)
            {
                this.root = child;
                return;
            }
            
            if(node.equals(parent.left_child))
            {
                parent.left_child = child;
            }
            
            else
            {
                parent.right_child = child;
            }
        }
        
        // Node has two childs
        else if(node.right_child != null && node.left_child != null)
        {
            Binary_Tree_Node<Key_Type,Value_Type> next = this.min(node.right_child);
            // Copy nexts data to node
            node.key = next.key;
            node.data = next.data;
            
            Binary_Tree_Node<Key_Type,Value_Type> child = next.right_child;
            Binary_Tree_Node<Key_Type,Value_Type> parent = next.parent;
            
            if(parent.left_child == next)
            {
                parent.left_child = child;
            }
            else
            {
                parent.right_child = child;
            }
            
            if(child != null)
            {
                child.parent = parent;
            }
        }
    }
    
    public String prerder()
    {
        String order = new String();
        return this.preorder(order,this.root) + " ";
    }
    
    /**
     * Return value that corresponds to given key.
     * @param key Key which value is retrieved.
     * @return Value corresponding given key.
     */
    @Override
    public Value_Type get(Key_Type key)
    {
        Binary_Tree_Node<Key_Type,Value_Type> node = this.get_node(key);
        if(node == null)
        {
            return null;
        }
        return node.data;
    }
    
    /**
     * Returns value of node which has smallest key.
     * @return Value of node that has smallest key.
     */
    @Override
    public Value_Type min()
    {
        Binary_Tree_Node<Key_Type,Value_Type> min = this.min(this.root);
        if(min == null)
        {
            return null;
        }
        return min.data;
    }
    
    /**
     * Returns smallest key that is in the tree.
     * @return Smallest key in the tree.
     */
    public Key_Type min_key()
    {
        Binary_Tree_Node<Key_Type,Value_Type> min = this.min(this.root);
        if(min == null)
        {
            return null;
        }
        return min.key;
    }
    
    /**
     * Return value of the node that has biggest key.
     * @return Value of node that has biggest key.
     */
    @Override
    public Value_Type max()
    {
        Binary_Tree_Node<Key_Type,Value_Type> max = this.max(this.root);
        if(max == null)
        {
            return null;
        }
        return max.data;
    }
    
    /**
     * Returns biggest key in the tree.
     * @return Biggest key in the tree.
     */
    public Key_Type max_key()
    {
        Binary_Tree_Node<Key_Type,Value_Type> max = this.max(this.root);
        if(max == null)
        {
            return null;
        }
        return max.key;
    }
    
    public Value_Type succ(Key_Type key)
    {
        Binary_Tree_Node<Key_Type,Value_Type> node = this.get_node(key);
        if(node == null)
        {
            return null;
        }
        if(node.right_child != null)
        {
            return this.min(node.right_child).data;
        }
        
        Binary_Tree_Node<Key_Type,Value_Type> parent = node.parent;
        while(parent != null && node == parent.right_child)
        {
            node = parent;
            parent = node.parent;
        }
        if(parent == null)
        {
            return null;
        }
        return parent.data;
    }
    
    public Key_Type succ_key(Key_Type key)
    {
        Binary_Tree_Node<Key_Type,Value_Type> node = this.get_node(key);
        if(node == null)
        {
            return null;
        }
        if(node.right_child != null)
        {
            return this.min(node.right_child).key;
        }
        
        Binary_Tree_Node<Key_Type,Value_Type> parent = node.parent;
        while(parent != null && node == parent.right_child)
        {
            node = parent;
            parent = node.parent;
        }
        if(parent == null)
        {
            return null;
        }
        return parent.key;
    }
    
    // Testable Data Structure
    
    @Override
    public void add(Key_Type key, Value_Type value) {
        this.tree_add(key, value);
    }

    @Override
    public void remove(Key_Type key) {
        this.delete(key);
    }

    @Override
    public Value_Type predecessor(Key_Type key) {
        Binary_Tree_Node<Key_Type,Value_Type> node = this.get_node(key);
        if(node == null)
        {
            return null;
        }
        if(node.left_child != null)
        {
            return this.max(node.left_child).data;
        }
        Binary_Tree_Node<Key_Type,Value_Type> parent = node.parent;
        while(parent != null && node == parent.left_child)
        {
            node = parent;
            parent = node.parent;
        }
        if(parent == null)
        {
            return null;
        }
        return parent.data;
    }

    @Override
    public Value_Type successor(Key_Type key) {
        return this.succ(key);
    }

    @Override
    public boolean contains_key(Key_Type key) {
        return this.preorder_as_list(new List<Key_Type>(),root).contains(key);
    }

    @Override
    public boolean contains(Value_Type value) {
        List<Key_Type> list = this.preorder_as_list(new List<Key_Type>(),root);
        list.itr_reset();
        while(list.itr_has_next())
        {
            if(this.get(list.itr_get()).equals(value))
            {
                return true;
            }
            list.itr_next();
        }
        return false;
    }

    @Override
    public void clear() {
        this.root = null;
    }

    @Override
    public int size() {
       return this.preorder_as_list(new List<Key_Type>(),root).size();
    }
    
    // Iterator
    
    @Override
    public Key_Type itr_next() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void itr_remove() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean itr_has_next() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void itr_reset() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Key_Type itr_get() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    // Private methods
    
    private List<Key_Type> preorder_as_list(List<Key_Type> list, Binary_Tree_Node<Key_Type,Value_Type> node)
    {
        if(node == null)
        {
            return list;
        }
        else
        {
            list.add(0, node.key);
        }
        this.preorder_as_list(list,node.left_child);
        this.preorder_as_list(list,node.right_child);
        return list;
    }
    
    private String preorder(String order, Binary_Tree_Node<Key_Type,Value_Type> node)
    {
        if(node == null)
        {
            return order;
        }
        else
        {
            order = order + " " + node.data.toString();
        }
        order = this.preorder(order,node.left_child);
        order = this.preorder(order,node.right_child);
        return order;
    }
    
    private Binary_Tree_Node<Key_Type,Value_Type> get_node(Key_Type key)
    {
        Binary_Tree_Node<Key_Type,Value_Type> node = this.root;
        while(node != null)
        {
            int compare = this.comparator.compare(key,node.key);
            if(compare == 0)
            {
                return node;
            }
            
            if(compare < 0)
            {
                node = node.left_child;
                continue;
            }
            
            if(compare > 0)
            {
                node = node.right_child;
                continue;
            }
        }
        return null;
    }
    
    private Binary_Tree_Node<Key_Type,Value_Type> min(Binary_Tree_Node<Key_Type,Value_Type> start)
    {
        Binary_Tree_Node<Key_Type,Value_Type> node = start;
        while(node != null)
        {
            if(node.left_child == null)
            {
                return node;
            }
            node = node.left_child;
        }
        return null;
    }
    
    private Binary_Tree_Node<Key_Type,Value_Type> max(Binary_Tree_Node<Key_Type,Value_Type> start)
    {
        Binary_Tree_Node<Key_Type,Value_Type> node = start;
        while(node != null)
        {
            if(node.right_child == null)
            {
                return node;
            }
            node = node.right_child;
        }
        return null;
    }
}
