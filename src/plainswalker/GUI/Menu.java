package plainswalker.GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Menu implements ActionListener {

	//Menu items
	private Interface gui;
	protected JMenuBar menu;
	protected JMenu fileMenu;
	protected JMenuItem newSim;
	protected JMenuItem loadSim;
	protected JMenuItem saveSim;
	protected JMenuItem quit;
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().equals("Quit"))
			System.exit(0);
		else if((e.getActionCommand().equals("New"))){
		
			gui.gridFrame.setVisible(false);
			gui.grid = new Grid(gui, 500, 500);
			gui.gridFrame.setViewportView(gui.grid);
			gui.mainFrame.validate();
			gui.gridFrame.setVisible(true);
		
		}
		
	}
	
	public Menu(JPanel container, Interface inter){
		
		gui = inter;
		menu = new JMenuBar();
		
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
		
		container.add(menu, BorderLayout.NORTH);
		
	}

}
