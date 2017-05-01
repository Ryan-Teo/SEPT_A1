package users;
import java.io.Serializable;

public class Employee implements Serializable {

	private static final long serialVersionUID = 4L;
	private String empID;	
	private String name;
	private Business employer;
	
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

}
