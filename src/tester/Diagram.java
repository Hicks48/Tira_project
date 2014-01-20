/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import data_structures.Hash_Map;
import data_structures.Queue;
import data_structures.Testable_Data_Structure;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author henrikorpela
 */
public class Diagram extends JPanel{
    private Hash_Map<Testable_Data_Structure,Queue<Point>> graphs;
    
    public Diagram(Hash_Map<Testable_Data_Structure,Queue<Point>> graphs)
    {
        this.graphs = graphs;
    }
    
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        
    }
    
    private void draw_graph(Graphics g, Queue<Point> points)
    {
        for(int i = 0;i < points.size();i ++)
        {
            if(points.size() - 1 <= i + 1)
            {
                this.draw_line(g,points.get(i),points.get(i + 1));
            }
        }
    }
    
    private void draw_line(Graphics g, Point start, Point end)
    {
        
    }
}
