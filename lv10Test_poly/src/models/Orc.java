package models;

import java.util.ArrayList;

public class Orc extends Monster{

	public Orc(String name, int maxHp, int att) {
		super(name, maxHp, att);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void skill(Player player) {
		
		
	}
	public void skill(ArrayList<Player>players) {
		// �÷��̾� ��ο��� ���ݷ� 50���� ����
	
		System.out.printf("[%s]�� ��ų�� ����մϴ�.\n",super.getName());
		for(int i=0;i<players.size();i++) {
			System.out.printf("[%s]�� [%s]���� %d�� �������� �����ϴ�.\n",super.getName(),players.get(i).getName(),50);
			players.get(i).setHp(players.get(i).getHp()-50);
			if(players.get(i).getHp() <= 0) {
				System.out.printf("[%s]�� ����Ͽ����ϴ�.\n",players.get(i).getName());
				players.get(i).setDie(true);
			}
		}
		
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
