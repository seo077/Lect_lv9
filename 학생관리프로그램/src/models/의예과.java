package models;

public class 의예과 extends School{

	public 의예과(String departmentName, int numberOfStudents) {
		super(departmentName, numberOfStudents);
	}

	@Override
	public void printInfo() {
		System.out.printf("[%s] [%d/%d명]\n",super.getDepartmentName(),super.getCurNumberOfStudents(),super.getMaxNumberOfStudents());
	}

	@Override
	public void prinAlltStudents() {
		// TODO Auto-generated method stub
		
	}

}
