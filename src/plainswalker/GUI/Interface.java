//Contains all gui components

package plainswalker.GUI;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

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
	protected JTabbedPane tabs;
	
	protected JLabel tileData;
	
	private HerdTree hTree;
	private WaypointTree wTree;
	private PackTree pTree;
	
	private HerdPopup hPop;
	private PackPopup pPop;
	
	//Constructor to initialise components
	public Interface(){
		
		//Main window
		mainFrame = new JFrame("PlainsWalker");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		
		//Contains toolbar
		upperFrame = new JPanel(new BorderLayout());
		mainFrame.add(upperFrame, BorderLayout.NORTH);
		upperFrame.setVisible(true);
		
		gridFrame = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		mainFrame.add(gridFrame);
		
		tabs = new JTabbedPane();
		
		//Tree setup
		JPanel herdPanel = new JPanel();
		hTree = new HerdTree();
		herdPanel.add(hTree.herdTree);

		JPanel wayPanel = new JPanel();
		wTree = new WaypointTree();
		wayPanel.add(wTree.wayTree);
		
		JPanel packPanel = new JPanel();
		pTree = new PackTree();
		packPanel.add(pTree.packTree);
		
		//Tabbed pane for trees
		tabs.addTab("Herds", herdPanel);
		tabs.addTab("Predators", packPanel);
		tabs.addTab("Waypoints", wayPanel);
		mainFrame.add(tabs, BorderLayout.WEST);
		
		menu = new Menu();
		mainFrame.setJMenuBar(menu.menu);
		
		tileData = new JLabel("x: y: h:");
		mainFrame.add(tileData, BorderLayout.SOUTH);
		
		tools = new ToolBar(upperFrame);
		
		hPop = new HerdPopup();
		hTree.herdTree.setComponentPopupMenu(hPop.herdPop);
		
		pPop = new PackPopup();
		pTree.packTree.setComponentPopupMenu(pPop.packPop);
	
	}
	
	public void drawInterface(){
		
		mainFrame.setVisible(true);
		
	}

	//As the model changes, update the view accordingly
	public void update(Observable obs, Object change) {
		
		//New Animal added
		if(change instanceof HerdAnimal){
			
			HerdAnimal hChange = (HerdAnimal) change;
			
			//Add new node to appropriate herd in tree
			DefaultTreeModel model = (DefaultTreeModel) hTree.herdTree.getModel();
			model.insertNodeInto(new DefaultMutableTreeNode("Animal " + hTree.herds[hChange.getIndex()].getChildCount()), hTree.herds[hChange.getIndex()], hTree.herds[hChange.getIndex()].getChildCount());
			
			HerdAnimalMarker mark = new HerdAnimalMarker(hChange.getPosition(), menu.showAvoid.isSelected(), menu.showNeighbour.isSelected());
			grid.add(mark);
			grid.herdMarks[hChange.getIndex()].add(mark);
			gridFrame.validate();
			gridFrame.repaint();
		}
		
		//New Waypoint
		else if(change instanceof Waypoint){
			
			Waypoint wChange = (Waypoint)change;
			
			//Add new node to appropriate route in tree
			DefaultTreeModel model = (DefaultTreeModel) wTree.wayTree.getModel();
			model.insertNodeInto(new DefaultMutableTreeNode("Waypoint " + wTree.routes[wChange.getIndex()].getChildCount()), wTree.routes[wChange.getIndex()], wTree.routes[wChange.getIndex()].getChildCount());
			
			WaypointMarker mark = new WaypointMarker(wChange);
			grid.add(mark);
			grid.wayMarks[wChange.getIndex()].add(mark);
			gridFrame.validate();
			gridFrame.repaint();
		}
		
		//New Predator
		else if(change instanceof Predator){
			
			Predator pChange = (Predator) change;
			
			//Add new node to appropriate route in tree
			DefaultTreeModel model = (DefaultTreeModel) pTree.packTree.getModel();
			model.insertNodeInto(new DefaultMutableTreeNode("Predator " + pTree.packs[pChange.getIndex()].getChildCount()), pTree.packs[pChange.getIndex()], pTree.packs[pChange.getIndex()].getChildCount());
			
			PredatorMarker mark = new PredatorMarker(pChange.getPosition(), menu.showAvoid.isSelected());
			grid.add(mark);
			grid.predMarks[pChange.getIndex()].add(mark);
			gridFrame.validate();
			gridFrame.repaint();
		}
		
		//Tile painted
		else if(change instanceof Tile){
			
			Tile tChange = (Tile)change;
			grid.obsTiles.add(new Rectangle(tChange.getX()*Grid.blockSize, tChange.getY()*Grid.blockSize, Grid.blockSize, Grid.blockSize));
			gridFrame.validate();
			gridFrame.repaint();
		}
		
		//Update positions of movable components
		else if(change instanceof Frame){
			
			Frame cFrame = (Frame)change;
			
			for(int i = 0; i < 9; ++i){
				
				for(int j = 0; j < cFrame.getHerds()[i].getAnims().size(); ++j){
				
					Vector3D pos = cFrame.getHerds()[i].getAnims().get(j).getPosition();
					grid.herdMarks[i].get(j).setLocation(pos);
				
				}
				
				for(int j = 0; j < cFrame.getPacks()[i].getPreds().size(); ++j){
					
					Vector3D pos = cFrame.getPacks()[i].getPreds().get(j).getPosition();
					grid.predMarks[i].get(j).setLocation(pos);
				
				}
				
			}
			
		}
		
	}

	//Attaches listeners to menu and toolbar
	public void setListener(Controller con) {
		
		tools.setListener(con.getTListen());
		menu.setListener(con.getMListen());
		for(int i = 0; i < 9; ++i){
			
			for(int j = 0; j < 9; ++j){
				hPop.herdMenus[i].getItem(j).addActionListener(con.getPListen());
				pPop.packMenus[i].getItem(j).addActionListener(con.getPListen());
			}
			
		}
		
	}
	
	//Creates a new Grid as part of a new simulation
	public void addGrid(Terrain t, Controller con){
		
		hTree.reset();
		grid = new Grid(t);
		GridViewport gridView = new GridViewport();
		gridView.setView(grid);
		gridFrame.setViewport(gridView);
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
	
	public HerdPopup getHerdPopup() { return hPop;}
	
	public PackPopup getPackPopup() { return pPop;}
	
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
		for(LinkedList<Waypoint> r: old.getRoutes())
			for(Vector3D way: r)
				update(old, way);
		
		//Restore predator markers
		for(Pack pack: old.getPacks())
			for(Predator p: pack.getPreds())
				update(old, p);
		
		for(Tile[] tiles: old.getTiles())
			for(Tile tile: tiles)
				if(!tile.getPassable())
					update(old, tile);
	}

	public void toggleGrid() {
		
		((GridViewport)(gridFrame.getViewport())).showGrid = !((GridViewport)(gridFrame.getViewport())).showGrid;
		gridFrame.validate();
		gridFrame.repaint();
		
	}
	
}
