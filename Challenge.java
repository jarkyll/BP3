import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
// JSON objects will be converted by reading
//and using JSON Simple
public class Challenge {
	
	private static final String FILEPATH = "C:\\Users\\Nabeel\\Documents\\task.json";
	
	public static void main(String args[]) throws Exception, Throwable{
	// main thing, read all the elements and put in an arraylist
    // inside the arrayList there will be an array of size 15.
	// depending on the index, a certain object will be retrieved
	try{
		//read the file
		FileReader reader = new FileReader(FILEPATH);
		
		JSONParser parser = new JSONParser();
		// since it is an array in the JSON file
		JSONArray mainArray = (JSONArray) parser.parse(reader);
		
		//System.out.println("hello");
		// read the instance name
		String instanceName = (String) .get("instanceName");
		System.out.println("instance name is: " + instanceName);
		
		
	} catch (FileNotFoundException ex) {
		ex.printStackTrace();
	} catch (IOException ex) {
		ex.printStackTrace();
	} catch (ParseException ex) {
		ex.printStackTrace();
	} catch (NullPointerException ex) {
		ex.printStackTrace();
	}
		
		
		
		
	}
}
