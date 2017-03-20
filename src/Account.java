import java.io.*;
import java.util.*;

public class Account {
	
	private ArrayList<Customer> customers = new ArrayList<Customer>();
	
	public Customer login(Scanner scan) throws IOException{
		//Change return type
		String username, password;
		Customer custInst = null;
		Boolean usernameCheck = false, passCheck = false;
		try{
			loadAcct();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.print("Username : ");
		username = scan.nextLine();
		System.out.print("Password : ");
		password = scan.nextLine();
		for(int i=0 ; i<customers.size(); i++){
			if(customers.get(i).getUsername().equals(username)){
				usernameCheck = true;
				if(customers.get(i).getPassword().equals(password)){
					passCheck = true;
					custInst = customers.get(i);
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
		return custInst;
	}
	
	public void register(Scanner scan) throws IOException{
		try{
			loadAcct();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		//Change return type
		//take user input, create new customer, write customer info to file/db
		Customer custInst;
		String name, username, password1, password2, address, phone;
		Boolean passcheck, usernameCheck;
		
		System.out.print("Please enter your name : ");
		name = scan.nextLine();
		
		do{
			usernameCheck = true;
			System.out.print("Please enter your desired username : "); //limit 6 - 15
			username = scan.nextLine();
			for(int i=0 ; i<customers.size(); i++){
				if(customers.get(i).getUsername().equals(username)){
					usernameCheck = false;
					System.out.println("Username Not Available - Please Try Again");
					break;
				}
			}
			//Check for uniqueness
		}while(!usernameCheck);
		
		//loop this -- check if 2 passwords are the same
		do{
			System.out.print("Please enter your password : ");		//limit 6-15
			password1 = scan.nextLine();
			System.out.print("Please re-enter your password to confirm : ");
			password2 = scan.nextLine();
			if(passcheck = !password1.equals(password2)){
				System.out.println("-- Passwords entered do not match! --");
			}
		}while(passcheck);
		//loop this
		
		System.out.print("Please enter your address : ");
		address = scan.nextLine();
		
		do{
			System.out.print("Please enter a valid Australian phone number : ");
			phone = scan.nextLine();
			break;
		}while(1>0);//Change this to regex for aussie phone number -- remove break()		//TO DO
		customers.add(new Customer(name, username, password1, address, phone));
		saveAcct();
		System.out.println("You have successfully registered!");
		System.out.println();
		try{
			Thread.sleep(1500);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	private void loadAcct() throws FileNotFoundException{
		customers.clear();
		String line, name, username, password, address, phone;
		Scanner sc = new Scanner(new File("customers.txt"));
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
	}
	
	private void saveAcct() throws IOException{
		String name, username, password, address, phone;
		PrintWriter pw = new PrintWriter (new BufferedWriter (new FileWriter ("customers.txt")));
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
	}
}
