package scenes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.*;
import system.Account;
import system.Booking;
import users.Business;
import users.Customer;
import users.Employee;

public class BusinessMenu extends SceneManager{
	String empNamePattern = "^[a-zA-Z ]*$";
	Business busInst;
	
	public BusinessMenu(ArrayList<Customer> customers, ArrayList<Business> businesses, Account account,
			ArrayList<Booking> bookings, Stage primaryStage) {
		super(customers, businesses, account, bookings, primaryStage);
		
	}
	
	public void businessMenu(Business busInst){
		this.busInst = busInst;
        GridPane grid3 = new GridPane();
    	grid3.setPadding(new Insets(30, 30, 30, 30));
    	grid3.setAlignment(Pos.CENTER);
    	grid3.setHgap(10);
    	grid3.setVgap(10);
        
    	Text busTitle = new Text("Welcome --" + busInst.getName() + "--");
    	busTitle.setFont(Font.font("Rockwell", FontWeight.NORMAL, 35));
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
        	addEmp(busInst);
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
        	busSelectEmp(busInst);
        	window.setScene(busSelectEmp);
        });
        
        Button busChangeWorkTime = new Button("Change Opening Hours");
        HBox hbBusChangeWorkTime = new HBox(10);
        hbBusChangeWorkTime.setAlignment(Pos.CENTER);
        busChangeWorkTime.setMinWidth(150);
        busChangeWorkTime.setMinHeight(25);
        busChangeWorkTime.setStyle("-fx-font: 10 verdana; -fx-base: #79B8FF;");
        hbBusChangeWorkTime.getChildren().add(busChangeWorkTime);
        grid3.add(hbBusChangeWorkTime, 0, 3);
        busChangeWorkTime.setOnAction(e -> {
        	busChangeHours(busInst);
        	window.setScene(busChangeHours);
        });
        
        Button busViewSum = new Button("View Booking Summary");
        HBox hbBusViewSum = new HBox(10);
        hbBusViewSum.setAlignment(Pos.CENTER);
        busViewSum.setMinWidth(150);
        busViewSum.setMinHeight(25);
        busViewSum.setStyle("-fx-font: 10 verdana; -fx-base: #79B8FF;");
        hbBusViewSum.getChildren().add(busViewSum);
        grid3.add(hbBusViewSum, 0, 9); //PLEASE REPOSITION
        busViewSum.setOnAction(e -> {
        	busViewSummary(busInst);
        	window.setScene(busViewSummary);
        });
        
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
        grid3.add(hbBusShowAvail, 0, 5);
        busShowAvail.setOnAction(e ->{
        	busShowAvailability(busInst);
        	window.setScene(busShowAvailable);
        });
        
        Button busEditServe = new Button("Edit Services");
        HBox hbBusEditServe = new HBox(10);
        hbBusEditServe.setAlignment(Pos.CENTER);
        busEditServe.setMinWidth(150);
        busEditServe.setMinHeight(25);
        busEditServe.setStyle("-fx-font: 10 verdana; -fx-base: #79B8FF;");
        hbBusEditServe.getChildren().add(busEditServe);
        grid3.add(hbBusEditServe, 0, 6);
        busEditServe.setOnAction(e -> {
        	busShowServices();
        	window.setScene(busShowServices);
        });
        
        Button busLogOut = new Button("Logout");
        HBox hbBusLogOut = new HBox(10);
        hbBusLogOut.setAlignment(Pos.CENTER);
        busLogOut.setMinWidth(150);
        busLogOut.setMinHeight(25);
        busLogOut.setStyle("-fx-font: 10 verdana; -fx-base: #79B8FF;");
        hbBusLogOut.getChildren().add(busLogOut);
        grid3.add(hbBusLogOut, 0, 7);
        busLogOut.setOnAction(e -> {
        	menuScreen.showMainMenu();
        	window.setScene(mainMenu);
        	userInst = null;
        	this.busInst = null;
        	});
        
        businessMenu = new Scene(grid3, 200, 400);
	}
	
	public void busShowAvailability(Business bus){
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(30, 30, 30, 30));
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		
		Text header = new Text("Worker Hours");
		header.setFont(Font.font("Rockwell", FontWeight.NORMAL, 40));
		grid.add(header, 0, 1, 2, 1);
		
		TableView<Employee>empTable = new TableView<Employee>();
		ObservableList<Employee>empList = FXCollections.observableArrayList();
		ArrayList<Employee>empArray = bus.getEmps();
		
		for(int i = 0; i < empArray.size(); i++){
			empList.add(empArray.get(i));
		}
		
		//Employee names
		TableColumn<Employee, String> empName = new TableColumn<>("Name");
		empName.setMinWidth(50);
		empName.setCellValueFactory(new PropertyValueFactory<>("name"));
	
		//Day 1
		TableColumn<Employee, String> day1 = new TableColumn<>("Monday");
		day1.setMinWidth(50);
		day1.setCellValueFactory(new PropertyValueFactory<>("monHour"));
		
		//Day 2
		TableColumn<Employee, String> day2 = new TableColumn<>("Tuesday");
		day2.setMinWidth(50);
		day2.setCellValueFactory(new PropertyValueFactory<>("tueHour"));
		
		//Day 3
		TableColumn<Employee, String> day3 = new TableColumn<>("Wednesday");
		day3.setMinWidth(50);
		day3.setCellValueFactory(new PropertyValueFactory<>("wedHour"));
		
		//Day 4
		
		TableColumn<Employee, String> day4 = new TableColumn<>("Thursday");
		day4.setMinWidth(50);
		day4.setCellValueFactory(new PropertyValueFactory<>("thurHour"));
		//Day 5
		TableColumn<Employee, String> day5 = new TableColumn<>("Friday");
		day5.setMinWidth(50);
		day5.setCellValueFactory(new PropertyValueFactory<>("friHour"));
		
		//Day 6
		TableColumn<Employee, String> day6 = new TableColumn<>("Saturday");
		day6.setMinWidth(50);
		day6.setCellValueFactory(new PropertyValueFactory<>("satHour"));
		
		//Day 7
		TableColumn<Employee, String> day7 = new TableColumn<>("Sunday");
		day7.setMinWidth(50);
		day7.setCellValueFactory(new PropertyValueFactory<>("sunHour"));
		
		empTable.setItems(empList);
		empTable.getColumns().addAll(empName, day1, day2, day3, day4, day5, day6, day7);
		empTable.setPlaceholder(new Label("There are currently no employees"));
		
		grid.add(empTable, 0, 3,6, 1);
		GridPane.setHalignment(empTable, HPos.CENTER);
		
		Button accept = new Button("Select");
    	HBox hbAccept = new HBox(10);
    	hbAccept.setAlignment(Pos.BOTTOM_RIGHT);
    	accept.setMinWidth(70);
    	accept.setMinHeight(30);
    	accept.setStyle("-fx-font: 15 verdana; -fx-base: #B7FF6E;");
        hbAccept.getChildren().add(accept);
        grid.add(hbAccept, 6, 5);
        accept.setOnAction(e -> {
        	if(empTable.getSelectionModel().getSelectedIndex() != -1){
        	empAvail(empTable.getSelectionModel().getSelectedItem());
        	window.setScene(empAvail);
        	}
        });
		
        Button back = new Button("Back");
        HBox hbBack = new HBox(10);
        hbBack.setAlignment(Pos.BOTTOM_LEFT);
        back.setMinWidth(70);
        back.setMinHeight(30);
        back.setStyle("-fx-font: 15 verdana; -fx-base: #B7FF6E;");
        hbBack.getChildren().add(back);
        grid.add(hbBack, 5, 5);
        
        back.setOnAction(e -> {
    		businessMenu(busInst);
    		window.setScene(businessMenu);
        });
		
        busShowAvailable = new Scene(grid, 600, 500);
	}
	
	public void empAvail(Employee emp){
	//class to show the available time of the individual employee in detail.	
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(30, 30, 30, 30));
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		
		Text header = new Text(emp.getName() + "'s Availability");
		header.setFont(Font.font("Rockwell", FontWeight.NORMAL, 40));
		grid.add(header, 0, 1, 2, 1);
		
		empAvail = new Scene(grid, 600, 500);
	}
	
	
	public void busViewSummary(Business bus){
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(30, 30, 30, 30));
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);

		Text header = new Text("Your Summary:");
		header.setFont(Font.font("Rockwell", FontWeight.NORMAL, 40));
		grid.add(header, 0, 1, 2, 1);
		
		TableView<Booking> table = new TableView<Booking>();
		ObservableList<Booking> bookItems = bus.viewBookingSummary(bookings);
		
		//Customer Column
		TableColumn<Booking,String> customer =  new TableColumn<>("Customer");
		customer.setMinWidth(50);
		customer.setCellValueFactory(new PropertyValueFactory<>("strCust"));
		
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
		table.getColumns().addAll(customer, bookingDate, sessionStart, sessionEnd, emp);
		table.setPlaceholder(new Label("You Currently Have no Bookings"));
		
		grid.add(table, 0, 3,6, 1);
		GridPane.setHalignment(table, HPos.CENTER);
		
        Button back = new Button("Back");
        HBox hbBack = new HBox(10);
        hbBack.setAlignment(Pos.BOTTOM_LEFT);
        back.setMinWidth(70);
        back.setMinHeight(30);
        back.setStyle("-fx-font: 15 verdana; -fx-base: #B7FF6E;");
        hbBack.getChildren().add(back);
        grid.add(hbBack, 5, 5);
        
        back.setOnAction(e -> {
    		businessMenu(busInst);
    		window.setScene(businessMenu);
        });
		
		busViewSummary = new Scene(grid, 600, 500);
	}
	
	public void busChangeHours(Business bus){
		GridPane grid2 = new GridPane();
    	grid2.setPadding(new Insets(30, 30, 30, 30));
    	grid2.setAlignment(Pos.CENTER);
    	grid2.setHgap(20);
    	grid2.setVgap(20);
    	
    	Text title = new Text ("Select working hours");
    	title.setFont(Font.font("Rockwell", FontWeight.NORMAL, 35));
    	grid2.add(title,  0,  1, 2, 1);
    	
        ArrayList<LocalTime> times = new ArrayList<LocalTime>();
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        String initTime = "00:00";
        String endTime = "24:00";
        
        LocalTime initialTime = LocalTime.parse(initTime, dtf);
        LocalTime endingTime = LocalTime.parse(endTime, dtf);
        int i = 0;
        do{
        	times.add(initialTime.plusMinutes(i*15));
        	i++;
        }while(!initialTime.plusMinutes(i*15).equals(endingTime));
        
        ObservableList<LocalTime> timeItems = FXCollections.observableArrayList(times);
           
        ChoiceBox<LocalTime> cbOpen = new ChoiceBox<LocalTime>();
        cbOpen.setItems(timeItems);
        cbOpen.setValue(timeItems.get(0));
        cbOpen.setTooltip(new Tooltip("Select Opening Time"));
        grid2.add(cbOpen, 1, 2);
    	
    	Label openLabel = new Label("Opening Time:");
    	grid2.add(openLabel, 0, 2);
    	
    	Label closeLabel = new Label("Closing Time:");
    	grid2.add(closeLabel, 0, 3);
    	
    	ChoiceBox<LocalTime> cbClose = new ChoiceBox<LocalTime>();
        cbClose.setItems(timeItems);
        cbClose.setValue(timeItems.get(0));
        cbClose.setTooltip(new Tooltip("Select Closing Time"));
        grid2.add(cbClose, 1, 3);
    	
    	Button accept = new Button("Change Times");
    	HBox hbAccept = new HBox(10);
    	hbAccept.setAlignment(Pos.BOTTOM_RIGHT);
    	accept.setMinWidth(70);
    	accept.setMinHeight(30);
    	accept.setStyle("-fx-font: 15 verdana; -fx-base: #B7FF6E;");
        hbAccept.getChildren().add(accept);
        grid2.add(hbAccept, 6, 5);
        accept.setOnAction(e -> {
        	if(cbOpen.getValue().isBefore(cbClose.getValue())){

        		
        		bus.setOpenTime(cbOpen.getValue());
        		bus.setCloseTime(cbClose.getValue());
        		
        		//logger goes here?
        		
        		businessMenu(busInst);
        		window.setScene(businessMenu);
        		
        		//window saying that times have been successfully changed please!  + New working times.
        		}
        	else{
        		//error window please!
        	}
        });
    	
        Button back = new Button("Back");
        HBox hbBack = new HBox(10);
        hbBack.setAlignment(Pos.BOTTOM_LEFT);
        back.setMinWidth(70);
        back.setMinHeight(30);
        back.setStyle("-fx-font: 15 verdana; -fx-base: #B7FF6E;");
        hbBack.getChildren().add(back);
        grid2.add(hbBack, 5, 5);
        
        back.setOnAction(e -> {
    		businessMenu(busInst);
    		window.setScene(businessMenu);
        });
        
        busChangeHours = new Scene(grid2, 600, 500);
	}
	
	public void addEmp(Business bus){
        GridPane grid2 = new GridPane();
    	grid2.setPadding(new Insets(30, 30, 30, 30));
    	grid2.setAlignment(Pos.CENTER);
    	grid2.setHgap(20);
    	grid2.setVgap(20);
    	
        
        Text registerTitle = new Text("Add new employee:");
        registerTitle.setFont(Font.font("Rockwell", FontWeight.NORMAL, 35));
        grid2.add(registerTitle, 0, 1,2, 1);
        
        Label empName = new Label("Employee Name: ");
        grid2.add(empName, 0, 2);

        TextField empNameText = new TextField();
        empNameText.setPromptText("employee name");
        grid2.add(empNameText, 1, 2);
            
        Button register = new Button("Add");
        HBox hbRegister = new HBox(10);
        hbRegister.setAlignment(Pos.BOTTOM_RIGHT);
        register.setMinWidth(70);
        register.setMinHeight(30);
        register.setStyle("-fx-font: 15 verdana; -fx-base: #79B8FF;");
        hbRegister.getChildren().add(register);
        grid2.add(hbRegister, 1, 3);
        register.setOnAction(e -> {
        	String empNameString = empNameText.getText();
        	System.out.println("emp name : "+empNameString);
        	//TODO
        	//check if empNameString is empty and valid?
        	if(empNameString.trim().length() > 0 && empNameString.matches(empNamePattern)){
	        	bus.addNewEmp(empNameString);
	        	FIO.saveBus(businesses);
	    		businessMenu(busInst);
	    		window.setScene(businessMenu);
        	}
        	
        	else{
        		String msg = "Please enter employee name!";
        		handleGenericFail(window, msg);
        		addEmp(bus);
        		window.setScene(busAddEmpSc);
        	}
        });
            
        Button back = new Button("Back");
        HBox hbBack = new HBox(10);
        hbBack.setAlignment(Pos.BOTTOM_LEFT);
        back.setMinWidth(70);
        back.setMinHeight(30);
        back.setStyle("-fx-font: 15 verdana; -fx-base: #B7FF6E;");
        hbBack.getChildren().add(back);
        grid2.add(hbBack, 0, 3);
        
        back.setOnAction(e -> {
    		businessMenu((Business) userInst);
    		window.setScene(businessMenu);
        });
        
        busAddEmpSc = new Scene(grid2, 600, 500);
	}
	public void busSelectEmp(Business bus){
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(30, 30, 30, 30));
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        System.out.println(bus);
        if(bus.getEmps().isEmpty()){
        	String msg = "There are no current employees";
        	handleGenericFail(window, msg);
    		businessMenu(busInst);
    		window.setMinHeight(500);
    		window.setMinWidth(600);
    		window.setScene(businessMenu);
        }
        Text header = new Text("Select an Employee:");
        header.setFont(Font.font("Rockwell", FontWeight.NORMAL, 35));
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
        checkButton.setMinHeight(30);
        checkButton.setMinWidth(70);
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
        back.setMinWidth(70);
        back.setMinHeight(30);
        back.setStyle("-fx-font: 15 verdana; -fx-base: #B7FF6E;");
        hbBack.getChildren().add(back);
        grid.add(hbBack, 0, 3);
        back.setOnAction(e -> {
    		businessMenu(busInst);
    		window.setScene(businessMenu);
        });
        
        
        busSelectEmp = new Scene(grid, 600, 500);
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
    	grid2.setHgap(10);
    	grid2.setVgap(10);
    	
    	ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(20);
    	ColumnConstraints col2 = new ColumnConstraints();
    	col2.setPercentWidth(20);

    	LocalDate currentDate = LocalDate.now();
		currentDate.getDayOfWeek();
        
        Text registerTitle = new Text("Add working time | " + emp.getName());
        registerTitle.setFont(Font.font("Rockwell", FontWeight.NORMAL, 30));
        grid2.add(registerTitle, 0, 1,6, 1);
        
