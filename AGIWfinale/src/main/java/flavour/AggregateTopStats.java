package flavour;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class AggregateTopStats {

	public static void main(String[] args) throws IOException {
		PrintWriter writer = new PrintWriter("src/main/webapp/topExtraStats.txt");
		BufferedReader br1 = new BufferedReader(new FileReader("Risultati_money/Primera_division.txt"));
		BufferedReader br2 = new BufferedReader(new FileReader("Risultati_money/Premier_league.txt"));
		BufferedReader br3 = new BufferedReader(new FileReader("Risultati_money/Bundesliga.txt"));
		BufferedReader br4 = new BufferedReader(new FileReader("Risultati_money/Serie_a.txt"));
		BufferedReader br5 = new BufferedReader(new FileReader("Risultati_money/Ligue_1.txt"));
		try {
			// Primera Division
			writer.println("State,Attacking Midfield,Central Midfield,Centre Back,Centre Forward,Defensive Midfield,Keeper,Left Midfield,Left Wing,Left Back,Right Midfield,Right Wing,Right Back,Secondary Striker");
			writer.print("Primera Division,");
			String line = br1.readLine();
			line = br1.readLine();
			line = br1.readLine();
			String[] parts = line.split("-");

			int i = 0;
			while(line != null || i<13) {
				parts = line.split("-");
				writer.print(parts[1].trim()+",");
				line = br1.readLine();
				i++;
			}
			
			// Premier League
			writer.println();
			writer.print("Premier League,");
			
			line = br2.readLine();
			line = br2.readLine();
			line = br2.readLine();
			parts = line.split("-");

			i = 0;
			while(line != null || i<13) {
				parts = line.split("-");
				writer.print(parts[1].trim()+",");
				line = br2.readLine();
				i++;
			}
			
			// Bundesliga
			writer.println();
			writer.print("Bundesliga,");
			
			line = br3.readLine();
			line = br3.readLine();
			line = br3.readLine();
			parts = line.split("-");

			i = 0;
			while(line != null || i<13) {
				parts = line.split("-");
				writer.print(parts[1].trim()+",");
				line = br3.readLine();
				i++;
			}
			
			// Serie A
			writer.println();
			writer.print("Serie A,");
			
			line = br4.readLine();
			line = br4.readLine();
			line = br4.readLine();
			parts = line.split("-");

			i = 0;
			while(line != null || i<13) {
				parts = line.split("-");
				writer.print(parts[1].trim()+",");
				line = br4.readLine();
				i++;
			}
			
			// Ligue 1
			writer.println();
			writer.print("Ligue 1,");
			
			line = br5.readLine();
			line = br5.readLine();
			line = br5.readLine();
			parts = line.split("-");

			i = 0;
			while(line != null || i<13) {
				parts = line.split("-");
				writer.print(parts[1].trim()+",");
				line = br5.readLine();
				i++;
			}

		} finally {
			br1.close();
			br2.close();
			br3.close();
			br4.close();
			br5.close();
			writer.close();
		}
		
	}

}
