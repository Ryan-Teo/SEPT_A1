package scenes;

import java.util.ArrayList;

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
import users.Business;
import users.Customer;
import users.User;

public class SceneManager {
	Stage window;
	Scene mainMenu, customerRegister, registerMenu, customerMenu, scene4, customerBookingSummary;
	ArrayList<Customer> customers;
	ArrayList<Business> businesses;
	User userInst = null;
	Account acct;

	public SceneManager(ArrayList<Customer> customers, ArrayList<Business> businesses, Account account,
			Stage primaryStage) {
		this.customers = customers;
		this.businesses = businesses;
		this.acct = account;
		window = primaryStage;
	}

	public void mainLogIn(ArrayList<Customer> customers, ArrayList<Business> businesses, String username,
			String password) {
		userInst = acct.login(customers, businesses, username, password);
	}

	public void mainRegisterCust(ArrayList<Customer> customers, String name, String username, String password1,
			String password2, String phone, String address) {
		if (acct.registerCustomer(name, username, password1, password2, phone, address, customers)) {
			/*
			 * If the registration is successful, then show a pop up box. The
			 * pop up box will tell the user that they have successfully
			 * registered. The pop up box needs to have a button in it. After
			 * the user clicks on the button, return them to the main menu
			 * scene.
			 */

			System.out.println("successful");

		}

	}

	public void showBookingSummary() {
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(30, 30, 30, 30));
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);

		Text header = new Text("Your Summary");
		header.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
		grid.add(header, 0, 1, 2, 1);
		
		

		
		
		
		
		Button backToMenuButton = new Button("Go back to menu");
		HBox hbBackToMenuButton = new HBox(10);
		hbBackToMenuButton.setAlignment(Pos.BOTTOM_RIGHT);
		backToMenuButton.minHeight(50);
		backToMenuButton.minWidth(100);
		backToMenuButton.setStyle("-fx-font: 22 arial; -fx-base: #000555;");
		hbBackToMenuButton.getChildren().add(backToMenuButton);
		grid.add(hbBackToMenuButton, 1, 5);

		backToMenuButton.setOnAction(e -> {
			customerMenu();
			window.setScene(customerMenu);
		});
		
		customerBookingSummary = new Scene(grid,600, 250);
		
	}

	public void showMainMenu() {
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

			if (userInst instanceof Customer) {
				customerMenu();
				window.setScene(customerMenu);
			}
		});

		Button registerButton = new Button("Click Here to Register");
		HBox hbRegisterButton = new HBox(10);
		hbRegisterButton.setAlignment(Pos.TOP_RIGHT);
		registerButton.setMinWidth(100);
		registerButton.setMinHeight(25);
		registerButton.setStyle("-fx-font: 10 arial; -fx-base: #009900;");
		hbRegisterButton.getChildren().add(registerButton);
		grid.add(hbRegisterButton, 1, 0);
		registerButton.setOnAction(e -> {
			showRegister();
			window.setScene(registerMenu);
		});
		mainMenu = new Scene(grid, 600, 250);
	}

	public void showRegister() {
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
		custInstButton.setOnAction(e -> {
			registerCustomer();
			window.setScene(customerRegister);
		});

		Button ownerInstButton = new Button("Business");
		HBox hbOwnerInstButton = new HBox(10);
		ownerInstButton.setMinWidth(100);
		ownerInstButton.setMinHeight(50);
		ownerInstButton.setStyle("-fx-font: 22 arial; -fx-base: #000555;");
		hbOwnerInstButton.getChildren().add(ownerInstButton);
		grid2a.add(hbOwnerInstButton, 1, 2);
		ownerInstButton.setOnAction(e -> {
			// meant to show the business menu
			showMainMenu();
			window.setScene(mainMenu);
		});

		registerMenu = new Scene(grid2a, 600, 250);
	}

	public void registerCustomer() {
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

		Label address = new Label("Address ");
		grid2.add(address, 0, 7);

		TextField addressNew = new TextField();
		addressNew.setPromptText("address");
		grid2.add(addressNew, 1, 7);

		Button register = new Button("Register!");
		HBox hbRegister = new HBox(10);
		hbRegister.setAlignment(Pos.BOTTOM_RIGHT);
		register.setMinWidth(80);
		register.setMinHeight(50);
		register.setStyle("-fx-font: 22 arial; -fx-base: #009900;");
		hbRegister.getChildren().add(register);
		grid2.add(hbRegister, 1, 8);

		register.setOnAction(e -> {
			String fullNameString = fullNameText.getText();
			String newUserNameString = newUserNameInput.getText();
			String newPasswordString = newPasswordInput.getText();
			String newPasswordString2 = newPasswordInput2.getText();
			String phoneString = phoneText.getText();
			String addressString = addressNew.getText();
			mainRegisterCust(customers, fullNameString, newUserNameString, newPasswordString, newPasswordString2,
					phoneString, addressString);
			showMainMenu();
			window.setScene(mainMenu);

		});

		Button back = new Button("Back");
		HBox hbBack = new HBox(10);
		hbBack.setAlignment(Pos.TOP_RIGHT);
		back.setMinWidth(50);
		back.setMinHeight(25);
		back.setStyle("-fx-font: 10 arial; -fx-base: #000555;");
		hbBack.getChildren().add(back);
		grid2.add(hbBack, 1, 0);
		back.setOnAction(e -> {
			showMainMenu();
			window.setScene(mainMenu);
		});

		customerRegister = new Scene(grid2, 200, 400);
	}

	public void customerMenu() {
		GridPane grid3 = new GridPane();
		grid3.setPadding(new Insets(30, 30, 30, 30));
		grid3.setAlignment(Pos.CENTER);
		grid3.setHgap(10);
		grid3.setVgap(10);

		Text custTitle = new Text("Welcome --" + userInst.getName() + "--");
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
		custCurrent.setOnAction(e -> {
			showBookingSummary();
			window.setScene(customerBookingSummary);
		});

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
		custLogOut.setOnAction(e -> {
			showMainMenu();
			window.setScene(mainMenu);
			userInst = null;
		});

		customerMenu = new Scene(grid3, 200, 400);
	}

	public void show() {
		window.setMinHeight(300);
		window.setMinWidth(600);
		window.setScene(mainMenu);
		window.show();
	}
}