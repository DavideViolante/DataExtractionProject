package extractor;
import java.io.IOException;
import java.io.PrintWriter;
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

import utils.GetSource;

public class GetPlayer  {

	public static void SavePlayer(int i,PrintWriter writer) throws IOException {

		String stringa = new GetSource().getUrlSource("http://www.transfermarkt.com/francesco-totti/profil/spieler/"+i);
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
	
	public static void main(String[] args) throws Exception {

		int i = 0;
		int exc;
		PrintWriter writer = new PrintWriter("Players1.txt", "UTF-8");

		while (true) {
			exc = 0;
			i++;
			try {
				SavePlayer(i, writer);
			} catch (Exception e) {
				exc++;
				if (exc > 30) {
					writer.close();
					return;
				}
			}
		}
	}

}