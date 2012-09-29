//Simple class containing a tree of all herds
//Daanyaal du Toit
//09 September 2012

package plainswalker.GUI;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;

public class HerdTree {
	
	protected JTree herdTree;
	DefaultMutableTreeNode root = new DefaultMutableTreeNode("Herds");
	protected JPopupMenu herdPop;
	protected DefaultMutableTreeNode[] herds = new DefaultMutableTreeNode[9];
	
	public HerdTree(){
		
		for(int i = 0; i < 9; ++i){
			
			herds[i] = new DefaultMutableTreeNode("Herd " + (i+1));
			root.add(herds[i]);
			
		}
		
		herdTree = new JTree(root);
		
		herdPop = new JPopupMenu("Hooray for Herds");
		
		JMenu h1 = new JMenu("Hooray");
		JMenuItem h2 = new JMenuItem("Hooray");
		h1.add(h2);
		
		herdPop.add(h1);
		herdTree.setComponentPopupMenu(herdPop);
		//herdTree.addMouseListener(new TreeListener());
		
	}
	
	//Clears all 
	public void reset(){
		
		DefaultTreeModel model = (DefaultTreeModel) herdTree.getModel();
		
		for(int i = 0; i < 9; ++i){
			
			for(int j = model.getChildCount(herds[i])-1; j >= 0; --j)
				model.removeNodeFromParent((MutableTreeNode) herds[i].getChildAt(j));
		
		}
		
	}
	
	/*public class TreeListener implements MouseListener{

		public void mouseClicked(MouseEvent e) {
			
			if(e.getClickCount() == 2){
				
				System.out.println("Double click");
				//System.out.println(herdTree.getComponentAt(e.getX(), e.getY()));
				
			}
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}	
		
	}*/

}
