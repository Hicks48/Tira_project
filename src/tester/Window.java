/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import javax.swing.JFrame;

/**
 *
 * @author henrikorpela
 */
public class Window extends JFrame{
    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    
    public Window(String name, Diagram diagram)
    {
        super(name);
        this.inti_win(diagram);
    }
    
    private void inti_win(Diagram diagram)
    {
        this.setSize(WIDTH,HEIGHT);
        this.getContentPane().add(diagram);
        this.setVisible(true);
    }
}
