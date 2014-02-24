
package data_structures;

import java.util.Comparator;

/**
 * Red Black Tree data structure. Red Black Tree implements Set interface.
 * All values in Red Black Tree are stored with key and value like in a map.
 * User must provide a comparator for key types.
 * @author Henri Korpela
 */
public class Red_Black_Tree<Key_Type, Value_Type> implements Set<Key_Type,Value_Type> {
    /**
     * Root of the tree.
     */
    private Red_Black_Tree_Node<Key_Type,Value_Type> root;
    
    /**
     * Comparator than can compare keys.
     */
    private Comparator<Key_Type> comparator;
    
    /**
     * Creates new tree that uses given comparator.
     * @param comparator 
     */
    public Red_Black_Tree(Comparator<Key_Type> comparator)
    {
        this.root = null;
        this.comparator = comparator;
    }
    
    /**
     * Resets tree by setting root to null.
     */
    @Override
    public void clear()
    {
        this.root = null;
    }
    
    private int size(Red_Black_Tree_Node node) 
    {
        if (node == null) 
        {
            return 0;
        }
        return node.sub_tree_count;
    }
    
    /**
     * Return size of the tree.
     * @return 
     */
    @Override
    public int size() 
    { 
        return size(root); 
    }
    
    /**
     * Determines whether tree is empty or not.
     * @return True if tree is empty and false if isn't.
     */
    public boolean isEmpty() 
    {
        return root == null;
    }
    
    /**
     * Return value of node with given key.
     * If tree doesn't contain given key
     * returns null.
     * @param key Key thats value is retrieved.
     * @return Value that corresponds given key. 
     */
    @Override
    public Value_Type get(Key_Type key) 
    {
        return get(root, key); 
    }

    private Value_Type get(Red_Black_Tree_Node<Key_Type,Value_Type> node, Key_Type key) 
    {
        while (node != null) 
        {
            int compare = this.comparator.compare(key, node.key);
            if(compare < 0) 
            {
                node = node.left;
            }
            else if(compare > 0) 
            {
                node = node.rigth;
            }
            else 
            {
                return node.value;
            }
        }
        return null;
    }
    
    /**
     * Checks whether tree contains node that has given key.
     * @param key Key that is checked.
     * @return True if tree contains key and false if doesn't.
     */
    @Override
    public boolean contains_key(Key_Type key) 
    {
        return (get(key) != null);
    }
    
    /**
     * Adds new node to the tree with given key and value.
     * If tree already contains node with given key replaces
     * old value with new one.
     * @param key Key for new node.
     * @param value Value for new node.
     */
    @Override
    public void add(Key_Type key, Value_Type value) 
    {
        root = insert(root, key, value);
        root.color = Red_Black.BLACK;
    }

    private Red_Black_Tree_Node<Key_Type,Value_Type> insert(Red_Black_Tree_Node<Key_Type,Value_Type> node, Key_Type key, Value_Type value) 
    { 
        if (node == null) 
        {
            return new Red_Black_Tree_Node(key, value, Red_Black.RED, 1);
        }

        int compare = this.comparator.compare(key, node.key);
        if(compare < 0) 
        { 
            node.left  = insert(node.left,  key, value);
        } 
        else if(compare > 0) 
        {
            node.rigth = insert(node.rigth, key, value);
        } 
        else 
        {
            node.value   = value;
        }

        if(Red_Black_Tree_Node.is_red(node.rigth) && !Red_Black_Tree_Node.is_red(node.left)) 
        {
            node = left_rotate(node);
        }
        if(Red_Black_Tree_Node.is_red(node.left)  &&  Red_Black_Tree_Node.is_red(node.left.left)) 
        {
            node = rigth_rotate(node);
        }
        if(Red_Black_Tree_Node.is_red(node.left)  &&  Red_Black_Tree_Node.is_red(node.rigth)) 
        {
            color_change(node);
        }
        node.sub_tree_count = size(node.left) + size(node.rigth) + 1;

        return node;
    }
    
