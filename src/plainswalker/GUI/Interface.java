//Contains all gui components

package plainswalker.GUI;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

import plainswalker.controller.Controller;
import plainswalker.simulation.*;

public class Interface implements Observer{
	
	//All gui components
	protected JFrame mainFrame;
	private JPanel upperFrame;
	protected JScrollPane gridFrame;
	
	protected Grid grid;
	protected Menu menu;
	protected ToolBar tools;
	private JTabbedPane tabs;
	private HerdTree hTree;
	protected JLabel tileData;
	
	//Constructor to initialise components
	public Interface(){
		
		mainFrame = new JFrame("PlainsWalker");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		
		upperFrame = new JPanel(new BorderLayout());
		mainFrame.add(upperFrame, BorderLayout.NORTH);
		upperFrame.setVisible(true);
		
		gridFrame = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		mainFrame.add(gridFrame);
		
		tabs = new JTabbedPane();
		
		JPanel herdPanel = new JPanel();
		hTree = new HerdTree();
		herdPanel.add(hTree.herdTree);
		
		tabs.addTab("Herds", herdPanel);
		tabs.addTab("Predators", new JPanel());
		tabs.addTab("Waypoints", new JPanel());
		mainFrame.add(tabs, BorderLayout.WEST);
		
		menu = new Menu(upperFrame, this);
		
		tileData = new JLabel("x: y: h:");
		mainFrame.add(tileData, BorderLayout.SOUTH);
		
		tools = new ToolBar(upperFrame);
	
	}
	
	public void drawInterface(){
		
		mainFrame.setVisible(true);
		
	}

	//As the model changes, update the view accordingly
	public void update(Observable obs, Object change) {
		
		//New Animal added
		if(change instanceof HerdAnimal){
			
			hTree.herds[0].add(new DefaultMutableTreeNode("Animal " + hTree.herds[0].getChildCount()));
			
			HerdAnimalMarker mark = new HerdAnimalMarker(((HerdAnimal) change).getPosition());
			grid.add(mark);
			grid.herdMarks[0].add(mark);
			grid.validate();
			grid.repaint();
		}
		
		//New Waypoint
		else if(change instanceof Vector3D){
			
			//hTree.herds[0].add(new DefaultMutableTreeNode("Animal " + hTree.herds[0].getChildCount()));
			//hTree.herdTree.validate();
			//hTree.herdTree.repaint();
			
			WaypointMarker mark = new WaypointMarker(((Vector3D) change));
			grid.add(mark);
			grid.wayMarks[0].add(mark);
			grid.validate();
			grid.repaint();
		}
		//New Predator
		else if(change instanceof Predator){
			
			//hTree.herds[0].add(new DefaultMutableTreeNode("Animal " + hTree.herds[0].getChildCount()));
			//hTree.herdTree.validate();
			//hTree.herdTree.repaint();
			
			PredatorMarker mark = new PredatorMarker(((Predator) change).getPosition());
			grid.add(mark);
			//grid.wayMarks[0].add(mark);
			grid.validate();
			grid.repaint();
		}
		
		//Update positions of herd animals
		else if(change instanceof Herd[]){
			
			for(int i = 0; i < 10; ++i)
				for(int j = 0; j < ((Herd[])(change))[i].getAnims().size(); ++j){
				
					Vector3D pos = ((Herd[])(change))[i].getAnims().get(j).getPosition();
					grid.herdMarks[i].get(j).setLocation(pos);
				
				}
			
		}
		
	}

	//Attaches listeners to menu and toolbar
	public void setListener(Controller con) {
		
		tools.setListener(con.getTListen());
		menu.setListener(con.getMListen());
		
	}
	
	//Creates a new Grid as part of a new simulation
	public void addGrid(int l, int w, Controller con){
		
		grid = new Grid(500, 500);
		gridFrame.setViewportView(grid);
		tools.start.setEnabled(true);
		mainFrame.validate();
		mainFrame.repaint();
		grid.setListener(con.getGListen());
		
	}
	
	//Get interface components
	//-----------------------------------------------------------------------------------------------------
	public JFrame getMain(){return mainFrame;}
	
	public Grid getGrid(){ return grid;}
	
	public ToolBar getToolbar(){ return tools;}
	
	//------------------------------------------------------------------------------------------------------

	//Display information about tile at mouse's location
	public void setTileData(String data) {
		
		tileData.setText(data);
		
	}

	//Change grid to reflect loaded sim state
	public void loadState(Simulation old) {
		
		//Restore herd animal markers
		for(Herd herd: old.getHerds())
			for(HerdAnimal ha: herd.getAnims())
				update(old, ha);
		
		//Restore waypoint markers
		for(LinkedList<Vector3D> r: old.getRoutes())
			for(Vector3D way: r)
				update(old, way);
		
		//Restore predator markers
		for(Pack pack: old.getPacks())
			for(Predator p: pack.getPreds())
				update(old, p);
		
	}
	
}
