package users;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedHashMap;

import system.Booking;

public abstract class User implements Serializable{

	private static final long serialVersionUID = 1L;
	String name, username, password, address, phone;
	Booking [] booking;
	
	//constructor
	User(String name, String username, String password, String address, String phone){
		this.name = name;
		this.username = username;
		this.password = password;
		this.address = address;
		this.phone = phone;
	}
	
	//Show all number of bookings made by user
	public abstract void viewBookingSummary(LinkedHashMap<Business, LinkedHashMap<LocalDate, Booking[]>> bookings);
	
	
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

	public Booking[] getBooking() {
		return booking;
	}

}
