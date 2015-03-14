package cbcjsonparser;

import java.io.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;



public class cbcJsonParser {
	
	private File destinationFile;
	private JSONParser parser;
	public void cbcJsonParser() {
		parser = new JSONParser();
		destinationFile = new File("C:\\Desktop\\data.json");
		
	}
	
	public void parseFile(File singleFile) {
		try {
			Object obj = parser.parse(new FileReader(singleFile.getAbsolutePath()));
			JSONObject jsonObj = (JSONObject) obj;
			System.out.println("gets here" + jsonObj.toString());
		}catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	public void writeJsonToDestination() {
		
	}
} 
