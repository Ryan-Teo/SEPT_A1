package scenes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import system.Account;
import system.Booking;
import users.Business;
import users.Customer;
import users.Employee;
import users.User;

public class MainMenu extends SceneManager{

	Logger logger = Logger.getLogger(MainMenu.class);
	
	public MainMenu(ArrayList<Customer> customers, ArrayList<Business> businesses, Account account,
			ArrayList<Booking> bookings, Stage primaryStage) {
		super(customers, businesses, account, bookings, primaryStage);
		// TODO Auto-generated constructor stub
	}
			
	public void showMainMenu(){
    	GridPane grid = new GridPane();
    	grid.setPadding(new Insets(30, 30, 30, 30));
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
       
        Text header = new Text("Press The Button");
        header.setFont(Font.font("Rockwell", FontWeight.NORMAL, 40));
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
        signInButton.setMinHeight(45);
        signInButton.setStyle("-fx-font: 20 verdana; -fx-base: #79B8FF;");
        hbsignInButton.getChildren().add(signInButton);
        grid.add(hbsignInButton, 1, 5);
        
        signInButton.setOnAction(e -> {
        	String userNameString = userNameInput.getText();
        	String passwordString = passwordInput.getText();
        	mainLogIn(customers, businesses, userNameString, passwordString);
        	if(userInst instanceof Customer){
        		logger.info("Initializing customer menu");
        		custScreen.customerMenu();
        		window.setScene(customerMenu);
        	}
        	else if(userInst instanceof Business){
        		logger.info("Initializing business menu");
        		busScreen.businessMenu();
        		window.setScene(businessMenu);
        	}

        });

        Button registerButton = new Button("Click Here to Register");
        HBox hbRegisterButton = new HBox(10);
        hbRegisterButton.setAlignment(Pos.TOP_RIGHT);
        registerButton.setMinWidth(100);
        registerButton.setMinHeight(25);
        registerButton.setStyle("-fx-font: 10 verdana; -fx-base: #B7FF6E;");
        hbRegisterButton.getChildren().add(registerButton);
        grid.add(hbRegisterButton, 1, 0);
        registerButton.setOnAction(e -> {
        		showRegister();
	        	window.setScene(registerMenu);
        	});
        mainMenu = new Scene(grid, 600, 250);
	}
	
	public void showRegister(){
		logger.info("Initializing sign up page");
        GridPane grid2a = new GridPane();
        grid2a.setPadding(new Insets(30, 30, 30, 30));
        grid2a.setAlignment(Pos.CENTER);
        grid2a.setHgap(10);
        grid2a.setVgap(10);
        
        Button back = new Button("Back");
        HBox hbBack = new HBox(10);
        hbBack.setAlignment(Pos.TOP_RIGHT);
        back.setMinWidth(50);
        back.setMinHeight(25);
        back.setStyle("-fx-font: 10 verdana; -fx-base: #B7FF6E;");
        hbBack.getChildren().add(back);
        grid2a.add(hbBack, 1, 0);
        back.setOnAction(e -> {
        	showMainMenu();
        	window.setScene(mainMenu);
        });
        
        Text registerheader = new Text("Are you registering as:");
        registerheader.setFont(Font.font("Rockwell", FontWeight.NORMAL, 20));
        grid2a.add(registerheader, 0,1, 2, 1);
        
        Button custInstButton = new Button("Customer");
        HBox hbcustInstButton = new HBox(10);
        custInstButton.setMinWidth(100);
        custInstButton.setMinHeight(30);
        custInstButton.setAlignment(Pos.CENTER);
        custInstButton.setStyle("-fx-font: 15 verdana; -fx-base: #79B8FF;");
        hbcustInstButton.getChildren().add(custInstButton);
        grid2a.add(hbcustInstButton, 0, 2);
        custInstButton.setOnAction(e -> {
        	registerCustomer();
        	window.setScene(customerRegister);
        });
        
        Button ownerInstButton = new Button("Business");
        HBox hbOwnerInstButton = new HBox(10);
        ownerInstButton.setMinWidth(100);
        ownerInstButton.setMinHeight(30);
        ownerInstButton.setAlignment(Pos.CENTER);
        ownerInstButton.setStyle("-fx-font: 15 verdana; -fx-base: #79B8FF;");
        hbOwnerInstButton.getChildren().add(ownerInstButton);
        grid2a.add(hbOwnerInstButton, 1, 2);
        ownerInstButton.setOnAction(e -> {
        		//meant to show the business menu
        		registerOwner();
        		window.setScene(ownerRegister);
        	});
        
        
        registerMenu = new Scene(grid2a, 600, 250);
	}
	
