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
import java.util.Set;
import java.util.StringTokenizer;

public class FileIO {
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
			//no existing customers, file will be created
		}
		return customers;
	}
	
	public void saveCust(ArrayList<Customer> customers){
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
	}
	
	public ArrayList<Business> loadBus(){
		ArrayList<Business> businesses = new ArrayList<Business>();
		String line, busName, name, username, password, address, phone;
		
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
				businesses.add(new Business(busName, name, address, phone, username, password));
			}
			scBus.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			//no existing businesses, file will be created
		}
		return businesses;
	}
	
	public void saveBus(ArrayList<Business> businesses){
		String busName, name, username, password, address, phone;
		PrintWriter pwBus = null;
		try {
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
	}
	

	public LinkedHashMap<Business, LinkedHashMap<LocalDate, Booking[]>> loadBook(Helper help, ArrayList<Business> businesses){
		//deal with exception here
		//part 2 : check against businesses array, init days for new businesses
		LinkedHashMap<Business, LinkedHashMap<LocalDate, Booking[]>> bookings = new LinkedHashMap<Business, LinkedHashMap<LocalDate, Booking[]>>(); //Check for null when called
		try {
			FileInputStream inFile = new FileInputStream("bookings.txt");
			ObjectInputStream in = new ObjectInputStream(inFile);
			bookings = (LinkedHashMap<Business, LinkedHashMap<LocalDate, Booking[]>>) in.readObject();			
			in.close();
			inFile.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("-- POPULATING DATA --");
			for(Business busInst : businesses){
				bookings.put(busInst, help.initDaySlots(busInst));
			}
			//no existing customers, file will be created
		}
		return bookings;
	}
	
	public void saveBook(LinkedHashMap<Business, LinkedHashMap<LocalDate, Booking[]>> bookings){
		try {
	        FileOutputStream outFile = new FileOutputStream("bookings.txt");
	        ObjectOutputStream out = new ObjectOutputStream(outFile);
			out.reset();
	        out.writeObject(bookings);
	        out.close();
	        outFile.close();
	     }catch(Exception e) {
	    	 System.out.println(e.getMessage());
	    	 e.printStackTrace();
	     }
	}

	
//WHAT IS THIS?
//	public static <T> T parseObjectFromString(String s, Class<T> clazz) throws Exception {
//	    return clazz.getConstructor(new Class[] {String.class }).newInstance(s);
//	}

}
