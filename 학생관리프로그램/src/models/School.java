package models;

import java.util.ArrayList;

public abstract class School {
	private String departmentName; //�а� �̸�
	private int curNumberOfStudents; //���� �а� �ο�
	private int maxNumberOfStudents; //��ü �а� �ο�
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
