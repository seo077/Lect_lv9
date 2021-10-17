package models;

public class M_Orc extends Monster implements Cheatable{

	public M_Orc(String name, int maxHp, int power) {
		super(name, maxHp, power);
		// TODO Auto-generated constructor stub
	}

	public void skill(Player player) {  // 스킬 사용 시 -> 한명에게 2배의 데미지+기절
		System.out.printf("[%s에게 %d의 데미지를 입히고 기절시킵니다]\n",player.getName(),this.getPower());
		System.out.printf("[%s는 다음 턴에 공격을 할 수 없습니다.]\n",player.getName());
		player.setFaint(true);
		player.setCurHp(-(this.getPower()*2));
	}



	
		
		
	
}
