package models;

import java.util.ArrayList;

public class Diamond extends Customer{ // ���� �� �� �ֻ��� ���(��޿� ���� ���,��ü�ѵ� �ٸ�)
	
	public Diamond(String grade,String name,String id,String pw,int totalMoney,int repAccIdx,ArrayList<Account>accs) {  //��� �̵� ��
		super(grade, name, id, pw, totalMoney, repAccIdx, accs);
	}

	public Diamond(String grade, String name, String id, String pw, String acc) { //ó�� ���� ���� ������
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
