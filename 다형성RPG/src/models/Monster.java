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
	public void damage(int attackerPower) {
		super.setCurHp(-attackerPower);
	}

	@Override
	public void attack(String defender) {
		if(super.getAttackable()) {
			System.out.printf("[%s]가 [%s]에게  %d의 데미지를 입힙니다.\n",super.getName(),defender,super.getPower());
		}else {
			System.out.println("마법사의 마법...");
			System.out.println("자기스스로를 공격합니다.");
			System.out.printf("[%s]가 [%s]에게  %d의 데미지를 입힙니다.\n",super.getName(),super.getName(),super.getPower()/2);
		}
	}

	
}
