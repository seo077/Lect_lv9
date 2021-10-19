package controller;

public class LoginManage extends Manage{
	private static LoginManage instance = new LoginManage();
	private LoginManage() {};
	public static LoginManage getInstance() {
		return instance;
	}
	
	private StudentManage sm = StudentManage.getInstance();
	private int log = -1;
	
	//�̸�,�й����� �α���
	//�޴� ����
	//1.�����߰�
	//2.���� �߰�
	//3.������ ����
	@Override
	public void run() {
		if(login()) {
			printMenu();
		}else {
			return;
		}
		
	}
	
	private boolean login() {
		System.out.print("�̸� �Է� : ");
		String name = StudentManager.scan.next();
		
		int stuIdx = sm.checkHakbun();
		
		if(stuIdx >= 0 && name.equals(sm.getName(stuIdx))) {
			this.log = stuIdx;
			System.out.printf("[%s]�� �α��� ...\n",name);
			return true;
		}
		System.out.println("�̸��� �й��� Ȯ���ϼ���.");
		return false;
	}
	
	private void printMenu() {
		System.out.printf("=== [%s SCHOOL] ===\n",StudentManager.getSchoolName());
		while (true) {
			System.out.println("{1.���� �߰�} {2.���� �߰�} {3.������ ����} {4.�α� �ƿ�}");
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
