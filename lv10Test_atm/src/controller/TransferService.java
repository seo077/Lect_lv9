package controller;

import java.util.ArrayList;

import models.Customer;

public class TransferService extends Service{
	private static TransferService instance = new TransferService();
	private TransferService() {};
	public static TransferService getInstance() {
		return instance;
	}

	@Override
	public ArrayList<Customer> moveService(ArrayList<Customer>customers) {
		System.out.println("transfer");
		return customers;
	}

	@Override
	public void printMenu() {
		// TODO Auto-generated method stub
		
	}

}
