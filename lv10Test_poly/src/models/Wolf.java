package models;

public class Wolf extends Monster implements Enchanted{

	public Wolf(String name, int maxHp, int att) {
		super(name, maxHp, att);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void skill(Player player) {
		// 플레이어의 hp 절반으로
		System.out.printf("[%s]가 스킬을 사용합니다.\n",super.getName());
		System.out.printf("[%s]가 [%s]의 hp를 절반으로 감소시켰습니다.\n",super.getName(),player.getName());
		player.setHp(player.getHp()/2);
		if(player.getHp() <= 0) {
			System.out.printf("[%s]가 사망하였습니다.\n",player.getName());
			super.setDie(true);
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
