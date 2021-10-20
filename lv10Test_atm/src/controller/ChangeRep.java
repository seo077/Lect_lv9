package controller;

import java.util.ArrayList;

import models.Customer;

public class ChangeRep extends Service{

	private static ChangeRep instance = new ChangeRep();
	private ChangeRep() {};
	public static ChangeRep getInstance() {
		return instance;
	}
	
	@Override
	public ArrayList<Customer> moveService(ArrayList<Customer> accs) {
		System.out.println("change");
		return accs;
	}

	@Override
	public void printMenu() {
		// TODO Auto-generated method stub
		
	}

}
