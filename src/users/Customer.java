package users;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import system.Booking;

public class Customer extends User{
	private static final long serialVersionUID = 3L;


	public Customer(String name, String username, String password, String address, String phone){
		super(name,username,password,address,phone);
	}
	
	@Override
	public ObservableList<Booking> viewBookingSummary(ArrayList<Booking> bookings) {	
		
		ObservableList<Booking> bookingsToBeViewed = FXCollections.observableArrayList();
		
		for(int i = 0; i < bookings.size(); i++){
			if(bookings.get(i).getBookCust().getUsername().equals(this.getUsername())){
				bookingsToBeViewed.add(bookings.get(i));
			}
		}
		return bookingsToBeViewed;
	}
	
	@Override
	public boolean bookSession(LocalDate date, LocalTime sessionStart, LocalTime sessionEnd, Customer cust, Business busInst ,Employee emp, ArrayList<Booking> bookings){
				
		if(emp.checkSchedule(date, sessionStart, sessionEnd, busInst.getTimeSlotInMins())){
			Booking newBook = new Booking(date, sessionStart, sessionEnd, cust, busInst, emp);
			bookings.add(newBook);
			return true;
		}
		else
			return false;
	}

	public boolean cancelBooking(ArrayList<Booking> bookings, Booking toCancel){
		for(int i = 0; i < bookings.size(); i++){
			if(bookings.get(i).equals(toCancel)){
				bookings.remove(i);
				//also set the employee to boolean false not in those sessions
				return true;
			}	
		}
		return false;
	}
				
				
}
