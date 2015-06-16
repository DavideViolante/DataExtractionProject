package getplayer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerMap {

	private Map<String,String> player2nationality=new HashMap<String,String>();
	private Map<String,String> nationality2keyFreeBase=new HashMap<String,String>();

	public PlayerMap(String trmkt, String frbs) throws IOException{

		BufferedReader br = new BufferedReader(new FileReader(trmkt));
		try {
			String line;
			while ( (line=br.readLine())!= null) {
				String parts[]=line.split("\t");
				//part[1] è il giocatore, [0] è la nazionaliotà
				player2nationality.put(parts[1].toLowerCase().replace(" ", ""), parts[0]);
				//player2nationality.put(parts[1], parts[0]);
			}
		} finally {
			br.close();
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
		System.out.println(nationality2keyFreeBase);
	}


	@SuppressWarnings("unused")
	private void getNationality(String nameFile, String nameFileOutput, String nameFileOutput2) throws IOException{
		PrintWriter writer = new PrintWriter(nameFileOutput);
		PrintWriter writer2 = new PrintWriter(nameFileOutput2);

		BufferedReader br = new BufferedReader(new FileReader(nameFile));
		try {
			String line;
			List<String>playerNoNation=new ArrayList <String>();
			while ( (line=br.readLine())!= null) {
				String parts[]=line.split("\t");
				//part[1] è il giocatore su freebase senza nazionalità
				String nationality=player2nationality.get(parts[1].toLowerCase().replace(" ", ""));
				//String nationality=player2nationality.get(parts[1]);
				playerNoNation.add(parts[1].toLowerCase().replace(" ", ""));
				//trmk ha la nazionalità di quel giocatore?
				if(nationality!=null&&!nationality.isEmpty()){
					//String key=nationality2keyFreeBase.get(nationality.toLowerCase().replace(" ", ""));
					//freebase possiede quella nazionalità?
					String key=nationality2keyFreeBase.get(nationality);
					if(key!=null){
						//completo tripla
						writer.println(parts[0]+"\t"+parts[1]+"\t"+key +"\t"+nationality+"\t"+"sports.sports_team.arena_stadium");
					}
					else {
						//ne scrivo una nuova
						writer2.println(parts[0]+"\t"+parts[1]+"\t"+nationality+"\t"+"sports.sports_team.arena_stadium");
					}
				}
			}

			for(String player :player2nationality.keySet()){
				if(!playerNoNation.contains(player)){
					String nationality=player2nationality.get(player);	
					if(nationality!=null&&!nationality.isEmpty()){
						//String key=nationality2keyFreeBase.get(nationality.toLowerCase().replace(" ", ""));
						String key=nationality2keyFreeBase.get(nationality);
						if(key!=null){
							writer2.println(player+"\t"+key +"\t"+nationality+"\t"+"sports.sports_team.arena_stadium");
						}
						else
							writer2.println(player+"\t"+nationality+"\t"+"sports.sports_team.arena_stadium");
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
		/*try {
			PlayerMap pm=new PlayerMap("transfermarkt_corretti.txt","countries.tsv");
			pm.getNationality("soccerplayers_Nonationality.tsv","triple_output2.txt","triple_with_no_key.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
		HashMap<String, String> hw = new HashMap<String, String>();
		hw.put("ciao", "33");
		hw.put("ciao", "22");
		System.out.println(hw);
		
	}
}
