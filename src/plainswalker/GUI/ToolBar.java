//Contains the main toolbar functionality

package plainswalker.GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import plainswalker.GUI.Interface.placerMode;

public class ToolBar implements ActionListener{

	//Toolbar items
	private Interface gui;
	protected JToolBar tools;
	protected JButton waypointButton;
	protected JButton herdAnimalButton;
	protected JButton predatorButton;
	protected JButton start;
	protected JButton stop;
	
	//Set up toolbar
	public ToolBar(JPanel container, Interface inter){
		
		gui = inter;
		
		tools = new JToolBar();
		herdAnimalButton = new JButton("H");
		herdAnimalButton.setToolTipText("Animal Placement Mode");
		herdAnimalButton.setActionCommand("Animal");
		herdAnimalButton.addActionListener(this);
		tools.add(herdAnimalButton);
		
		waypointButton = new JButton("W");
		waypointButton.setToolTipText("Waypoint Placement Mode");
		waypointButton.setActionCommand("Waypoint");
		waypointButton.addActionListener(this);
		tools.add(waypointButton);
		
		predatorButton = new JButton("P");
		predatorButton.setToolTipText("Predator Placement Mode");
		predatorButton.setActionCommand("Predator");
		predatorButton.addActionListener(this);
		tools.add(predatorButton);
		
		start = new JButton("Start");
		start.setToolTipText("Start Simulation");
		start.setActionCommand("Play/Pause");
		start.addActionListener(this);
		start.setEnabled(false);
		tools.add(start);
		
		stop = new JButton("Stop");
		stop.setToolTipText("Stop Simulation");
		stop.setActionCommand("Stop");
		stop.addActionListener(this);
		stop.setEnabled(false);
		tools.add(stop);
		
		tools.setFloatable(false);
		
		herdAnimalButton.setSelected(true);
		
		container.add(tools, BorderLayout.SOUTH);
		
	}

	public void actionPerformed(ActionEvent e) {
		
		//Activate Animal mode
		if(e.getActionCommand().equals("Animal"))
			{
				System.out.println("Animal Mode");
				gui.curMode = placerMode.HERD_ANIMAL;
				
			}
		//Activate Waypoint mode
		else if(e.getActionCommand().equals("Waypoint"))
			{
			
				System.out.println("Waypoint Mode");
				gui.curMode = placerMode.WAYPOINT;
			
			}
		//Activate Predator mode
		else if(e.getActionCommand().equals("Predator"))
			{
				System.out.println("Predator Mode");
				gui.curMode = placerMode.PREDATOR;
				
			}
		//Start simulation
		else if(e.getActionCommand().equals("Play/Pause")){
			
			gui.sim.start();
			stop.setEnabled(true);
			
		}
		
	}
	
}
