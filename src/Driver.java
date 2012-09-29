//Driver class containing main method

import plainswalker.GUI.Interface;
import plainswalker.controller.Controller;

public class Driver {

	public static void main(String[] args) {
		
		//Set up view and controller components (model done in controller)
		Interface gui = new Interface();
		Controller con = new Controller(gui);
		
		gui.setListener(con);
		gui.drawInterface();

	}

}
