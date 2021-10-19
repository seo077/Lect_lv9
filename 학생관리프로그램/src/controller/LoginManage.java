package controller;

public class LoginManage extends Manage{
	private static LoginManage instance = new LoginManage();
	private LoginManage() {};
	public static LoginManage getInstance() {
		return instance;
	}
	
	private StudentManage sm = StudentManage.getInstance();
	private int log = -1;
	
	//이름,학번으로 로그인
	//메뉴 선택
	//1.과목추가
	//2.성적 추가
	//3.부전공 성택
	@Override
	public void run() {
		if(login()) {
			printMenu();
		}else {
			return;
		}
		
	}
	
	private boolean login() {
		System.out.print("이름 입력 : ");
		String name = StudentManager.scan.next();
		
		int stuIdx = sm.checkHakbun();
		
		if(stuIdx >= 0 && name.equals(sm.getName(stuIdx))) {
			this.log = stuIdx;
			System.out.printf("[%s]님 로그인 ...\n",name);
			return true;
		}
		System.out.println("이름과 학번을 확인하세요.");
		return false;
	}
	
	private void printMenu() {
		System.out.printf("=== [%s SCHOOL] ===\n",StudentManager.getSchoolName());
		while (true) {
			System.out.println("{1.과목 추가} {2.성적 추가} {3.부전공 선택} {4.로그 아웃}");
			System.out.println("===================");
			int sel = StudentManager.selInt();

			if (sel == 1) {
				sm.addSubject(this.log);
			} else if (sel == 2) {
				sm.addScore(this.log);
			} else if (sel == 3) {
				sm.selMinor(this.log);
			} else if (sel == 4) {
				this.log = -1;
				break;
			} 
		}
	}
}
