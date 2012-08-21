package plainswalker.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Tile extends JButton implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private int xCo, yCo;
	
	public Tile(int x, int y, String label){
		
		super(label);
		setOpaque(false);
		setContentAreaFilled(false);
		xCo = x;
		yCo = y;
		addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		
	}

}
