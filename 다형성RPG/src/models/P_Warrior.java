package models;

public class P_Warrior extends Player implements Healable{

	public P_Warrior(String name, int maxHp, int power, int skillCnt) {
		super(name, maxHp, power, skillCnt);
		// TODO Auto-generated constructor stub
	}
	

	public void skill(Monster mon) { //��ų ��� �� -> ���ݷ�100���� �� �Ѹ��� �� �� ����
		System.out.printf("[%s]�� [%s]����  %d�� �������� �����ϴ�.\n", super.getName(), mon.getName(), 100);
		mon.setCurHp(-100);
	}


}
