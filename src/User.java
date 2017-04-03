import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public abstract class User {

	String name, username, password, address, phone;
	Booking [] booking;
	HashMap<Date, ArrayList<Booking>> schedule;
	
	//constructor
	User(String name, String username, String password, String address, String phone){
		this.name = name;
		this.username = username;
		this.password = password;
		this.address = address;
		this.phone = phone;
	}
	
	//Show all number of bookings made by user
	public abstract void viewBookingSummary(HashMap<Business, HashMap<LocalDate, Booking[]>> bookings);
	
	
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
	public void setSchedule(HashMap<Date, ArrayList<Booking>> schedule) {
		this.schedule = schedule;
	}
	
	public HashMap<Date, ArrayList<Booking>> getSchedule(){
		return this.schedule;
	}


	
	
	
}
