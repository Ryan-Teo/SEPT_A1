package system;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import users.Business;
import users.Customer;
import users.User;

public class Account {
	
	Logger logger = Logger.getLogger(Account.class);
	
	FileIO FIO = new FileIO();
	
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
			logger.error("Incorrect Username/Password");
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
		if(name != null){
			for(int i=0 ; i<customers.size(); i++){
				if(customers.get(i).getUsername().equals(name))
					return true;
			}
		}
		return false;
	}
	
	public boolean checkBusName(String name, ArrayList<Business> businesses){
		if(name != null){
			for(int i=0 ; i<businesses.size(); i++){
				if(businesses.get(i).getUsername().equals(name))
					return true;
			}
		}
		return false;
	}
	
	public boolean checkBusBusName(String busName, ArrayList<Business> businesses){
		for(int i=0 ; i<businesses.size(); i++){
			if(businesses.get(i).getBusName().equals(busName))
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
	public boolean registerBusiness(String name, String username, String busName, String password1, String password2, String phone, String address, ArrayList<Business> businesses){
		if(checkLength(username, 6, 12)){
			logger.error("fail name length");
			return false;
		}
		else if(checkBusName(username, businesses)){
			logger.error("fail username check");
			return false;
		}
		else if (checkBusBusName(busName, businesses)){
			logger.error("fail business name");
			return false;
		}
		else if(!checkPass(password1, password2)){
			logger.error("fail password");
			return false;
		}
		else if(!checkPhone(phone)){
			logger.error("fail phone");
			return false;
		}
		else{
			logger.info("Business registration is successful");
			businesses.add(new Business(busName, name, address, phone, username, password1));
			FIO.saveBus(businesses);
			FIO.saveBus(businesses);
			return true;
		}
	}
	public boolean registerCustomer(String name, String username, String password1, String password2, String phone, String address, ArrayList<Customer> customers){
		if(checkLength(username, 6, 12)){
			logger.error("fail name length");
			return false;
		}
		else if(checkCustName(username, customers)){
			logger.error("fail username check");
			return false;
		}
		else if(!checkPass(password1, password2)){
			logger.error("fail password");
			return false;
		}
		else if(!checkPhone(phone)){
			logger.error("fail phone");
			return false;
		}
		else{
			logger.info("Customer registration is successful");
			customers.add(new Customer(name, username, password1, address, phone));
			FIO.saveCust(customers);
			return true;
		}
	}

}
