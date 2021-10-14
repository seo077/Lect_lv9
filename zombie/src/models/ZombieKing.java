package models;

public class ZombieKing extends Unit{

	int shield;
	public ZombieKing(String name,int hp,int att,int def,int pos,int shield) {
		super(name, hp, att, def, pos);
		this.shield = shield;
	}
	
	@Override
	void damage(String attackerName, int dam) {
		System.out.printf("[�̸� : %s]�� ����!\n",attackerName);
		System.out.printf("%d�� ������!\n",dam);
		if(this.shield>0) {
			this.shield-=dam;
		}else {
			setHp(dam);
		}
		System.out.printf("[�̸� : %s]�� ���� ü�� (���� : %d): %d\n",getName(),getHp(),this.shield);
	}

	
}
