package users;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import system.Booking;

public class Business extends User {

	private static final long serialVersionUID = 2L;
	private String busName;
	private ArrayList<Employee> emps = new ArrayList<Employee>();
	private LocalTime openTime, closeTime; //hardcoded
	private int timeSlotInMins = 30; //Default 30 min slots
	private HashMap<String, Integer> services = new HashMap<String, Integer>();
	
	public Business(String busName, String ownerName, String address, String phone, String username, String password){
		super(ownerName,username,password,address,phone);
		this.busName = busName;
		
		String start = "09:00" , end = "17:00";
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
		
		openTime = LocalTime.parse(start, dtf);
		closeTime = LocalTime.parse(end, dtf);
		services.put("General", 1);
		services.put("General 2", 2); //HARDCODED REMOVE
		services.put("General 3", 3);
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
	public void setOpenTime(LocalTime openTime){
		this.openTime = openTime;
	}
	
	//Set closing hour
	public void setCloseTime(LocalTime closeTime){
		this.closeTime = closeTime;
	}
	
	//Get length of each time slot in minutes
	public int getTimeSlotInMins(){
		return timeSlotInMins;
	}
	
	//Set length of each time slot in minutes
	public void setTimeSlotInMins(int timeSlot){
		timeSlotInMins = timeSlot;
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
			System.err.println("Add Service : Service already exists"); //LOG
		}
		else if(noOfTimeSlots<1){ //Minimum 1 slot
			System.err.println("Each service has to take up at least one time slot"); //LOG
		}
		else{
			services.put(serviceName, noOfTimeSlots); //LOG
		}
	}
	
	//Updating a service a business has
	public void updateService(String serviceName, int noOfTimeSlots){
		if(services.containsKey(serviceName)){
			services.put(serviceName, noOfTimeSlots); //LOG
		}
		else if(noOfTimeSlots<1){ //Minimum 1 slot
			System.err.println("Each service has to take up at least one time slot"); //LOG
		}
		else{
			System.err.println("Update Service : Service does not exist"); //LOG
		}
	}
	
	//Removing service from business
	public void removeService(String serviceName){
		if(services.containsKey(serviceName)){
			services.remove(serviceName); //LOG
		}
		else{
			System.err.println("Remove Service : Service does not exist"); //LOG
		}
	}

	//View all bookings for a business
	@Override
	public ObservableList<Booking> viewBookingSummary(ArrayList<Booking>bookings) {	
		ObservableList<Booking> bookingsToBeViewed = FXCollections.observableArrayList();
		for(int i = 0; i < bookings.size(); i++){
			if(bookings.get(i).getBookBus().equals(this.getBusName())){
				bookingsToBeViewed.add(bookings.get(i));
			}
		}
		return bookingsToBeViewed;
	}
	
	//Adding booking on behalf of customer
	//Do we need to make "makeBooking" as a function in the super class?
	public void makeBooking(){
		
	}
	
	public void showWorkerAvailability(){
		//display all employees
		//choose employees from the given options
		//Show the worker's availability
		//==== OR ====
		//Display all workers along side with their availabilities
	}
	
	public void addEmp(Employee myEmp){
		emps.add(myEmp);
	}
	
	//Adding a new employee into the business
	public void addNewEmp(String empName){
		String empID;
		//Make sure emp id is unique
		empID = String.format("emp%03d", emps.size());
		Employee new_emp = new Employee(empID,empName,this);
		emps.add(new_emp); //LOG
	}

	@Override
	public boolean bookSession(LocalDate date, LocalTime sessionStart, LocalTime sessionEnd, Customer cust,
			Business busInst, Employee emp, ArrayList<Booking> bookings) {
		// TODO Auto-generated method stub
		return false;
	}


}

