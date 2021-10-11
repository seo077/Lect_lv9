package models;

public class Monster {
	
	private String name;
	private int level;
	private int hp;
	private int maxhp;
	private int att;
	private int def;
	
	public Monster(String name,int level,int hp, int maxhp, int att,int def) {
		this.name = name;
		this.level = level;
		this.hp = hp;
		this.maxhp = maxhp;
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

	public int getHp() {
		return this.hp;
	}

	public int getMaxHp() {
		return this.maxhp;
	}

	public void minusHp(int partyAtt) {
		this.hp -= partyAtt;
	}


}
