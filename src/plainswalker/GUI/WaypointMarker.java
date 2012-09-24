//Graphical representation of waypoint's world location
package plainswalker.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

import plainswalker.simulation.Vector3D;

public class WaypointMarker extends JComponent {

	static Dimension SIZE = new Dimension(10,10);
	private static final long serialVersionUID = 1L;
	
	public WaypointMarker(Vector3D pos){
		
		setLocation(pos);
		setSize(SIZE);
		
	}
	
	//Paint at waypoint location
	protected void paintComponent(Graphics g){
		
		super.paintComponent(g);
		
		((Graphics2D) g).setPaint(Color.BLUE);
		g.fillRect(SIZE.width/2-5, SIZE.height/2-5, 10, 10);

	}
	
	//Set center of graphic to animal position
	public void setLocation(Vector3D vec){
			
		super.setLocation(Math.round(vec.x) - (SIZE.width/2), Math.round(vec.y) - (SIZE.height/2));
			
	}
	
}
