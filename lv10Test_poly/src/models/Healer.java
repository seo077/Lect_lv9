package models;

import java.util.ArrayList;

public class Healer extends Player{

	public Healer(String name, int maxHp, int att) {
		super(name, maxHp, att);
		// TODO Auto-generated constructor stub
	}
	
	public void skill(ArrayList<Player>player_list) {
		// 힐러의 스킬 : 동료 치료( 현재 hp -> maxHp ) (치료할 수 있는 플레이어만)
		int num = player_list.size();
		if(!super.isMute()) {
			for(int i=0;i<num;i++) {
				Player p = player_list.get(i);
				try {
					Healable hp = (Healable)p;
					p.setHp(p.getMaxHp());
					System.out.printf("[%s]가 [%s] 치료를 완료하였습니다.\n",super.getName(),p.getName());
					System.out.printf("+++[%s]의 hp : [%d]\n",p.getName(),p.getHp());
				} catch (Exception e) {
					System.out.printf("[%s]는 치료가 불가능합니다.\n",p.getName());
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

	@Override
	public void skill(Monster monster) {
		// TODO Auto-generated method stub
		
	}




}
