package models;

public class P_Healer extends Player{

	public P_Healer(String name, int maxHp, int power, int skillCnt) {
		super(name, maxHp, power, skillCnt);
		// TODO Auto-generated constructor stub
	}
	
	public void skill(Healable healable) { // 스킬 사용 시 -> healable객체만  힐
		Unit unit = (Unit)healable;
		int hp = unit.getMaxHp() - unit.getCurHp();
		unit.setCurHp(hp);
		unit.setCurSkillCnt(unit.getMaxSkillCnt());
		System.out.println(">> 힐 완료!");
	}


}
