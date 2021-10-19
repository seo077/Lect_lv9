package models;

public class 컴퓨터공학과 extends School implements Minor{

	public 컴퓨터공학과(String departmentName, int numberOfStudents) {
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
	@Override
	public boolean checkSubject(Subject sub) {
		try {
			컴퓨터공학과_과목 subject = (컴퓨터공학과_과목)sub;
			return true;
		} catch (Exception e) {
		}
		return false;
	}

}
