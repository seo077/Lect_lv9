package models;

public class M_Wolf extends Monster {

	public M_Wolf(String name, int maxHp, int power) {
		super(name, maxHp, power);
		// TODO Auto-generated constructor stub
	}

	public void skill(Player player) { // ��ų ��� �� -> �� ��ü���� ���ݷ��� ���� ������
		System.out.printf("[%s���� %d�� �������� �����ϴ�.]\n",player.getName(),this.getPower()/2);
		player.setCurHp(-(this.getPower()/2));
	}

	

}
