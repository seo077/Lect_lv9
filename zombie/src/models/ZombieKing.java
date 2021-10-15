package models;

import controller.Game;

public class ZombieKing extends Unit implements Resilient,GiveItems{

	int shield;
	public ZombieKing(String name,int hp,int max_hp,int att,int def,int pos,int shield) {
		super(name, hp,max_hp, att, def, pos);
		this.shield = shield;
	}
	
	@Override
	public void damage(String attackerName, int dam) {
		int ran = Game.ran.nextInt(3);
		System.out.printf("[�̸� : %s]�� ����!\n",attackerName);
		System.out.printf("%d�� ������!\n",dam);
		if(this.shield>0) {
			this.shield-=dam;
		}else {
			setHp(-dam);
		}
		System.out.printf("[�̸� : %s]�� ���� ü��: %d  (���� : %d)\n",getName(),getHp(),this.shield);
		if(ran == 0) {
			this.resile();
		}
	}

	@Override
	public void resile() {
		if(this.shield<=0) {
			int std = super.getHp();
			int up = std/10*3;
			super.setHp(up);
			System.out.printf("����ŷ�� ü���� %d��ŭ �ڰ�ȸ���մϴ�.\n",up);
			System.out.printf("[�̸� : %s]�� ü�� : %d\n",super.getName(),super.getHp());
		}else {
			int std = this.shield;
			int up = std/15*2;
			this.shield+=up;
			System.out.printf("����ŷ�� ���尡 %d��ŭ �ڰ�ȸ���մϴ�.\n",up);
		}
	}
	
	@Override
	public void printInfo() {
		System.out.printf("[�̸�] : %s     [ü��] : %d/%d\n",super.getName(),super.getHp(),super.getMax_hp());
		System.out.printf("[���ݷ�] : %d  [����] : %d  [��ġ] : %d\n",super.getAtt(),super.getDef(),super.getPos());
		System.out.printf("[����] : %d\n",this.shield);
	}
	
}
