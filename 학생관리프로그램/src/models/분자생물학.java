package models;

public class ���ڻ����� extends Subject implements ȭ�л������а�_����,�ǿ���_����{

	public ���ڻ�����(String subName) {
		super(subName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void printInfo() {
		System.out.printf("[���� : %s] [���� : %d��]\n",super.getSubName(),super.getScore());
	}

}
