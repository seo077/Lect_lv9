package models;

public class Zombie extends Unit implements GiveItems{

	public Zombie(String name,int hp,int max_hp,int att,int def,int pos) {
		super(name, hp,max_hp, att, def, pos);
	}

	@Override
	void damage(String attackerName,int dam) {
		System.out.printf("[이름 : %s]의 공격!\n",attackerName);
		System.out.printf("%d의 데미지!\n",dam);
		setHp(-dam);
		System.out.printf("[이름 : %s]의 남은 체력 : %d\n",getName(),getHp());
	}
	
	@Override
	public void printInfo() {
		System.out.printf("[이름] : %s     [체력] : %d/%d\n",super.getName(),super.getHp(),super.getMax_hp());
		System.out.printf("[공격력] : %d  [방어력] : %d  [위치] : %d\n",super.getAtt(),super.getDef(),super.getPos());
	}
	
}
