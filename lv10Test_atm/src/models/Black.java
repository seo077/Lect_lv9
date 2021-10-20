package models;

import java.util.ArrayList;

public class Black extends Customer{ //은행 고객 중 최하위 등급(등급에 따라 출금,이체한도 다름)


	public Black(String grade,String name,String id,String pw,int totalMoney,int repAccIdx,ArrayList<Account>accs) {  //등급 이동 시
		super(grade, name, id, pw, totalMoney, repAccIdx, accs);
	}
	
	public Black(String grade, String name, String id, String pw, String acc ,boolean rep) { //처음 계좌개설시(+1000원)
		super(grade, name, id, pw, acc,rep);
		// TODO Auto-generated constructor stub
	}
	
	public Black(String grade, String name, String id, String pw, String acc) { //처음 제외 계좌 개설시
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
		super.printAccs();
	}


}
