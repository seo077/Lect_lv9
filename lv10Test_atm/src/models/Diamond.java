package models;

public class Diamond extends Customer{ // ���� �� �� �ֻ��� ���(��޿� ���� ���,��ü�ѵ� �ٸ�)
	
	


	public Diamond(String grade, String name, String id, String pw, String acc) {
		super(grade, name, id, pw, acc);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setLimit() {
		super.setTransferLimit(200);
		super.setWithdrawLimit(500);
	}

	@Override
	public void printInfo() {
		// TODO Auto-generated method stub
		
	}
}
