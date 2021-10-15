package models;

import controller.Game;

public class Hero extends Unit implements Resilient{

	public Hero(String name,int hp,int max_hp,int att,int def,int pos) {
		super(name, hp,max_hp, att, def, pos);
	}
	
	@Override
	void damage(String attackerName,int dam) {
		System.out.printf("[�̸� : %s]�� ����!\n",attackerName);
		System.out.printf("%d�� ������!\n",dam);
		super.setHp(-dam);
		System.out.printf("[�̸� : %s]�� ���� ü�� : %d\n",getName(),getHp());
	}
	
	public int getPos() {
		return super.getPos();
	}

	@Override
	public void resile() { //ȸ��
		int std = super.getHp();
		int up = std/10*3;
		super.setHp(up);
		System.out.printf("ü���� %d��ŭ ȸ���մϴ�.\n",up);
		System.out.printf("[�̸� : %s]�� ü�� : %d\n",super.getName(),super.getHp());
	}
	
	public void eatItems(GiveItems items) { //���� ���̸� ������
		int ran = Game.ran.nextInt(3);
		Unit unit = (Unit)items;
		
		int std = unit.getMax_hp();
		if(ran == 0) { //ü�� ����
			int up = std/2;
			super.setHp(up);
		}else if(ran == 1) { //���ݷ� ����
			int up = std/10;
			super.setAtt(up);
		}else if(ran == 2) { //���� ����
			int up = std/10;
			super.setDef(up);
		}
		
	}

	@Override
	public void printInfo() {
		System.out.printf("[�̸�] : %s     [ü��] : %d/%d\n",super.getName(),super.getHp(),super.getMax_hp());
		System.out.printf("[���ݷ�] : %d  [����] : %d  [��ġ] : %d\n",super.getAtt(),super.getDef(),super.getPos());
	}

	public void setPos() {
		super.setPos();
	}

}
