package models;

import java.util.ArrayList;

public class Platinum extends Customer { // ���� �� �� �ι����� ���� ���(��޿� ���� ���,��ü�ѵ� �ٸ�)
	
	public Platinum(String grade,String name,String id,String pw,int totalMoney,int repAccIdx,ArrayList<Account>accs) {  //��� �̵� ��
		super(grade, name, id, pw, totalMoney, repAccIdx, accs);
	}
	
	public Platinum(String grade, String name, String id, String pw, String acc) { //ó�� ���� ���� ������
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
