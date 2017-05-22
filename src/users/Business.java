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

	private final static Logger logger = Logger.getLogger(Business.class);
	private static final long serialVersionUID = 2L;
	private String busName, openTime, closeTime, sessionTime;
	private ArrayList<Employee> emps = new ArrayList<Employee>();
	private LocalTime openTimeLocal, closeTimeLocal; //hardcoded
	private int sessionTimeLocal;
	private HashMap<String, Integer> services = new HashMap<String, Integer>();
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
	
	public Business(String busName, String ownerName, String address, String phone, String username, String password, String openTime, String closeTime, String timeSlotInMins){
		super(ownerName,username,password,address,phone);
		this.busName = busName;
		this.openTime = openTime;
		this.closeTime = closeTime;
		this.sessionTimeLocal = sessionTimeLocal;
//		String start = "09:00" , end = "17:00"; //HARDCODED REMOVE
		
		
//		openTime = LocalTime.parse(start, dtf);
//		closeTime = LocalTime.parse(end, dtf);
//		services.put("General", 1);
//		services.put("General 2", 2); //HARDCODED REMOVE
//		services.put("General 3", 3);
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
		openTimeLocal = LocalTime.parse(openTime, dtf);
		return openTimeLocal;
	}
	
	//Get closing hour
	public LocalTime getCloseTime(){
		closeTimeLocal = LocalTime.parse(closeTime, dtf);
		return closeTimeLocal;
	}
	
	//Set opening hour
	public void setOpenTime(LocalTime openTimeLocal){
		this.openTime = openTimeLocal.toString();
	}
	
	//Set closing hour
	public void setCloseTime(LocalTime closeTimeLocal){
		this.closeTime = closeTimeLocal.toString();
	}
	
	//Get length of each time slot in minutes
	public int getTimeSlotInMins(){
		sessionTimeLocal = Integer.parseInt(sessionTime);
		return sessionTimeLocal;
	}
	
	//Set length of each time slot in minutes
	public void setSessionTime(int sessionTimeLocal){
		sessionTime = Integer.toString(sessionTimeLocal);
	}
	
	//return list of employees
	public ArrayList<Employee> getEmps() {
		return emps;
	}
	
	//Get HM of Services and how many slots each service will take
	public HashMap<String, Integer> getServices(){
		return services;
	}
	
	//Adding a service to a business
	public void addService(String serviceName, int noOfTimeSlots){
		if(services.containsKey(serviceName)){
			logger.error("Add Service : Service already exists");
		}
		else if(noOfTimeSlots<1){ //Minimum 1 slot
			logger.error("Each service has to take up at least one time slot");
		}
		else{
			services.put(serviceName, noOfTimeSlots); 
			logger.info( "'"+ serviceName +"'"+" service has been added to the business");
		}
	}
	
	//Updating a service a business has
	public void updateService(String serviceName, int noOfTimeSlots){
		if(services.containsKey(serviceName)){
			services.put(serviceName, noOfTimeSlots); //LOG
			logger.info( "'"+ serviceName +"'"+" service has been added updated");
		}
		else if(noOfTimeSlots<1){ //Minimum 1 slot
			logger.error("Each service has to take up at least one time slot");
		}
		else{
			logger.error("Update Service : Service does not exist");
		}
	}
	
	//Removing service from business
	public void removeService(String serviceName){
		if(services.containsKey(serviceName)){
			services.remove(serviceName);
			logger.info("'"+ serviceName +"'"+" service has been removed successfully"); 
		}
		else{
			logger.info("Remove Service : Service does not exist");
		}
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
	
	//TODO
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
	@Override
	public boolean makeBooking(LocalDate date, LocalTime startTime, Customer bookCust, Business bus, Employee myEmp,
			String service, ArrayList<Booking> bookings) {
		// TODO Auto-generated method stub
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
}

