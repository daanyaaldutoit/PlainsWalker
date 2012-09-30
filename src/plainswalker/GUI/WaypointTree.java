//Simple class containing a tree of all waypoints
//Daanyaal du Toit
//24 September 2012

package plainswalker.GUI;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;

public class WaypointTree {
	
	protected JTree wayTree;
	protected DefaultMutableTreeNode[] routes = new DefaultMutableTreeNode[9];
	
	public WaypointTree(){
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Routes");
		
		for(int i = 0; i < 9; ++i){
			
			routes[i] = new DefaultMutableTreeNode("Route " + (i+1));
			root.add(routes[i]);
			
		}
		
		wayTree = new JTree(root);
		
	}

	public void reset() {
		
		DefaultTreeModel model = (DefaultTreeModel) wayTree.getModel();
		
		for(int i = 0; i < 9; ++i){
			
			for(int j = model.getChildCount(routes[i])-1; j >= 0; --j)
				model.removeNodeFromParent((MutableTreeNode) routes[i].getChildAt(j));
		
		}
		
	}

}
