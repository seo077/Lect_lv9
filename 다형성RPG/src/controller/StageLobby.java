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
			System.out.println("[1.����] [2.����]");
			String temp = GameManager.scan.next();
			int sel = GameManager.checkInt(temp);
			
			if(sel == 1) {
				return true;
			}else if(sel == 2) {
				System.out.println("������ �����մϴ�.");
				return false;
			}else {
				System.out.println("�ٽ� �Է��ϼ���");
				continue;
			}
			
		}
	}


}
