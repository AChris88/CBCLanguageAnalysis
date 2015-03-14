package cbcjsonparser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class StoryParser {

	public static void main(String[] args) {
		 parseStories();
	}

	private static void parseStories() {
		String[] neighborhoods = {"Pointe-aux-Trembles", "Saint-Michel",
				"Downtown Montreal", "Notre-Dame-de-Gr�ce", "Place Bonaventure",
				"Duvernay-Est", "Dollard-des- Ormeaux", "Montreal East",
				"Ahuntsic", "Place Desjardins", "Saint-Fran�ois",
				"Rivi�re-des-Prairies", "Griffintown", "Saint-Henri",
				"Saint-Vincent-de-Paul", "L'�le-Bizard", "Villeray",
				"L'�le-Des-Soeurs", "Ville �mard", "Duvernay", "Montr�al-Nord",
				"Petite-Patrie", "Verdun", "Pont-Viau", "Santa Claus",
				"Montreal North", "Plateau Mont-Royal", "Auteuil",
				"Sainte-Genevi�ve", "Anjou", "Petite-Bourgogne", "Cartierville",
				"Kirkland", "Centre-Sud", "Pointe-Saint-Charles", "Senneville",
				"Mercier", "Saint-Laurent", "Sainte-Rose", "Akwesasne", "Vimont",
				"Parc-Extension", "Laval-des-Rapides", "LaSalle", "Saint-L�onard",
				"Mount Royal", "Fabreville", "Dorval", "Laval-sur-le-Lac",
				"Ville Saint-Pierre", "Pointe-Claire", "C�te-des-Neiges",
				"Chomedey", "Lachine", "Rosemont", "Maisonneuve", "Outremont",
				"C�te Saint-Luc", "Hochelaga", "Beaconsfield", "Hampstead",
				"Montreal West", "Sainte-Doroth�e", "Sainte-Anne-De- Bellevue",
				"Old Montreal", "Westmount", "�les-Laval", "Roxboro",
				"Tour de la Bourse", "Pierrefonds"};
		
		JSONParser parser = new JSONParser();
		File f = new File("./res/json/");
		ArrayList<String> names = new ArrayList<String>(Arrays.asList(f.list()));
		JSONObject jsonObj = null;
		JSONObject story = null;
		JSONObject keywords = null;
		JSONObject title = null;
		ArrayList<JSONObject> storiesToKeep = new ArrayList<JSONObject>();
		Object obj = null;
		String hood = "";
		try {
			for (int i = 0; i < names.size(); i++) {
				obj = parser.parse(new FileReader("./res/json/"
						+ names.get(i)));
				jsonObj = (JSONObject) obj;
				story = (JSONObject) jsonObj.get("ept-story");
				keywords = (JSONObject) story.get("keywords");
				title = (JSONObject) ((JSONObject) story.get("promo"))
						.get("title");
				for(int j = 0; j < neighborhoods.length; j++){
					hood = neighborhoods[j].toLowerCase();
				if(keywords.toString().toLowerCase().contains(hood) ||
						title.toString().toLowerCase().contains(hood))
					storiesToKeep.add((JSONObject) obj);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println("Stories to keep: " + storiesToKeep.size());
	}
}