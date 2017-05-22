package scenes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

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
import users.User;

public class BusinessMenu{
	ArrayList<Customer> customers;
	ArrayList<Business> businesses;
	ArrayList<Booking> bookings;
	
	String empNamePattern = "^[a-zA-Z ]*$";
	
	public BusinessMenu(ArrayList<Customer> customers, ArrayList<Business> businesses,
			ArrayList<Booking> bookings, Stage primaryStage) {
		this.customers = customers;
		this.businesses = businesses;
		this.bookings = bookings;
		
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
        	//check if empNameString is empty and valid?
        	if(empNameString.trim().length() > 0 && empNameString.matches(empNamePattern)){
	        	bus.addNewEmp(empNameString);
	        	FIO.saveBus(businesses);
	    		businessMenu();
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
        //CHECK HERE IF BUSINESS DOES NOT HAVE ANY EMPLOYEES
        System.out.println(bus);
        if(bus.getEmps().isEmpty()){
        	String msg = "There are no current employees";
        	handleGenericFail(window, msg);
    		businessMenu();
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
        int timeInMin = bus.getTimeSlotInMins();
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
	    		System.out.println("THE WEDNESDAY START IS: " + newTimes.get("wedStart"));
	    		wedCheck = true;
    		}
    		else if(!cbWednesday.isSelected()){
        		wedCheck =true;
        	}
    		
    		if(thurStart.getSelectionModel().getSelectedItem().isBefore(thurEnd.getSelectionModel().getSelectedItem()) && cbThursday.isSelected()){
	    		newTimes.put("thurStart", thurStart.getSelectionModel().getSelectedItem());
	    		newTimes.put("thurEnd", thurEnd.getSelectionModel().getSelectedItem());
	    		System.out.println("THE THURSDAY START IS: " + newTimes.get("thurStart"));
	    		thuCheck = true;
    		}
    		else if(!cbThursday.isSelected()){
        		thuCheck =true;
        	}
    		
    		if(friStart.getSelectionModel().getSelectedItem().isBefore(friEnd.getSelectionModel().getSelectedItem()) && cbFriday.isSelected()){
	    		newTimes.put("friStart", friStart.getSelectionModel().getSelectedItem());
	    		newTimes.put("friEnd", friEnd.getSelectionModel().getSelectedItem());
	    		System.out.println("THE FRIDAY START IS: " + newTimes.get("friStart"));
	    		friCheck = true;
    		}
    		else if(!cbFriday.isSelected()){
        		friCheck =true;
        	}
    		
    		if(satStart.getSelectionModel().getSelectedItem().isBefore(satEnd.getSelectionModel().getSelectedItem()) && cbSaturday.isSelected()){
	    		newTimes.put("satStart", satStart.getSelectionModel().getSelectedItem());
	    		newTimes.put("satEnd", satEnd.getSelectionModel().getSelectedItem());
	    		System.out.println("THE SATURDAY START IS: " + newTimes.get("satStart"));
	    		satCheck = true;
    		}
    		else if(!cbSaturday.isSelected()){
        		satCheck =true;
        	}
    		
    		if(sunStart.getSelectionModel().getSelectedItem().isBefore(sunEnd.getSelectionModel().getSelectedItem()) && cbSunday.isSelected()){
	    		newTimes.put("sunStart", sunStart.getSelectionModel().getSelectedItem());
	    		newTimes.put("sunEnd", sunEnd.getSelectionModel().getSelectedItem());
	    		System.out.println("THE SUNDAY START IS: " + newTimes.get("sunStart"));
	    		sunCheck = true;
	        	
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
        		FIO.saveBus(businesses);
        		businessMenu();
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
	
	

}
