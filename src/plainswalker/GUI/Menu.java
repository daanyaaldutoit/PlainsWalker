//Contains all interface GUI functionality

package plainswalker.GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Menu implements ActionListener {

	//File Menu items
	private Interface gui;
	protected JMenuBar menu;
	protected JMenu fileMenu;
	protected JMenuItem newSim;
	protected JMenuItem loadSim;
	protected JMenuItem saveSim;
	protected JMenuItem quit;
	
	//View Menu Items
	protected JMenu viewMenu;
	protected JCheckBoxMenuItem showTerrain;
	
	public void actionPerformed(ActionEvent e) {
		
		//Quit
		if(e.getActionCommand().equals("Quit"))
			System.exit(0);
		
		//New Simulation
		else if((e.getActionCommand().equals("New"))){
		
			gui.grid = new Grid(gui, 500, 500);
			gui.gridFrame.setViewportView(gui.grid);
			gui.tools.start.setEnabled(true);
			gui.mainFrame.validate();
			gui.mainFrame.repaint();
		
		}
		
	}
	
	//Set up all menu items
	public Menu(JPanel container, Interface inter){
		
		gui = inter;
		menu = new JMenuBar();
		
		//File Menu
		fileMenu = new JMenu("File");
		fileMenu.setMnemonic('F');
		menu.add(fileMenu);
		
		newSim = new JMenuItem("New Simulation");
		newSim.setMnemonic('N');
		newSim.setActionCommand("New");
		newSim.addActionListener(this);
		fileMenu.add(newSim);
		
		loadSim = new JMenuItem("Load Simulation");
		loadSim.setMnemonic('L');
		loadSim.setActionCommand("Load");
		loadSim.addActionListener(this);
		fileMenu.add(loadSim);
		
		saveSim = new JMenuItem("Save Simulation");
		saveSim.setMnemonic('S');
		saveSim.setActionCommand("Save");
		saveSim.addActionListener(this);
		fileMenu.add(saveSim);
		
		quit = new JMenuItem("Quit");
		quit.setMnemonic('Q');
		quit.setActionCommand("Quit");
		quit.addActionListener(this);
		
		fileMenu.add(quit);
		
		//View Menu
		viewMenu = new JMenu("View");
		fileMenu.setMnemonic('V');
		menu.add(viewMenu);
		
		showTerrain = new JCheckBoxMenuItem("View Tile Heights");
		showTerrain.setMnemonic('H');
		showTerrain.setActionCommand("Show Heights");
		showTerrain.addActionListener(this);
		viewMenu.add(showTerrain);
		
		container.add(menu, BorderLayout.NORTH);
		
	}

}
