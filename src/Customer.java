import java.util.Hashtable;

public class Customer extends User{ 
	Customer(String name, String username, String password, String address, String phone){
		super(name,username,password,address,phone);
	}

	@Override
	public void viewSession(Business business){
		business.printSchedule();
	}

	@Override
	public void viewBookingSummary() {
		// TODO Auto-generated method stub
		
	}
	
	public void bookSession(){
		
	}
}
