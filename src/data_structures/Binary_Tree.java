/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data_structures;

/**
 *
 * @author henrikorpela
 */
public class Binary_Tree<Key_Type extends Comparable<Key_Type>,Value_Type> {
    private Binary_Tree_Node<Key_Type,Value_Type> root;
    
    public Binary_Tree()
    {
        this.root = null;
    }
    
    public void add(Key_Type key,Value_Type value)
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
            int compare = new_node.key.compareTo(node.key);
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
            parent = child.parent;
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
            
            if(parent.left_child.equals(next))
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
    
    public Value_Type get(Key_Type key)
    {
        Binary_Tree_Node<Key_Type,Value_Type> node = this.get_node(key);
        if(node == null)
        {
            return null;
        }
        return node.data;
    }
    
    public Value_Type min()
    {
        Binary_Tree_Node<Key_Type,Value_Type> min = this.min(this.root);
        if(min == null)
        {
            return null;
        }
        return min.data;
    }
    
    public Key_Type min_key()
    {
        Binary_Tree_Node<Key_Type,Value_Type> min = this.min(this.root);
        if(min == null)
        {
            return null;
        }
        return min.key;
    }
    
    public Value_Type max()
    {
        Binary_Tree_Node<Key_Type,Value_Type> max = this.max(this.root);
        if(max == null)
        {
            return null;
        }
        return max.data;
    }
    
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
        return parent.key;
    }
    
    public void preorder()
    {
        this.preorder(null, root);
    }
    
    private void preorder(String order,Binary_Tree_Node<Key_Type,Value_Type> node)
    {
        if(node == null)
        {
            return;
        }
        this.preorder(order,node.left_child);
        this.preorder(order,node.right_child);
        System.out.print(" " + node.data);
    }
    
    private Binary_Tree_Node<Key_Type,Value_Type> get_node(Key_Type key)
    {
        Binary_Tree_Node<Key_Type,Value_Type> node = this.root;
        while(node != null)
        {
            int compare = key.compareTo(node.key);
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
        Binary_Tree_Node<Key_Type,Value_Type> node = this.root;
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
