package models;

public class ZombieKing extends Unit implements Resilient,GiveItems{

	int shield;
	public ZombieKing(String name,int hp,int max_hp,int att,int def,int pos,int shield) {
		super(name, hp,max_hp, att, def, pos);
		this.shield = shield;
	}
	
	@Override
	void damage(String attackerName, int dam) {
		System.out.printf("[�̸� : %s]�� ����!\n",attackerName);
		System.out.printf("%d�� ������!\n",dam);
		if(this.shield>0) {
			this.shield-=dam;
		}else {
			setHp(-dam);
		}
		System.out.printf("[�̸� : %s]�� ���� ü�� (���� : %d): %d\n",getName(),getHp(),this.shield);
	}

	@Override
	public void resile() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void printInfo() {
		System.out.printf("[�̸�] : %s     [ü��] : %d/%d\n",super.getName(),super.getHp(),super.getMax_hp());
		System.out.printf("[���ݷ�] : %d  [����] : %d  [��ġ] : %d\n",super.getAtt(),super.getDef(),super.getPos());
		System.out.printf("[����] : %d\n",this.shield);
	}
	
}
