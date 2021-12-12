package models;

public class Warrior extends Player implements Healable{

	public Warrior(String name, int maxHp, int att) {
		super(name, maxHp, att);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void skill(Monster monster) {
		if(!super.isMute()) {
			// 전사의 스킬 : 공격력 100
			monster.attacked(super.getName(), 100);
			if(monster.getHp() < 0) {
				monster.setDie(true);
			}
		}else {
			System.out.print("침묵중 >>>\n");
			super.setMute(false);
		}
	}

	@Override
	public void attacked(String attacker,int att) {
			System.out.printf("[%s]가  [%s]에게 %d의 데미지를 입힙니다.\n",attacker,super.getName(),att);
			super.setHp(super.getHp()-att);
			if(super.getHp() <= 0) {
				System.out.printf("[%s]가 사망하였습니다.\n",super.getName());
				super.setDie(true);
			}
	}

}
