package models;

public class Item {
	static final int WEAPON = 1;
	static final int ARMOR = 2;
	static final int RING = 3;
	
	private int kind;
	private String name;
	private int power;
	private int price;
	
	public void Item(int kind,String name,int power,int price) {
		this.kind = kind;
		this.name = name;
		this.power = power;
		this.price = price;
	}
}
