package tests;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;

import system.Booking;
import system.FileIO;
import users.Business;
import users.Customer;
import users.Employee;


/*
 * viewBookingSummary
 * makeBooking
 * cancelBooking
 */





public class CustomerTest {
	FileIO FIO = new FileIO();
	Customer customer = new Customer("Bob", "Bobber", "Bobby123", "Bob@bob.com", "999999999");
	Business myBusiness = new Business("Sal's Hair Salon","Harry","Alumbra St","0120103004","harryOwner","password", "09:00", "17:00", "15");
	ArrayList<Business> businesses = new ArrayList<Business>();
	Employee emp = new Employee("james","james",myBusiness);
	LinkedHashMap<LocalDate,Booking[]> linkedHm = new LinkedHashMap<LocalDate, Booking[]>();
	LinkedHashMap<Business,LinkedHashMap<LocalDate,Booking[]>> businesslinkedHm = new LinkedHashMap<Business,LinkedHashMap<LocalDate,Booking[]>>();
	DateTimeFormatter daytimeformat = DateTimeFormatter.ofPattern("EE");
	DateTimeFormatter timeformat = DateTimeFormatter.ofPattern("HH:mm");
	LocalDate myDate = LocalDate.now();
	HashMap<LocalDate, HashMap<LocalTime, Boolean>> schedule = new HashMap<LocalDate, HashMap<LocalTime, Boolean>>() ; //one month in advance
	LocalTime myTime = LocalTime.parse("09:00", timeformat);
	Scanner scan = new Scanner(System.in);
	ArrayList<Booking> myBooking = new ArrayList<Booking>();
	
	
	
	@Test
	public void testInitialise() {
		
		assertNotNull("should instantiate customer", customer);
		assertTrue(customer.getName() instanceof String);
		assertTrue(customer.getUsername() instanceof String);
		assertTrue(customer.getPassword() instanceof String);
		assertTrue(customer.getAddress() instanceof String);
		assertTrue(customer.getPhone() instanceof String);
	}
	
	
	@Test 
	public void testMakeBooking(){
		myBusiness.addService("male hair cut", 15);
		Boolean value = false;
		value = customer.makeBooking(myDate, myTime, customer, myBusiness, emp, "male hair cut", myBooking);
	
		if(value == true){
			for(Booking custBooking : myBooking){
				Assert.assertEquals(custBooking.getBookCust(),customer);
				Assert.assertEquals(custBooking.getBookDate(), myDate);
				Assert.assertEquals(custBooking.getStartTime(), myTime);
				Assert.assertEquals(custBooking.getStrBus(), myBusiness.getBusName());
				Assert.assertEquals(custBooking.getService(), "male hair cut");
				Assert.assertEquals(custBooking.getBookEmp(), emp);
			}
		
		}
			
	}

	@Test
	public void testViewSummaryBooking(){
		assert (customer.viewBookingSummary(myBooking) != null);
		
	}

	
	@Test
	public void testCancelBooking(){
		myBusiness.addService("male hair cut", 15);
		Boolean value = false;
		value = customer.makeBooking(myDate, myTime, customer, myBusiness, emp, "male hair cut", myBooking);
		value = customer.cancelBooking(myBooking, myBooking.get(0));
		assertEquals(value, true);
	}

}
