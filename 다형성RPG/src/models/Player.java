package models;

import controller.GameManager;

public class Player extends Unit implements Silent,Faint{

	private boolean faint = false;
	private boolean silent = false;
	
	public Player(String name, int maxHp, int power, int skillCnt) {
		super(name, maxHp, power, skillCnt);
	}

	@Override
	public void printInfo() {
		System.out.printf("[%s] [%d/%d] [att : %d] [skill : %d/%d]\n",super.getName(),super.getCurHp(),super.getMaxHp(),super.getPower(),super.getCurSkillCnt(),super.getMaxSkillCnt());
	}

	@Override
	public void damage(int attackerPower) {
		super.setCurHp(-attackerPower);
	}

	@Override
	public void attack(String defender) {
		System.out.printf("[%s]가 [%s]에게  %d의 데미지를 입힙니다.\n",super.getName(),defender,super.getPower());
		
	}

	@Override
	public void setFaint(boolean bool) {
		this.faint = bool;
	}

	@Override
	public boolean getFaint() {
		return this.faint;
	}

	@Override
	public void setSilent(boolean bool) {
		this.silent = bool;
	}

	@Override
	public boolean getSilent() {
		return this.silent;
	}



}
