import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class Main {
	
	public static void main(String args[]) throws IOException{ //Handle exceptions
		Scanner scan = new Scanner(System.in);
		FileIO FIO = new FileIO();
		Account acct = new Account();
		Helper help = new Helper();//Remove
		ArrayList<Customer> customers = FIO.loadCust();
		ArrayList<Business> businesses = FIO.loadBus();
		LinkedHashMap<Business, LinkedHashMap<LocalDate, Booking[]>> bookings = FIO.loadBook(help, businesses); //Loading existing bookings	
		User userInst = null;
		String userInput;
		
		do{
			/*
			 * MAIN MENU
			 */
			System.out.println("--Welcome to ABC Booking system--");
			System.out.println("1 : Login");
			System.out.println("2 : Register");
			System.out.println("0 : Quit");
			System.out.print("Please enter your selection : ");
			userInput = scan.nextLine();
			System.out.println("########################");
			switch(userInput){
				case "1":
					userInst = acct.login(scan, customers, businesses);
					break;
				case "2":
					acct.register(scan, customers, businesses);
					break;
				case "0":
					System.out.println("--Bye Bye--");
					break;
				default:
					System.out.println("Invalid Input - Please Try Again");
					break;
			}
			
			
			/*
			 * CUSTOMER MENU BELOW
			 */
			if(userInst instanceof Customer){
				System.out.println("-Customer Mode-");
				Customer custInst = (Customer)userInst;
				boolean exit = false;
				do{
					custInst.customerMenu();
					userInput = scan.nextLine();
					switch(userInput){
						case "1"://add booking
							custInst.makeBooking(bookings, businesses, scan);
							break;
						case "2"://view current bookings
							custInst.viewBookingSummary(bookings);
							break;
						case "3"://view sessions of a business
							custInst.viewSession(bookings, businesses, scan);
							break;
						case "4"://Cancel Booking
							custInst.cancelBooking(bookings, scan);
							break;
						case "9"://log out
							System.out.println("-- Logging Out --");
							userInst = null;
							exit = true;
							break;
						case "0":
							//customer log out and other log out stuff
							System.out.println("-Logging Out & Exiting-");
							userInst = null;
							exit = true;
							break;
						default:
							System.out.println("Invalid Input - Please Try Again");
							break;
					}
					System.out.println("#######################");
				}while(exit == false);
				try{
					Thread.sleep(1500);
				}catch(Exception e){
					System.out.println(e.getMessage());
				}
			}
			
			
			/*
			 * BUSINESS MENU BELOW
			 */
			else if (userInst instanceof Business){
				System.out.println("-Owner Mode-");
				
				boolean exit = false;
				do{
					Business busInst = (Business)userInst;
					busInst.businessMenu();
					userInput = scan.nextLine();
					switch(userInput){
						case "1": //Add employee
							busInst.addNewEmployee(scan);
							busInst.viewEmployees();
							break;
						case "2": //Add working Time & Dates
							busInst.addWorkingTime(bookings, scan);
							break;
						case "3": //View booking summary
							busInst.viewBookingSummary(bookings);
							break;
						case "4": //Make new booking
							busInst.makeBooking();
							break;
						case "5":
							busInst.showWorkerAvailability();
							break;
						case "0": //log off
							System.out.println("-Logging Out & Exiting-");
							userInst = null;
							exit = true;
							break;
					}
				}while (exit == false);
			}
		
			
		}while(!userInput.equals("0") && userInst == null);
		
		FIO.saveBook(bookings);	// Saving all bookings
		FIO.saveBus(businesses);
		FIO.saveCust(customers);
		System.exit(0);
	}
}
