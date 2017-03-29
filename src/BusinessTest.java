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

	HashMap<Date,ArrayList<Booking>> schedule = new HashMap<Date,ArrayList<Booking>>();
	Business business = new Business("Bob inc.", "Bobb","iloveyou","Franklin Street","03 9924 3812",schedule);
	Employee emp_1 = new Employee("Chris","chris");
	Employee emp_2 = new Employee("John Wall", "Wall");
	Employee emp_3 = new Employee("William Stryker", "Stryker");
	ArrayList<Booking> mondaySessions = new ArrayList<Booking>();
	ArrayList<Booking> tuesdaySessions = new ArrayList<Booking>();
	
	SimpleDateFormat sdf = new SimpleDateFormat("EE");
	SimpleDateFormat dayFormat = new SimpleDateFormat("EE");
	String [] days = {"monday","tuesday","wednesday"};
	
	Date monday = new Date();
	Date tuesday = new Date();
	Date wednesday = new Date();
	String startTime = "08:30";
	String endTime = "09:30";

	@Test
	public void testPrintSchedule(){
		Date monday = null;
		Date tuesday = null;
		try {
			
			monday = sdf.parse(days[0]);
			mondaySessions = business.addSessionTime(startTime, endTime, mondaySessions,emp_1);
			mondaySessions = business.addSessionTime("10:30","12:30", mondaySessions,emp_1);
			mondaySessions = business.addSessionTime("13:30","14:30", mondaySessions,emp_2);
			mondaySessions = business.addSessionTime("15:30","16:30", mondaySessions,emp_3);
			
			tuesday = sdf.parse(days[1]);
			tuesdaySessions = business.addSessionTime(startTime, endTime, tuesdaySessions,emp_3);
			tuesdaySessions = business.addSessionTime("15:30","17:30", tuesdaySessions,emp_2);
			tuesdaySessions = business.addSessionTime("18:30","19:30", tuesdaySessions,emp_1);
			tuesdaySessions = business.addSessionTime("20:30","21:30", tuesdaySessions,emp_1);
			
			business.addSession(monday, mondaySessions);
			business.addSession(tuesday, tuesdaySessions);
			
			business.printSchedule();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
