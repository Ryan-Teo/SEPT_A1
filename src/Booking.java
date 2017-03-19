import java.util.HashMap;

public class Booking {
private String time;
private Customer cust;
private Employee emp;

Booking(String time,Customer cust, Employee emp){
	this.time = time;
	this.cust = cust;
	this.emp = emp;
}

Booking(String name,String time, Employee emp){
	this.time = time;
	this.emp = emp;
}

public String getTime() {
	return time;
}

public void setTime(String time) {
	this.time = time;
}

public Customer getCust() {
	return cust;
}

public void setCust(Customer cust) {
	this.cust = cust;
}

public Employee getEmp() {
	return emp;
}

public void setEmp(Employee emp) {
	this.emp = emp;
}


}
