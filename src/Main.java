import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class Main extends Application{
	
	Stage window;
	Scene scene1, scene2, scene2a, scene3, scene4;
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
		
		Scanner scan = new Scanner(System.in);
		
		
		
//		User userInst = null;
		String userInput;
		
		
// SIGN IN SCREEN		
		window = primaryStage;
    	window.setTitle("Press The Button");
        
    	GridPane grid = new GridPane();
    	grid.setPadding(new Insets(30, 30, 30, 30));
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
       
        Text header = new Text("Welcome to Press The Button");
        header.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
        grid.add(header, 0, 1, 2, 1);

        Label userName = new Label("User Name:");
        grid.add(userName, 0, 2);

        TextField userNameInput = new TextField();
        userNameInput.setPromptText("username");
        grid.add(userNameInput, 1, 2);
        

        Label password = new Label("Password:");
        grid.add(password, 0, 3);

        PasswordField passwordInput = new PasswordField();
        passwordInput.setPromptText("password");
        grid.add(passwordInput, 1, 3);
        

        
        Button signInButton = new Button("Sign in");
        HBox hbsignInButton = new HBox(10);
        hbsignInButton.setAlignment(Pos.BOTTOM_RIGHT);
        signInButton.setMinWidth(100);
        signInButton.setMinHeight(50);
        signInButton.setStyle("-fx-font: 22 arial; -fx-base: #000555;");
        hbsignInButton.getChildren().add(signInButton);
        grid.add(hbsignInButton, 1, 5);
        
        signInButton.setOnAction(e -> {
        	String userNameString = userNameInput.getText();
        	String passwordString = passwordInput.getText();
        	mainLogIn(customers, businesses, userNameString, passwordString);
        });

        Button registerButton = new Button("Click Here to Register");
        HBox hbRegisterButton = new HBox(10);
        hbRegisterButton.setAlignment(Pos.TOP_RIGHT);
        registerButton.setMinWidth(100);
        registerButton.setMinHeight(25);
        registerButton.setStyle("-fx-font: 10 arial; -fx-base: #009900;");
        hbRegisterButton.getChildren().add(registerButton);
        grid.add(hbRegisterButton, 1, 0);
        registerButton.setOnAction(e -> window.setScene(scene2a));
        scene1 = new Scene(grid, 600, 250);

// REGISTER SCREEN        
        
        GridPane grid2a = new GridPane();
        grid2a.setPadding(new Insets(30, 30, 30, 30));
        grid2a.setAlignment(Pos.CENTER);
        grid2a.setHgap(10);
        grid2a.setVgap(10);
        
        Text registerheader = new Text("Are you registering as:");
        registerheader.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
        grid2a.add(registerheader, 0, 1, 1, 1);
        
        Button custInstButton = new Button("Customer");
        HBox hbcustInstButton = new HBox(10);
        custInstButton.setMinWidth(100);
        custInstButton.setMinHeight(50);
        custInstButton.setStyle("-fx-font: 22 arial; -fx-base: #000555;");
        hbcustInstButton.getChildren().add(custInstButton);
        grid2a.add(hbcustInstButton, 0, 2);
        custInstButton.setOnAction(e -> window.setScene(scene2));
        
        Button ownerInstButton = new Button("Business");
        HBox hbOwnerInstButton = new HBox(10);
        ownerInstButton.setMinWidth(100);
        ownerInstButton.setMinHeight(50);
        ownerInstButton.setStyle("-fx-font: 22 arial; -fx-base: #000555;");
        hbOwnerInstButton.getChildren().add(ownerInstButton);
        grid2a.add(hbOwnerInstButton, 1, 2);
        ownerInstButton.setOnAction(e -> window.setScene(scene1));
        
        
        scene2a = new Scene(grid2a, 600, 250);
        
        
        
        
        
        
        
        
        
        
        
        GridPane grid2 = new GridPane();
    	grid2.setPadding(new Insets(30, 30, 30, 30));
    	grid2.setAlignment(Pos.CENTER);
    	grid2.setHgap(10);
    	grid2.setVgap(10);
    	
        
        Text registerTitle = new Text("Register Here:");
        registerTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
        grid2.add(registerTitle, 0, 1, 2, 1);
        
        Label fullName = new Label("Full Name: ");
        grid2.add(fullName, 0, 2);

        TextField fullNameText = new TextField();
        fullNameText.setPromptText("legal name");
        grid2.add(fullNameText, 1, 2);

        Label newUserName = new Label("User Name:");
        grid2.add(newUserName, 0, 3);

        TextField newUserNameInput = new TextField();
        newUserNameInput.setPromptText("username");
        grid2.add(newUserNameInput, 1, 3);

        Label newPassword = new Label("Password:");
        grid2.add(newPassword, 0, 4);

        PasswordField newPasswordInput = new PasswordField();
        newPasswordInput.setPromptText("password");
        grid2.add(newPasswordInput, 1, 4);
        
        Label newPassword2 = new Label("Re-Enter Password:");
        grid2.add(newPassword2, 0, 5);

        PasswordField newPasswordInput2 = new PasswordField();
        newPasswordInput2.setPromptText("password");
        grid2.add(newPasswordInput2, 1, 5);
        
        Label phone = new Label("Contact Number ");
        grid2.add(phone, 0, 6);

        TextField phoneText = new TextField();
        phoneText.setPromptText("phone number");
        grid2.add(phoneText, 1, 6);
        
        Label address = new Label("Contact Number ");
        grid2.add(address, 0, 7);

        TextField addressNew = new TextField();
        addressNew.setPromptText("phone number");
        grid2.add(addressNew, 1, 7);
        
        Button register2 = new Button("Register!");
        HBox hbRegister2 = new HBox(10);
        hbRegister2.setAlignment(Pos.BOTTOM_RIGHT);
        register2.setMinWidth(80);
        register2.setMinHeight(50);
        register2.setStyle("-fx-font: 22 arial; -fx-base: #009900;");
        hbRegister2.getChildren().add(register2);
        register2.setOnAction(e -> acct.register(scan, customers, businesses));
        grid2.add(hbRegister2, 1, 8);
        
        register2.setOnAction(e -> {
        	String fullNameString = fullNameText.getText();
        	String newUserNameString = newUserNameInput.getText();
        	String newPasswordString = newPasswordInput.getText();
        	String newPasswordString2 = newPasswordInput2.getText();
        	String phoneString = phoneText.getText();
        	String addressString = addressNew.getText();
//        	mainRegister(customers, businesses, fullNameString, newUserNameString, newPasswordString, newPasswordString2, phoneString, addressString);
        });
        
        
        
        
        
        
        
        Button back = new Button("Back");
        HBox hbBack = new HBox(10);
        hbBack.setAlignment(Pos.TOP_RIGHT);
        back.setMinWidth(50);
        back.setMinHeight(25);
        back.setStyle("-fx-font: 10 arial; -fx-base: #000555;");
        hbBack.getChildren().add(back);
        grid2.add(hbBack, 1, 0);
        back.setOnAction(e -> window.setScene(scene1));
        
        scene2 = new Scene(grid2, 200, 400);
        
        GridPane grid3 = new GridPane();
    	grid3.setPadding(new Insets(30, 30, 30, 30));
    	grid3.setAlignment(Pos.CENTER);
    	grid3.setHgap(10);
    	grid3.setVgap(10);
        
    	Text custTitle = new Text("Welcome --INSERT NAME HERE--");
    	custTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
        grid3.add(custTitle, 0, 1, 2, 1);
        
        Button custAdd = new Button("Add Booking");
        HBox hbCustAdd = new HBox(10);
        hbCustAdd.setAlignment(Pos.TOP_RIGHT);
        custAdd.setMinWidth(50);
        custAdd.setMinHeight(25);
        custAdd.setStyle("-fx-font: 10 arial; -fx-base: #000555;");
        hbCustAdd.getChildren().add(custAdd);
        grid3.add(hbCustAdd, 1, 0);
        
    
        Button custCurrent = new Button("View Current Bookings");
        HBox hbCustCurrent = new HBox(10);
        hbCustCurrent.setAlignment(Pos.TOP_RIGHT);
        custCurrent.setMinWidth(50);
        custCurrent.setMinHeight(25);
        custCurrent.setStyle("-fx-font: 10 arial; -fx-base: #000555;");
        hbCustCurrent.getChildren().add(custCurrent);
        grid3.add(hbCustCurrent, 2, 0);
        
        Button custSession = new Button("View Available Sessions");
        HBox hbCustSession = new HBox(10);
        hbCustSession.setAlignment(Pos.TOP_RIGHT);
        custSession.setMinWidth(50);
        custSession.setMinHeight(25);
        custSession.setStyle("-fx-font: 10 arial; -fx-base: #000555;");
        hbCustSession.getChildren().add(custSession);
        grid3.add(hbCustSession, 3, 0);
        
        
        Button custCancel = new Button("Cancel Bookings");
        HBox hbCustCancel = new HBox(10);
        hbCustCancel.setAlignment(Pos.TOP_RIGHT);
        custCancel.setMinWidth(50);
        custCancel.setMinHeight(25);
        custCancel.setStyle("-fx-font: 10 arial; -fx-base: #000555;");
        hbCustCancel.getChildren().add(custCancel);
        grid3.add(hbCustCancel, 4, 0);
        
        Button custLogOut = new Button("Logout");
        HBox hbCustLogOut = new HBox(10);
        hbCustLogOut.setAlignment(Pos.TOP_RIGHT);
        custLogOut.setMinWidth(50);
        custLogOut.setMinHeight(25);
        custLogOut.setStyle("-fx-font: 10 arial; -fx-base: #000555;");
        hbCustLogOut.getChildren().add(custLogOut);
        grid3.add(hbCustLogOut, 5, 0);
        
        
        scene3 = new Scene(grid3, 200, 400);
        
        window.setMinHeight(300);
        window.setMinWidth(600);
        window.setScene(scene1);
        window.show();
        
        
        
        
        
        
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		do{
			/*
			 * MAIN MENU
			 */
//			System.out.println("--Welcome to ABC Booking system--");
//			System.out.println("1 : Login");
//			System.out.println("2 : Register");
//			System.out.println("0 : Quit");
//			System.out.print("Please enter your selection : ");
//			userInput = scan.nextLine();
//			System.out.println("########################");
//			switch(userInput){
//				case "1":
//					userInst = acct.login(scan, customers, businesses);
//					break;
//				case "2":
//					acct.register(scan, customers, businesses);
//					break;
//				case "0":
//					System.out.println("--Bye Bye--");
//					break;
//				default:
//					System.out.println("Invalid Input - Please Try Again");
//					break;
//			}
			
			
			/*
			 * CUSTOMER MENU BELOW
			 */
//			if(userInst instanceof Customer){
//				System.out.println("-Customer Mode-");
//				Customer custInst = (Customer)userInst;
//				boolean exit = false;
//				do{
//					custInst.customerMenu();
//					userInput = scan.nextLine();
//					switch(userInput){
//						case "1"://add booking
//							custInst.makeBooking(bookings, businesses, scan);
//							break;
//						case "2"://view current bookings
//							custInst.viewBookingSummary(bookings);
//							break;
//						case "3"://view sessions of a business
//							custInst.viewSession(bookings, businesses, scan);
//							break;
//						case "4"://Cancel Booking
//							custInst.cancelBooking(bookings, scan);
//							break;
//						case "9"://log out
//							System.out.println("-- Logging Out --");
//							userInst = null;
//							exit = true;
//							break;
//						case "0":
//							//customer log out and other log out stuff
//							System.out.println("-Logging Out & Exiting-");
//							userInst = null;
//							exit = true;
//							break;
//						default:
//							System.out.println("Invalid Input - Please Try Again");
//							break;
//					}
//					System.out.println("#######################");
//				}while(exit == false);
//				try{
//					Thread.sleep(1500);
//				}catch(Exception e){
//					System.out.println(e.getMessage());
//				}
//			}
//			
//			
//			/*
//			 * BUSINESS MENU BELOW
//			 */
//			else if (userInst instanceof Business){
//				System.out.println("-Owner Mode-");
//				System.out.println("Welcome, "+ userInst.getName() +"!");
//				
//				boolean exit = false;
//				do{
//				Business busInst = (Business)userInst;
//				userInput = scan.nextLine();
//				busInst.businessMenu();
//				
//				switch(userInput){
//				case "1": //add booking
//					break;
//				case "2": //other stuff..
//					break;
//				case "0": //log off
//					System.out.println("-Logging Out & Exiting-");
//					userInst = null;
//					exit = true;
//					break;
//				}
//				
//				}while (exit == false);
//			}
//		
//			
//		}while(!userInput.equals("0") && userInst == null);
		
		FIO.saveBook(bookings);	// Saving all bookings
		FIO.saveBus(businesses);
		FIO.saveCust(customers);
//		System.exit(0);
        
        
        
	}
	public void mainLogIn(ArrayList<Customer> customers, ArrayList<Business> businesses, String username, String password){
		userInst = acct.login(customers, businesses, username, password);
	}
	public void mainRegister(ArrayList<Customer> customers, ArrayList<Business> businesses, String username, String password){
		userInst = acct.login(customers, businesses, username, password);
	}
}
