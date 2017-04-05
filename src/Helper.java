import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;


public class Helper{

	//To be removed
	public LinkedHashMap<LocalDate, ArrayList<Booking>> asLinkedLinkedHashMap(ArrayList<Booking> bookings){
		LinkedHashMap<LocalDate, ArrayList<Booking>> schedule = new LinkedHashMap<LocalDate, ArrayList<Booking>>();
		LocalDate bookDate;
		for(Booking bookInst : bookings){
			bookDate = bookInst.getBookDate();
			if(!schedule.containsKey(bookDate)){
				//put date and init array list
				schedule.put(bookDate, new ArrayList<Booking>());
			}
			//if date already exists or bookDate has been init
			schedule.get(bookDate).add(bookInst);
		}
		return schedule;
	}
	
	public Booking[] initTimeSlots(LocalDate myDate,Business myBusiness){
		Booking[] bookings = new Booking[16];
		Employee emp = new Employee("emp001","Juls",myBusiness);
		Customer cust = null;
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
	
}
