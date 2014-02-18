
package data_structures;

/**
 * Node that Red Black Tree uses to store data. Node contains information
 * about its key, value, left and right children, its color and how many nodes
 * its subtrees contain. Class also has two static functions. One determines whether
 * given nodes color is red, one changes given nodes color to opposite color and 
 * one return amount of nodes in given nodes subtrees.
 * @author Henri Korpela
 */
public class Red_Black_Tree_Node <Key_Type,Value_Type> {
    /**
     * Nodes key.
     */
    public Key_Type key;
    
    /**
     * Nodes value.
     */
    public Value_Type value;
    
    /**
     * Nodes left child.
     */
    public Red_Black_Tree_Node left;
    
    /**
     * Nodes right child.
     */
    public Red_Black_Tree_Node rigth;
    
    /**
     * Nodes color.
     * Color is either red or black.
     */
    public Red_Black color;
    
    /**
     * Amount of nodes in nodes subtrees.
     */
    public int sub_tree_count;
    
    /**
     * Creates new node with given key, value, color and subtree count.
     * @param key Key for new node.
     * @param value Value for new node.
     * @param color Color for new node.
     * @param sub_trees Amount of nodes in new nodes subtrees.
     */
    public Red_Black_Tree_Node(Key_Type key, Value_Type value, Red_Black color, int sub_trees)
    {
        this.key = key;
        this.value = value;
        this.sub_tree_count = sub_trees;
        this.color = color;
    }
    
    /**
     * Determines whether given node is red or not.
     * @param node Node thats color is checked.
     * @return True if nodes color is red and false f color is black.
     * if node is null return false.
     */
    public static boolean is_red(Red_Black_Tree_Node node)
    {
        if(node == null)
        {
            return false;
        }
        if(node.color == Red_Black.RED)
        {
            return true;
        }
        return false;
    }
    
    /**
     * Return the amount of nodes in given nodes subtrees.
     * @param node Node which subtree count is returned.
     * @return amount of nodes in given nodes subtrees.
     * If node is null return 0.
     */
    public static int size(Red_Black_Tree_Node node)
    {
        if(node == null)
        {
            return 0;
        }
        return node.sub_tree_count;
    }
    
    /**
     * Changes color of given node to the opposite color.
     * @param node Node thats color is changed.
     */
    public static void change_color(Red_Black_Tree_Node node)
    {
        if(node == null)
        {
            return;
        }
        if(node.color == Red_Black.RED)
        {
            node.color = Red_Black.BLACK;
        }
        else
        {
            node.color = Red_Black.RED;
        }
    }
}

