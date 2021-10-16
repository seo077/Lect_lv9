package controller;

public class StageTitle extends Stage{

	private static  StageTitle instance  = new StageTitle();
	private StageTitle() {}	
	public static StageTitle getInstance() {
		return instance;
	}
	
	

	@Override
	public boolean run() {
		System.out.println("==== 다형성 RPG ====");
		System.out.println("[시작]을 입력하세요");
		String start = GameManager.scan.next();
		
		if(start.equals("시작")) {
			return true;
			
		}
		return false;
	}

}