//        Text selectText = new Text("SELECT");
//        selectText.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
//        grid2.add(selectText, 0, 2);
        
        Text dayText = new Text("DAY");
        dayText.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        grid2.add(dayText, 1, 2);
        
        Text startText = new Text("START");
        startText.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        
        grid2.add(startText, 2, 2);
        
        Text endText = new Text("END");
        endText.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        grid2.add(endText, 3, 2);
        
        ArrayList<LocalTime> times = new ArrayList<LocalTime>() ;
        LocalTime checkTime = bus.getOpenTime();
        int timeInMin = bus.getSessionTime();
        int i = 0;
        while(!checkTime.plusMinutes(i*timeInMin).isAfter(bus.getCloseTime())){
        	times.add(checkTime.plusMinutes(i*timeInMin));
        	i++;
        }
        
        ObservableList<LocalTime> timeChoices = FXCollections.observableArrayList(times);
        
//MONDAY
        
        CheckBox cbMonday = new CheckBox();
        cbMonday.setMaxWidth(50);
        grid2.add(cbMonday, 0, 3);
        Text mondayText = new Text("Monday");
        mondayText.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        grid2.add(mondayText, 1, 3);
        
        ChoiceBox<LocalTime> monStart = new ChoiceBox<LocalTime>();
        monStart.setMaxWidth(100);
        monStart.setMinWidth(100);
        monStart.setItems(timeChoices);
        monStart.setValue(timeChoices.get(0));
        monStart.setTooltip(new Tooltip("Select"));
        grid2.add(monStart, 2, 3);
        
        ChoiceBox<LocalTime> monEnd = new ChoiceBox<LocalTime>();
        monEnd.setMaxWidth(100);
        monEnd.setMinWidth(100);
        monEnd.setItems(timeChoices);
        monEnd.setValue(timeChoices.get(0));
        monEnd.setTooltip(new Tooltip("Select"));
        grid2.add(monEnd, 3, 3);
        
        
