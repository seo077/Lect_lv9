package models;

public class Item {

	private String kind;
	private String name;
	private int power;
	private int price;
	
	public Item(String kind,String name,int power,int price) {
		this.kind = kind;
		this.name = name;
		this.power = power;
		this.price = price;
	}
	
	
	public String getKind() {
		return this.kind;
	}
	public String getName() {
		return this.name;
	}
	public int getPower() {
		return this.power;
	}
	public int getPrice() {
		return this.price;
	}
}
