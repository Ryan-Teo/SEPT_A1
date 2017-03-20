
public class Owner extends User{
	String name, username, password, address, phone;
	Owner(String name, String username, String password, String address, String phone){
		
		super(name,username,password,address,phone);
	}
	
	@Override
	public void viewSession(String businessName) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void viewBookingSummary() {
		// TODO Auto-generated method stub
		
	}
	
	public void addEmployee(String name,String working_time){
		
	}
	
	public void addWorkingTime(String empName,String working_time){
		
	}
	
}
