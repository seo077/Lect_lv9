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
	private ArrayList<Account>accs = new ArrayList<>();
	private int transferLimit;
	private int withdrawLimit;
	
	public Customer(String grade,String name,String id,String pw,String acc) {
		this.grade = grade;
		this.name = name;
		this.id = id;
		this.pw = pw;
		System.out.println("가입 축하금 1000원 지급!!");
		int money = 1000; //가입 축하금 : 1000원
		this.accs.add(new Account(acc, money));
	}
	
	public abstract void printInfo();
	public abstract void setLimit(); //등급에 따라 제한이 다름
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
	
}
