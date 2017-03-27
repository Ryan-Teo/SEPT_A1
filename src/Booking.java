import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.*;

public class Booking {
private Date startTime;
private Date endTime;
private Customer cust;
private ArrayList<Employee> emp;
protected static ArrayList<Booking> bookings = new ArrayList<Booking>();
private String bookDate, bookStart, bookEnd, bookCust, bookBus, bookEmp;

//public Booking(Date startTime, Date endTime){
//	this.startTime = startTime;
//	this.endTime = endTime;
//}

public Booking(String bookDate, String bookStart, String bookEnd, String bookCust, String bookBus, String bookEmp) {
	bookDate = this.bookDate;
	bookStart = this.bookStart;
	bookEnd = this.bookEnd;
	bookCust = this.bookCust;
	bookBus = this.bookBus;
	bookEmp = this.bookEmp;
	
}

public ArrayList<Booking> getBookings() {
	return bookings;
}

public String getBookDate() {
	return bookDate;
}

public String getBookStart() {
	return bookStart;
}

public String getBookEnd() {
	return bookEnd;
}

public String getBookCust() {
	return bookCust;
}

public String getBookBus() {
	return bookBus;
}

public String getBookEmp() {
	return bookEmp;
}

public Date getStartTime() {
	return startTime;
}


public Date getEndTime() {
	return endTime;
}

public void setStartTime(String time) {
	SimpleDateFormat ft = new SimpleDateFormat("HH");
	try {
		this.startTime = ft.parse(time);
	} catch (ParseException e) {
		e.printStackTrace();
	}
}

public void setEndTime(String time) {
	SimpleDateFormat ft = new SimpleDateFormat("HH");
	try {
		this.endTime = ft.parse(time);
	} catch (ParseException e) {
		e.printStackTrace();
	}
}

public void saveBookingToFile( HashMap<Date, ArrayList<Booking>> map){
	try {
        FileOutputStream outFile = new FileOutputStream("bookings.txt");
        ObjectOutputStream out = new ObjectOutputStream(outFile);
        out.writeObject(map);
        out.close();
        outFile.close();
        System.out.printf("Booking has been saved");
     }catch(IOException i) {
        i.printStackTrace();
     }
}

public Customer getCust() {
	return cust;
}

public void setCust(Customer cust) {
	this.cust = cust;
}

public ArrayList<Employee> getEmp() {
	return emp;
}

public static void loadAcct(){
	//deal with exception here
	String bookDate, bookStart, bookEnd, bookCust, bookBus, bookEmp, line;
	Scanner sc;
	try {
		sc = new Scanner(new File("bookings.txt"));
		while (sc.hasNext()){
			line = sc.nextLine();
			StringTokenizer st = new StringTokenizer(line, "|");
			bookDate = st.nextToken();
			bookStart = st.nextToken();
			bookEnd = st.nextToken();
			bookCust = st.nextToken();
			bookBus = st.nextToken();
			bookEmp = st.nextToken();
			Booking booktep = new Booking(bookDate, bookStart, bookEnd, bookCust, bookBus, bookEmp);
			bookings.add(booktep);
		}	  
		sc.close();
	} catch (FileNotFoundException e) {
		//no existing customers, file will be created
	}
}

public static ArrayList<Booking> readBookings(String name){
	ArrayList<Booking> temp = null;
	for(int i=0 ; i<bookings.size(); i++){
		if(bookings.get(i).getBookCust().equals(name)){//BOOKING.GET.GETBOOKCUST RETURNS NULL WHYYYYYYYYYYYYYYY
				System.out.printf("%15s %20s %15s %20s %20s %20s\n", bookings.get(i).getBookDate(), 
						bookings.get(i).getBookStart(), bookings.get(i).getBookEnd(), bookings.get(i).getBookCust(),
						bookings.get(i).getBookBus(), bookings.get(i).getBookEmp() );
		}
	}
	return temp;
}
}
