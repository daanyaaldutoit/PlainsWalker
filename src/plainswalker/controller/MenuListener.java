//Handles all menu events
//Daanyaal du Toit
//09 Septemeber 2012

package plainswalker.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.ProgressMonitor;
import javax.swing.ProgressMonitorInputStream;
import javax.swing.filechooser.FileNameExtensionFilter;

import plainswalker.simulation.HeightMap;
import plainswalker.simulation.Simulation;
import plainswalker.simulation.Terrain;

public class MenuListener implements ActionListener {

	Controller con;
	
	public MenuListener(Controller c) {
		con = c;
	}

	public void actionPerformed(ActionEvent e) {
		
		//File Menu
		//------------------------------------------------------------------------------------------
		
		//Quit
		if(e.getActionCommand().equals("Quit"))
			System.exit(0);
		
		//New Simulation
		else if(e.getActionCommand().equals("New")){
			
			JFileChooser loadMap = new JFileChooser();
			loadMap.setCurrentDirectory(new File("../Plainswalker"));
			loadMap.setFileFilter(new FileNameExtensionFilter("Map files (*.map)", "map"));
			int val = loadMap.showOpenDialog(con.view.getMain());
			
			if(val == JFileChooser.APPROVE_OPTION){
				
				String fileName = loadMap.getCurrentDirectory() + "/" + loadMap.getSelectedFile().getName();
				if(!fileName.endsWith(".map"))
					fileName += ".map";
				
				try {
					HeightMap h = new HeightMap(fileName);
					
					con.model = new Simulation(h);
					con.model.addObserver(con.view);
					
					con.view.addGrid(con.model.getTerrain(), con);
					
					con.view.getToolbar().resetStart();
					
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		
		}
		
		//Save Simulation
		else if(e.getActionCommand().equals("Save")){
			
			JFileChooser saveTo = new JFileChooser();
			saveTo.setCurrentDirectory(new File("../Plainswalker"));
			saveTo.setFileFilter(new FileNameExtensionFilter("Plainswalker Simulations (*.pws)", "pws"));
			int val = saveTo.showSaveDialog(con.view.getMain());
			
			if(val == JFileChooser.APPROVE_OPTION){
				
				String fileName = saveTo.getCurrentDirectory() + "/" + saveTo.getSelectedFile().getName();
				if(!fileName.endsWith(".pws"))
					fileName += ".pws";
				
				try {
					FileOutputStream fileOut = new FileOutputStream(fileName);
					ObjectOutputStream simOut = new ObjectOutputStream(fileOut);
					ProgressMonitor monitor = new ProgressMonitor(con.view.getMain(), "Saving " + fileName, "", 0, 1);
					simOut.writeObject(con.model);
					monitor.setProgress(1);
					simOut.close();
					fileOut.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
			
		}
		
		//Load Simulation
		else if(e.getActionCommand().equals("Load")){
			
			JFileChooser loadFrom = new JFileChooser();
			loadFrom.setCurrentDirectory(new File("../Plainswalker"));
			loadFrom.setFileFilter(new FileNameExtensionFilter("Plainswalker Simulations (*.pws)", "pws"));
			
			int val = loadFrom.showOpenDialog(con.view.getMain());
			if(val == JFileChooser.APPROVE_OPTION){
				
				String fileName = loadFrom.getCurrentDirectory() + "/" + loadFrom.getSelectedFile().getName();
				if(!fileName.endsWith(".pws"))
					fileName += ".pws";
				
				try {
					
					ProgressMonitorInputStream monitor = new ProgressMonitorInputStream(con.view.getMain(), "Loading " + fileName, new FileInputStream(fileName));
					ObjectInputStream simIn = new ObjectInputStream(monitor);
					con.model = (Simulation) simIn.readObject();
					con.model.addObserver(con.view);
					con.view.addGrid(con.model.getTerrain(), con);
					con.view.loadState(con.model);
					simIn.close();
					monitor.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
				
			}
			
		}
		
		//----------------------------------------------------------------------------
		
		//View Menu
		//----------------------------------------------------------------------------
		
		//Toggle red avoidance radii
		else if(e.getActionCommand().equals("Show Avoidance")){
			
			con.view.getGrid().toggleShowAvoid();
			
		}
		
		//Toggle green neighbour radii
		else if(e.getActionCommand().equals("Show Neighbours")){
			
			con.view.getGrid().toggleShowNeighbours();
			
		}
		
		else if(e.getActionCommand().equals("Show Grid")){
			
			con.view.toggleGrid();
			
		}
		
	}

}
