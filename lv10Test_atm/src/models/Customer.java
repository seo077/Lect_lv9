package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class Customer {
	
	private String grade;
	private String name;
	private String id;
	private String pw;
	private int totalMoney;
	private int repAccIdx;
	private ArrayList<Account>accs = new ArrayList<>();
	private int transferLimit;
	private int withdrawLimit;
	
	public Customer(String grade,String name,String id,String pw,int totalMoney,int repAccIdx,ArrayList<Account>accs) { //��� �̵� ��
		this.grade = grade;
		this.name = name;
		this.id = id;
		this.pw = pw;
		this.totalMoney = totalMoney;
		this.repAccIdx = repAccIdx;
		this.accs = accs;
	}
	
	public Customer(String grade,String name,String id,String pw,String acc,boolean rep) { //ó�� ���°�����(+1000��)
		this.grade = grade;
		this.name = name;
		this.id = id;
		this.pw = pw;
		System.out.println("���� ���ϱ� 1000�� ����!!");
		int money = 1000; //���� ���ϱ� : 1000��
		this.totalMoney = money;
		this.accs.add(new Account(acc, money, rep));
	}
	
	public Customer(String grade,String name,String id,String pw,String acc) { //ó�� ���� ���� ������
		this.grade = grade;
		this.name = name;
		this.id = id;
		this.pw = pw;
		this.accs.add(new Account(acc, 0));
	}
	
	public abstract void printInfo();
	public abstract void setLimit(); //��޿� ���� ������ �ٸ�
	public void setTransferLimit(int transferLimit) {
		this.transferLimit = transferLimit;
	}
	public void setWithdrawLimit(int withdrawLimit) {
		this.withdrawLimit = withdrawLimit;
	}
	
	
	public int accsCnt() {
		return this.accs.size();
	}

	public String getAcc(int j) {
		return this.accs.get(j).getAcc();
	}
	
	
	public String getGrade() {
		return this.grade;
	}
	public String getName() {
		return this.name;
	}
	public String getId() {
		return this.id;
	}
	public String getPw() {
		return this.pw;
	}
	public void printAccs() {
		int size = this.accsCnt();
		for(int i=0;i<size;i++) {
			System.out.print("-->");
			this.accs.get(i).printInfo();
		}
	}

	public void inputMoney(int money) {
		this.accs.get(repAccIdx).inputMoney(money);
		this.totalMoney += money;
	}

	public int getTotalMoney() {
		return this.totalMoney;
	}

	public int getRepAccIdx() {
		return this.repAccIdx;
	}

	public ArrayList<Account> getAccs() {
		return this.accs;
	}
	
}
