package models;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

class Add extends JFrame{
	
}

class Del extends JFrame{
	
}

public class ManagerPanel extends MyUtill{
	private String nextPage = "";
	private JButton back, addMenu,delMenu; //-> menuSett
	private static JLabel sales;
	
	private JTable table;
	private JScrollPane scroll;
	
	public static ArrayList<Menu>coffeeMenu = new ArrayList<>();
	public static ArrayList<Menu>teaMenu = new ArrayList<>();
	
	public ManagerPanel() {
		setLayout(null);
		setBounds(0, 0, 620, 800);
		
		setmenu();
		setMenuNamePrice();
		setLabel();
		setBtn();
	}
	
	public static void setSales() {
		sales.setText(String.format("총 매출 : %d", MenuPanel.sales));
	}
	
	private void setLabel() {
		sales = new JLabel();
		sales.setBounds(10, 50, 500, 50);
		sales.setText(String.format("총 매출 : %d", MenuPanel.sales));
		sales.setVisible(true);
		add(sales);
		
	}

	private void setBtn() {
		// TODO Auto-generated method stub
		
	}

	public void setmenu() {
		int x = 20;
		int y = 10;
		for (int i = 0; i < 16; i++) {
			String fileName1 = "";
			String fileName2 = "";
			if (i < 9) {
				fileName1 = String.format("menuImages/coffee_sub0%d.png", i + 1);
				fileName2 = String.format("menuImages/coffee0%d.png", i + 1);
			} else {
				fileName1 = String.format("menuImages/coffee_sub%d.png", i + 1);
				fileName2 = String.format("menuImages/coffee%d.png", i + 1);
			}
			Images im = new Images(x, y, 120, 160, fileName1);
			Images simple_im = new Images(x, y, 120, 160, fileName2);
			coffeeMenu.add(new Menu(im, simple_im));

			x += 130;
			if (i != 0 && i % 4 == 3) {
				y += 170;
				x = 20;
			}
		
			
		}

		x = 20;
		y = 10;
		for (int i = 0; i < 16; i++) {
			String fileName1 = "";
			String fileName2 = "";
			if (i < 9) {
				fileName1 = String.format("menuImages/tea_sub0%d.png", i + 1);
				fileName2 = String.format("menuImages/tea0%d.png", i + 1);
			} else {
				fileName1 = String.format("menuImages/tea_sub%d.png", i + 1);
				fileName2 = String.format("menuImages/tea%d.png", i + 1);
			}
			
			Images im = new Images(x, y, 120, 160, fileName1);
			Images simple_im = new Images(x, y, 120, 160, fileName2);
			teaMenu.add(new Menu(im, simple_im));

			
			x += 130;
			if (i != 0 && i % 4 == 3) {
				y += 170;
				x = 20;
			}
			
		}

	}
	
	public void setMenuNamePrice() {
		
		coffeeMenu.get(0).setNamePrice("아메리카노",1500);
		coffeeMenu.get(1).setNamePrice("스페셜아메리카노",2500);
		coffeeMenu.get(2).setNamePrice("헤이즐넛아메리카노",2000);
		coffeeMenu.get(3).setNamePrice("유자아메리카노",2500);
		coffeeMenu.get(4).setNamePrice("카푸치노",2500);
		coffeeMenu.get(5).setNamePrice("카페라떼",2500);
		coffeeMenu.get(6).setNamePrice("헤이즐넛라떼",3000);
		coffeeMenu.get(7).setNamePrice("바닐라라떼",3000);
		coffeeMenu.get(8).setNamePrice("크리미라떼",3000);
		coffeeMenu.get(9).setNamePrice("헤이즐넛크리미라떼",3500);
		coffeeMenu.get(10).setNamePrice("카페모카",3500);
		coffeeMenu.get(11).setNamePrice("카라멜마끼아또",3500);
		coffeeMenu.get(12).setNamePrice("에스프레소",1500);
		coffeeMenu.get(13).setNamePrice("더치커피",2500);
		coffeeMenu.get(14).setNamePrice("더치시나몬라떼",3000);
		coffeeMenu.get(15).setNamePrice("더치코코넛라떼",3000);
		
		teaMenu.get(0).setNamePrice("허니레몬티",3500);
		teaMenu.get(1).setNamePrice("허니유자티",3500);
		teaMenu.get(2).setNamePrice("허니자몽티",3500);
		teaMenu.get(3).setNamePrice("얼그레이",2500);
		teaMenu.get(4).setNamePrice("국화차",2500);
		teaMenu.get(5).setNamePrice("민트초코티",2500);
		teaMenu.get(6).setNamePrice("캐모마일",2500);
		teaMenu.get(7).setNamePrice("페퍼민트",2500);
		teaMenu.get(8).setNamePrice("청포도에이드",3500);
		teaMenu.get(9).setNamePrice("보이차",2500);
		teaMenu.get(10).setNamePrice("루이보스",2500);
		teaMenu.get(11).setNamePrice("로즈힙",2500);
		teaMenu.get(12).setNamePrice("히비스커스",2500);
		teaMenu.get(13).setNamePrice("복숭아아이스티",3000);
		teaMenu.get(14).setNamePrice("블루레몬에이드",3500);
		teaMenu.get(15).setNamePrice("자몽에이드",3500);
	}

	
	@Override
	public String updateNextPage() {
		return this.nextPage;
	}

	@Override
	public void resetNextPage() {
		this.nextPage = "";
	}

}
