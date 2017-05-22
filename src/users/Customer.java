package users;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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
	public boolean makeBooking(LocalDate date, LocalTime startTime, Customer bookCust, Business bus, 
								Employee myEmp, String service, ArrayList<Booking> bookings){
    	int bookingLen = bus.getServices().get(service)*bus.getTimeSlotInMins();
    	bookings.add(new Booking(date, startTime, startTime.plusMinutes(bookingLen), this ,bus, myEmp, service));
    	myEmp.bookEmp(date, startTime, service);

		return true;
	}

	public boolean cancelBooking(ArrayList<Booking> bookings, Booking booking){
		booking.getBookEmp().unbookEmp(booking.getBookDate(), booking.getStartTime(), booking.getService());
		if(bookings.remove(booking)){
			return true;
		}
		return false;
	}
				
				
}
