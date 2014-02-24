
package data_structures;

import java.util.Comparator;

/**
 * AVL Tree data structure. AVL Tree implements Set interface.
 * All values in AVL Tree are stored with key and value like in a map.
 * User must provide a comparator for key types.
 * @author Henri Korpela
 */
public class AVL_Tree<Key_Type,Value_Type> implements Set<Key_Type,Value_Type> {
    /**
     * Root of the tree.
     */
    private AVL_Tree_Node<Key_Type,Value_Type> root;
    
    /**
     * Comparator that compares keys.
     */
    private Comparator<Key_Type> comparator;
    
    /**
     * Creates new tree that uses given comparator to compare keys.
     * @param comparator Comparator that is used to compare keys.
     */
    public AVL_Tree(Comparator<Key_Type> comparator)
    {
        this.comparator = comparator;
    }
    
    /**
     * Return string presentation of the tree.
     * Presentation is all keys in the tree in pre-order.
     * Nodes are separated with spaces. String also starts
     * and ends in space.
     * @return string presentation of the tree.
     */
    public String prerder()
    {
        String order = new String();
        return this.preorder(order,this.root) + " ";
    }
    
    /**
     * Returns value that 
     * corresponds to given key.
     * @param key Key which value is retrieved.
     * @return Value that corresponds given key.
     * Null if key is not found.
     */
    @Override
    public Value_Type get(Key_Type key) {
        AVL_Tree_Node<Key_Type,Value_Type> node = this.get_node((Key_Type)key);
        if(node == null)
        {
            return null;
        }
        return node.data;
    }
    
    /**
     * Adds new node to data structure
     * with given key and value.
     * @param key Key for new node.
     * @param value Value for new node.
     */
    @Override
    public void add(Key_Type key, Value_Type value) {
        AVL_Tree_Node<Key_Type,Value_Type> new_node = this.insert(key, value);
        if(new_node == null)
        {
            return;
        }
        AVL_Tree_Node<Key_Type,Value_Type> p = new_node.parent;
        while(p != null)
        {
            if(this.height(p.left_child) == this.height(p.right_child) + 2)
            {
                AVL_Tree_Node<Key_Type,Value_Type> parent = p.parent;
                AVL_Tree_Node<Key_Type,Value_Type> u_tree;
                if(this.height(p.left_child.left_child) > this.height(p.left_child.right_child))
                {
                    u_tree = this.rigth_rotate(p);
                }
                else
                {
                    u_tree = this.left_rigth_rotate(p);
                }
                if(parent == null)
                {
                    this.root = u_tree;
                }
                else if(parent.left_child == p)
                {
                    parent.left_child = u_tree;
                }
                else
                {
                    parent.right_child = u_tree;
                }
                if(parent != null)
                {
                    parent.heigth = this.max(this.height(parent.left_child),this.height(parent.right_child)) + 1;
                }
                return;
            }
            
            if(this.height(p.right_child) == this.height(p.left_child) + 2)
            {
                AVL_Tree_Node<Key_Type,Value_Type> parent = p.parent;
                AVL_Tree_Node<Key_Type,Value_Type> u_tree;
                if(this.height(p.right_child.right_child) > this.height(p.right_child.left_child))
                {
                    u_tree = this.left_rotate(p);
                }
                else
                {
                    u_tree = this.rigth_left_rotate(p);
                }
                if(parent == null)
                {
                    this.root = u_tree;
                }
                else if(parent.left_child == p)
                {
                    parent.left_child = u_tree;
                }
                else
                {
                    parent.right_child = u_tree;
                }
                if(parent != null)
                {
                    parent.heigth = this.max(this.height(parent.left_child), this.height(parent.right_child)) + 1;
                }
                return;
            }
            
            p.heigth = this.max(this.height(p.left_child), this.height(p.right_child)) + 1;
            p = p.parent;
        }
    }
    
    /**
     * Adds new node with given given key and
     * value that is same as the key.
     * Only works if key- and value types are the same.
     * If key already exists in the set replaces old entry
     * with new value.
     * @param key Key that is added also determines value.
     */
    @Override
    public void add_identical(Key_Type key) {
        this.add(key,(Value_Type)key);
    }
    
    /**
     * Return height of the tree.
     * @return height of the tree.
     */
    public int get_heigth()
    {
        return this.root.heigth;
    }
    
