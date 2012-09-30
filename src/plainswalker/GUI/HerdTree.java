//Simple class containing a tree of all herds
//Daanyaal du Toit
//09 September 2012

package plainswalker.GUI;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;

public class HerdTree {
	
	protected JTree herdTree;
	DefaultMutableTreeNode root = new DefaultMutableTreeNode("Herds");
	protected DefaultMutableTreeNode[] herds = new DefaultMutableTreeNode[9];
	
	public HerdTree(){
		
		for(int i = 0; i < 9; ++i){
			
			herds[i] = new DefaultMutableTreeNode("Herd " + (i+1));
			root.add(herds[i]);
			
		}
		
		herdTree = new JTree(root);
		
	}
	
	//Clears all
	public void reset(){
		
		DefaultTreeModel model = (DefaultTreeModel) herdTree.getModel();
		
		for(int i = 0; i < 9; ++i){
			
			for(int j = model.getChildCount(herds[i])-1; j >= 0; --j)
				model.removeNodeFromParent((MutableTreeNode) herds[i].getChildAt(j));
		
		}
		
	}

}
