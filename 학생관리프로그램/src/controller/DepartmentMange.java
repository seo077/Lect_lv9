package controller;

public class DepartmentMange extends Manage{
	private static DepartmentMange instance = new DepartmentMange();
	private DepartmentMange() {};
	public static DepartmentMange getInstance(){
		return instance;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
