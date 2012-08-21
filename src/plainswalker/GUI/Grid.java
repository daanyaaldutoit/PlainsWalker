package plainswalker.GUI;

import java.awt.GridLayout;

import javax.swing.JPanel;

public class Grid extends JPanel{

	private static final long serialVersionUID = 1L;
	
	public Grid(){
		
		super(new GridLayout(6,6));
		for(int i = 0; i < 6; ++i)
			for(int j = 0; j < 6; ++j)
				add(new Tile(j, i, j + ", " + i));
		setVisible(true);
		
	}

}
