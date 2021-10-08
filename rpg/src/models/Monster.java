package models;

public class Monster {
	
	private String name;
	private int level;
	private int att;
	private int def;
	
	public Monster(String name,int level,int att,int def) {
		this.name = name;
		this.level = level;
		this.att = att;
		this.def = def;
	}

	public String getName() {
		return this.name;
	}

	public int getLevel() {
		return this.level;
	}

	public int getAtt() {
		return this.att;
	}

	public int getDef() {
		return this.def;
	}
}
