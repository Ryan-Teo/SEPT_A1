import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Business extends User{

	private String busName;
	private ArrayList<Employee> emp = new ArrayList<Employee>();
	private ArrayList<Booking> schedule = new ArrayList<Booking>();
	private Helper help = new Helper();
	
	Business(String busName, String ownerName, String address, String phone, String username, String password){
		super(ownerName,username,password,address,phone);
		this.busName = busName;
	}
	
	//return business name
	public String getBusName(){
		return this.busName;
	}
	
	//return owner name
	public String getOwnerName(){
		return this.name;
	}
	
	//return list of employees
	public ArrayList<Employee> getEmp() {
		return emp;
	}
	
	public HashMap<Date, ArrayList<Booking>> getSchedule(){
		return help.asHashmap(schedule);
	}
	
	public void businessMenu(){
		System.out.println("Welcome " + this.getName() + "!");
		System.out.println("1 : Add Employee");
		System.out.println("2 : Add Working Time/Dates");
		System.out.println("3 : View Booking Summary");
		System.out.println("4 : Add Booking");
		System.out.println("5 : Show Worker Availability");
		System.out.println("0 : Exit");
		System.out.printf("Please select an option: ");
	}
	
	//view business weekly schedule 
	public void printSchedule(ArrayList<Booking> bookings){
		ArrayList<Booking> sessions;
		int i = 1;
		for(Date key : this.getSchedule().keySet()){
			// Day in month - date - day
			System.out.printf("%1$s %2$tB %2$td, %2$tA", "Date:", key);
			System.out.println();
			System.out.println("-----------------------------");
			System.out.println(String.format("	%-5s 	%-5s","session","Employee"));
			sessions = this.getSchedule().get(key);
			for(Booking session : sessions){
				// %R => 24 hour time, no seconds
				System.out.printf("%1$s. |%2$tR - %2$tR|	",i,session.getStartTime(),session.getEndTime());
				System.out.printf("%-5s", session.getBookEmp().getName());
				System.out.println();
				i++;
			}
			i = 1;
			System.out.println();
		}
	}
	
		//Adding a time slot on a specified day
	public ArrayList<Booking> addSessionTime(String startTime, String endTime,ArrayList<Booking> session){
		
		//Setting time format as Hour:minute
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
		Date start = new Date();
		Date end = new Date();
		
		//Parsing from String to type Date
		try {
			start = timeFormat.parse(startTime);
			end = timeFormat.parse(endTime);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		//As parsing is successful, create a new instance of booking 
		Booking session_time = new Booking(start,end);
		
		//Append a session to the list of sessions
		session.add(session_time);
		
		//return the updated list of sessions
		return session;
	}
	
	//Adding an available time slot and assigning an employee to that session directly
	public ArrayList<Booking> addSessionTime(String startTime, String endTime,ArrayList<Booking> session,Employee emp){

		//Setting time format as Hour:minute
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
		Date start = new Date();
		Date end = new Date();
		
		//Parsing from String to type Date
		try {
			start = timeFormat.parse(startTime);
			end = timeFormat.parse(endTime);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		//As parsing is successful, create a new instance of booking 
		Booking session_time = new Booking(start,end);
		
		//Assign an employee to that session
		session_time.setEmployee(emp);
		
		//append the session to the list
		session.add(session_time);
		
		//return the updated list of sessions
		return session;
	}
		
		
	//Adding a new session
	public void addSession(String day,ArrayList<Booking> new_session){
		SimpleDateFormat dayFormat = new SimpleDateFormat("EE");
		Date date;
		try {
			date = dayFormat.parse(day);
			this.getSchedule().put(date,new_session);
		} catch (ParseException e) {
			e.printStackTrace();
		}	
	}
	
	@SuppressWarnings("unchecked")
	public void assignEmployee(Employee empName,String day,String session_startTime, Business business){
		
		ArrayList<Booking> sessions;
		HashMap <Date,ArrayList<Booking>> schedule = business.getSchedule();
		
		//Day-Date-Month-Year
		SimpleDateFormat dayFormat = new SimpleDateFormat ("E dd.mm");
		//Hour-minute
		SimpleDateFormat sessionFormat = new SimpleDateFormat ("HH.mm");
		
		try {
			Date working_day = dayFormat.parse(day);
			Date working_hour = sessionFormat.parse(session_startTime);
			
			//Iterate and check if the key exists
			for(Date key : schedule.keySet()){
				if(working_day.equals(key)){
					//return all sessions available on that day/date 
					sessions = (ArrayList<Booking>) schedule.get(key);
					
					/*Iterate all available sessions and add employee 
					 *to the specified session time
					 */
					for(Booking session : sessions){
						if(working_hour.equals(session.getStartTime())){
							//Assign an employee to that session
							session.getEmp().add(empName);
						}
					}
				}
			}
		} catch (ParseException e) {
			System.out.println("Unparseable"); 
		}
	}

	//Change starting time of a time slot
	public void changeSessionStartTime(String day, String startTime){
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
		SimpleDateFormat dayFormat = new SimpleDateFormat("EE");
		Date date;
		Date time;
		try{
			time = timeFormat.parse(startTime);
			date = dayFormat.parse(day);
			for(Date key : this.getSchedule().keySet()){
				if(key.equals(date)){
					for(Booking session : this.getSchedule().get(key)){
						if(session.getStartTime().equals(time)){
							session.setStartTime(startTime);
						}
					}
				}
			}
		}
		catch(ParseException e){
			e.printStackTrace();
		}
	}
	
	//Change ending time of a time slot
	public void changeSessionEndTime(String day, String endTime){
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
		SimpleDateFormat dayFormat = new SimpleDateFormat("EE");
		Date date;
		Date time;

		try{
			time = timeFormat.parse(endTime);
			date = dayFormat.parse(day);
			for(Date key : this.getSchedule().keySet()){
				if(key.equals(date)){
					for(Booking session : this.getSchedule().get(key)){
						if(session.getStartTime().equals(time)){
							session.setStartTime(endTime);
						}
					}
				}
			}
		}
		catch(ParseException e){
			e.printStackTrace();
		}
	}

	@Override
	public void viewBookingSummary(ArrayList<Booking> bookings) {
		// TODO Auto-generated method stub
		// PLACEHOLDER FOR TESTING
		// REMOVE
	}
	
	//View all bookings
	@Override
	public void viewBookingSummary() {
		ArrayList<Booking> sessions = new ArrayList<Booking>();
		ArrayList<Booking> booked = new ArrayList<Booking>();
		
		for(Date date : this.getSchedule().keySet()){
			sessions = this.getSchedule().get(date);
			for(Booking session : sessions){
				System.out.printf("%1$s %2$tB %2$td, %2$tA", "Date:", date);
				System.out.println("----------------------------------");
				if(session.getBookCust() != null){
					booked.add(session);
				}
			}
			for(Booking book : booked){
				System.out.println("");
				System.out.printf("%1$s. |%2$tR - %2$tR|	","Session time : ",book.getStartTime(),book.getEndTime());
				System.out.println(" This session is booked for : " + book.getBookCust().getName());
				System.out.println("Employee assigned to this session is : " + book.getBookEmp().getName());
			}
			booked.clear();
		}
	}
	
	//view sessions by day (not done)
	public void viewScheduleByDay(String date){
		SimpleDateFormat sdf = new SimpleDateFormat("EE");
		try {
			Date d = sdf.parse(date);
			for(Date day : this.getSchedule().keySet()){
				if(day.equals(d)){
					this.getSchedule().get(day);
				}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
