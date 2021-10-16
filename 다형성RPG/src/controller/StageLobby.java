package controller;

public class StageLobby extends Stage{
	
	private static StageLobby instance = new StageLobby();
	private void  StageLobby() {}
	public static StageLobby getInstance() {
		return instance;
	}

	@Override
	public boolean run() {
		while(true) {
			System.out.println("=====[LOBBY]=====");
			System.out.println("[1.전투] [2.종료]");
			String temp = GameManager.scan.next();
			int sel = GameManager.checkInt(temp);
			
			if(sel == 1) {
				return true;
			}else if(sel == 2) {
				System.out.println("게일을 종료합니다.");
				return false;
			}else {
				System.out.println("다시 입력하세요");
				continue;
			}
			
		}
	}


}
