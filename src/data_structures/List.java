
package data_structures;

import java.util.Comparator;

/**
 * List that also has queue type methods like pop and peak last and first.
 * Queue type adding and popping and peaking works in time O(1).
 * List methods work in time O(n). List also implements Iterator
 * and therefore should be iterated through using iterator interface methods.
 * List implements Testable_Data_Structure interface and can be used
 * in performance tests. Requires that integer is used as key type.
 * List uses given objects equals and compare methods. Max, min, predecessor
 * and successor use compare method of Comparator. Comparator can be provided for the list.
 * Accuracy of successor and predecessor depend on the accuracy of the given Comparator.
 * If comparator is not provided max, min, successor, predecessor return null. 
 * If multiple duplicates are added to the list.
 * It's not defined which duplicate methods use.
 * @author Henri Korpela
 */
public class List<Type> implements Set<Integer,Type>, Iterator<Type> {
    /**
     * First node in the list. Used to make queue operations
     * work in time O(1).
     */
    private List_Node<Type> first;
    /**
     * Last Last node in the list. Used to make queue operations
     * work in time O(1).
     */
    private List_Node<Type> last;
    /**
     * Size of the list. Used to make size method work in time O(1).
     */
    private int size;
    /**
     * Comparator that is used to compare data in min, max,
     * predecessor and successor methods.
     */
    private Comparator<Type> comparator;
    /**
     * Node that is used implementing iterator interface methods.
     */
    private List_Node<Type> iterator_node;
    
    /**
     * Creates new empty list.
     */
    public List()
    {
        this.size = 0;
        this.first = null;
        this.last = null;
    }
    
    /**
     * Adds given object to the end of the list.
     * Works in time O(1).
     * @param object Object to be added.
     */
    public void add_behind(Type object)
    {
        List_Node<Type> new_node = new List_Node(object);
        this.size ++;
        
        if(this.add_first(new_node))
        {
            return;
        }
        
        new_node.next = this.last;
        this.last.prev = new_node;
        
        this.last = new_node;
    }
    
    /**
     * Adds given object to the start of the list.
     * Works in time O(1).
     * @param object Object to be added.
     */
    public void add_front(Type object)
    {
        List_Node<Type> new_node = new List_Node(object);
        this.size ++;
        
        if(this.add_first(new_node))
        {
            return;
        }
        
        new_node.prev = this.first;
        this.first.next = new_node;
        
        this.first = new_node;
    }
    
    /**
     * Returns first element in the list and removes it from the list.
     * Works in time O(1).
     * @return First element in the list.
     */
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
    
    /**
     * Returns last element in the list and removes it from the list.
     * Works in time O(1).
     * @return Last element in the list.
     */
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
    
    /**
     * Return first element in the list. Element is not removed from the list.
     * Works in time O(1).
     * @return First element in the list.
     */
    public Type peak_first()
    {
        if(this.is_empty())
        {
            return null;
        }
        return this.first.data;
    }
    
    /**
     * Return last element in the list. Element is not removed from the list.
     * Works in time O(1).
     * @return Last element in the list.
     */
    public Type peak_last()
    {
        if(this.is_empty())
        {
            return null;
        }
        return this.last.data;
    }
    
    /**
     * Returns object that is in given index.
     * @param index Index where objects is retrieved.
     * @return object in given index.
     */
    public Type get(int index)
    {
        List_Node<Type> node = this.first;
        int current_index = 0;
        
        while(node != null && current_index <= index)
        {
            if(current_index == index)
            {
                return node.data;
            }
            node = node.prev;
            current_index ++;
        }
        
        return null;
    }
    
