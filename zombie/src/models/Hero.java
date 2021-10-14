package models;

public class Hero extends Unit{

	public Hero(String name,int hp,int att,int def,int pos) {
		super(name, hp, att, def, pos);
	}
	
	@Override
	void damage(String attackerName,int dam) {
		System.out.printf("[�̸� : %s]�� ����!\n",attackerName);
		System.out.printf("%d�� ������!\n",dam);
		setHp(dam);
		System.out.printf("[�̸� : %s]�� ���� ü�� : %d\n",getName(),getHp());
	}

}
