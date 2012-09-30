//Allows pack to route assignment in the packTree
//Daanyaal du Toit
//30 September 2012

package plainswalker.GUI;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class PackPopup {

	protected JPopupMenu packPop;
	protected JMenu [] packMenus = new JMenu[9];
	
	public PackPopup(){
		
		packPop = new JPopupMenu();
		
		for(int i = 0; i < 9; ++i){
			packMenus[i] = new JMenu("Pack " + (i+1));
			for(int j = 0; j < 9; ++j){
				JMenuItem mItem = new JMenuItem("Assign to Route " + (j+1));
				mItem.setActionCommand("Pack " + i + " to " + j);
				packMenus[i].add(mItem);
				}
			packPop.add(packMenus[i]);
			
		}
		
	}

	public void disable(int hIndex, int rIndex) {

		for(int j = 0; j < 9; ++j){
			
			packMenus[hIndex].getItem(j).setEnabled(true);
			
		}
		
		packMenus[hIndex].getItem(rIndex).setEnabled(false);
		
	}
	
}
