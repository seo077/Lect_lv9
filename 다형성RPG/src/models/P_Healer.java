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
		int cnt = unit.getMaxSkillCnt() - unit.getCurSkillCnt();
		unit.setCurSkillCnt(cnt);
		System.out.printf(">> [%s] 힐 완료!\n",unit.getName());
	}
	

}
