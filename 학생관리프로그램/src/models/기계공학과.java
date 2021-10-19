package models;

public class 기계공학과 extends School implements Minor{

	public 기계공학과(String departmentName, int numberOfStudents) {
		super(departmentName, numberOfStudents);
		// TODO Auto-generated constructor stub
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
