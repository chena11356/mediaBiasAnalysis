import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BiasAnalysis {
	
	public static int getWorth(String url) throws IOException {
		//first strip url to get only example.com format
		url = url.substring(url.indexOf("//")+2);
		Document d=Jsoup.connect("https://www.worthofweb.com/website-value/"+url+"/").timeout(10000).get(); //connect to website
		Elements h = d.getElementsByClass("stat_row_number");
		String res="0";
		for (Element j : h) {
			res=j.text().substring(2).replaceAll(",","");
			break;
		}
		return Integer.parseInt(res);
		
	}
	
	public static int countWords(String s) {
		String trim = s.trim();
		if (trim.isEmpty())
		    return 0;
		return trim.split("\\s+").length; // separate string around spaces
	}
	
	public static int[] loadedLanguage(String article) {
		article=article.toUpperCase();
		String[][] loadedWords = {
				LoadedLanguage.getCuriosity(),
				LoadedLanguage.getUrgency(),
				LoadedLanguage.getAnger(),
				LoadedLanguage.getConfusion(),
				LoadedLanguage.getHappy(),
				LoadedLanguage.getInspiring(),
				LoadedLanguage.getPeaceful(),
				LoadedLanguage.getSafe()
		};
		
		int[] loadedWordsCount = {
				0,
				0,
				0,
				0,
				0,
				0,
				0,
				0,
				0 //last number is just overall number of emotional words
		};
		
		int i = 0;
		for (String[] wordsList : loadedWords) {
			
			for (String word : wordsList) {
				if (article.indexOf(word.toUpperCase())!=-1) {
					loadedWordsCount[i]=loadedWordsCount[i]+1;
					loadedWordsCount[8]=loadedWordsCount[8]+1;
				}
			}
			
			i++;
		}
		
		return loadedWordsCount;
		
	}
	
	public static int[] rhetoricProportion(String article) {
		int[] result = new int[3]; //returns results from 0-100 proportion, as ethos-logos-pathos
		return result;
	}
}
