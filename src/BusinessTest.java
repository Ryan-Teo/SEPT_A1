import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class BusinessTest {

	//Dummy schedule and Business
	HashMap<Date,ArrayList<Booking>> schedule = new HashMap<Date,ArrayList<Booking>>();
	Business business = new Business("Bob inc.", "Bobb","iloveyou","Franklin Street","03 9924 3812","   sas");
	
	//Dummy Employees
	Employee emp_1 = new Employee("Chris","chris");
	Employee emp_2 = new Employee("John Wall", "Wall");
	Employee emp_3 = new Employee("William Stryker", "Stryker");
	
	//Dummy sessions
	ArrayList<Booking> mondaySessions = new ArrayList<Booking>();
	ArrayList<Booking> tuesdaySessions = new ArrayList<Booking>();
	
	//Date format for Day
	SimpleDateFormat sdf = new SimpleDateFormat("EE");
	String [] days = {"monday","tuesday","wednesday"};
	
	Date monday = new Date();
	Date tuesday = new Date();
	Date wednesday = new Date();
	
	String startTime = "08:30";
	String endTime = "09:30";

	@Test
	public void testPrintSchedule(){
		
		business.setSchedule(schedule);
		mondaySessions = business.addSessionTime(startTime, endTime, mondaySessions,emp_1);
		mondaySessions = business.addSessionTime("10:30","12:30", mondaySessions,emp_1);
		mondaySessions = business.addSessionTime("13:30","14:30", mondaySessions,emp_2);
		mondaySessions = business.addSessionTime("15:30","16:30", mondaySessions,emp_3);
		
		tuesdaySessions = business.addSessionTime(startTime, endTime, tuesdaySessions,emp_3);
		tuesdaySessions = business.addSessionTime("15:30","17:30", tuesdaySessions,emp_2);
		tuesdaySessions = business.addSessionTime("18:30","19:30", tuesdaySessions,emp_1);
		tuesdaySessions = business.addSessionTime("20:30","21:30", tuesdaySessions,emp_1);
		
		business.addSession("monday", mondaySessions);
		business.addSession("tuesday", tuesdaySessions);
		
		business.printSchedule();
	}
	
	@Test
	public void testInitialise() {
		//Name of the business
		assertTrue(business.getBusName() instanceof String);
		
		//Name of the Owner
		assertTrue(business.getOwnerName() instanceof String);
		
		//List of Employees
		assertTrue(business.getEmp() instanceof ArrayList);
		
		//Working schedule of the business
		assertTrue(business.getSchedule() instanceof HashMap);
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
