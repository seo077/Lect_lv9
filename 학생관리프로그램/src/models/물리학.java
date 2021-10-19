package models;

public class 물리학 extends Subject implements 컴퓨터공학과_과목{

	public 물리학(String subName) {
		super(subName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void printInfo() {
		System.out.printf("[과목 : %s] [성적 : %d점]\n",super.getSubName(),super.getScore());
	}

}
