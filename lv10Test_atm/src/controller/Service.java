package controller;

import java.util.ArrayList;

import models.Customer;

public abstract class Service {
	
	public abstract ArrayList<Customer> moveService(ArrayList<Customer>accs);
	public abstract void printMenu();
}
