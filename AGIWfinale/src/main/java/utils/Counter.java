package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Counter {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		BufferedReader br1 = new BufferedReader(new FileReader("stadi/Stadium_correct_transfermarkt.txt"));
		BufferedReader br2 = new BufferedReader(new FileReader("stadi/teams_NOstadium.tsv"));

		List<String> clibtr = new ArrayList<String>();
		List<String> clibfr = new ArrayList<String>();

		List<String> aaa = new ArrayList<String>();

		
		String s,s1;
		while((s=br1.readLine())!=null)
			clibtr.add(s.split("\t")[1]);
		while((s1=br2.readLine())!=null)
			clibfr.add(s1.split("\t")[1]);
		
		int cont=0;
		for(String parte : clibfr)
			if(clibtr.contains(parte))
				aaa.add(parte);
		
		System.out.println(cont);
		
		
	}
	
}
