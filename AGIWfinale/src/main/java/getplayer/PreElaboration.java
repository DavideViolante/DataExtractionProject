package getplayer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import org.simmetrics.StringMetric;
import org.simmetrics.StringMetrics;

public class PreElaboration {

	private HashMap<String,String> word2correct;
	private BufferedReader trmkt_reader;
	StringMetric metric = StringMetrics.levenshtein(); 

	public PreElaboration(String transfermarkt, String freebase) throws IOException {
		this.word2correct = new HashMap<String, String>();
		this.trmkt_reader = new BufferedReader(new FileReader(transfermarkt));
	}

	public void wordCorrector() throws IOException {

		PrintWriter pw = new PrintWriter("transfermarkt_corretti.txt");

		String lt;
		while((lt = trmkt_reader.readLine())!=null) {
			//parts_trmkt[2] è lo stadio, parts_trmkt[1] è il club
			String parts_trmkt[] = lt.split("\t");
			//passo parts_trmkt[2] e mi torna quello corretto
			String corretta = word2correct.get(parts_trmkt[0]);
			if(corretta==null) {
				corretta = getCorrect(parts_trmkt[0],"countries.tsv");
				this.word2correct.put(parts_trmkt[0], corretta);
			}
			pw.println(corretta+"\t"+parts_trmkt[1]);
		}
		pw.close();
		trmkt_reader.close();
	}

	public String getCorrect(String daCorreggere,String frbs_file) throws IOException {
		BufferedReader frbs_reader = new BufferedReader(new FileReader(frbs_file));	
		String lf;
		float v_max = 0;
		String s_max = daCorreggere;
		while((lf = frbs_reader.readLine())!=null) {
			String parts_frbs[] = lf.split("\t"); //parts_frbs[1] è lo stadio
			float cosSim = metric.compare(daCorreggere, parts_frbs[1]);
			if(parts_frbs[1].contains(daCorreggere)||daCorreggere.contains(parts_frbs[1])||cosSim>=0.8) {
				if(cosSim>v_max) {
					v_max = cosSim;
					s_max = parts_frbs[1];
				}
			}	
		}
		frbs_reader.close();
		return s_max;
	}

	public static void main(String[] args) throws IOException {
		new PreElaboration("Players2.txt", "countries.tsv").wordCorrector();
	}
}
