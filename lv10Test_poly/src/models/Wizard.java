package models;

import java.util.ArrayList;

public class Wizard extends Player implements Healable{

	public Wizard(String name, int maxHp, int att) {
		super(name, maxHp, att);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void skill(Monster monster) {
		
	}
	
	public void skill(Monster monster, ArrayList<Monster>mons) {
		if(!super.isMute()) {
			// �������� ��ų : ���� + ���� �����ϵ��� ����( ������ �ɸ� �� �ִ� ���͸�)
			System.out.printf("[%s]��  [%s]���� ������ �ɾ����ϴ�.\n",super.getName(),monster.getName());
			try {
				Enchanted mon = (Enchanted)monster;
			} catch (Exception e) {
				System.out.printf("[%s]��  [%s]�� ������ ���ѽ��׽��ϴ�.\n",monster.getName(),super.getName());
				return;
			}
			System.out.printf("[%s]��  ���ΰ� �ڽ��� ������� �����մϴ�.\n",monster.getName());
			for(int i=0;i<mons.size();i++) {
				System.out.printf("[%s]��  [%s]���� %d�� �������� �����ϴ�.\n",monster.getName(),mons.get(i).getName(),monster.getAtt());
				mons.get(i).setHp(mons.get(i).getHp()-monster.getAtt());
				if(mons.get(i).getHp() <= 0) {
					System.out.printf("[%s]�� ����Ͽ����ϴ�.\n",mons.get(i).getName());
					mons.get(i).setDie(true);
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

}
