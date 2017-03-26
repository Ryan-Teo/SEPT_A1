import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Business {

	private String name;
	private String owner;
	private ArrayList<Employee> emp;	
	/*Hashtable info
	 * key : type Time 
	 * time format : dd.MMMM.yyyy.HH.mm
	 * H - 24 Hour in a day
	 * mm - Minutes
	 * 
	 * Value : ArrayList <Booking> sessions;
	*/
	private HashMap<Date, ArrayList<Booking>> schedule;
	
	
	Business(String name, String owner){
		this.name = name;
		this.owner = owner;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public ArrayList<Employee> getEmp() {
		return emp;
	}
	
	public HashMap<Date, ArrayList<Booking>>  getSchedule(){
		return this.schedule;
	}
	
	public void printSchedule(){
		ArrayList<Booking> sessions;
		for(Object key : schedule.keySet()){
			// Day in week - date - year
			System.out.printf("%s %tB %<te  %<tY",key);
			System.out.println();
			sessions = (ArrayList<Booking>) schedule.get(key);
			for(Booking session : sessions){
				// %R => 24 hour time, no seconds
				System.out.printf("%R-%R",session.getStartTime(),session.getEndTime());
			}

		}
	}
	
	public void addSession(Date day,Booking new_session){
		//if no session have been created for one of the days
		if(this.schedule.containsKey(day) == false){
			
			//add the day into the schedule as the key and add 'null' as values
			this.schedule.put(day,null);
			
			//assign the value to variable 'session'
			ArrayList<Booking> session = schedule.get(day);
			
			//add new session to the arrayList 
			session.add(new_session);
			
			//update the schedule
			this.schedule.put(day,session);
		}
		else{
			//get the group of sessions on that day 
			ArrayList<Booking> session = schedule.get(day);
			
			//append new session on the arrayList
			session.add(new_session);
			
			//Update the schedule
			this.schedule.put(day,session);
			
		}
		
		
	}
	
	//Add session time with specified format("hour:minute")
	public Booking addSessionTime(String startTime, String endTime){
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
		
		return session_time;
	}
}
