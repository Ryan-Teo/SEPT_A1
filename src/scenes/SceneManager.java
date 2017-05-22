package scenes;

import java.time.LocalTime;
import java.util.ArrayList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import system.Account;
import system.Booking;
import system.FileIO;
import users.Business;
import users.Customer;
import users.User;

public class SceneManager {
	static Stage window;
	static Scene mainMenu, customerRegister, ownerRegister, registerMenu, customerMenu, custSelectService, busAddEmpSc, busAddWorkTime,
			custSelectBus, custSelectDate, custSelectTime, custSelectEmp, businessMenu, scene4, customerBookingSummary,
			busSelectEmp, busShowServices, busChangeHours, busViewSummary;
	ArrayList<Customer> customers;
	ArrayList<Business> businesses;
	ArrayList<Booking> bookings;
	User userInst;
	Account acct;
	FileIO FIO = new FileIO();
	static MainMenu menuScreen;
	static CustomerMenu custScreen;
	static BusinessMenu busScreen;
	/*
	 * After movement, Scenemanager should have the following methods:
	 * 
	 * -show()
	 * -getScreens()
	 * -getMenus()
	 * -getMenus()
	 * -mainLogIn()
	 * -mainRegisterBusiness()
	 * -mainRegisterCust()
	 * -handleSuccess()
	 * -handleFail()
	 * 
	 * The rest have been moved to their respective classes
	 */
	
	public SceneManager(ArrayList<Customer> customers, ArrayList<Business> businesses, 
			Account account, ArrayList<Booking> bookings, Stage primaryStage) {
		this.customers = customers;
		this.businesses = businesses;
		this.acct = account;
		this.bookings = bookings;
		window = primaryStage;
		
	}
	
	public void show(){
        window.setMinHeight(300);
        window.setMinWidth(600);
        window.setScene(mainMenu);
        window.show();
	}

	public void getScreens(){
		custScreen = new CustomerMenu(customers, businesses, acct, bookings, window);
		busScreen = new BusinessMenu(customers, businesses, acct, bookings, window);
		menuScreen = new MainMenu(customers, businesses, acct, bookings, window);
	}
	public void getMenus(){
		getScreens();
		menuScreen.showMainMenu();
		show();
	}
 
	public void mainLogIn(ArrayList<Customer> customers, ArrayList<Business> businesses, String username,
			String password) {
		userInst = acct.login(customers, businesses, username, password);

	}

	public boolean mainRegisterBusiness(ArrayList<Business>businesses, String name, String username, 
			String busName, String password1, String password2, String phone, String address, LocalTime openTime, LocalTime closeTime, int timeSlotInMins){
		return acct.registerBusiness(name, username, busName, password1, password2, phone, address, businesses, openTime, closeTime, timeSlotInMins);

	}
	
	public boolean mainRegisterCust(ArrayList<Customer> customers, String name, String username, 
			String password1, String password2, String phone, String address) {
		return acct.registerCustomer(name, username, password1, password2, phone, address, customers);
	}
       
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
        Label successful1 = new Label("Please Log In to Begin.");
        successful1.setFont(Font.font("Rockwell", FontWeight.NORMAL, 15));
        GridPane.setHalignment(successful1, HPos.CENTER);
//        successful1.setTextAlignment(TextAlignment.CENTER);
        dialogVbox.add(successful1, 0, 2);
        
        Button back = new Button("Return");
        HBox hbBack = new HBox(15);
        hbBack.setAlignment(Pos.BASELINE_CENTER);
        back.setMinWidth(100);
        back.setMinHeight(20);
        back.setStyle("-fx-font: 10 verdana; -fx-base: #B7FF6E;");
        dialogVbox.getChildren().add(hbBack);
        dialogVbox.add(back, 0, 4);
        back.setOnAction(e -> {
        	menuScreen.showMainMenu();
        	window.setScene(mainMenu);
        	((Node)(e.getSource())).getScene().getWindow().hide();
        });
        GridPane.setHalignment(back, HPos.CENTER);
        dialogVbox.setAlignment(Pos.CENTER);
        
