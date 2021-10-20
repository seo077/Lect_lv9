package models;

public class Account {
	private String acc;
	private int money;
	
	public Account(String acc,int money) {
		this.acc = acc;
		this.money = money;
	}

	public String getAcc() {
		return this.acc;
	}

	public void printInfo() {
		System.out.printf("[°èÁÂ : %s (%d¿ø)]\n",this.acc,this.money);
	}
}
