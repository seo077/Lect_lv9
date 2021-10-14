package models;

public abstract class Unit {
	private String name;
	private int hp;
	private int att;
	private int def;
	private int pos;
	
	public Unit(String name,int hp,int att,int def,int pos) {
		this.name = name;
		this.hp = hp;
		this.att = att;
		this.def = def;
		this.pos = pos;
	}
	
	abstract void damage(String attackerName,int dam);
	
	public void setHp(int dam) {
		this.hp-=dam;
	}
	
	public String getName() {
		return this.name;
	}
	public int getHp() {
		return this.hp;
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
}
