package models;

public interface Faint {//오크의 스킬 공격(플레이어 모두 적용) --> 게임 한 턴이 도는 동안 기절
	
	public void setFaint(boolean bool);
	public boolean getFaint();
}
