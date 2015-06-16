package getplayer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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

public class getPlayer  {


	public static void main(String[] args) throws Exception {
		//i=5958
		int i=263787;
		int exc;
		PrintWriter writer = new PrintWriter("Players9.txt", "UTF-8");
		//writer.println("The first line");
		//writer.println("The second line");
		
		//SavePlayer(i,writer);
		//writer.close();
		
		while(i<263787+17000){
			exc=0;
			i++;
			try { 
			SavePlayer(i,writer);
			}
			catch(Exception e){
				exc++;	
				if(exc>30){
				writer.close();
				return;
				}
			}
		}
		writer.close();
		
	}

	public static void SavePlayer(int i,PrintWriter writer) throws IOException {

		String stringa=getUrlSource("http://www.transfermarkt.com/francesco-totti/profil/spieler/"+i);
		Document doc = null;
		CleanerProperties props = new CleanerProperties();
		props.setRecognizeUnicodeChars(false);


		TagNode tagNode = new HtmlCleaner(props).clean(stringa);

		try {
			doc = new DomSerializer(props).createDOM(tagNode);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		XPath xpath = XPathFactory.newInstance().newXPath();
		String expression="//span[@itemprop='nationality']";
		try {
			NodeList nodes = (NodeList) xpath.evaluate(expression, doc,XPathConstants.NODESET);
			System.out.println(StringEscapeUtils.unescapeHtml(nodes.item(0).getTextContent()));
			writer.print(StringEscapeUtils.unescapeHtml(nodes.item(0).getTextContent()));

		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		
		writer.print("\t");
		
		String expression2="//div[@class='spielername-profil']";
		try {
			NodeList nodes = (NodeList) xpath.evaluate(expression2, doc,XPathConstants.NODESET);
			System.out.println(StringEscapeUtils.unescapeHtml(nodes.item(0).getTextContent()));
			writer.println(StringEscapeUtils.unescapeHtml(nodes.item(0).getTextContent()));
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
	}


	private static String getUrlSource(String url) throws IOException {

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

}