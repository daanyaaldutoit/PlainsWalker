package plainswalker.GUI;

import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

import plainswalker.simulation.Simulation;

public class Interface{
	
	protected enum placerMode {HERD_ANIMAL, WAYPOINT, PREDATOR};
	protected placerMode curMode = placerMode.HERD_ANIMAL;
	
	protected JFrame mainFrame;
	private JPanel upperFrame;
	protected JScrollPane gridFrame;
	
	protected Grid grid;
	protected ToolBar tools;
	
	private JTabbedPane tabs;
	private JTree animTree;
	
	protected JLabel tileData;
	
	protected Simulation sim;
	
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
		DefaultMutableTreeNode herdRoot = new DefaultMutableTreeNode("Herds");
		
		DefaultMutableTreeNode herdOne = new DefaultMutableTreeNode("Herd 1");
		herdRoot.add(herdOne);
		DefaultMutableTreeNode unassigned = new DefaultMutableTreeNode("Unassigned");
		herdRoot.add(unassigned);
		
		animTree = new JTree(herdRoot);
		herdPanel.add(animTree);
		
		tabs.addTab("Herds", herdPanel);
		tabs.addTab("Predators", new JPanel());
		tabs.addTab("Waypoints", new JPanel());
		mainFrame.add(tabs, BorderLayout.WEST);
		
		new Menu(upperFrame, this);
		
		tileData = new JLabel("x: y: h:");
		mainFrame.add(tileData, BorderLayout.SOUTH);
		
		tools = new ToolBar(upperFrame, this);
	
	}
	
	public void drawInterface(){
		
		mainFrame.setVisible(true);
		
	}
	
	public JFrame getMain(){
		
		return mainFrame;
		
	}
	
}
