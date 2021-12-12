package controller;

public class TitleStage extends Stage{

	private static TitleStage instance = new TitleStage();
	private TitleStage() {};
	public static TitleStage getInstance() {
		return instance;
	}
	
	
	@Override
	public void init() {
	}

	@Override
	public boolean nextStage() {
		System.out.println("=== TEXT RPG ===");
		System.out.println("[시작]을 입력하세요");
		String start = GameManager.scan.next();
		
		if(start.equals("시작")) {
			GameManager.nextStage = "LOBBY";
		}else {
			GameManager.nextStage = "";
		}
		return true;
	}

}
