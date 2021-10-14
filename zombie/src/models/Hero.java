package models;

public class Hero extends Character{

	public Hero(String name,int hp,int att,int def,int pos) {
		super.Character(name, hp, att, def, pos);
	}
	
	@Override
	void damage(String attackerName,int dam) {
		System.out.printf("[이름 : %s]의 공격!\n",attackerName);
		System.out.printf("%d의 데미지!\n",dam);
		setHp(dam);
		System.out.printf("[이름 : %s]의 남은 체력 : %d\n",getName(),getHp());
	}

}
