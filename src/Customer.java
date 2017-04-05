import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Customer extends User{
	private static final long serialVersionUID = 3L;


	Customer(String name, String username, String password, String address, String phone){
		super(name,username,password,address,phone);
	}
	
	//method to display the menu, should be looped in the main.
	public void customerMenu(){
		System.out.println("Welcome, " + this.getName() + "!");
		System.out.println("1 : Add Booking"); //refer to bookSession()
		System.out.println("2 : View Bookings");//refer to viewBookingSummary()
		System.out.println("3 : View Sessions of a Business"); //refer to viewSession Method
		System.out.println("4 : Logout");
		System.out.println("0 : Exit");
		System.out.printf("Please select an option: ");
	}

	public void viewSession(String businessName, LinkedHashMap<Business,LinkedHashMap<LocalDate, Booking[]>> hm) {
		int counter = 0;
		int seven_days = 7;
		for(Business key : hm.keySet()){
			if(key.getBusName().equals(businessName)){
				LinkedHashMap<LocalDate, Booking[]> businessSched = hm.get(key);
				for(LocalDate myDate : businessSched.keySet()){		//For each date
					System.out.printf("%1$s %2$tB %2$td, %2$tA \n", "Date:", myDate);
					System.out.println("----------------------------------");
			
					Booking[] myBooking = businessSched.get(myDate);
					for(int i =0 ; i< myBooking.length; i++){	//For all bookings on each day
						System.out.println("Session time : "+myBooking[i].getStartTime()+" - "+myBooking[i].getEndTime());
						System.out.println("Employee assigned to this session is : " + myBooking[i].getBookEmp().getName());
					}
					
					counter++;
					if(counter == seven_days){
						break;
					
					}
				}
			}
		}
	}


	/*
	 * (non-Javadoc)
	 * @see User#viewBookingSummary()
	 * Method for customer to check all of their current bookings
	 */
	@Override
	public void viewBookingSummary(LinkedHashMap<Business, LinkedHashMap<LocalDate, Booking[]>> bookings) {	
		
		for(Business myBus : bookings.keySet()){	//For each business
			LinkedHashMap<LocalDate, Booking[]> myDay = bookings.get(myBus);	//For each business LinkedHashMap
			for(LocalDate myDate : myDay.keySet()){		//For each date
				Booking[] myBooking = myDay.get(myDate);
				for(int i=0 ; i < myBooking.length; i++){	//For all bookings on each day
					if(myBooking[i].getBookStat()){
						if(myBooking[i].getBookCust().equals(this)){
							System.out.println("----------------------------------");
							System.out.println("Business Name : " + myBus.getBusName());
							System.out.printf("%1$s %2$tB %2$td, %2$tA \n", "Date:", myDate);
							System.out.println("Session time : "+myBooking[i].getStartTime()+" - "+myBooking[i].getEndTime());
							System.out.println("Employee assigned to this session is : " + myBooking[i].getBookEmp().getName());
						}
					}
				}		
			}
		}
	}
	
	
	//Customer booking function
	private boolean bookSession(LocalDate date, LocalTime sessionStart, Business busInst ,LinkedHashMap<Business, LinkedHashMap<LocalDate, Booking[]>> bookings){
				
		//Time format for day and hour
		LinkedHashMap<LocalDate, Booking[]> busSchedule = bookings.get(busInst);
		Booking[] daySessions;
		boolean bookingSuccess = false;
		
		//Iterate through schedule of the business and find the specified date
		for(LocalDate dayInst : busSchedule.keySet()){ //For each day
			//when found,
			if(date.equals(dayInst)){
				//Iterate through all the sessions on that day and find a specific session time
				daySessions = busSchedule.get(dayInst);
				for(int i=0; i<daySessions.length ; i++){
					if(daySessions[i].getStartTime().equals(sessionStart)){
						if(daySessions[i].getBookStat()){
							//Slot already booked
							System.out.println("-- Sorry This Slot Is Taken --");
							bookingSuccess = false;
						}
						else{
//							bookings.get(busInst).get(date)[i].setCust(this);
//							bookings.get(busInst).get(date)[i].booked();
							daySessions[i].setCust(this);
							daySessions[i].booked();
							System.out.println("-- Booking Successful! --");
							bookingSuccess = true;
						}
					}
				}
			}
		}
		return bookingSuccess;
	}
	
	public void makeBooking(LinkedHashMap<Business, LinkedHashMap<LocalDate, Booking[]>> bookings, ArrayList<Business> businesses, Scanner scan){
		//change of plans!
		//list out all of the businesses, and then pair with their index.  Then let the user pick using an index
		Business busInst = null;
		int businessID = -1;
		LocalDate dateSelected = null;
		LocalTime timeSelected = null;
		boolean bookingSuccess = false;
		
		
		System.out.println("-----Displaying Available Businesses-----");
		System.out.printf("%4s %15s\n", "ID", "Business Name");
		System.out.println("---------------------------------------------------");
		for(int i = 0; i < businesses.size(); i++){
			System.out.printf("%4s %15s\n", i, businesses.get(i).getBusName());
		}
		System.out.println("---------------------------------------------------");
		
		try{
			System.out.println("Please enter the business ID you would like to book for: ");
			businessID = scan.nextInt();
			scan.nextLine(); //CONSUME
			busInst = businesses.get(businessID);
			LinkedHashMap<LocalDate, Booking[]> busBookings = bookings.get(busInst);
			do{
				int i=0;
				for(LocalDate myDate : busBookings.keySet()){
					System.out.printf("%4s %2$tB %2$td, %2$tA \n", i, myDate);
					i++;
					if(i==7){
						break;
					}
				}
				int dateOption;
				int timeOption;
				do{
					System.out.printf("Please pick your day: ");
					dateOption = scan.nextInt();
					scan.nextLine();
				}while(!(dateOption>=0 && dateOption<=6));
				int j=0;
				for(LocalDate myDate : busBookings.keySet()){
					if(j==dateOption){
						dateSelected = myDate;
						break;
					}
					j++;
				}
				Booking[] daySlots = busBookings.get(dateSelected);
				for(int k=0 ; k<daySlots.length; k++){
					if(daySlots[k].getBookStat()==false){
						System.out.println("Slot " + k + " : Time ["+daySlots[k].getStartTime()+" - "+daySlots[k].getEndTime()+"]");
						System.out.printf("\tEmployee assigned to this session is : %s\n", daySlots[k].getBookEmp().getName());
					}
					else{
						System.out.printf("Slot %s : %s\n",k,"UNAVAILABLE");
					}
				}
				
				do{
					System.out.printf("Please pick your time slot: ");
					timeOption = scan.nextInt();
					scan.nextLine();
				}while(!(timeOption>=0 && timeOption<daySlots.length));
				timeSelected = daySlots[timeOption].getStartTime();
				bookingSuccess = bookSession(dateSelected, timeSelected, busInst, bookings);
			}while(!bookingSuccess);
		}catch(NumberFormatException e){
			System.out.println("Invalid Input");
		}						
	}

}
