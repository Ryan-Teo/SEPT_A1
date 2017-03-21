
public class Customer extends User{
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void viewBookingSummary() {
		// TODO Auto-generated method stub
		
	}
	
	public void bookSession(){
		
	}
}
