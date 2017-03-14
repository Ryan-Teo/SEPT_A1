import static org.junit.Assert.*;

import org.junit.Test;

public class CustomerTest {

	@Test
	public void testInitialise() {
		Customer customer = new Customer("Bob", "Bobber", "Bobby123", "Bob@bob.com", "999999999");
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
	}
	
	@Test
	public void testPassword(){
		//perform regex for password
		//for length of password (6-15)
	}
	
	@Test
	public void testUser(){
		//limit the length of the username (6-15)
	}
	
	@Test
	public void testAddress(){
		//regex for email address
	}
	
	@Test
	public void testPhone(){
		//regex for length of phone number, and that it is all numbers
		
	}

}
