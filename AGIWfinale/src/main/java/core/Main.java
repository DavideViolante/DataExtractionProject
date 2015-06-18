package core;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException{

		new PreElaboration("Dati_stadi/club_stadio_transfermarkt.txt","Dati_stadi/architectures.tsv", "Dati_stadi/teams_NOstadium.tsv").wordCorrector("Dati_stadi/Stadium_correct_transfermarkt.txt");
		new PreElaboration("Dati_nationality/Players18.txt","Dati_nationality/countries.tsv", "Dati_nationality/soccerplayers_Nonationality.tsv").wordCorrector("Dati_nationality/player_nationality_correct_transfermarkt.txt");
		
		Mapper m = new Mapper("Dati_stadi/Stadium_correct_transfermarkt.txt","Dati_stadi/architectures.tsv","sports.sports_team.arena_stadium");
		m.getObject("Dati_stadi/teams_NOstadium.tsv","Dati_stadi/completed_triple_stadio_duplicati.txt","Risultati_stadi/completed_triple_stadio_senzachiave.txt");

		Mapper m1 = new Mapper("Dati_nationality/player_nationality_correct_transfermarkt.txt","Dati_nationality/countries.tsv","people.person.nationality");
		m1.getObject("Dati_nationality/soccerplayers_Nonationality.tsv","Dati_nationality/completed_triple_nation_duplicati.txt","Risultati_nationality/completed_triple_nation_senzachiave.txt");

		Statistics statStadium = new Statistics ("Dati_stadi/teams_NOstadium.tsv","Dati_stadi/completed_triple_stadio_duplicati.txt");
		statStadium.getStatistics("sports.sports_team.arena_stadium","Risultati_stadi/free_base_completed_stadium.txt", "Risultati_stadi/completed_triple_stadium","statistic-stadium.txt");
	
		Statistics statNation = new Statistics ("Dati_nationality/soccerplayers_Nonationality.tsv","Dati_nationality/completed_triple_nation_duplicati.txt");
		statNation.getStatistics("people.person.nationality","Risultati_nationality/free_base_completed_nation.txt", "Risultati_nationality/completed_triple_nation.txt","statistic-nationality.txt");

	}
}
