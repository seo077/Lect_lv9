package models;

public class Monster extends Unit{

	public Monster(String name, int maxHp, int power) {
		super(name, maxHp, power);
	}

	@Override
	public void printInfo() {
		System.out.printf("[%s] [%d/%d] [att:%d]\n", super.getName(), super.getCurHp(), super.getMaxHp(),
				super.getPower());
	}

	@Override
	public void damage(int attackerPower) {
		super.setCurHp(-attackerPower);
	}

	@Override
	public void attack(String defender) {
		System.out.printf("[%s]�� [%s]����  %d�� �������� �����ϴ�.\n", super.getName(), defender, super.getPower());
	}



}
