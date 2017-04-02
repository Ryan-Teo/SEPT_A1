import java.util.ArrayList;

public abstract class User {

	String name, username, password, address, phone;
	Booking [] booking;
	ArrayList<Booking> schedule;
	
	//constructor
	User(String name, String username, String password, String address, String phone){
		this.name = name;
		this.username = username;
		this.password = password;
		this.address = address;
		this.phone = phone;
	}
	
	//Show all number of bookings made by user
	public abstract void viewBookingSummary();

	public String getName() {
		return name;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getAddress() {
		return address;
	}

	public String getPhone() {
		return phone;
	}

	// ?
	public Booking[] getBooking() {
		return booking;
	}
	
	//What are these schedules for?
	public void setSchedule(ArrayList<Booking> schedule) {
		this.schedule = schedule;
	}
	
	public ArrayList<Booking> getSchedule(){
		return this.schedule;
	}
	
	
	
}
