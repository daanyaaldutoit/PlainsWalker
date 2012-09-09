//Contains all menu components

package plainswalker.GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Menu{

	//File Menu items
	protected JMenuBar menu;
	protected JMenu fileMenu;
	protected JMenuItem newSim;
	protected JMenuItem loadSim;
	protected JMenuItem saveSim;
	protected JMenuItem quit;
	
	//View Menu Items
	protected JMenu viewMenu;
	protected JCheckBoxMenuItem showTerrain;
	
	//Set up all menu items
	public Menu(JPanel container, Interface inter){
		
		menu = new JMenuBar();
		
		//File Menu
		fileMenu = new JMenu("File");
		fileMenu.setMnemonic('F');
		menu.add(fileMenu);
		
		newSim = new JMenuItem("New Simulation");
		newSim.setMnemonic('N');
		newSim.setActionCommand("New");
		fileMenu.add(newSim);
		
		loadSim = new JMenuItem("Load Simulation");
		loadSim.setMnemonic('L');
		loadSim.setActionCommand("Load");
		fileMenu.add(loadSim);
		
		saveSim = new JMenuItem("Save Simulation");
		saveSim.setMnemonic('S');
		saveSim.setActionCommand("Save");
		fileMenu.add(saveSim);
		
		quit = new JMenuItem("Quit");
		quit.setMnemonic('Q');
		quit.setActionCommand("Quit");
		
		fileMenu.add(quit);
		
		//View Menu
		viewMenu = new JMenu("View");
		fileMenu.setMnemonic('V');
		menu.add(viewMenu);
		
		showTerrain = new JCheckBoxMenuItem("View Tile Heights");
		showTerrain.setMnemonic('H');
		showTerrain.setActionCommand("Show Heights");
		viewMenu.add(showTerrain);
		
		container.add(menu, BorderLayout.NORTH);
		
	}
	
	//Add an ActionListener to all components
	public void setListener(ActionListener lis){
		
		newSim.addActionListener(lis);
		loadSim.addActionListener(lis);
		saveSim.addActionListener(lis);
		quit.addActionListener(lis);
		showTerrain.addActionListener(lis);
		
	}

}
