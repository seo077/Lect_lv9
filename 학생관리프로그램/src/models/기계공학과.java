package models;

public class �����а� extends School implements Minor{

	public �����а�(String departmentName, int numberOfStudents) {
		super(departmentName, numberOfStudents);
		// TODO Auto-generated constructor stub
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
			�����а�_���� subject = (�����а�_����)sub;
			return true;
		} catch (Exception e) {
		
		}
		return false;
	}

}
