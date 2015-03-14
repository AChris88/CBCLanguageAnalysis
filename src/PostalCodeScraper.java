import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PostalCodeScraper {

	public static void main(String[] args) {
		saveNeighborhoodCodesAsJSON();
	}

	private static void saveNeighborhoodCodesAsJSON() {
		JSONObject obj = new JSONObject();
		OutputStreamWriter file = null;		
		Element tmp = null;
		Element hood = null;
		try {
			Document doc = Jsoup
					.connect(
							"http://en.wikipedia.org/wiki/List_of_H_postal_codes_of_Canada")
					.get();
			Element info = doc.getElementById("mw-content-text");
			Elements data = info.getElementsByTag("table").get(0)
					.getElementsByTag("td");
			file = new OutputStreamWriter(new FileOutputStream(new File("areaCodes.json")),
					Charset.forName("UTF-8").newEncoder());
			
			for (int i = 0; i < data.size(); i++) {
				// td instance
				tmp = data.get(i);
				
				if (tmp.getElementsContainingText("Not assigned").size() == 0) {
					hood = tmp.getElementsByTag("a").get(0);
					obj.put(tmp.getElementsByTag("b").text(), hood.text());					
				}
			}
				file.write(obj.toJSONString());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				file.flush();
				file.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}