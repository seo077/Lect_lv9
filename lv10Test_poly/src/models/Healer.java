package models;

import java.util.ArrayList;

public class Healer extends Player{

	public Healer(String name, int maxHp, int att) {
		super(name, maxHp, att);
		// TODO Auto-generated constructor stub
	}
	
	public void skill(ArrayList<Player>player_list) {
		// ������ ��ų : ���� ġ��( ���� hp -> maxHp ) (ġ���� �� �ִ� �÷��̾)
		int num = player_list.size();
		if(!super.isMute()) {
			for(int i=0;i<num;i++) {
				Player p = player_list.get(i);
				try {
					Healable hp = (Healable)p;
					p.setHp(p.getMaxHp());
					System.out.printf("[%s]�� [%s] ġ�Ḧ �Ϸ��Ͽ����ϴ�.\n",super.getName(),p.getName());
					System.out.printf("+++[%s]�� hp : [%d]\n",p.getName(),p.getHp());
				} catch (Exception e) {
					System.out.printf("[%s]�� ġ�ᰡ �Ұ����մϴ�.\n",p.getName());
				}
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

	@Override
	public void skill(Monster monster) {
		// TODO Auto-generated method stub
		
	}




}
