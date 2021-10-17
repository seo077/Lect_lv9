package models;

public class P_Wizard extends Player implements Healable{

	public P_Wizard(String name, int maxHp, int power, int skillCnt) {
		super(name, maxHp, power, skillCnt);
	}

	public void skill(Cheatable cheatable) { // ��ų ��� �� -> �������� ���� �ӿ� ���� �ڱ��� ����(���ݷ��� ����)
		Unit unit  = (Unit)cheatable;
		System.out.printf("[%s]�� �ڽ��� %d�� ���ݷ����� �����մϴ�.\n",unit.getName(),unit.getPower()/2);
		unit.damage(unit.getPower()/2);
	}
}
