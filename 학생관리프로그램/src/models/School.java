package models;

import java.util.ArrayList;

public abstract class School {
	private String departmentName; //학과 이름
	private int curNumberOfStudents; //현재 학과 인원
	private int maxNumberOfStudents; //전체 학과 인원
	private ArrayList<Student>stu;
	
	public School(String departmentName, int numberOfStudents) {
		this.departmentName = departmentName;
		this.maxNumberOfStudents = numberOfStudents;
		this.stu = new ArrayList<>();
	}
	
	public abstract void printInfo();
	
	public void prinAlltStudents() {
		//System.out.printf("");
	}
	
}
