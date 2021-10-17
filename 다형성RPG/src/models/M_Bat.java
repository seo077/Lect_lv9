package models;

public class M_Bat extends Monster implements Cheatable{

	public M_Bat(String name, int maxHp, int power) {
		super(name, maxHp, power);
		// TODO Auto-generated constructor stub
	}

	public void skill(Player player) { // 스킬 사용 시 -> 적 한명에게  침묵 시전
		System.out.printf("[%s에게 침묵을 시전합니다.]\n",player.getName());
		System.out.printf("[%s는 다음 턴에 공격을 할 수 없습니다.]\n",player.getName());
		player.setSilent(true);
	}
	

	
	
		
	
}
