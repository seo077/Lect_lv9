package models;

public abstract class Unit { //���Ϳ� �÷��̾��� �ְ� ���� Ŭ����
	private String name;
	private int curHp;
	private int maxHp;
	private int power;
	private int curSkillCnt;
	private int maxSkillCnt; //��ų ��� ���� Ƚ��(�÷��̾ ��ų���Ƚ�� ����)
	
	public Unit(String name,int maxHp,int power) {
		this.name = name;
		this.maxHp = maxHp;
		this.curHp = maxHp;
		this.power = power;
	}
	public Unit(String name,int maxHp,int power,int skillCnt) {
		this.name = name;
		this.maxHp = maxHp;
		this.curHp = maxHp;
		this.power = power;
		this.curSkillCnt = skillCnt;
		this.maxSkillCnt = skillCnt;
	}
	
	public abstract void printInfo();
	public abstract void damage(String attacker,int attackerPower);
	
	public String getName() {
		return this.name;
	}
	public int  getCurHp() {
		return this.curHp;
	}
	public int getMaxHp() {
		return this.maxHp;
	}
	public int getPower() {
		return this.power;
	}
	public int getCurSkillCnt() {
		return this.curSkillCnt;
	}
	public int getMaxSkillCnt() {
		return this.maxSkillCnt;
	}
	public void setCurHp(int hp) {
		this.curHp += hp;
	}
	public void setCurSkillCnt(int SkillCnt) {
		this.curSkillCnt += SkillCnt;
	}
	
}
