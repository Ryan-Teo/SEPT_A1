import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Account {
	
	private ArrayList<Customer> customers = new ArrayList<Customer>();
	private ArrayList<Owner> owners = new ArrayList<Owner>();
	//merge to one arraylist?
	
	public User login(Scanner scan){
		//Make usable for owner as well
		//Change return type include owner
		String username, password;
		User userInst = null;
		Boolean usernameCheck = false, passCheck = false;
		loadAcct();
		System.out.print("Username : ");
		username = scan.nextLine();
		System.out.print("Password : ");
		password = scan.nextLine();
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
		for(int i=0 ; i<owners.size(); i++){
			if(owners.get(i).getUsername().equals(username)){
				usernameCheck = true;
				if(owners.get(i).getPassword().equals(password)){
					passCheck = true;
					userInst = owners.get(i);
					break;
				}
			}
		}
		if(usernameCheck == false || passCheck == false){
			System.out.println("-- Incorrect Username/Password --");
			try{
				Thread.sleep(1500);
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
		//check user exists & password is correct
		saveAcct();
		System.out.println();
		return userInst;
	}
	
	public void register(Scanner scan){
		loadAcct();
		//Change return type
		//take user input, create new customer, write customer info to file/db
		String name, username, password1, password2, address, phone, userInput, userType = null;
		Boolean passcheck, usernameCheck;
		System.out.println("--Who are you?--");
		System.out.printf("1 : Customer\n2 : Owner\n3 : I'm not sure where I am!\n");
		System.out.printf("Please enter your selection : ");
		userInput = scan.nextLine();
		switch(userInput){
			case "1":
				userType = "cust";
				break;
			case "2":
				userType = "owner";
				break;
			case "3":
				return;
			default:
				System.out.println("Invalid Input - Please Try Again");
				break;
		}
		System.out.print("Please enter your name : ");
		name = scan.nextLine();
		
		System.out.print("Please enter your desired username with 6-12 characters: ");
		do{
			usernameCheck = true;
			username = scan.nextLine();
			if (username.length() < 6 || username.length() > 13){
				System.out.println("-- Please enter a username with 6-12 characters. --");
			}
			else{
				for(int i=0 ; i<customers.size(); i++){
					if(customers.get(i).getUsername().equals(username)){
						usernameCheck = false;
						System.out.println("-- Username Not Available - Please Try Again-- ");
					}
					else
						break;
				}
				break;
			}
			//Check for uniqueness
		}while(usernameCheck);
		//loop this -- check if 2 passwords are the same
		
		System.out.print("Please enter your password with 6-12 characters : ");		//limit 6-15
		do{
			password1 = scan.nextLine();
			if (password1.length() < 6 || password1.length() > 13)
				System.out.println("-- Please enter a password with 6-12 characters! --");
			else{ 
				System.out.print("Please re-enter your password to confirm : ");
				password2 = scan.nextLine();
				if(passcheck = !password1.equals(password2)){
					System.out.println("-- Passwords entered do not match! --");
					System.out.println("-- Press Enter and try again. --");
				}
				else 
					break;
			}
			
		}while(1>0);
		//loop this
		
		System.out.print("Please enter your address : ");
		address = scan.nextLine();
		String pattern = "\\({0,1}((0|\\+61)(2|4|3|7|8)){0,1}\\){0,1}(\\ |-){0,1}[0-9]{2}(\\ |-){0,1}[0-9]{2}(\\ |-){0,1}[0-9]{1}(\\ |-){0,1}[0-9]{3}";
		Pattern phonePattern = Pattern.compile(pattern);
		
		System.out.print("Please enter a valid Australian phone number : ");
		do{
			
			phone = scan.nextLine();
			Matcher m = phonePattern.matcher(phone);
			if (m.find( ))
				break;
			else 
				System.out.println("--Invalid Australian phone number. 2--");
		}while(1>0);

		if(userType.equals("cust")){
			customers.add(new Customer(name, username, password1, address, phone));
		}
		else if (userType.equals("owner")){
			owners.add(new Owner(name, username, password1, address, phone));
		}
		saveAcct();
		System.out.println("You have successfully registered!");
		System.out.println();
		try{
			Thread.sleep(1500);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	private void loadAcct(){
		//deal with exception here
		customers.clear();
		owners.clear();
		String line, name, username, password, address, phone;
		Scanner sc;
		try {
			sc = new Scanner(new File("customers.txt"));
			while (sc.hasNext()){
				line = sc.nextLine();
				StringTokenizer st = new StringTokenizer(line, "|");
				name = st.nextToken();
				username = st.nextToken();
				password = st.nextToken();
				address = st.nextToken();
				phone = st.nextToken();
				customers.add(new Customer(name, username, password, address, phone));
			}	
			sc.close();
		} catch (FileNotFoundException e) {
			//no existing customers, file will be created
		}
		
		Scanner scOwner;
		try {
			scOwner = new Scanner(new File("owners.txt"));
			while (scOwner.hasNext()){
				line = scOwner.nextLine();
				StringTokenizer st = new StringTokenizer(line, "|");
				name = st.nextToken();
				username = st.nextToken();
				password = st.nextToken();
				address = st.nextToken();
				phone = st.nextToken();
				owners.add(new Owner(name, username, password, address, phone));
			}
			scOwner.close();
		} catch (FileNotFoundException e) {
			//no existing owners, file will be created
		}	
	}
	
	private void saveAcct(){
		//add functionality for owners
		//deal with exception here
		String name, username, password, address, phone;
		PrintWriter pw = null;
		try {
			pw = new PrintWriter (new BufferedWriter (new FileWriter ("customers.txt")));
		} catch (IOException e) {
			System.err.println("-customers.txt has been created-");
		}
		for (int i=0; i<customers.size(); i++){
			Customer customer = customers.get(i);			
			name = customer.getName();
			username = customer.getUsername();
			password = customer.getPassword();
			address = customer.getAddress();
			phone = customer.getPhone();
			pw.printf("%s|%s|%s|%s|%s\n", name, username, password, address, phone);			
		}
		pw.close();
		customers.clear();
		PrintWriter pwOwner = null;
		try {
			pwOwner = new PrintWriter (new BufferedWriter (new FileWriter ("owners.txt")));
		} catch (IOException e) {
			System.err.println("-owners.txt has been created-");
		}
		for (int i=0; i<owners.size(); i++){
			Owner owner = owners.get(i);			
			name = owner.getName();
			username = owner.getUsername();
			password = owner.getPassword();
			address = owner.getAddress();
			phone = owner.getPhone();
			pwOwner.printf("%s|%s|%s|%s|%s\n", name, username, password, address, phone);			
		}
		pwOwner.close();
		owners.clear();
	}
}
