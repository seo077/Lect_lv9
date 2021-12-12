package models;

import java.util.ArrayList;

public class Orc extends Monster{

	public Orc(String name, int maxHp, int att) {
		super(name, maxHp, att);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void skill(Player player) {
		
		
	}
	public void skill(ArrayList<Player>players) {
		// 플레이어 모두에게 공격력 50으로 공격
	
		System.out.printf("[%s]가 스킬을 사용합니다.\n",super.getName());
		for(int i=0;i<players.size();i++) {
			System.out.printf("[%s]가 [%s]에게 %d의 데미지를 입힙니다.\n",super.getName(),players.get(i).getName(),50);
			players.get(i).setHp(players.get(i).getHp()-50);
			if(players.get(i).getHp() <= 0) {
				System.out.printf("[%s]가 사망하였습니다.\n",players.get(i).getName());
				players.get(i).setDie(true);
			}
		}
		
	}

	@Override
	public void attacked(String attacker,int att) {
		// TODO Auto-generated method stub
		System.out.printf("[%s]가  [%s]에게 %d의 데미지를 입힙니다.\n",attacker,super.getName(),att);
		super.setHp(super.getHp()-att);
		if(super.getHp() <= 0) {
			System.out.printf("[%s]가 사망하였습니다.\n",super.getName());
			super.setDie(true);
		}
	}

}
