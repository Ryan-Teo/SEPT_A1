import java.util.*;

public class Main {
	
	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);
		Account acct = new Account();
		System.out.println("--Welcome to ABC Booking system--");
		System.out.println("1 : Login");
		System.out.println("2 : Register");
		System.out.println("3 : Quit");
		String userInput = scan.nextLine();
		switch(userInput){
			case "1":
				acct.login();
				break;
			case "2":
				acct.register();
				break;
			case "3":
				System.out.println("--Bye Bye--");
				System.exit(0);
			default:
				System.out.println("Invalid Input - Please Try Again");
				break;
		
		}
	}
}
