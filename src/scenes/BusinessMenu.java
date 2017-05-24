package scenes;


import java.time.DayOfWeek;

import java.io.IOException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.event.ChangeEvent;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.*;
import system.Account;
import system.Booking;
import users.Business;
import users.Customer;
import users.Employee;
import users.Service;

public class BusinessMenu extends SceneManager{
	String empNamePattern = "^[a-zA-Z ]*$";
	Business busInst;
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
	
	public BusinessMenu(ArrayList<Customer> customers, ArrayList<Business> businesses, Account account,
			ArrayList<Booking> bookings, Stage primaryStage) {
		super(customers, businesses, account, bookings, primaryStage);
		
	}
	
	public void businessMenu(Business busInst){
		this.busInst = busInst;
        GridPane grid3 = new GridPane();
    	grid3.setPadding(new Insets(30, 30, 30, 30));
    	grid3.setAlignment(Pos.CENTER);
    	grid3.setHgap(30);
    	grid3.setVgap(10);
        
    	Text busTitle = new Text("Welcome --" + busInst.getName() + "--");
    	busTitle.setFont(Font.font("Rockwell", FontWeight.NORMAL, 35));
    	busTitle.setTextAlignment(TextAlignment.CENTER);
        grid3.add(busTitle, 0, 0, 3, 1);
        
        Text employeeHeading = new Text("Employee");
        employeeHeading.setUnderline(true);
        employeeHeading.setFont(Font.font("Rockwell", FontWeight.NORMAL, 15));
        employeeHeading.setTextAlignment(TextAlignment.CENTER);
        grid3.add(employeeHeading, 0, 1);
        
        Button busAddEmp = new Button("Add New Employee");
        HBox hbBusAddEmp = new HBox(10);
        hbBusAddEmp.setAlignment(Pos.CENTER);
        busAddEmp.setMinWidth(150);
        busAddEmp.setMinHeight(25);
        busAddEmp.setStyle("-fx-font: 10 verdana; -fx-base: #79B8FF;");
        hbBusAddEmp.getChildren().add(busAddEmp);
        grid3.add(hbBusAddEmp, 0, 2);
        busAddEmp.setOnAction(e -> {
        	addEmp(busInst);
        	window.setScene(busAddEmpSc);
        });
        
    
        Button busWorkingTime = new Button("Add Emp. Working Time");
        HBox hbBusWorkingTime = new HBox(10);
        hbBusWorkingTime.setAlignment(Pos.CENTER);
        busWorkingTime.setMinWidth(150);
        busWorkingTime.setMinHeight(25);
        busWorkingTime.setStyle("-fx-font: 10 verdana; -fx-base: #79B8FF;");
        hbBusWorkingTime.getChildren().add(busWorkingTime);
        grid3.add(hbBusWorkingTime, 0, 3);
        busWorkingTime.setOnAction(e -> {
        	busSelectEmp(busInst);
        	window.setScene(busSelectEmp);
        });
        
        Button busShowAvail = new Button("Show Employee Availability");
        HBox hbBusShowAvail = new HBox(10);
        hbBusShowAvail.setAlignment(Pos.CENTER);
        busShowAvail.setMinWidth(150);
        busShowAvail.setMinHeight(25);
        busShowAvail.setStyle("-fx-font: 10 verdana; -fx-base: #79B8FF;");
        hbBusShowAvail.getChildren().add(busShowAvail);
        grid3.add(hbBusShowAvail, 0, 4);
        busShowAvail.setOnAction(new EventHandler<ActionEvent>() {
    	   @Override public void handle(ActionEvent e) {
    	        Stage stage = new Stage();
    	        busShowAvailability(stage, busInst);
    	   }
        });
        

//        busShowAvail.setOnAction(e ->{
//        	busShowAvailability(busInst);
//        	window.setScene(busShowAvailable);
//        });
        
        
        
        
        Text bookingHeading = new Text("Bookings");
        bookingHeading.setUnderline(true);
        bookingHeading.setFont(Font.font("Rockwell", FontWeight.NORMAL, 15));
        bookingHeading.setTextAlignment(TextAlignment.CENTER);
        grid3.add(bookingHeading, 1, 1);
        
        Button busViewSum = new Button("View Booking Summary");
        HBox hbBusViewSum = new HBox(10);
        hbBusViewSum.setAlignment(Pos.CENTER);
        busViewSum.setMinWidth(150);
        busViewSum.setMinHeight(25);
        busViewSum.setStyle("-fx-font: 10 verdana; -fx-base: #79B8FF;");
        hbBusViewSum.getChildren().add(busViewSum);
        grid3.add(hbBusViewSum, 1, 2); 
        busViewSum.setOnAction(e -> {
        	busViewSummary(busInst);
        	window.setScene(busViewSummary);
        });
        
        Text settingHeading = new Text("Settings");
        settingHeading.setUnderline(true);
        settingHeading.setFont(Font.font("Rockwell", FontWeight.NORMAL, 15));
        settingHeading.setTextAlignment(TextAlignment.CENTER);
        grid3.add(settingHeading, 2, 1);
        
        Button busEditServe = new Button("Edit Services");
        HBox hbBusEditServe = new HBox(10);
        hbBusEditServe.setAlignment(Pos.CENTER);
        busEditServe.setMinWidth(150);
        busEditServe.setMinHeight(25);
        busEditServe.setStyle("-fx-font: 10 verdana; -fx-base: #79B8FF;");
        hbBusEditServe.getChildren().add(busEditServe);
        grid3.add(hbBusEditServe, 2, 2);
        busEditServe.setOnAction(e -> {
        	busShowServices();
        	window.setScene(busShowServices);
        });
        
        Button busChangeWorkTime = new Button("Change Opening Hours");
        HBox hbBusChangeWorkTime = new HBox(10);
        hbBusChangeWorkTime.setAlignment(Pos.CENTER);
        busChangeWorkTime.setMinWidth(150);
        busChangeWorkTime.setMinHeight(25);
        busChangeWorkTime.setStyle("-fx-font: 10 verdana; -fx-base: #79B8FF;");
        hbBusChangeWorkTime.getChildren().add(busChangeWorkTime);
        grid3.add(hbBusChangeWorkTime, 2, 3);
        busChangeWorkTime.setOnAction(e -> {
        	busChangeHours(busInst);
        	window.setScene(busChangeHours);
        });
        
        Button busLogOut = new Button("Logout");
        HBox hbBusLogOut = new HBox(10);
        hbBusLogOut.setAlignment(Pos.CENTER);
        busLogOut.setMinWidth(150);
        busLogOut.setMinHeight(25);
        busLogOut.setStyle("-fx-font: 10 verdana; -fx-base: #AA3939;");
        hbBusLogOut.getChildren().add(busLogOut);
        grid3.add(hbBusLogOut, 2, 4);
        busLogOut.setOnAction(e -> {
        	menuScreen.showMainMenu();
        	window.setScene(mainMenu);
        	userInst = null;
        	this.busInst = null;
        	});
        
        businessMenu = new Scene(grid3, 900, 600);
	}
	
