package models;

public class �����ʽǽ� extends Subject implements �����а�_����{

	public �����ʽǽ�(String subName) {
		super(subName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void printInfo() {
		System.out.printf("[���� : %s] [���� : %d��]\n",super.getSubName(),super.getScore());
	}

}
