
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
	
	private static final String INSTANCENAME = "instanceName";
	
	private static final String NAME = "name";
	
	private static final String INSTANCEID = "instanceId";
	
	private static final String STATUS = "status";
	
	private static final String START = "createDate";
	
	private static final String END = "closeDate";
	
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
				// goes to each big element in JSON
				// from that we read the input and spit out the answer
				// case 1
				//since arrayObject.get spits out an object instanceName
				// we give it the date in the way I am assuming
				// 2014-10-06T23:32:33Z
				// read start and end to get the date
				// read what is opened and what is closed on that date
				String example = "2014-10-06";
				//String[] parts = example.split("-");
				String case1Start = (String) arrayObject.get(START);
				// fetch start date
				String case1End = (String) arrayObject.get(END);
				//fetch end date
			//	int closed= 0;
		//		int open = 0;
	//			if(case1Start.contains(example) )
					//the task started on the date
		//			open++;
				// it can start then
				// * rewrite later to include next day case
		//		else if(case1End.contains(example) )
		//			closed++;
				
				System.out.println("start " + case1Start );
				System.out.println("end " + case1End);
				System.out.println("next");
				
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