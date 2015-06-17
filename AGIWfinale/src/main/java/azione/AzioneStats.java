package azione;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class AzioneStats extends Azione {

	@Override 
	public String esegui(HttpServletRequest request) throws ServletException, IOException, InterruptedException, ExecutionException {

		String esitoAzione = "statsKO";

		BufferedReader br1 = new BufferedReader(new FileReader("statistic-nationality.txt"));
	    try {
	        String line = br1.readLine();
	        String parts[] = line.split(",");
	        request.setAttribute("statsN", parts);
	        esitoAzione = "statsOK";
	    } finally {
	        br1.close();
	    }
	    
		BufferedReader br2 = new BufferedReader(new FileReader("statistic-stadium.txt"));
	    try {
	        String line = br2.readLine();
	        String parts[] = line.split(",");
	        request.setAttribute("statsS", parts);
	        esitoAzione = "statsOK";
	    } finally {
	        br2.close();
	    }

		return esitoAzione;
	}
}