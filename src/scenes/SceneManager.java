package scenes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import system.Account;
import system.Booking;
import users.Business;
import users.Customer;
import users.Employee;
import users.User;

public class SceneManager {
	Stage window;
	Scene mainMenu, customerRegister, ownerRegister, registerMenu, customerMenu, custSelectBus, custSelectSession, businessMenu, scene4, customerBookingSummary;
	ArrayList<Customer> customers;
	ArrayList<Business> businesses;
	LinkedHashMap<Business, LinkedHashMap<LocalDate, Booking[]>> bookings;
	User userInst = null;
	Account acct;
	Scanner scan = new Scanner(System.in);

	public SceneManager(ArrayList<Customer> customers, ArrayList<Business> businesses, Account account,LinkedHashMap<Business, LinkedHashMap<LocalDate, Booking[]>> bookings,
			Stage primaryStage) {
		this.customers = customers;
		this.businesses = businesses;
		this.acct = account;
		this.bookings = bookings;
		window = primaryStage;
	}

	public void mainLogIn(ArrayList<Customer> customers, ArrayList<Business> businesses, String username,
			String password) {
		userInst = acct.login(customers, businesses, username, password);
	}
	public void mainRegisterBusiness(ArrayList<Business>businesses, String name, String username, String busName, String password1, String password2, String phone, String address){
		if(acct.registerBusiness(name, username, busName, password1, password2, phone, address, businesses)){
			/*
			 * Registration successful for popup box
			 * As with register customer.
			 */
			
		}
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

	@SuppressWarnings("unchecked")
	public void showBookingSummary() {
		

		GridPane grid = new GridPane();
		grid.setPadding(new Insets(30, 30, 30, 30));
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);

		Text header = new Text("Your Booking Summary");
		header.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
		grid.add(header, 0, 1, 2, 1);
		
		TableView<Booking> table;
		
		//Business Column
		TableColumn<Booking,Business> business =  new TableColumn<>("Business");
		business.setMinWidth(200);
		business.setCellValueFactory(new PropertyValueFactory<>("bookBus"));
		
		//Date Column
		TableColumn<Booking,LocalDate> bookingDate =  new TableColumn<>("Date");
		bookingDate.setMinWidth(200);
		bookingDate.setCellValueFactory(new PropertyValueFactory<>("bookDate"));
		
		//Session start column
		TableColumn<Booking,LocalTime> sessionStart =  new TableColumn<>("Session Start");
		sessionStart.setMinWidth(200);
		sessionStart.setCellValueFactory(new PropertyValueFactory<>("startTime"));
				
		//Session ends column
		TableColumn<Booking,LocalTime> sessionEnd =  new TableColumn<>("Session End");
		sessionEnd.setMinWidth(200);
		sessionEnd.setCellValueFactory(new PropertyValueFactory<>("endTime"));
		
		//Employee
		TableColumn<Booking, Employee> emp =  new TableColumn<>("Employee");
		emp.setMinWidth(200);
		emp.setCellValueFactory(new PropertyValueFactory<>("bookEmp"));
		
		emp = new TableColumn<>("Employee");
		emp.setCellValueFactory(new PropertyValueFactory<>("bookEmp"));
		// ======== setting the cell factory for the city column  
		emp.setCellFactory(new Callback<TableColumn<Booking, Employee>,TableCell<Booking, Employee>>(){

				@Override
		        public TableCell<Booking, Employee> call(TableColumn<Booking, Employee> param) {

		            TableCell<Booking, Employee> cityCell = new TableCell<Booking, Employee>(){
		            	
		            	protected void updateItem(Employee item, boolean empty) {
		                    if (item != null) {
		                        Label cityLabel = new Label(item.getName());
		                        setGraphic(cityLabel);
		                    }
		                }                    
		            };               

		            return cityCell;                
		        }

		    });
		
		ObservableList<Booking> empList = getCustomerBookings((Customer) userInst,bookings);
		if(empList.isEmpty() == false){
			table = new TableView<>();
			table.setItems(getCustomerBookings((Customer) userInst,bookings));
			table.getColumns().addAll(business,bookingDate,sessionStart,sessionEnd,emp);
		}else{
			System.out.println("No bookings yet");
		}

	}

	public void selectTime(int busIndex){
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(30, 30, 30, 30));
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        
        Text header = new Text("Select a Date");
        header.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
        grid.add(header, 2, 0);
        
        DatePicker datePicker = new DatePicker();
        