	public void busShowAvailability(Stage primaryStage, Business bus){
	    primaryStage.setTitle("Employee Availability");
	    GridPane grid = new GridPane();
		grid.setPadding(new Insets(30, 30, 30, 30));
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
	    LocalDate today = LocalDate.now();

		Text dateString = new Text(today.toString());
		HBox dateStringBox =  new HBox(10);
		dateString.setFont(Font.font("Rockwell", FontWeight.NORMAL, 25));
		dateStringBox.getChildren().add(dateString);
		dateStringBox.setAlignment(Pos.TOP_LEFT);
		grid.add(dateStringBox, 0, 0);

		
//		Button accept = new Button("Select");
//    	HBox hbAccept = new HBox(10);
//    	hbAccept.setAlignment(Pos.BOTTOM_RIGHT);
//    	accept.setMinWidth(70);
//    	accept.setMinHeight(30);
//    	accept.setStyle("-fx-font: 15 verdana; -fx-base: #B7FF6E;");
//        hbAccept.getChildren().add(accept);
//        grid.add(hbAccept, 7, 5);
//        accept.setOnAction(e -> {
//        	if(empTable.getSelectionModel().getSelectedIndex() != -1){
//        	empAvail(empTable.getSelectionModel().getSelectedItem(), DayOfWeek.MONDAY);
//        	window.setScene(empAvail);
//        	}
//        });
		
		LocalTime busOpen = busInst.getOpenTime();
		LocalTime busClose = busInst.getCloseTime();
		int sessionTime = busInst.getSessionTime();
		
		ObservableList<Employee>empList = FXCollections.observableArrayList();
		ArrayList<Employee>empArray = bus.getEmps();
		
		for(int j = 0; j < empArray.size(); j++){
			empList.add(empArray.get(j));
		}
		
	    Group root = new Group();
	    Scene scene = new Scene(grid, 800, 550, Color.WHITE);
	    TabPane tabPane = new TabPane();
	    BorderPane borderPane = new BorderPane();
	    for (int i = 0; i < 7; i++) {
			TableView<String>empTable = new TableView<String>();
	    	Tab tab = new Tab();
	    	tab.setText(today.plusDays(i).getDayOfWeek().toString());
	    	//add things to hbox

	        List<String> columns = new ArrayList<String>();
	        List<String> rows = new ArrayList<String>();
	    	
	    	for(Employee empInst: empList){
	    		rows.add(empInst.getName());
	    	}
    		columns.add("Emp. ID");
    		int x = 0;
    		while(busOpen.plusMinutes(sessionTime*x).isBefore(busClose)){
    			columns.add(busOpen.plusMinutes(sessionTime*x).format(dtf));
    			x++;
    		}
	    	TableRow [] tableRows = new TableRow[rows.size()];
	    	TableColumn [] tableColumns = new TableColumn[columns.size()];
	    	
	    	for(int j=0 ; j<columns.size(); j++){
//	            final int j = i;
	            TableColumn col = new TableColumn(columns.get(j));
//	            col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                   
//	               public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                             
//	                    return new SimpleStringProperty(param.getValue().get(j).toString());                       
//	                }                   
//	            });
	            empTable.getColumns().addAll(col);
	    	}
	    	for(int j=0 ; j<rows.size(); j++){
	            empTable.getItems().add(rows.get(j));
//	            final int j = i;
//	            TableColumn col = new TableColumn(rows.get(j));
//	            col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                   
//	               public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                             
//	                    return new SimpleStringProperty(param.getValue().get(j).toString());                       
//	                }                   
//	            });
//	            empTable.getRows().addAll();
	    	}
//	    	rows.add("d1");
//    	    rows.add("d11");
//    	    empTable.getItems().add(rows);
	    	
	    	
	    	
//			//Employee names
//			TableColumn<Employee, String> empName = new TableColumn<>("Name");
//			empName.setMinWidth(100);
//			empName.setCellValueFactory(new PropertyValueFactory<>("name"));
//		
//			//Day 1
//			TableColumn<Employee, String> day1 = new TableColumn<>("Monday");
//			day1.setMinWidth(100);
//			day1.setCellValueFactory(new PropertyValueFactory<>("monHour"));
//			
//			//Day 2
//			TableColumn<Employee, String> day2 = new TableColumn<>("Tuesday");
//			day2.setMinWidth(100);
//			day2.setCellValueFactory(new PropertyValueFactory<>("tueHour"));
//			
//			//Day 3
//			TableColumn<Employee, String> day3 = new TableColumn<>("Wednesday");
//			day3.setMinWidth(100);
//			day3.setCellValueFactory(new PropertyValueFactory<>("wedHour"));
//			
//			//Day 4
//			
//			TableColumn<Employee, String> day4 = new TableColumn<>("Thursday");
//			day4.setMinWidth(100);
//			day4.setCellValueFactory(new PropertyValueFactory<>("thurHour"));
//			//Day 5
//			TableColumn<Employee, String> day5 = new TableColumn<>("Friday");
//			day5.setMinWidth(100);
//			day5.setCellValueFactory(new PropertyValueFactory<>("friHour"));
//			
//			//Day 6
//			TableColumn<Employee, String> day6 = new TableColumn<>("Saturday");
//			day6.setMinWidth(100);
//			day6.setCellValueFactory(new PropertyValueFactory<>("satHour"));
//			
//			//Day 7
//			TableColumn<Employee, String> day7 = new TableColumn<>("Sunday");
//			day7.setMinWidth(100);
//			day7.setCellValueFactory(new PropertyValueFactory<>("sunHour"));
//			
//			empTable.setItems(empList);
//			empTable.getColumns().addAll(empName, day1, day2, day3, day4, day5, day6, day7);
			empTable.setPlaceholder(new Label("There are currently no employees"));
			empTable.setMinSize(scene.getWidth(), scene.getHeight());
			GridPane.setHalignment(empTable, HPos.CENTER);
	      
	      
	      HBox hbox = new HBox();
	      hbox.getChildren().add(empTable);
	      hbox.setAlignment(Pos.CENTER);
	      tab.setId(Integer.toString(i));
	      tab.setClosable(false);
	      tab.setContent(hbox);
	      tabPane.getTabs().add(tab);
	    }

	    tabPane.getSelectionModel().selectedItemProperty().addListener(
			new ChangeListener<Tab>() {
			    @Override
			    public void changed(ObservableValue<? extends Tab> ov, Tab orignalTab, Tab changedTab) {
			        dateString.setText(today.plusDays(Integer.parseInt(changedTab.getId())).toString());
			    }
			}
	    );
	    
	    // bind to take available space
	    borderPane.prefHeightProperty().bind(scene.heightProperty().subtract(100));
	    borderPane.prefWidthProperty().bind(scene.widthProperty());

	    borderPane.setCenter(tabPane);
	    root.getChildren().add(borderPane);
	    grid.add(root, 0, 1);
	    primaryStage.setScene(scene);
	    primaryStage.show();
	}
	

