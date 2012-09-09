//Handles mouse events on the interface's grid
//Daanyaal du Toit
//09 Septemeber 2012

package plainswalker.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import plainswalker.simulation.*;

public class GridListener implements MouseListener, MouseMotionListener {

	Controller con;
	
	public GridListener(Controller c) {
		con = c;
	}

	//Place an object on the grid
	public void mouseClicked(MouseEvent e) {
		
		//Place Herd Animal
		if(con.curMode == Controller.placerMode.HERD_ANIMAL){
		
			HerdAnimal h = new HerdAnimal(new Vector3D(e.getX(), e.getY(), 0));
			con.model.addAnimal(h, 0);
		
		}
		//Place Waypoint
		else if(con.curMode == Controller.placerMode.WAYPOINT){
			
			//WaypointPlacer w = new WaypointPlacer(new Point(e.getX()-(WaypointPlacer.SIZE.width/2), e.getY()-(WaypointPlacer.SIZE.height/2)));
			//herdAnimals.add(h);
			//add(w);
			//gui.sim.addAnimal(h.animal);
			//validate();
			//repaint();
			
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	//Display current location information
	public void mouseMoved(MouseEvent e) {
		
		con.view.setTileData("x: " + e.getX() + " y: " + e.getY() + " h: " + con.model.getHeight(e.getX(), e.getY()));
		
	}

}
