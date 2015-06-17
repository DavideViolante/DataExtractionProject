package generalize;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import org.simmetrics.Metric;
import org.simmetrics.StringMetric;
import org.simmetrics.StringMetrics;

import utils.DifferentContains;
import utils.StopWord;

public class PreElaboration {

	private String freebaseObject; 
	private String freebaseSubject; 
	private HashMap<String,String> object2correct; 
	private HashMap<String,String> subject2correct; 
	private BufferedReader trmkt_reader;
	private StringMetric metric = StringMetrics.levenshtein(); 
	private StopWord sw; 

	public PreElaboration(String transfermarkt, String freebaseObjects,String SubjectNoObject) throws IOException {
		this.object2correct = new HashMap<String, String>();
		this.subject2correct = new HashMap<String, String>();
		this.trmkt_reader = new BufferedReader(new FileReader(transfermarkt));
		this.freebaseSubject=SubjectNoObject;
		this.freebaseObject=freebaseObjects;
		this.sw = new StopWord("stopwords/stopwords.txt");
	}

	public void wordCorrector(String output) throws IOException {
		PrintWriter pw = new PrintWriter(output);
		String lt;
		while((lt = trmkt_reader.readLine())!=null) {

			String parts_trmkt[] = lt.split("\t");
			String correctNation = object2correct.get(parts_trmkt[0]);
			if(correctNation==null) {
				correctNation = getCorrect(parts_trmkt[0],freebaseObject);
				this.object2correct.put(parts_trmkt[0], correctNation);
			}			
			String correttaPlay = subject2correct.get(parts_trmkt[1]);
			if(correttaPlay==null) {
				correttaPlay = getCorrect(parts_trmkt[1],freebaseSubject);
				//System.out.println(parts_trmkt[1]+"\t"+correttaPlay);
				this.subject2correct.put(parts_trmkt[1], correttaPlay);
			}
			pw.println(correctNation+"\t"+correttaPlay);
		}
		pw.close();
		trmkt_reader.close();
	}

	public void wordCorrectorNoPlayer(String output) throws IOException {
		PrintWriter pw = new PrintWriter(output);
		String lt;
		while((lt = trmkt_reader.readLine())!=null) {

			String parts_trmkt[] = lt.split("\t");
			String correctNation = object2correct.get(parts_trmkt[0]);
			if(correctNation==null) {
				correctNation = getCorrect(parts_trmkt[0],freebaseObject);
				this.object2correct.put(parts_trmkt[0], correctNation);
			}			
			String correttaPlay = subject2correct.get(parts_trmkt[1]);
			if(correttaPlay==null) {
				correttaPlay = getCorrectPlayer(parts_trmkt[1],freebaseSubject);
				//System.out.println(parts_trmkt[1]+"\t"+correttaPlay);
				this.subject2correct.put(parts_trmkt[1], correttaPlay);
			}
			pw.println(correctNation+"\t"+correttaPlay);
		}
		pw.close();
		trmkt_reader.close();
	}

	public String getCorrect(String toCorrect,String frbs_file) throws IOException {
		BufferedReader frbs_reader = new BufferedReader(new FileReader(frbs_file));	
		String lf;
		float v_max = 0;
		String s_max = toCorrect;
		while((lf = frbs_reader.readLine())!=null) {
			String parts_frbs[] = lf.split("\t");
			String toCorrect_sw = sw.clean(toCorrect).toLowerCase().replaceAll("[.|,]","").replaceAll("-", " ");
			String parts_frbs1_sw = sw.clean(parts_frbs[1]).toLowerCase().replaceAll("[.|,]","").replaceAll("-", " ");
			float leven = metric.compare(toCorrect_sw,parts_frbs1_sw);
			if(new DifferentContains().contains(toCorrect_sw,parts_frbs1_sw,sw)||leven>=0.8) {
				if(leven>v_max) {
					v_max = leven;
					s_max = parts_frbs[1];
				}
			}	
		}
		frbs_reader.close();
		return s_max;
	}
	
	public String getCorrectPlayer(String toCorrect,String frbs_file) throws IOException {
		BufferedReader frbs_reader = new BufferedReader(new FileReader(frbs_file));	
		String lf;
		float v_max = 0;
		String s_max = toCorrect;
		while((lf = frbs_reader.readLine())!=null) {
			String parts_frbs[] = lf.split("\t");
			float cosSim = metric.compare(toCorrect.toLowerCase(), parts_frbs[1].toLowerCase());
			if(parts_frbs[1].contains(toCorrect)||toCorrect.contains(parts_frbs[1])||cosSim>=0.8) {
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
		new PreElaboration("Dati_stadi/club_stadio_transfermarkt.txt","Dati_stadi/architectures.tsv", "Dati_stadi/teams_NOstadium.tsv").wordCorrector("Dati_stadi/Stadium_correct_transfermarkt.txt");
		//new PreElaboration("nationality/nation_player_transfermarkt.txt","nationality/countries.tsv", "nationality/soccerplayers_Nonationality.tsv").wordCorrector("nationality/player_nationality_correct_transfermarkt.txt");
	}
}
