package controller;

import java.util.ArrayList;

import models.Customer;

public class OpenAccService extends Service{
	private static OpenAccService instance = new OpenAccService();
	private OpenAccService() {};
	public static OpenAccService getInstance() {
		return instance;
	}

	@Override
	public ArrayList<Customer> moveService(ArrayList<Customer>customers) {
		System.out.println("open");
		return customers;
	}

	@Override
	public void printMenu() {
		// TODO Auto-generated method stub
		
	}

}
