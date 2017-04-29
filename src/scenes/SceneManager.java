package scenes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;
import system.Account;
import system.Booking;
import system.FileIO;
import users.Business;
import users.Customer;
import users.Employee;
import users.User;

public class SceneManager {
	Stage window;
	Scene mainMenu, customerRegister, ownerRegister, registerMenu, customerMenu, custSelectService, busAddEmpSc, busAddWorkTime,
			custSelectBus, custSelectDate, custSelectTime, custSelectEmp, businessMenu, scene4, customerBookingSummary,
			busSelectEmp;
	ArrayList<Customer> customers;
	ArrayList<Business> businesses;
	ArrayList<Booking> bookings;
	User userInst = null;
	Account acct;
	FileIO FIO = new FileIO();

	public SceneManager(ArrayList<Customer> customers, ArrayList<Business> businesses, 
			Account account, ArrayList<Booking> bookings, 
			Stage primaryStage) {
		this.customers = customers;
		this.businesses = businesses;
		this.acct = account;
		this.bookings = bookings;
		window = primaryStage;
	}

	/*
	 * Methods for interacting with outside methods
	 */
	public void mainLogIn(ArrayList<Customer> customers, ArrayList<Business> businesses, String username,
			String password) {
		userInst = acct.login(customers, businesses, username, password);
	}
	public boolean mainRegisterBusiness(ArrayList<Business>businesses, String name, String username, 
			String busName, String password1, String password2, String phone, String address){
		return acct.registerBusiness(name, username, busName, password1, password2, phone, address, businesses);

	}
	public boolean mainRegisterCust(ArrayList<Customer> customers, String name, String username, String password1, String password2, String phone, String address) {
		return acct.registerCustomer(name, username, password1, password2, phone, address, customers);
	
	}
	
	/*
	 * END METHODS FOR OUTSIDE METHODS
	 */
	
	
	/*
	 * Methods for the main menu + registering
	 */
	public void show(){
        window.setMinHeight(300);
        window.setMinWidth(600);
        window.setScene(mainMenu);
        window.show();
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
	
	
	/*
	 * END METHODS FOR MAIN MENU
	 */
	
	/*
	 * CUSTOMER STUFF
	 */
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
        
    
        Button custCurrent = new Button("View/Cancel My Bookings");
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
        
//        Button custSession = new Button("View Available Sessions");
//        HBox hbCustSession = new HBox(10);
//        hbCustSession.setAlignment(Pos.CENTER);
//        custSession.setMinWidth(150);
//        custSession.setMinHeight(25);
//        custSession.setStyle("-fx-font: 10 verdana; -fx-base: #79B8FF;");
//        hbCustSession.getChildren().add(custSession);
//        grid3.add(hbCustSession, 0, 3);
//        
        
//        Button custCancel = new Button("Cancel Bookings");
//        HBox hbCustCancel = new HBox(10);
//        hbCustCancel.setAlignment(Pos.CENTER);
//        custCancel.setMinWidth(150);
//        custCancel.setMinHeight(25);
//        custCancel.setStyle("-fx-font: 10 verdana; -fx-base: #79B8FF;");
//        hbCustCancel.getChildren().add(custCancel);
//        grid3.add(hbCustCancel, 0, 4);
        
        Button custLogOut = new Button("Logout");
        HBox hbCustLogOut = new HBox(10);
        hbCustLogOut.setAlignment(Pos.CENTER);
        custLogOut.setMinWidth(150);
        custLogOut.setMinHeight(25);
        custLogOut.setStyle("-fx-font: 10 verdana; -fx-base: #79B8FF;");
        hbCustLogOut.getChildren().add(custLogOut);
        grid3.add(hbCustLogOut, 0, 3);
        custLogOut.setOnAction(e -> {
        	showMainMenu();
        	window.setScene(mainMenu);
        	userInst = null;
        	});
        
        customerMenu = new Scene(grid3, 200, 400);
	}
		//Customer Add Booking Stuff
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
        grid.add(selectButton, 3, 3);
        selectButton.setOnAction(e -> {
        	int busIndex = busList.getSelectionModel().getSelectedIndex();
        	if(busIndex != -1){
        		selectService(busIndex);
        		window.setScene(custSelectService);
        	}
        });
        
