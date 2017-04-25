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
import system.Helper;
import users.Business;
import users.Customer;
import users.Employee;

public class BusinessTest {

	public Booking[] initTimeSlots(LocalDate myDate,Business myBusiness){
		Booking[] bookings = new Booking[16];
		Employee emp = new Employee("emp001","Juls",myBusiness);
		Customer cust = new Customer("TestCust", "TestUserCust", "password", "120 Address Str", "0435261626");
		LocalTime startTime, endTime;
		String start = "09:00";
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
		
		startTime = LocalTime.parse(start, dtf);
		endTime = startTime.plusMinutes(30);
		
		for(int i=0 ; i < bookings.length ; i++){
			bookings[i] = new Booking(myDate,startTime.plusMinutes(30*i),endTime.plusMinutes(30*i),cust,myBusiness,emp);
		}
		
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
	
	//asd
	
	//Dummy schedule and Business
	Helper help = new Helper();
	Business business = new Business("Bob inc.", "Bobb","iloveyou","Franklin Street","03 9924 3812","   sas");
	
	
	//Dummy Employees
	Employee emp_1 = new Employee("Chris","chris",business);
	Employee emp_2 = new Employee("John Wall", "Wall",business);
	Employee emp_3 = new Employee("William Stryker", "Stryker",business);

	Customer cust = new Customer("Harry","HARRY","RMIT","01010102030","hello");
	
	//Date format for Day
	SimpleDateFormat sdf = new SimpleDateFormat("EE");
	String [] days = {"monday","tuesday","wednesday"};
	
	LocalTime startTime = LocalTime.now();
	LocalTime endTime = startTime.plusMinutes(60);
	LocalDate date = LocalDate.now();
	
	Booking  book_1 = new Booking( date, startTime , endTime,cust, business, emp_1);
	Booking  book_2 = new Booking( date, startTime , endTime,cust, business, emp_1);
	Booking  book_3 = new Booking( date, startTime , endTime,cust, business, emp_1);
	
	Booking [] book = {book_1,book_2,book_3};
	
	@Test
	public void testPrintSchedule(){
		LinkedHashMap<LocalDate, Booking[]> busDates = initDaySlots(business);	
		
//		busDates.put(date, book);
//		busDates.put(date.plusDays(1), book);
//		busDates.put(date.plusDays(1), book);
//		busDates.put(date.plusDays(1), book);
//		busDates.put(date.plusDays(1), book);
		
			for(LocalDate myDate : busDates.keySet()){		//For each date
				System.out.printf("%1$s %2$tB %2$td, %2$tA \n", "Date:", myDate);
				System.out.println("----------------------------------");
				Booking[] myBooking = busDates.get(myDate);
				for(int i=0 ; i < myBooking.length; i++){	//For all bookings on each day
					if(myBooking[i].getBookCust().equals(this)){ //REMOVE "!"
						System.out.printf("%1$s. %2$tR - %2$tR	","Session time : ",myBooking[i].getStartTime(),myBooking[i].getEndTime());
						System.out.println("Employee assigned to this session is : " + myBooking[i].getBookEmp().getName());
					}
				}
								
			}
	}
	
	@Test
	public void testInitialise() {
		
		
//		//Name of the business
//		assertTrue(business.getBusName() instanceof String);
//		
//		//Name of the Owner
//		assertTrue(business.getOwnerName() instanceof String);
//		
//		//List of Employees
//		assertTrue(business.getEmp() instanceof ArrayList);
//		
//		//Working schedule of the business
//		assertTrue(business.getSchedule() instanceof HashMap);
	}
	
	@Test
	//Testing Name should be in Type String and not numeric
	public void testName(){
		Pattern nameP = Pattern.compile("[a-zA-Z][a-z]*");
		Matcher nameM = nameP.matcher(business.getOwnerName());
		assertTrue(nameM.matches());
	}
	
	@Test
	//Testing Name should be in Type String and not numeric
	public void testBusinessName(){
		Pattern nameP = Pattern.compile("[a-zA-Z][a-z]*");
		Matcher nameM = nameP.matcher(business.getBusName());
		assertTrue(nameM.matches());
	}
}
