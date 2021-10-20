package models;

public class Account {
	private String acc;
	private int money;
	private boolean rep;
	
	public Account(String acc,int money,boolean rep) { //ù��° ����
		this.acc = acc;
		this.money = money;
		this.rep = rep;
	}
	public Account(String acc,int money) {
		this.acc = acc;
		this.money = money;
	}
	

	public String getAcc() {
		return this.acc;
	}

	public void printInfo() {
		System.out.printf("[���� : %s (%d��)]\n",this.acc,this.money);
	}
	public void inputMoney(int money) {
		this.money += money;
	}
}