        /*
         * code based on https://docs.oracle.com/javase/8/javafx/user-interface-tutorial/date-picker.htm
         */
        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        
                        if (item.isBefore(LocalDate.now())) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                };
            }
        };
        datePicker.setDayCellFactory(dayCellFactory);
        grid.add(datePicker, 2, 2);
        
        
        
        Button checkButton = new Button("Check");
        checkButton.setMinHeight(50);
        checkButton.setMinWidth(100);
        checkButton.setStyle("-fx-font: 22 arial; -fx-base: #000555;");
        grid.add(checkButton, 1, 3);
        checkButton.setOnAction(e -> {
        	Business bus = null;
        	for(Business myBus : bookings.keySet()){
        		if(businesses.get(busIndex).getBusName().equals(myBus.getBusName()))
        			bus = myBus;
        	}
        	
        	customerMenu();
        	window.setScene(customerMenu);
        });
        
        Button returnButton = new Button("Back");
        returnButton.setMinHeight(50);
        returnButton.setMinWidth(100);
        returnButton.setStyle("-fx-font: 22 arial; -fx-base: #000555;");
        grid.add(returnButton, 3, 3);
        returnButton.setOnAction(e -> {
        	selectBusiness();
        	window.setScene(custSelectBus);
        });
        
        
        custSelectSession = new Scene(grid, 500, 500);
        
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
        		customerMenu();
        		window.setScene(customerMenu);
        	}
        	else if(userInst instanceof Business){
        		businessMenu();
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

	
	public void selectBusiness(){
		
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(30, 30, 30, 30));
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        
        Text header = new Text("Select a Business to Book");
        header.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
        grid.add(header, 0, 1,2, 1);
        
        ListView<String> busList = new ListView<String>(); 
        ObservableList<String>busItems = FXCollections.observableArrayList();
        for(Business business : businesses){
        	busItems.add(business.getBusName());
        }
        busList.setItems(busItems);
        
        busList.setPrefHeight(300);
        busList.setPrefWidth(300);
        
        grid.add(busList, 2,2);
        
        Button selectButton = new Button("Select");
        selectButton.setMinHeight(50);
        selectButton.setMinWidth(100);
        selectButton.setStyle("-fx-font: 22 arial; -fx-base: #000555;");
        grid.add(selectButton, 1, 3);
        selectButton.setOnAction(e -> {
        	int busIndex = busList.getSelectionModel().getSelectedIndex();
        	if(busIndex != -1){
        		selectTime(busIndex);
        		window.setScene(custSelectSession);
        	}
        });
        
        Button returnButton = new Button("Back");
        returnButton.setMinHeight(50);
        returnButton.setMinWidth(100);
        returnButton.setStyle("-fx-font: 22 arial; -fx-base: #000555;");
        grid.add(returnButton, 3, 3);
        returnButton.setOnAction(e -> {
        	customerMenu();
        	window.setScene(customerMenu);
        });
        custSelectBus = new Scene(grid, 500, 500);
	}

        
	
	public void showRegister(){
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
        register.setStyle("-fx-font: 15 arial; -fx-base: #79B8FF;");
        hbRegister.getChildren().add(register);
        grid2.add(hbRegister, 1, 8);
        
        register.setOnAction(e -> {
        	String fullNameString = fullNameText.getText();
        	String newUserNameString = newUserNameInput.getText();
        	String newPasswordString = newPasswordInput.getText();
        	String newPasswordString2 = newPasswordInput2.getText();
        	String phoneString = phoneText.getText();
        	String addressString = addressNew.getText();
        	mainRegisterCust(customers, fullNameString, newUserNameString, newPasswordString, newPasswordString2, phoneString, addressString);
        	showMainMenu();
        	window.setScene(mainMenu);
        	
        });
            
        Button back = new Button("Back");
        HBox hbBack = new HBox(10);
        hbBack.setAlignment(Pos.TOP_RIGHT);
        back.setMinWidth(50);
        back.setMinHeight(20);
        back.setStyle("-fx-font: 10 arial; -fx-base: #B7FF6E;");
        hbBack.getChildren().add(back);
        grid2.add(hbBack, 1, 0);
        back.setOnAction(e -> {
        	showMainMenu();
        	window.setScene(registerMenu);
        });
        
        customerRegister = new Scene(grid2, 300, 500);
	}
	
	public void registerOwner() {
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
        	mainRegisterBusiness(businesses, fullNameString, newUserNameString, busNameString, newPasswordString, newPasswordString2, phoneString, addressString);
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
	
	public void customerMenu(){
        GridPane grid3 = new GridPane();
    	grid3.setPadding(new Insets(30, 30, 30, 30));
    	grid3.setAlignment(Pos.CENTER);
    	grid3.setHgap(10);
    	grid3.setVgap(10);
        
    	Text custTitle = new Text("Welcome --" + userInst.getName() + "--");
    	custTitle.setFont(Font.font("Rockwell", FontWeight.NORMAL, 35));
        grid3.add(custTitle, 0, 0, 1, 1);
        
        Button custAdd = new Button("Add Booking");
        HBox hbCustAdd = new HBox(10);
        hbCustAdd.setAlignment(Pos.CENTER);
        custAdd.setMinWidth(150);
        custAdd.setMinHeight(25);
        custAdd.setStyle("-fx-font: 10 verdana; -fx-base: #79B8FF;");
        hbCustAdd.getChildren().add(custAdd);
        grid3.add(hbCustAdd, 0, 1);
        custAdd.setOnAction(e -> {
        	selectBusiness();
        	window.setScene(custSelectBus);
        });
        
    
        Button custCurrent = new Button("View Current Bookings");
        HBox hbCustCurrent = new HBox(10);
        hbCustCurrent.setAlignment(Pos.CENTER);
        custCurrent.setMinWidth(150);
        custCurrent.setMinHeight(25);
        custCurrent.setStyle("-fx-font: 10 verdana; -fx-base: #79B8FF;");
        hbCustCurrent.getChildren().add(custCurrent);
        grid3.add(hbCustCurrent, 0, 2);
        custCurrent.setOnAction(e -> {
          	showBookingSummary();
        	window.setScene(customerBookingSummary);
        });
        
        Button custSession = new Button("View Available Sessions");
        HBox hbCustSession = new HBox(10);
        hbCustSession.setAlignment(Pos.CENTER);
        custSession.setMinWidth(150);
        custSession.setMinHeight(25);
        custSession.setStyle("-fx-font: 10 verdana; -fx-base: #79B8FF;");
        hbCustSession.getChildren().add(custSession);
        grid3.add(hbCustSession, 0, 3);
        
        
        Button custCancel = new Button("Cancel Bookings");
        HBox hbCustCancel = new HBox(10);
        hbCustCancel.setAlignment(Pos.CENTER);
        custCancel.setMinWidth(150);
        custCancel.setMinHeight(25);
        custCancel.setStyle("-fx-font: 10 verdana; -fx-base: #79B8FF;");
        hbCustCancel.getChildren().add(custCancel);
        grid3.add(hbCustCancel, 0, 4);
        
        Button custLogOut = new Button("Logout");
        HBox hbCustLogOut = new HBox(10);
        hbCustLogOut.setAlignment(Pos.CENTER);
        custLogOut.setMinWidth(150);
        custLogOut.setMinHeight(25);
        custLogOut.setStyle("-fx-font: 10 verdana; -fx-base: #79B8FF;");
        hbCustLogOut.getChildren().add(custLogOut);
        grid3.add(hbCustLogOut, 0, 5);
        custLogOut.setOnAction(e -> {
        	showMainMenu();
        	window.setScene(mainMenu);
        	userInst = null;
        	});
        
        customerMenu = new Scene(grid3, 200, 400);
	}
	
	public void businessMenu(){
        GridPane grid3 = new GridPane();
    	grid3.setPadding(new Insets(30, 30, 30, 30));
    	grid3.setAlignment(Pos.CENTER);
    	grid3.setHgap(10);
    	grid3.setVgap(10);
        
    	Text busTitle = new Text("Welcome --" + userInst.getName() + "--");
    	busTitle.setFont(Font.font("Rockwell", FontWeight.NORMAL, 40));
        grid3.add(busTitle, 0, 0, 1, 1);
        
        Button busAddEmp = new Button("Add New Employee");
        HBox hbBusAddEmp = new HBox(10);
        hbBusAddEmp.setAlignment(Pos.CENTER);
        busAddEmp.setMinWidth(150);
        busAddEmp.setMinHeight(25);
        busAddEmp.setStyle("-fx-font: 10 verdana; -fx-base: #79B8FF;");
        hbBusAddEmp.getChildren().add(busAddEmp);
        grid3.add(hbBusAddEmp, 0, 1);
        
    
        Button busWorkingTime = new Button("Add Working Time");
        HBox hbBusWorkingTime = new HBox(10);
        hbBusWorkingTime.setAlignment(Pos.CENTER);
        busWorkingTime.setMinWidth(150);
        busWorkingTime.setMinHeight(25);
        busWorkingTime.setStyle("-fx-font: 10 verdana; -fx-base: #79B8FF;");
        hbBusWorkingTime.getChildren().add(busWorkingTime);
        grid3.add(hbBusWorkingTime, 0, 2);
        
        Button busAssignSesh = new Button("Assign Sessions");
        HBox hbBusAssignSesh = new HBox(10);
        hbBusAssignSesh.setAlignment(Pos.CENTER);
        busAssignSesh.setMinWidth(150);
        busAssignSesh.setMinHeight(25);
        busAssignSesh.setStyle("-fx-font: 10 verdana; -fx-base: #79B8FF;");
        hbBusAssignSesh.getChildren().add(busAssignSesh);
        grid3.add(hbBusAssignSesh, 0, 3);
        
        
        Button busViewSum = new Button("View Booking Summary");
        HBox hbBusViewSum = new HBox(10);
        hbBusViewSum.setAlignment(Pos.CENTER);
        busViewSum.setMinWidth(150);
        busViewSum.setMinHeight(25);
        busViewSum.setStyle("-fx-font: 10 verdana; -fx-base: #79B8FF;");
        hbBusViewSum.getChildren().add(busViewSum);
        grid3.add(hbBusViewSum, 0, 4);
        
        Button busAddBooking = new Button("Add Booking");
        HBox hbBusAddBooking = new HBox(10);
        hbBusAddBooking.setAlignment(Pos.CENTER);
        busAddBooking.setMinWidth(150);
        busAddBooking.setMinHeight(25);
        busAddBooking.setStyle("-fx-font: 10 verdana; -fx-base: #79B8FF;");
        hbBusAddBooking.getChildren().add(busAddBooking);
        grid3.add(hbBusAddBooking, 0, 4);
        
        Button busShowAvail = new Button("Show Employee Availability");
        HBox hbBusShowAvail = new HBox(10);
        hbBusShowAvail.setAlignment(Pos.CENTER);
        busShowAvail.setMinWidth(150);
        busShowAvail.setMinHeight(25);
        busShowAvail.setStyle("-fx-font: 10 verdana; -fx-base: #79B8FF;");
        hbBusShowAvail.getChildren().add(busShowAvail);
        grid3.add(hbBusShowAvail, 0, 4);
        
        Button busLogOut = new Button("Logout");
        HBox hbBusLogOut = new HBox(10);
        hbBusLogOut.setAlignment(Pos.CENTER);
        busLogOut.setMinWidth(150);
        busLogOut.setMinHeight(25);
        busLogOut.setStyle("-fx-font: 10 verdana; -fx-base: #79B8FF;");
        hbBusLogOut.getChildren().add(busLogOut);
        grid3.add(hbBusLogOut, 0, 5);
        busLogOut.setOnAction(e -> {
        	showMainMenu();
        	window.setScene(mainMenu);
        	userInst = null;
        	});
        
        businessMenu = new Scene(grid3, 200, 400);
	}
	public ObservableList<Booking> getCustomerBookings(Customer cust,LinkedHashMap<Business, LinkedHashMap<LocalDate, Booking[]>> bookings) {
		ObservableList<Booking> bookingsToBeViewed = FXCollections.observableArrayList();
		
		int counter = 0;
		for(Business myBus : bookings.keySet()){	//For each business
			LinkedHashMap<LocalDate, Booking[]> myDay = bookings.get(myBus);	//For each business LinkedHashMap
			for(LocalDate myDate : myDay.keySet()){		//For each date
				Booking[] myBooking = myDay.get(myDate);
				for(int i=0 ; i < myBooking.length; i++){	//For all bookings on each day
					if(myBooking[i].getBookStat()){
						if(myBooking[i].getBookCust().getUsername().equals(cust.getUsername())){
							System.out.println("Test");
							bookingsToBeViewed.add(myBooking[i]);
							counter++;
						}
					}
				}		
			}
		}
		if(counter == 0){
			System.out.printf("\n-- You have no current bookings! --\n\n");
		}
		
		return bookingsToBeViewed;
	}
	
	public void show(){
        window.setMinHeight(300);
        window.setMinWidth(600);
        window.setScene(mainMenu);
        window.show();
	}
}
