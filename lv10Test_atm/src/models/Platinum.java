package models;

public class Platinum extends Customer { // ���� �� �� �ι����� ���� ���(��޿� ���� ���,��ü�ѵ� �ٸ�)
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
