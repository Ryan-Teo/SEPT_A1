import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Account {
	
	private ArrayList<Customer> customers = new ArrayList<Customer>();
	
	public void login(){
		//Change return type
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
			System.out.print("Please enter your desired username : ");
			username = scan.nextLine();
			for(int i=0 ; i<customers.size(); i++){
				System.out.println("Customer.get" + customers.get(i).getUsername());
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
			System.out.print("Please enter your password : ");
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
		String pattern = "\\({0,1}((0|+61)(2|4|3|7|8)){0,1}\\){0,1}(\\ |-){0,1}[0-9]{2}(\\ |-){0,1}[0-9]{2}(\\ |-){0,1}[0-9]{1}(\\ |-){0,1}[0-9]{3}";
		Pattern phonePattern = Pattern.compile(pattern);
		
		do{
			System.out.print("Please enter a valid Australian phone number : ");
			phone = scan.nextLine();
			Matcher m = phonePattern.matcher(phone);
			if (m.find( ))
				break;
		}while(1>0);//Change this to regex for aussie phone number -- remove break()					//TO DO
		
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
