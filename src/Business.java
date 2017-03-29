import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Business extends User{

	private String busName;
	private ArrayList<Employee> emp;	
	private HashMap<Date, ArrayList<Booking>> schedule;
	
	Business(String busName, String ownerName, String address, String phone, String username, String password, HashMap<Date, ArrayList<Booking>> schedule){
		super(ownerName,username,password,address,phone);
		this.busName = busName;
		this.schedule = schedule;
	}
	
	public String getBusName(){
		return busName;
	}
	
	public ArrayList<Employee> getEmp() {
		return emp;
	}
	
	public HashMap<Date, ArrayList<Booking>>  getSchedule(){
		return this.schedule;
	}
	
	public void printSchedule(){
		ArrayList<Booking> sessions;
		int i = 1;
		for(Date key : this.schedule.keySet()){
			// Day in month - date - day
			System.out.printf("%1$s %2$tB %2$td, %2$tA", "Date:", key);
			System.out.println();
			System.out.println("-----------------------------");
			System.out.println(String.format("	%-5s 	%-5s","session","Employee"));
			sessions = this.schedule.get(key);
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
	
	public ArrayList<Booking> addSessionTime(String startTime, String endTime,ArrayList<Booking> session){
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
		Date start = new Date();
		Date end = new Date();
		
		try {
			start = timeFormat.parse(startTime);
			end = timeFormat.parse(endTime);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		Booking session_time = new Booking(start,end);
		session.add(session_time);
		return session;
	}
	
	public ArrayList<Booking> addSessionTime(String startTime, String endTime,ArrayList<Booking> session,Employee emp){
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
		Date start = new Date();
		Date end = new Date();
		
		try {
			start = timeFormat.parse(startTime);
			end = timeFormat.parse(endTime);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		Booking session_time = new Booking(start,end,emp);
		session.add(session_time);
		return session;
	}
	
	public void addSession(Date day,ArrayList<Booking>  new_session){
		this.schedule.put(day,new_session);
	}
	
	public void businessMenu(){
		//prints out all of the menu items for a business user
	}

	public void viewSession(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void viewBookingSummary() {
		// TODO Auto-generated method stub
		
	}
	

}
