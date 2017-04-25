package system;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import users.Business;
import users.Customer;
import users.User;

public class Account {
	
	public User login(ArrayList<Customer> customers, ArrayList<Business> businesses, String username, String password){

		User userInst = null;
		Boolean usernameCheck = false, passCheck = false;

		for(int i=0 ; i<customers.size(); i++){
			if(customers.get(i).getUsername().equals(username)){
				usernameCheck = true;
				if(customers.get(i).getPassword().equals(password)){
					passCheck = true;
					userInst = customers.get(i);
					break;
				}
			}
		}
		for(int i=0 ; i<businesses.size(); i++){
			if(businesses.get(i).getUsername().equals(username)){
				usernameCheck = true;
				if(businesses.get(i).getPassword().equals(password)){
					passCheck = true;
					userInst = businesses.get(i);
					break;
				}
			}
		}
		if(usernameCheck == false || passCheck == false){
			//Please change this to a pop up box
			System.out.println("-- Incorrect Username/Password --");
			try{
				Thread.sleep(1500);
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}

		return userInst;
	}
	
	public boolean checkLength(String name, int min, int max){
		if (name.length() < min || name.length() > max)
			return true;
		return false;
	}
	
	public boolean checkCustName(String name, ArrayList<Customer> customers){
		for(int i=0 ; i<customers.size(); i++){
			if(customers.get(i).getUsername().equals(name))
				return true;
		}
		return false;
	}
	
	public boolean checkBusName(String name, ArrayList<Business> businesses){
		for(int i=0 ; i<businesses.size(); i++){
			if(businesses.get(i).getUsername().equals(name))
				return true;
		}
		return false;
	}
	
	public boolean checkPass(String password1, String password2){
		if (password1.equals(password2))
			return true;
		else
			return false;
	}
	
	public boolean checkPhone(String phone){
		String pattern = "\\({0,1}((0|\\+61)(2|4|3|7|8)){0,1}\\){0,1}(\\ |-){0,1}[0-9]{2}(\\ |-){0,1}[0-9]{2}(\\ |-){0,1}[0-9]{1}(\\ |-){0,1}[0-9]{3}";
		Pattern phonePattern = Pattern.compile(pattern);
		Matcher m = phonePattern.matcher(phone);
		if (m.find( ))
			return true;
		return false;
	}

	public boolean registerCustomer(String name, String username, String password1, String password2, String phone, String address, ArrayList<Customer> customers){
		if(checkLength(username, 6, 12)){
			System.out.println("fail name length");
			return false;
		}
		else if(checkCustName(username, customers)){
			System.out.println("fail username check");
			return false;
		}
		else if(!checkPass(password1, password2)){
			System.out.println("fail password");
			return false;
		}
		else if(!checkPhone(phone)){
			System.out.println("fail phone");
			return false;
		}
		else{
			customers.add(new Customer(name, username, password1, address, phone));
			return true;
		}
	}

}