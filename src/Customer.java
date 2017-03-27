import java.util.ArrayList;
import java.util.Date;

public class Customer extends User{
	private ArrayList<Booking> sessions= new ArrayList();
	
	Customer(String name, String username, String password, String address, String phone){
		super(name,username,password,address,phone);
	}
	
	//method to display the menu, should be looped in the main.
	public void customerMenu(){
		System.out.println("Welcome " + this.getName() + "!");
		System.out.println("Please select an option: ");
		System.out.println("1. Add Booking"); //refer to bookSession()
		System.out.println("2. View Bookings");//refer to viewBookingSummary()
		System.out.println("3. View Sessions of a Business"); //refer to viewSession Method
		System.out.println("0. Exit");
		
	}
	
	@Override
	public void viewSession(String businessName) {
		
	}

	//method for customer to check all of their current bookings
	@Override
	public void viewBookingSummary() {
		System.out.printf("%15s %20s %15s\n", "Date", "Business", "Employee"); //length of each section may need changing
		System.out.println("----------------------------------------------");
		//loop that goes through all the session of the customer
		//will then print in the following order:
		//Date/Time, Business Name, Employee of the business taking the job
		for(int i = 0; i < sessions.size(); i++){
			System.out.printf("%15s %20s %15s\n",sessions.get(i).getTime(), sessions.get(i).getbName(), sessions.get(i).getEmp());
		}
		System.out.println("---END SUMMARY---");
		
	}
	
	public void bookSession(){
		
	}
}