	public void empAvail(Employee emp, DayOfWeek day){
	//class to show the available time of the individual employee in detail.	
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(30, 30, 30, 30));
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		
		Text header = new Text(emp.getName() + "'s Availability");
		header.setFont(Font.font("Rockwell", FontWeight.NORMAL, 40));
		grid.add(header, 0, 1, 2, 1);
		
		ListView<String>timeTable = new ListView<String>();
		ObservableList<String> timeList = FXCollections.observableArrayList();
		HashMap<LocalDate, HashMap<LocalTime, Boolean>> schedule = emp.getSchedule();
		HashMap<LocalTime, Boolean> dayTimes = null;
		LocalDate date = null;
		
		for(int i=0; i < 7; i++){
			if(LocalDate.now().plusDays(i).getDayOfWeek().equals(day)){
				dayTimes = schedule.get(LocalDate.now().plusDays(i));
				date = LocalDate.now().plusDays(i);
			}
		}
		
		//array for the times the person is working on the day.

		LocalTime initialTime = emp.getStartTime(date);
		LocalTime endingTime = emp.getEndTime(date);
		int i = 0;
		
		do{
			if(!dayTimes.get(initialTime.plusMinutes(i*emp.getEmployer().getSessionTime()))){
				System.out.println(initialTime.plusMinutes(i*emp.getEmployer().getSessionTime()) + " - Available");
				timeList.add(initialTime.plusMinutes(i*emp.getEmployer().getSessionTime()) + " - Available");	
			}
			
			else{
				System.out.println(initialTime.plusMinutes(i*emp.getEmployer().getSessionTime()) + " - Booked");
				timeList.add(initialTime.plusMinutes(i*emp.getEmployer().getSessionTime()) + " - Booked");
			}
			i++;
		}while(!initialTime.plusMinutes(i*emp.getEmployer().getSessionTime()).equals(endingTime));
			
				
		//time
	
