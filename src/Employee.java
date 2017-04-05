import java.io.Serializable;

public class Employee implements Serializable {

	private static final long serialVersionUID = 3L;
	private String empID;	
	private String name;
	private Business employer;
	
	Employee(String empID, String name, Business employer){
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
