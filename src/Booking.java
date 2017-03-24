import java.util.HashMap;

public class Booking {
private String time;
private Customer cust;
private Employee emp;
private String bName; //anton added business name

Booking(String time,Customer cust, Employee emp, Business bName){
	this.time = time;
	this.cust = cust;
	this.emp = emp;
	this.bName = bName.getName(); //get the business, and then use the business name
}

Booking(String name,String time, Employee emp, Business bName){
	this.time = time;
	this.emp = emp;
	this.bName = bName.getName();//get the business, and then use the business name
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
//anton added business name
public String getbName(){
	return bName;
}

}