    private Red_Black_Tree_Node<Key_Type,Value_Type> delete_min(Red_Black_Tree_Node<Key_Type,Value_Type> node) 
    { 
        if (node.left == null) 
        {
            return null;
        }

        if (!Red_Black_Tree_Node.is_red(node.left) && !Red_Black_Tree_Node.is_red(node.left.left)) 
        {
            node = shift_red_left(node);
        }

        node.left = delete_min(node.left);
        return balance(node);
    }
    
    /**
     * Removes node with given key from the tree.
     * If tree doesn't contain node with given key
     * does nothing.
     * @param key Key to be removed.
     */
    @Override
    public void remove(Key_Type key) 
    { 
        if (!contains_key(key)) 
        {
            return;
        }

        if (!Red_Black_Tree_Node.is_red(root.left) && !Red_Black_Tree_Node.is_red(root.rigth)) 
        {
            root.color = Red_Black.RED;
        }

        root = delete(root, key);
        if (!isEmpty()) 
        {
            root.color = Red_Black.BLACK;
        }
    }

    private Red_Black_Tree_Node<Key_Type,Value_Type> delete(Red_Black_Tree_Node<Key_Type,Value_Type> node, Key_Type key) 
    { 
        if (this.comparator.compare(key, node.key) < 0)  
        {
            if (!Red_Black_Tree_Node.is_red(node.left) && !Red_Black_Tree_Node.is_red(node.left.left)) 
            {
                node = shift_red_left(node);
            }
            node.left = delete(node.left, key);
        }
        else 
        {
            if (Red_Black_Tree_Node.is_red(node.left)) 
            {
                node = rigth_rotate(node);
            }
            if (this.comparator.compare(key, node.key) == 0 && (node.rigth == null)) 
            {
                return null;
            }
            if (!Red_Black_Tree_Node.is_red(node.rigth) && !Red_Black_Tree_Node.is_red(node.rigth.left)) 
            {
                node = shift_red_right(node);
            }
            if (this.comparator.compare(key, node.key) == 0) 
            {
                Red_Black_Tree_Node<Key_Type,Value_Type> n2 = min(node.rigth);
                node.key = n2.key;
                node.value = n2.value;
                node.rigth = delete_min(node.rigth);
            }
            else 
            {
                node.rigth = delete(node.rigth, key);
            }
        }
        return balance(node);
    }
    
    private Red_Black_Tree_Node<Key_Type,Value_Type> rigth_rotate(Red_Black_Tree_Node node) 
    {
        Red_Black_Tree_Node n2 = node.left;
        node.left = n2.rigth;
        n2.rigth = node;
        n2.color = n2.rigth.color;
        n2.rigth.color = Red_Black.RED;
        n2.sub_tree_count = node.sub_tree_count;
        node.sub_tree_count = size(node.left) + size(node.rigth) + 1;
        return n2;
    }

    private Red_Black_Tree_Node<Key_Type,Value_Type> left_rotate(Red_Black_Tree_Node node) 
    {
        Red_Black_Tree_Node n2 = node.rigth;
        node.rigth = n2.left;
        n2.left = node;
        n2.color = n2.left.color;
        n2.left.color = Red_Black.RED;
        n2.sub_tree_count = node.sub_tree_count;
        node.sub_tree_count = size(node.left) + size(node.rigth) + 1;
        return n2;
    }

    private void color_change(Red_Black_Tree_Node h) 
    {
        Red_Black_Tree_Node.change_color(h);
        Red_Black_Tree_Node.change_color(h.left);
        Red_Black_Tree_Node.change_color(h.rigth);
    }

    private Red_Black_Tree_Node<Key_Type,Value_Type> shift_red_left(Red_Black_Tree_Node node) 
    {
        color_change(node);
        if (Red_Black_Tree_Node.is_red(node.rigth.left)) { 
            node.rigth = rigth_rotate(node.rigth);
            node = left_rotate(node);
        }
        return node;
    }

    private Red_Black_Tree_Node<Key_Type,Value_Type> shift_red_right(Red_Black_Tree_Node node) 
    {
        color_change(node);
        if (Red_Black_Tree_Node.is_red(node.left.left)) 
        { 
            node = rigth_rotate(node);
        }
        return node;
    }

