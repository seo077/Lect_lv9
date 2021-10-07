package models;

import java.util.ArrayList;

public class Character {
	public static int prices[] = {1000,2000,3000,4000,5000,6000,7000,8000,9000,10000};
	
	private String name;
	private int level;
	private int price;
	private int hp;
	private int maxhp;
	private int att;
	private int def;
	
	private ArrayList<ItemCategory>items = new ArrayList<>();
	
	public Character(String name,int level,int hp,int maxhp,int att,int def) {
		this.name = name;
		this.level = level;
		this.price = this.prices[this.level-1];
		this.hp = hp;
		this.maxhp = maxhp;
		this.att = att;
		this.def = def;
	}
	public Character(String name,int level,int price,int hp,int maxhp,int att,int def) {
		this.name = name;
		this.level = level;
		this.price = price;
		this.hp = hp;
		this.maxhp = maxhp;
		this.att = att;
		this.def = def;
	}

	public int getPrice() {
		return this.price;
	}
	
	public String getName() {
		return this.name;
	}

	public int getLevel() {
		return this.level;
	}

	public int getHp() {
		return this.hp;
	}

	public int getMaxhp() {
		return this.maxhp;
	}

	public int getAtt() {
		return this.att;
	}

	public int getDef() {
		return this.def;
	}

	public void setLevel(int po) {
		this.level += po;
	}

	public void setHp(int po) {
		this.hp += po;
	}

	public void setAtt(int po) {
		this.att += po;
	}

	public void setDef(int po) {
		this.def += po;
	}

	


}
