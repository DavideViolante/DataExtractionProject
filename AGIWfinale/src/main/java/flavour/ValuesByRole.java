package flavour;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import utils.Player;

public class ValuesByRole {

	public Map<String,List<Player>> createMap(String inputvalues) throws NumberFormatException, IOException {
		Map<String,List<Player>> role2players = new TreeMap<String, List<Player>>();
		BufferedReader br = new BufferedReader(new FileReader(inputvalues));
		String row;
		while((row=br.readLine()) != null) {
			String parts[] = row.split("\t");
			Player p = new Player(Double.parseDouble(parts[2].split(" ")[0].replace(",", ".")), parts[0]);
			if(!role2players.containsKey(parts[1]))
				role2players.put(parts[1], new ArrayList<Player>());
			role2players.get(parts[1]).add(p);
		}
		br.close();
		return role2players;
	}

	public void calculate(String toWrite, String inputvalues) throws NumberFormatException, IOException {
		Map<String,List<Player>> m1 = createMap(inputvalues);

		PrintWriter pw = new PrintWriter(toWrite);
		pw.println("Role - Average Of Values (Mill. €) - Top Value (Mill. €) - Lower Value (Mill. €)");
		pw.println();
		for(String s : m1.keySet()) {
			Double sum = 0.0;
			List<Player> players = m1.get(s);
			Player minpl = players.get(0);
			Player maxpl = players.get(players.size()-1);
			for(Player p : players) 
				sum+=p.getMoney();
			pw.println(s+" - "+Math.floor((sum/players.size())*100)/100+" - "+minpl.getName()+" ("+minpl.getMoney()+")"+" - "+maxpl.getName()+" ("+maxpl.getMoney()+")");
		}
		pw.close();
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		new ValuesByRole().calculate("Risultati_money/Bundesliga.txt", "Dati_money/player_money_bundesliga.txt");
		new ValuesByRole().calculate("Risultati_money/Ligue_1.txt", "Dati_money/player_money_ligue_1.txt");
		new ValuesByRole().calculate("Risultati_money/Premier_league.txt", "Dati_money/player_money_premier_league.txt");
		new ValuesByRole().calculate("Risultati_money/Primera_division.txt", "Dati_money/player_money_primera_division.txt");
		new ValuesByRole().calculate("Risultati_money/Serie_a.txt", "Dati_money/player_money_serie_a.txt");
	}
}
