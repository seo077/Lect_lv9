package controller;

public class LobbyStage extends Stage{
	
	private static LobbyStage instance = new LobbyStage();
	private LobbyStage() {};
	public static LobbyStage getInstance() {
		return instance;
	}
	
	
	@Override
	public void init() {
	}
	@Override
	public boolean nextStage() {
		System.out.println("===== [LOBBY] =====");
		System.out.println("[1.전투] [2.종료]");
		String temp = GameManager.scan.next();
		int sel = -1;
		try {
			sel = Integer.parseInt(temp);
		} catch (Exception e) {
		}
		
		if(sel == 1) {
			GameManager.nextStage="BATTLE";
			return true;
		}else if(sel == 2) {
			GameManager.nextStage="";
			return true;
		}else {
			System.out.println("다시 입력하세요.");
		}
		return false;
	}
	



}
