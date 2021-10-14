package models;

public class Zombie extends Unit implements GiveItems{

	public Zombie(String name,int hp,int max_hp,int att,int def,int pos) {
		super(name, hp,max_hp, att, def, pos);
	}

	@Override
	void damage(String attackerName,int dam) {
		System.out.printf("[�̸� : %s]�� ����!\n",attackerName);
		System.out.printf("%d�� ������!\n",dam);
		setHp(-dam);
		System.out.printf("[�̸� : %s]�� ���� ü�� : %d\n",getName(),getHp());
	}
	
	@Override
	public void printInfo() {
		System.out.printf("[�̸�] : %s     [ü��] : %d/%d\n",super.getName(),super.getHp(),super.getMax_hp());
		System.out.printf("[���ݷ�] : %d  [����] : %d  [��ġ] : %d\n",super.getAtt(),super.getDef(),super.getPos());
	}
	
}
