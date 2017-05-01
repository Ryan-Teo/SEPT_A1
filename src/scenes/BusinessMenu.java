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
import javafx.scene.control.CheckBox;
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
        	if(empNameString.length() > 0){
	        	bus.addNewEmp(empNameString);
	        	FIO.saveBus(businesses);
	    		businessMenu();
	    		window.setScene(businessMenu);
        	}
        	
        	else{
        		/*
        		 * JULIA
        		 * please add box saying that they need to enter a name... or can't leave box blank
        		 * 
        		 */
        		addEmp((Business)userInst);
        		window.setScene(busAddEmpSc);
        	}
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
        
        ArrayList<LocalTime> times = new ArrayList<LocalTime>() ;
        LocalTime checkTime = bus.getOpenTime();
        int timeInMin = bus.getTimeSlotInMins();
        int i = 0;
        while(checkTime.plusMinutes(i*timeInMin).isBefore(bus.getCloseTime())){
        	times.add(checkTime.plusMinutes(i*timeInMin));
        	i++;
        }
        
        ObservableList<LocalTime> timeChoices = FXCollections.observableArrayList(times);
        
 
        
        //Monday
        grid2.add(new Label("-Monday-"), 0, 2);
        Label monStartTime = new Label("Start time : ");
        grid2.add(monStartTime, 0, 3);
        CheckBox cbMonday = new CheckBox();
        grid2.add(cbMonday, 1, 2);
        ChoiceBox<LocalTime> monStart = new ChoiceBox<LocalTime>();
        monStart.setItems(timeChoices);
        monStart.setValue(timeChoices.get(0));
        monStart.setTooltip(new Tooltip("Select Start Time"));
        grid2.add(monStart, 1, 3);
        
        Label monEndTime = new Label("End time : ");
        grid2.add(monEndTime, 2, 3);
        ChoiceBox<LocalTime> monEnd = new ChoiceBox<LocalTime>();
        monEnd.setItems(timeChoices);
        monEnd.setValue(timeChoices.get(0));
        monEnd.setTooltip(new Tooltip("Select End Time"));
        grid2.add(monEnd, 3, 3);
        
        //Tuesday
        grid2.add(new Label("-Tuesday-"), 0, 4);
        Label tueStartTime = new Label("Start time : ");
        grid2.add(tueStartTime, 0, 5);
        CheckBox cbTuesday = new CheckBox();
        grid2.add(cbTuesday, 1, 4);
        ChoiceBox<LocalTime> tueStart = new ChoiceBox<LocalTime>();
        tueStart.setItems(timeChoices);
        tueStart.setValue(timeChoices.get(0));
        tueStart.setTooltip(new Tooltip("Select Start Time"));
        grid2.add(tueStart, 1, 5);
        
        Label tueEndTime = new Label("End time : ");
        grid2.add(tueEndTime, 2, 5);
        ChoiceBox<LocalTime> tueEnd = new ChoiceBox<LocalTime>();
        tueEnd.setItems(timeChoices);
        tueEnd.setValue(timeChoices.get(0));
        tueEnd.setTooltip(new Tooltip("Select End Time"));
        grid2.add(tueEnd, 3, 5);
        
        //Wednesday
        grid2.add(new Label("-Wednesday-"), 0, 6);
        Label wedStartTime = new Label("Start time : ");
        grid2.add(wedStartTime, 0, 7);
        CheckBox cbWednesday = new CheckBox();
        grid2.add(cbWednesday, 1, 6);
        ChoiceBox<LocalTime> wedStart = new ChoiceBox<LocalTime>();
        wedStart.setItems(timeChoices);
        wedStart.setValue(timeChoices.get(0));
        wedStart.setTooltip(new Tooltip("Select Start Time"));
        grid2.add(wedStart, 1, 7);
        
        Label wedEndTime = new Label("End time : ");
        grid2.add(wedEndTime, 2, 7);
        ChoiceBox<LocalTime> wedEnd = new ChoiceBox<LocalTime>();
        wedEnd.setItems(timeChoices);
        wedEnd.setValue(timeChoices.get(0));
        wedEnd.setTooltip(new Tooltip("Select End Time"));
        grid2.add(wedEnd, 3, 7);
        
        //Thursday
        grid2.add(new Label("-Thursday-"), 0, 8);
        Label thurStartTime = new Label("Start time : ");
        grid2.add(thurStartTime, 0, 9);
        CheckBox cbThursday = new CheckBox();
        grid2.add(cbThursday, 1, 8);
        ChoiceBox<LocalTime> thurStart = new ChoiceBox<LocalTime>();
        thurStart.setItems(timeChoices);
        thurStart.setValue(timeChoices.get(0));
        thurStart.setTooltip(new Tooltip("Select Start Time"));
        grid2.add(thurStart, 1, 9);
        
        Label thurEndTime = new Label("End time : ");
        grid2.add(thurEndTime, 2, 9);
        ChoiceBox<LocalTime> thurEnd = new ChoiceBox<LocalTime>();
        thurEnd.setItems(timeChoices);
        thurEnd.setValue(timeChoices.get(0));
        thurEnd.setTooltip(new Tooltip("Select End Time"));
        grid2.add(thurEnd, 3, 9);
        
        //Friday
        grid2.add(new Label("-Friday-"), 0, 10);
        Label friStartTime = new Label("Start time : ");
        grid2.add(friStartTime, 0, 11);
        CheckBox cbFriday = new CheckBox();
        grid2.add(cbFriday, 1, 10);
        ChoiceBox<LocalTime> friStart = new ChoiceBox<LocalTime>();
        friStart.setItems(timeChoices);
        friStart.setValue(timeChoices.get(0));
        friStart.setTooltip(new Tooltip("Select Start Time"));
        grid2.add(friStart, 1, 11);
        
        Label friEndTime = new Label("End time : ");
        grid2.add(friEndTime, 2, 11);
        ChoiceBox<LocalTime> friEnd = new ChoiceBox<LocalTime>();
        friEnd.setItems(timeChoices);
        friEnd.setValue(timeChoices.get(0));
        friEnd.setTooltip(new Tooltip("Select End Time"));
        grid2.add(friEnd, 3, 11);
        
        //Saturday
        grid2.add(new Label("-Saturday-"), 0, 12);
        Label satStartTime = new Label("Start time : ");
        grid2.add(satStartTime, 0, 13);
        CheckBox cbSaturday = new CheckBox();
        grid2.add(cbSaturday, 1, 12);
        ChoiceBox<LocalTime> satStart = new ChoiceBox<LocalTime>();
        satStart.setItems(timeChoices);
        satStart.setValue(timeChoices.get(0));
        satStart.setTooltip(new Tooltip("Select Start Time"));
        grid2.add(satStart, 1, 13);
        
        
        Label satEndTime = new Label("End time : ");
        grid2.add(satEndTime, 2, 13);
        ChoiceBox<LocalTime> satEnd = new ChoiceBox<LocalTime>();
        satEnd.setItems(timeChoices);
        satEnd.setValue(timeChoices.get(0));
        satEnd.setTooltip(new Tooltip("Select End Time"));
        grid2.add(satEnd, 3, 13);
        
        //Sunday
        grid2.add(new Label("-Sunday-"), 0, 14);
        Label sunStartTime = new Label("Start time : ");
        grid2.add(sunStartTime, 0, 15);
        CheckBox cbSunday = new CheckBox();
        grid2.add(cbSunday, 1, 14);
        ChoiceBox<LocalTime> sunStart = new ChoiceBox<LocalTime>();
        sunStart.setItems(timeChoices);
        sunStart.setValue(timeChoices.get(0));
        sunStart.setTooltip(new Tooltip("Select Start Time"));
        grid2.add(sunStart, 1, 15);
        
        Label sunEndTime = new Label("End time : ");
        grid2.add(sunEndTime, 2, 15);
        ChoiceBox<LocalTime> sunEnd = new ChoiceBox<LocalTime>();
        sunEnd.setItems(timeChoices);
        sunEnd.setValue(timeChoices.get(0));
        sunEnd.setTooltip(new Tooltip("Select End Time"));
        grid2.add(sunEnd, 3, 15);
        
        //TODO 
        //CHECK FORMAT OF INPUT "HHMMMMMMMMMMMMMMM....."
            
        Button register = new Button("Add!");
        HBox hbRegister = new HBox(10);
        hbRegister.setAlignment(Pos.BOTTOM_RIGHT);
        register.setMinWidth(80);
        register.setMinHeight(40);
        register.setStyle("-fx-font: 15 verdana; -fx-base: #79B8FF;");
        hbRegister.getChildren().add(register);
        grid2.add(hbRegister, 3, 16);
        
        register.setOnAction(e -> {
        	HashMap<String,LocalTime> newTimes = new HashMap<String,LocalTime>();
    		
        	if(monStart.getSelectionModel().getSelectedItem().isBefore(monEnd.getSelectionModel().getSelectedItem()) && cbMonday.isSelected()){
	  
	    		newTimes.put("monStart", monStart.getSelectionModel().getSelectedItem());
	    		newTimes.put("monEnd", monEnd.getSelectionModel().getSelectedItem());
	    		System.out.println("THE MONDAY START IS: " + newTimes.get("monStart"));
        	}
        	if(tueStart.getSelectionModel().getSelectedItem().isBefore(tueEnd.getSelectionModel().getSelectedItem()) && cbTuesday.isSelected()){
	    		newTimes.put("tueStart", tueStart.getSelectionModel().getSelectedItem());
	    		newTimes.put("tueEnd", tueEnd.getSelectionModel().getSelectedItem());
	    		System.out.println("THE TUesdat START IS: " + newTimes.get("tueStart"));
        	}
    		if(wedStart.getSelectionModel().getSelectedItem().isBefore(wedEnd.getSelectionModel().getSelectedItem()) && cbWednesday.isSelected()){
	        	newTimes.put("wedStart", wedStart.getSelectionModel().getSelectedItem());
	    		newTimes.put("wedEnd", wedEnd.getSelectionModel().getSelectedItem());
    		}
    		
    		if(thurStart.getSelectionModel().getSelectedItem().isBefore(thurEnd.getSelectionModel().getSelectedItem()) && cbThursday.isSelected()){
	    		newTimes.put("thurStart", thurStart.getSelectionModel().getSelectedItem());
	    		newTimes.put("thurEnd", thurEnd.getSelectionModel().getSelectedItem());
    		}
    		
    		if(friStart.getSelectionModel().getSelectedItem().isBefore(friEnd.getSelectionModel().getSelectedItem()) && cbFriday.isSelected()){
	    		newTimes.put("friStart", friStart.getSelectionModel().getSelectedItem());
	    		newTimes.put("friEnd", friEnd.getSelectionModel().getSelectedItem());
    		}
    		
    		if(satStart.getSelectionModel().getSelectedItem().isBefore(satEnd.getSelectionModel().getSelectedItem()) && cbSaturday.isSelected()){
	    		newTimes.put("satStart", satStart.getSelectionModel().getSelectedItem());
	    		newTimes.put("satEnd", satEnd.getSelectionModel().getSelectedItem());
    		}
    		
    		if(sunStart.getSelectionModel().getSelectedItem().isBefore(sunEnd.getSelectionModel().getSelectedItem()) && cbSunday.isSelected()){

	    		newTimes.put("sunStart", sunStart.getSelectionModel().getSelectedItem());
	    		newTimes.put("sunEnd", sunEnd.getSelectionModel().getSelectedItem());
	        	
    		}
    		
    		emp.updateSchedule(newTimes);
//        	
//        	//TODO
//    		//CHECK INPUTS FOR WRONG/EMPTY strings

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
