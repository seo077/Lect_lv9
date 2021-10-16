package models;

public class P_Wizard extends Player implements Healable {

	public P_Wizard(String name, int maxHp, int power, int skillCnt) {
		super(name, maxHp, power, skillCnt);
	}

	public void skill() { // 스킬 사용 시 -> 마법으로 적을 속여 적이 자기편 공격(공격력은 절반)

	}
}
