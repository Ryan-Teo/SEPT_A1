package tests;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
	Business myBusiness = new Business("Sal's Hair Salon","Harry","Alumbra St","0120103004","harryOwner","password", null, null, null);
	ArrayList<Business> businesses = new ArrayList<Business>();
	Employee emp = new Employee("james","james",myBusiness);
	LinkedHashMap<LocalDate,Booking[]> linkedHm = new LinkedHashMap<LocalDate, Booking[]>();
	LinkedHashMap<Business,LinkedHashMap<LocalDate,Booking[]>> businesslinkedHm = new LinkedHashMap<Business,LinkedHashMap<LocalDate,Booking[]>>();
	DateTimeFormatter daytimeformat = DateTimeFormatter.ofPattern("EE");
	DateTimeFormatter timeformat = DateTimeFormatter.ofPattern("HH:mm");
	LocalDate myDate = LocalDate.now();
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
	public void testName(){
		//perform regex on customer name
		Pattern nameP = Pattern.compile("[a-zA-Z][a-z]*");
		Matcher nameM = nameP.matcher(customer.getName());
		assertTrue(nameM.matches());
		
	}
	
	@Test
	public void testPassword(){
		//perform regex for password
		//for length of password (6-15)
		Pattern passwordP = Pattern.compile("\\w*{6,15}");
		Matcher passwordM = passwordP.matcher(customer.getPassword());
		assertTrue(passwordM.matches());
	}
	
	@Test
	public void testUser(){
		//limit the length of the username (6-15)
		Pattern userP = Pattern.compile("\\w*{6,15}");
		Matcher userM = userP.matcher(customer.getUsername());
		assertTrue(userM.matches());
	}
	
	@Test
	public void testAddress(){
		//regex for email address
		Pattern addressP = Pattern.compile("\\w*.com");
		Matcher addressM = addressP.matcher(customer.getAddress());
		assertTrue(addressM.matches());
	}
	
	@Test
	public void testPhone(){
		//regex for length of phone number, and that it is all numbers
		Pattern phoneP = Pattern.compile("[0-9]{9}");
		Matcher phoneM = phoneP.matcher(customer.getPhone());
		assertTrue(phoneM.matches());
		
	}
	

	
	@Test 
	public void testMakeBooking(){
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
		Boolean value = false;
		value = customer.makeBooking(myDate, myTime, customer, myBusiness, emp, "male hair cut", myBooking);
		value = customer.cancelBooking(myBooking, myBooking.get(0));
		assertEquals(value, true);
	}

}
