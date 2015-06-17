package generalize;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Mapper { 

	private Map<String,List<String>> subject2objects =new HashMap<String,List<String>>();
	private Map<String,String> object2keyFreeBase=new HashMap<String,String>();
	private String relation;

	public Mapper(String trmkt, String frbs, String relation) throws IOException{
		this.relation = relation;
		BufferedReader br = new BufferedReader(new FileReader(trmkt));
		try {
			String line;
			while ( (line=br.readLine())!= null) {
				String parts[]=line.split("\t");
				String subject = parts[1].trim();
				if(subject2objects .containsKey(subject))
					subject2objects .get(subject).add(parts[0]);
				else{
					List<String> objects = new ArrayList<String>();
					objects.add(parts[0]);
					subject2objects .put(subject, objects);
				}
			}
		} finally {
			br.close();
		}
		BufferedReader br2 = new BufferedReader(new FileReader(frbs));
		try {
			String line;
			while ( (line=br2.readLine())!= null) {
				String parts[]=line.split("\t");
				object2keyFreeBase.put(parts[1], parts[0]);
			}
		} finally {
			br2.close();
		}
	}

	public void getObject(String nameFile, String nameFileOutput, String nameFileOutput2) throws IOException{
		PrintWriter writer = new PrintWriter(nameFileOutput);
		PrintWriter writer2 = new PrintWriter(nameFileOutput2);

		BufferedReader br = new BufferedReader(new FileReader(nameFile));
		try {
			String line;
			List<String> subjectNoObject =new ArrayList <String>();
			while ( (line=br.readLine())!= null) {
				String parts[]=line.split("\t");
				String subject = parts[1].trim();
				List<String> objects = subject2objects .get(subject);
				if(objects!=null && !objects.isEmpty()){
					 subjectNoObject .add(parts[1]);
					for(String object : objects) {
						String key=object2keyFreeBase.get(object);
						if(key!=null){
							writer.println(parts[0]+"\t"+parts[1]+"\t"+key +"\t"+object+"\t"+relation);
						}
						else {
							//writer2.println(parts[0]+"\t"+parts[1]+"\t"+object+"\t"+relation);
							writer2.println(parts[0]+"\t"+parts[1]+"\t"+" "+"\t"+object+"\t"+relation);
						}
					}
				}
			}
			for(String subject : subject2objects .keySet()){
				if(! subjectNoObject .contains(subject)){
					List<String> objects = subject2objects .get(subject);
					if(objects!=null&&!objects.isEmpty()){
						for(String object : objects) {
							String key=object2keyFreeBase.get(object);
							if(key!=null){
								//writer2.println(subject+"\t"+key +"\t"+object+"\t"+relation);
								writer2.println(" "+"\t"+subject+"\t"+key +"\t"+object+"\t"+relation);
							}
							else{
								//writer2.println(subject+"\t"+object+"\t"+relation);
								writer2.println(" "+"\t"+subject+"\t"+" "+"\t"+object+"\t"+relation);
							}
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
			Mapper m=new Mapper("stadi/Stadium_correct_transfermarkt.txt","stadi/architectures.tsv","sports.sports_team.arena_stadium");
			m.getObject("stadi/teams_NOstadium.tsv","Triple_Stadio_Completate.txt","Triple_Stadio_Completate_senzachiave.txt");
		} catch (IOException e) {
			e.printStackTrace();
		} 

	}
}
