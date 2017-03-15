
public abstract class User {

	String name, username, password, address, phone;
	Booking [] booking;
	
	User(String name, String username, String password, String address, String phone){
		this.name = name;
		this.username = username;
		this.password = password;
		this.address = address;
		this.phone = phone;
	}
	
	//View all available sessions within the business
	public abstract void viewSession(String businessName);
	
	//Show all number of bookings made by user
	public abstract void viewBookingSummary();
	
	
}
