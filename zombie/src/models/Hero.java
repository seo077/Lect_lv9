package models;

import controller.Game;

public class Hero extends Unit implements Resilient{

	public Hero(String name,int hp,int max_hp,int att,int def,int pos) {
		super(name, hp,max_hp, att, def, pos);
	}
	
	@Override
	void damage(String attackerName,int dam) {
		System.out.printf("[이름 : %s]의 공격!\n",attackerName);
		System.out.printf("%d의 데미지!\n",dam);
		super.setHp(-dam);
		System.out.printf("[이름 : %s]의 남은 체력 : %d\n",getName(),getHp());
	}
	
	public int getPos() {
		return super.getPos();
	}

	@Override
	public void resile() { //회복
		int std = super.getHp();
		int up = std/10*3;
		super.setHp(up);
		System.out.printf("체력을 %d만큼 회복합니다.\n",up);
		System.out.printf("[이름 : %s]의 체력 : %d\n",super.getName(),super.getHp());
	}
	
	public void eatItems(GiveItems items) { //좀비 죽이면 아이템
		int ran = Game.ran.nextInt(3);
		Unit unit = (Unit)items;
		
		int std = unit.getMax_hp();
		if(ran == 0) { //체력 증가
			int up = std/2;
			super.setHp(up);
		}else if(ran == 1) { //공격력 증가
			int up = std/10;
			super.setAtt(up);
		}else if(ran == 2) { //방어력 증가
			int up = std/10;
			super.setDef(up);
		}
		
	}

	@Override
	public void printInfo() {
		System.out.printf("[이름] : %s     [체력] : %d/%d\n",super.getName(),super.getHp(),super.getMax_hp());
		System.out.printf("[공격력] : %d  [방어력] : %d  [위치] : %d\n",super.getAtt(),super.getDef(),super.getPos());
	}

	public void setPos() {
		super.setPos();
	}

}
