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
				System.out.println("Animal Mode");
				con.curMode = Controller.placerMode.HERD_ANIMAL;
			}
		//Activate Waypoint mode
		else if(e.getActionCommand().equals("Waypoint"))
			{
			
				System.out.println("Waypoint Mode");
				con.curMode = Controller.placerMode.WAYPOINT;
			
			}
		//Activate Predator mode
		else if(e.getActionCommand().equals("Predator"))
			{
				System.out.println("Predator Mode");
				con.curMode = Controller.placerMode.PREDATOR;
				
			}
		//Start simulation
		else if(e.getActionCommand().equals("Play")){
			
			con.model.start();
			//start.setActionCommand("Pause");
			//start.setText("Pause");
			con.view.getToolbar().stopEnabled(true);
			
		}
		//Pause simulation at current frame
		else if(e.getActionCommand().equals("Pause")){
			
			//gui.sim.pause();
			//start.setActionCommand("Play");
			//start.setText("Play");
			
		}
		else if(e.getActionCommand().equals("Stop")){
			
			//gui.sim.stop();
			//stop.setEnabled(false);
			//start.setActionCommand("Play");
			
		}
		
	}

}
