import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.*;

public class Booking {
	private Date bookDate;
	private Date startTime;
	private Date endTime;
	private ArrayList<Booking> bookings = new ArrayList<Booking>();
	private Customer bookCust;
	private Business bookBus;
	private Employee bookEmp;

public Booking(Date bookDate, Date bookStart, Date bookEnd, Customer bookCust, Business bookBus, Employee bookEmp){
	bookDate = this.bookDate;
	bookStart = this.startTime;
	bookEnd = this.endTime;
	bookCust = this.bookCust;
	bookBus = this.bookBus;
	bookEmp = this.bookEmp;
}

public Date getBookDate() {
	return bookDate;
}

public Date getStartTime() {
	return startTime;
}

public Date getEndTime() {
	return endTime;
}

public ArrayList<Booking> getBookings() {
	return bookings;
}

public Customer getBookCust() {
	return bookCust;
}

public Business getBookBus() {
	return bookBus;
}

public Employee getBookEmp() {
	return bookEmp;
}

//Why do we need setters? 

//public void setStartTime(String time) {
//	SimpleDateFormat ft = new SimpleDateFormat("HH");
//	try {
//		this.startTime = ft.parse(time);
//	} catch (ParseException e) {
//		e.printStackTrace();
//	}
//}
//
//public void setEndTime(String time) {
//	SimpleDateFormat ft = new SimpleDateFormat("HH");
//	try {
//		this.endTime = ft.parse(time);
//	} catch (ParseException e) {
//		e.printStackTrace();
//	}
//}

}
