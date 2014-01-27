/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author henrikorpela
 */
public class Window {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private JFrame win;
    
    public Window(String name, Diagram diagram)
    {
        this.win = new JFrame(name);
        this.inti_win(diagram);
    }
    
    public void dispose()
    {
        this.win.dispose();
    }
    
    private void inti_win(Diagram diagram)
    {
        this.win.setSize(WIDTH,HEIGHT);
        this.win.setPreferredSize(new Dimension(Window.WIDTH, Window.HEIGHT));
        this.win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.win.getContentPane().add(diagram);
        this.win.pack();
        this.win.setVisible(true);
        this.win.repaint();
    }
}
