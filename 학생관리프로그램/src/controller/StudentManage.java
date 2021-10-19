package controller;

import java.util.ArrayList;

import models.Minor;
import models.School;
import models.Student;
import models.Subject;
import models.공학설계;
import models.공학수학;
import models.기계기초실습;
import models.물리학;
import models.분자생물학;
import models.세포생물학;
import models.알고리즘;
import models.의학개론;

public class StudentManage extends Manage {
	private static StudentManage instance = new StudentManage();

	private StudentManage() {
	};

	public static StudentManage getInstance() {
		return instance;
	}
	// 1.전체 학생 보기
	// 2.학생 정보 조회
	// 3.학생 추가 //학생이름 +전공만 입력(+랜덤학번 생성)
	// 4.학생 삭제

	private DepartmentMange dm = DepartmentMange.getInstance();
	private ArrayList<Student> students = new ArrayList<>();
	private ArrayList<Subject> subjects = new ArrayList<>();

	public void setData() {
		System.out.println("fsd");
		this.subjects.add(new 공학설계("공학설계"));
		this.subjects.add(new 공학수학("공학수학"));
		this.subjects.add(new 기계기초실습("기계기초실습"));
		this.subjects.add(new 물리학("물리학"));
		this.subjects.add(new 분자생물학("분자생물학"));
		this.subjects.add(new 세포생물학("세포생물학"));
		this.subjects.add(new 알고리즘("알고리즘"));
		this.subjects.add(new 의학개론("의학개론"));
		
		//
		this.students.add(new Student("홍길동", 1111, dm.getMajor(1), true, dm.getMajor(2), this.subjects.get(7)));
		this.students.add(new Student("홍희동", 3333, dm.getMajor(3), this.subjects.get(4)));
		this.students.add(new Student("홍둘리", 2222, dm.getMajor(2), true, dm.getMajor(2), this.subjects.get(6)));
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
			System.out.println("{1.전체 학생 보기} {2.학생 정보 조회} {3.학생 추가} {4.학생 삭제} {5.종료하기}");
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
			System.out.println("해당 학번은 존재하지 않습니다.");
		}else {
			System.out.printf("[이름 : %s]을 삭제합니다.",this.students.get(idx).getName());
			this.students.remove(idx);
		}
	}

	private void addStudent() {
		System.out.print("추가할 학생의 이름을 입력하세요:");
		String name = StudentManager.scan.next();
		int hakbun = hakbunmaker();
		dm.printDepartment();
		System.out.print("전공");

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
		System.out.print("학번 입력 : ");
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
			System.out.println("해당 학번은 존재하지 않습니다.");
		}else {
			System.out.println("학생 정보 >>>");
			this.students.get(idx).printInfo();
		}

	}

	private void printAllStudent() {
		System.out.println("--------------");
		int size = this.studentSize();
		if (size == 0) {
			System.out.println("학생 없음.");
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
			
			System.out.println("수강신청 가능한 전공 과목>>");
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
				System.out.println("수강신청 가능한 부전공 과목 >>");
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
				System.out.println("다시 입력하세요");
			}
			
		}
	}

	public void addScore(int log) {
		Student stu = this.students.get(log);
		int size = stu.subSize();
		while(true) {
			System.out.println("성적을 추가할 과목을 선택하세요: ");
			stu.printSub();
			int sel = StudentManager.selInt() -1;
			if(sel >= 0 && sel < size) {
				while(true) {
					System.out.print("성적을 입력하세요 : ");
					String temp = StudentManager.scan.next();
					
					try {
						int score = Integer.parseInt(temp);
						stu.addScore(sel,score);
						break;
					} catch (Exception e) {
						System.out.println("숫자를 입력하세요");
					}
					
				}
				break;
			}else {
				System.out.println("다시 입력하세요");
			}
			
		}
	}

	public void selMinor(int log) {
		if(this.students.get(log).getTakeMinor()) {
			System.out.println("이미 부전공이 존재합니다.");
		}else {
			dm.printDepartment();
			int sel = StudentManager.selInt()-1;
			
			try {
				Minor minor = (Minor)dm.getMajor(sel);
				this.students.get(log).selMinor(minor);
			} catch (Exception e) {
				System.out.println("부전공으로 선택할 수 없는 학과입니다.");
			}
		}
	}

}
