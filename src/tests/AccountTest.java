package tests;
import static org.junit.Assert.*;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

import users.Customer;

public class AccountTest {
	
	//correct format
	Customer customer1 = new Customer("Ryan", "cokeyed", "password", "82 Happy St", "0467957315");
	//insuffient password letters
	Customer customer2 = new Customer("Anthony", "nyanton", "12345", "12 High Dr", "0467946358");
	//incorrect phone number format
	Customer customer3 = new Customer("Harry", "harryiszen2", "incorrect", "51 Zen Ave", "04976591345");
	//insufficient username letters
	Customer customer4 = new Customer("Julia", "Seig", "opensesame", "13 Terror Crt", "0423468265");

	@Test
	public void testRegister() {
	    //Only checking input validation, should there be test cases?

	}

}
