package models;

import java.util.ArrayList;

public class Wizard extends Player implements Healable{

	public Wizard(String name, int maxHp, int att) {
		super(name, maxHp, att);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void skill(Monster monster) {
		
	}
	
	public void skill(Monster monster, ArrayList<Monster>mons) {
		if(!super.isMute()) {
			// 마법사의 스킬 : 본인 + 동료 공격하도록 마법( 마법에 걸릴 수 있는 몬스터만)
			System.out.printf("[%s]가  [%s]에게 마법을 걸었습니다.\n",super.getName(),monster.getName());
			try {
				Enchanted mon = (Enchanted)monster;
			} catch (Exception e) {
				System.out.printf("[%s]가  [%s]의 마법을 파훼시켰습니다.\n",monster.getName(),super.getName());
				return;
			}
			System.out.printf("[%s]가  본인과 자신의 동료들을 공격합니다.\n",monster.getName());
			for(int i=0;i<mons.size();i++) {
				System.out.printf("[%s]가  [%s]에게 %d의 데미지를 입힙니다.\n",monster.getName(),mons.get(i).getName(),monster.getAtt());
				mons.get(i).setHp(mons.get(i).getHp()-monster.getAtt());
				if(mons.get(i).getHp() <= 0) {
					System.out.printf("[%s]가 사망하였습니다.\n",mons.get(i).getName());
					mons.get(i).setDie(true);
				}
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
