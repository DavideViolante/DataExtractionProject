package getplayer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerMap_modified {

	private Map<String,List<String>> player2nationality=new HashMap<String,List<String>>();
	private Map<String,String> nationality2keyFreeBase=new HashMap<String,String>();

	public PlayerMap_modified(String trmkt, String frbs) throws IOException{

		BufferedReader br = new BufferedReader(new FileReader(trmkt));
		try {
			String line;
			while ( (line=br.readLine())!= null) {
				String parts[]=line.split("\t");
				//String player = parts[1].toLowerCase().replace(" ", "");
				String player = parts[1].trim();
				//part[1] è il giocatore, [0] è la nazionaliotà
				if(player2nationality.containsKey(player))
					player2nationality.get(player).add(parts[0]);
				else{
					List<String> nationalities = new ArrayList<String>();
					nationalities.add(parts[0]);
					player2nationality.put(player, nationalities);
				}
				//player2nationality.put(parts[1], parts[0]);
			}
		} finally {
			br.close();
			System.out.println(player2nationality);
		}
		BufferedReader br2 = new BufferedReader(new FileReader(frbs));
		try {
			String line;
			while ( (line=br2.readLine())!= null) {
				String parts[]=line.split("\t");
				//nationality2keyFreeBase.put(parts[1].toLowerCase().replace(" ", ""), parts[0]);
				//parts[1] è la nazionalità su freebase, [0] è la chiave su frbs
				nationality2keyFreeBase.put(parts[1], parts[0]);
			}
		} finally {
			br2.close();
		}
		//System.out.println(nationality2keyFreeBase);
	}


	public void getNationality(String nameFile, String nameFileOutput, String nameFileOutput2) throws IOException{
		PrintWriter writer = new PrintWriter(nameFileOutput);
		PrintWriter writer2 = new PrintWriter(nameFileOutput2);

		BufferedReader br = new BufferedReader(new FileReader(nameFile));
		try {
			String line;
			List<String>playerNoNation=new ArrayList <String>();
			while ( (line=br.readLine())!= null) {
				String parts[]=line.split("\t");
				//part[1] è il giocatore su freebase senza nazionalità
				//String player = parts[1].toLowerCase().replace(" ", "");
				String player = parts[1].trim();
				//System.out.println(player);
				//qui get(0) su lista e poi tolgo primo elem dalla lista
				//String nationality=player2nationality.get(parts[1]);
				List<String> nationalities = player2nationality.get(player);
				//trmk ha la nazionalità di quel giocatore?
				if(nationalities!=null && !nationalities.isEmpty()){
					playerNoNation.add(parts[1]);
					//System.out.println(player2nationality.get(player)+player);
					//player2nationality.put(player, new ListUtils().remove(player2nationality.get(player),nationality));
					//System.out.println(player2nationality.get(player)+player);
					//String key=nationality2keyFreeBase.get(nationality.toLowerCase().replace(" ", ""));
					//freebase possiede quella nazionalità?
					for(String nationality : nationalities) {
						String key=nationality2keyFreeBase.get(nationality);
						if(key!=null){
							//completo tripla
							writer.println(parts[0]+"\t"+parts[1]+"\t"+key +"\t"+nationality+"\t"+"people.person.nationality");
						}
						else {
							//nazione senza chiave
							writer2.println(parts[0]+"\t"+parts[1]+"\t"+nationality+"\t"+"people.person.nationality");
						}
					}
				}
			}

			for(String player :player2nationality.keySet()){
				if(!playerNoNation.contains(player)){
					List<String> nationalities = player2nationality.get(player);
					if(nationalities!=null&&!nationalities.isEmpty()){
						for(String nationality : nationalities) {
							//String key=nationality2keyFreeBase.get(nationality.toLowerCase().replace(" ", ""));
							String key=nationality2keyFreeBase.get(nationality);
							if(key!=null){
								writer2.println(player+"\t"+key +"\t"+nationality+"\t"+"people.person.nationality");
							}
							else
								writer2.println(player+"\t"+nationality+"\t"+"people.person.nationality");
						}
					}

				}

			}
		} finally {
			br.close();
			writer.close();
			writer2.close();
		}	
	}

	public static void main(String[]args){
		try {
			PlayerMap_modified pm=new PlayerMap_modified("transfermarkt_corretti_prova.txt","countries.tsv");
			pm.getNationality("soccerplayers_Nonationality.tsv","triple_output_modif.txt","triple_with_no_key_modif.txt");
		} catch (IOException e) {
			e.printStackTrace();
		} 

	}
}
