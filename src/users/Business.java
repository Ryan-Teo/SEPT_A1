package users;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.apache.log4j.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import system.Booking;

public class Business extends User {
	private static final long serialVersionUID = 2L;

	private final static Logger logger = Logger.getLogger(Business.class);
	private String busName;
	private ArrayList<Employee> emps = new ArrayList<Employee>();
	private LocalTime openTime, closeTime;
	private int sessionTime;
	private ArrayList<Service> services = new ArrayList<Service>();
	
	public Business(String busName, String ownerName, String address, String phone, String username, String password, String openTime, String closeTime, String sessionTime){
		super(ownerName,username,password,address,phone);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
		this.busName = busName;
		this.sessionTime = Integer.parseInt(sessionTime);
		this.openTime = LocalTime.parse(openTime, dtf);
		this.closeTime = LocalTime.parse(closeTime, dtf);
		services.add(new Service("General", 1));
	}
	
	//return business name
	public String getBusName(){
		return this.busName;
	}
	
	//return owner name
	public String getOwnerName(){
		return this.name;
	}
	
	//Get opening hour
	public LocalTime getOpenTime(){
		return openTime;
	}
	
	//Get closing hour
	public LocalTime getCloseTime(){
		return closeTime;
	}
	
	//Set opening hour
	public void setOpenTime(LocalTime openTimeLocal){
		this.openTime = openTimeLocal;
	}
	
	//Set closing hour
	public void setCloseTime(LocalTime closeTimeLocal){
		this.closeTime = closeTimeLocal;
	}
	
	//Get length of each time slot in minutes
	public int getSessionTime(){
		return sessionTime;
	}
	
	//Set length of each time slot in minutes
	public void setSessionTime(int sessionTimeLocal){
		sessionTime = sessionTimeLocal;
	}
	
	//return list of employees
	public ArrayList<Employee> getEmps() {
		return emps;
	}
	
	//Get HM of Services and how many slots each service will take
	public ArrayList<Service> getServiceList(){
		return services;
	}
	
	public Service getService(String serviceName){
		Service thisService = null;
		for(Service myServe : services){
			if(myServe.getServiceName().equals(serviceName)){
				thisService = myServe;
			}
		}
		if(thisService == null){
			logger.debug("This service does not exist, should not happen");
		}
		return thisService;
	}
	
	//Adding a service to a business
	public void addService(String serviceName, int noOfTimeSlots){
		//check service dosent exist
		//check if time slot is more than 1
		//if not add service
		for(Service myServe : services){
			if(myServe.getServiceName().equals(serviceName)){
				logger.error("Add Service : Service already exists");
				return;
			}
		}
		
		if(noOfTimeSlots<1){ //Minimum 1 slot
			logger.error("Each service has to take up at least one time slot");
			return;
		}
		
		services.add(new Service(serviceName, noOfTimeSlots));
		logger.info( "'"+ serviceName +"'"+" service has been added to the business");
	}
	
	//Updating a service a business has
	public void updateService(String serviceName, int noOfTimeSlots){
		if(noOfTimeSlots<1){ //Minimum 1 slot
			logger.error("Each service has to take up at least one time slot");
			return;
		}
		
		for(Service myServe : services){
			if(myServe.getServiceName().equals(serviceName)){
				myServe.setBlocks(noOfTimeSlots);
				logger.info( "'"+ serviceName +"'"+" service has been updated");
				return;
			}
		}
		
		logger.error("Update Service : Service does not exist");
		
	}
	
	//Removing service from business
	public void removeService(String serviceName){
		for(Service myServe : services){
			if(myServe.getServiceName().equals(serviceName)){
				services.remove(myServe);
				logger.info("'"+ serviceName +"'"+" service has been removed successfully"); 
				return;
			}
		}
		logger.info("Remove Service : Service does not exist");
	}

	//View all bookings for a business
	@Override
	public ObservableList<Booking> viewBookingSummary(ArrayList<Booking>bookings) {	
		ObservableList<Booking> bookingsToBeViewed = FXCollections.observableArrayList();
		for(int i = 0; i < bookings.size(); i++){
			if(bookings.get(i).getBookBus().getBusName().equals(this.getBusName())){
				bookingsToBeViewed.add(bookings.get(i));
			}
		}
		return bookingsToBeViewed;
	}
	
	public void showWorkerAvailability(){
		//display all employees
		//choose employees from the given options
		//Show the worker's availability
		//==== OR ====
		//Display all workers along side with their availabilities
	}
	
	public void addEmp(Employee myEmp){
		logger.info("New employee has been added to the system");
		emps.add(myEmp);
	}
	
	//Adding a new employee into the business
	public void addNewEmp(String empName){
		String empID;
		//Make sure emp id is unique
		empID = String.format("emp%03d", emps.size());
		Employee new_emp = new Employee(empID,empName,this);
		emps.add(new_emp);
	}
	
//	Adding booking on behalf of customer
//	Need to check make sure it does not break anything
	@Override
	public boolean makeBooking(LocalDate date, LocalTime startTime, Customer bookCust, Business bus, 
			Employee myEmp, String service, ArrayList<Booking> bookings){
		return false;
	}
	
	public boolean slotExists(LocalDate date){
		boolean exists = false;
		for(Employee emp : emps){
			if(emp.getSchedule().containsKey(date)){
				for(LocalTime time : emp.getSchedule().get(date).keySet()){
					if(emp.getSchedule().get(date).get(time).equals(false)){
						//If one employee is free, return true and give date as an option
						return true;
					}
				}
			}
		}
		//If not return false and remove data as option
		return exists;
	}
	
	public LocalDate earliestAvilable(){
		LocalDate date = LocalDate.MAX;
		for(Employee emp : emps){
			for(LocalDate thisDate : emp.getSchedule().keySet()){
				for(LocalTime time : emp.getSchedule().get(thisDate).keySet()){
					if(emp.getSchedule().get(thisDate).get(time).equals(false)){
						if(thisDate.isBefore(date)){
							date = thisDate;
						}
					}
				}
			}
		}
		return date;
	}
}

