package models;

public class Item {
	private String name;
	private int price;
	
	public Item(String name,int price) {
		this.name = name;
		this.price = price;
	}
	public String get_name() {
		return name;
	}
	public int get_price() {
		return price;
	}
}
