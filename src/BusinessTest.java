import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class BusinessTest {
	
	//list of employees
	//list of times
	
	Business business = new Business("Bob inc.", "Bobb",null ,null );
	@Test
	public void testInitialise() {
		assertTrue(business.getName() instanceof String);
		assertTrue(business.getOwner() instanceof String);
		assertTrue(business.getEmp() instanceof ArrayList<?>);
		assertTrue(business.getSession() instanceof ArrayList<?>);
	}
	
	@Test
	public void testName(){
		Pattern nameP = Pattern.compile("[a-zA-Z][a-z]*");
		Matcher nameM = nameP.matcher(business.getName());
		assertTrue(nameM.matches());
	}
	
	@Test
	public void testOwner(){
		Pattern nameP = Pattern.compile("[a-zA-Z][a-z]*");
		Matcher nameM = nameP.matcher(business.getOwner());
		assertTrue(nameM.matches());
	}
}
