package users;
import java.io.Serializable;
import java.util.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class Employee implements Serializable {

	private static final long serialVersionUID = 4L;
	private String empID;	
	private String name;
	private Business employer;
	private HashMap<LocalDate, HashMap<LocalTime, Boolean>> schedule;
	
	public Employee(String empID, String name, Business employer){
		this.empID = empID;
		this.name = name;
		this.employer = employer;
	}
	
	public String getEmpID() {
		return empID;
	}
	
	public String getName() {
		return name;
	}
	
	public Business getEmployer(){
		return employer;
	}
	
	public HashMap<LocalDate, HashMap<LocalTime, Boolean>> getSchedule(){
		//Init enough slots for each employee working time
		//TODO
		return schedule;
	}
	
	public boolean checkSchedule(LocalDate date, LocalTime startTime, LocalTime endTime, long steps){
		LocalTime checkTime = startTime;
		
		HashMap<LocalTime, Boolean>day = schedule.get(date);
		do{
			if(!day.get(checkTime)) //may need to get rid of ! depending on how it was implemented, me not know lel
				return false;
			else
				checkTime = checkTime.plusMinutes(steps);
		}while(checkTime.isBefore(endTime));
		
		
		
		return true;
		
	}
}
