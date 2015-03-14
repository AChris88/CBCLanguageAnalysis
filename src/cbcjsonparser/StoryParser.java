package cbcjsonparser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class StoryParser {

	public static void main(String[] args) {
		parseStories();
	}

	private static void parseStories() {
		JSONParser parser = new JSONParser();
		File f = new File("./res/json/");
		ArrayList<String> names = new ArrayList<String>(Arrays.asList(f.list()));
		try {
			for (int i = 0; i < names.size(); i++) {
				Object obj = parser.parse(new FileReader("./res/json/"+ names.get(i)));

				JSONObject jsonObject = (JSONObject) obj;

				String story = (String) jsonObject.get("ept-story").toString();
				System.out.println(story);
//
//				String title = (String) jsonObject.get("link-title");
//				System.out.println(title);
//
//				String body = (String) jsonObject.get("body");
//				System.out.println(body);
//				
//				String summary = (String) jsonObject.get("summary");
//				System.out.println(summary);
				
				// loop array
//				JSONArray msg = (JSONArray) jsonObject.get("ept-story");
//				Iterator<String> iterator = msg.iterator();
//				while (iterator.hasNext()) {
//					System.out.println(iterator.next());
//				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}