		timeTable.setItems(timeList);
		
        timeTable.setPrefHeight(300);
        timeTable.setPrefWidth(300);
        
        grid.add(timeTable, 2,2);
        
		empAvail = new Scene(grid, 900, 600);
}

//	public void busShowAvailability(Business bus){
//		GridPane grid = new GridPane();
//		grid.setPadding(new Insets(30, 30, 30, 30));
//		grid.setAlignment(Pos.CENTER);
//		grid.setHgap(10);
//		grid.setVgap(10);
//		
//		Text header = new Text("Worker Hours");
//		header.setFont(Font.font("Rockwell", FontWeight.NORMAL, 40));
//		grid.add(header, 0, 1, 2, 1);
//		
//		TableView<Employee>empTable = new TableView<Employee>();
//		ObservableList<Employee>empList = FXCollections.observableArrayList();
//		ArrayList<Employee>empArray = bus.getEmps();
//		
//		for(int i = 0; i < empArray.size(); i++){
//			empList.add(empArray.get(i));
//		}
//		
//		//Employee names
//		TableColumn<Employee, String> empName = new TableColumn<>("Name");
//		empName.setMinWidth(100);
//		empName.setCellValueFactory(new PropertyValueFactory<>("name"));
//	
//		//Day 1
//		TableColumn<Employee, String> day1 = new TableColumn<>("Monday");
//		day1.setMinWidth(100);
//		day1.setCellValueFactory(new PropertyValueFactory<>("monHour"));
//		
//		//Day 2
//		TableColumn<Employee, String> day2 = new TableColumn<>("Tuesday");
//		day2.setMinWidth(100);
//		day2.setCellValueFactory(new PropertyValueFactory<>("tueHour"));
//		
//		//Day 3
//		TableColumn<Employee, String> day3 = new TableColumn<>("Wednesday");
//		day3.setMinWidth(100);
//		day3.setCellValueFactory(new PropertyValueFactory<>("wedHour"));
//		
//		//Day 4
//		
//		TableColumn<Employee, String> day4 = new TableColumn<>("Thursday");
//		day4.setMinWidth(100);
//		day4.setCellValueFactory(new PropertyValueFactory<>("thurHour"));
//		//Day 5
//		TableColumn<Employee, String> day5 = new TableColumn<>("Friday");
//		day5.setMinWidth(100);
//		day5.setCellValueFactory(new PropertyValueFactory<>("friHour"));
//		
//		//Day 6
//		TableColumn<Employee, String> day6 = new TableColumn<>("Saturday");
//		day6.setMinWidth(100);
//		day6.setCellValueFactory(new PropertyValueFactory<>("satHour"));
//		
//		//Day 7
//		TableColumn<Employee, String> day7 = new TableColumn<>("Sunday");
//		day7.setMinWidth(100);
//		day7.setCellValueFactory(new PropertyValueFactory<>("sunHour"));
//		
//		empTable.setItems(empList);
//		empTable.getColumns().addAll(empName, day1, day2, day3, day4, day5, day6, day7);
//		empTable.setPlaceholder(new Label("There are currently no employees"));
//		
//		grid.add(empTable, 0, 3,8, 1);
//		GridPane.setHalignment(empTable, HPos.CENTER);
//		
//		Button accept = new Button("Select");
//    	HBox hbAccept = new HBox(10);
//    	hbAccept.setAlignment(Pos.BOTTOM_RIGHT);
//    	accept.setMinWidth(70);
//    	accept.setMinHeight(30);
//    	accept.setStyle("-fx-font: 15 verdana; -fx-base: #B7FF6E;");
//        hbAccept.getChildren().add(accept);
//        grid.add(hbAccept, 7, 5);
//        accept.setOnAction(e -> {
//        	if(empTable.getSelectionModel().getSelectedIndex() != -1){
//        	empAvail(empTable.getSelectionModel().getSelectedItem(), DayOfWeek.MONDAY);
//        	window.setScene(empAvail);
//        	}
//        });
//		
//        Button back = new Button("Back");
//        HBox hbBack = new HBox(10);
//        hbBack.setAlignment(Pos.BOTTOM_LEFT);
//        back.setMinWidth(70);
//        back.setMinHeight(30);
//        back.setStyle("-fx-font: 15 verdana; -fx-base: #B7FF6E;");
//        hbBack.getChildren().add(back);
//        grid.add(hbBack, 1, 5);
//        
//        back.setOnAction(e -> {
//    		businessMenu(busInst);
//    		window.setScene(businessMenu);
//        });
//		
//        busShowAvailable = new Scene(grid, 900, 600);
//	}
	
