package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import models.School;
import models.Student;
import models.Subject;
import models.�����а�;
import models.�ǿ���;
import models.��ǻ�Ͱ��а�;
import models.ȭ�л������а�;

public class StudentManager {
	public Scanner scan = new Scanner(System.in);
	public Random ran = new Random();
	
	private static StudentManager instance = new StudentManager();
	private StudentManager() {}
	public static StudentManager getInstance() {
		return instance;
	}
	
	private HashMap<Integer, Manage> menu = new HashMap<>();
	private String schoolName = "";
	private ArrayList<School>schoolDepartment = new ArrayList<>();
	private ArrayList<Student>students = new ArrayList<>();
	
	public void run() {
		setData();
		while(true) {
			if(!printMenu()) {
				break;
			}
		}
	}
	
	private void setData() {
		this.schoolName = "�׸�";
		//�к� �̸�, �ִ� ����
		this.schoolDepartment.add(new �����а�("�����а�", 8));
		this.schoolDepartment.add(new �ǿ���("�ǿ���", 15));
		this.schoolDepartment.add(new ��ǻ�Ͱ��а�("��ǻ�Ͱ��а�", 20));
		this.schoolDepartment.add(new ȭ�л������а�("ȭ�л������а�", 25));
		
		this.menu.put(1, DepartmentMange.getInstance());
		this.menu.put(2, StudentManage.getInstance());
		this.menu.put(3, LoginManage.getInstance());
	}
	
	private boolean printMenu() {
		while(true) {
			System.out.printf("=== [%s SCHOOL] ===\n",this.schoolName);
			String menu = "  {1.�а� ����}\n  {2.�л� ����}\n  {3.�α���}\n  {4.�����ϱ�}";
			System.out.println(menu);
			System.out.println("===================");
			
			int sel = selInt();
			if(sel > 0 && sel<4) {
				this.menu.get(sel).run();
			}else if(sel == 4) {
				return false;
			}else {
				continue;
			}
			return true;
			
//			if(sel == 0) {
//				//�й��Է� --> �л� ���� ��ȸ
//				
//			}else if(sel == 1) {
//				//1.��ü �а� ����
//				//2.�а� �߰�
//				//3.�а� ����
//			}else if(sel == 2) {
//				//1.��ü �л� ����
				//2.�л� ���� ��ȸ
//				//3.�л� �߰� //�л��̸� +������ �Է�(+�����й� ����)
//				//4.�л� ����
//			}else if(sel == 3) {
//				//�̸�,�й����� �α���
//				//�޴� ����
//				//1.�����߰�
//				//2.���� �߰�
//				//3.������ ����
//				
//			}else if(sel == 4){
//				return false;
//			}else {
//				continue;
//			}
//			return true;
		}
	}
	
	private int selInt() {
		while(true) {
			System.out.print("���� : ");
			String temp = scan.next();
			try {
				int sel = Integer.parseInt(temp);
				return sel;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	
	private int departmentSize() {
		return this.schoolDepartment.size();
	}
	
	private void printDepartment() {
		System.out.println("--------------");
		int size = this.departmentSize();
		System.out.println(size);
		for(int i=0;i<size;i++) {
			System.out.printf("[%d] ",i+1);
			this.schoolDepartment.get(i).printInfo();
		}
		System.out.println("--------------");
	}
}
