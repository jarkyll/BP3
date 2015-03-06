
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
			int closed= 0;
			int open = 0;
			//number of open and closed cases 
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
				String example = "2015-02-22";
				//String[] parts = example.split("-");
				String case1Start = (String) arrayObject.get(START);
				// fetch start date
				String case1End = (String) arrayObject.get(END);
				//fetch end date
				//check first if case1Start and case1End are not null
				
				if(case1Start!=null && case1Start.contains(example) )
					//the task started on the date
					open++;
				// it can start then
				// * rewrite later to include next day case
				else if(case1End != null && case1End.contains(example) )
					closed++;

				//System.out.println(case1Start);
				//System.out.println(case1End);
				//System.out.println(example);
			}
			System.out.println("number of open cases: " + open );
			System.out.println("number of closed cases: " + closed);
			System.out.println("total: " + (open + closed) );
			
//CASE 2

			Iterator iterate = mainArray.iterator();
			// go through each big set
			int numTasks = 0;
			// Start example
			String sexample = "2015-02-22";
			// end example
			String eexample = "2023-2323-2323";
			
			//number of tasks opened and closed in that range
			while(iterate.hasNext()){
				JSONObject arrayObject = (JSONObject) iterate.next();
				//String[] parts = example.split("-");
				//compare if it is in that open or close day
				//then check if in that time frame
				// hours then minutes then secs
				// add if it passes the task
				// 
				String case1Start = (String) arrayObject.get(START);
				// fetch start date
				String case1End = (String) arrayObject.get(END);
				//fetch end date
				//check first if case1Start and case1End are not null
				
				if(case1Start!=null && case1Start.contains(example) )
					//the task started on the date
					open++;
				// it can start then
				// * rewrite later to include next day case
				else if(case1End != null && case1End.contains(example) )
					closed++;

				//System.out.println(case1Start);
				//System.out.println(case1End);
				//System.out.println(example);
			}
			System.out.println("number of open cases: " + open );
			System.out.println("number of closed cases: " + closed);
			System.out.println("total: " + (open + closed) );
		
			
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