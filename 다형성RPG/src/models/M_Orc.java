package models;

public class M_Orc extends Monster implements Cheatable{

	public M_Orc(String name, int maxHp, int power) {
		super(name, maxHp, power);
		// TODO Auto-generated constructor stub
	}

	public void skill(Player player) {  // ��ų ��� �� -> �Ѹ��� 2���� ������+����
		System.out.printf("[%s���� %d�� �������� ������ ������ŵ�ϴ�]\n",player.getName(),this.getPower());
		System.out.printf("[%s�� ���� �Ͽ� ������ �� �� �����ϴ�.]\n",player.getName());
		player.setFaint(true);
		player.setCurHp(-(this.getPower()*2));
	}



	
		
		
	
}
