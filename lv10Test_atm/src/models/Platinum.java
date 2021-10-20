package models;

public class Platinum extends Customer { // 은행 고객 중 두번쨰로 낮은 등급(등급에 따라 출금,이체한도 다름)
	public Platinum(String grade, String name, String id, String pw, String acc) {
		super(grade, name, id, pw, acc);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setLimit() {
		super.setTransferLimit(70);
		super.setWithdrawLimit(200);
	}

	@Override
	public void printInfo() {
		// TODO Auto-generated method stub
		
	}

}
