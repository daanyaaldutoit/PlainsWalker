//A snapshot of all movable object positions
//Daanyaal du Toit
//25 September 2012

package plainswalker.simulation;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Frame {
	
	protected Herd[] herds = new Herd[9];
	protected Pack[] packs = new Pack[9];
	
	public Frame(Herd[] h, Pack[] p){
		
		for(int i = 0; i < 9; ++i){
			
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream out;
			try {
				out = new ObjectOutputStream(bos);
				out.writeObject(h[i]);
				out.flush();
				out.close();
				
				ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
				
				herds[i] = (Herd) in.readObject();
				
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			packs[i] = p[i];
			
		}
		
	}
	
	public Herd[] getHerds(){return herds;}
	
	public Pack[] getPacks(){return packs;}

}
