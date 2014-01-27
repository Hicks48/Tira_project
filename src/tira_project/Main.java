/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tira_project;

import control.Controller;
import data_structures.Binary_Tree;
import data_structures.List;
import data_structures.Testable_Data_Structure;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;
import tester.Tester;

/**
 *
 * @author henrikorpela
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
         //Controller controller = new Controller(new Tester());
         //controller.run();
        
        /*
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
        */
        
        
        Tester tester = new Tester();
        List<Testable_Data_Structure> data_structures = new List();
        data_structures.add_front(new List<Integer>());
        tester.run("add",data_structures);
        System.out.print("Im Done :D !!!");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        
        
        /*
        JFrame win = new JFrame("test");
        win.setSize(800,600);
        win.pack();
        win.setVisible(true);
        */
        /*
        //1. Create the frame.
        JFrame frame = new JFrame("FrameDemo");
        frame.setPreferredSize(new Dimension(500,500));
        //2. Optional: What happens when the frame closes?
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.getContentPane().add(new JLabel(), BorderLayout.CENTER);
        
        //4. Size the frame.
        frame.pack();

        //5. Show it.
        frame.setVisible(true);
        */
    }
}
