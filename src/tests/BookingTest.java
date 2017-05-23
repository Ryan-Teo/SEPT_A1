package tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Test;

import system.Booking;
import users.Business;
import users.Customer;
import users.Employee;

public class BookingTest {
	//Mock data
	Business myBus =  new Business("bobobo","bobobo","password","password","044412121", "haha","haha", null, null);
	Customer myCustomer = new Customer("Bob", "Bobber", "Bobby123", "Bob@bob.com", "999999999");;
	Employee myEmp = new Employee("e001","Ryan The Maid",myBus);
	LocalDate bookDate = LocalDate.now();
	LocalTime bookStart = LocalTime.now();
	LocalTime bookEnd = bookStart.plusMinutes(30);
	//everything are getters and setters, does not need for unit test
		
		@Test void initializingBookingInstance(){
			Booking newBooking = new Booking(bookDate,bookStart,bookEnd,myCustomer,myBus,myEmp,null);
			
		}
		
		@Test void testEmployeeName(){
			
		}
		
		@Test void testTime(){
			
		}
}
