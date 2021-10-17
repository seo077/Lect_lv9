package models;

public class P_Warrior extends Player implements Healable{

	public P_Warrior(String name, int maxHp, int power, int skillCnt) {
		super(name, maxHp, power, skillCnt);
		// TODO Auto-generated constructor stub
	}
	

	public void skill(Monster mon) { //스킬 사용 시 -> 공격력100으로 적 한명을 한 번 공격
		System.out.printf("[%s]가 [%s]에게  %d의 데미지를 입힙니다.\n", super.getName(), mon.getName(), 100);
		mon.setCurHp(-100);
	}


}
