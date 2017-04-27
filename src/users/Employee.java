package users;
import java.io.Serializable;
import java.util.*;
import java.time.LocalDate;

public class Employee implements Serializable {

	private static final long serialVersionUID = 4L;
	private String empID;	
	private String name;
	private Business employer;
	private HashMap<LocalDate, ArrayList<Boolean>> schedule;
	
	public Employee(String empID, String name, Business employer){
		this.empID = empID;
		this.name = name;
		this.employer = employer;
	}
	
	public String getEmpID() {
		return empID;
	}
	
	public String getName() {
		return name;
	}
	
	public Business getEmployer(){
		return employer;
	}
	
	public HashMap<LocalDate, ArrayList<Boolean>> getSchedule(){
		//Init enough slots for each employee working time
		return schedule;
	}

}
