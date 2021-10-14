package models;

abstract public class Character {
	private String name;
	private int hp;
	private int att;
	private int def;
	private int pos;
	
	public void Character(String name,int hp,int att,int def,int pos) {
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
	
	public int getHp() {
		return this.hp;
	}
	public String getName() {
		return this.name;
	}
}
