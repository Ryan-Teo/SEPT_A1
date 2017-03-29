import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class Owner extends User{
	String name, username, password, address, phone;
	Owner(String name, String username, String password, String address, String phone){
		
		super(name,username,password,address,phone);
	}
	
	@Override
	public void viewSession(String name) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void viewBookingSummary() {
		// TODO Auto-generated method stub
		
	}
	
	public void addEmployee(String name,String working_time){
		
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
	
}
