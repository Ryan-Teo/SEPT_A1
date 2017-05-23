package tests;
import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import system.Booking;
import users.Business;
import users.Customer;
import users.Employee;

public class BusinessTest {

	/*
	 * addService
	 * updateService
	 * removeService
	 * viewBookingSummary
	 * showWorkerAvailability
	 * addNewEmp
	 * makeBooking (for cust)
	 */
	private ArrayList<Employee> emps = new ArrayList<Employee>();

	Customer customer = new Customer("Bob", "Bobber", "Bobby123", "Bob@bob.com", "999999999");
	Business myBusiness = new Business("Sal's Hair Salon","Harry","Alumbra St","0120103004","harryOwner","password", null, null, null);
	DateTimeFormatter daytimeformat = DateTimeFormatter.ofPattern("EE");
	DateTimeFormatter timeformat = DateTimeFormatter.ofPattern("HH:mm");
	LocalDate myDate = LocalDate.now();
	LocalTime myTime = LocalTime.parse("09:00", timeformat);
	Employee emp = new Employee("james","james",myBusiness);
	ArrayList<Booking> myBooking = new ArrayList<Booking>();
	private HashMap<String, Integer> services = new HashMap<String, Integer>();
	
	@Test
	public void testPrint(){
		String empID = String.format("emp%03d", 5);
		System.out.println("empID : "+ empID);
	}
	
	@Test
	public void testAddService(){
		try{
			int serviceDuration;
			myBusiness.addService("male hair cut", 30);
			serviceDuration = services.get("male hair cut");
			assertEquals(serviceDuration, 50);
		}catch(Exception e){
			fail("adding service failed");
		}
		
	}
	
	@Test
	public void testUpdateService(){
		int serviceDuration;
		myBusiness.addService("male hair cut", 30);
		serviceDuration = services.get("male hair cut");
		myBusiness.updateService("male hair cut", 50);
		assertEquals(serviceDuration, 50);
		
	}
	
	@Test
	public void testRemoveService(){
		try{
			myBusiness.addService("male hair cut", 30);
			myBusiness.removeService("Male hair cut");
		}catch(Exception e){
			fail("removing service not successful");
		}
		
	}
	
	@Test
	public void testShowWorkerAvailabilities(){
		//TODO
	}
	
	@Test
	public void testViewBookingSummary(){
		myBusiness.makeBooking(myDate, myTime, customer, myBusiness, emp, "male hair cut", myBooking);
		myBusiness.viewBookingSummary(myBooking);
		assert(myBooking != null);
	}
	
	@Test
	public void testMakeBooking(){
		Boolean value = false;
		value = myBusiness.makeBooking(myDate, myTime, customer, myBusiness, emp, "male hair cut", myBooking);
	
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
	public void testAddNewEmp(){
		emps.add(emp);
		
		assertEquals(emps.get(0).getName(),"james");
	}
	
}
