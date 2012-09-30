//Handles communication between the gui and the simulation model
//Daanyaal du Toit
//09 September 2012

package plainswalker.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import plainswalker.GUI.Interface;
import plainswalker.simulation.Simulation;

public class Controller{

	//Switches placement between prey, waypoints, predators and obstacles
	public enum placerMode {HERD_ANIMAL, WAYPOINT, PREDATOR, OBSTACLE};
	protected placerMode curMode = placerMode.HERD_ANIMAL;
	
	//Listener objects
	private ToolBarListener tListen = new ToolBarListener(this);
	private GridListener gListen = new GridListener(this);
	private MenuListener mListen = new MenuListener(this);
	private PopupListener pListen = new PopupListener(this);
	
	protected Interface view;
	protected Simulation model;
	
	//Change current herd/route/pack being assigned to 
	Action changeIndex = new AbstractAction(){
		
		private static final long serialVersionUID = 1L;

		public void actionPerformed(ActionEvent e){
			
			String index = ""+e.getActionCommand().charAt(e.getActionCommand().length()-1);
			System.out.println("Pressed " + index);		
			switch(curMode){
			
			case HERD_ANIMAL: curHerd = Integer.parseInt(index)-1; break;
			case WAYPOINT: curRoute = Integer.parseInt(index)-1; break;
			case PREDATOR: curPack = Integer.parseInt(index)-1; break;
			
			}
			
		}
		
	};
	
	protected int curHerd = 0, curRoute = 0, curPack = 0;
	
	public Controller(Interface v){
		
		view = v;

		//Set index change key bindings
		for(int i = 0; i < 9; ++i){
		
			view.getMain().getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_1+i, 0, false), "Change to " +i+1);
			view.getMain().getRootPane().getActionMap().put("Change to " + i+1, changeIndex);
		
		}
		
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
	
	public PopupListener getPListen(){
		return pListen;
	}
	
}
