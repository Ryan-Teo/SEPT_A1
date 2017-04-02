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
	
	public Customer getBookCust() {
		return bookCust;
	}
	
	public Business getBookBus() {
		return bookBus;
	}
	
	public Employee getBookEmp() {
		return bookEmp;
	}

	public void setEmployee(Employee emp){
		this.bookEmp = emp;
	}
	


//	Why do we need setters? 
	
//	Set start time on a particular time slot
	public void setStartTime(String time) {
		SimpleDateFormat ft = new SimpleDateFormat("HH:mm");
		try {
			this.startTime = ft.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
//	Set end time on a particular time slot
	public void setEndTime(String time) {
		SimpleDateFormat ft = new SimpleDateFormat("HH:mm");
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
	
	//How do we populate every business and customer schedule
	//Schedule is fixed 
	//need a method to update customer and business schedule due to bookings 
	public static void loadAcct(String name){
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
		ArrayList<Booking> temp = null;
		System.out.printf("%15s %20s %15s %20s %20s %20s\n", "Date", "Start", "End", "Customer", "Business", "Employee"); //length of each section may need changing
		System.out.println("---------------------------------------------------------------------------------------------");
		for(int i=0 ; i<bookings.size(); i++){
			if(bookings.get(i).getBookCust().equals(name)){//BOOKING.GET.GETBOOKCUST RETURNS NULL WHYYYYYYYYYYYYYYY
					System.out.printf("%15s %20s %15s %20s %20s %20s\n", bookings.get(i).getBookDate(), 
							bookings.get(i).getBookStart(), bookings.get(i).getBookEnd(), bookings.get(i).getBookCust(),
							bookings.get(i).getBookBus(), bookings.get(i).getBookEmp() );
			}
		}
		sc.close();
	} catch (FileNotFoundException e) {
		//no existing customers, file will be created
		}
		
		return;
	}
	
	public void setCust(Customer cust){
		this.bookCust = cust;
	}
}
