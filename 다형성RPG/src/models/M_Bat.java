package models;

public class M_Bat extends Monster implements Cheatable{

	public M_Bat(String name, int maxHp, int power) {
		super(name, maxHp, power);
		// TODO Auto-generated constructor stub
	}

	public void skill(Player player) { // ��ų ��� �� -> �� �Ѹ���  ħ�� ����
		System.out.printf("[%s���� ħ���� �����մϴ�.]\n",player.getName());
		System.out.printf("[%s�� ���� �Ͽ� ������ �� �� �����ϴ�.]\n",player.getName());
		player.setSilent(true);
	}
	

	
	
		
	
}
