package models;

public class 공학설계 extends Subject implements 컴퓨터공학과_과목,기계공학과_과목{

	public 공학설계(String subName) {
		super(subName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void printInfo() {
		System.out.printf("[과목 : %s] [성적 : %d점]\n",super.getSubName(),super.getScore());
	}

}