// TUESDAY
        
        CheckBox cbTuesday = new CheckBox();
        cbTuesday.setMaxWidth(50);
        grid2.add(cbTuesday, 0, 4);
        Text tuesdayText = new Text("Tuesday");
        tuesdayText.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        grid2.add(tuesdayText, 1, 4);
        
        ChoiceBox<LocalTime> tueStart = new ChoiceBox<LocalTime>();
        tueStart.setMaxWidth(100);
        tueStart.setMinWidth(100);
        tueStart.setItems(timeChoices);
        tueStart.setValue(timeChoices.get(0));
        tueStart.setTooltip(new Tooltip("Select"));
        grid2.add(tueStart, 2, 4);
        
        ChoiceBox<LocalTime> tueEnd = new ChoiceBox<LocalTime>();
        tueEnd.setMaxWidth(100);
        tueEnd.setMinWidth(100);
        tueEnd.setItems(timeChoices);
        tueEnd.setValue(timeChoices.get(0));
        tueEnd.setTooltip(new Tooltip("Select"));
        grid2.add(tueEnd, 3, 4);
        
// WEDNESDAY        
        
        CheckBox cbWednesday = new CheckBox();
        cbWednesday.setMaxWidth(50);
        grid2.add(cbWednesday, 0, 5);
        Text wednesdayText = new Text("Wednesday");
        wednesdayText.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        grid2.add(wednesdayText, 1, 5);
        
        ChoiceBox<LocalTime> wedStart = new ChoiceBox<LocalTime>();
        wedStart.setMaxWidth(100);
        wedStart.setMinWidth(100);
        wedStart.setItems(timeChoices);
        wedStart.setValue(timeChoices.get(0));
        wedStart.setTooltip(new Tooltip("Select"));
        grid2.add(wedStart, 2, 5);
        
        ChoiceBox<LocalTime> wedEnd = new ChoiceBox<LocalTime>();
        wedEnd.setMaxWidth(100);
        wedEnd.setMinWidth(100);
        wedEnd.setItems(timeChoices);
        wedEnd.setValue(timeChoices.get(0));
        wedEnd.setTooltip(new Tooltip("Select"));
        grid2.add(wedEnd, 3, 5);
        
