package users;
import java.io.Serializable;
import java.util.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public class Employee implements Serializable {

	private static final long serialVersionUID = 4L;
	private String empID;	
	private String name;
	private Business employer;
	private HashMap<LocalDate, HashMap<LocalTime, Boolean>> schedule = new HashMap<LocalDate, HashMap<LocalTime, Boolean>>() ; //one month in advance
	
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
	
	public boolean empFree(LocalDate date, LocalTime startTime, String service){
		boolean freeCheck = true;
        int slotsNeeded = employer.getServices().get(service);
        int slotsInMins = employer.getTimeSlotInMins();
        LocalTime endTime = startTime.plusMinutes(slotsNeeded * slotsInMins);
        HashMap<LocalTime, Boolean> daySchedule = schedule.get(date);
        System.out.println(daySchedule.keySet());
        for(LocalTime thisTime : daySchedule.keySet()){
	    	int i = 0;
	    	do{
	    		if(startTime.plusMinutes(i*slotsInMins).equals(thisTime)){
	        		if(daySchedule.get(thisTime).equals(true)){
	        			return false;
	        		}
	        		else{
	        			System.out.println(name + " is free at " + thisTime);
	        		}
	        	}
	    		i++;
	    	}while(startTime.plusMinutes(i*slotsInMins).isBefore(endTime));
        }
		return freeCheck;
	}
	
	public void bookEmp(LocalDate date, LocalTime startTime, String service){
        int slotsNeeded = employer.getServices().get(service);
        int slotsInMins = employer.getTimeSlotInMins();
        LocalTime endTime = startTime.plusMinutes(slotsNeeded * slotsInMins);
        HashMap<LocalTime, Boolean> daySchedule = schedule.get(date);
        for(LocalTime thisTime : daySchedule.keySet()){
	    	int i = 0;
	    	do{
	    		if(startTime.plusMinutes(i*slotsInMins).equals(thisTime)){
	        		if(daySchedule.get(thisTime).equals(true)){
	        			//ALREADY BOOKED
	        			//DO NOTHING
	        			System.err.println("CODE SHOULD NOT REACH HERE : 001");
	        		}
	        		else{
	        			//NOT BOOKED
	        			//MAKE A BOOKING
	        			daySchedule.put(thisTime, true);
	        			System.out.println(name + " has been booked at " + thisTime);
	        		}
	        	}
	    		i++;
	    	}while(startTime.plusMinutes(i*slotsInMins).isBefore(endTime));
        }
	}
	
	
	public void updateSchedule(HashMap<String,LocalTime> times){
		int days = 31, slotsInMins = employer.getTimeSlotInMins();
		LocalDate currentDate = LocalDate.now();
		LocalDate endDate = currentDate.plusDays(days); //the last day the schedule needs to be put in
		LocalTime startTime = null, endTime = null;
		//increment days for 31 days save to HM
		int i = 0;
		do{
			LocalDate thisDate = currentDate.plusDays(i);
			if(thisDate.getDayOfWeek().equals(DayOfWeek.MONDAY)){
				startTime = times.get("monStart");
				endTime = times.get("monEnd");
			}
			else if(thisDate.getDayOfWeek().equals(DayOfWeek.TUESDAY)){
				startTime = times.get("tueStart");
				endTime = times.get("tueEnd");
			}
			else if(thisDate.getDayOfWeek().equals(DayOfWeek.WEDNESDAY)){
				startTime = times.get("wedStart");
				endTime = times.get("wedEnd");
			}
			else if(thisDate.getDayOfWeek().equals(DayOfWeek.THURSDAY)){
				startTime = times.get("thurStart");
				endTime = times.get("thurEnd");
			}
			else if(thisDate.getDayOfWeek().equals(DayOfWeek.FRIDAY)){
				startTime = times.get("friStart");
				endTime = times.get("friEnd");
			}
			else if(thisDate.getDayOfWeek().equals(DayOfWeek.SATURDAY)){
				startTime = times.get("satStart");
				endTime = times.get("satEnd");
			}
			else if(thisDate.getDayOfWeek().equals(DayOfWeek.SUNDAY)){
				startTime = times.get("sunStart");
				endTime = times.get("sunEnd");
			}
			HashMap<LocalTime, Boolean> daySchedule = new HashMap<LocalTime, Boolean>();
	    	int j = 0;
	    	do{
	    		daySchedule.put(startTime.plusMinutes(j*slotsInMins), false);
	    		//FALSE == UNBOOKED
	    		//TRUE == BOOKED
	    		j++;
	    	}while(!startTime.plusMinutes(j*slotsInMins).isAfter(endTime));
	    	System.out.println(thisDate);
	    	schedule.put(thisDate,daySchedule);
			
			i++;
		}while(!currentDate.plusDays(i).isAfter(endDate));
	}

}
