package controller;

public class StageTitle extends Stage{

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		System.out.println("==== 다형성 RPG ====");
		System.out.println("[시작]을 입력하세요");
		String start = GameManager.scan.next();
		
		if(start.equals("시작")) {
			
		}
	}

}
