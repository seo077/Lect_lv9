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
			System.out.print("�Ա��� �ݾ��� �Է��ϼ��� : ");
			String temp = AtmManager.scan.next();
			try {
				inputMoney = Integer.parseInt(temp);
				break;
			} catch (Exception e) {
				System.out.println("���ڸ� �Է��ϼ���");
			}
		}
		
		if(inputMoney > 0) {
			customer = inputMoney(inputMoney,customer);
		}else {
			System.out.println("�Ա��� �Ұ����մϴ�.");
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
