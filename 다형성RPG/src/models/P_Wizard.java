package models;

public class P_Wizard extends Player implements Healable {

	public P_Wizard(String name, int maxHp, int power, int skillCnt) {
		super(name, maxHp, power, skillCnt);
	}

	public void skill() { // 스킬 사용 시 -> 적2명의 공격 반사

	}
}
