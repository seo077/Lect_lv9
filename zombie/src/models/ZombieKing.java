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
		System.out.printf("[이름 : %s]의 공격!\n",attackerName);
		System.out.printf("%d의 데미지!\n",dam);
		if(this.shield>0) {
			this.shield-=dam;
		}else {
			setHp(-dam);
		}
		System.out.printf("[이름 : %s]의 남은 체력: %d  (쉴드 : %d)\n",getName(),getHp(),this.shield);
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
			System.out.printf("좀비킹이 체력을 %d만큼 자가회복합니다.\n",up);
			System.out.printf("[이름 : %s]의 체력 : %d\n",super.getName(),super.getHp());
		}else {
			int std = this.shield;
			int up = std/15*2;
			this.shield+=up;
			System.out.printf("좀비킹의 쉴드가 %d만큼 자가회복합니다.\n",up);
		}
	}
	
	@Override
	public void printInfo() {
		System.out.printf("[이름] : %s     [체력] : %d/%d\n",super.getName(),super.getHp(),super.getMax_hp());
		System.out.printf("[공격력] : %d  [방어력] : %d  [위치] : %d\n",super.getAtt(),super.getDef(),super.getPos());
		System.out.printf("[쉴드] : %d\n",this.shield);
	}
	
}
