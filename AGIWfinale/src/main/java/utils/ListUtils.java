package utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListUtils {
	
	public List<String> remove(List<String> nationalities, String toRemove) {
		List<String> toReturn = new ArrayList<String>();
		Iterator<String> it = nationalities.iterator();
		while(it.hasNext()) {
			String nat = it.next();
			if(nat.equals(toRemove))
				break;
			toReturn.add(nat);
		}
		while(it.hasNext()) {
			toReturn.add(it.next());
		}

		return toReturn;
	} 
	
}
