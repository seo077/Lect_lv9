package models;

public class ��ǻ�Ͱ��а� extends School implements Minor{

	public ��ǻ�Ͱ��а�(String departmentName, int numberOfStudents) {
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
			��ǻ�Ͱ��а�_���� subject = (��ǻ�Ͱ��а�_����)sub;
			return true;
		} catch (Exception e) {
		}
		return false;
	}

}
