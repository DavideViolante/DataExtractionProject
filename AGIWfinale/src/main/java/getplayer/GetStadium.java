package getplayer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.lang.StringEscapeUtils;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class GetStadium {

	private PrintWriter writer;
	String expression_name;
	String expression_stadium;	
	String site;

	public GetStadium() {
		this.expression_name = "//div[@class='spielername-profil']";
		this.expression_stadium = "//div[@class='box-personeninfos']//th[text()='Stadium:']/../td/a";	
		this.site = "http://www.transfermarkt.com/atletico-de-madrid/startseite/verein/";

		try {
			this.writer = new PrintWriter("stadi/stadi_24.txt", "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public void getStadium() {
		int cont = 48918;
		int end = cont+2000;

		while(cont<end) {
			try {
				Iteration(getUrlSource(site+cont));
			}
			catch(Exception e) {
				System.out.println(cont+" non esiste, continuo.");
			}
			cont++;
		}
		writer.close();
	}

	public void Iteration(String site) {

		TagNode tagNode = new HtmlCleaner().clean(site);
		Document doc = null;
		try {
			doc = new DomSerializer(new CleanerProperties()).createDOM(tagNode);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		XPath xpath = XPathFactory.newInstance().newXPath();
		try {
			NodeList name = (NodeList) xpath.evaluate(expression_name, doc,XPathConstants.NODESET);			
			NodeList stadium = (NodeList) xpath.evaluate(expression_stadium, doc,XPathConstants.NODESET);			
			String sname = StringEscapeUtils.unescapeHtml(name.item(0).getTextContent());
			String sstadium = StringEscapeUtils.unescapeHtml(stadium.item(0).getTextContent());
			System.out.println(sname);
			System.out.println(sstadium);
			writer.print(sname+" ");
			writer.print(sstadium);
			writer.println();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
	}

	private String getUrlSource(String url) throws IOException {

		URL yahoo = new URL(url);
		URLConnection yc = yahoo.openConnection();
		yc.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
		BufferedReader in = new BufferedReader(new InputStreamReader(
				yc.getInputStream(), "UTF-8"));
		String inputLine;
		StringBuilder a = new StringBuilder();
		while ((inputLine = in.readLine()) != null)
			a.append(inputLine);
		in.close();

		return a.toString();
	}

	public static void main(String[] args) {
		new GetStadium().getStadium();
	}
}