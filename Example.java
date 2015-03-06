
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;


// Utilized JSON simple because it is free
// and it would parse through the JSON file
// as that is the input
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Example {
		    
	private static final String filePath = "C:\\Users\\Nabeel\\Documents\\task.json";//change the file name to other parsers
	
	public static void main(String[] args) {

		try {
			// read the json file
			FileReader reader = new FileReader(filePath);
			JSONParser jsonParser = new JSONParser();
			//  it is an array so we read the array
			JSONArray mainArray = (JSONArray) jsonParser.parse(reader);
			
			//test case
			Iterator iterate = mainArray.iterator();
			// go through each big set
			while(iterate.hasNext()){
				JSONObject arrayObject = (JSONObject) iterate.next();
				System.out.println("this is the name " + arrayObject.get("instanceName"));
				System.out.println("hello");
			}
			
			
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