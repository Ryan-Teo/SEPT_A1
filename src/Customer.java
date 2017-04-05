import java.time.LocalDate;
import java.util.LinkedHashMap;

public class Customer extends User{
	private static final long serialVersionUID = 1L;


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
<<<<<<< HEAD
		int counter = 1;
		int seven_days = 7;
		for(Business key : hm.keySet()){
			if(key.getName().equals(businessName)){
				LinkedHashMap<LocalDate, Booking[]> businessSched = hm.get(key);
				for(LocalDate myDate : businessSched.keySet()){		//For each date
					System.out.printf("%1$s %2$tB %2$td, %2$tA \n", "Date:", myDate);
					System.out.println("----------------------------------");
			
					Booking[] myBooking = businessSched.get(myDate);
					for(int i =0 ; i< myBooking.length; i++){	//For all bookings on each day
						System.out.printf("%1$s. %2$tR - %2$tR	","Session time : ",myBooking[i].getStartTime(),myBooking[i].getEndTime());
						System.out.println("Employee assigned to this session is : " + myBooking[i].getBookEmp().getName());
					}
					
					counter++;
					if(counter == seven_days){
						break;
					
					}
				}
=======
		for(Business b : hm.keySet()){
			if(b.getName().equals(businessName)){
				b.printSchedule();
>>>>>>> 39d6792be4e2c35acf83a6d017c36d8d81b65065
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
					if(myBooking[i].getBookCust().equals(this)){
						System.out.println("Business Name : " + myBus.getBusName());
						System.out.printf("%1$s %2$tB %2$td, %2$tA \n", "Date:", myDate);
						System.out.println("----------------------------------");
						System.out.printf("%1$s. %2$tR - %2$tR	","Session time : ",myBooking[i].getStartTime(),myBooking[i].getEndTime());
						System.out.println("Employee assigned to this session is : " + myBooking[i].getBookEmp().getName());
					}
				}
								
			}
		}
	}
	
	
	//Customer booking function
//	public void bookSession(String date, String sessionStart, Business busInst){
//				
//		//Time format for day and hour
//		SimpleDateFormat dayFormat = new SimpleDateFormat("EE");
//		SimpleDateFormat ft = new SimpleDateFormat("HH:mm");
//		Date d;
//		Date t;
//		
//		try {
//			//Parsing String to specified date format
//			d = dayFormat.parse(date);
//			t = ft.parse(sessionStart);
//			
//			//Iterate through schedule of the business and find the specified date
//			for(Date day : busInst.getSchedule().keySet()){
//				//when found,
//				if(d.equals(day)){
//					//Iterate through all the sessions on that day and find a specific session time
//					for(Booking book : busInst.getSchedule().get(day) ){
//
//						//when found,
//						if(book.getStartTime().equals(t)){
//							
//							//Check if no other customer books this session and there is at least
//							//an employee assigned to the session
//							//If condition is met, update the session info
//							if(book.getBookCust() == null) {
//								book.setCust(this);
//								
//								//Inform the customer the booking is successful
//								System.out.println("Booking successful");
//							}
//							else{
//								//Should we add some exception(?)
//								System.out.println("Session is not available");
//							}
//						}
//					}
//				}
//			}
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		
//	}

}
