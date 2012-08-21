package plainswalker.GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Interface{
	
	protected JFrame mainFrame;
	private JPanel upperFrame;
	protected JScrollPane gridFrame;
	
	private Menu menu;
	protected Grid grid;
	
	//Toolbar items
	private JToolBar tools;
	private JButton waypointButton;
	private JButton herdAnimalButton;
	private JButton predatorButton;
	
	public Interface(){
		
		mainFrame = new JFrame("PlainsWalker");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		
		upperFrame = new JPanel(new BorderLayout());
		mainFrame.add(upperFrame, BorderLayout.NORTH);
		upperFrame.setVisible(true);
		
		grid = new Grid();
		gridFrame = new JScrollPane(grid);
		mainFrame.add(gridFrame);
		
		menu = new Menu(upperFrame, this);
		
		buildToolbar();
	
	}
	
	public void buildToolbar(){
		
		tools = new JToolBar();
		herdAnimalButton = new JButton("H");
		herdAnimalButton.setToolTipText("Animal Placement Mode");
		tools.add(herdAnimalButton);
		waypointButton = new JButton("W");
		waypointButton.setToolTipText("Waypoint Placement Mode");
		tools.add(waypointButton);
		predatorButton = new JButton("P");
		predatorButton.setToolTipText("Predator Placement Mode");
		tools.add(predatorButton);
		tools.setFloatable(false);
		
		upperFrame.add(tools, BorderLayout.SOUTH);
		
	}
	
	public void drawInterface(){
		
		mainFrame.setVisible(true);
		
	}
	
}
