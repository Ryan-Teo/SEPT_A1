package system;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import users.Business;
import users.Customer;
import users.Employee;

public class Booking implements Serializable{
	private static final long serialVersionUID = 5L;
	private LocalDate bookDate;
	private LocalTime startTime;
	private LocalTime endTime;
	private Customer bookCust;
	private Business bookBus;
	private Employee bookEmp;
	private boolean isBooked = false;

	public Booking(LocalDate bookDate, LocalTime bookStart, LocalTime bookEnd, Customer bookCust, Business bookBus, Employee bookEmp){
		this.bookDate = bookDate;
		this.startTime = bookStart;
		this.endTime= bookEnd;
		this.bookCust = bookCust;
		this.bookBus = bookBus;
		this.bookEmp = bookEmp;
	}
	
	public LocalDate getBookDate() {
		return bookDate;
	}
	
	public LocalTime getStartTime() {
		return startTime;
	}
	
	public LocalTime getEndTime() {
		return endTime;
	}
	
	public Customer getBookCust() {
		return bookCust;
	}
	
	public Business getBookBus() {
		return bookBus;
	}
	
	public Employee getBookEmp() {
		return bookEmp;
	}

	public void setEmployee(Employee emp){
		this.bookEmp = emp;
	}
	
	public void setCust(Customer cust){
		this.bookCust = cust;
	}
	
	public boolean getBookStat(){
		return isBooked;
	}
	
	public void unbooked(){
		isBooked = false;
	}
	public void booked(){
		isBooked = true;
	}

//	Why do we need setters? 
//	Set start time on a particular time slot
//	public void setStartTime(String time) {
//		SimpleDateFormat ft = new SimpleDateFormat("HH:mm");
//		try {
//			this.startTime = ft.parse(time);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//	}
	
//	Set end time on a particular time slot
//	public void setEndTime(String time) {
//		SimpleDateFormat ft = new SimpleDateFormat("HH:mm");
//		try {
//			this.endTime = ft.parse(time);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//	}

}