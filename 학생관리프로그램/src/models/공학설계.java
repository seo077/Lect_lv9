package models;

public class ���м��� extends Subject implements ��ǻ�Ͱ��а�_����,�����а�_����{

	public ���м���(String subName) {
		super(subName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void printInfo() {
		System.out.printf("[���� : %s] [���� : %d��]\n",super.getSubName(),super.getScore());
	}

}
