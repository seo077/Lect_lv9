package models;

public class 기계기초실습 extends Subject implements 기계공학과_과목{

	public 기계기초실습(String subName) {
		super(subName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void printInfo() {
		System.out.printf("[과목 : %s] [성적 : %d점]\n",super.getSubName(),super.getScore());
	}

}
