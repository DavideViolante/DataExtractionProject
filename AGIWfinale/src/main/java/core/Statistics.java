package core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Statistics {

	private String fbIncomplete;
	private String tmComplete;

	public Statistics(String fbIncomplete, String tmComplete) {
		this.fbIncomplete = fbIncomplete;
		this.tmComplete = tmComplete;
	}

	public void getStatistics(String relation, String file_writer,String file_writer_triple, String statistic_output)
			throws IOException {
		PrintWriter writer = new PrintWriter(file_writer);
		PrintWriter writer_triple = new PrintWriter(file_writer_triple);
		PrintWriter statistic = new PrintWriter(statistic_output);

		BufferedReader brfb = new BufferedReader(new FileReader(fbIncomplete));
		BufferedReader brtm = null;
		int i = 0;
		int j = 0;

		String linefb;
		String linetm;
		boolean trovato;

		while ((linefb = brfb.readLine()) != null) {
			trovato = false;
			i++;
			String partsfb[] = linefb.split("\t");
			String keySubjectFb = partsfb[0].trim();
			brtm = new BufferedReader(new FileReader(tmComplete));
			while ((linetm = brtm.readLine()) != null) {
				String partstm[] = linetm.split("\t");
				String keySubjectTm = partstm[0].trim();

				if (keySubjectFb.equals(keySubjectTm)) {
					trovato = true;
					j++;
					writer.println(partsfb[0] + "\t" + partsfb[1] + "\t"
							+ partstm[2] + "\t" + partstm[3] + "\t" + relation);
					writer_triple.println(partsfb[0] + "\t" + partsfb[1] + "\t"
							+ partstm[2] + "\t" + partstm[3] + "\t" + relation);
					break;
				}
			}
			if (!trovato) {
				writer.println(partsfb[0] + "\t" + partsfb[1]);
			}
		}

		statistic.println(i + "," + j);
		writer.close();
		writer_triple.close();
		brfb.close();
		brtm.close();
		statistic.close();
	}

}
