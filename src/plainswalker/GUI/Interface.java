package plainswalker.GUI;

import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.*;

public class Interface{
	
	protected enum placerMode {HERD_ANIMAL, WAYPOINT, PREDATOR};
	protected placerMode curMode = placerMode.HERD_ANIMAL;
	
	protected JFrame mainFrame;
	private JPanel upperFrame;
	protected JScrollPane gridFrame;
	
	protected Grid grid;
	private JTabbedPane tabs;
	
	protected JLabel tileData;
	
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
		tabs.addTab("Herds", new JPanel());
		tabs.addTab("Predators", new JPanel());
		tabs.addTab("Waypoints", new JPanel());
		mainFrame.add(tabs, BorderLayout.WEST);
		
		new Menu(upperFrame, this);
		
		tileData = new JLabel("x: y: h:");
		mainFrame.add(tileData, BorderLayout.SOUTH);
		
		new ToolBar(upperFrame, this);
	
	}
	
	public void drawInterface(){
		
		mainFrame.setVisible(true);
		
	}
	
}
