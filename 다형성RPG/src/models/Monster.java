package models;

public  class Monster extends Unit{

	public Monster(String name, int maxHp, int power) {
		super(name, maxHp, power);
	}

	@Override
	public void printInfo() {
		System.out.printf("[%s] [%d/%d] [att:%d]\n",super.getName(),super.getCurHp(),super.getMaxHp(),super.getPower());
	}

	@Override
	public void damage(String attacker, int attackerPower) {
		super.setCurHp(-attackerPower);
		System.out.printf("[%s]가 [%s]에게  %d의 데미지를 입힙니다.\n",attacker,super.getName(),attackerPower);
	}

	
}
