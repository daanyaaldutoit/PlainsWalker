//Graphic representation of an animal's world location

package plainswalker.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

import plainswalker.simulation.HerdAnimal;
import plainswalker.simulation.Vector3D;

public class HerdAnimalMarker extends JComponent{

	public static Dimension SIZE = new Dimension(HerdAnimal.neighbourRad*2, HerdAnimal.neighbourRad*2);
	protected boolean showARadius = false;
	protected boolean showNRadius = false;
	protected boolean selected = false;
	
	private static final long serialVersionUID = 1L;
	
	//Set up animal
	public HerdAnimalMarker(Vector3D pos, boolean showARad, boolean showNRad){
		
		setSize(SIZE);
		setLocation(pos);
		showARadius = showARad;
		showNRadius = showNRad;
		
	}
	
	//Paint at animal location
	protected void paintComponent(Graphics g){
		
		super.paintComponent(g);
		
		if(showARadius){
		
			((Graphics2D) g).setPaint(Color.RED);
			g.drawArc((SIZE.width/2-HerdAnimal.avoidRad), (SIZE.height/2-HerdAnimal.avoidRad), HerdAnimal.avoidRad*2-1, HerdAnimal.avoidRad*2-1, 0, 360);
		
		}
		
		if(selected){
			
			((Graphics2D) g).setPaint(Color.CYAN);
			g.fillRect(SIZE.width/2-10, SIZE.height/2-10, 20, 20);
			
		}
		
		((Graphics2D) g).setPaint(Color.GREEN);
		g.fillRect(SIZE.width/2-5, SIZE.height/2-5, 10, 10);
		
		if(showNRadius)
		g.drawArc(0, 0, SIZE.width-1, SIZE.height-1, 0, 360);

	}
	
	//Set center of graphic to animal position
	public void setLocation(Vector3D vec){
		
		super.setLocation(Math.round(vec.x) - (SIZE.width/2), Math.round(vec.y) - (SIZE.height/2));
		
	}

}
