package models;

public abstract class Unit {
	private String name;
	private int hp;
	private int max_hp;
	private int att;
	private int def;
	private int pos;
	
	public Unit(String name,int hp,int max_hp,int att,int def,int pos) {
		this.name = name;
		this.hp = hp;
		this.max_hp = max_hp;
		this.att = att;
		this.def = def;
		this.pos = pos;
	}
	
	abstract void damage(String attackerName,int dam);
	abstract void printInfo();
	
	public String getName() {
		return this.name;
	}
	public int getHp() {
		return this.hp;
	}
	public int getMax_hp() {
		return this.max_hp;
	}
	public int getAtt() {
		return this.att;
	}
	public int getDef() {
		return this.def;
	}
	public int getPos() {
		return this.pos;
	}

	public void setHp(int dam) {
		this.hp+=dam;
	}
	
	public void setAtt(int up) {
		this.att += up;
	}

	public void setDef(int up) {
		this.def += up;
	}
}
