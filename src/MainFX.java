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
	Scene scene1, scene2;
	
	public static void main(String args[]) throws IOException{ //Handle exceptions
		launch(args);
	}
	
	@Override
    public void start(Stage primaryStage) {	
		
		Scanner scan = new Scanner(System.in);
		FileIO FIO = new FileIO();
		Account acct = new Account();
		Helper help = new Helper();//Remove
		ArrayList<Customer> customers = FIO.loadCust();
		ArrayList<Business> businesses = FIO.loadBus();
		LinkedHashMap<Business, LinkedHashMap<LocalDate, Booking[]>> bookings = FIO.loadBook(help, businesses); //Loading existing bookings	
		User userInst = null;
		String userInput;
		
		
		
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

        Button registerButton = new Button("Click Here to Register");
        HBox hbRegisterButton = new HBox(10);
        hbRegisterButton.setAlignment(Pos.TOP_RIGHT);
        registerButton.setMinWidth(100);
        registerButton.setMinHeight(25);
        registerButton.setStyle("-fx-font: 10 arial; -fx-base: #009900;");
        hbRegisterButton.getChildren().add(registerButton);
        grid.add(hbRegisterButton, 1, 0);
        registerButton.setOnAction(e -> window.setScene(scene2));
        scene1 = new Scene(grid, 600, 250);
        
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
        grid2.add(hbRegister2, 1, 8);
        
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
        
        
        
        
        
        
        
        window.setMinHeight(300);
        window.setMinWidth(600);
        window.setScene(scene1);
        window.show();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
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
				System.out.println("Welcome, "+ userInst.getName() +"!");
				
				boolean exit = false;
				do{
				Business busInst = (Business)userInst;
				userInput = scan.nextLine();
				busInst.businessMenu();
				
				switch(userInput){
				case "1": //add booking
					break;
				case "2": //other stuff..
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
