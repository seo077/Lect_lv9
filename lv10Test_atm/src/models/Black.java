package models;

public class Black extends Customer{ //은행 고객 중 최하위 등급(등급에 따라 출금,이체한도 다름)


	public Black(String grade, String name, String id, String pw, String acc) {
		super(grade, name, id, pw, acc);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setLimit() {
		super.setTransferLimit(30);
		super.setWithdrawLimit(100);
	}

	@Override
	public void printInfo() {
		System.out.printf("[등급 : %s] [이름 : %s] [아이디 : %s] [비밀번호 : %s]\n",super.getGrade(),super.getName(),super.getId(),super.getPw());
		super.accsCnt();
	}


}
