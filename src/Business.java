import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Business extends User{

	private String busName;
	private ArrayList<Employee> emp = new ArrayList<Employee>();
	private ArrayList<Booking> schedule = new ArrayList<Booking>();
	
	Business(String busName, String ownerName, String address, String phone, String username, String password){
		super(ownerName,username,password,address,phone);
		this.busName = busName;
	}
	
	public String getBusName(){
		return busName;
	}
		
	public ArrayList<Employee> getEmp(){
		return emp;
	}
	
	public ArrayList<Booking> getSchedule(){
		return schedule;
	}
	
//	public ArrayList<Booking>  getSchedule(){
//		return this.schedule;
//	}
	
//	public void printSchedule(){
//		ArrayList<Booking> sessions;
//		int i = 1;
//		for(Date key : this.schedule.keySet()){
//			// Day in month - date - day
//			System.out.printf("%1$s %2$tB %2$td, %2$tA", "Date:", key);
//			System.out.println();
//			System.out.println("-----------------------------");
//			System.out.println(String.format("	%-5s 	%-5s","session","Employee"));
//			sessions = this.schedule.get(key);
//			for(Booking session : sessions){
//				// %R => 24 hour time, no seconds
//				System.out.printf("%1$s. |%2$tR - %2$tR|	",i,session.getStartTime(),session.getEndTime());
//				System.out.printf("%-5s", session.getBookEmp().getName());
//				System.out.println();
//				i++;
//			}
//			i = 1;
//			System.out.println();
//		}
//	}
	
//	public ArrayList<Booking> addSessionTime(String startTime, String endTime,ArrayList<Booking> session){
//		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
//		Date start = new Date();
//		Date end = new Date();
//		
//		try {
//			start = timeFormat.parse(startTime);
//			end = timeFormat.parse(endTime);
//		} catch (ParseException e1) {
//			e1.printStackTrace();
//		}
//		
//		Booking session_time = new Booking(start,end);
//		session.add(session_time);
//		return session;
//	}
//	
//	public ArrayList<Booking> addSessionTime(String startTime, String endTime,ArrayList<Booking> session,Employee emp){
//		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
//		Date start = new Date();
//		Date end = new Date();
//		
//		try {
//			start = timeFormat.parse(startTime);
//			end = timeFormat.parse(endTime);
//		} catch (ParseException e1) {
//			e1.printStackTrace();
//		}
//		
//		Booking session_time = new Booking(start,end,emp);
//		session.add(session_time);
//		return session;
//	}
//	
//	public void addSession(Date day, ArrayList<Booking> new_session){
//		this.schedule.put(day,new_session);
//	}
	
	


	//	@SuppressWarnings("unchecked")
//	public void assignEmployee(Employee empName,String day,String session_startTime, Business business){
//		
//		ArrayList<Booking> sessions;
//		HashMap <Date,ArrayList<Booking>> schedule = business.getSchedule();
//		
//		//Day-Date-Month-Year
//		SimpleDateFormat dayFormat = new SimpleDateFormat ("E dd.mm");
//		//Hour-minute
//		SimpleDateFormat sessionFormat = new SimpleDateFormat ("HH.mm");
//		
//		try {
//			Date working_day = dayFormat.parse(day);
//			Date working_hour = sessionFormat.parse(session_startTime);
//			
//			//Iterate and check if the key exists
//			for(Date key : schedule.keySet()){
//				if(working_day.equals(key)){
//					//return all sessions available on that day/date 
//					sessions = (ArrayList<Booking>) schedule.get(key);
//					
//					/*Iterate all available sessions and add employee 
//					 *to the specified session time
//					 */
//					for(Booking session : sessions){
//						if(working_hour.equals(session.getStartTime())){
//							//Assign an employee to that session
//							session.getEmp().add(empName);
//						}
//					}
//				}
//			}
//		} catch (ParseException e) {
//			System.out.println("Unparseable"); 
//		}
//	}
	public void businessMenu(){
		System.out.println("Welcome " + this.getName() + "!");
		System.out.println("1 : Add Booking"); //refer to bookSession()
		System.out.println("2 : View Bookings");//refer to viewBookingSummary()
		System.out.println("3 : View Sessions of a Business"); //refer to viewSession Method
		System.out.println("4 : Logout");
		System.out.println("0 : Exit");
		System.out.printf("Please select an option: ");
	}

	public void viewSession(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void viewBookingSummary() {
		// TODO Auto-generated method stub
		
	}
	

}
