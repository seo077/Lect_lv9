package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import models.School;
import models.Student;
import models.Subject;
import models.기계공학과;
import models.의예과;
import models.컴퓨터공학과;
import models.화학생물공학과;

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
		this.schoolName = "그린";
		//학부 이름, 최대 정원
		this.schoolDepartment.add(new 기계공학과("기계공학과", 8));
		this.schoolDepartment.add(new 의예과("의예과", 15));
		this.schoolDepartment.add(new 컴퓨터공학과("컴퓨터공학과", 20));
		this.schoolDepartment.add(new 화학생물공학과("화학생물공학과", 25));
		
		this.menu.put(1, DepartmentMange.getInstance());
		this.menu.put(2, StudentManage.getInstance());
		this.menu.put(3, LoginManage.getInstance());
	}
	
	private boolean printMenu() {
		while(true) {
			System.out.printf("=== [%s SCHOOL] ===\n",this.schoolName);
			String menu = "  {1.학과 관리}\n  {2.학생 관리}\n  {3.로그인}\n  {4.종료하기}";
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
//				//학번입력 --> 학생 정보 조회
//				
//			}else if(sel == 1) {
//				//1.전체 학과 보기
//				//2.학과 추가
//				//3.학과 삭제
//			}else if(sel == 2) {
//				//1.전체 학생 보기
				//2.학생 정보 조회
//				//3.학생 추가 //학생이름 +전공만 입력(+랜덤학번 생성)
//				//4.학생 삭제
//			}else if(sel == 3) {
//				//이름,학번으로 로그인
//				//메뉴 선택
//				//1.과목추가
//				//2.성적 추가
//				//3.부전공 성택
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
			System.out.print("선택 : ");
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