    private Red_Black_Tree_Node<Key_Type,Value_Type> balance(Red_Black_Tree_Node node) 
    {
        if (Red_Black_Tree_Node.is_red(node.rigth)) 
        {
            node = left_rotate(node);
        }
        if (Red_Black_Tree_Node.is_red(node.left) && Red_Black_Tree_Node.is_red(node.left.left)) 
        {
            node = rigth_rotate(node);
        }
        if (Red_Black_Tree_Node.is_red(node.left) && Red_Black_Tree_Node.is_red(node.rigth)) 
        {
            color_change(node);
        }

        node.sub_tree_count = size(node.left) + size(node.rigth) + 1;
        return node;
    }
    
    private Red_Black_Tree_Node<Key_Type,Value_Type> min(Red_Black_Tree_Node start) 
    {
        Red_Black_Tree_Node<Key_Type,Value_Type> node = start;
        while(node != null)
        {
            if(node.left == null)
            {
                return node;
            }
            node = node.left;
        }
        return null;
    }
    
    /**
     * Return string presentation of trees pre-order.
     * String contains spaces as first and last char
     * and nodes keys are divided with space.
     * @return trees pre-order presentation.
     */
    public String prerder()
    {
        String order = new String();
        return this.preorder(order,this.root) + " ";
    }
    
    private String preorder(String order, Red_Black_Tree_Node<Key_Type,Value_Type> node)
    {
        if(node == null)
        {
            return order;
        }
        else
        {
            order = order + " " + node.value.toString();
        }
        order = this.preorder(order,node.left);
        order = this.preorder(order,node.rigth);
        return order;
    }
    
    /**
     * Return value of predecessor of node with given key.
     * @param key Key which predecessors value is retrieved.
     * * If key is not found return null.
     * @return Value of predecessor.
     */
    @Override
    public Value_Type predecessor(Key_Type key) {
        List<Key_Type> list = this.preorder_as_list(new List<Key_Type>(), root);
        list.set_comparator(this.comparator);
        int index = list.index_of(key);
        if(index == -1)
        {
            return null;
        }
        else
        {
            return this.get(list.predecessor(index));
        }
    }
    
    /**
     * Return value of successor of node with given key.
     * @param key Key which successor value is retrieved.
     * If key is not found return null.
     * @return Value of successor.
     */
    @Override
    public Value_Type successor(Key_Type key) {
        List<Key_Type> list = this.preorder_as_list(new List<Key_Type>(), root);
        list.set_comparator(this.comparator);
        int index = list.index_of(key);
        if(index == -1)
        {
            return null;
        }
        else
        {
            return this.get(list.successor(index));
        }
    }
    
     /**
     * Return value of node that has smallest key in the set.
     * @return value of node that has smallest key in the set.
     */
    @Override
    public Value_Type min() {
        return this.min(this.root).value;
    }
    
    /**
     * Return value of node that has largest key in the set.
     * @return value of node that has largest key in the set.
     */
    @Override
    public Value_Type max() {
        Red_Black_Tree_Node<Key_Type,Value_Type> node = this.root;
        
        while(node != null)
        {
            if(node.rigth != null)
            {
                node = node.rigth;
            }
            else
            {
                break;
            }
        }
        if(node == null)
        {
            return null;
        }
        else
        {
            return node.value;
        }
    }
    
    /**
     * Checks whether set contains node that has given value.
     * @param value Value that is checked.
     * @return True if set contains node with given value
     * and false if doesn't.
     */
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
        this.add(key, (Value_Type)key);
    }
    
    private Red_Black_Tree_Node<Key_Type, Value_Type> get_node(Key_Type key)
    {
        Red_Black_Tree_Node<Key_Type, Value_Type> node = this.root;
        while(node != null)
        {
            int compare = this.comparator.compare(key,node.key);
            if(compare == 0)
            {
                return node;
            }
            
            if(compare < 0)
            {
                node = node.left;
                continue;
            }
            
            if(compare > 0)
            {
                node = node.rigth;
                continue;
            }
        }
        return null;
    }
    
    private List<Key_Type> preorder_as_list(List<Key_Type> list, Red_Black_Tree_Node<Key_Type, Value_Type> node)
    {
        if(node == null)
        {
            return list;
        }
        else
        {
            list.add(0, node.key);
        }
        this.preorder_as_list(list,node.left);
        this.preorder_as_list(list,node.rigth);
        return list;
    }
}