
public class Customer extends User{
	String name, username, password, address, phone;
	Customer(String name, String username, String password, String address, String phone){
		
		super(name,username,password,address,phone);
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public String getUsername() {
		return username;
	}

	@Override
	public void viewSession(String businessName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void viewBookingSummary() {
		// TODO Auto-generated method stub
		
	}
	
	public void bookSession(){
		
	}
}
