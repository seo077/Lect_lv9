package models;

public class Inventory {
	private String kind;
	private int effect;
	private String name;
	private int power;
	private int price;
	private int cnt;
	private boolean available;
	
	public Inventory(String kind,int effect,String name,int power,int price) {
		this.kind = kind;
		this.effect = effect;
		this.name = name;
		this.power = power;
		this.price = price;
		this.cnt = 1;
		this.available = true;
	}

	public String getName() {
		return this.name;
	}

	public String getKind() {
		return this.kind;
	}

	public void addCnt(int i) {
		this.cnt+=i;
	}

	public int getEffect() {
		return this.effect;
	}

	public int getPower() {
		return this.power;
	}

	public int getCnt() {
		return this.cnt;
	}

	public boolean getAvailable() {
		return this.available;
	}

	public int getPrice() {
		return this.price;
	}
}
