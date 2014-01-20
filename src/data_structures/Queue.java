/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data_structures;

/**
 *
 * @author henrikorpela
 */
public class Queue<Type> {
    private Queue_Node<Type> first;
    private Queue_Node<Type> last;
    private int size;
    
    public Queue()
    {
        this.size = 0;
        this.first = null;
        this.last = null;
    }
    
    public void add_behind(Type object)
    {
        Queue_Node<Type> new_node = new Queue_Node(object);
        this.size ++;
        
        if(this.add_first(new_node))
        {
            return;
        }
        
        new_node.next = this.last;
        this.last.prev = new_node;
        
        this.last = new_node;
    }
    
    public void add_front(Type object)
    {
        Queue_Node<Type> new_node = new Queue_Node(object);
        this.size ++;
        
        if(this.add_first(new_node))
        {
            return;
        }
        
        new_node.prev = this.first;
        this.first.next = new_node;
        
        this.first = new_node;
    }
    
    public Type pop_first()
    {
        if(this.is_empty())
        {
            return null;
        }
        
        Type data = this.first.data;
        this.first = this.first.prev;
        
        if(this.first != null)
        {
            this.first.next = null;
        }
        
        this.size--;
        return data;
    }
    
    public Type pop_last()
    {
        if(this.is_empty())
        {
            return null;
        }
        
        Type data = this.last.data;
        this.last = this.last.next;
        
        if(this.last != null)
        {
            this.last.prev = null;
        }
        
        this.size--;
        return data;
    }
    
    public Type peak_first()
    {
        if(this.is_empty())
        {
            return null;
        }
        return this.first.data;
    }
    
    public Type peak_last()
    {
        if(this.is_empty())
        {
            return null;
        }
        return this.last.data;
    }
    
    public Type get(int index)
    {
        Queue_Node<Type> node = this.first;
        int current_index = 0;
        
        while(node != null && current_index <= index)
        {
            if(current_index == index)
            {
                return node.data;
            }
            node = node.prev;
        }
        
        return null;
    }
    
    public int contains(Type object)
    {
        Queue_Node<Type> node = this.first;
        int index = 0;
        
        while(node != null)
        {
            if(node.data.equals(object))
            {
                return index;
            }
            node = node.prev;
            index ++;
        }
        return -1;
    }
    
    public boolean is_empty()
    {
        if(this.first == null)
        {
            return true;
        }
        return false;
    }
    
    public void clear()
    {
        this.first = null;
        this.last = null;
        this.size = 0;
    }
    
    public int size()
    {
        return this.size;
    }
    
    @Override
    public String toString()
    {
        String content = " ";
        
        Queue_Node<Type> node = this.first;
        
        while(node != null)
        {
            content = content + node.data.toString() + " ";
            node = node.prev;
        }
        
        return content;
    }
    
    private boolean add_first(Queue_Node<Type> new_node)
    {
        boolean empty = this.is_empty();
        
        if(empty)
        {
            this.first = new_node;
            this.last = new_node;
        }
        
        return empty;
    }
}
