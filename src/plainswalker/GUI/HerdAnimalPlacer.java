//Graphic representation of an animal's world location

package plainswalker.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JComponent;

import plainswalker.simulation.HerdAnimal;
import plainswalker.simulation.Vector3D;

public class HerdAnimalPlacer extends JComponent{

	static Dimension SIZE = new Dimension(HerdAnimal.neighbourRad, HerdAnimal.neighbourRad);
	private static final long serialVersionUID = 1L;
	protected HerdAnimal animal;
	
	//Set up animal
	public HerdAnimalPlacer(Point p){
		
		animal = new HerdAnimal(new Vector3D(p.x, p.y, 0), this);
		setLocation(p);
		setSize(SIZE);
		
	}
	
	//Paint at animal location
	protected void paintComponent(Graphics g){
		
		super.paintComponent(g);
		
		((Graphics2D) g).setPaint(Color.RED);
		g.drawArc((SIZE.width-HerdAnimal.avoidRad)/2, (SIZE.height-HerdAnimal.avoidRad)/2, HerdAnimal.avoidRad-1, HerdAnimal.avoidRad-1, 0, 360);
		((Graphics2D) g).setPaint(Color.GREEN);
		g.fillRect(SIZE.width/2-5, SIZE.height/2-5, 10, 10);
		g.drawArc(0, 0, SIZE.width-1, SIZE.height-1, 0, 360);

	}

}
