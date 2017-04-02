import java.io.IOException;
import java.util.*;

public class Main {
	
	public static void main(String args[]) throws IOException{ //Handle exceptions
		Scanner scan = new Scanner(System.in);
		FileIO FIO = new FileIO();
		Account acct = new Account();
		Helper help = new Helper();
		ArrayList<Booking> bookings = FIO.loadBook(); //Loading existing bookings
		ArrayList<Customer> customers = FIO.loadCust();
		ArrayList<Business> businesses = FIO.loadBus();
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
							
							break;
						case "2"://view current bookings
							
							custInst.viewBookingSummary();
							break;
						case "3"://view sessions of a business
							System.out.println("Please select a business to search up: ");
							String bussName = scan.nextLine();
//							custInst.viewSession(bussName, businesses);
							break;
						case "4"://logout
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
				System.out.println("Welcome, "+ userInst.getName() +"!");
			}
		
			
		}while(!userInput.equals("0") && userInst == null);
		
		FIO.saveBook(bookings);	// Saving all bookings
		FIO.saveBus(businesses);
		FIO.saveCust(customers);
		System.exit(0);
	}
}