    /**
     * Removes node with given key from the tree.
     * If key is not found from the tree does nothing.
     * @param key Key to be removed.
     */
    @Override
    public void remove(Key_Type key) {
        AVL_Tree_Node<Key_Type,Value_Type> del = this.delete((Key_Type)key);
        if(del == null)
        {
            return;
        }
        AVL_Tree_Node<Key_Type,Value_Type> p = del.parent;
        while(p != null)
        {
            if(this.height(p.left_child) == this.height(p.right_child) + 2)
            {
                AVL_Tree_Node<Key_Type,Value_Type> parent = p.parent;
                AVL_Tree_Node<Key_Type,Value_Type> u_tree;
                if(this.height(p.left_child.left_child) > this.height(p.left_child.right_child))
                {
                    u_tree = this.rigth_rotate(p);
                }
                else
                {
                    u_tree = this.left_rigth_rotate(p);
                }
                if(parent == null)
                {
                    this.root = u_tree;
                    return;
                }
                else if(parent.left_child == p)
                {
                    parent.left_child = u_tree;
                }
                else
                {
                    parent.right_child = u_tree;
                }
                if(parent != null)
                {
                    parent.heigth = this.max(this.height(parent.left_child),this.height(parent.right_child)) + 1;
                }
                p = parent;
            }
            
            else if(this.height(p.right_child) == this.height(p.left_child) + 2)
            {
                AVL_Tree_Node<Key_Type,Value_Type> parent = p.parent;
                AVL_Tree_Node<Key_Type,Value_Type> u_tree;
                if(this.height(p.right_child.right_child) > this.height(p.right_child.left_child))
                {
                    u_tree = this.left_rotate(p);
                }
                else
                {
                    u_tree = this.rigth_left_rotate(p);
                }
                if(parent == null)
                {
                    this.root = u_tree;
                    return;
                }
                else if(parent.left_child == p)
                {
                    parent.left_child = u_tree;
                }
                else
                {
                    parent.right_child = u_tree;
                }
                if(parent != null)
                {
                    parent.heigth = this.max(this.height(parent.left_child), this.height(parent.right_child)) + 1;
                }
                p = parent;
            }
            
            else
            {
                p.heigth = this.max(this.height(p.left_child), this.height(p.right_child)) + 1;
                p = p.parent;
            }
        }
    }
    
    /**
     * Checks whether tree contains node that has given key.
     * @param key Key that is checked.
     * @return True if tree contains key and false if doesn't.
     */
    @Override
    public boolean contains_key(Key_Type key) {
        return (this.get(key) != null);
    }
    
