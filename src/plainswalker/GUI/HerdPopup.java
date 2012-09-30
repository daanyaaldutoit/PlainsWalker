//Allows herd to route assignment in the herdTree
//Daanyaal du Toit
//30 September 2012

package plainswalker.GUI;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class HerdPopup {

	protected JPopupMenu herdPop;
	protected JMenu [] herdMenus = new JMenu[9];
	
	public HerdPopup(){
		
		herdPop = new JPopupMenu();
		
		for(int i = 0; i < 9; ++i){
			herdMenus[i] = new JMenu("Herd " + (i+1));
			for(int j = 0; j < 9; ++j){
				JMenuItem mItem = new JMenuItem("Assign to Route " + (j+1));
				mItem.setActionCommand("Herd " + i + " to " + j);
				herdMenus[i].add(mItem);
				}
			herdPop.add(herdMenus[i]);
			
		}
		
	}

	public void disable(int hIndex, int rIndex) {

		for(int j = 0; j < 9; ++j){
			
			herdMenus[hIndex].getItem(j).setEnabled(true);
			
		}
		
		herdMenus[hIndex].getItem(rIndex).setEnabled(false);
		
	}
	
}
