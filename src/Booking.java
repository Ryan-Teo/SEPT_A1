import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Booking {
private Date startTime;
private Date endTime;
private Customer cust;
private ArrayList<Employee> emp;


public Booking(Date startTime, Date endTime){
	this.startTime = startTime;
	this.endTime = endTime;
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

public Customer getCust() {
	return cust;
}

public void setCust(Customer cust) {
	this.cust = cust;
}

public ArrayList<Employee> getEmp() {
	return emp;
}

}
