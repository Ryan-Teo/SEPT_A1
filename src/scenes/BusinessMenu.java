package scenes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
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

public class BusinessMenu extends SceneManager{

	public BusinessMenu(ArrayList<Customer> customers, ArrayList<Business> businesses, Account account,
			ArrayList<Booking> bookings, Stage primaryStage) {
		super(customers, businesses, account, bookings, primaryStage);
		// TODO Auto-generated constructor stub
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
        	menuScreen.showMainMenu();
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

}
