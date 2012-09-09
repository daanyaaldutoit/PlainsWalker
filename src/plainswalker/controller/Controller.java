//Handles communication between the gui and the simulation model
//Daanyaal du Toit
//09 September 2012

package plainswalker.controller;

import plainswalker.GUI.Interface;
import plainswalker.simulation.Simulation;

public class Controller{

	//Switches placement between prey, waypoints and predators
	public enum placerMode {HERD_ANIMAL, WAYPOINT, PREDATOR};
	protected placerMode curMode = placerMode.HERD_ANIMAL;
	
	//Listener objects
	private ToolBarListener tListen = new ToolBarListener(this);
	private GridListener gListen = new GridListener(this);
	private MenuListener mListen = new MenuListener(this);
	
	protected Interface view;
	protected Simulation model;
	
	public Controller(Interface v){
		
		view = v;
		
	}

	//Getters for listeners
	public ToolBarListener getTListen() {
		return tListen;
	}

	public MenuListener getMListen() {
		return mListen;
	}

	public GridListener getGListen() {
		return gListen;
	}
	
}
