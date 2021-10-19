package controller;

public class StudentManage extends Manage{
	private static StudentManage instance = new StudentManage();
	private StudentManage() {};
	public static StudentManage getInstance() {
		return instance;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	
}
