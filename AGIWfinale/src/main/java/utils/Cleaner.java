package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Cleaner {

	public void cleanStadium() throws IOException {
		PrintWriter pw = new PrintWriter("stadi/club_stadio_transfermarkt.txt");
		String s;
		for(int i=1; i<=24; i++) {
			BufferedReader br = new BufferedReader(new FileReader("stadi/stadi_"+i+".txt"));			
			while((s=br.readLine()) != null) {
				String parts[] = s.split("    ");
				pw.println(parts[2]+"\t"+parts[1]);
			}
			br.close();
		}
		pw.close();
	}

	public void cleanPlayer() throws IOException {
		PrintWriter pw = new PrintWriter("Dati_nationality/PlayersCompleto.txt");
		String s;
		//il 17 è da aggiungere
		for(int i=1; i<=18; i++) {
			BufferedReader br = new BufferedReader(new FileReader("Dati_nationality/Players"+i+".txt"));			
			while((s=br.readLine()) != null) {
				String parts[] = s.split("\t");
				pw.println(parts[0]+"\t"+parts[1]);
			}
			br.close();
		}
		pw.close();
	}
	public static void main(String[] args) throws IOException {
		//new Cleaner().cleanStadium();
		new Cleaner().cleanPlayer();
	}
}
