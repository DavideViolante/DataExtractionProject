package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Cleaner {

	public void cleanStadium() throws IOException {
		PrintWriter pw = new PrintWriter("club_stadio_transfermarkt.txt");
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
	
	public static void main(String[] args) throws IOException {
		new Cleaner().cleanStadium();
	}
}
