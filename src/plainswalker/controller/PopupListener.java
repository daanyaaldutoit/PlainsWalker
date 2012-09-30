//Handles route assignment through popup menues
//Daanyaal du Toit
//30 September 2012

package plainswalker.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PopupListener implements ActionListener {

	Controller con;
	
	public PopupListener(Controller c) {
		con = c;
	}

	public void actionPerformed(ActionEvent e) {
		
		String command = e.getActionCommand();
		
		int gIndex = command.charAt(5)-48;
		int rIndex = command.charAt(command.length()-1)-48;
		
		if(command.startsWith("Herd")){
			
			if(con.model != null){
				con.model.assignHerdRoute(gIndex, rIndex);
				con.view.getHerdPopup().disable(gIndex, rIndex);
			}
			
		}
		else if(command.startsWith("Pack")){
			
			if(con.model != null){
				con.model.assignPackRoute(gIndex, rIndex);
				con.view.getPackPopup().disable(gIndex, rIndex);
			}
			
		}

	}

}
