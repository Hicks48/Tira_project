/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data_structures;

/**
 *
 * @author henrikorpela
 */
public interface Testable_Data_Structure<Key_Type, Value_Type> {
    public Value_Type get(Key_Type key);
    public void add(Key_Type key,Value_Type value);
    public void remove(Key_Type key);
    public Value_Type predeccessor(Key_Type key);
    public Value_Type successor(Key_Type key);
    public Value_Type min();
    public Value_Type max();
    public boolean contains(Key_Type key);
    public void clear();
    public int size();
}
