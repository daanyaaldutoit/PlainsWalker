//Contains the toolbar components

package plainswalker.GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class ToolBar{

	//Toolbar items
	protected JToolBar tools;
	protected JButton waypointButton;
	protected JButton herdAnimalButton;
	protected JButton predatorButton;
	protected JButton obstacleButton;
	protected JButton start;
	protected JButton stop;
	
	//Set up toolbar
	public ToolBar(JPanel container){
		
		tools = new JToolBar();
		
		//Animal Placement Button
		herdAnimalButton = new JButton("H");
		herdAnimalButton.setToolTipText("Animal Placement Mode");
		herdAnimalButton.setActionCommand("Animal");
		tools.add(herdAnimalButton);
		
		//Waypoint Button
		waypointButton = new JButton("W");
		waypointButton.setToolTipText("Waypoint Placement Mode");
		waypointButton.setActionCommand("Waypoint");
		tools.add(waypointButton);
		
		//Predator Button
		predatorButton = new JButton("P");
		predatorButton.setToolTipText("Predator Placement Mode");
		predatorButton.setActionCommand("Predator");
		tools.add(predatorButton);
		
		//Obstacle Button
		obstacleButton = new JButton("O");
		obstacleButton.setToolTipText("Obstacle Painting Mode");
		obstacleButton.setActionCommand("Obstacle");
		tools.add(obstacleButton);
		
		//Start Button
		start = new JButton("Play");
		start.setToolTipText("Start Simulation");
		start.setActionCommand("Play");
		start.setEnabled(false);
		tools.add(start);
		
		//Stop Button
		stop = new JButton("Stop");
		stop.setToolTipText("Stop Simulation");
		stop.setActionCommand("Stop");
		stop.setEnabled(false);
		tools.add(stop);
		
		tools.setFloatable(false);
		herdAnimalButton.setSelected(true);
		
		container.add(tools, BorderLayout.SOUTH);
		
	}
	
	//Attaches a listener to all event-creating components
	public void setListener(ActionListener lis){
		
		herdAnimalButton.addActionListener(lis);
		waypointButton.addActionListener(lis);
		predatorButton.addActionListener(lis);
		start.addActionListener(lis);
		stop.addActionListener(lis);
		
	}
	
	//Enable/disable buttons
	public void stopEnabled(boolean set){
		
		stop.setEnabled(set);
		
	}
	
	public void togglePlayPause(){
		
		if(start.getActionCommand().equals("Play")){
			
			start.setText("Pause");
			start.setActionCommand("Pause");
			
		}
		else{
			
			start.setText("Play");
			start.setActionCommand("Play");
			
		}
		
	}

	public void resetStart() {
		
		start.setText("Play");
		start.setActionCommand("Play");
		
	}
	
}
