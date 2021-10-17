package models;

public interface Silent { //박쥐의 스킬 공격(플레이어 모두 적용) --> 게임이 한 턴 도는 동안 침묵 유지
	
	public void setSilent(boolean bool);
	public boolean getSilent();
}
