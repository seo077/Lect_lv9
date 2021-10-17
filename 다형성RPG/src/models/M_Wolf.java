package models;

public class M_Wolf extends Monster {

	public M_Wolf(String name, int maxHp, int power) {
		super(name, maxHp, power);
		// TODO Auto-generated constructor stub
	}

	public void skill(Player player) { // 스킬 사용 시 -> 적 전체에게 공격력의 절반 데미지
		System.out.printf("[%s에게 %d의 데미지를 입힙니다.]\n",player.getName(),this.getPower()/2);
		player.setCurHp(-(this.getPower()/2));
	}

	

}
