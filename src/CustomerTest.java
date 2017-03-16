import static org.junit.Assert.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.BeforeClass;
import org.junit.Test;

public class CustomerTest {
	Customer customer = new Customer("Bob", "Bobber", "Bobby123", "Bob@bob.com", "999999999");;
	
	
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

}
