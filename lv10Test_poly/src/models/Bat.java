package models;

public class Bat extends Monster implements Enchanted{

	public Bat(String name, int maxHp, int att) {
		super(name, maxHp, att);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void skill(Player player) {
		// ���ݹ��� �÷��̾�� ���� �Ͽ� �� �� ħ��
		System.out.printf("[%s]�� ��ų�� ����մϴ�.\n",super.getName());
		System.out.printf("[%s]�� [%s]���� ħ�� ������ �����մϴ�.\n",super.getName(),player.getName());
		player.setMute(true);
	}

	@Override
	public void attacked(String attacker,int att) {
		// TODO Auto-generated method stub
		System.out.printf("[%s]��  [%s]���� %d�� �������� �����ϴ�.\n",attacker,super.getName(),att);
		super.setHp(super.getHp()-att);
		if(super.getHp() <= 0) {
			System.out.printf("[%s]�� ����Ͽ����ϴ�.\n",super.getName());
			super.setDie(true);
		}
	}

}
