
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class lr {
	
	public static String[] getUrlList() throws IOException {
		Document d=Jsoup.connect("https://www.allsides.com/bias/bias-ratings").timeout(10000).get(); //connect to website
		Elements links = d.getElementsByAttributeValueStarting("href","/news-source/");
		String[] linkArray= new String[links.toArray().length];
		int i=0;
		for (Element link : links) {
			Document page=Jsoup.connect("https://www.allsides.com"+link).timeout(10000).get(); //connect to website
			Elements a = page.select("div.source-image");
			for (Element b : a.select("a")) {
				linkArray[i]=b.attr("href");
				break;
			}
			i++;
		}
		return linkArray;
	}
	
	public static void main(String[] args) throws IOException {
		Document d=Jsoup.connect("https://www.allsides.com/bias/bias-ratings").timeout(10000).get(); //connect to website
		
		System.out.println("NAMES");
		Elements names = d.getElementsByAttributeValueStarting("href","/news-source/");
		for (Element name : names) {
			System.out.println(name.text());
		}

		//get ratings
		System.out.println("RATINGS");;
		Elements icons = d.getElementsByAttributeValueStarting("alt","Political News Media Bias Rating:");
		for (Element element : icons) {
			String rating = (element.attr("alt").substring(34));
			if (rating.equals("Left"))
				System.out.println(0);
			else if (rating.equals("Lean Left"))
				System.out.println(1);
			else if (rating.equals("Center"))
				System.out.println(2);
			else if (rating.equals("Lean Right"))
				System.out.println(3);
			else
				System.out.println(4);
			}
		
		}
	}

