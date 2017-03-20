import java.io.IOException;
import java.util.*;

public class Main {
	
	public static void main(String args[]) throws IOException{ //Handle exceptions
		Scanner scan = new Scanner(System.in);
		Account acct = new Account();
		User userInst = null;
		String userInput;
		do{
			System.out.println("--Welcome to ABC Booking system--");
			System.out.println("1 : Login");
			System.out.println("2 : Register");
			System.out.println("3 : Quit");
			System.out.print("Please enter your selection : ");
			userInput = scan.nextLine();
			switch(userInput){
				case "1":
					userInst = acct.login(scan);
					break;
				case "2":
					acct.register(scan);
					break;
				case "3":
					System.out.println("--Bye Bye--");
					break;
				default:
					System.out.println("Invalid Input - Please Try Again");
					break;
			
			}
		}while(!userInput.equals("3") && userInst == null);
		
		if(userInst instanceof Customer){
			System.out.println("-Customer Mode-");
		}
		else if (userInst instanceof Owner){
			System.out.println("-Owner Mode-");
		}
		System.out.println("Welcome, "+ userInst.getName() +"!");
		
		System.exit(0);
	}
}
