package models;

public class �˰��� extends Subject implements ��ǻ�Ͱ��а�_����{

	public �˰���(String subName) {
		super(subName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void printInfo() {
		System.out.printf("[���� : %s] [���� : %d��]\n",super.getSubName(),super.getScore());
	}

}
