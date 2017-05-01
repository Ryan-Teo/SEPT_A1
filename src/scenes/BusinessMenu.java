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
import javafx.scene.layout.ColumnConstraints;
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
        back.setMinWidth(70);
        back.setMinHeight(30);
        back.setStyle("-fx-font: 15 verdana; -fx-base: #B7FF6E;");
        hbBack.getChildren().add(back);
        grid2.add(hbBack, 0, 3);
        
        back.setOnAction(e -> {
    		businessMenu();
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
        //TODO
        //CHECK HERE IF BUS DOES NOT HAVE ANY EMPLOYEES
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
    		businessMenu();
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
    	
//    	ColumnConstraints col2 = new ColumnConstraints();
//        col2.setPercentWidth(20);
    	

    	LocalDate currentDate = LocalDate.now();
		currentDate.getDayOfWeek();
        
        Text registerTitle = new Text("Add working time | " + emp.getName());
        registerTitle.setFont(Font.font("Rockwell", FontWeight.NORMAL, 30));
        grid2.add(registerTitle, 0, 1,6, 1);
        
        //Monday
        
        Text mondayText = new Text("MONDAY");
        mondayText.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        grid2.add(mondayText, 0, 2);
        Label monStartTime = new Label("Start time : ");
        grid2.add(monStartTime, 1, 2);
        TextField monStartTimeText = new TextField();
        monStartTimeText.setPromptText("HH:mm");
        grid2.add(monStartTimeText, 2, 2);
        monStartTimeText.setMaxWidth(100);
        monStartTimeText.setAlignment(Pos.BASELINE_LEFT);
        
        Label monEndTime = new Label("End time : ");
        grid2.add(monEndTime, 3, 2);
        TextField monEndTimeText = new TextField();
        monEndTimeText.setPromptText("HH:mm");
        grid2.add(monEndTimeText, 4, 2);
        monEndTimeText.setMaxWidth(100);
        
        //Tuesday
        Text tuesdayText = new Text("TUESDAY");
        tuesdayText.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        grid2.add(tuesdayText, 0, 3);
        Label tueStartTime = new Label("Start time : ");
        grid2.add(tueStartTime, 1, 3);
        TextField tueStartTimeText = new TextField();
        tueStartTimeText.setPromptText("HH:mm");
        grid2.add(tueStartTimeText, 2, 3);
        tueStartTimeText.setMaxWidth(100);
        
        Label tueEndTime = new Label("End time : ");
        grid2.add(tueEndTime, 3, 3);
        TextField tueEndTimeText = new TextField();
        tueEndTimeText.setPromptText("HH:mm");
        grid2.add(tueEndTimeText, 4, 3);
        tueEndTimeText.setMaxWidth(100);
        
        //Wednesday
        Text wednesdayText = new Text("WEDNESDAY");
        wednesdayText.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        grid2.add(wednesdayText, 0, 4);
        Label wedStartTime = new Label("Start time : ");
        grid2.add(wedStartTime, 1, 4);
        TextField wedStartTimeText = new TextField();
        wedStartTimeText.setPromptText("HH:mm");
        grid2.add(wedStartTimeText, 2, 4);
        wedStartTimeText.setMaxWidth(100);
        
        Label wedEndTime = new Label("End time : ");
        grid2.add(wedEndTime, 3, 4);
        TextField wedEndTimeText = new TextField();
        wedEndTimeText.setPromptText("HH:mm");
        grid2.add(wedEndTimeText, 4, 4);
        wedEndTimeText.setMaxWidth(100);
        
        //Thursday
        Text thursdayText = new Text("THURSDAY");
        thursdayText.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        grid2.add(thursdayText, 0, 5);
        Label thurStartTime = new Label("Start time : ");
        grid2.add(thurStartTime, 1, 5);
        TextField thurStartTimeText = new TextField();
        thurStartTimeText.setPromptText("HH:mm");
        grid2.add(thurStartTimeText, 2, 5);
        thurStartTimeText.setMaxWidth(100);
        
        Label thurEndTime = new Label("End time : ");
        grid2.add(thurEndTime, 3, 5);
        TextField thurEndTimeText = new TextField();
        thurEndTimeText.setPromptText("HH:mm");
        grid2.add(thurEndTimeText, 4, 5);
        thurEndTimeText.setMaxWidth(100);
        
        //Friday
        Text fridayText = new Text("FRIDAY");
        fridayText.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        grid2.add(fridayText, 0, 6);
        Label friStartTime = new Label("Start time : ");
        grid2.add(friStartTime, 1, 6);
        TextField friStartTimeText = new TextField();
        friStartTimeText.setPromptText("HH:mm");
        grid2.add(friStartTimeText, 2, 6);
        friStartTimeText.setMaxWidth(100);
        
        Label friEndTime = new Label("End time : ");
        grid2.add(friEndTime, 3, 6);
        TextField friEndTimeText = new TextField();
        friEndTimeText.setPromptText("HH:mm");
        grid2.add(friEndTimeText, 4, 6);
        friEndTimeText.setMaxWidth(100);
        
        //Saturday
        Text saturdayText = new Text("SATURDAY");
        saturdayText.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        grid2.add(saturdayText, 0, 7);
        Label satStartTime = new Label("Start time : ");
        grid2.add(satStartTime, 1, 7);
        TextField satStartTimeText = new TextField();
        satStartTimeText.setPromptText("HH:mm");
        grid2.add(satStartTimeText, 2, 7);
        satStartTimeText.setMaxWidth(100);
        
        Label satEndTime = new Label("End time : ");
        grid2.add(satEndTime, 3, 7);
        TextField satEndTimeText = new TextField();
        satEndTimeText.setPromptText("HH:mm");
        grid2.add(satEndTimeText, 4, 7);
        satEndTimeText.setMaxWidth(100);

        //Sunday
        Text sundayText = new Text("SUNDAY");
        sundayText.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        grid2.add(sundayText, 0, 8);
        Label sunStartTime = new Label("Start time : ");
        grid2.add(sunStartTime, 1, 8);
        TextField sunStartTimeText = new TextField();
        sunStartTimeText.setPromptText("HH:mm");
        grid2.add(sunStartTimeText, 2, 8);
        sunStartTimeText.setMaxWidth(100);
        
        Label sunEndTime = new Label("End time : ");
        grid2.add(sunEndTime, 3, 8);
        TextField sunEndTimeText = new TextField();
        sunEndTimeText.setPromptText("HH:mm");
        grid2.add(sunEndTimeText, 4, 8);
        sunEndTimeText.setMaxWidth(100);
        //TODO 
        //CHECK FORMAT OF INPUT "HHMM"
            
        Button register = new Button("Add");
        HBox hbRegister = new HBox(10);
        hbRegister.setAlignment(Pos.BOTTOM_RIGHT);
        register.setMinWidth(70);
        register.setMinHeight(30);
        register.setStyle("-fx-font: 15 verdana; -fx-base: #79B8FF;");
        hbRegister.getChildren().add(register);
        grid2.add(hbRegister, 4, 9);
        
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
        back.setMinWidth(70);
        back.setMinHeight(30);
        back.setStyle("-fx-font: 15 verdana; -fx-base: #B7FF6E;");
        hbBack.getChildren().add(back);
        grid2.add(hbBack, 0, 9);
        back.setOnAction(e -> {
        	busSelectEmp((Business)userInst);
        	window.setScene(busSelectEmp);
        });
        
        busAddWorkTime = new Scene(grid2, 600, 500);
	}

}
