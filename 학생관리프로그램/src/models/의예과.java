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

	@Override
	public boolean checkSubject(Subject sub) {
		try {
			의예과_과목 subject = (의예과_과목)sub;
			return true;
		} catch (Exception e) {
		
		}
		return false;
	}

}
