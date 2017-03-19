public class Business {

	private String name;
	private String owner;
	private Employee [] emp;
	private Booking [] session;
	
	Business(String name, String owner, Employee [] emp, Booking[] session){
		this.name = name;
		this.owner = owner;
		this.emp = emp;
		this.session = session;
	}
	
	public void assignEmp(Employee emp,String time){
		for(int i=0 ; i<session.length ; i++){
			if(session[i].getTime().equals(time)){
				session[i].setEmp(emp);
			}
			else{
				System.out.println("session not found");;
			}
			
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Employee[] getEmp() {
		return emp;
	}

	public void setEmp(Employee[] emp) {
		this.emp = emp;
	}

	public Booking[] getSession() {
		return session;
	}

	public void setSession(Booking[] session) {
		this.session = session;
	}
	
}
