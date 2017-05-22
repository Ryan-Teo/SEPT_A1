package system;
import java.io.IOException;
import java.util.*;

import javafx.application.Application;
import javafx.stage.Stage;
import scenes.SceneManager;
import users.Business;
import users.Customer;

public class Main extends Application{
	
	Account acct = new Account();
	FileIO FIO = new FileIO();
	
	ArrayList<Customer> customers = FIO.loadCust();
	ArrayList<Business> businesses = FIO.loadBus();
	ArrayList<Booking> bookings = FIO.loadBook();
	
	public static void main(String args[]) throws IOException{ //Handle exceptions
	
		launch(args);
	}
	
	@Override
    public void start(Stage primaryStage) {	
		
		SceneManager manager = new SceneManager(customers, businesses, acct, bookings, primaryStage);

    	// load main menu at the start
    	manager.getMenus();


//		System.exit(0);
        

	}
}
