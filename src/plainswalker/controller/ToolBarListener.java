//Handles all toolbar events
//Daanyaal du Toit
//09 Septemeber 2012

package plainswalker.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolBarListener implements ActionListener {

	Controller con;
	
	public ToolBarListener(Controller c) {
		con = c;
	}

	public void actionPerformed(ActionEvent e) {
		
		//Activate Animal mode
		if(e.getActionCommand().equals("Animal"))
			{
				con.curMode = Controller.placerMode.HERD_ANIMAL;
			}
		//Activate Waypoint mode
		else if(e.getActionCommand().equals("Waypoint"))
			{
				con.curMode = Controller.placerMode.WAYPOINT;
			}
		//Activate Predator mode
		else if(e.getActionCommand().equals("Predator"))
			{
				con.curMode = Controller.placerMode.PREDATOR;
			}
		//Activate Obstacle mode
		else if(e.getActionCommand().equals("Obstacle"))
			{
				con.curMode = Controller.placerMode.OBSTACLE;
			}
		//Start simulation
		else if(e.getActionCommand().equals("Play")){
			
			con.model.start();
			con.view.getToolbar().togglePlayPause();
			con.view.getToolbar().stopEnabled(true);
			
		}
		//Pause simulation at current frame
		else if(e.getActionCommand().equals("Pause")){
			
			con.model.pause();
			con.view.getToolbar().togglePlayPause();
			
		}
		//Reset to start of simulation
		else if(e.getActionCommand().equals("Stop")){
			
			con.model.stop();
			con.view.getToolbar().stopEnabled(false);
			con.view.getToolbar().resetStart();
			
		}
		
	}

}
