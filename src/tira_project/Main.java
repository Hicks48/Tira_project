/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tira_project;

import data_structures.Binary_Tree;

/**
 *
 * @author henrikorpela
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*
         * Contrroler controller = new Controller();
         * controller.run();
         */
        Binary_Tree<Integer,String> tree = new Binary_Tree();
        tree.add(1, "One");
        tree.add(3, "Tree");
        tree.add(6, "Six");
        tree.add(2, "Two");
        tree.add(5, "Five");
        tree.add(8, "Eigth");
        tree.add(10, "Ten");
        tree.preorder();
        System.out.print("\nMin: " + tree.min());
        System.out.print("\nMax: " + tree.max());
        System.out.print("\nSucc: " + tree.succ(10));
    }
}
