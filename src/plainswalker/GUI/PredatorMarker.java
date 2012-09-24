package plainswalker.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

import plainswalker.simulation.Predator;
import plainswalker.simulation.Vector3D;

public class PredatorMarker extends JComponent{

	public static Dimension SIZE = new Dimension(Predator.avoidRad*2, Predator.avoidRad*2);
	private static final long serialVersionUID = 1L;
	
	//Set up animal
	public PredatorMarker(Vector3D pos){
			
		setLocation(pos);
		setSize(SIZE);
			
	}
	
	//Paint at animal location
	protected void paintComponent(Graphics g){
			
		super.paintComponent(g);
			
		((Graphics2D) g).setPaint(Color.RED);
		g.fillRect(SIZE.width/2-5, SIZE.height/2-5, 10, 10);
		g.drawArc(0, 0, SIZE.width-1, SIZE.height-1, 0, 360);

	}
	
	//Set center of graphic to animal position
	public void setLocation(Vector3D vec){
			
		super.setLocation(Math.round(vec.x) - (SIZE.width/2), Math.round(vec.y) - (SIZE.height/2));
			
	}

}
