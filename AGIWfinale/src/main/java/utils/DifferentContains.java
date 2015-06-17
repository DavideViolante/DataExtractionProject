package utils;

import java.util.HashMap;
import java.util.Map;

public class DifferentContains {

	public boolean contains(String toCorrect_sw,String parts_frbs1_sw, StopWord sw) {

		int match = 0;
		String w1,w2;

		if(toCorrect_sw.split(" ").length < parts_frbs1_sw.split(" ").length) {
			w1 = toCorrect_sw;
			w2 = parts_frbs1_sw;
		}
		else {
			w1 = parts_frbs1_sw;
			w2 = toCorrect_sw;
		}

		Map<String,String> map = new HashMap<String, String>();
		String parts[] = w2.split(" ");
		for(String part : parts)
			map.put(part, "");
		
		String parts2[] = w1.split(" ");
		for(String part : parts2)
			if(map.containsKey(part))
				match++;
		
		return match==parts2.length;

	}

}
