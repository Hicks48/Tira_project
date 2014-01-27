/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tester;

import data_structures.Hash_Map;
import data_structures.List;
import data_structures.Testable_Data_Structure;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;

/**
 *
 * @author henrikorpela
 */
public class Diagram extends JPanel{
    private long max_coordinate_x;
    private long max_coordinate_y;
    private Hash_Map<Testable_Data_Structure,List<Point>> graphs;
    private Random random;
    
    public Diagram(long max_coordinate_x, long max_coordinate_y, Hash_Map<Testable_Data_Structure,List<Point>> graphs)
    {
        this.graphs = graphs;
        this.max_coordinate_x = max_coordinate_x;
        this.max_coordinate_y = max_coordinate_y;
        this.setSize(Window.WIDTH,Window.HEIGHT);
        this.random = new Random();
    }
    
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        List<Testable_Data_Structure> data_structs = this.graphs.keys();
        for(int i = 0;i < data_structs.size();i ++)
        {
            this.draw_graph(g,this.graphs.get(data_structs.pop_first()));
        }
    }
    
    private void draw_graph(Graphics g, List<Point> points)
    {
        for(int i = 0;i < points.size();i ++)
        {
            if(points.size() - 1 <= i + 1)
            {
                g.setColor(this.random_color());
                this.draw_line(g,points.get(i),points.get(i + 1));
            }
        }
    }
    
    private void draw_line(Graphics g, Point start, Point end)
    {
        Point start_win_coord = this.result_to_win_coordinate(start);
        Point end_win_coord = this.result_to_win_coordinate(end);
        g.drawLine((int)start_win_coord.x, (int)start_win_coord.y, (int)end_win_coord.x, (int)end_win_coord.y);
    }
    
    private Point result_to_win_coordinate(Point result)
    {
        int win_x = (int)((Window.WIDTH * result.x) / this.max_coordinate_x);
        int win_y = (int)((Window.HEIGHT * result.y) / this.max_coordinate_y);
        return new Point(win_x,win_y);
    }
    
    private Color random_color()
    {
        return new Color(this.random.nextFloat(),this.random.nextFloat(),this.random.nextFloat());
    }
}
