package system;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import scenes.SceneManager;
import users.Business;
import users.Customer;
import users.User;

public class Main extends Application{
	
//	Stage window;
	Scene mainMenu, customerRegister, registerMenu, customerMenu, scene4;
	User userInst = null;
	Account acct = new Account();
	FileIO FIO = new FileIO();
	
	Helper help = new Helper();//Remove
	ArrayList<Customer> customers = FIO.loadCust();
	ArrayList<Business> businesses = FIO.loadBus();
	LinkedHashMap<Business, LinkedHashMap<LocalDate, Booking[]>> bookings = FIO.loadBook(help, businesses); //Loading existing bookings	
	
	public static void main(String args[]) throws IOException{ //Handle exceptions
		launch(args);
	}
	
	@Override
    public void start(Stage primaryStage) {	
		
		SceneManager manager = new SceneManager(customers, businesses, acct, primaryStage);
     
    	// load main menu at the start
    	manager.showMainMenu();
    	manager.show();



		FIO.saveBook(bookings);	// Saving all bookings
		FIO.saveBus(businesses);
		FIO.saveCust(customers);
//		System.exit(0);
        

}
}
