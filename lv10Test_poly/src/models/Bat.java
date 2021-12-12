package models;

public class Bat extends Monster implements Enchanted{

	public Bat(String name, int maxHp, int att) {
		super(name, maxHp, att);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void skill(Player player) {
		// 공격받은 플레이어는 다음 턴에 한 번 침묵
		System.out.printf("[%s]가 스킬을 사용합니다.\n",super.getName());
		System.out.printf("[%s]가 [%s]에게 침묵 공격을 시전합니다.\n",super.getName(),player.getName());
		player.setMute(true);
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
