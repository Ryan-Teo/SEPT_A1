public class Employee {

	private String empID;	
	private String name;
	private String employer;
	
	Employee(String empID, String name, String employer){
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
	
	public String getEmployer(){
		return employer;
	}

}
