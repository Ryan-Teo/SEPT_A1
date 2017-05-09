kpackage users;
import java.io.Serializable;
import java.util.*;

import org.apache.log4j.Logger;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public class Employee implements Serializable {

	private static final Logger logger = Logger.getLogger(Employee.class);
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
	
	private LocalTime getStartTime(LocalDate date){
		LocalTime thisTime = LocalTime.MAX;
		for(LocalTime myTime : schedule.get(date).keySet()){
			if(myTime.isBefore(thisTime)){
				thisTime = myTime;
			}
		}
		return thisTime;
	}
	
	private LocalTime getEndTime(LocalDate date){
		LocalTime thisTime = LocalTime.MIN;
		if(schedule.containsKey(date)){
			for(LocalTime myTime : schedule.get(date).keySet()){
				if(myTime.isAfter(thisTime)){
					thisTime = myTime;
				}
			}
			return thisTime.plusMinutes(employer.getTimeSlotInMins());
		}
		return thisTime;
	}
	
	public boolean empFree(LocalDate date, LocalTime startTime, String service){
		boolean freeCheck = false;
        int slotsNeeded = employer.getServices().get(service);
        int slotsInMins = employer.getTimeSlotInMins();
        LocalTime endTime = startTime.plusMinutes(slotsNeeded * slotsInMins);
        HashMap<LocalTime, Boolean> daySchedule = schedule.get(date);
        if(endTime.isAfter(getEndTime(date))){
        	return false;
        }
        if(startTime.isBefore(getStartTime(date))){
        	return false;
        }
        for(LocalTime thisTime : daySchedule.keySet()){
	    	int i = 0;
	    	do{
	    		if(startTime.plusMinutes(i*slotsInMins).equals(thisTime)){
	        		if(daySchedule.get(thisTime).equals(true)){
	        			logger.info(name + " is NOT free at " + thisTime);
	        			return false;
	        		}
	        		else{
	        			logger.info(name + " is free at " + thisTime);
	        			freeCheck = true;
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
	        			logger.error("CODE SHOULD NOT REACH HERE : 001");
	        		}
	        		else{
	        			//NOT BOOKED
	        			//MAKE A BOOKING
	        			daySchedule.put(thisTime, true);
	        			logger.info(name + " has been booked at " + thisTime);
	        		}
	        	}
	    		i++;
	    	}while(startTime.plusMinutes(i*slotsInMins).isBefore(endTime));
        }
	}
	
	public void unbookEmp(LocalDate date, LocalTime startTime, String service){
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
	        			//FREE EMP
	        			daySchedule.put(thisTime, false);
	        			logger.info(name + " has been freed at " + thisTime);
	        		}
	        		else{
	        			//NOT BOOKED
	        			//DO NOTHING
	        			logger.error("CODE SHOULD NOT REACH HERE : 001");
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
			//Reset time variables
			startTime=null;
			endTime=null;
			
			LocalDate thisDate = currentDate.plusDays(i);
			if(times.get("monStart")!= null && thisDate.getDayOfWeek().equals(DayOfWeek.MONDAY)){
				startTime = times.get("monStart");
				endTime = times.get("monEnd");
			}
			else if(times.get("tueStart")!= null &&thisDate.getDayOfWeek().equals(DayOfWeek.TUESDAY)){
				startTime = times.get("tueStart");
				endTime = times.get("tueEnd");
			}
			else if(times.get("wedStart")!= null && thisDate.getDayOfWeek().equals(DayOfWeek.WEDNESDAY)){
				startTime = times.get("wedStart");
				endTime = times.get("wedEnd");
			}
			else if(times.get("thurStart")!= null &&thisDate.getDayOfWeek().equals(DayOfWeek.THURSDAY)){
				startTime = times.get("thurStart");
				endTime = times.get("thurEnd");
			}
			else if(times.get("friStart")!= null && thisDate.getDayOfWeek().equals(DayOfWeek.FRIDAY)){
				startTime = times.get("friStart");
				endTime = times.get("friEnd");
			}
			else if(times.get("satStart")!= null &&thisDate.getDayOfWeek().equals(DayOfWeek.SATURDAY)){
				startTime = times.get("satStart");
				endTime = times.get("satEnd");
			}
			else if(times.get("sunStart")!= null &&thisDate.getDayOfWeek().equals(DayOfWeek.SUNDAY)){
				startTime = times.get("sunStart");
				endTime = times.get("sunEnd");
			}
			
			if(startTime!=null && endTime!=null){
				HashMap<LocalTime, Boolean> daySchedule = new HashMap<LocalTime, Boolean>();
		    	int j = 0;
		    	do{
		    		daySchedule.put(startTime.plusMinutes(j*slotsInMins), false);
		    		//FALSE == UNBOOKED
		    		//TRUE == BOOKED
		    		j++;
		    	}while(startTime.plusMinutes(j*slotsInMins).isBefore(endTime));
		    	System.out.println(thisDate);
		    	schedule.put(thisDate,daySchedule);
			}
			
			i++;
		}while(!currentDate.plusDays(i).isAfter(endDate));
	}

}
