import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Helper {
	
	public HashMap<Date, ArrayList<Booking>> asHashmap(ArrayList<Booking> bookings){
		HashMap<Date, ArrayList<Booking>> schedule = new HashMap<Date, ArrayList<Booking>>();
		Date bookDate;
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
	
}
