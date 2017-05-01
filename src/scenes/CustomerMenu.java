package scenes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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

public class CustomerMenu extends SceneManager{
	
	Logger logger = Logger.getLogger(SceneManager.class);
	
	public CustomerMenu(ArrayList<Customer> customers, ArrayList<Business> businesses, Account account,
			ArrayList<Booking> bookings, Stage primaryStage) {
		super(customers, businesses, account, bookings, primaryStage);
		// TODO Auto-generated constructor stub

	}
	
	public void customerMenu(){
        GridPane grid3 = new GridPane();
    	grid3.setPadding(new Insets(30, 30, 30, 30));
    	grid3.setAlignment(Pos.CENTER);
    	grid3.setHgap(10);
    	grid3.setVgap(10);
//    	
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
        	logger.info("Customer picks add booking option");
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
        	logger.info("Customer picks show booking summary option");
          	showBookingSummary();
        	window.setScene(customerBookingSummary);
        });
        
        Button custLogOut = new Button("Logout");
        HBox hbCustLogOut = new HBox(10);
        hbCustLogOut.setAlignment(Pos.CENTER);
        custLogOut.setMinWidth(150);
        custLogOut.setMinHeight(25);
        custLogOut.setStyle("-fx-font: 10 verdana; -fx-base: #79B8FF;");
        hbCustLogOut.getChildren().add(custLogOut);
        grid3.add(hbCustLogOut, 0, 3);
        custLogOut.setOnAction(e -> {
        	logger.info("Customer picks logging out option");
        	menuScreen.showMainMenu();
        	window.setScene(mainMenu);
        	this.userInst = null;
        	});
        
        customerMenu = new Scene(grid3, 100, 200);
	}
		//Customer Add Booking Stuff
	public void selectBusiness(){
		logger.info("Entering business selection phase");
				
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(30, 30, 30, 30));
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        
        
        Text header = new Text("Select business:");
        header.setFont(Font.font("Rockwell", FontWeight.NORMAL, 35));
        grid.add(header, 0, 1,2, 1);
        
        ListView<String> busList = new ListView<String>(); 
        ObservableList<String>busItems = FXCollections.observableArrayList();
        for(Business business : businesses){
        	busItems.add(business.getBusName());
        }
        busList.setItems(busItems);
        
        busList.setPrefHeight(300);
        busList.setPrefWidth(300);
        
        grid.add(busList, 1,2);
        
        Button selectButton = new Button("Next");
        selectButton.setMinWidth(70);
        selectButton.setMinHeight(30);
        selectButton.setStyle("-fx-font: 15 verdana; -fx-base: #79B8FF;");
        grid.add(selectButton, 3, 3);
        selectButton.setOnAction(e -> {
        
        	int busIndex = busList.getSelectionModel().getSelectedIndex();
        	logger.info("Business option no:" + busIndex +" is selected");
        	if(busIndex != -1){
        		selectService(busIndex);
        		window.setScene(custSelectService);
        	}
        });
        
        GridPane.setHalignment(busList, HPos.CENTER);
        
        Button returnButton = new Button("Back");
        returnButton.setMinWidth(70);
        returnButton.setMinHeight(30);
        returnButton.setStyle("-fx-font: 15 verdana; -fx-base: #B7FF6E;");
        grid.add(returnButton, 0, 3);
        returnButton.setOnAction(e -> {
        	logger.info("Back to customer menu");
        	customerMenu();
        	window.setScene(customerMenu);
        });
        custSelectBus = new Scene(grid, 600, 500);
	}
	
	public void selectService(int busIndex){
		logger.info("Entering service selection phase");
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(30, 30, 30, 30));
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        
        Text header = new Text("Select service:");
        header.setFont(Font.font("Rockwell", FontWeight.NORMAL, 35));
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
        
        grid.add(serviceList, 1,2);
        
        Button selectButton = new Button("Next");
        selectButton.setMinWidth(70);
        selectButton.setMinHeight(30);
        selectButton.setStyle("-fx-font: 15 verdana; -fx-base: #79B8FF;");
        grid.add(selectButton, 3, 3);
        selectButton.setOnAction(e -> {
    		String service = serviceList.getSelectionModel().getSelectedItem();
    		logger.info("'"+ service +"'" + " service is selected");
    		selectDate(busIndex, service);
    		window.setScene(custSelectDate);
        });
        
        Button returnButton = new Button("Back");
        returnButton.setMinWidth(70);
        returnButton.setMinHeight(30);
        returnButton.setStyle("-fx-font: 15 verdana; -fx-base: #B7FF6E;");
        grid.add(returnButton, 0, 3);
        returnButton.setOnAction(e -> {
        	logger.info("Back to business selection phase ");
        	selectBusiness();
        	window.setScene(custSelectBus);
        });
		
		custSelectService = new Scene(grid, 600, 500);
	}
	
	public void selectDate(int busIndex, String service){
		
		logger.info("Entering date selection phase");
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(30, 30, 30, 30));
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        
        Text header = new Text("Select Date:");
        header.setFont(Font.font("Rockwell", FontWeight.NORMAL, 35));
        grid.add(header, 0, 1,2, 1);
        
        DatePicker datePicker = new DatePicker();
        
        /*
         * code based on https://docs.oracle.com/javase/8/javafx/user-interface-tutorial/date-picker.htm
         */
        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        
                        if (item.isBefore(LocalDate.now())||item.isAfter(LocalDate.now().plusDays(31))) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                };
            }
        };
        datePicker.setDayCellFactory(dayCellFactory);
        datePicker.setValue(LocalDate.now());
        grid.add(datePicker, 1, 2);
        
        
        Button checkButton = new Button("Check");
        checkButton.setMinWidth(70);
        checkButton.setMinHeight(30);
        checkButton.setStyle("-fx-font: 15 verdana; -fx-base: #79B8FF;");
        grid.add(checkButton, 3, 3);
        checkButton.setOnAction(e -> {
            Business bus = businesses.get(busIndex);
            logger.info("Date : " + datePicker.getValue() + "is selected");
        	selectTime(busIndex ,bus, datePicker.getValue() , service);
        	window.setScene(custSelectTime);
        });
        
        Button returnButton = new Button("Back");
        returnButton.setMinWidth(70);
        returnButton.setMinHeight(30);
        returnButton.setStyle("-fx-font: 15 verdana; -fx-base: #B7FF6E;");
        grid.add(returnButton, 0, 3);
        returnButton.setOnAction(e -> {
        	logger.info("back to service selection phase");
    		selectService(busIndex);
    		window.setScene(custSelectService);
        });
        
        
        custSelectDate = new Scene(grid, 600, 500);
        
	}
		
	public void selectTime(int busIndex, Business bus, LocalDate date, String service){
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(30, 30, 30, 30));
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        
        Text header = new Text("Select time:");
        header.setFont(Font.font("Rockwell", FontWeight.NORMAL, 35));
        grid.add(header, 0, 1,2, 1);
        
        ListView<String> timeList = new ListView<String>();
        
    	//Show times here based on bus opening, closing hour and time slots
    	LocalTime openTime, closeTime;
    	long timeSlot;
    	logger.info(bus);
    	logger.info(bus.getOpenTime());
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
        
        Button selectButton = new Button("Next");
        selectButton.setMinWidth(70);
        selectButton.setMinHeight(30);
        selectButton.setStyle("-fx-font: 15 verdana; -fx-base: #79B8FF;");
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
        returnButton.setMinWidth(70);
        returnButton.setMinHeight(30);
        returnButton.setStyle("-fx-font: 15 verdana; -fx-base: #B7FF6E;");
        grid.add(returnButton, 0, 3);
        returnButton.setOnAction(e -> {
    		selectDate(busIndex, service);
    		window.setScene(custSelectDate);
        });
        
        custSelectTime = new Scene(grid,600, 500);
        
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
        
        Text header = new Text("Select Employee:");
        header.setFont(Font.font("Rockwell", FontWeight.NORMAL, 35));
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
        
        Button selectButton = new Button("Next");
        selectButton.setMinWidth(70);
        selectButton.setMinHeight(30);
        selectButton.setStyle("-fx-font: 15 verdana; -fx-base: #79B8FF;");
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
        returnButton.setMinWidth(70);
        returnButton.setMinHeight(30);
        returnButton.setStyle("-fx-font: 15 verdana; -fx-base: #B7FF6E;");
        grid.add(returnButton, 0, 3);
        returnButton.setOnAction(e -> {
        	selectTime(busIndex ,bus, date , service);
        	window.setScene(custSelectTime);
        });
        
        custSelectEmp = new Scene(grid, 600, 500);
	}
	
		//End Customer Add Booking Stuff
	
	@SuppressWarnings("unchecked")
	public void showBookingSummary() {

		GridPane grid = new GridPane();
		grid.setPadding(new Insets(30, 30, 30, 30));
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);

		Text header = new Text("Your Summary:");
		header.setFont(Font.font("Rockwell", FontWeight.NORMAL, 40));
		grid.add(header, 0, 1,2, 1);
		
		
		TableView<Booking> table = new TableView<Booking>();
		ObservableList<Booking> bookItems = userInst.viewBookingSummary(bookings);
		
		//Business Column
		TableColumn<Booking,String> business =  new TableColumn<>("Business");
		business.setMinWidth(50);
		business.setCellValueFactory(new PropertyValueFactory<>("strBus"));
		
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
		TableColumn<Booking, String> emp =  new TableColumn<>("Employee");
		emp.setMinWidth(50);
		emp.setCellValueFactory(new PropertyValueFactory<>("strEmp"));
		
		
		table.setItems(bookItems);
		table.getColumns().addAll(business, bookingDate, sessionStart, sessionEnd, emp);
		table.setPlaceholder(new Label("You Currently Have no Bookings"));
		
		grid.add(table, 0, 3,6, 1);
		GridPane.setHalignment(table, HPos.CENTER);
		
		Button cancelButton = new Button("Cancel Booking");
		cancelButton.minHeight(30);
		cancelButton.minWidth(70);
		cancelButton.setStyle("-fx-font: 15 verdana; -fx-base: #B7FF6E;");
		grid.add(cancelButton,  5,  5);
		cancelButton.setOnAction(e -> {
			
			if(table.getSelectionModel().getSelectedIndex() != -1){
				Booking bookInst = table.getSelectionModel().getSelectedItem();
				Employee empInst;
				
				for(int i = 0; i < businesses.size(); i++){
					if(bookInst.getBookBus().equals(businesses.get(i).getBusName())){
						ArrayList<Employee>emps = businesses.get(i).getEmps();
						for(int j = 0; j < emps.size(); j++){
							if(bookInst.getBookEmp().equals(emps.get(i).getName())){
								empInst = emps.get(i);
							}
						}
					}
						
				}
				
				if(((Customer) userInst).cancelBooking(bookings, bookInst)){
					
					FIO.saveBook(bookings);
				}
				logger.info("A booking has been cancelled, summary is updated");

				showBookingSummary();
				window.setScene(customerBookingSummary);
			}

		});
		
		Button backToMenuButton = new Button("Return to Menu");
		HBox hbBackToMenuButton = new HBox(10);
		hbBackToMenuButton.setAlignment(Pos.BOTTOM_RIGHT);
		backToMenuButton.minHeight(30);
		backToMenuButton.minWidth(70);
        backToMenuButton.setStyle("-fx-font: 15 verdana; -fx-base: #79B8FF;");
		hbBackToMenuButton.getChildren().add(backToMenuButton);
		grid.add(hbBackToMenuButton, 0, 5);

		backToMenuButton.setOnAction(e -> {
			logger.info("Back to customer menu");
			customerMenu();
			window.setScene(customerMenu);
		});
		
		customerBookingSummary = new Scene(grid, 600, 500);

	}
}
