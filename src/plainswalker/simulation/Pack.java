package plainswalker.simulation;

import java.io.Serializable;
import java.util.ArrayList;

public class Pack implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	ArrayList<Predator> preds = new ArrayList<Predator>();

	public ArrayList<Predator> getPreds() {
		return preds;
	}

}