        Scene dialogScene = new Scene(dialogVbox, 300, 100);
        dialog.setScene(dialogScene);
        dialog.show();
    }
	public void handleGenericSuccess(Stage window, String msg) {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(window);
        GridPane dialogVbox = new GridPane();
        
        dialogVbox.setPadding(new Insets(30, 30, 30, 30));
        dialogVbox.setHgap(10);
        dialogVbox.setVgap(5);
        Text successful = new Text(msg);
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
        	((Node)(e.getSource())).getScene().getWindow().hide();
        });
        GridPane.setHalignment(back, HPos.CENTER);
        dialogVbox.setAlignment(Pos.CENTER);
        
        Scene dialogScene = new Scene(dialogVbox, 300, 100);
        dialog.setScene(dialogScene);
        dialog.show();
    }
	public void handleFail(Stage window, Boolean checkUser, Boolean checkUserLength, Boolean checkPassLength, Boolean checkPassword1, Boolean checkPhone) {
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
        
        if (checkUser == true){
        	Text user = new Text("-  UserName already exists");
        	user.setFont(Font.font("Rockwell", FontWeight.NORMAL, 10));
        	user.setTextAlignment(TextAlignment.CENTER);
        	user.setFill(Color.RED);
            dialogVbox.add(user, 0, i+=1);
        }
        
        if (checkUserLength == true){
        	Text passlength = new Text("-  UserName must be between 6-12 characters");
        	passlength.setFont(Font.font("Rockwell", FontWeight.NORMAL, 10));
        	passlength.setTextAlignment(TextAlignment.CENTER);
        	passlength.setFill(Color.RED);
            dialogVbox.add(passlength, 0, i+=1);
        }
        	
        if (checkPassLength == true){
        	Text passlength = new Text("-  Password must be between 6-12 characters");
        	passlength.setFont(Font.font("Rockwell", FontWeight.NORMAL, 10));
        	passlength.setTextAlignment(TextAlignment.CENTER);
        	passlength.setFill(Color.RED);
            dialogVbox.add(passlength, 0, i+=1);
        }
        else if (checkPassLength == false){
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
        	((Node)(e.getSource())).getScene().getWindow().hide();
        });
        GridPane.setHalignment(back, HPos.CENTER);
        dialogVbox.setAlignment(Pos.CENTER);
        
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
    }

	public void handleBusFail(Stage window, Boolean checkUser, Boolean checkUserLength, Boolean checkPassLength, Boolean checkPassword1, Boolean checkPhone, Boolean checkTime) {
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
        
        if (checkUser == true){
        	Text user = new Text("-  UserName already exists");
        	user.setFont(Font.font("Rockwell", FontWeight.NORMAL, 10));
        	user.setTextAlignment(TextAlignment.CENTER);
        	user.setFill(Color.RED);
            dialogVbox.add(user, 0, i+=1);
        }
        
        if (checkUserLength == true){
        	Text passlength = new Text("-  UserName must be between 6-12 characters");
        	passlength.setFont(Font.font("Rockwell", FontWeight.NORMAL, 10));
        	passlength.setTextAlignment(TextAlignment.CENTER);
        	passlength.setFill(Color.RED);
            dialogVbox.add(passlength, 0, i+=1);
        }
        	
        if (checkPassLength == true){
        	Text passlength = new Text("-  Password must be between 6-12 characters");
        	passlength.setFont(Font.font("Rockwell", FontWeight.NORMAL, 10));
        	passlength.setTextAlignment(TextAlignment.CENTER);
        	passlength.setFill(Color.RED);
            dialogVbox.add(passlength, 0, i+=1);
        }
        else if (checkPassLength == false){
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
        
        if (checkTime == false){
        	Text time = new Text("-  Incorrect opening and closing hours");
        	time.setFont(Font.font("Rockwell", FontWeight.NORMAL, 10));
        	time.setTextAlignment(TextAlignment.CENTER);
        	time.setFill(Color.RED);
            dialogVbox.add(time, 0, i+=1);
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
        	((Node)(e.getSource())).getScene().getWindow().hide();
        });
        GridPane.setHalignment(back, HPos.CENTER);
        dialogVbox.setAlignment(Pos.CENTER);
        
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
    }
	
	public void handleWorkTimeFail(Stage window, Boolean monCheck, Boolean tueCheck, Boolean wedCheck, Boolean thuCheck, Boolean friCheck, Boolean satCheck, Boolean sunCheck) {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(window);
        GridPane dialogVbox = new GridPane();
        
        dialogVbox.setPadding(new Insets(30, 30, 30, 30));
        dialogVbox.setHgap(10);
        dialogVbox.setVgap(5);
        Text fail = new Text("Incorrect Working Time Input!");
        Text fail1 = new Text("Please amend the following days:");
        fail.setFont(Font.font("Rockwell", FontWeight.NORMAL, 15));
        fail.setTextAlignment(TextAlignment.CENTER);
        fail1.setFont(Font.font("Rockwell", FontWeight.NORMAL, 15));
        fail1.setTextAlignment(TextAlignment.CENTER);
        dialogVbox.add(fail, 0, 1);
        dialogVbox.add(fail1, 0, 2);
        
        int i = 2;
        
        if (monCheck == false){
        	Text user = new Text("-  Monday ");
        	user.setFont(Font.font("Rockwell", FontWeight.NORMAL, 10));
        	user.setTextAlignment(TextAlignment.CENTER);
        	user.setFill(Color.RED);
            dialogVbox.add(user, 0, i+=1);
        }
        
        if (tueCheck == false){
        	Text user = new Text("-  Tuesday ");
        	user.setFont(Font.font("Rockwell", FontWeight.NORMAL, 10));
        	user.setTextAlignment(TextAlignment.CENTER);
        	user.setFill(Color.RED);
            dialogVbox.add(user, 0, i+=1);
        }
        
        if (wedCheck == false){
        	Text user = new Text("-  Wednesday ");
        	user.setFont(Font.font("Rockwell", FontWeight.NORMAL, 10));
        	user.setTextAlignment(TextAlignment.CENTER);
        	user.setFill(Color.RED);
            dialogVbox.add(user, 0, i+=1);
        }
        
        if (thuCheck == false){
        	Text user = new Text("-  Thursday ");
        	user.setFont(Font.font("Rockwell", FontWeight.NORMAL, 10));
        	user.setTextAlignment(TextAlignment.CENTER);
        	user.setFill(Color.RED);
            dialogVbox.add(user, 0, i+=1);
        }
        
        if (friCheck == false){
        	Text user = new Text("-  Friday ");
        	user.setFont(Font.font("Rockwell", FontWeight.NORMAL, 10));
        	user.setTextAlignment(TextAlignment.CENTER);
        	user.setFill(Color.RED);
            dialogVbox.add(user, 0, i+=1);
        }
        
        if (satCheck == false){
        	Text user = new Text("-  Saturday ");
        	user.setFont(Font.font("Rockwell", FontWeight.NORMAL, 10));
        	user.setTextAlignment(TextAlignment.CENTER);
        	user.setFill(Color.RED);
            dialogVbox.add(user, 0, i+=1);
        }
        
        if (sunCheck == false){
        	Text user = new Text("-  Sunday ");
        	user.setFont(Font.font("Rockwell", FontWeight.NORMAL, 10));
        	user.setTextAlignment(TextAlignment.CENTER);
        	user.setFill(Color.RED);
            dialogVbox.add(user, 0, i+=1);
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
        	((Node)(e.getSource())).getScene().getWindow().hide();
        });
        GridPane.setHalignment(back, HPos.CENTER);
        dialogVbox.setAlignment(Pos.CENTER);
        
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
    }
	public void handleGenericFail(Stage window, String msg) {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(window);
        GridPane dialogVbox = new GridPane();
        
        dialogVbox.setPadding(new Insets(30, 30, 30, 30));
        dialogVbox.setHgap(10);
        dialogVbox.setVgap(5);
        Text fail = new Text(msg);
        fail.setFont(Font.font("Rockwell", FontWeight.NORMAL, 15));
        fail.setFill(Color.RED);
        fail.setTextAlignment(TextAlignment.CENTER);
        dialogVbox.add(fail, 0, 1);
        
        Button back = new Button("Return");
        HBox hbBack = new HBox(15);
        hbBack.setAlignment(Pos.BASELINE_CENTER);
        back.setMinWidth(100);
        back.setMinHeight(20);
        back.setStyle("-fx-font: 10 verdana; -fx-base: #B7FF6E;");
        dialogVbox.getChildren().add(hbBack);
        dialogVbox.add(back, 0, 2);
        back.setOnAction(e -> {
        	((Node)(e.getSource())).getScene().getWindow().hide();
        	return;
        	//I just did this to return to the previous screen
//        	menuScreen.showMainMenu();
//        	window.setScene(mainMenu);
        });
        GridPane.setHalignment(back, HPos.CENTER);
        dialogVbox.setAlignment(Pos.CENTER);
        
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
    }
}
