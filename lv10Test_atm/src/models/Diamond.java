package models;

import java.util.ArrayList;

public class Diamond extends Customer{ // 은행 고객 중 최상위 등급(등급에 따라 출금,이체한도 다름)
	
	public Diamond(String grade,String name,String id,String pw,int totalMoney,int repAccIdx,ArrayList<Account>accs) {  //등급 이동 시
		super(grade, name, id, pw, totalMoney, repAccIdx, accs);
	}

	public Diamond(String grade, String name, String id, String pw, String acc) { //처음 제외 계좌 개설시
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
