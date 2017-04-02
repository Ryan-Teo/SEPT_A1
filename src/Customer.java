import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Customer extends User{
	private ArrayList<Booking> sessions = new ArrayList<Booking>();
	
	Customer(String name, String username, String password, String address, String phone){
		super(name,username,password,address,phone);
	}
	
	//method to display the menu, should be looped in the main.
	public void customerMenu(){
		System.out.println("Welcome " + this.getName() + "!");
		System.out.println("1 : Add Booking"); //refer to bookSession()
		System.out.println("2 : View Bookings");//refer to viewBookingSummary()
		System.out.println("3 : View Sessions of a Business"); //refer to viewSession Method
		System.out.println("4 : Logout");
		System.out.println("0 : Exit");
		System.out.printf("Please select an option: ");
	}

	public void viewSession(String businessName, ArrayList<Business> businesses) {
		for(Business b :businesses){
			if(b.getName().equals(businessName)){
				b.printSchedule();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see User#viewBookingSummary()
	 * Method for customer to check all of their current bookings
	 */
	@Override
	public void viewBookingSummary() {
		System.out.printf("%15s %20s %15s %20s %20s %20s\n", "Date", "Start", "End", "Customer", "Business", "Employee"); //length of each section may need changing
		System.out.println("---------------------------------------------------------------------------------------------");
		//loop that goes through all the session of the customer
		//will then print in the following order:
		//Date/Time, Business Name, Employee of the business taking the job
		Booking.loadAcct(this.getUsername()); 
	}
	
	//Another method of viewBooking summary
	public void viewBookingSummary_1() {

		ArrayList<Booking> sessions = new ArrayList<Booking>();
		ArrayList<Booking> booked = new ArrayList<Booking>();
		
		for(Date date : this.getSchedule().keySet()){
			sessions = this.getSchedule().get(date);
			for(Booking session : sessions){
				System.out.printf("%1$s %2$tB %2$td, %2$tA", "Date:", date);
				System.out.println("----------------------------------");
				if(session.getBookCust() != null){
					booked.add(session);
				}
			}
			for(Booking book : booked){
				System.out.println("Company : " + book.getBookBus());
				System.out.printf("%1$s. %2$tR - %2$tR	","Session time : ",book.getStartTime(),book.getEndTime());
				System.out.println("Employee assigned to this session is : " + book.getBookEmp().getName());
			}
			booked.clear();
		}
	}
	
	//Customer booking function
	public void bookSession(String date, String sessionStart,Business business){
		//Time format for day and hour
		SimpleDateFormat dayFormat = new SimpleDateFormat("EE");
		SimpleDateFormat ft = new SimpleDateFormat("HH:mm");
		Date d;
		Date t;
		
		try {
			//Parsing String to specified date fomat
			d = dayFormat.parse(date);
			t = ft.parse(sessionStart);
			
			//Iterate through schedule of the business and find the specified date
			for(Date day : business.getSchedule().keySet()){
				//when found,
				if(d.equals(day)){
					//Iterate through all the sessions on that day and find a specific session time
					for(Booking book : business.getSchedule().get(day) ){

						//when found,
						if(book.getStartTime().equals(t)){
							
							//Check if no other customer books this session and there is at least
							//an employee assigned to the session
							//If condition is met, update the session info
							if(book.getBookCust() == null && book.getBookEmp() != null){
								book.setCust(this);
								
								//append book into booking list
								sessions.add(book);
								
								//Inform the customer the booking is successful
								System.out.println("Booking successful");
							}
							else{
								//Should we add some exception(?)
								System.out.println("Session is not available");
							}
						}
					}
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}


}
