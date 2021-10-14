package models;

public class ZombieKing extends Unit{

	int shield;
	public ZombieKing(String name,int hp,int att,int def,int pos,int shield) {
		super(name, hp, att, def, pos);
		this.shield = shield;
	}
	
	@Override
	void damage(String attackerName, int dam) {
		System.out.printf("[이름 : %s]의 공격!\n",attackerName);
		System.out.printf("%d의 데미지!\n",dam);
		if(this.shield>0) {
			this.shield-=dam;
		}else {
			setHp(dam);
		}
		System.out.printf("[이름 : %s]의 남은 체력 (쉴드 : %d): %d\n",getName(),getHp(),this.shield);
	}

	
}
