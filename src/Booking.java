import java.util.HashMap;

public class Booking {
private String business_name;
private String time;
private Customer cust;
private Employee emp;

Booking(String name,String time,Customer cust, Employee emp){
	this.business_name = name;
	this.time = time;
	this.cust = cust;
	this.emp = emp;
}

public String getBusiness_name() {
	return business_name;
}

public void setBusiness_name(String business_name) {
	this.business_name = business_name;
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
