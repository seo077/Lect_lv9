package models;

public class 의학개론 extends Subject implements 의예과_과목{

	public 의학개론(String subName) {
		super(subName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void printInfo() {
		System.out.printf("[과목 : %s] [성적 : %d점]\n",super.getSubName(),super.getScore());
	}

}
