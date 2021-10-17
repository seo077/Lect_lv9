package models;

public class P_Wizard extends Player implements Healable{

	public P_Wizard(String name, int maxHp, int power, int skillCnt) {
		super(name, maxHp, power, skillCnt);
	}

	public void skill(Cheatable cheatable) { // 스킬 사용 시 -> 마법으로 적을 속여 적이 자기편 공격(공격력은 절반)
		Unit unit  = (Unit)cheatable;
		System.out.printf("[%s]가 자신을 %d의 공격력으로 공격합니다.\n",unit.getName(),unit.getPower()/2);
		unit.damage(unit.getPower()/2);
	}
}
