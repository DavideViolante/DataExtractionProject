package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class StopWord {

	private Map<String, String> stopwords;

	public StopWord(String file_stopwords) throws IOException {
		this.stopwords = new HashMap<String, String>();
		BufferedReader br = new BufferedReader(new FileReader(file_stopwords));
		String s;
		while ((s = br.readLine()) != null)
			this.stopwords.put(s, "");
		br.close();
	}

	public boolean contains(String w) {
		return this.stopwords.containsKey(w);
	}

	public String clean(String w) {
		String toReturn = "";
		String parts[] = w.split(" ");

		for (String part : parts)
			if (!this.stopwords.containsKey(part.toLowerCase()))
				toReturn += part + " ";
		return toReturn.trim();
	}
}
