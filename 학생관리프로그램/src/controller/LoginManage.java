package controller;

public class LoginManage extends Manage{
	private static LoginManage instance = new LoginManage();
	private LoginManage() {};
	public static LoginManage getInstance() {
		return instance;
	}
	
	//이름,학번으로 로그인
	//메뉴 선택
	//1.과목추가
	//2.성적 추가
	//3.부전공 성택
	@Override
	public void run() {
		System.out.printf("=== [%s SCHOOL] ===\n",StudentManager.getSchoolName());
		System.out.println("===================");
		// TODO Auto-generated method stub
		
	}
}
