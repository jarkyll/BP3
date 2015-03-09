// BP-3 challenge
// It does all of the methods required by asking which of the methods wants to be done
// For simplicity, JSON simple was used and is an added jar file
// it makes it so the json file is parsed and translated
// Date object is used for the dates in the methods that dates were asked or needed.
// by: Nabeel Virani
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Date;
import java.util.Scanner;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

// Utilized JSON simple because it is free

// and it would parse through the JSON file
// as that is the input
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Example {
		    
	private static final String filePath = "C:\\Users\\Nabeel\\Documents\\task.json";//change the file name to other parsers
	
	private static final String NAME = "name";
	
	private static final String INSTANCEID = "instanceId";
	
	private static final String START = "createDate";
	
	private static final String END = "closeDate";
	
	// These were static names and did not change, thus I made final Strings out of them
	
	
	public static void main(String[] args) throws java.text.ParseException {

		try {
			// read the json file
			FileReader reader = new FileReader(filePath);
			JSONParser jsonParser = new JSONParser();
			//  it is an array so we read the array
			JSONArray mainArray = (JSONArray) jsonParser.parse(reader);
			
			//test case
			Iterator iterate = mainArray.iterator();
			Scanner input = new Scanner(System.in);
			System.out.println("Hello, welcome. What task do you want to complete?");
			System.out.println("Choice 1:Given a specific date give me the current number of open and closed tasks");
			System.out.println("Choice 2:Given a specific start and end date, how many tasks where opened and how many were closed in that range");
			System.out.println("Choice 3:Given a particular instanceId, give me the name of the most recent task");
			System.out.println("Choice 4:Given a particular instanceId, give me the count of tasks");
			
			int choice = 0;
			// depending on the choice, it will do a certain task
			// each task has it's own method
			while(choice < 1 || choice > 4){
				System.out.print("Press the number to intitate that certain task: ");
				choice = input.nextInt();
				if(choice < 1 || choice > 4)
					System.out.println("Please choose a valid number");
			}
			if(choice == 1 ){
				String startDate = getDate(input);
				number_currentTasks(startDate, iterate);
			}
			else if(choice == 3 || choice == 4){
				System.out.print("Please enter input: ");
				long instanceid = input.nextLong();
				if(choice == 3)
					recent_task(instanceid,iterate);
				else
					tasks_count(instanceid,iterate);
					
			}
			else{
				String startDate= getDate(input);
				String endDate = getDate(input);
				tasks_in_range(startDate, endDate, iterate);
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
	// find the number of tasks for an instanceid
	private static void tasks_count(long input, Iterator iterate) {
		
		// go through each big set
		// instanceId example
		int num_tasks = 0;
		while(iterate.hasNext()){
			JSONObject arrayObject = (JSONObject) iterate.next();
			// check to see if the task instance is the 
			// instance id given if so, increment
			long instanceId = (Long) arrayObject.get(INSTANCEID);
			//check how many times this appears 
			//
			
			if( instanceId == input )			
				 num_tasks++;
		}
		System.out.println("number of tasks: " + num_tasks );
		
	}
	// finds all the tasks that match the instanceid
	// then it uses a comparator to find the most recent one
	private static void recent_task(long input, Iterator iterate) {
		  final long current = System.currentTimeMillis();
		  List<Date> dates = new ArrayList<Date>();
		  Map<Date, String> myMap = new HashMap<Date,String>();
		   while(iterate.hasNext()){
				JSONObject arrayObject = (JSONObject) iterate.next();
				// check to see if the task instance is the 
				// instance id given if so, increment
				long instanceId = (Long) arrayObject.get(INSTANCEID);
				// if equals then check if the task is the most recent
				if( instanceId == input){
					String starting = (String) arrayObject.get(START);
					if(starting !=null){
//						String starting = (String) arrayObject.get(START);
						String[] date_time = starting.split("T");
						String[]tempdate = date_time[0].split("-");
						String[]temptime = date_time[1].split(":");
						temptime[2] = temptime[2].replace("Z","");
						int year = Integer.parseInt(tempdate[0]);
						int month = Integer.parseInt(tempdate[1]);
						int day = Integer.parseInt(tempdate[2]);
						int hour = Integer.parseInt(temptime[0]);
						int minute = Integer.parseInt(temptime[1]);
						int sec = Integer.parseInt(temptime[2]);
						Date temp = new Date(year, month, day, hour, minute, sec);
						// the above is frowned upon, but it was the simplest way I found to do it for the time being
						if(!myMap.containsKey(temp)){
							String name = (String) arrayObject.get(NAME);
							myMap.put(temp,name);
						}
						dates.add(temp);
					}
				}
		   }
		   // comparator that would convert to milliseconds that then discover the closest to the current
		   // learned through googling.
		   Date close = Collections.min(dates, new Comparator<Date>(){
				public int compare(Date num1, Date num2){
					long difference = Math.abs(num1.getTime() - current);
				    long difference2 = Math.abs(num2.getTime() - current);
				    return difference < difference2 ? -1 : 1;
				}
			});
		   String ans = myMap.get(close);
		   System.out.println(ans);
	
		
	}
	// finds the number of tasks between the start and end where start is inclusive and end is not.
	private static void tasks_in_range(String startDate, String endDate, Iterator iterate) throws java.text.ParseException {
					int closedtask = 0;
					int opentask = 0;
					DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
					Date start = format.parse(startDate);
					Date end = format.parse(endDate);
				
					
					//number of tasks opened and closed in that range
					while(iterate.hasNext()){
						JSONObject arrayObject = (JSONObject) iterate.next();
						//String[] parts = example.split("-");
						//compare if it is in that open or close day
						String[] startArray = new String[0];
						String[] endArray = new String[0];
						String starting = (String) arrayObject.get(START);
						String ending = (String) arrayObject.get(END);
						// gets the start and end from the element
						if(starting != null){
							startArray = starting.split("T");
							Date temp = format.parse(startArray[0]);
								// convert into a Date object
								// means that now it exists past the start date
								// check if it started before the end date
							if(temp.after(start) || temp.equals(start))
								if(temp.before(end))
									opentask ++;
						}
						//same as earlier
						if(ending != null){
							endArray = ending.split("T");
							Date temp = format.parse(endArray[0]);

							if(temp.after(start) || temp.equals(start))
								if(temp.before(end))
									closedtask ++;
						}
					}
					System.out.println("number of open tasks: " + opentask);
					System.out.println("number of closed tasks: "+ closedtask);
		
	}
	// finds the current number of tasks
	// It prints it out, but can be changed very easily to make it return it.
	private static void number_currentTasks(String startDate, Iterator iterate) {
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

					}
					System.out.println("number of open cases: " + open );
					System.out.println("number of closed cases: " + closed);
					System.out.println("total: " + (open + closed) );
					
		
	}

	// retrieves the date input from the choices made
	// it returns a string of the date, and makes sure the date is a legit date
	private static String getDate(Scanner input) {
		boolean found = false;
		boolean flag = false;
		String date = "";
		while(!found){
			System.out.print("Please enter the date like so yyyy-mm-dd, thank you: "
					+ "");
			date = input.next();
			for(int i = 0; i < date.length(); i++){
				char character = date.charAt(i);
				if(i == 4 || i == 7){
					if(character !='-')
						flag = true;
				}
				else{
					if(!(character >= '0' && character <= '9'))
						flag = true;
				}
			}
			if(flag || date.length() != 10){
				System.out.println("you have not correctly typed in the date. Please try again.");
				flag = false;
			}
			else
				found = true;
		}
		return date;
	}
	

}