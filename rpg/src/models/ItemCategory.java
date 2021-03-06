package models;

import java.util.ArrayList;

public class ItemCategory {
	public static String effects[] = {"level_Up","hp_Up","att_Up","def_Up"};
	
	private String kind;
	private int effect;
	private ArrayList<Item>items = new ArrayList<>();
	
	public ItemCategory(String kind,int effect) {
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
		return this.effects[this.effect];
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
	
	public int getItemSize() {
		return this.items.size();
	}
	
	public void removeItem(int selItem) {
		this.items.remove(selItem);
	}
	public String getItemKind(int j) {
		return this.kind;
	}
	public int getEffectNum() {
		return this.effect;
	}
}
