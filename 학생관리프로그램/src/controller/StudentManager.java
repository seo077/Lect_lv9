package controller;

import java.util.ArrayList;
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
	
	private boolean printMenu() {
		while(true) {
			System.out.printf("=== [%s SCHOOL] ===\n",this.schoolName);
			String menu = "  {0.학생 정보 조회}\n  {1.학과 관리}\n  {2.학생 관리}\n  {3.성적관리}\n  {4.부전공 선택}\n  {6.종료하기}";
			System.out.println(menu);
			System.out.println("===================");
			
			int sel = selInt();
			if(sel == 0) {
				//학번입력 --> 학생 정보 조회
				
			}else if(sel == 1) {
				//1.전체 학과 보기
				//2.학과 추가
				//3.학과 삭제
				printDepartment();
			}else if(sel == 2) {
				//1.전체 학생 보기
				//2.학생 추가 //학생이름 +전공만 입력(+랜덤학번 생성)
				//3.학생 삭제
			}else if(sel == 3) {
				//1.과목추가
				//2.성적 추가
				
			}else if(sel == 4) {
				//부전공 선택
				
			}else if(sel == 5) {
				//1.부전공 입력
				//2.과목입력
				//3.성적입력
			}else if(sel == 6){
				return false;
			}else {
				continue;
			}
			return true;
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
	private void setData() {
		this.schoolName = "그린";
		//학부 이름, 최대 정원
		this.schoolDepartment.add(new 기계공학과("기계공학과", 8));
		this.schoolDepartment.add(new 의예과("의예과", 15));
		this.schoolDepartment.add(new 컴퓨터공학과("컴퓨터공학과", 20));
		this.schoolDepartment.add(new 화학생물공학과("화학생물공학과", 25));
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
