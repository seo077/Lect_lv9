package controller;

import java.util.ArrayList;

import models.Minor;
import models.School;
import models.Student;
import models.Subject;
import models.���м���;
import models.���м���;
import models.�����ʽǽ�;
import models.������;
import models.���ڻ�����;
import models.����������;
import models.�˰���;
import models.���а���;

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
	private ArrayList<Subject> subjects = new ArrayList<>();

	public void setData() {
		System.out.println("fsd");
		this.subjects.add(new ���м���("���м���"));
		this.subjects.add(new ���м���("���м���"));
		this.subjects.add(new �����ʽǽ�("�����ʽǽ�"));
		this.subjects.add(new ������("������"));
		this.subjects.add(new ���ڻ�����("���ڻ�����"));
		this.subjects.add(new ����������("����������"));
		this.subjects.add(new �˰���("�˰���"));
		this.subjects.add(new ���а���("���а���"));
		
		//
		this.students.add(new Student("ȫ�浿", 1111, dm.getMajor(1), true, dm.getMajor(2), this.subjects.get(7)));
		this.students.add(new Student("ȫ��", 3333, dm.getMajor(3), this.subjects.get(4)));
		this.students.add(new Student("ȫ�Ѹ�", 2222, dm.getMajor(2), true, dm.getMajor(2), this.subjects.get(6)));
		dm.plusCurNumberOfStudent(1);
		dm.plusCurNumberOfStudent(2);
		dm.plusCurNumberOfStudent(3);
			
	}
	
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
			dm.plusCurNumberOfStudent(sel);
		}

		this.students.add(new Student(name, hakbun, major));
		
		Student stu = this.students.get(this.students.size()-1);
		dm.addStudent(sel,stu);
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

	public int checkHakbun() {
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

	public String getName(int stuIdx) {
		return this.students.get(stuIdx).getName();
	}

	public void addSubject(int log) {
		Student stu = this.students.get(log);
		int size = this.subjects.size();
		while(true) {
			ArrayList<Integer>tempSub = new ArrayList<>();
			
			System.out.println("������û ������ ���� ����>>");
			int idx = 1;
			for(int i=0;i<size;i++) {
				if(this.students.get(log).getMajor().checkSubject(this.subjects.get(i))) {
					System.out.printf("(%d)",idx);
					this.subjects.get(i).printInfo();
					tempSub.add(i);
					idx++;
				}
			}
			if(this.students.get(log).getTakeMinor()) {
				System.out.println("������û ������ ������ ���� >>");
				for(int i=0;i<size;i++) {
					if(this.students.get(log).getMinor().checkSubject(this.subjects.get(i))) {
						System.out.printf("(%d)",idx);
						this.subjects.get(i).printInfo();
						tempSub.add(i);
						idx++;
					}
				}
			}
			
			int sel = StudentManager.selInt()-1;
			if(sel >= 0 && sel <idx) {
				this.students.get(log).addSub(this.subjects.get(tempSub.get(sel)));
				break;
			}else {
				System.out.println("�ٽ� �Է��ϼ���");
			}
			
		}
	}

	public void addScore(int log) {
		Student stu = this.students.get(log);
		int size = stu.subSize();
		while(true) {
			System.out.println("������ �߰��� ������ �����ϼ���: ");
			stu.printSub();
			int sel = StudentManager.selInt() -1;
			if(sel >= 0 && sel < size) {
				while(true) {
					System.out.print("������ �Է��ϼ��� : ");
					String temp = StudentManager.scan.next();
					
					try {
						int score = Integer.parseInt(temp);
						stu.addScore(sel,score);
						break;
					} catch (Exception e) {
						System.out.println("���ڸ� �Է��ϼ���");
					}
					
				}
				break;
			}else {
				System.out.println("�ٽ� �Է��ϼ���");
			}
			
		}
	}

	public void selMinor(int log) {
		if(this.students.get(log).getTakeMinor()) {
			System.out.println("�̹� �������� �����մϴ�.");
		}else {
			dm.printDepartment();
			int sel = StudentManager.selInt()-1;
			
			try {
				Minor minor = (Minor)dm.getMajor(sel);
				this.students.get(log).selMinor(minor);
			} catch (Exception e) {
				System.out.println("���������� ������ �� ���� �а��Դϴ�.");
			}
		}
	}

}
