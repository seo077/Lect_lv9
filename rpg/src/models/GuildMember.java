package models;

import java.util.ArrayList;

public class GuildMember {
	private String name;
	private int level;
	private int hp;
	private int maxhp;
	private int att;
	private int def;
	private boolean party;
	
	private ArrayList<ItemCategory>items = new ArrayList<>();
	
	public GuildMember(String name,int level,int hp,int maxhp,int att,int def,boolean party) {
		this.name = name;
		this.level = level;
		this.hp = hp;
		this.maxhp = maxhp;
		this.att = att;
		this.def = def;
		this.party = party;
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

	public boolean getParty() {
		return this.party;
	}
}
