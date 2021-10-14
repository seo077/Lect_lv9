package models;

public class ZombieKing extends Unit implements Resilient,GiveItems{

	int shield;
	public ZombieKing(String name,int hp,int max_hp,int att,int def,int pos,int shield) {
		super(name, hp,max_hp, att, def, pos);
		this.shield = shield;
	}
	
	@Override
	void damage(String attackerName, int dam) {
		System.out.printf("[이름 : %s]의 공격!\n",attackerName);
		System.out.printf("%d의 데미지!\n",dam);
		if(this.shield>0) {
			this.shield-=dam;
		}else {
			setHp(-dam);
		}
		System.out.printf("[이름 : %s]의 남은 체력 (쉴드 : %d): %d\n",getName(),getHp(),this.shield);
	}

	@Override
	public void resile() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void printInfo() {
		System.out.printf("[이름] : %s     [체력] : %d/%d\n",super.getName(),super.getHp(),super.getMax_hp());
		System.out.printf("[공격력] : %d  [방어력] : %d  [위치] : %d\n",super.getAtt(),super.getDef(),super.getPos());
		System.out.printf("[쉴드] : %d\n",this.shield);
	}
	
}
