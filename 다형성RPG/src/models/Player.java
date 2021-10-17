package models;

import controller.GameManager;

public class Player extends Unit{

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
		if(super.getAttackable()) {
			System.out.printf("[%s]�� [%s]����  %d�� �������� �����ϴ�.\n",super.getName(),defender,super.getPower());
		}else {
			System.out.println("[����]�� ħ����ų...");
			System.out.printf("[%s]�� ������ ���� ���մϴ�.\n",super.getName());
		}
	}



}