	public void registerCustomer() {
		logger.info("Initializing customer sign up page");
        GridPane grid2 = new GridPane();
    	grid2.setPadding(new Insets(30, 30, 30, 30));
    	grid2.setAlignment(Pos.CENTER);
    	grid2.setHgap(20);
    	grid2.setVgap(20);
    	
        
        Text registerTitle = new Text("Register Here:");
        registerTitle.setFont(Font.font("Rockwell", FontWeight.NORMAL, 35));
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
        
        Label address = new Label("Address ");
        grid2.add(address, 0, 7);

        TextField addressNew = new TextField();
        addressNew.setPromptText("address");
        grid2.add(addressNew, 1, 7);
        
        Button register = new Button("Register!");
        HBox hbRegister = new HBox(10);
        hbRegister.setAlignment(Pos.BOTTOM_RIGHT);
        register.setMinWidth(80);
        register.setMinHeight(40);
        register.setStyle("-fx-font: 15 verdana; -fx-base: #79B8FF;");
        hbRegister.getChildren().add(register);
        grid2.add(hbRegister, 1, 8);
        
        register.setOnAction(e -> {
        	String fullNameString = fullNameText.getText();
        	String newUserNameString = newUserNameInput.getText();
        	String newPasswordString = newPasswordInput.getText();
        	String newPasswordString2 = newPasswordInput2.getText();
        	String phoneString = phoneText.getText();
        	String addressString = addressNew.getText();
        	
        	
        	Boolean checkUser = acct.checkCustName(newUserNameString, customers);
        	Boolean checkPassLength = acct.checkLength(newPasswordString, 6, 12);
        	Boolean checkPassword1 = acct.checkPass(newPasswordString, newPasswordString2);
        	Boolean checkPhone = acct.checkPhone(phoneString);
        	Boolean check = mainRegisterCust(customers, fullNameString, newUserNameString, newPasswordString, newPasswordString2, phoneString, addressString);
        	if (check){
        		handleSuccess(window);
        	}
        	else{
        		handleFail(window, checkUser, checkPassLength, checkPassword1, checkPhone);
        	}
        	
        	showMainMenu();
        	window.setScene(mainMenu);
        	
        });
            
        Button back = new Button("Back");
        HBox hbBack = new HBox(10);
        hbBack.setAlignment(Pos.TOP_RIGHT);
        back.setMinWidth(50);
        back.setMinHeight(20);
        back.setStyle("-fx-font: 10 verdana; -fx-base: #B7FF6E;");
        hbBack.getChildren().add(back);
        grid2.add(hbBack, 1, 0);
        back.setOnAction(e -> {
        	showMainMenu();
        	window.setScene(registerMenu);
        });
        
        customerRegister = new Scene(grid2, 300, 500);
	}
	
	public void registerOwner() {
		logger.info("Initializing business sign up page");
        GridPane grid3 = new GridPane();
    	grid3.setPadding(new Insets(30, 30, 30, 30));
    	grid3.setAlignment(Pos.CENTER);
    	grid3.setHgap(20);
    	grid3.setVgap(20);
    	
        
        Text registerTitle = new Text("Register Here:");
        registerTitle.setFont(Font.font("Rockwell", FontWeight.NORMAL, 35));
        grid3.add(registerTitle, 0, 1, 2, 1);
        
        Label fullName = new Label("Full Name: ");
        grid3.add(fullName, 0, 2);

        TextField fullNameText = new TextField();
        fullNameText.setPromptText("legal name");
        grid3.add(fullNameText, 1, 2);

        Label newUserName = new Label("User Name:");
        grid3.add(newUserName, 0, 3);

        TextField newUserNameInput = new TextField();
        newUserNameInput.setPromptText("username");
        grid3.add(newUserNameInput, 1, 3);

        Label newPassword = new Label("Password:");
        grid3.add(newPassword, 0, 4);

        PasswordField newPasswordInput = new PasswordField();
        newPasswordInput.setPromptText("password");
        grid3.add(newPasswordInput, 1, 4);
        
        Label newPassword2 = new Label("Re-Enter Password:");
        grid3.add(newPassword2, 0, 5);

        PasswordField newPasswordInput2 = new PasswordField();
        newPasswordInput2.setPromptText("password");
        grid3.add(newPasswordInput2, 1, 5);
        
        Label newBusiness = new Label("Business Name:");
        grid3.add(newBusiness, 0, 6);

        TextField newBusinessInput = new TextField();
        newBusinessInput.setPromptText("business name");
        grid3.add(newBusinessInput, 1, 6);
        
        Label phone = new Label("Contact Number ");
        grid3.add(phone, 0, 7);

        TextField phoneText = new TextField();
        phoneText.setPromptText("phone number");
        grid3.add(phoneText, 1, 7);
        
        Label address = new Label("Address ");
        grid3.add(address, 0, 8);

        TextField addressNew = new TextField();
        addressNew.setPromptText("address");
        grid3.add(addressNew, 1, 8);
        
        Button register = new Button("Register!");
        HBox hbRegister = new HBox(10);
        hbRegister.setAlignment(Pos.BOTTOM_RIGHT);
        register.setMinWidth(80);
        register.setMinHeight(40);
        register.setStyle("-fx-font: 15 verdana; -fx-base: #79B8FF;");
        hbRegister.getChildren().add(register);
        grid3.add(hbRegister, 1, 9);
        
        register.setOnAction(e -> {
        	String fullNameString = fullNameText.getText();
        	String newUserNameString = newUserNameInput.getText();
        	String newPasswordString = newPasswordInput.getText();
        	String newPasswordString2 = newPasswordInput2.getText();
        	String busNameString = newBusinessInput.getText();
        	String phoneString = phoneText.getText();
        	String addressString = addressNew.getText();

        	Boolean check = mainRegisterBusiness(businesses, fullNameString, newUserNameString, busNameString, newPasswordString, newPasswordString2, phoneString, addressString);
        	
        	if (check){
        		handleSuccess(window);
        	}
        	showMainMenu();
        	window.setScene(mainMenu);
        	
       	
        	
        });
            
        Button back = new Button("Back");
        HBox hbBack = new HBox(10);
        hbBack.setAlignment(Pos.CENTER);
        back.setMinWidth(50);
        back.setMinHeight(25);
        back.setStyle("-fx-font: 10 verdana; -fx-base: #B7FF6E;");
        hbBack.getChildren().add(back);
        grid3.add(hbBack, 1, 0);
        back.setOnAction(e -> {
        	showMainMenu();
        	window.setScene(registerMenu);
        });
        
        ownerRegister = new Scene(grid3, 300, 500);
	}
}
