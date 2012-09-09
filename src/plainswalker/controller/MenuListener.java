//Handles all menu events
//Daanyaal du Toit
//09 Septemeber 2012

package plainswalker.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import plainswalker.simulation.Simulation;

public class MenuListener implements ActionListener {

	Controller con;
	
	public MenuListener(Controller c) {
		con = c;
	}

	public void actionPerformed(ActionEvent e) {
		
		//Quit
		if(e.getActionCommand().equals("Quit"))
			System.exit(0);
		
		//New Simulation
		else if((e.getActionCommand().equals("New"))){
			
			con.model = new Simulation(500, 500);
			con.model.addObserver(con.view);
			
			con.view.addGrid(500, 500, con);
		
		}
		
	}

}
