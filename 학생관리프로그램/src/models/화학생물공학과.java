package models;

public class ȭ�л������а� extends School implements Minor{

	public ȭ�л������а�(String departmentName, int numberOfStudents) {
		super(departmentName, numberOfStudents);
	}

	@Override
	public void printInfo() {
		System.out.printf("[%s] [%d/%d��]\n",super.getDepartmentName(),super.getCurNumberOfStudents(),super.getMaxNumberOfStudents());
	}

	@Override
	public boolean checkSubject(Subject sub) {
		try {
			ȭ�л������а�_���� subject = (ȭ�л������а�_����)sub;
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
