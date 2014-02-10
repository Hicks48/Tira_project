
package data_structures;

import java.util.Comparator;

/**
 *
 * @author Henri Korpela
 */
public class Red_Black_Tree<Key_Type,Value_Type> implements Set<Key_Type,Value_Type>, Iterator<Key_Type> {
    private Red_Black_Tree_Node<Key_Type,Value_Type> root;
    private Comparator<Key_Type> comparator;
    
    public Red_Black_Tree(Comparator<Key_Type> comparator)
    {
        this.root = null;
        this.comparator = comparator;
    }
    
    @Override
    public Value_Type get(Key_Type key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void add(Key_Type key, Value_Type value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void remove(Key_Type key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Value_Type predecessor(Key_Type key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Value_Type successor(Key_Type key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Value_Type min() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Value_Type max() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean contains_key(Key_Type key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean contains(Value_Type value) {
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
    
}