        Button returnButton = new Button("Back");
        returnButton.setMinHeight(50);
        returnButton.setMinWidth(100);
        returnButton.setStyle("-fx-font: 22 arial; -fx-base: #000555;");
        grid.add(returnButton, 0, 3);
        returnButton.setOnAction(e -> {
        	customerMenu();
        	window.setScene(customerMenu);
        });
        custSelectBus = new Scene(grid, 500, 500);
	}
	
	public void selectService(int busIndex){
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(30, 30, 30, 30));
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        
        Text header = new Text("Select a Service");
        header.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
        grid.add(header, 0, 1,2, 1);
        
        Business bus = businesses.get(busIndex);
        
        
        ListView<String> serviceList = new ListView<String>(); 
        ObservableList<String> serviceItems = FXCollections.observableArrayList();
        for(String myService : bus.getServices().keySet()){
        	serviceItems.add(myService);
        }
        serviceList.setItems(serviceItems);
        
        serviceList.setPrefHeight(300);
        serviceList.setPrefWidth(300);
        
        grid.add(serviceList, 2,2);
        
        Button selectButton = new Button("Select");
        selectButton.setMinHeight(50);
        selectButton.setMinWidth(100);
        selectButton.setStyle("-fx-font: 22 arial; -fx-base: #000555;");
        grid.add(selectButton, 3, 3);
        selectButton.setOnAction(e -> {
    		String service = serviceList.getSelectionModel().getSelectedItem();
    		selectDate(busIndex, service);
    		window.setScene(custSelectDate);
        });
        
        Button returnButton = new Button("Back");
        returnButton.setMinHeight(50);
        returnButton.setMinWidth(100);
        returnButton.setStyle("-fx-font: 22 arial; -fx-base: #000555;");
        grid.add(returnButton, 0, 3);
        returnButton.setOnAction(e -> {
        	selectBusiness();
        	window.setScene(custSelectBus);
        });
		
		custSelectService = new Scene(grid, 500, 500);
	}
	
	public void selectDate(int busIndex, String service){
		
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
        datePicker.setValue(LocalDate.now());
        grid.add(datePicker, 2, 2);
        
        
        
        Button checkButton = new Button("Check");
        checkButton.setMinHeight(50);
        checkButton.setMinWidth(100);
        checkButton.setStyle("-fx-font: 22 arial; -fx-base: #000555;");
        grid.add(checkButton, 3, 3);
        checkButton.setOnAction(e -> {
            Business bus = businesses.get(busIndex);
        	
        	selectTime(busIndex ,bus, datePicker.getValue() , service);
        	window.setScene(custSelectTime);
        });
        
        Button returnButton = new Button("Back");
        returnButton.setMinHeight(50);
        returnButton.setMinWidth(100);
        returnButton.setStyle("-fx-font: 22 arial; -fx-base: #000555;");
        grid.add(returnButton, 0, 3);
        returnButton.setOnAction(e -> {
    		selectService(busIndex);
    		window.setScene(custSelectService);
        });
        
        
        custSelectDate = new Scene(grid, 500, 500);
        
	}
		
	public void selectTime(int busIndex, Business bus, LocalDate date, String service){
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(30, 30, 30, 30));
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        
        Text header = new Text("Select a Time");
        header.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
        grid.add(header, 2, 0);
        
        ListView<String> timeList = new ListView<String>();
        
    	//Show times here based on bus opening, closing hour and time slots
    	LocalTime openTime, closeTime;
    	int timeSlot;
    	System.out.println(bus);
    	System.out.println(bus.getOpenTime());
    	openTime = bus.getOpenTime();
    	closeTime = bus.getCloseTime();
    	timeSlot = bus.getTimeSlotInMins();
    	int noOfTimeSlots = bus.getServices().get(service);
    	//uhm, get number of timeslots for each service from business
    	//service needs to be selected before time is shown.
    	
    	//do the service after selecting the business
    	
    	ArrayList<LocalTime> timeSlots = new ArrayList<LocalTime>();
    	int i = 0;
    	do{
    		timeSlots.add(openTime.plusMinutes(i*timeSlot));
    		i++;
    	}while(!openTime.plusMinutes(i*timeSlot+noOfTimeSlots*timeSlot).isAfter(closeTime));
    	
        ObservableList<String>timeItems = FXCollections.observableArrayList();
        for(LocalTime times : timeSlots){
        	timeItems.add("Start time : " + times + " | End time : " + times.plusMinutes(timeSlot*noOfTimeSlots));
        }
        timeList.setItems(timeItems);
        
        timeList.setPrefHeight(300);
        timeList.setPrefWidth(300);
        
        grid.add(timeList, 2,2);
        
        Button selectButton = new Button("Select");
        selectButton.setMinHeight(50);
        selectButton.setMinWidth(100);
        selectButton.setStyle("-fx-font: 22 arial; -fx-base: #000555;");
        grid.add(selectButton, 3, 3);
        selectButton.setOnAction(e -> {
        	int timeIndex = timeList.getSelectionModel().getSelectedIndex();
        	if(timeIndex != -1){
        		LocalTime selectedTime = timeSlots.get(timeIndex);
        		selectEmployee(busIndex, bus, service, date, selectedTime); //add interval needed for specific services
        		window.setScene(custSelectEmp);
        		
        	}
        });
        
        Button returnButton = new Button("Back");
        returnButton.setMinHeight(50);
        returnButton.setMinWidth(100);
        returnButton.setStyle("-fx-font: 22 arial; -fx-base: #000555;");
        grid.add(returnButton, 0, 3);
        returnButton.setOnAction(e -> {
    		selectDate(busIndex, service);
    		window.setScene(custSelectDate);
        });
        
        custSelectTime = new Scene(grid, 500, 500);
        
	}
	
	public void selectEmployee(int busIndex, Business bus, String service, LocalDate date, LocalTime startTime){ //add interval needed for specific whatever... services? sure
		//Check bus employee list
		//Check their availability for the slots needed, use the no of slots for each service too
		//TODO
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(30, 30, 30, 30));
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        
        Text header = new Text("Select an Employee:");
        header.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
        grid.add(header, 0, 1,2,1);
        
        ArrayList<Employee> emps = new ArrayList<Employee>();
        for(Employee emp : bus.getEmps()){
        	if(emp.empFree(date, startTime, service)){
        		emps.add(emp);
        	}
        }
        
        Label empName = new Label("Employee Name: ");
        grid.add(empName, 0, 2);
        
        ArrayList<String> empNames = new ArrayList<String>();
        for(Employee emp : emps){
        	empNames.add(emp.getName());
        }
        ObservableList<String> empItems = FXCollections.observableArrayList(empNames);
        
        ChoiceBox<String> cb = new ChoiceBox<String>();
        cb.setItems(empItems);
        cb.setValue(empItems.get(0));
        cb.setTooltip(new Tooltip("Select employee"));
        grid.add(cb, 1, 2);
        
        
        Button selectButton = new Button("Select");
        selectButton.setMinHeight(50);
        selectButton.setMinWidth(100);
        selectButton.setStyle("-fx-font: 22 arial; -fx-base: #000555;");
        grid.add(selectButton, 3, 3);
        selectButton.setOnAction(e -> {
        	Employee myEmp = emps.get(cb.getSelectionModel().getSelectedIndex());
        	int bookingLen = bus.getServices().get(service)*bus.getTimeSlotInMins();
        	bookings.add(new Booking(date, startTime, startTime.plusMinutes(bookingLen), (Customer)userInst ,bus, myEmp));
        	myEmp.bookEmp(date, startTime, service);
        	FIO.saveBus(businesses);
        	FIO.saveBook(bookings);
    		customerMenu();
    		window.setScene(customerMenu);
    		//SET BOOLEANS TO TRUE FOR EMP
    		//TODO
    		//Success or fail alerts?
        });
        
        Button returnButton = new Button("Back");
        returnButton.setMinHeight(50);
        returnButton.setMinWidth(100);
        returnButton.setStyle("-fx-font: 22 arial; -fx-base: #000555;");
        grid.add(returnButton, 0, 3);
        returnButton.setOnAction(e -> {
        	selectTime(busIndex ,bus, date , service);
        	window.setScene(custSelectTime);
        });
        
        custSelectEmp = new Scene(grid, 500, 500);
		
	}
	
		//End Customer Add Booking Stuff
	
	@SuppressWarnings("unchecked")
	public void showBookingSummary() {
		

		GridPane grid = new GridPane();
		grid.setPadding(new Insets(30, 30, 30, 30));
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);

		Text header = new Text("Your Summary");
		header.setFont(Font.font("Rockwell", FontWeight.NORMAL, 40));
		grid.add(header, 3, 1);
		
		
		TableView<Booking> table = new TableView<Booking>();
		ObservableList<Booking> bookItems = getCustomerBookings((Customer)userInst, bookings);
		
		//Business Column
		TableColumn<Booking,Business> business =  new TableColumn<>("Business");
		business.setMinWidth(50);
		business.setCellValueFactory(new PropertyValueFactory<>("bookBus"));
		
		//Date Column
		TableColumn<Booking,LocalDate> bookingDate =  new TableColumn<>("Date");
		bookingDate.setMinWidth(50);
		bookingDate.setCellValueFactory(new PropertyValueFactory<>("bookDate"));
		
		//Session start column
		TableColumn<Booking,LocalTime> sessionStart =  new TableColumn<>("Session Start");
		sessionStart.setMinWidth(50);
		sessionStart.setCellValueFactory(new PropertyValueFactory<>("startTime"));
				
		//Session ends column
		TableColumn<Booking,LocalTime> sessionEnd =  new TableColumn<>("Session End");
		sessionEnd.setMinWidth(50);
		sessionEnd.setCellValueFactory(new PropertyValueFactory<>("endTime"));
		
		//Employee
		TableColumn<Booking, Employee> emp =  new TableColumn<>("Employee");
		emp.setMinWidth(50);
		emp.setCellValueFactory(new PropertyValueFactory<>("bookEmp"));
		
		
		table.setItems(bookItems);
		table.getColumns().addAll(business, bookingDate, sessionStart, sessionEnd, emp);
		
		grid.add(table, 3, 3);
		
		
		Button cancelButton = new Button("Cancel a Booking");
		cancelButton.minHeight(50);
		cancelButton.minWidth(100);
		cancelButton.setStyle("-fx-font: 22 verdana; -fx-base: #000555;");
		grid.add(cancelButton,  4,  5);
		cancelButton.setOnAction(e -> {
			
			if(table.getSelectionModel().getSelectedIndex() != -1){
				Booking bookInst = table.getSelectionModel().getSelectedItem();
				if(((Customer) userInst).cancelBooking(bookings, bookInst))
					FIO.saveBook(bookings);
			}
		});
		
		Button backToMenuButton = new Button("Go back to menu");
		HBox hbBackToMenuButton = new HBox(10);
		hbBackToMenuButton.setAlignment(Pos.BOTTOM_RIGHT);
		backToMenuButton.minHeight(50);
		backToMenuButton.minWidth(100);
		backToMenuButton.setStyle("-fx-font: 22 verdana; -fx-base: #000555;");
		hbBackToMenuButton.getChildren().add(backToMenuButton);
		grid.add(hbBackToMenuButton, 3, 5);

		backToMenuButton.setOnAction(e -> {
			customerMenu();
			window.setScene(customerMenu);
		});
		
		customerBookingSummary = new Scene(grid, 500, 500);

	}
	
	
	/*
	 * END CUSTOMER STUFF
	 */
	
	
	
	
	
	
	/*
	 * BUSINESS STUFF
	 */
	
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
        busAddEmp.setOnAction(e -> {
        	addEmp((Business)userInst);
        	window.setScene(busAddEmpSc);
        });
        
    
        Button busWorkingTime = new Button("Add Working Time");
        HBox hbBusWorkingTime = new HBox(10);
        hbBusWorkingTime.setAlignment(Pos.CENTER);
        busWorkingTime.setMinWidth(150);
        busWorkingTime.setMinHeight(25);
        busWorkingTime.setStyle("-fx-font: 10 verdana; -fx-base: #79B8FF;");
        hbBusWorkingTime.getChildren().add(busWorkingTime);
        grid3.add(hbBusWorkingTime, 0, 2);
        busWorkingTime.setOnAction(e -> {
        	busSelectEmp((Business)userInst);
        	window.setScene(busSelectEmp);
        });
        
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
	
	public void addEmp(Business bus){
        GridPane grid2 = new GridPane();
    	grid2.setPadding(new Insets(30, 30, 30, 30));
    	grid2.setAlignment(Pos.CENTER);
    	grid2.setHgap(20);
    	grid2.setVgap(20);
    	
        
        Text registerTitle = new Text("Add new employee:");
        registerTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
        grid2.add(registerTitle, 0, 1,2, 1);
        
        Label empName = new Label("Employee Name: ");
        grid2.add(empName, 0, 2);

        TextField empNameText = new TextField();
        empNameText.setPromptText("employee name");
        grid2.add(empNameText, 1, 2);
            
        Button register = new Button("Add!");
        HBox hbRegister = new HBox(10);
        hbRegister.setAlignment(Pos.BOTTOM_RIGHT);
        register.setMinWidth(80);
        register.setMinHeight(40);
        register.setStyle("-fx-font: 15 verdana; -fx-base: #79B8FF;");
        hbRegister.getChildren().add(register);
        grid2.add(hbRegister, 1, 3);
        register.setOnAction(e -> {
        	String empNameString = empNameText.getText();
        	//TODO
        	//check if empNameString is empty?
        	bus.addNewEmp(empNameString);
        	FIO.saveBus(businesses);
    		businessMenu();
    		window.setScene(businessMenu);
        });
            
        Button back = new Button("Back");
        HBox hbBack = new HBox(10);
        hbBack.setAlignment(Pos.BOTTOM_LEFT);
        back.setMinWidth(80);
        back.setMinHeight(40);
        back.setStyle("-fx-font: 15 verdana; -fx-base: #000555;");
        hbBack.getChildren().add(back);
        grid2.add(hbBack, 0, 3);
        
        back.setOnAction(e -> {
    		businessMenu();
    		window.setScene(businessMenu);
        });
        
        busAddEmpSc = new Scene(grid2, 300, 300);
	}
	
	
	public void busSelectEmp(Business bus){
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(30, 30, 30, 30));
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        //TODO
        //CHECK HERE IF BUS DOES NOT HAVE ANY EMPLOYEES
        Text header = new Text("Select an Employee:");
        header.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
        grid.add(header, 0, 1,2,1);
        
        Label empName = new Label("Employee Name: ");
        grid.add(empName, 0, 2);
        
        ArrayList<Employee> emps = bus.getEmps();
        ArrayList<String> empNames = new ArrayList<String>();
        for(Employee emp : emps){
        	empNames.add(emp.getName());
        }
        ObservableList<String> empItems = FXCollections.observableArrayList(empNames);
        
        ChoiceBox<String> cb = new ChoiceBox<String>();
        cb.setItems(empItems);
        cb.setValue(empItems.get(0));
        cb.setTooltip(new Tooltip("Select employee"));
        grid.add(cb, 1, 2);
        
        
        Button checkButton = new Button("Check");
        HBox hbCheck = new HBox(10);
        hbCheck.setAlignment(Pos.BOTTOM_RIGHT);
        checkButton.setMinHeight(40);
        checkButton.setMinWidth(80);
        checkButton.setStyle("-fx-font: 15 verdana; -fx-base: #79B8FF;");
        hbCheck.getChildren().add(checkButton);
        grid.add(hbCheck, 1,3);
        checkButton.setOnAction(e -> {
        	Employee myEmp = emps.get(cb.getSelectionModel().getSelectedIndex());
        	addWorkTime(myEmp , bus);
        	window.setScene(busAddWorkTime);
        });
        
        Button back = new Button("Back");
        HBox hbBack = new HBox(10);
        hbBack.setAlignment(Pos.BOTTOM_LEFT);
        back.setMinWidth(80);
        back.setMinHeight(40);
        back.setStyle("-fx-font: 15 verdana; -fx-base: #000555;");
        hbBack.getChildren().add(back);
        grid.add(hbBack, 0, 3);
        back.setOnAction(e -> {
    		businessMenu();
    		window.setScene(businessMenu);
        });
        
        
        busSelectEmp = new Scene(grid, 300, 300);
	}
	
	public void addWorkTime(Employee emp, Business bus){
		//add to employees hm HashMap<LocalDate, HashMap<LocalTime, Boolean>>
		//Weekly? by day?
		//select time or user input?
		//By day
		//Will update schedule by one month
		GridPane grid2 = new GridPane();
    	grid2.setPadding(new Insets(30, 30, 30, 30));
    	grid2.setAlignment(Pos.CENTER);
    	grid2.setHgap(20);
    	grid2.setVgap(20);
    	
    	

    	LocalDate currentDate = LocalDate.now();
		currentDate.getDayOfWeek();
        
        Text registerTitle = new Text("Add working time for employee | " + emp.getName());
        registerTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
        grid2.add(registerTitle, 0, 1,2, 1);
        
        //Monday
        grid2.add(new Label("-Monday-"), 0, 2);
        Label monStartTime = new Label("Start time : ");
        grid2.add(monStartTime, 0, 3);
        TextField monStartTimeText = new TextField();
        monStartTimeText.setPromptText("HH:mm");
        grid2.add(monStartTimeText, 1, 3);
        
        Label monEndTime = new Label("End time : ");
        grid2.add(monEndTime, 2, 3);
        TextField monEndTimeText = new TextField();
        monEndTimeText.setPromptText("HH:mm");
        grid2.add(monEndTimeText, 3, 3);
        
        //Tuesday
        grid2.add(new Label("-Tuesday-"), 0, 4);
        Label tueStartTime = new Label("Start time : ");
        grid2.add(tueStartTime, 0, 5);
        TextField tueStartTimeText = new TextField();
        tueStartTimeText.setPromptText("HH:mm");
        grid2.add(tueStartTimeText, 1, 5);
        
        Label tueEndTime = new Label("End time : ");
        grid2.add(tueEndTime, 2, 5);
        TextField tueEndTimeText = new TextField();
        tueEndTimeText.setPromptText("HH:mm");
        grid2.add(tueEndTimeText, 3, 5);
        
        //Wednesday
        grid2.add(new Label("-Wednesday-"), 0, 6);
        Label wedStartTime = new Label("Start time : ");
        grid2.add(wedStartTime, 0, 7);
        TextField wedStartTimeText = new TextField();
        wedStartTimeText.setPromptText("HH:mm");
        grid2.add(wedStartTimeText, 1, 7);
        
        Label wedEndTime = new Label("End time : ");
        grid2.add(wedEndTime, 2, 7);
        TextField wedEndTimeText = new TextField();
        wedEndTimeText.setPromptText("HH:mm");
        grid2.add(wedEndTimeText, 3, 7);
        
        //Thursday
        grid2.add(new Label("-Thursday-"), 0, 8);
        Label thurStartTime = new Label("Start time : ");
        grid2.add(thurStartTime, 0, 9);
        TextField thurStartTimeText = new TextField();
        thurStartTimeText.setPromptText("HH:mm");
        grid2.add(thurStartTimeText, 1, 9);
        
        Label thurEndTime = new Label("End time : ");
        grid2.add(thurEndTime, 2, 9);
        TextField thurEndTimeText = new TextField();
        thurEndTimeText.setPromptText("HH:mm");
        grid2.add(thurEndTimeText, 3, 9);
        
        //Friday
        grid2.add(new Label("-Friday-"), 0, 10);
        Label friStartTime = new Label("Start time : ");
        grid2.add(friStartTime, 0, 11);
        TextField friStartTimeText = new TextField();
        friStartTimeText.setPromptText("HH:mm");
        grid2.add(friStartTimeText, 1, 11);
        
        Label friEndTime = new Label("End time : ");
        grid2.add(friEndTime, 2, 11);
        TextField friEndTimeText = new TextField();
        friEndTimeText.setPromptText("HH:mm");
        grid2.add(friEndTimeText, 3, 11);
        
        //Saturday
        grid2.add(new Label("-Saturday-"), 0, 12);
        Label satStartTime = new Label("Start time : ");
        grid2.add(satStartTime, 0, 13);
        TextField satStartTimeText = new TextField();
        satStartTimeText.setPromptText("HH:mm");
        grid2.add(satStartTimeText, 1, 13);
        
        Label satEndTime = new Label("End time : ");
        grid2.add(satEndTime, 2, 13);
        TextField satEndTimeText = new TextField();
        satEndTimeText.setPromptText("HH:mm");
        grid2.add(satEndTimeText, 3, 13);

        //Sunday
        grid2.add(new Label("-Sunday-"), 0, 14);
        Label sunStartTime = new Label("Start time : ");
        grid2.add(sunStartTime, 0, 15);
        TextField sunStartTimeText = new TextField();
        sunStartTimeText.setPromptText("HH:mm");
        grid2.add(sunStartTimeText, 1, 15);
        
        Label sunEndTime = new Label("End time : ");
        grid2.add(sunEndTime, 2, 15);
        TextField sunEndTimeText = new TextField();
        sunEndTimeText.setPromptText("HH:mm");
        grid2.add(sunEndTimeText, 3, 15);
        //TODO 
        //CHECK FORMAT OF INPUT "HHMM"
            
        Button register = new Button("Add!");
        HBox hbRegister = new HBox(10);
        hbRegister.setAlignment(Pos.BOTTOM_RIGHT);
        register.setMinWidth(80);
        register.setMinHeight(40);
        register.setStyle("-fx-font: 15 verdana; -fx-base: #79B8FF;");
        hbRegister.getChildren().add(register);
        grid2.add(hbRegister, 3, 16);
        
        register.setOnAction(e -> {
        	LocalTime monStart, monEnd, tueStart, tueEnd, wedStart, wedEnd, thurStart, thurEnd, 
        				friStart, friEnd, satStart, satEnd, sunStart, sunEnd;
        	HashMap<String,LocalTime> times = new HashMap<String,LocalTime>();
    		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        	String monStartTimeStr = monStartTimeText.getText();
        	String monEndTimeStr = monEndTimeText.getText();
        	String tueStartTimeStr = tueStartTimeText.getText();
        	String tueEndTimeStr = tueEndTimeText.getText();
        	String wedStartTimeStr = wedStartTimeText.getText();
        	String wedEndTimeStr = wedEndTimeText.getText();
        	String thurStartTimeStr = thurStartTimeText.getText();
        	String thurEndTimeStr = thurEndTimeText.getText();
        	String friStartTimeStr = friStartTimeText.getText();
        	String friEndTimeStr = friEndTimeText.getText();
        	String satStartTimeStr = satStartTimeText.getText();
        	String satEndTimeStr = satEndTimeText.getText();
        	String sunStartTimeStr = sunStartTimeText.getText();
        	String sunEndTimeStr = sunEndTimeText.getText();
    		monStart = LocalTime.parse(monStartTimeStr, dtf);
    		monEnd = LocalTime.parse(monEndTimeStr, dtf);
    		tueStart = LocalTime.parse(tueStartTimeStr, dtf);
    		tueEnd = LocalTime.parse(tueEndTimeStr, dtf);
    		wedStart = LocalTime.parse(wedStartTimeStr, dtf);
    		wedEnd = LocalTime.parse(wedEndTimeStr, dtf);
    		thurStart = LocalTime.parse(thurStartTimeStr, dtf);
    		thurEnd = LocalTime.parse(thurEndTimeStr, dtf);
    		friStart = LocalTime.parse(friStartTimeStr, dtf);
    		friEnd = LocalTime.parse(friEndTimeStr, dtf);
    		satStart = LocalTime.parse(satStartTimeStr, dtf);
    		satEnd = LocalTime.parse(satEndTimeStr, dtf);
    		sunStart = LocalTime.parse(sunStartTimeStr, dtf);
    		sunEnd = LocalTime.parse(sunEndTimeStr, dtf);
    		times.put("monStart", monStart);
    		times.put("monEnd", monEnd);
    		times.put("tueStart", tueStart);
    		times.put("tueEnd", tueEnd);
    		times.put("wedStart", wedStart);
    		times.put("wedEnd", wedEnd);
    		times.put("thurStart", thurStart);
    		times.put("thurEnd", thurEnd);
    		times.put("friStart", friStart);
    		times.put("friEnd", friEnd);
    		times.put("satStart", satStart);
    		times.put("satEnd", satEnd);
    		times.put("sunStart", sunStart);
    		times.put("sunEnd", sunEnd);
        	
    		emp.updateSchedule(times);
        	
        	//TODO
    		//CHECK INPUTS FOR WRONG/EMPTY strings

        	FIO.saveBus(businesses);
    		businessMenu();
    		window.setScene(businessMenu);
        	
        });
            
        Button back = new Button("Back");
        HBox hbBack = new HBox(10);
        hbBack.setAlignment(Pos.BOTTOM_LEFT);
        back.setMinWidth(80);
        back.setMinHeight(40);
        back.setStyle("-fx-font: 15 verdana; -fx-base: #000555;");
        hbBack.getChildren().add(back);
        grid2.add(hbBack, 0, 16);
        back.setOnAction(e -> {
        	busSelectEmp((Business)userInst);
        	window.setScene(busSelectEmp);
        });
        
        busAddWorkTime = new Scene(grid2, 700, 600);
	}
	
	/*
	 * END BUSINESS STUFF
	 */
       
	public void handleSuccess(Stage window) {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(window);
        GridPane dialogVbox = new GridPane();
        
        dialogVbox.setPadding(new Insets(30, 30, 30, 30));
        dialogVbox.setHgap(10);
        dialogVbox.setVgap(5);
        Text successful = new Text("You have successfully registered!");
        successful.setFont(Font.font("Rockwell", FontWeight.NORMAL, 15));
        successful.setTextAlignment(TextAlignment.CENTER);
        dialogVbox.add(successful, 0, 1);
        
        Button back = new Button("Return");
        HBox hbBack = new HBox(15);
        hbBack.setAlignment(Pos.BASELINE_CENTER);
        back.setMinWidth(100);
        back.setMinHeight(20);
        back.setStyle("-fx-font: 10 verdana; -fx-base: #B7FF6E;");
        dialogVbox.getChildren().add(hbBack);
        dialogVbox.add(back, 0, 4);
        back.setOnAction(e -> {
        	showMainMenu();
        	window.setScene(mainMenu);
        	((Node)(e.getSource())).getScene().getWindow().hide();
        });
        GridPane.setHalignment(back, HPos.CENTER);
        dialogVbox.setAlignment(Pos.CENTER);
        
        Scene dialogScene = new Scene(dialogVbox, 300, 100);
        dialog.setScene(dialogScene);
        dialog.show();
    }
	
	public void handleFail(Stage window, Boolean checkUser, Boolean checkPassLength, Boolean checkPassword1, Boolean checkPhone) {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(window);
        GridPane dialogVbox = new GridPane();
        
        dialogVbox.setPadding(new Insets(30, 30, 30, 30));
        dialogVbox.setHgap(10);
        dialogVbox.setVgap(5);
        Text fail = new Text("Incorrect Registration Entry!");
        Text fail1 = new Text("Please amend the following errors:");
        fail.setFont(Font.font("Rockwell", FontWeight.NORMAL, 15));
        fail.setTextAlignment(TextAlignment.CENTER);
        fail1.setFont(Font.font("Rockwell", FontWeight.NORMAL, 15));
        fail1.setTextAlignment(TextAlignment.CENTER);
        dialogVbox.add(fail, 0, 1);
        dialogVbox.add(fail1, 0, 2);
        
        int i = 2;
        
        if (checkUser == false){
        	Text user = new Text("-  UserName already exists");
        	user.setFont(Font.font("Rockwell", FontWeight.NORMAL, 10));
        	user.setTextAlignment(TextAlignment.CENTER);
        	user.setFill(Color.RED);
            dialogVbox.add(user, 0, i+=1);
        }
        
        if (checkPassLength == false){
        	Text passlength = new Text("-  Password must be between 6-12 characters");
        	passlength.setFont(Font.font("Rockwell", FontWeight.NORMAL, 10));
        	passlength.setTextAlignment(TextAlignment.CENTER);
        	passlength.setFill(Color.RED);
            dialogVbox.add(passlength, 0, i+=1);
        }
        else if (checkPassLength == true){
        	if (checkPassword1 == false){
        		Text passCopy = new Text("-  Password doesn't match");
        		passCopy.setFont(Font.font("Rockwell", FontWeight.NORMAL, 10));
        		passCopy.setTextAlignment(TextAlignment.CENTER);
        		passCopy.setFill(Color.RED);
                dialogVbox.add(passCopy, 0, i+=1);
        	}
        }
        
        if (checkPhone == false){
        	Text phone = new Text("-  Incorrect phone format");
        	phone.setFont(Font.font("Rockwell", FontWeight.NORMAL, 10));
        	phone.setTextAlignment(TextAlignment.CENTER);
        	phone.setFill(Color.RED);
            dialogVbox.add(phone, 0, i+=1);
        }
        
        Button back = new Button("Return");
        HBox hbBack = new HBox(15);
        hbBack.setAlignment(Pos.BASELINE_CENTER);
        back.setMinWidth(100);
        back.setMinHeight(20);
        back.setStyle("-fx-font: 10 verdana; -fx-base: #B7FF6E;");
        dialogVbox.getChildren().add(hbBack);
        dialogVbox.add(back, 0, i+=1);
        back.setOnAction(e -> {
        	showMainMenu();
        	window.setScene(mainMenu);
        	((Node)(e.getSource())).getScene().getWindow().hide();
        });
        GridPane.setHalignment(back, HPos.CENTER);
        dialogVbox.setAlignment(Pos.CENTER);
        
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
    }
	
	

	public ObservableList<Booking> getCustomerBookings(Customer cust, ArrayList<Booking> bookings) {
		ObservableList<Booking> bookingsToBeViewed = FXCollections.observableArrayList();
		
		for(int i = 0; i < bookings.size(); i++){
			if(bookings.get(i).getBookCust().getUsername().equals(cust.getUsername())){
				bookingsToBeViewed.add(bookings.get(i));
			}
		}

		
		return bookingsToBeViewed;
	}
	

}
