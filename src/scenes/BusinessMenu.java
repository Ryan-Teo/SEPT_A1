package scenes;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
//        	selectEmp((Business)userInst);
//        	window.setScene(busSelectEmp);
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
        grid2.add(hbRegister, 1, 8);
        
        register.setOnAction(e -> {
        	String empNameString = empNameText.getText();
        	//TODO
        	//check if empNameString is empty?
        	bus.addNewEmp(empNameString);

    		businessMenu();
    		window.setScene(businessMenu);
        	
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
    		businessMenu();
    		window.setScene(businessMenu);
        });
        
        busAddEmpSc = new Scene(grid2, 300, 500);
	}
	
	
//	public void selectEmp(Business bus){
//		GridPane grid = new GridPane();
//		grid.setPadding(new Insets(30, 30, 30, 30));
//        grid.setAlignment(Pos.CENTER);
//        grid.setHgap(10);
//        grid.setVgap(10);
//        //TODO
//        //CHECK HERE IF BUS DOES NOT HAVE ANY EMPLOYEES
//        Text header = new Text("Select an Employee");
//        header.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
//        grid.add(header, 2, 0);
//        
//        ArrayList<Employee> emps = bus.getEmps();
//        System.out.println(emps.size());
//        ArrayList<String> empNames = new ArrayList<String>();
//        for(Employee emp : emps){
//        	empNames.add(emp.getName());
//        }
//        
//        for(String name : empNames){
//        	System.out.println(name);
//        }
//
//        ObservableList<String> empItems = FXCollections.observableArrayList(empNames);
//        
//        ChoiceBox<String> cb = new ChoiceBox<String>();
//        cb.setItems(empItems);
//        cb.setValue(empItems.get(0));
//        cb.setTooltip(new Tooltip("Select employee"));
//        grid.add(cb, 2, 2);
//        
//        
//        Button checkButton = new Button("Check");
//        checkButton.setMinHeight(50);
//        checkButton.setMinWidth(100);
//        checkButton.setStyle("-fx-font: 22 arial; -fx-base: #000555;");
//        grid.add(checkButton, 1, 3);
//        checkButton.setOnAction(e -> {
//        	Employee myEmp = emps.get(cb.getSelectionModel().getSelectedIndex());
//        	System.out.println(myEmp.getName());
//        	addWorkTime(myEmp , bus);
//        	window.setScene(busAddWorkTime);
//        });
//        
//        Button back = new Button("Back");
//        HBox hbBack = new HBox(10);
//        hbBack.setAlignment(Pos.TOP_RIGHT);
//        back.setMinWidth(50);
//        back.setMinHeight(20);
//        back.setStyle("-fx-font: 10 verdana; -fx-base: #B7FF6E;");
//        hbBack.getChildren().add(back);
//        grid.add(hbBack, 1, 0);
//        back.setOnAction(e -> {
//    		businessMenu();
//    		window.setScene(businessMenu);
//        });
//        
//        
//        busSelectEmp = new Scene(grid, 500, 500);
//	}
	
//	public void addWorkTime(Employee emp, Business bus){
//		//add to employees hm HashMap<LocalDate, HashMap<LocalTime, Boolean>>
//		//Weekly?
//		//select time or user input?
//		GridPane grid2 = new GridPane();
//    	grid2.setPadding(new Insets(30, 30, 30, 30));
//    	grid2.setAlignment(Pos.CENTER);
//    	grid2.setHgap(20);
//    	grid2.setVgap(20);
//    	
//        
//        Text registerTitle = new Text("Add working time for employee | " + emp.getName());
//        registerTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 40));
//        grid2.add(registerTitle, 0, 1,2, 1);
//        
//        Label startTime = new Label("Start time : ");
//        grid2.add(startTime, 0, 2);
//
//        TextField startTimeText = new TextField();
//        startTimeText.setPromptText("HHMM");
//        grid2.add(startTimeText, 1, 2);
//        //TODO 
//        //CHECK FORMAT OF INPUT "HHMM"
//            
//        Button register = new Button("Add!");
//        HBox hbRegister = new HBox(10);
//        hbRegister.setAlignment(Pos.BOTTOM_RIGHT);
//        register.setMinWidth(80);
//        register.setMinHeight(40);
//        register.setStyle("-fx-font: 15 verdana; -fx-base: #79B8FF;");
//        hbRegister.getChildren().add(register);
//        grid2.add(hbRegister, 1, 8);
//        
//        register.setOnAction(e -> {
//        	String empNameString = empNameText.getText();
//        	//TODO
//        	//check if empNameString is empty?
//        	bus.addNewEmp(empNameString);
//
//    		businessMenu();
//    		window.setScene(businessMenu);
//        	
//        });
//            
//        Button back = new Button("Back");
//        HBox hbBack = new HBox(10);
//        hbBack.setAlignment(Pos.TOP_RIGHT);
//        back.setMinWidth(50);
//        back.setMinHeight(20);
//        back.setStyle("-fx-font: 10 verdana; -fx-base: #B7FF6E;");
//        hbBack.getChildren().add(back);
//        grid2.add(hbBack, 1, 0);
//        back.setOnAction(e -> {
//    		businessMenu();
//    		window.setScene(businessMenu);
//        });
//        
//        busAddEmpSc = new Scene(grid2, 300, 500);
//	}

}
