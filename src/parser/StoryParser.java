package parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class StoryParser {

	public static void main(String[] args) {
		parseStories();
	}

	private static void parseStories() {
		String[] neighborhoods = { "Pointe-aux-Trembles", "Saint-Michel",
				"Downtown Montreal", "Notre-Dame-de-Grâce",
				"Place Bonaventure", "Duvernay-Est", "Dollard-des- Ormeaux",
				"Montreal East", "Ahuntsic", "Place Desjardins",
				"Saint-François", "Rivière-des-Prairies", "Griffintown",
				"Saint-Henri", "Saint-Vincent-de-Paul", "L'Île-Bizard",
				"Villeray", "L'Île-Des-Soeurs", "Ville Émard", "Duvernay",
				"Montréal-Nord", "Petite-Patrie", "Verdun", "Pont-Viau",
				"Santa Claus", "Montreal North", "Plateau Mont-Royal",
				"Auteuil", "Sainte-Geneviève", "Anjou", "Petite-Bourgogne",
				"Cartierville", "Kirkland", "Centre-Sud",
				"Pointe-Saint-Charles", "Senneville", "Mercier",
				"Saint-Laurent", "Sainte-Rose", "Akwesasne", "Vimont",
				"Parc-Extension", "Laval-des-Rapides", "LaSalle",
				"Saint-Léonard", "Mount Royal", "Fabreville", "Dorval",
				"Laval-sur-le-Lac", "Ville Saint-Pierre", "Pointe-Claire",
				"Côte-des-Neiges", "Chomedey", "Lachine", "Rosemont",
				"Maisonneuve", "Outremont", "Côte Saint-Luc", "Hochelaga",
				"Beaconsfield", "Hampstead", "Montreal West",
				"Sainte-Dorothée", "Sainte-Anne-De- Bellevue", "Old Montreal",
				"Westmount", "Îles-Laval", "Roxboro", "Tour de la Bourse",
				"Pierrefonds" };
		OutputStreamWriter file = null;		
		JSONParser parser = new JSONParser();
		File f = new File("./res/json/");
		ArrayList<String> names = new ArrayList<String>(Arrays.asList(f.list()));
		JSONObject jsonObj = null;
		JSONObject story = null;
		JSONObject keywords = null;
		JSONObject title = null;
		JSONArray storiesToKeep = new JSONArray();
		Object obj = null;
		String hood = "";
		JSONObject storyToKeep = null;
		JSONObject img = null;
		JSONObject link = null;
		JSONObject lastUpdate = null;
		JSONObject summary = null;
		try {
			file = new OutputStreamWriter(new FileOutputStream(new File("stories.json")),
					Charset.forName("UTF-8").newEncoder());
			for (int i = 0; i < names.size(); i++) {
				obj = parser
						.parse(new FileReader("./res/json/" + names.get(i)));
				jsonObj = (JSONObject) obj;
				story = (JSONObject) jsonObj.get("ept-story");
				keywords = (JSONObject) story.get("keywords");
				title = (JSONObject) ((JSONObject) story.get("promo"))
						.get("title");
				for (int j = 0; j < neighborhoods.length; j++) {
					hood = neighborhoods[j].toLowerCase();
					if (keywords.toString().toLowerCase().contains(hood)
							|| title.toString().toLowerCase().contains(hood)) {
						
						link = (JSONObject) story.get("relative-url");
						img = (JSONObject) ((JSONObject) story.get("promo")).get("image");
						summary = (JSONObject) story.get("summary");
						lastUpdate = (JSONObject)((JSONObject) story
								.get("last-updated-time")).get("xsl-format");
						
						storyToKeep = new JSONObject();
						storyToKeep.put("title", title.get("$t"));
						storyToKeep.put("link", "www.cbc.ca" + link.get("$t"));
						storyToKeep.put("image", img.get("$t"));
						storyToKeep.put("summary", summary.get("$t"));
						storyToKeep.put("neightborhood", hood);
						storyToKeep.put("date", lastUpdate.get("$t"));
						storiesToKeep.add(storyToKeep);
					}
				}
				file.write(storiesToKeep.toJSONString());
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