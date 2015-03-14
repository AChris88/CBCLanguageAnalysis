import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class PostalCodeScraper {

	public static void main(String[] args){
		getCodes();
	}
	
	private static void getCodes(){
		ArrayList<String> postalCodes = new ArrayList<String>();
		ArrayList<String> hoods = new ArrayList<String>();
		try {
			Document doc = Jsoup.connect("http://en.wikipedia.org/wiki/List_of_H_postal_codes_of_Canada").get();
			Element info = doc.getElementById("mw-content-text");
			Elements data = info.getElementsByTag("table").get(0).getElementsByTag("td");
			Element tmp = null;
			Element hood = null;
			for(int i = 0; i < data.size(); i++ ){
				tmp = data.get(i);
				if (tmp.getElementsContainingText("Not assigned").size() == 0){
					hood = tmp.getElementsByTag("a").get(0);
					if(!hoods.contains(hood.text()))
						hoods.add(hood.text());
					postalCodes.add(tmp.getElementsByTag("b").text());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Postal codes:");
		for(int i = 0; i < postalCodes.size(); i++){
			System.out.println(postalCodes.get(i));
		}
		
		System.out.println("Neighborhoods::");
		for(int i = 0; i < hoods.size(); i++){
			System.out.println(hoods.get(i));
		}
	}
}
