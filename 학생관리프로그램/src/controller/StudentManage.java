package controller;

import java.util.ArrayList;

import models.School;
import models.Student;

public class StudentManage extends Manage {
	private static StudentManage instance = new StudentManage();

	private StudentManage() {
	};

	public static StudentManage getInstance() {
		return instance;
	}
	// 1.��ü �л� ����
	// 2.�л� ���� ��ȸ
	// 3.�л� �߰� //�л��̸� +������ �Է�(+�����й� ����)
	// 4.�л� ����

	private DepartmentMange dm = DepartmentMange.getInstance();
	private ArrayList<Student> students = new ArrayList<>();

	@Override
	public void run() {
		printMenu();
	}

	private void printMenu() {
		System.out.printf("=== [%s SCHOOL] ===\n",StudentManager.getSchoolName());
		while (true) {
			System.out.println("{1.��ü �л� ����} {2.�л� ���� ��ȸ} {3.�л� �߰�} {4.�л� ����} {5.�����ϱ�}");
			System.out.println("===================");
			int sel = StudentManager.selInt();

			if (sel == 1) {
				printAllStudent();
			} else if (sel == 2) {
				searchStudent();
			} else if (sel == 3) {
				addStudent();
			} else if (sel == 4) {
				delStudent();
			} else if (sel == 5) {
				break;
			}
		}
	}

	private void delStudent() {
		int idx = checkHakbun();
		if(idx < 0) {
			System.out.println("�ش� �й��� �������� �ʽ��ϴ�.");
		}else {
			System.out.printf("[�̸� : %s]�� �����մϴ�.",this.students.get(idx).getName());
			this.students.remove(idx);
		}
	}

	private void addStudent() {
		System.out.print("�߰��� �л��� �̸��� �Է��ϼ���:");
		String name = StudentManager.scan.next();
		int hakbun = hakbunmaker();
		dm.printDepartment();
		System.out.print("����");

		School major = null;
		int sel = StudentManager.selInt() - 1;
		int size = dm.departmentSize();
		if (sel >= 0 && sel < size) {
			major = dm.getMajor(sel);
		}

		this.students.add(new Student(name, hakbun, major));
	}

	private int hakbunmaker() {
		while (true) {
			int hak = StudentManager.ran.nextInt(8999) + 1000;
			int check = -1;
			int size = this.studentSize();
			for (int i = 0; i < size; i++) {
				if (hak == this.students.get(i).getHakbun()) {
					check = i;
				}
			}
			if (check == -1) {
				return hak;
			}
		}
	}

	private int checkHakbun() {
		int size = this.studentSize();
		int hakbun = -1;
		System.out.print("�й� �Է� : ");
		String temp = StudentManager.scan.next();

		try {
			hakbun = Integer.parseInt(temp);
		} catch (Exception e) {
			return -2;
		}

		int idx = -1;
		for (int i = 0; i < size; i++) {
			if (hakbun == this.students.get(i).getHakbun()) {
				idx = i;
			}
		}
		return idx;
	}

	private void searchStudent() {
		int idx = checkHakbun();
		if(idx < 0) {
			System.out.println("�ش� �й��� �������� �ʽ��ϴ�.");
		}else {
			System.out.println("�л� ���� >>>");
			this.students.get(idx).printInfo();
		}

	}

	private void printAllStudent() {
		System.out.println("--------------");
		int size = this.studentSize();
		if (size == 0) {
			System.out.println("�л� ����.");
		} else {
			for (int i = 0; i < size; i++) {
				this.students.get(i).printInfo();
			}
		}
		System.out.println("--------------");
	}

	private int studentSize() {
		return this.students.size();
	}

}
