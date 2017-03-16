
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BookingTest {
	Customer customer = new Customer("Bob", "Bobber", "Bobby123", "Bob@bob.com", "999999999");;
	Employee emp = new Employee("Harry","Monday,afternoon");
	Booking book_1 = new Booking("ABC","Monday,afternoon",customer,emp);
		
		@Test
		public void initialising(){
			assertNotNull("should instantiate booking", book_1);
			assertTrue(book_1.getBusiness_name() instanceof String);
			assertTrue(book_1.getTime() instanceof String);
			assertTrue(book_1.getCust() instanceof Customer);
			assertTrue(book_1.getEmp() instanceof Employee);
		}
		
		@Test void testBusinessName(){
			
		}
		
		@Test void testCustomerName(){
			
		}
		
		@Test void testEmployeeName(){
			
		}
		
		@Test void testTime(){
			
		}
}