//	public void empAvail(Employee emp, DayOfWeek day){
//	//class to show the available time of the individual employee in detail.	
//		GridPane grid = new GridPane();
//		grid.setPadding(new Insets(30, 30, 30, 30));
//		grid.setAlignment(Pos.CENTER);
//		grid.setHgap(10);
//		grid.setVgap(10);
//		
//		Text header = new Text(emp.getName() + "'s Availability");
//		header.setFont(Font.font("Rockwell", FontWeight.NORMAL, 40));
//		grid.add(header, 0, 1, 2, 1);
//		
//		ListView<String>timeTable = new ListView<String>();
//		ObservableList<String> timeList = FXCollections.observableArrayList();
//		HashMap<LocalDate, HashMap<LocalTime, Boolean>> schedule = emp.getSchedule();
//		HashMap<LocalTime, Boolean> dayTimes = null;
//		LocalDate date = null;
//		
//		for(int i=0; i < 7; i++){
//			if(LocalDate.now().plusDays(i).getDayOfWeek().equals(day)){
//				dayTimes = schedule.get(LocalDate.now().plusDays(i));
//				date = LocalDate.now().plusDays(i);
//			}
//		}
//		
//		//array for the times the person is working on the day.
//
//		LocalTime initialTime = emp.getStartTime(date);
//		LocalTime endingTime = emp.getEndTime(date);
//		int i = 0;
//		
//		do{
//			if(!dayTimes.get(initialTime.plusMinutes(i*emp.getEmployer().getSessionTime()))){
//				System.out.println(initialTime.plusMinutes(i*emp.getEmployer().getSessionTime()) + " - Available");
//				timeList.add(initialTime.plusMinutes(i*emp.getEmployer().getSessionTime()) + " - Available");	
//			}
//		
//			else{
//				System.out.println(initialTime.plusMinutes(i*emp.getEmployer().getSessionTime()) + " - Booked");
//				timeList.add(initialTime.plusMinutes(i*emp.getEmployer().getSessionTime()) + " - Booked");
//			}
//			i++;
//		}while(!initialTime.plusMinutes(i*emp.getEmployer().getSessionTime()).equals(endingTime));
//			
//				
//		//time
//	
//		timeTable.setItems(timeList);
//		
//        timeTable.setPrefHeight(300);
//        timeTable.setPrefWidth(300);
//        
//        grid.add(timeTable, 2,2);
//        
//		empAvail = new Scene(grid, 900, 600);
//}

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
		
		busViewSummary = new Scene(grid, 900, 600);
	}
	
	public void busChangeHours(Business bus){
		GridPane grid2 = new GridPane();
    	grid2.setPadding(new Insets(30, 30, 30, 30));
    	grid2.setAlignment(Pos.CENTER);
    	grid2.setHgap(100);
    	grid2.setVgap(20);
    	
    	Text title = new Text ("Select working hours");
    	title.setFont(Font.font("Rockwell", FontWeight.NORMAL, 35));
    	grid2.add(title,  0,  0, 2, 1);
    	
        ArrayList<LocalTime> times = new ArrayList<LocalTime>();
        ArrayList<Integer> sessions = new ArrayList<Integer>();
        
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
        
        sessions.add(15);
        sessions.add(30);
        sessions.add(45);
        sessions.add(60);
        
        ObservableList<LocalTime> timeItems = FXCollections.observableArrayList(times);
        ObservableList<Integer> sessionItems = FXCollections.observableArrayList(sessions);
        
        LocalTime currentOpen = bus.getOpenTime();
        LocalTime currentClose = bus.getCloseTime();
        System.out.println("OOEN TIME: " + currentOpen);
        System.out.println("CLOSE TIME: " + currentClose);
        
        int currentSession = bus.getSessionTime();
        
        LocalTime setOpen = timeItems.get(0);
        LocalTime setClose = timeItems.get(0);

        int setSession = sessionItems.get(0);
        
        
//        for (int j = 0; j < timeItems.size(); j++){
//        	if (currentOpen == timeItems.get(j)){
//        		setOpen = currentOpen;
//        	}
//        }
//        
//        for (int l = 0; l < timeItems.size(); l++){
//        	if (currentClose == timeItems.get(l)){
//        		setClose = currentClose;
//        	}
//        }
//        
//        for (int k = 0; k < sessionItems.size(); k++){
//        	if (currentSession == sessionItems.get(k)){
//        		setSession = currentSession;
//        	}
//        }
        
        Text openLabel = new Text("Opening Time:");
    	openLabel.setFont(Font.font("Verdana", FontWeight.NORMAL, 15));
    	openLabel.minWidth(150);
    	grid2.add(openLabel, 0, 1);
    	
        ChoiceBox<LocalTime> cbOpen = new ChoiceBox<LocalTime>();
        cbOpen.setItems(timeItems);
        cbOpen.setValue(currentOpen);
        cbOpen.setTooltip(new Tooltip("Select Opening Time"));
        cbOpen.setMinWidth(150);
        grid2.add(cbOpen, 1, 1);
    	
    	Text closeLabel = new Text("Closing Time:");
    	closeLabel.setFont(Font.font("Verdana", FontWeight.NORMAL, 15));
    	closeLabel.minWidth(150);
    	grid2.add(closeLabel, 0, 2);
    	
    	ChoiceBox<LocalTime> cbClose = new ChoiceBox<LocalTime>();
        cbClose.setItems(timeItems);
        cbClose.setValue(currentClose);
        cbClose.setTooltip(new Tooltip("Select Closing Time"));
        cbClose.setMinWidth(150);
        grid2.add(cbClose, 1, 2);
    	
        Text sessionLabel = new Text("Session Length (mins):");
        sessionLabel.setFont(Font.font("Verdana", FontWeight.NORMAL, 15));
        sessionLabel.minWidth(150);
    	grid2.add(sessionLabel, 0, 3);
    	
        ChoiceBox<Integer> cbSession = new ChoiceBox<Integer>();
        cbSession.setItems(sessionItems);
        cbSession.setValue(currentSession);
        cbSession.setTooltip(new Tooltip("Select Opening Time"));
        cbSession.setMinWidth(150);
        grid2.add(cbSession, 1, 3);
        
    	Button accept = new Button("Change");
    	HBox hbAccept = new HBox(10);
    	hbAccept.setAlignment(Pos.BOTTOM_RIGHT);
    	accept.setMinWidth(70);
    	accept.setMinHeight(30);
    	accept.setStyle("-fx-font: 15 verdana; -fx-base: #79B8FF;");
        hbAccept.getChildren().add(accept);
        grid2.add(hbAccept, 1, 4);
        accept.setOnAction(e -> {
        	Boolean checkBooking = true;
    		ObservableList<Booking> bookItems = bus.viewBookingSummary(bookings);
            LocalTime checkStart = null; //swap for error checking
            LocalTime checkEnd =  null;
            
            System.out.println("Bookings: " + bookItems);
    		
    		for(int a = 0; a < bookItems.size(); a++){
    			if(!cbOpen.getValue().isBefore(bookItems.get(a).getStartTime())){
    				checkBooking = false;
    				if (checkStart == null){
    					checkStart = bookItems.get(a).getStartTime();
    					checkEnd = bookItems.get(a).getEndTime();
    				}
    				else if(bookItems.get(a).getStartTime().isBefore(checkStart)){
    					checkStart = bookItems.get(a).getStartTime();
    				}
    				else if(bookItems.get(a).getEndTime().isAfter(checkEnd)){
    					checkEnd = bookItems.get(a).getEndTime();
    				}
    			}	
    			if(!cbClose.getValue().isAfter(bookItems.get(a).getEndTime())){
    				checkBooking = false;
    				if (checkEnd == null){
    					checkStart = bookItems.get(a).getStartTime();
    					checkEnd = bookItems.get(a).getEndTime();
    				}
    				if(bookItems.get(a).getEndTime().isAfter(checkEnd)){
    					checkEnd = bookItems.get(a).getEndTime();
    				}
    			}
    		}
        	
        	if(cbOpen.getValue().isBefore(cbClose.getValue()) && checkBooking == true){
        		bus.setOpenTime(cbOpen.getValue());
        		bus.setCloseTime(cbClose.getValue());
        		bus.setSessionTime(cbSession.getValue());
        		
        		//logger goes here?
        		
        		String msg = "Business Hours Successfully Changed: \n"
        				+ "Opening Hours: " + cbOpen.getValue() + "\n"
        				+ "Closing Hours: " + cbClose.getValue() + "\n"
        				+ "Session Time: " + cbSession.getValue() + " mins";
        		
        		handleGenericSuccess(window, msg);
        		FIO.saveBus(businesses);
        		businessMenu(busInst);
        		window.setScene(businessMenu);
        		}
        	else if(checkBooking == false){
        		String msg = "Unable to change opening hours \n"
        				+ "due to pre-existing booking time clashes: \n"
        				+ "Earliest Booking Start: " + checkStart + "\n"
        				+ "Latest Booking End    : " + checkEnd;
        		handleGenericFail(window, msg);
        	}
        	else{
        		String msg = "Invalid hours, please fix.";
        		handleGenericFail(window, msg);
        	}
        });
    	
        Button back = new Button("Back");
        HBox hbBack = new HBox(10);
        hbBack.setAlignment(Pos.BOTTOM_LEFT);
        back.setMinWidth(70);
        back.setMinHeight(30);
        back.setStyle("-fx-font: 15 verdana; -fx-base: #B7FF6E;");
        hbBack.getChildren().add(back);
        grid2.add(hbBack, 0, 4);
        
        back.setOnAction(e -> {
    		businessMenu(busInst);
    		window.setScene(businessMenu);
        });
        
        busChangeHours = new Scene(grid2, 900, 600);
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
    		businessMenu(busInst);
    		window.setScene(businessMenu);
        });
        
        busAddEmpSc = new Scene(grid2, 900, 600);
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
        
        
        busSelectEmp = new Scene(grid, 900, 600);
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
        	System.out.println(timeInMin);
        	System.out.println(i + " : " + checkTime.plusMinutes(i*timeInMin));
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
	    		wedCheck = true;
    		}
    		else if(!cbWednesday.isSelected()){
        		wedCheck =true;
        	}
    		
    		if(thurStart.getSelectionModel().getSelectedItem().isBefore(thurEnd.getSelectionModel().getSelectedItem()) && cbThursday.isSelected()){
	    		newTimes.put("thurStart", thurStart.getSelectionModel().getSelectedItem());
	    		newTimes.put("thurEnd", thurEnd.getSelectionModel().getSelectedItem());
        		thuCheck =true;
    		}
    		else if(!cbThursday.isSelected()){
        		thuCheck =true;
        	}
    		
    		if(friStart.getSelectionModel().getSelectedItem().isBefore(friEnd.getSelectionModel().getSelectedItem()) && cbFriday.isSelected()){
	    		newTimes.put("friStart", friStart.getSelectionModel().getSelectedItem());
	    		newTimes.put("friEnd", friEnd.getSelectionModel().getSelectedItem());
        		friCheck =true;
    		}
    		else if(!cbFriday.isSelected()){
        		friCheck =true;
        	}
    		
    		if(satStart.getSelectionModel().getSelectedItem().isBefore(satEnd.getSelectionModel().getSelectedItem()) && cbSaturday.isSelected()){
	    		newTimes.put("satStart", satStart.getSelectionModel().getSelectedItem());
	    		newTimes.put("satEnd", satEnd.getSelectionModel().getSelectedItem());
        		satCheck =true;
    		}
    		else if(!cbSaturday.isSelected()){
        		satCheck =true;
        	}
    		
    		if(sunStart.getSelectionModel().getSelectedItem().isBefore(sunEnd.getSelectionModel().getSelectedItem()) && cbSunday.isSelected()){
	    		newTimes.put("sunStart", sunStart.getSelectionModel().getSelectedItem());
	    		newTimes.put("sunEnd", sunEnd.getSelectionModel().getSelectedItem());
        		sunCheck =true;
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
        
        busAddWorkTime = new Scene(grid2,900, 600);
	}
	public void addDays(Stage window, int row, String day, GridPane grid){
		
	}
	
	private void busShowServices(){
		GridPane grid = new GridPane();
    	grid.setPadding(new Insets(30, 30, 30, 30));
    	grid.setAlignment(Pos.CENTER);
    	grid.setHgap(20);
    	grid.setVgap(20);
    	
        Text registerTitle = new Text("Services | "+busInst.getBusName());
        registerTitle.setFont(Font.font("Rockwell", FontWeight.NORMAL, 35));
        grid.add(registerTitle, 1, 0);
    	
    	ArrayList<Service> services = busInst.getServiceList();
    	
    	Text header = new Text("Services:");
		header.setFont(Font.font("Rockwell", FontWeight.NORMAL, 40));
		grid.add(header, 0, 1,2, 1);
		
		TableView<Service> table = new TableView<Service>();
		ObservableList<Service> serviceItems = FXCollections.observableArrayList();
		for(Service myServe : services){
			serviceItems.add(myServe);
		}
        
		//Service Column
		TableColumn<Service,String> service =  new TableColumn<>("Service");
		service.setMinWidth(150);
		service.setCellValueFactory(new PropertyValueFactory<>("serviceName"));
		
		//Block Column
		TableColumn<Service,Integer> blocks =  new TableColumn<>("Blocks");
		blocks.setMinWidth(150);
		blocks.setCellValueFactory(new PropertyValueFactory<>("blocks"));
    	
    	table.setItems(serviceItems);
    	table.setMinWidth(300);
    	table.getColumns().addAll(service, blocks);
        grid.add(table, 0,1, 3, 1);
        
        Button add = new Button("Add New");
        HBox hbAdd = new HBox(10);
        hbAdd.setAlignment(Pos.BOTTOM_RIGHT);
        add.setMinWidth(70);
        add.setMinHeight(30);
        add.setStyle("-fx-font: 15 verdana; -fx-base: #B7FF6E;");
        hbAdd.getChildren().add(add);
        grid.add(hbAdd, 2, 2);
        add.setOnAction(e -> {
        	addService();
    		window.setScene(busAddService);
        	//TODO
        });
        
        Button edit = new Button("Edit");
        HBox hbEdit = new HBox(10);
        hbEdit.setAlignment(Pos.BOTTOM_CENTER);
        edit.setMinWidth(70);
        edit.setMinHeight(30);
        edit.setStyle("-fx-font: 15 verdana; -fx-base: #B7FF6E;");
        hbEdit.getChildren().add(edit);
        grid.add(hbEdit, 1, 2);
        edit.setOnAction(e -> {
        	if(table.getSelectionModel().getSelectedIndex() != -1){
				Service serviceInst = table.getSelectionModel().getSelectedItem();
				editService(serviceInst);
				window.setScene(busEditService);
        	}	
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
        
        
        busShowServices = new Scene(grid, 900, 600);
	}
	
	public void addService(){
        GridPane grid2 = new GridPane();
    	grid2.setPadding(new Insets(30, 30, 30, 30));
    	grid2.setAlignment(Pos.CENTER);
    	grid2.setHgap(20);
    	grid2.setVgap(20);
    	
        
        Text registerTitle = new Text("Add new Service:");
        registerTitle.setFont(Font.font("Rockwell", FontWeight.NORMAL, 35));
        grid2.add(registerTitle, 0, 1,2, 1);
        
        Label serviceName = new Label("Service Name: ");
        grid2.add(serviceName, 0, 2);

        TextField serviceText = new TextField();
        serviceText.setPromptText("service name");
        grid2.add(serviceText, 1, 2);
        
        Label blocks = new Label("Required blocks: ");
        grid2.add(blocks, 0, 3);

        ChoiceBox<Integer> cbOpen = new ChoiceBox<Integer>();
        cbOpen.getItems().addAll(1,2,3,4,5);
        cbOpen.setValue(1);
        cbOpen.setTooltip(new Tooltip("Select required blocks"));
        cbOpen.setMinWidth(150);
        grid2.add(cbOpen, 1, 3);
            
        Button register = new Button("Add");
        HBox hbRegister = new HBox(10);
        hbRegister.setAlignment(Pos.BOTTOM_RIGHT);
        register.setMinWidth(70);
        register.setMinHeight(30);
        register.setStyle("-fx-font: 15 verdana; -fx-base: #79B8FF;");
        hbRegister.getChildren().add(register);
        grid2.add(hbRegister, 1, 4);
        register.setOnAction(e -> {
        	String serviceString = serviceText.getText();
        	int serviceBlock = cbOpen.getSelectionModel().getSelectedItem();
        	System.out.println("service name : "+serviceString);
        	//TODO
        	//check if empNameString is empty and valid?
        	if(serviceString.trim().length() > 0 && serviceBlock>=1 && serviceBlock<5){
//	        	bus.addNewEmp(empNameString);
        		busInst.addService(serviceString, serviceBlock);
	        	FIO.saveBus(businesses);
	    		busShowServices();
	    		window.setScene(busShowServices);
        	}
        	
        	else{
        		String msg = "Service name has to be entered\n & \nblock has to be between 1 to 4";
        		handleGenericFail(window, msg);
//        		addEmp(bus);
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
        grid2.add(hbBack, 0, 4);
        
        back.setOnAction(e -> {
    		busShowServices();
    		window.setScene(busShowServices);
        });
        
        busAddService = new Scene(grid2, 900, 600);
	}
	
	public void editService(Service thisService){
		GridPane grid2 = new GridPane();
		grid2.setPadding(new Insets(30, 30, 30, 30));
		grid2.setAlignment(Pos.CENTER);
		grid2.setHgap(20);
		grid2.setVgap(20);
	
    
		Text registerTitle = new Text("Edit Service | "+ thisService.getServiceName());
		registerTitle.setFont(Font.font("Rockwell", FontWeight.NORMAL, 35));
		grid2.add(registerTitle, 0, 1,2, 1);
		
        Label blocks = new Label("Required blocks: ");
        grid2.add(blocks, 0, 2);
		
        ChoiceBox<Integer> cbOpen = new ChoiceBox<Integer>();
        cbOpen.getItems().addAll(1,2,3,4,5);
        cbOpen.setValue(1);
        cbOpen.setTooltip(new Tooltip("Select required blocks"));
        cbOpen.setMinWidth(150);
        grid2.add(cbOpen, 1, 2);
		
        Button register = new Button("Change");
        HBox hbRegister = new HBox(10);
        hbRegister.setAlignment(Pos.BOTTOM_RIGHT);
        register.setMinWidth(70);
        register.setMinHeight(30);
        register.setStyle("-fx-font: 15 verdana; -fx-base: #79B8FF;");
        hbRegister.getChildren().add(register);
        grid2.add(hbRegister, 2, 3);
        register.setOnAction(e -> {
        	int serviceBlock = cbOpen.getSelectionModel().getSelectedItem();
        	busInst.updateService(thisService.getServiceName(), serviceBlock);
        	FIO.saveBus(businesses);
    		busShowServices();
    		window.setScene(busShowServices);
        });
        
		
        Button delete = new Button("Remove");
        HBox hbDelete = new HBox(10);
        hbDelete.setAlignment(Pos.BOTTOM_RIGHT);
        delete.setMinWidth(70);
        delete.setMinHeight(30);
        delete.setStyle("-fx-font: 15 verdana; -fx-base: #79B8FF;");
        hbDelete.getChildren().add(delete);
        grid2.add(hbDelete, 1, 3);
        delete.setOnAction(e -> {
        	int serviceBlock = cbOpen.getSelectionModel().getSelectedItem();
        	busInst.removeService(thisService.getServiceName());
        	FIO.saveBus(businesses);
    		busShowServices();
    		window.setScene(busShowServices);
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
    		busShowServices();
    		window.setScene(busShowServices);
        });
        
		busEditService = new Scene(grid2, 900, 600);
	}

}
