//Simple class containing a tree of all herds
//Daanyaal du Toit
//09 September 2012

package plainswalker.GUI;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class HerdTree {
	
	protected JTree herdTree;
	protected DefaultMutableTreeNode[] herds = new DefaultMutableTreeNode[10];
	
	public HerdTree(){
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Herds");
		
		for(int i = 0; i < 9; ++i){
			
			herds[i] = new DefaultMutableTreeNode("Herd " + (i+1));
			root.add(herds[i]);
			
		}
		
		herds[9] = new DefaultMutableTreeNode("Unassigned");
		root.add(herds[9]);
		herdTree = new JTree(root);
		
	}

}
