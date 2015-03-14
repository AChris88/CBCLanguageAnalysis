package scraper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PostalCodeScraper {

	public static void main(String[] args) {
		saveNeighborhoodCodesAsJSON();
	}

	public static ArrayList<String> getNeighborhoods(){
		Element tmp = null;
		ArrayList<String> hoods = new ArrayList<String>();
		
		try {
			Document doc = Jsoup
					.connect(
							"http://en.wikipedia.org/wiki/List_of_H_postal_codes_of_Canada")
					.get();
			Element info = doc.getElementById("mw-content-text");
			Elements data = info.getElementsByTag("table").get(0)
					.getElementsByTag("td");
			
			for (int i = 0; i < data.size(); i++) {
				tmp = data.get(i);
				if (tmp.getElementsContainingText("Not assigned").size() == 0) {
					if (!hoods.contains(tmp.getElementsByTag("a").get(0).text()))
					hoods.add(tmp.getElementsByTag("a").get(0).text());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return hoods;
	}
	
	private static void saveNeighborhoodCodesAsJSON() {
		JSONObject obj = new JSONObject();
		OutputStreamWriter file = null;		
		Element tmp = null;
		Element hood = null;
		JSONArray codes = new JSONArray();
		try {
			Document doc = Jsoup
					.connect(
							"http://en.wikipedia.org/wiki/List_of_H_postal_codes_of_Canada")
					.get();
			Element info = doc.getElementById("mw-content-text");
			Elements data = info.getElementsByTag("table").get(0)
					.getElementsByTag("td");
			
			// OutputStreamWriter to enable forcing UTF-8 encoding to support accents
			file = new OutputStreamWriter(new FileOutputStream(new File("areaCodes.json")),
					Charset.forName("UTF-8").newEncoder());
			
			for (int i = 0; i < data.size(); i++) {
				tmp = data.get(i);
				if (tmp.getElementsContainingText("Not assigned").size() == 0) {
					obj = new JSONObject();
					obj.put("code", tmp.getElementsByTag("b").text());
					obj.put("neighborhood", tmp.getElementsByTag("a").get(0).text());
					codes.add(obj);
				}
			}
			file.write(JSONValue.toJSONString(codes));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				file.flush();
				file.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}