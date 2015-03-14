package cbcjsonparser;

import java.io.File;
import java.io.FileReader;
public class Main {

	private static File folderLocation;
	
	public static void main(String[] args) {
		
		folderLocation = new File("C:\\Users\\konce_000\\Downloads\\stories.tar\\stories\\story\\json");
		cbcJsonParser parser = new cbcJsonParser();
		//System.out.println(folderLocation.listFiles());
		for(File fileEntry : folderLocation.listFiles()) {
			parser.parseFile(fileEntry);
		}
	}
	

}
