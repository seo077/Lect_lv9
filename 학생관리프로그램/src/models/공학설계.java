package models;

public class ���м��� extends Subject{

	public ���м���(String subName) {
		super(subName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void printInfo() {
		System.out.printf("[���� : %s] [���� : %d��]\n",super.getSubName(),super.getScore());
	}

}
