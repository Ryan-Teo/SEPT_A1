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
	private LocalTime startTime, endTime;
	private Customer bookCust;
	private Business bookBus;
	private Employee bookEmp;
	private String strBus, strCust, strEmp;
	private String service;

	public Booking(LocalDate bookDate, LocalTime bookStart, LocalTime bookEnd, Customer bookCust, Business bookBus, Employee bookEmp, String service){
		this.bookDate = bookDate;
		this.startTime = bookStart;
		this.endTime= bookEnd;
		this.bookCust = bookCust;
		this.bookBus = bookBus;
		this.bookEmp = bookEmp;
		this.setStrBus(bookBus.getBusName());
		this.setStrEmp(bookEmp.getName());
		this.service = service;
	}
	
	
	
	public String getService(){
		return service;
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

	public String getStrBus() {
		return strBus;
	}

	public void setStrBus(String strBus) {
		this.strBus = strBus;
	}

	public String getStrCust() {
		return strCust;
	}

	public void setStrCust(String strCust) {
		this.strCust = strCust;
	}

	public String getStrEmp() {
		return strEmp;
	}

	public void setStrEmp(String strEmp) {
		this.strEmp = strEmp;
	}
}
