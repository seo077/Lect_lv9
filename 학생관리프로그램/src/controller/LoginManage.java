package controller;

public class LoginManage extends Manage{
	private static LoginManage instance = new LoginManage();
	private LoginManage() {};
	public static LoginManage getInstance() {
		return instance;
	}
	
	//�̸�,�й����� �α���
	//�޴� ����
	//1.�����߰�
	//2.���� �߰�
	//3.������ ����
	@Override
	public void run() {
		System.out.printf("=== [%s SCHOOL] ===\n",StudentManager.getSchoolName());
		System.out.println("===================");
		// TODO Auto-generated method stub
		
	}
}
