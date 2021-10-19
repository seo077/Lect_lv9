package controller;

import java.util.ArrayList;

import models.School;
import models.�����а�;
import models.�ǿ���;
import models.��ǻ�Ͱ��а�;
import models.ȭ�л������а�;

public class DepartmentMange extends Manage{
	//1.��ü �а� ����
	//2.�а� �߰�
	//3.�а� ����
	private static DepartmentMange instance = new DepartmentMange();
	private DepartmentMange() {};
	public static DepartmentMange getInstance(){
		return instance;
	}
	
	
	private ArrayList<School>schoolDepartment = new ArrayList<>();
	
	public void setData() {
		//�к� �̸�, �ִ� ����
		this.schoolDepartment.add(new �����а�("�����а�", 8));
		this.schoolDepartment.add(new �ǿ���("�ǿ���", 15));
		this.schoolDepartment.add(new ��ǻ�Ͱ��а�("��ǻ�Ͱ��а�", 20));
		this.schoolDepartment.add(new ȭ�л������а�("ȭ�л������а�", 25));
	}
	
	@Override
	public void run() {
		printMenu();
	}
	
	private void printMenu() {
		System.out.printf("=== [%s SCHOOL] ===\n",StudentManager.getSchoolName());
		while(true) {
			System.out.println("{1.��ü �а� ����} {2.�����ϱ�}");
			System.out.println("===================");
			int sel = StudentManager.selInt();
			
			if(sel == 1) {
				printDepartment();
			}else if(sel == 2) {
				break;
			}
		}
	}
	
	public int departmentSize() {
		return this.schoolDepartment.size();
	}
	
	public void printDepartment() {
		System.out.println("--------------");
		int size = this.departmentSize();
		for(int i=0;i<size;i++) {
			System.out.printf("[%d] ",i+1);
			this.schoolDepartment.get(i).printInfo();
		}
		System.out.println("--------------");
	}
	public School getMajor(int sel) {
		return this.schoolDepartment.get(sel);
	}
}
