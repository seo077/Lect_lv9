package models;

public abstract class Player extends Unit{

	
	public Player(String name, int maxHp, int att) {
		super(name, maxHp, att);
		// TODO Auto-generated constructor stub
	}

	public abstract void skill(Monster monster);
	public abstract void attacked(String attacker,int att);
}