    /**
     * Checks whether tree contains node that has given value.
     * @param value Value that is checked.
     * @return True if tree contains node with given value
     * and false if doesn't.
     */
    @Override
    public boolean contains(Value_Type value) {
        List<Key_Type> list = this.preorder_as_list(new List<Key_Type>(), this.root);
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
    
    /**
     * Sets root to null. This resets the set.
     */
    @Override
    public void clear() {
        this.root = null;
    }
    
    /**
     * Return size of the set.
     * @return size of the set.
     */
    @Override
    public int size() {
        return this.preorder_as_list(new List<Key_Type>(), this.root).size();
    }
    
    /**
     * Return value of predecessor of node with given key.
     * @param key Key which predecessors value is retrieved.
     * * If key is not found return null.
     * @return Value of predecessor.
     */
    @Override
    public Value_Type predecessor(Key_Type key) {
        AVL_Tree_Node<Key_Type,Value_Type> node = this.get_node(key);
        if(node == null)
        {
            return null;
        }
        if(node.left_child != null)
        {
            return this.max(node.left_child).data;
        }
        AVL_Tree_Node<Key_Type,Value_Type> parent = node.parent;
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
    
    /**
     * Return value of successor of node with given key.
     * @param key Key which successor value is retrieved.
     * If key is not found return null.
     * @return Value of successor.
     */
    @Override
    public Value_Type successor(Key_Type key) {
        AVL_Tree_Node<Key_Type,Value_Type> node = this.get_node(key);
        if(node.right_child != null)
        {
            return this.min(node.right_child).data;
        }
        
        AVL_Tree_Node<Key_Type,Value_Type> parent = node.parent;
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
    
    /**
     * Return value of node that has smallest key in the set.
     * @return value of node that has smallest key in the set.
     */
    @Override
    public Value_Type min() {
        AVL_Tree_Node<Key_Type,Value_Type> min = this.min(this.root);
        if(min == null)
        {
            return null;
        }
        return min.data;
    }
    
    /**
     * Return value of node that has largest key in the set.
     * @return value of node that has largest key in the set.
     */
    @Override
    public Value_Type max() {
        AVL_Tree_Node<Key_Type,Value_Type> max = this.max(this.root);
        if(max == null)
        {
            return null;
        }
        return max.data;
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
        node.heigth = this.max(this.height(node.left_child), this.height(node.right_child)) + 1;
        node_2.heigth = this.max(this.height(node_2.left_child), this.height(node_2.right_child)) + 1;
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
        node.heigth = this.max(this.height(node.left_child), this.height(node.right_child)) + 1;
        node_2.heigth = this.max(this.height(node_2.left_child), this.height(node_2.right_child)) + 1;
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
    
    private int height(AVL_Tree_Node<Key_Type,Value_Type> node)
    {
        if(node == null)
        {
            return -1;
        }
        return node.heigth;
    }

    
    private AVL_Tree_Node<Key_Type,Value_Type> insert(Key_Type key, Value_Type value)
    {
        AVL_Tree_Node<Key_Type,Value_Type> new_node = new AVL_Tree_Node(key,value);
        
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
    
    private AVL_Tree_Node<Key_Type,Value_Type> delete(Key_Type key)
    {
        AVL_Tree_Node<Key_Type,Value_Type> node = this.get_node(key);
        AVL_Tree_Node<Key_Type,Value_Type> return_this = node;
        // Tree doesn't contain node with given key
        if(node == null)
        {
            return null;
        }
        
        // Node doesn't have childs
        if(node.left_child == null && node.right_child == null)
        {
            AVL_Tree_Node<Key_Type,Value_Type> parent = node.parent;
            if(parent == null)
            {
                this.root = null;
                return return_this;
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
            AVL_Tree_Node<Key_Type,Value_Type> child;
            AVL_Tree_Node<Key_Type,Value_Type> parent;
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
                return return_this;
            }
            
            if(node.equals(parent.left_child))
            {
                parent.left_child = child;
            }
            
            else
            {
                parent.right_child = child;
            }
            return node;
        }
        
        // Node has two childs
        else if(node.right_child != null && node.left_child != null)
        {
            AVL_Tree_Node<Key_Type,Value_Type> next = this.max(node.left_child);
            // Copy nexts data to node
            node.key = next.key;
            node.data = next.data;
            
            AVL_Tree_Node<Key_Type,Value_Type> child = next.right_child;
            AVL_Tree_Node<Key_Type,Value_Type> parent = next.parent;
            
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
            return next;
        }
        return return_this;
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
    
    private AVL_Tree_Node<Key_Type,Value_Type> rigth_left_rotate(AVL_Tree_Node<Key_Type,Value_Type> node)
    {
        AVL_Tree_Node<Key_Type,Value_Type> node_2 = node.right_child;
        node.right_child = this.rigth_rotate(node_2);
        return this.left_rotate(node);
    }
    
    private AVL_Tree_Node<Key_Type,Value_Type> left_rigth_rotate(AVL_Tree_Node<Key_Type,Value_Type> node)
    {
        AVL_Tree_Node<Key_Type,Value_Type> node_2 = node.left_child;
        node.left_child = this.left_rotate(node_2);
        return this.rigth_rotate(node);
    }
    
    private AVL_Tree_Node<Key_Type,Value_Type> min(AVL_Tree_Node<Key_Type,Value_Type> start)
    {
        AVL_Tree_Node<Key_Type,Value_Type> node = start;
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
    
    private AVL_Tree_Node<Key_Type,Value_Type> max(AVL_Tree_Node<Key_Type,Value_Type> start)
    {
        AVL_Tree_Node<Key_Type,Value_Type> node = start;
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
    
    private AVL_Tree_Node<Key_Type,Value_Type> get_node(Key_Type key)
    {
        AVL_Tree_Node<Key_Type,Value_Type> node = this.root;
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
    
    private List<Key_Type> preorder_as_list(List<Key_Type> list, AVL_Tree_Node<Key_Type,Value_Type> node)
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

    private String preorder(String order, AVL_Tree_Node<Key_Type, Value_Type> node) {
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
    
    
}
