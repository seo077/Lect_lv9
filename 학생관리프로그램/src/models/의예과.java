package models;

public class �ǿ��� extends School{

	public �ǿ���(String departmentName, int numberOfStudents) {
		super(departmentName, numberOfStudents);
	}

	@Override
	public void printInfo() {
		System.out.printf("[%s] [%d/%d��]\n",super.getDepartmentName(),super.getCurNumberOfStudents(),super.getMaxNumberOfStudents());
	}

	@Override
	public void prinAlltStudents() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean checkSubject(Subject sub) {
		try {
			�ǿ���_���� subject = (�ǿ���_����)sub;
			return true;
		} catch (Exception e) {
		
		}
		return false;
	}

}
