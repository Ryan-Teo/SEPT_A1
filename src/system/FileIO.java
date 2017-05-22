package system;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import org.apache.log4j.Logger;
import users.Business;
import users.Customer;
import users.Employee;

public class FileIO {
	Logger logger = Logger.getLogger(FileIO.class);
	
	public void save(ArrayList<Customer> customers, ArrayList<Business> businesses, ArrayList<Booking> bookings){
		saveCust(customers);
		saveBus(businesses);
		saveBook(bookings);
	}
	
	
	public ArrayList<Customer> loadCust(){
		ArrayList<Customer> customers = new ArrayList<Customer>(); //Use for error checking
		String line, name, username, password, address, phone;
		try {
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
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("-- POPULATING CUSTOMERS --");
			customers.add(new Customer("Harry", "harryCust", "password", "utomorulz@sept.com", "0400000000"));
			customers.add(new Customer("Ryan", "ryanCust", "password", "ryan@sept.com", "0411111111"));
			customers.add(new Customer("Anton", "antonCust", "password", "anton@sept.com", "0422222222"));
			customers.add(new Customer("Julia", "juliaCust", "password", "julia@sept.com", "0433333333"));
			customers.add(new Customer("Grace", "graceCust", "password", "grace@sept.com", "0493020302"));
			//no existing customers, file will be created
		}
		saveCust(customers);
		return customers;
	}
	
	public void saveCust(ArrayList<Customer> customers){
		String name, username, password, address, phone;
		PrintWriter pw = null;
		try {
			pw = new PrintWriter (new BufferedWriter (new FileWriter ("customers.txt")));
		} catch (IOException e) {
			logger.error("customer.txt has been created");
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
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Business> loadBus(){
		ArrayList<Business> businesses = new ArrayList<Business>();
		String line, busName, name, username, password, address, phone, openTime, closeTime, sessionTime;
		
		try {
			Scanner scBus = new Scanner(new File("business.txt"));
			while (scBus.hasNext()){
				line = scBus.nextLine();
				StringTokenizer st = new StringTokenizer(line, "|");
				busName = st.nextToken();
				name = st.nextToken();
				address = st.nextToken();
				phone = st.nextToken();
				username = st.nextToken();
				password = st.nextToken();
				openTime = st.nextToken();
				closeTime = st.nextToken();
				sessionTime = st.nextToken();
				
				businesses.add(new Business(busName, name, address, phone, username, password, openTime, closeTime, sessionTime));
			}
			scBus.close();
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.info("-- POPULATING BUSINESSES --");
			businesses.add(new Business("Sal's Hair Salon", "Harry", "1 Alumbra St", "0400000000", "harryOwner", "password", "09:00", "15:00", "15"));
			businesses.add(new Business("East Medical Centre", "Ryan", "10 Car St", "0411111111", "ryanOwner", "password", "09:00", "15:00", "15"));
			businesses.add(new Business("Manny's Manicures", "Anton", "100 Leianne St", "0422222222", "antonOwner", "password", "09:00", "15:00", "15"));
			businesses.add(new Business("Don't Shop, Adopt", "Julia", "3 Puppy St", "0433333333", "juliaOwner", "password", "09:00", "15:00", "15"));
			businesses.add(new Business("Doctor Approved", "Sally", "35 Novia St", "0444444444", "sallyOwner", "password", "09:00", "15:00", "15"));
			businesses.add(new Business("Netherlands", "Nathan", "40 Oak St", "0455555555", "nathanOwner", "password", "09:00", "15:00", "15"));
			businesses.add(new Business("Blinding Smiles", "Grace", "209 Yellow St", "0466666666", "graceOwner", "password", "09:00", "15:00", "15"));
			businesses.add(new Business("Beds R Us", "Paulo", "245 Hunny St", "0477777777", "pauloOwner", "password", "09:00", "15:00", "15"));
			businesses.add(new Business("Personally Fit", "Jayden", "90 Twin St", "0488888888", "jaydenOwner", "password", "09:00", "15:00", "15"));
			//no existing businesses, file will be created
		}


		ArrayList<Employee> emps = new ArrayList<Employee>();
		try {
			FileInputStream inFile = new FileInputStream("employees");
			ObjectInputStream in = new ObjectInputStream(inFile);
			emps = (ArrayList<Employee>) in.readObject();
			in.close();
			inFile.close();
		} catch (IOException e) {
			logger.warn("No existing employees");
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage());
		}
		
		for(Employee emp : emps){
			//ADD EMP TO BUSINESS
			Business myBus = emp.getEmployer();
			for(Business myBus1 : businesses){
				if(myBus.getBusName().equals(myBus1.getBusName())){
					myBus1.addEmp(emp);
				}
			}
		}
		saveBus(businesses);
		
		return businesses;
	}
	
	public void saveBus(ArrayList<Business> businesses){
		String busName, name, username, password, address, phone;
		PrintWriter pwBus = null;
		try {
			logger.info("Business has been saved successfully into business.txt");
			pwBus = new PrintWriter (new BufferedWriter (new FileWriter ("business.txt")));
		} catch (IOException e) {
			logger.warn("business.txt has been created");
		}
		
		for (int i=0; i<businesses.size(); i++){
			Business business = businesses.get(i);			
			busName = business.getBusName();
			name = business.getName();
			address = business.getAddress();
			phone = business.getPhone();
			username = business.getUsername();
			password = business.getPassword();
			pwBus.printf("%s|%s|%s|%s|%s|%s\n", busName, name, address, phone, username, password);			
		}
		pwBus.close();
		ArrayList<Employee> emps = new ArrayList<Employee>();
		//Saving employee arraylist for each business
		try {
			logger.info("Employees has been successfully saved into 'employees' file");
	        FileOutputStream outFile = new FileOutputStream("employees");
	        ObjectOutputStream out = new ObjectOutputStream(outFile);
			out.reset();
			for(Business busInst : businesses){
				for(Employee emp : busInst.getEmps()){
					emps.add(emp);
				}
			}
	        out.writeObject(emps);
	        out.close();
	        outFile.close();
	     }catch(IOException e) {
	    	 logger.warn("-employees file has been created-");
	     }
	}
	

	@SuppressWarnings("unchecked")
	public ArrayList<Booking> loadBook(){
		//deal with exception here
		//part 2 : check against businesses array, init days for new businesses (defunct)
		ArrayList<Booking> bookings = new ArrayList<Booking>(); //Check for null when called
		try {
			logger.info("bookings has been loaded successfully");
			FileInputStream inFile = new FileInputStream("bookings");
			ObjectInputStream in = new ObjectInputStream(inFile);
			bookings = (ArrayList<Booking>) in.readObject();			
			in.close();
			inFile.close();
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.warn("-- NO BOOKINGS EXIST --");
		}
		
		saveBook(bookings);
		return bookings;
	}
	
	public void saveBook(ArrayList<Booking> bookings){
		try {
			logger.info("Bookings have been saved successfully");
	        FileOutputStream outFile = new FileOutputStream("bookings");
	        ObjectOutputStream out = new ObjectOutputStream(outFile);
			out.reset();
	        out.writeObject(bookings);
	        out.close();
	        outFile.close();
	     }catch(Exception e) {
	    	 logger.error(e.getMessage());
	    	 e.printStackTrace();
	     }
	}
}