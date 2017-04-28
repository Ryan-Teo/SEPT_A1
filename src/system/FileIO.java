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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.StringTokenizer;
import org.apache.log4j.Logger;
import users.Business;
import users.Customer;
import users.Employee;


public class FileIO {
	private static Logger logger = Logger.getLogger(FileIO.class);
	
	public ArrayList<Customer> loadCust(){
		ArrayList<Customer> customers = new ArrayList<Customer>(); //Use for error checking
		String line, name, username, password, address, phone;
		try {
			logger.info("Loading customer data from text file is succesful");
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
			logger.info("No Exsisting Costumers, creating dummies");
//			System.out.println("-- POPULATING CUSTOMERS --");
			customers.add(new Customer("Harry", "harryCust", "password", "utomorulz@sept.com", "0400000000"));
			customers.add(new Customer("Ryan", "ryanCust", "password", "ryan@sept.com", "0411111111"));
			customers.add(new Customer("Anton", "antonCust", "password", "anton@sept.com", "0422222222"));
			customers.add(new Customer("Julia", "juliaCust", "password", "julia@sept.com", "0433333333"));
			customers.add(new Customer("Grace", "graceCust", "password", "grace@sept.com", "0493020302"));
			//no existing customers, file will be created
		}
		return customers;
	}
	
	//Check with Ryan
	public void saveCust(ArrayList<Customer> customers){
		String name, username, password, address, phone;
		PrintWriter pw = null;
		try {
			logger.info("Saving customer data is successful");
			pw = new PrintWriter (new BufferedWriter (new FileWriter ("customers.txt")));
		} catch (IOException e) {
			//?
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
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<Business> loadBus(){
		ArrayList<Business> businesses = new ArrayList<Business>();
		String line, busName, name, username, password, address, phone;
		
		try {
			logger.info("Loading business data from text file is successful");
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
				businesses.add(new Business(busName, name, address, phone, username, password));
			}
			scBus.close();
		} catch (Exception e) {
			logger.info("No Exsisting Business, creating dummies");
			System.out.println("-- POPULATING BUSINESSES --");
			businesses.add(new Business("Sal's Hair Salon", "Harry", "1 Alumbra St", "0400000000", "harryOwner", "password"));
			businesses.add(new Business("East Medical Centre", "Ryan", "10 Car St", "0411111111", "ryanOwner", "password"));
			businesses.add(new Business("Manny's Manicures", "Anton", "100 Leianne St", "0422222222", "antonOwner", "password"));
			businesses.add(new Business("Don't Shop, Adopt", "Julia", "3 Puppy St", "0433333333", "juliaOwner", "password"));
			businesses.add(new Business("Doctor Approved", "Sally", "35 Novia St", "0444444444", "sallyOwner", "password"));
			businesses.add(new Business("Netherlands", "Nathan", "40 Oak St", "0455555555", "nathanOwner", "password"));
			businesses.add(new Business("Blinding Smiles", "Grace", "209 Yellow St", "0466666666", "graceOwner", "password"));
			businesses.add(new Business("Beds R Us", "Paulo", "245 Hunny St", "0477777777", "pauloOwner", "password"));
			businesses.add(new Business("Personally Fit", "Jayden", "90 Twin St", "0488888888", "jaydenOwner", "password"));
			//no existing businesses, file will be created
		}


		ArrayList<Employee> emps = new ArrayList<Employee>();
		try {
			logger.info("Loading Employees data");
			FileInputStream inFile = new FileInputStream("employees");
			ObjectInputStream in = new ObjectInputStream(inFile);
			emps = (ArrayList<Employee>) in.readObject();
			in.close();
			inFile.close();
		} catch (IOException e) {
			logger.info("No Exsisting Costumers, creating dummies");
			System.out.println("-No existing employees-");
		} catch (ClassNotFoundException e) {
			logger.info("No Exsisting Costumers, creating dummies");
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		
		
		return businesses;
	}
	
	public void saveBus(ArrayList<Business> businesses){
		String busName, name, username, password, address, phone;
		PrintWriter pwBus = null;
		try {
			logger.info("Saving Business data to business.txt");
			pwBus = new PrintWriter (new BufferedWriter (new FileWriter ("business.txt")));
		} catch (IOException e) {
			System.err.println("-business.txt has been created-");
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
	        FileOutputStream outFile = new FileOutputStream("employees");
	        ObjectOutputStream out = new ObjectOutputStream(outFile);
			out.reset();
			for(Business busInst : businesses){
				for(Employee emp : busInst.getEmp()){
					emps.add(emp);
				}
			}
	        out.writeObject(emps);
	        out.close();
	        outFile.close();
	     }catch(IOException e) {
	    	 System.err.println("-employees file has been created-");
	     }
	}
	

	@SuppressWarnings("unchecked")
	public LinkedHashMap<Business, LinkedHashMap<LocalDate, Booking[]>> loadBook(Helper help, ArrayList<Business> businesses){
		//deal with exception here
		//part 2 : check against businesses array, init days for new businesses
		LinkedHashMap<Business, LinkedHashMap<LocalDate, Booking[]>> bookings = new LinkedHashMap<Business, LinkedHashMap<LocalDate, Booking[]>>(); //Check for null when called
		try {
			logger.info("Load booking from bookings.txt");
			FileInputStream inFile = new FileInputStream("bookings.txt");
			ObjectInputStream in = new ObjectInputStream(inFile);
			bookings = (LinkedHashMap<Business, LinkedHashMap<LocalDate, Booking[]>>) in.readObject();			
			in.close();
			inFile.close();
		} catch (Exception e) {
			logger.info("No Exsisting booking, creating dummies");
			System.out.println(e.getMessage());
			System.out.println("-- POPULATING BOOKINGS --");
			for(Business busInst : businesses){
				bookings.put(busInst, help.initDaySlots(busInst));
			}
			//no existing customers, file will be created
		}
		return bookings;
	}
	
	public void saveBook(LinkedHashMap<Business, LinkedHashMap<LocalDate, Booking[]>> bookings){
		try {
			
			logger.info("Saving bookings to bookings.txt");
	        FileOutputStream outFile = new FileOutputStream("bookings.txt");
	        ObjectOutputStream out = new ObjectOutputStream(outFile);
			out.reset();
	        out.writeObject(bookings);
	        out.close();
	        outFile.close();
	     }catch(Exception e) {
	    	 logger.error("Unable to save bookings to bookings.txt, err :" + e.getMessage());
	     }
	}

}