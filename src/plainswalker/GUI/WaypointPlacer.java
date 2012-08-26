//Graphical representation of waypoint's world location
package plainswalker.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JComponent;

public class WaypointPlacer extends JComponent {

	static Dimension SIZE = new Dimension(10,10);
	private static final long serialVersionUID = 1L;
	
	public WaypointPlacer(Point p){
		
		setLocation(p);
		setSize(SIZE);
		
	}
	
	//Paint at waypoint location
	protected void paintComponent(Graphics g){
		
		super.paintComponent(g);
		
		((Graphics2D) g).setPaint(Color.BLUE);
		g.fillRect(SIZE.width/2-5, SIZE.height/2-5, 10, 10);

	}
	
}
