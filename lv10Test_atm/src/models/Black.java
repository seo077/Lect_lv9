package models;

public class Black extends Customer{ //���� �� �� ������ ���(��޿� ���� ���,��ü�ѵ� �ٸ�)


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
		System.out.printf("[��� : %s] [�̸� : %s] [���̵� : %s] [��й�ȣ : %s]\n",super.getGrade(),super.getName(),super.getId(),super.getPw());
		super.accsCnt();
	}


}
