package controller;

import java.util.ArrayList;

import models.Customer;

public class WithdrawService extends Service{
	private static WithdrawService instance = new WithdrawService();
	private WithdrawService() {};
	public static WithdrawService getInstance() {
		return instance;
	}

	@Override
	public ArrayList<Customer> moveService(ArrayList<Customer>customers) {
		System.out.println("withdraw");
		return customers;
	}

	@Override
	public void printMenu() {
		// TODO Auto-generated method stub
		
	}

}
