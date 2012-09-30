//Simple class containing a tree of all packs
//Daanyaal du Toit
//24 September 2012

package plainswalker.GUI;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;

public class PackTree {

	protected JTree packTree;
	protected DefaultMutableTreeNode[] packs = new DefaultMutableTreeNode[9];
	
	public PackTree(){
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Packs");
		
		for(int i = 0; i < 9; ++i){
			
			packs[i] = new DefaultMutableTreeNode("Pack " + (i+1));
			root.add(packs[i]);
			
		}
		
		packTree = new JTree(root);
		
	}

	public void reset() {
		
		DefaultTreeModel model = (DefaultTreeModel) packTree.getModel();
		
		for(int i = 0; i < 9; ++i){
			
			for(int j = model.getChildCount(packs[i])-1; j >= 0; --j)
				model.removeNodeFromParent((MutableTreeNode) packs[i].getChildAt(j));
		
		}
	}
	
}