// THURSDAY        
        
        CheckBox cbThursday = new CheckBox();
        cbThursday.setMaxWidth(50);
        grid2.add(cbThursday, 0, 6);
        Text thursdayText = new Text("Thursday");
        thursdayText.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        grid2.add(thursdayText, 1, 6);
        
        ChoiceBox<LocalTime> thurStart = new ChoiceBox<LocalTime>();
        thurStart.setMaxWidth(100);
        thurStart.setMinWidth(100);
        thurStart.setItems(timeChoices);
        thurStart.setValue(timeChoices.get(0));
        thurStart.setTooltip(new Tooltip("Select"));
        grid2.add(thurStart, 2, 6);
        
        ChoiceBox<LocalTime> thurEnd = new ChoiceBox<LocalTime>();
        thurEnd.setMaxWidth(100);
        thurEnd.setMinWidth(100);
        thurEnd.setItems(timeChoices);
        thurEnd.setValue(timeChoices.get(0));
        thurEnd.setTooltip(new Tooltip("Select"));
        grid2.add(thurEnd, 3, 6);
        
// FRIDAY
        
        CheckBox cbFriday = new CheckBox();
        cbFriday.setMaxWidth(50);
        grid2.add(cbFriday, 0, 7);
        Text fridayText = new Text("Friday");
        fridayText.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        grid2.add(fridayText, 1, 7);
        
        ChoiceBox<LocalTime> friStart = new ChoiceBox<LocalTime>();
        friStart.setMaxWidth(100);
        friStart.setMinWidth(100);
        friStart.setItems(timeChoices);
        friStart.setValue(timeChoices.get(0));
        friStart.setTooltip(new Tooltip("Select"));
        grid2.add(friStart, 2, 7);
        
        ChoiceBox<LocalTime> friEnd = new ChoiceBox<LocalTime>();
        friEnd.setMaxWidth(100);
        friEnd.setMinWidth(100);
        friEnd.setItems(timeChoices);
        friEnd.setValue(timeChoices.get(0));
        friEnd.setTooltip(new Tooltip("Select"));
        grid2.add(friEnd, 3, 7);
        
