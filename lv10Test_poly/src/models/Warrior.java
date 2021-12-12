package models;

public class Warrior extends Player implements Healable{

	public Warrior(String name, int maxHp, int att) {
		super(name, maxHp, att);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void skill(Monster monster) {
		if(!super.isMute()) {
			// ������ ��ų : ���ݷ� 100
			monster.attacked(super.getName(), 100);
			if(monster.getHp() < 0) {
				monster.setDie(true);
			}
		}else {
			System.out.print("ħ���� >>>\n");
			super.setMute(false);
		}
	}

	@Override
	public void attacked(String attacker,int att) {
			System.out.printf("[%s]��  [%s]���� %d�� �������� �����ϴ�.\n",attacker,super.getName(),att);
			super.setHp(super.getHp()-att);
			if(super.getHp() <= 0) {
				System.out.printf("[%s]�� ����Ͽ����ϴ�.\n",super.getName());
				super.setDie(true);
			}
	}

}
