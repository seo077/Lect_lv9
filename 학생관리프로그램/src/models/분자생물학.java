package models;

public class 분자생물학 extends Subject implements 화학생물공학과_과목,의예과_과목{

	public 분자생물학(String subName) {
		super(subName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void printInfo() {
		System.out.printf("[과목 : %s] [성적 : %d점]\n",super.getSubName(),super.getScore());
	}

}
