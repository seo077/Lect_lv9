package models;

import java.util.ArrayList;

public class ItemCategory {
	private String kind;
	private String effect;
	private ArrayList<Item>items = new ArrayList<>();
	
	public ItemCategory(String kind,String effect) {
		this.kind = kind;
		this.effect = effect;
	}
	public int itemsSize() {
		return this.items.size();
	}
	
	public String getKind() {
		return this.kind;
	}
	public String getEffect() {
		return this.effect;
	}
	
	public String getItemName(int j) {
		return this.items.get(j).getName();
	}
	public int getItemPower(int j) {
		return this.items.get(j).getPower();
	}
	public int getItemPrice(int j) {
		return this.items.get(j).getPrice();
	}

	public void addItems(String name, int power, int price) {
		this.items.add(new Item(this.kind,name, power, price));
	}
}
