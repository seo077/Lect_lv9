package controller;

public class StageTitle extends Stage{

	private static  StageTitle instance  = new StageTitle();
	private StageTitle() {}	
	public static StageTitle getInstance() {
		return instance;
	}
	
	

	@Override
	public boolean run() {
		System.out.println("==== ������ RPG ====");
		System.out.println("[����]�� �Է��ϼ���");
		String start = GameManager.scan.next();
		
		if(start.equals("����")) {
			return true;
			
		}
		return false;
	}

}
