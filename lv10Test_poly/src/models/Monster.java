package models;

public abstract class Monster extends Unit{

	public Monster(String name, int maxHp, int att) {
		super(name, maxHp, att);
	}

	public abstract void skill(Player player);
	public abstract void attacked(String attacker,int att);
}
