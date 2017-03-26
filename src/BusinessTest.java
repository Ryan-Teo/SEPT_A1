import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class BusinessTest {

	
	Business business = new Business("Bob inc.", "Bobb");
	HashMap<Date,ArrayList<Booking>> schedule = new HashMap<Date,ArrayList<Booking>>();
	Date monday = new Date();
	Date tuesday = new Date();
	Date wednesday = new Date();
	String startTime = "08:30";
	String endTime = "09:30";
	
	@Test
	public void testPrintSchedule(){
		Booking session = business.addSessionTime(startTime, endTime);
		System.out.printf("%R - %R",session.getStartTime(),session.getEndTime());
//		business.addSession(monday, session);
//		
//		business.printSchedule();
	}
	
//	@Test
//	public void testInitialise() {
//		assertTrue(business.getName() instanceof String);
//		assertTrue(business.getOwner() instanceof String);
//		assertTrue(business.getEmp() instanceof ArrayList);
//		assertTrue(business.getSession() instanceof ArrayList);
//	}
//	
//	@Test
//	public void testName(){
//		Pattern nameP = Pattern.compile("[a-zA-Z][a-z]*");
//		Matcher nameM = nameP.matcher(business.getName());
//		assertTrue(nameM.matches());
//	}
//	
//	@Test
//	public void testOwner(){
//		Pattern nameP = Pattern.compile("[a-zA-Z][a-z]*");
//		Matcher nameM = nameP.matcher(business.getOwner());
//		assertTrue(nameM.matches());
//	}
}
