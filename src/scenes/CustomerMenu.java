package scenes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

public class CustomerMenu extends SceneManager{
	
	
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
        
        Button custLogOut = new Button("Logout");
        HBox hbCustLogOut = new HBox(10);
        hbCustLogOut.setAlignment(Pos.CENTER);
        custLogOut.setMinWidth(150);
        custLogOut.setMinHeight(25);
        custLogOut.setStyle("-fx-font: 10 verdana; -fx-base: #79B8FF;");
        hbCustLogOut.getChildren().add(custLogOut);
        grid3.add(hbCustLogOut, 0, 3);
        custLogOut.setOnAction(e -> {
        	menuScreen.showMainMenu();
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
        grid.add(selectButton, 1, 3);
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
        grid.add(returnButton, 3, 3);
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
        grid.add(selectButton, 1, 3);
        selectButton.setOnAction(e -> {
    		String service = serviceList.getSelectionModel().getSelectedItem();
    		selectDate(busIndex, service);
    		window.setScene(custSelectDate);
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
        grid.add(datePicker, 2, 2);
        
        
        
        Button checkButton = new Button("Check");
        checkButton.setMinHeight(50);
        checkButton.setMinWidth(100);
        checkButton.setStyle("-fx-font: 22 arial; -fx-base: #000555;");
        grid.add(checkButton, 1, 3);
        checkButton.setOnAction(e -> {
            Business bus = businesses.get(busIndex);
        	
        	selectTime(busIndex ,bus, datePicker.getValue() , service);
        	window.setScene(custSelectTime);
        });
        
        Button returnButton = new Button("Back");
        returnButton.setMinHeight(50);
        returnButton.setMinWidth(100);
        returnButton.setStyle("-fx-font: 22 arial; -fx-base: #000555;");
        grid.add(returnButton, 3, 3);
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
    	long timeSlot;
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
        grid.add(selectButton, 1, 3);
        selectButton.setOnAction(e -> {
        	int timeIndex = timeList.getSelectionModel().getSelectedIndex();
        	if(timeIndex != -1){
        		LocalTime selected = timeSlots.get(timeIndex);
        		selectEmployee(selected); //add interval needed for specific services
        		window.setScene(custSelectEmp);
        		
        	}
        });
        
        Button returnButton = new Button("Back");
        returnButton.setMinHeight(50);
        returnButton.setMinWidth(100);
        returnButton.setStyle("-fx-font: 22 arial; -fx-base: #000555;");
        grid.add(returnButton, 3, 3);
        returnButton.setOnAction(e -> {
    		selectDate(busIndex, service);
    		window.setScene(custSelectDate);
        });
        
        custSelectTime = new Scene(grid, 500, 500);
        
	}
	
	public void selectEmployee(LocalTime time){ //add interval needed for specific whatever... services? sure
		//Check bus employee list
		//Check their availability for the slots needed, use the no of slots for each service too
		ArrayList<Booking> bookings = null;
		
		/*
		 * Methods to save a booking
		 * 
		 * boolean cust.bookSession(LocalDate date, LocalTime sessionStart, LocalTime sessionEnd, Customer cust, Business busInst ,Employee emp, ArrayList<Booking> bookings);
		 * FIO.saveBook(bookings);
		 * 
		 */

		
		
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
		ObservableList<Booking> bookItems = userInst.viewBookingSummary(bookings);
		
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
}
