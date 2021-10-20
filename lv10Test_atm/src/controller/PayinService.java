package controller;

import java.util.ArrayList;

import models.Account;
import models.Black;
import models.Customer;
import models.Diamond;
import models.Gold;
import models.Platinum;

public class PayinService extends Service{
	private static PayinService instance = new PayinService();
	private PayinService() {};
	public static PayinService getInstance() {
		return instance;
	}

	
	@Override
	public ArrayList<Customer> moveService(ArrayList<Customer>customer) {
		int inputMoney = 0;
		while(true) {
			System.out.print("입금할 금액을 입력하세요 : ");
			String temp = AtmManager.scan.next();
			try {
				inputMoney = Integer.parseInt(temp);
				break;
			} catch (Exception e) {
				System.out.println("숫자를 입력하세요");
			}
		}
		
		if(inputMoney > 0) {
			customer = inputMoney(inputMoney,customer);
		}else {
			System.out.println("입금이 불가능합니다.");
		}
		return customer;
	}

	public ArrayList<Customer> inputMoney(int money ,ArrayList<Customer>customer) {
		customer.get(AtmManager.getLog()).inputMoney(money);
		return customer;
	}

	@Override
	public void printMenu() {
		// TODO Auto-generated method stub
		
	}

}
