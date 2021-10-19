package models;

public class 화학생물공학과 extends School implements Minor{

	public 화학생물공학과(String departmentName, int numberOfStudents) {
		super(departmentName, numberOfStudents);
	}

	@Override
	public void printInfo() {
		System.out.printf("[%s] [%d/%d명]\n",super.getDepartmentName(),super.getCurNumberOfStudents(),super.getMaxNumberOfStudents());
	}

	@Override
	public boolean checkSubject(Subject sub) {
		try {
			화학생물공학과_과목 subject = (화학생물공학과_과목)sub;
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	@Override
	public void prinAlltStudents() {
		// TODO Auto-generated method stub
		
	}

}
