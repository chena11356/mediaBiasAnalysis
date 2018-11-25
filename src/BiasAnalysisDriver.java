import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;

public class BiasAnalysisDriver {

	/*plan is:
	 * 1. Have array of websites. 
	 * 2. Get bias rating from AllSides
	 * 3. Get front-page articles from website and analyze.
	 * 4. Get worth $$$
	 * 5. Correlate in SPSS
	 * 
	 * NOTE THAT YOU CANNOT DO ALL WEBSITES AT ONCE BECAUSE JAVA'S CONSOLE IS LIMITED IN SIZE
	 */
	
	
	public static void main(String[] args) throws IOException {
		String[] websites = { //input websites to analyze
				
				/*"https://www.huffingtonpost.com", //left
				"https://www.washingtonpost.com", //leaning left
				"https://www.wsj.com", //center
				"https://www.washingtontimes.com", //leaning right
				"http://www.foxnews.com" //right
				*/
				
				//"https://www.huffingtonpost.com",
				//"https://www.washingtonpost.com",
				//"https://www.washingtontimes.com",
				//"https://www.wsj.com",
				//"http://www.foxnews.com",
				//"https://www.vox.com",
				//"https://townhall.com",
				//"http://www.bbc.com", bbc refuses to work for some reason
				//"http://thehill.com",
				//"https://www.reuters.com",
				//"https://www.theguardian.com/us",
				//"https://www.nytimes.com",
				//"http://www.washingtonexaminer.com",
				//"http://www.motherjones.com",
				//"https://www.pbs.org/newshour",
				//"https://www.usatoday.com",
				//"https://www.dailykos.com",
				//"http://www.nationalreview.com",
				//"http://www.cbn.com",
				//"http://dailycaller.com",
				"https://spectator.org"
				};

		
		double[] websitesLoaded = new double[websites.length]; //proportion of loaded language of front page for webs
				
		
		int i=0;
		ArrayList<String> al=new ArrayList<String>(); 
		Elements headings;
		String[] articleTexts;
		Document article;
		Elements p;
		
		for (String website : websites) {
			//System.out.println(website);
			al.clear();
			Document d=Jsoup.connect(website).timeout(10000).get(); //connect to website
			//System.out.println(website);
			
			
			if (website.equals("https://www.huffingtonpost.com"))
				headings = d.getElementsByTag("h2");
			else if (website.equals("https://www.washingtonpost.com"))
				headings = d.select("div.headline");
			else if (website.equals("https://www.washingtontimes.com")) {
				headings = d.getElementsByClass("article-headline");
			}
			
			else if (website.equals("https://www.wsj.com")) {
				headings = d.getElementsByClass("wsj-headline-link");
			}
			else if (website.equals("http://www.foxnews.com")) {
				headings = d.select("h2.title");
			}
			else if (website.equals("https://www.vox.com")) {
				headings = d.getElementsByAttributeValue("data-chorus-optimize-field","hed");
			}
			else if (website.equals("https://townhall.com")) {
				headings = d.getElementsByClass("thm-inc");
			}
			/*else if (website.equals("http://www.bbc.com")) {
				headings = d.select("div.gs-c-promo-body");
			}*/
			else if (website.equals("http://thehill.com")) {
				headings = d.select("div.story_image");
			}
			else if (website.equals("https://www.reuters.com")) {
				headings = d.select("div.story-content");
			}
			else if (website.equals("https://www.theguardian.com/us")) {
				headings = d.select("h2.fc-item__title");
			}
			else if (website.equals("https://www.nytimes.com")) {
				headings = d.select("h2.story-heading");
			}
			else if (website.equals("http://www.washingtonexaminer.com")) {
				headings = d.getElementsByClass("list-group-item list-group-item-action pr-0");
			}
			else if (website.equals("http://www.motherjones.com")) {
				headings = d.select("h3.hed");
			}
			else if (website.equals("https://www.pbs.org/newshour")) {
				headings = d.select("a.card-sm__title");
			}
			else if (website.equals("https://www.usatoday.com")) {
				headings = d.getElementsByAttributeValueStarting("href","/story/");
			}
			else if (website.equals("https://www.dailykos.com")) {
				headings = d.getElementsByAttributeValueStarting("href","/stories/");
			}
			else if (website.equals("http://www.nationalreview.com")) {
				headings = d.getElementsByAttributeValueStarting("href","article");
			}
			else if (website.equals("http://www.cbn.com")) {
				headings = d.getElementsByAttributeValueStarting("href","/cbnnews");
			}
			else if (website.equals("http://dailycaller.com")) {
				headings = d.getElementsByAttributeValueStarting("href","http://dailycaller.com/2017");
			}
			else if (website.equals("https://spectator.org")) {
				headings = d.select("div.story");
			}
			else if (website.equals("https://www.dailykos.com")) {
				headings = d.getElementsByAttributeValueStarting("href","/stories/");
			}
			else if (website.equals("https://www.dailykos.com")) {
				headings = d.getElementsByAttributeValueStarting("href","/stories/");
			}
			else if (website.equals("https://www.dailykos.com")) {
				headings = d.getElementsByAttributeValueStarting("href","/stories/");
			}
			else if (website.equals("https://www.dailykos.com")) {
				headings = d.getElementsByAttributeValueStarting("href","/stories/");
			}
			else if (website.equals("https://www.dailykos.com")) {
				headings = d.getElementsByAttributeValueStarting("href","/stories/");
			}
			else if (website.equals("https://www.dailykos.com")) {
				headings = d.getElementsByAttributeValueStarting("href","/stories/");
			}
			else if (website.equals("https://www.dailykos.com")) {
				headings = d.getElementsByAttributeValueStarting("href","/stories/");
			}
			else {
				headings = d.select("");
			}
			
			//System.out.println(headings.toArray()[0].toString());
			
			int y = 0;
			for (Element h : headings.select("a")) { //a loop to create a list of article URLs
				if (website.equals("https://www.huffingtonpost.com"))
					al.add(website + h.attr("href"));
				else if (website.equals("https://www.washingtonpost.com")) {
					if (h.attr("href").indexOf("http")!=-1)
						al.add(h.attr("href"));
				}
				else if (website.equals("https://www.washingtontimes.com")) {
					if (h.attr("href").indexOf("/")==0)
						al.add(website + h.attr("href"));
				}
				else if (website.equals("https://www.wsj.com")) {
					if (h.attr("href").indexOf("http")!=-1)
						al.add(h.attr("href"));
				}
				else if (website.equals("http://www.foxnews.com")) {
					if (h.attr("href").indexOf("http")!=-1)
						al.add(h.attr("href"));
				}
				else if (website.equals("https://www.vox.com")) {
					if (h.attr("href").indexOf("http")!=-1)
						al.add(h.attr("href"));
				}
				else if (website.equals("https://townhall.com")) {
					if (h.attr("href").indexOf("http")!=-1)
						al.add(h.attr("href"));
				}
				else if (website.equals("http://www.bbc.com")) {
					if (h.attr("href").indexOf("http")!=-1)
						al.add(website + h.attr("href"));
				}
				else if (website.equals("http://thehill.com")) {
						al.add(website + h.attr("href"));
				}
				else if (website.equals("https://www.reuters.com")) {
						al.add(website + h.attr("href"));
				}
				else if (website.equals("https://www.theguardian.com/us")) {
						al.add(h.attr("href"));
				}
				else if (website.equals("https://www.nytimes.com")) {
					if (y<50) {
							al.add(h.attr("href"));
					}
				}
				else if (website.equals("http://www.washingtonexaminer.com")) {
						al.add(website + h.attr("href"));
				}
				else if (website.equals("http://www.motherjones.com")) {
						al.add(h.attr("href"));
				}
				else if (website.equals("https://www.pbs.org/newshour")) {
						al.add(h.attr("href"));
				}
				else if (website.equals("https://www.usatoday.com")) {
						al.add(website+h.attr("href"));
				}
				else if (website.equals("https://www.dailykos.com")) {
						al.add(website+h.attr("href"));
				}
				else if (website.equals("http://www.nationalreview.com")) {
						al.add(website+"/"+h.attr("href"));
				}
				else if (website.equals("http://www.cbn.com")) {
						al.add(website+h.attr("href"));
				}
				else if (website.equals("http://dailycaller.com")) {
						al.add(h.attr("href"));
				}
				else if (website.equals("https://spectator.org")) {
					System.out.println("ADGaFH");
						al.add(h.attr("href"));
				}
				else if (website.equals("https://www.dailykos.com")) {
					System.out.println("ADGaFH");
						al.add(website+h.attr("href"));
				}
				else if (website.equals("https://www.dailykos.com")) {
					System.out.println("ADGaFH");
						al.add(website+h.attr("href"));
				}
				else if (website.equals("https://www.dailykos.com")) {
					System.out.println("ADGaFH");
						al.add(website+h.attr("href"));
				}
				else if (website.equals("https://www.dailykos.com")) {
					System.out.println("ADGaFH");
						al.add(website+h.attr("href"));
				}
				else if (website.equals("https://www.dailykos.com")) {
					System.out.println("ADGaFH");
						al.add(website+h.attr("href"));
				}
				else if (website.equals("https://www.dailykos.com")) {
					System.out.println("ADGaFH");
						al.add(website+h.attr("href"));
				}
				else if (website.equals("https://www.dailykos.com")) {
					System.out.println("ADGaFH");
						al.add(website+h.attr("href"));
				}
				
				y++;
				//System.out.println(h.attr("href"));
			}
			//System.out.println(al.get(0));

			articleTexts = new String[al.size()];			
			for (int count = 0; count<al.size(); count++) { //a loop to create a list of articles
					article = Jsoup.connect(al.get(count)).timeout(10000).get(); //connect to website
					//Document article = Jsoup.connect("https://www.washingtontimes.com").timeout(10000).get(); //connect to website
					String articleText = "";
				
					p = article.getElementsByTag("p");
				
					for (Element element : p) {
						articleText+=element.text()+" ";
						//System.out.println(element.text());
					}
				
					articleTexts[count] = articleText;
					//System.out.println(articleTexts[count]);
				
			}
			
			//analyze loaded words
			int proportionSum=0;
			int z = 0;
			for (String articleText : articleTexts) { //do it for each article
				int[] loadedWordsList = BiasAnalysis.loadedLanguage(articleText);
				
				int loadedWordsCount=loadedWordsList[8];
				int wordCount=BiasAnalysis.countWords(articleText);
				double proportionLoaded=((double)loadedWordsCount/wordCount)*100;
				if (wordCount>50) {
					proportionSum+=proportionLoaded;
					z++;
				}
				
				/*System.out.println(articleText);
				System.out.println("There are "+loadedWordsCount+" emotional words.");
				System.out.println("There are "+wordCount+" words total.");*/

				//System.out.println("Loaded proportion: "+proportionLoaded+"%");
				System.out.println(proportionLoaded);
				
				websitesLoaded[i]=proportionLoaded;
				
			}
			double proportionTotal=((double)proportionSum/z);
			System.out.println("The average loaded proportion is "+proportionTotal);
			System.out.println("Worth is $"+BiasAnalysis.getWorth(website));
			
			//add variance variable
			
			
			
			
			
			
			i++;
			
			
			
			
			
			
			
			
			
			
		}
		
		

	}

}
