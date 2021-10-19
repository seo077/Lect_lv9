package models;

import java.util.ArrayList;

public abstract class School {
	private String departmentName; //�а� �̸�
	private int curNumberOfStudents; //���� �а� �ο�
	private int maxNumberOfStudents; //��ü �а� �ο�
	private ArrayList<Student>stu;
	
	public School(String departmentName, int maxNumberOfStudents) {
		this.departmentName = departmentName;
		this.maxNumberOfStudents = maxNumberOfStudents;
		this.stu = new ArrayList<>();
	}
	
	public abstract void printInfo();
	public abstract void prinAlltStudents();
	public abstract boolean checkSubject(Subject sub);
	
	public String getDepartmentName() {
		return this.departmentName;
	}
	public int getCurNumberOfStudents() {
		return this.curNumberOfStudents;
	}
	public int getMaxNumberOfStudents() {
		return this.maxNumberOfStudents;
	}

	public String getName() {
		return this.departmentName;
	}

	public void plusNum() {
		this.curNumberOfStudents ++;
	}
	public void setStu(Student stu) {
		
	}
}
