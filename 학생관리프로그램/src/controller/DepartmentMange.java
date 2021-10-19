package controller;

import java.util.ArrayList;

import models.School;
import models.기계공학과;
import models.의예과;
import models.컴퓨터공학과;
import models.화학생물공학과;

public class DepartmentMange extends Manage{
	//1.전체 학과 보기
	//2.학과 추가
	//3.학과 삭제
	private static DepartmentMange instance = new DepartmentMange();
	private DepartmentMange() {};
	public static DepartmentMange getInstance(){
		return instance;
	}
	
	
	private ArrayList<School>schoolDepartment = new ArrayList<>();
	
	public void setData() {
		//학부 이름, 최대 정원
		this.schoolDepartment.add(new 기계공학과("기계공학과", 8));
		this.schoolDepartment.add(new 의예과("의예과", 15));
		this.schoolDepartment.add(new 컴퓨터공학과("컴퓨터공학과", 20));
		this.schoolDepartment.add(new 화학생물공학과("화학생물공학과", 25));
	}
	
	@Override
	public void run() {
		printMenu();
	}
	
	private void printMenu() {
		System.out.printf("=== [%s SCHOOL] ===\n",StudentManager.getSchoolName());
		while(true) {
			System.out.println("{1.전체 학과 보기} {2.종료하기}");
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
