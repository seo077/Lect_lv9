package models;

import java.util.ArrayList;

public class Gold extends Customer { // ���� �� �� �ι����� ���� ���(��޿� ���� ���,��ü�ѵ� �ٸ�)
	
	public Gold(String grade,String name,String id,String pw,int totalMoney,int repAccIdx,ArrayList<Account>accs) {  //��� �̵� ��
		super(grade, name, id, pw, totalMoney, repAccIdx, accs);
	}
	
	public Gold(String grade, String name, String id, String pw, String acc) { //ó�� ���� ���� ������
		super(grade, name, id, pw, acc);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setLimit() {
		super.setTransferLimit(50);
		super.setWithdrawLimit(150);
	}

	@Override
	public void printInfo() {
		// TODO Auto-generated method stub
		
	}

}
