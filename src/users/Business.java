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
	private long timeSlotInMins = 30; //Default 30 min slots
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
	public long getTimeSlotInMins(){
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
	
	
//	//Add working time for employee or Assign employee working time 
//	public void addWorkingTime(Employee emp,LinkedHashMap<Business, LinkedHashMap<LocalDate, Booking[]>> bookings,Scanner scan){
//		//Display all sessions with the specified date
//		//List out all of the sessions, and then pair with their index
//		
//		LocalDate dateSelected = null;
//		LocalTime timeSelected = null;
//		boolean addWorkingTimeSuccess = false;
//		try{
//			LinkedHashMap<LocalDate, Booking[]> busBookings = bookings.get(this);
//			do{
//				int i=0;
//				for(LocalDate myDate : busBookings.keySet()){
//					System.out.printf("%4s %2$tB %2$td, %2$tA \n", i+1, myDate);
//					i++;
//					if(i==7){
//						break;
//					}
//				}
//				int dateOption;
//				int timeOption;
//				do{
//					System.out.printf("Please pick your day (or any non-numeral key to cancel): ");
//					dateOption = scan.nextInt()-1;
//					scan.nextLine();
//				}while(!(dateOption>=0 && dateOption<=6));
//				int j=0;
//				for(LocalDate myDate : busBookings.keySet()){
//					if(j==dateOption){
//						dateSelected = myDate;
//						break;
//					}
//					j++;
//				}
//				Booking[] daySlots = busBookings.get(dateSelected);
//				for(int k=0 ; k<daySlots.length; k++){
//					System.out.println("Slot " + (k+1) + " : Time ["+daySlots[k].getStartTime()+" - "+daySlots[k].getEndTime()+"]");
//					System.out.printf("\tEmployee assigned to this session is : %s\n", daySlots[k].getBookEmp().getName());
//				}
//				
//				do{
//					System.out.printf("Please pick your time slot: ");
//					timeOption = scan.nextInt()-1;
//					scan.nextLine();
//				}while(!(timeOption>=0 && timeOption<daySlots.length));
//				timeSelected = daySlots[timeOption].getStartTime();
//				addWorkingTimeSuccess = assignEmpToSession(emp, dateSelected, timeSelected,bookings);
//			}while(!addWorkingTimeSuccess);
//		}catch(IndexOutOfBoundsException e){
//			scan.nextLine();
//			System.out.println("Invalid Input - Returning to menu");
//		}
//}
	
//	public boolean assignEmpToSession(Employee emp, LocalDate date ,LocalTime sessionStart, ArrayList<Booking> bookings){
//		
//		//Time format for day and hour
//				LinkedHashMap<LocalDate, Booking[]> busSchedule = bookings.get(this);
//				Booking[] daySessions;
//				boolean assignEmpSuccess = false;
//				
//				//Iterate through schedule of the business and find the specified date
//				for(LocalDate dayInst : busSchedule.keySet()){ //For each day
//					//when found,
//					if(date.equals(dayInst)){
//						//Iterate through all the sessions on that day and find a specific session time
//						daySessions = busSchedule.get(dayInst);
//						for(int i=0; i<daySessions.length ; i++){
//							if(daySessions[i].getStartTime().equals(sessionStart)){
//									daySessions[i].setEmployee(emp);
//									daySessions[i].booked();
//									System.out.println("-- Booking Successful! --");
//									assignEmpSuccess = true;
//							}
//						}
//					}
//				}
//				return assignEmpSuccess;
//	}

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
	
	public void businessMenu(){
		System.out.println("Welcome " + this.getName() + "!");
		System.out.println("1 : Add Employee");
		System.out.println("2 : Add Working Time/Dates");
		System.out.println("3 : View Booking Summary");
		System.out.println("4 : Add Booking");
		System.out.println("5 : Show Worker Availability");
		System.out.println("0 : Exit");
		System.out.printf("Please select an option: ");
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
	
	//view business weekly schedule 
//	public void printSchedule(){
//		ArrayList<Booking> sessions;
//		int i = 1;
//		for(Date key : this.getSchedule().keySet()){
//			// Day in month - date - day
//			System.out.printf("%1$s %2$tB %2$td, %2$tA", "Date:", key);
//			System.out.println();
//			System.out.println("-----------------------------");
//			System.out.println(String.format("	%-5s 	%-5s","session","Employee"));
//			sessions = this.getSchedule().get(key);
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
	
		//Adding a time slot on a specified day
//	public ArrayList<Booking> addSessionTime(String startTime, String endTime,ArrayList<Booking> session){
//		
//		//Setting time format as Hour:minute
//		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
//		Date start = new Date();
//		Date end = new Date();
//		
//		//Parsing from String to type Date
//		try {
//			start = timeFormat.parse(startTime);
//			end = timeFormat.parse(endTime);
//		} catch (ParseException e1) {
//			e1.printStackTrace();
//		}
//		
//		//As parsing is successful, create a new instance of booking 
//		Booking session_time = new Booking(start,end);
//		
//		//Append a session to the list of sessions
//		session.add(session_time);
//		
//		//return the updated list of sessions
//		return session;
//	}
	
	//Adding an available time slot and assigning an employee to that session directly
//	public ArrayList<Booking> addSessionTime(String startTime, String endTime,ArrayList<Booking> session,Employee emp){
//
//		//Setting time format as Hour:minute
//		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
//		Date start = new Date();
//		Date end = new Date();
//		
//		//Parsing from String to type Date
//		try {
//			start = timeFormat.parse(startTime);
//			end = timeFormat.parse(endTime);
//		} catch (ParseException e1) {
//			e1.printStackTrace();
//		}
//		
//		//As parsing is successful, create a new instance of booking 
//		Booking session_time = new Booking(start,end);
//		
//		//Assign an employee to that session
//		session_time.setEmployee(emp);
//		
//		//append the session to the list
//		session.add(session_time);
//		
//		//return the updated list of sessions
//		return session;
//	}
//		
//		
//	//Adding a new session
//	public void addSession(String day,ArrayList<Booking> new_session){
//		SimpleDateFormat dayFormat = new SimpleDateFormat("EE");
//		Date date;
//		try {
//			date = dayFormat.parse(day);
//			this.getSchedule().put(date,new_session);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}	
//	}
	
//	@SuppressWarnings("unchecked")
//	public void assignEmployee(Employee empName,String day,String session_startTime, Business business){
//		
//		ArrayList<Booking> sessions;
//		LinkedHashMap <Date,ArrayList<Booking>> schedule = business.getSchedule();
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

	//Change starting time of a time slot
//	public void changeSessionStartTime(String day, String startTime){
//		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
//		SimpleDateFormat dayFormat = new SimpleDateFormat("EE");
//		Date date;
//		Date time;
//		try{
//			time = timeFormat.parse(startTime);
//			date = dayFormat.parse(day);
//			for(Date key : this.getSchedule().keySet()){
//				if(key.equals(date)){
//					for(Booking session : this.getSchedule().get(key)){
//						if(session.getStartTime().equals(time)){
//							session.setStartTime(startTime);
//						}
//					}
//				}
//			}
//		}
//		catch(ParseException e){
//			e.printStackTrace();
//		}
//	}
	
	//Change ending time of a time slot
//	public void changeSessionEndTime(String day, String endTime){
//		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
//		SimpleDateFormat dayFormat = new SimpleDateFormat("EE");
//		Date date;
//		Date time;
//
//		try{
//			time = timeFormat.parse(endTime);
//			date = dayFormat.parse(day);
//			for(Date key : this.getSchedule().keySet()){
//				if(key.equals(date)){
//					for(Booking session : this.getSchedule().get(key)){
//						if(session.getStartTime().equals(time)){
//							session.setStartTime(endTime);
//						}
//					}
//				}
//			}
//		}
//		catch(ParseException e){
//			e.printStackTrace();
//		}
//	}

	
	//View all bookings
//	@Override
//	public void viewBookingSummary() {
//		ArrayList<Booking> sessions = new ArrayList<Booking>();
//		ArrayList<Booking> booked = new ArrayList<Booking>();
//		
//		for(Date date : this.getSchedule().keySet()){
//			sessions = this.getSchedule().get(date);
//			for(Booking session : sessions){
//				System.out.printf("%1$s %2$tB %2$td, %2$tA", "Date:", date);
//				System.out.println("----------------------------------");
//				if(session.getBookCust() != null){
//					booked.add(session);
//				}
//			}
//			for(Booking book : booked){
//				System.out.println("");
//				System.out.printf("%1$s. |%2$tR - %2$tR|	","Session time : ",book.getStartTime(),book.getEndTime());
//				System.out.println(" This session is booked for : " + book.getBookCust().getName());
//				System.out.println("Employee assigned to this session is : " + book.getBookEmp().getName());
//			}
//			booked.clear();
//		}
//	}
	
	//view sessions by day (not done)
//	public void viewScheduleByDay(String date){
//		SimpleDateFormat sdf = new SimpleDateFormat("EE");
//		try {
//			Date d = sdf.parse(date);
//			for(Date day : this.getSchedule().keySet()){
//				if(day.equals(d)){
//					this.getSchedule().get(day);
//				}
//			}
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

}

