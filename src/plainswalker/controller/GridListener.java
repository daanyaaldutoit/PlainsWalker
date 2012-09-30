//Handles mouse events on the interface's grid
//Daanyaal du Toit
//09 Septemeber 2012

package plainswalker.controller;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import plainswalker.GUI.Grid;
import plainswalker.controller.Controller.placerMode;
import plainswalker.simulation.*;

public class GridListener implements MouseListener, MouseMotionListener{

	Controller con;
	Point origin;
	
	public GridListener(Controller c) {
		con = c;
	}

	//Place an object on the grid
	public void mouseClicked(MouseEvent e) {
		
		if(e.getButton() == MouseEvent.BUTTON1){
		//Place Herd Animal
		if(con.curMode == Controller.placerMode.HERD_ANIMAL){
		
			HerdAnimal h = new HerdAnimal(new Vector3D(e.getX(), e.getY(), 0), con.curHerd);
			con.model.addAnimal(h);
		
		}
		//Place Waypoint
		else if(con.curMode == Controller.placerMode.WAYPOINT){
			
			Waypoint waypoint = new Waypoint(e.getX(), e.getY(), 0, con.curRoute);
			con.model.addWaypoint(waypoint);
			
		}
		else if(con.curMode == Controller.placerMode.PREDATOR){
			
			Predator p = new Predator(new Vector3D(e.getX(), e.getY(), 0), con.curPack );
			con.model.addPredator(p);
			
		}
		
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
	public void mousePressed(MouseEvent e) {

		origin = new Point(e.getX(), e.getY());

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

		con.view.getGrid().selectMarkers();
		con.view.getGrid().setSelectionRectangle(null);
		origin = null;

	}

	@Override
	public void mouseDragged(MouseEvent e) {

		if(con.curMode == placerMode.OBSTACLE){
			
			con.model.setPassable(e.getX()/Grid.blockSize, e.getY()/Grid.blockSize, false);
			
		}
		else
			con.view.getGrid().setSelectionRectangle(new Rectangle(origin.x, origin.y, e.getX()-origin.x, e.getY()-origin.y) );

	}

	//Display current location information
	public void mouseMoved(MouseEvent e) {
		
		con.view.setTileData("x: " + e.getX() + " y: " + e.getY() + " h: " + con.model.getHeight(e.getX(), e.getY()));
		
	}

}
