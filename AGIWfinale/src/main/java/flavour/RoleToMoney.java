package flavour;

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

public class RoleToMoney {

	private String expression_name1;
	private String expression_name2;
	private String expression_role1;
	private String expression_role2;
	private String expression_money1;
	private String expression_money2;
	
	public RoleToMoney() {
		this.expression_name1 = "//*[@id='yw1']//table[@class='items']//tr[";
		this.expression_name2 = "]//table[@class='inline-table']//a[@class='spielprofil_tooltip']";
		this.expression_money1 = "//*[@id='yw1']//table[@class='items']//tr[";
		this.expression_money2 = "]//td[6]";	
		this.expression_role1 = "//*[@id='yw1']//table[@class='items']//tr[";
		this.expression_role2 = "]//table[@class='inline-table']//tr[2]";	
	}

	public void getInfos(String toWrite, String site) throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter(toWrite, "UTF-8");

		String page = "/page/";
		for (int npage = 1; npage<=4; npage++) {
			for(int nrow = 1; nrow<=25; nrow++) {
				try {
					String expression_name = this.expression_name1+nrow+this.expression_name2;
					String expression_role = this.expression_role1+nrow+this.expression_role2;
					String expression_money = this.expression_money1+nrow+this.expression_money2;		
					Iteration(getUrlSource(site+page+npage),expression_name,expression_role,expression_money,writer);
				}
				catch(Exception e) {
					System.out.println("Errore 404");
				}
			}
		}
		writer.close();
	}

	public void Iteration(String site,String expression_name,String expression_role,String expression_money, PrintWriter writer) {
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
			NodeList role = (NodeList) xpath.evaluate(expression_role, doc,XPathConstants.NODESET);			
			NodeList money = (NodeList) xpath.evaluate(expression_money, doc,XPathConstants.NODESET);			
			String sname = StringEscapeUtils.unescapeHtml(name.item(0).getTextContent()).trim();
			String srole = StringEscapeUtils.unescapeHtml(role.item(0).getTextContent()).trim();
			String smoney = StringEscapeUtils.unescapeHtml(money.item(0).getTextContent()).trim();
			System.out.println(sname);
			System.out.println(srole);
			System.out.println(smoney);
			writer.println(sname+"\t"+srole+"\t"+smoney);
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

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		RoleToMoney rtm = new RoleToMoney();
		rtm.getInfos("Dati_money/player_money_bundesliga.txt","http://www.transfermarkt.com/bundesliga/marktwerte/wettbewerb/L1/pos//detailpos//altersklasse/alle");
		rtm.getInfos("Dati_money/player_money_ligue_1.txt","http://www.transfermarkt.com/ligue-1/marktwerte/wettbewerb/FR1/pos//detailpos//altersklasse/alle");
		rtm.getInfos("Dati_money/player_money_premier_league.txt","http://www.transfermarkt.com/premier-league/marktwerte/wettbewerb/GB1/pos//detailpos//altersklasse/alle");
		rtm.getInfos("Dati_money/player_money_primera_division.txt","http://www.transfermarkt.com/primera-division/marktwerte/wettbewerb/ES1/pos//detailpos//altersklasse/alle");
		rtm.getInfos("Dati_money/player_money_serie_a.txt","http://www.transfermarkt.com/serie-a/marktwerte/wettbewerb/IT1/pos//detailpos//altersklasse/alle");
	}
}