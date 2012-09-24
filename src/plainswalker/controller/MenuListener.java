//Handles all menu events
//Daanyaal du Toit
//09 Septemeber 2012

package plainswalker.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import plainswalker.simulation.Simulation;

public class MenuListener implements ActionListener {

	Controller con;
	
	public MenuListener(Controller c) {
		con = c;
	}

	public void actionPerformed(ActionEvent e) {
		
		//Quit
		if(e.getActionCommand().equals("Quit"))
			System.exit(0);
		
		//New Simulation
		else if((e.getActionCommand().equals("New"))){
			
			con.model = new Simulation(500, 500);
			con.model.addObserver(con.view);
			
			con.view.addGrid(500, 500, con);
		
		}
		
		//Save Simulation
		else if((e.getActionCommand().equals("Save"))){
			
			JFileChooser saveTo = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Plainswalker Simulations (.pws)", "pws");
			saveTo.setFileFilter(filter);
			int val = saveTo.showSaveDialog(con.view.getMain());
			if(val == JFileChooser.APPROVE_OPTION){
				
				String fileName = saveTo.getCurrentDirectory() + "/" + saveTo.getSelectedFile().getName();
				if(!fileName.endsWith(".pws"))
					fileName += ".pws";
				
				try {
					FileOutputStream fileOut = new FileOutputStream(fileName);
					ObjectOutputStream simOut = new ObjectOutputStream(fileOut);
					simOut.writeObject(con.model);
					simOut.close();
					fileOut.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
			
		}
		
		//Load Simulation
		else if((e.getActionCommand().equals("Load"))){
			
			JFileChooser loadFrom = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Plainswalker Simulations (.pws)", "pws");
			loadFrom.setFileFilter(filter);
			int val = loadFrom.showOpenDialog(con.view.getMain());
			if(val == JFileChooser.APPROVE_OPTION){
				
				String fileName = loadFrom.getCurrentDirectory() + "/" + loadFrom.getSelectedFile().getName();
				if(!fileName.endsWith(".pws"))
					fileName += ".pws";
				
				try {
					FileInputStream fileIn = new FileInputStream(fileName);
					ObjectInputStream simIn = new ObjectInputStream(fileIn);
					con.model = (Simulation)simIn.readObject();
					con.model.addObserver(con.view);
					con.view.addGrid(500,500, con);
					con.view.loadState(con.model);
					simIn.close();
					fileIn.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
				
			}
			
		}
		
	}

}