    /**
     * Return index of given object. Returns -1 if
     * objects is not in the list.
     * @param object Object which index is to be found.
     * @return Index of given object or -1 if object is not in the list.
     */
    public int index_of(Type object)
    {
        List_Node<Type> node = this.first;
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
    
    /**
     * Checks whether list is empty or not.
     * @return True if list is empty and false if isn't.
     */
    public boolean is_empty()
    {
        if(this.first == null)
        {
            return true;
        }
        return false;
    }
    
    /**
     * Set comparator for the list.
     * @param comparator Comparator that list is to use.
     */
    public void set_comparator(Comparator<Type> comparator)
    {
        this.comparator = comparator;
    }
    
    /**
     * String presentation of the list.
     * @return String presentation of the list.
     */
    @Override
    public String toString()
    {
        String content = " ";
        
        List_Node<Type> node = this.first;
        
        while(node != null)
        {
            content = content + node.data.toString() + " ";
            node = node.prev;
        }
        
        return content;
    }
    
    // Testable data structure
    
    /**
     * Return size of the list.
     * @return Size of the list.
     */
    @Override
    public int size()
    {
        return this.size;
    }
    
    /**
     * Retrieves given object from list.
     * @param key Index of object to be retrieved.
     * @return Object in given index and null if index is out of list.
     */
    @Override
    public Type get(Integer key) {
        int index = (Integer)key;
        return this.get(index);
    }
    
    /**
     * Adds given object to the start of the list.
     * Works in time O(n).
     * @param key Index that node is added. If 1 or smaller
     * node is added to first in the list and if bigger than
     * lists size node is added to the end of the list.
     * @param value Value that is stored in the list.
     */
    @Override
    public void add(Integer key, Type value) {
        if(key >= this.size)
        {
            this.add_behind(value);
            return;
        }
        else if(key <= 1)
        {
            this.add_front(value);
            return;
        }
        
        List_Node<Type> node  = new List_Node(value);
        this.size ++;
        List_Node<Type> counter = this.first;
        for(int i = 0;i < key;i ++)
        {
            counter = counter.prev;
        }
        List_Node<Type> before = counter;
        List_Node<Type> after = counter.prev;
        
        node.next = before;
        node.prev = after;
        
        before.prev = node;
        after.next = node;
    }
    
    /**
     * Removes data in given index from the list.
     * @param key Index of the object that is removed.
     */
    @Override
    public void remove(Integer key) {
        int index = (int)key;
        List_Node<Type> node = this.first;
        int i = 0;
        while(node != null && index != i)
        {
            node = node.prev;
            i ++;
        }
        this.remove_node(node);
    }
    
    /**
     * Return predecessor for data in the given index.
     * @param key index of data which successor is looked up.
     * @return Predecessor of data that is in given index.
     */
    @Override
    public Type predecessor(Integer key) {
        if(this.comparator == null)
        {
            return null;
        }
        
        Type data = this.get(key);
        Type predecessor = this.first.data;
        int min_difference = Integer.MIN_VALUE;
        List_Node<Type> node = this.first;
        
        while(node != null)
        {
            if(min_difference > 0)
            {
                min_difference = Integer.MIN_VALUE;
            }
            
            int compare = this.comparator.compare(node.data,data);
            if(compare < 0 && compare > min_difference)
            {
                predecessor = node.data;
                min_difference = compare;
            }
            node = node.prev;
        }
        
        if(this.comparator.compare(data,predecessor) <= 0)
        {
            return null;
        }
        return predecessor;
    }
    
    /**
     * Return successor for data in the given index.
     * @param key index of data which successor is looked up.
     * @return Successor of data that is in given index.
     */
    @Override
    public Type successor(Integer key) {
        if(this.comparator == null)
        {
            return null;
        }
        
        Type data = this.get(key);
        Type successor = this.first.data;
        int min_difference = Integer.MAX_VALUE;
        List_Node<Type> node = this.first;
        
        while(node != null)
        {
            if(min_difference < 0)
            {
                min_difference = Integer.MAX_VALUE;
            }
            int compare = this.comparator.compare(node.data,data);
            if(compare > 0 && compare < min_difference)
            {
                successor = node.data;
                min_difference = compare;
            }
            node = node.prev;
        }
        
        if(this.comparator.compare(data,successor) >= 0)
        {
            return null;
        }
        return successor;
    }
    
    /**
     * Return min value in the list.
     * @return Min value in the list.
     */
    @Override
    public Type min() {
        if(this.comparator == null)
        {
            return null;
        }
        Type min = this.first.data;
        List_Node<Type> node = this.first;
        while(node != null)
        {
            int compare = this.comparator.compare(min, node.data);
            if(compare > 0)
            {
                min = node.data;
            }
            node = node.prev;
        }
        return min;
    }
    
    /**
     * Return max value in the list.
     * @return Max value in the list.
     */
    @Override
    public Type max() {
        if(this.comparator == null)
        {
            return null;
        }
        Type max = this.first.data;
        List_Node<Type> node = this.first;
        while(node != null)
        {
            int compare = this.comparator.compare(max, node.data);
            if(compare < 0)
            {
                max = node.data;
            }
            node = node.prev;
        }
        return max;
    }
    
    /**
     * Checks whether list contains given index.
     * @param value Check if this index is in the list.
     * @return True if list contains given index false if doesn't.
     */
    @Override
    public boolean contains_key(Integer key) {
        if(key >= 0 && key < this.size)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * Checks whether list contains given object.
     * @param value Check if this object is in the list.
     * @return True if list contains given object false if doesn't.
     */
    @Override
    public boolean contains(Type value) {
        int contains = this.index_of(value);
        if(contains == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    /**
     * Clears list. Removes all data from the list.
     */
    @Override
    public void clear()
    {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    // Iterator
    
    /**
     * Removes data that iterator is currently in and moves
     * to next node in the list.
     */
    @Override
    public void itr_remove() {
        if(this.iterator_node == null)
        {
            return;
        }
        List_Node<Type> node = this.iterator_node;
        this.iterator_node = this.iterator_node.prev;
        this.remove_node(node);
    }
    
    /**
     * Checks whether iterator has next node to move to.
     * @return True if iterator has data left and false if hasn't.
     */
    @Override
    public boolean itr_has_next() {
        if(this.iterator_node == null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    /**
     * Returns data where iterator is currently in 
     * and moves to next node in the list.
     * @return Data where iterator is currently in.
     */
    @Override
    public Type itr_next() {
        if(this.iterator_node == null)
        {
            this.iterator_node = this.first;
            return this.iterator_node.data;
        }
        this.iterator_node = this.iterator_node.prev;
        if(this.iterator_node == null)
        {
                return null;
        }
        return this.iterator_node.data;
    }
    
    /**
     * Resets iterator. After this method 
     * iterator points to the start of the list.
     */
    @Override
    public void itr_reset()
    {
        this.iterator_node = this.first;
    }
    
    /**
     * Return data of node where 
     * @return 
     */
    @Override
    public Type itr_get()
    {
        if(this.iterator_node == null)
        {
            this.iterator_node = this.first;
            return this.iterator_node.data;
        }
        return this.iterator_node.data;
    }
    
    // Private methods
    
    private boolean add_first(List_Node<Type> new_node)
    {
        boolean empty = this.is_empty();
        
        if(empty)
        {
            this.first = new_node;
            this.last = new_node;
        }
        
        return empty;
    }
    
    private void remove_node(List_Node<Type> node)
    {
        if(node == null)
        {
            return;
        }
        if(this.size == 1)
        {
            this.clear();
            return;
        }
        else if(this.first == node)
        {
            List_Node<Type> new_first = node.prev;
            new_first.next = null;
            node.prev = null;
            this.first = new_first;
        }
        else if(this.last == node)
        {
            List_Node<Type> new_last = node.next;
            new_last.prev = null;
            node.next = null;
            this.last = new_last;
        }
        else
        {
            List_Node<Type> next_node = node.next;
            List_Node<Type> prev_node = node.prev;
            
            next_node.prev = prev_node;
            prev_node.next = next_node;
            
            node.next = null;
            node.prev = null;
        }
        this.size --;
    }
}
