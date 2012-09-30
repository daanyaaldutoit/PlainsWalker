//Handles pathfinding for a simulation
//Daanyaal du Toit
//30 September 2012

package plainswalker.simulation;

import java.awt.Point;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class AStar implements Serializable{

	private static final long serialVersionUID = 1L;

	//Encapsulates AStar info
	protected class Node implements Comparable<Node>{
		
		//Grid Coordinates
		protected int x;
		protected int y;
		
		protected double g;			//Distance travelled
		protected double h;			//Estimated heuristic
		protected Node parent;
		
		public Node(int x, int y){
			
			this.x = x;
			this.y = y;
			
		}
		
		public boolean equals(Object o){
			
			if(o instanceof Node)
				return ((Node) o).x == x && ((Node) o).y == y;
			return false;
			
		}

		public int compareTo(Node n) {
			
			if(g+h < n.g+n.h)
				return -1;
			else if(g+h == n.g+n.h)
				return 0;
			else
				return 1;
		}
		
		public String toString(){
			
			return "(" + x + ", " + y +")";
			
		}
		
	}
	
	private Simulation sim;
	private boolean[][] visited;
	
	public AStar(){}
	
	public AStar(Simulation s){
		
		sim = s;
		visited = new boolean[s.tiles.length][s.tiles[0].length];
		
	}
	
	//Return least cost path
	public LinkedList<Node> getPath(int startX, int startY, int endX, int endY){
		
		//reset visited table
		visited = new boolean[visited.length][visited[0].length];
		
		for(int i = 0; i < visited.length; ++i)
			for(int j = 0; j < visited[0].length; ++j)
				visited[i][j] = false;
		
		PriorityQueue<Node> nodes = new PriorityQueue<Node>();
		
		Node start = new Node(startX, startY);
		nodes.add(start);
		
		while (!nodes.isEmpty()){
			
			Node cur = nodes.poll();
			if(cur.equals(new Node(endX, endY))){
				
				//return completed path
				LinkedList<Node> path = new LinkedList<Node>();
				
				while(cur.parent != null){
					
					path.addFirst(cur);
					cur = cur.parent;
					
				}
				
				return path;
				
			}
			else
			{
				
				visited[cur.y][cur.x] = true;
				
				//process neighbours
				for(int i = Math.max(cur.y-1, 0); i < Math.min(cur.y+2, sim.tiles.length); ++i)
					for(int j = Math.max(cur.x-1, 0); j < Math.min(cur.x+2, sim.tiles[0].length); ++j){
						
						//Check if tile visited or impassable
						if(!visited[i][j] && !nodes.contains(new Node(j, i)) && sim.tiles[i][j].passable){
							
							Node next = new Node(j, i);
							next.g = cur.g+getCost(cur, next);
							next.h = getHeuristic(next, new Node(endX, endY));
							next.parent = cur;
							nodes.add(next);
							
						}
						
					}
				
			}
			
		}
		
		return null;
		
	}
	
	//Cost of travelling to a neightbouring node
	public double getCost(Node cur, Node next){
		
		return 1+sim.terrain.heights.heightgrid[next.y][next.x] - sim.terrain.heights.heightgrid[cur.y][cur.x];
		
	}
	
	//Estimate cost of traversal
	public double getHeuristic(Node next, Node end){
		
		//Use euclidean heuristic for distance
		return new Point(next.x, next.y).distance(new Point(end.x, end.y));
		
	}
	
}