//SATURDAY
        
        CheckBox cbSaturday = new CheckBox();
        cbSaturday.setMaxWidth(50);
        grid2.add(cbSaturday, 0, 8);
        Text saturdayText = new Text("Saturday");
        saturdayText.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        grid2.add(saturdayText, 1, 8);
        
        ChoiceBox<LocalTime> satStart = new ChoiceBox<LocalTime>();
        satStart.setMaxWidth(100);
        satStart.setMinWidth(100);
        satStart.setItems(timeChoices);
        satStart.setValue(timeChoices.get(0));
        satStart.setTooltip(new Tooltip("Select"));
        grid2.add(satStart, 2, 8);
        
        ChoiceBox<LocalTime> satEnd = new ChoiceBox<LocalTime>();
        satEnd.setMaxWidth(100);
        satEnd.setMinWidth(100);
        satEnd.setItems(timeChoices);
        satEnd.setValue(timeChoices.get(0));
        satEnd.setTooltip(new Tooltip("Select"));
        grid2.add(satEnd, 3, 8);
        
//SUNDAY
        
        CheckBox cbSunday = new CheckBox();
        cbSunday.setMaxWidth(50);
        grid2.add(cbSunday, 0, 9);
        Text sundayText = new Text("Sunday");
        sundayText.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        grid2.add(sundayText, 1, 9);
        
        ChoiceBox<LocalTime> sunStart = new ChoiceBox<LocalTime>();
        sunStart.setMaxWidth(100);
        sunStart.setMinWidth(100);
        sunStart.setItems(timeChoices);
        sunStart.setValue(timeChoices.get(0));
        sunStart.setTooltip(new Tooltip("Select"));
        grid2.add(sunStart, 2, 9);
        
        ChoiceBox<LocalTime> sunEnd = new ChoiceBox<LocalTime>();
        sunEnd.setMaxWidth(100);
        sunEnd.setMinWidth(100);
        sunEnd.setItems(timeChoices);
        sunEnd.setValue(timeChoices.get(0));
        sunEnd.setTooltip(new Tooltip("Select"));
        grid2.add(sunEnd, 3, 9);
        
        
        
        
       
        Button register = new Button("Add");
        HBox hbRegister = new HBox(10);
        hbRegister.setAlignment(Pos.BOTTOM_RIGHT);
        register.setMinWidth(70);
        register.setMinHeight(30);
        register.setStyle("-fx-font: 15 verdana; -fx-base: #79B8FF;");
        hbRegister.getChildren().add(register);
        grid2.add(hbRegister, 3, 10);

        register.setOnAction(e -> {
        	Boolean monCheck = false;
        	Boolean tueCheck = false;
        	Boolean wedCheck = false;
        	Boolean thuCheck = false;
        	Boolean friCheck = false;
        	Boolean satCheck = false;
        	Boolean sunCheck = false;
        	
        	HashMap<String,LocalTime> newTimes = new HashMap<String,LocalTime>();
    		
        	if(monStart.getSelectionModel().getSelectedItem().isBefore(monEnd.getSelectionModel().getSelectedItem()) && cbMonday.isSelected()){
	    		newTimes.put("monStart", monStart.getSelectionModel().getSelectedItem());
	    		newTimes.put("monEnd", monEnd.getSelectionModel().getSelectedItem());
	    		System.out.println("THE MONDAY START IS: " + newTimes.get("monStart"));
	    		monCheck = true;
        	}
        	else if(!cbMonday.isSelected()){
        		monCheck =true;
        	}
        	if(tueStart.getSelectionModel().getSelectedItem().isBefore(tueEnd.getSelectionModel().getSelectedItem()) && cbTuesday.isSelected()){
	    		newTimes.put("tueStart", tueStart.getSelectionModel().getSelectedItem());
	    		newTimes.put("tueEnd", tueEnd.getSelectionModel().getSelectedItem());
	    		System.out.println("THE TUESDAY START IS: " + newTimes.get("tueStart"));
	    		tueCheck = true;
        	}
        	else if(!cbTuesday.isSelected()){
        		tueCheck =true;
        	}
    		if(wedStart.getSelectionModel().getSelectedItem().isBefore(wedEnd.getSelectionModel().getSelectedItem()) && cbWednesday.isSelected()){
	        	newTimes.put("wedStart", wedStart.getSelectionModel().getSelectedItem());
	    		newTimes.put("wedEnd", wedEnd.getSelectionModel().getSelectedItem());

    		}
    		else if(!cbWednesday.isSelected()){
        		wedCheck =true;
        	}
    		
    		if(thurStart.getSelectionModel().getSelectedItem().isBefore(thurEnd.getSelectionModel().getSelectedItem()) && cbThursday.isSelected()){
	    		newTimes.put("thurStart", thurStart.getSelectionModel().getSelectedItem());
	    		newTimes.put("thurEnd", thurEnd.getSelectionModel().getSelectedItem());
    		}
    		else if(!cbThursday.isSelected()){
        		thuCheck =true;
        	}
    		
    		if(friStart.getSelectionModel().getSelectedItem().isBefore(friEnd.getSelectionModel().getSelectedItem()) && cbFriday.isSelected()){
	    		newTimes.put("friStart", friStart.getSelectionModel().getSelectedItem());
	    		newTimes.put("friEnd", friEnd.getSelectionModel().getSelectedItem());
    		}
    		else if(!cbFriday.isSelected()){
        		friCheck =true;
        	}
    		
    		if(satStart.getSelectionModel().getSelectedItem().isBefore(satEnd.getSelectionModel().getSelectedItem()) && cbSaturday.isSelected()){
	    		newTimes.put("satStart", satStart.getSelectionModel().getSelectedItem());
	    		newTimes.put("satEnd", satEnd.getSelectionModel().getSelectedItem());
    		}
    		else if(!cbSaturday.isSelected()){
        		satCheck =true;
        	}
    		
    		if(sunStart.getSelectionModel().getSelectedItem().isBefore(sunEnd.getSelectionModel().getSelectedItem()) && cbSunday.isSelected()){
	    		newTimes.put("sunStart", sunStart.getSelectionModel().getSelectedItem());
	    		newTimes.put("sunEnd", sunEnd.getSelectionModel().getSelectedItem());
	        	
    		}
    		else if(!cbSunday.isSelected()){
        		sunCheck =true;
        	}
    		
    		emp.updateSchedule(newTimes);
        	
        	//TODO
    		//CHECK INPUTS FOR WRONG/EMPTY strings	
    		if(monCheck == true && tueCheck == true && wedCheck == true && thuCheck == true && friCheck == true && satCheck == true && sunCheck == true){
    			String msg = "Working times successfully added.";
        		handleGenericSuccess(window, msg);
            	FIO.save(customers, businesses, bookings);
        		businessMenu(busInst);
        		window.setScene(businessMenu);
    		}
    		else{
    			handleWorkTimeFail(window, monCheck, tueCheck, wedCheck, thuCheck, friCheck, satCheck, sunCheck);
//    			busSelectEmp(busInst);
//            	window.setScene(busSelectEmp);
    		}
        });
            
        Button back = new Button("Back");
        HBox hbBack = new HBox(10);
        hbBack.setAlignment(Pos.BOTTOM_LEFT);
        back.setMinWidth(70);
        back.setMinHeight(30);
        back.setStyle("-fx-font: 15 verdana; -fx-base: #B7FF6E;");
        hbBack.getChildren().add(back);
        grid2.add(hbBack, 0, 10, 2, 1);
        back.setOnAction(e -> {
        	busSelectEmp(bus);
        	window.setScene(busSelectEmp);
        });
        
        busAddWorkTime = new Scene(grid2, 600, 500);
	}
	public void addDays(Stage window, int row, String day, GridPane grid){
		
	}
	
	private void busShowServices(){
		GridPane grid = new GridPane();
    	grid.setPadding(new Insets(30, 30, 30, 30));
    	grid.setAlignment(Pos.CENTER);
    	grid.setHgap(20);
    	grid.setVgap(20);
    	
    	HashMap<String, Integer> services = busInst.getServices();
    	
        Text registerTitle = new Text("Services | "+busInst.getBusName());
        registerTitle.setFont(Font.font("Rockwell", FontWeight.NORMAL, 35));
        grid.add(registerTitle, 1, 0);
        
        ListView<String> serviceList = new ListView<String>(); 
        ObservableList<String> serviceItems = FXCollections.observableArrayList();
        for(String myService : busInst.getServices().keySet()){
        	serviceItems.add(myService + " | " + busInst.getServices().get(myService));
        }
        serviceList.setItems(serviceItems);
        
        serviceList.setPrefHeight(300);
        serviceList.setPrefWidth(300);
        
        grid.add(serviceList, 1,1);
 

        
        
        
//        
//        
//        TableView table = new TableView();
//        TableColumn serviceCol = new TableColumn("Service");
//        TableColumn durationCol = new TableColumn("Duration");
//        
//        //!!!!REFER TO Adding Maps of Data to the Table on website
//        
//        TableColumn<String,Integer> c1 = new TableColumn<String,Integer>("ServiceASD"); //!!!!look at the actual program and see that there is another column already there
//        //c1.setCellValueFactory(p -> p.getValue().getOwner().firstNameProperty());
//        c1.setCellValueFactory(new MapValueFactory("service")); //!!!!may need to change this value, it needs to be the key? put that key in
//        table.getColumns().add(c1);
//
//        //TableColumn<CarOfPerson,String> c2 = new TableColumn<CarOfPerson,String>("last");
//        //c2.setCellValueFactory(p -> p.getValue().getOwner().lastNameProperty());
//        //c2.setCellValueFactory(new PropertyValueFactory<String, Integer>("")));
//        //table.getColumns().add(c2);
//        
//        table.getColumns().addAll(serviceCol, durationCol);
 
        
//        grid.add(table_view, 1, 1);
//
//        ObservableList<String> keys = FXCollections.observableArrayList(services.keySet());
//
//		final TableView<String> table = new TableView<>(keys);
//		TableColumn<String, Integer> column1 = new TableColumn<>("Key");
//		// display item value (= constant)
//		column1.setCellValueFactory(cd -> Bindings.createIntegerBinding(() -> cd.getValue()));
//		
//		TableColumn<String, Integer> column2 = new TableColumn<>("Value");
//		column2.setCellValueFactory(cd -> Bindings.valueAt(services, cd.getValue()));
//
//		table.getColumns().setAll(column1, column2);
        
        Button edit = new Button("Edit");
        HBox hbEdit = new HBox(10);
        hbEdit.setAlignment(Pos.BOTTOM_RIGHT);
        edit.setMinWidth(70);
        edit.setMinHeight(30);
        edit.setStyle("-fx-font: 15 verdana; -fx-base: #B7FF6E;");
        hbEdit.getChildren().add(edit);
        grid.add(hbEdit, 2, 2);
        edit.setOnAction(e -> {
    		//TODO
        });
        
        Button delete = new Button("Delete");
        HBox hbDel = new HBox(10);
        hbDel.setAlignment(Pos.BOTTOM_CENTER);
        delete.setMinWidth(70);
        delete.setMinHeight(30);
        delete.setStyle("-fx-font: 15 verdana; -fx-base: #B7FF6E;");
        hbDel.getChildren().add(delete);
        grid.add(hbDel, 1, 2);
        delete.setOnAction(e -> {
    		//TODO
        });
        
        
        Button back = new Button("Back");
        HBox hbBack = new HBox(10);
        hbBack.setAlignment(Pos.BOTTOM_LEFT);
        back.setMinWidth(70);
        back.setMinHeight(30);
        back.setStyle("-fx-font: 15 verdana; -fx-base: #B7FF6E;");
        hbBack.getChildren().add(back);
        grid.add(hbBack, 0, 2);
        back.setOnAction(e -> {
    		businessMenu(busInst);
    		window.setScene(businessMenu);
        });
        
        
        busShowServices = new Scene(grid, 600, 500);
	}
	

}
