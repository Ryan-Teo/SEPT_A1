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
	public Booking[] initTimeSlots(LocalDate myDate,Business myBusiness){
		Booking[] bookings = new Booking[16];
		Employee emp = new Employee("emp001","Juls",myBusiness);
		Customer cust = new Customer("TestCust", "TestUserCust", "password", "120 Address Str", "0435261626");
		LocalTime startTime, endTime;
		String start = "09:00";
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
		
		startTime = LocalTime.parse(start, dtf);
		endTime = startTime.plusMinutes(30);
		
		return bookings ;
	}
	
	public LinkedHashMap<LocalDate,Booking[]> initDaySlots(Business myBusiness){
	     int days = 31;
	     
		 LinkedHashMap<LocalDate,Booking[]> hm = new  LinkedHashMap<LocalDate,Booking[]>();
		 LocalDate currentDate = LocalDate.now();
		 
		for(int i=0; i<days; i++){
			hm.put(currentDate.plusDays(i), initTimeSlots(currentDate.plusDays(i),myBusiness));
		}
		return  hm;
	}

	@Test
	public void testPrint(){
		String empID = String.format("emp%03d", 5);
		System.out.println("empID : "+ empID);
	}
	
}
