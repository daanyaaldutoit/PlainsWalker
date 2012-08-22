//Graphic representation of an animal's world location

package plainswalker.GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JComponent;

import plainswalker.simulation.HerdAnimal;

public class HerdAnimalPlacer extends JComponent{

	private static final long serialVersionUID = 1L;
	
	private HerdAnimal animal;
	
	//Set up animal
	public HerdAnimalPlacer(Point p){
		
		setLocation(p);
		setSize(10, 10);
		
	}
	
	//Paint at animal location
	protected void paintComponent(Graphics g){
		
		super.paintComponent(g);
		
		((Graphics2D) g).setPaint(Color.RED);
		g.fillRect(0, 0, 10, 10);

	}

}
