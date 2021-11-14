package models;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MenuPanel extends MyUtill {
	private String nextPage = "";
	private Image back = new ImageIcon("mainImages/back.png").getImage().getScaledInstance(620, 800, Image.SCALE_SMOOTH);
	
	private Menu coffees[] = new Menu[16];
	private Menu teas[] = new Menu[16];
	
	private JLabel coffee[] = new JLabel[16];
	private JLabel tea[] = new JLabel[16];
	
	private JScrollPane scrollCoffee, scrollTea;
	
	private final int COFFEE = 1;
	private final int TEA = 2;
	
	private JPanel coffeeMenu, teaMenu;
	private JButton bt_coffe,bt_tea,cancle, pay;
	private int cur = this.COFFEE;
	
	public static Vector<Vector <String>> myMenu = new Vector<>();
	public static Vector<String> col = new Vector<>(); //제품명, 수량, 가격
	public static int totalPrice;
	public static int totalCnt;
	
	private JScrollPane scroll;
	private JTable table;

	public MenuPanel() {
		setLayout(null);
		setBounds(0, 0, 620, 800);
		
		JLabel temp = new JLabel(new ImageIcon(this.back));
		temp.setBounds(0, 0, 620, 800);
		temp.setVisible(true);
		add(temp);
		
		addMouseListener(this);
		
		setBtn();
		setmenu();
		setMenuPanel();
		setScroll();
		setMenuNamePrice();
		setTable();
	}

	private void setTable() {
		this.table = new JTable(myMenu,col);
		
		this.scroll = new JScrollPane(this.table);
		this.scroll.setBounds(10, 500, 582, 190);
		this.add(this.scroll,0);
	}

	private void setScroll() {
		this.scrollCoffee = new JScrollPane(this.coffeeMenu);
		this.scrollCoffee.setBounds(10, 70, 582, 400);
		this.coffeeMenu.setPreferredSize(getSize());
		this.scrollCoffee.setViewportView(this.coffeeMenu);
		this.scrollCoffee.addMouseListener(this);
		add(this.scrollCoffee,0);
		
		this.scrollTea = new JScrollPane(this.teaMenu);
		this.scrollTea .setBounds(10, 70, 582, 400);
		this.teaMenu.setPreferredSize(getSize());
		this.scrollTea .setViewportView(this.teaMenu);
		this.scrollTea .addMouseListener(this);
		//add(this.scrollTea );
	}

	private void setBtn() {
		this.bt_coffe = new JButton("커피");
		this.bt_coffe.setBounds(10, 10, 90, 60);
		this.bt_coffe.setVisible(true);
		this.bt_coffe.addActionListener(this);
		add(this.bt_coffe,0);
		
		this.bt_tea = new JButton("티/에이드");
		this.bt_tea.setBounds(100, 30, 90, 40);
		this.bt_tea.setVisible(true);
		this.bt_tea.addActionListener(this);
		add(this.bt_tea,0);
		
		this.cancle = new JButton("취소하기");
		this.cancle.setBounds(290, 700, 150, 50);
		this.cancle.setVisible(true);
		this.cancle.addActionListener(this);
	
		add(this.cancle,0);
		
		this.pay = new JButton("결제하기");
		this.pay.setBounds(447, 700, 150, 50);
		this.pay.setVisible(true);
		this.pay.addActionListener(this);
		add(this.pay,0);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.bt_coffe) {
			if(this.cur != this.COFFEE) {
				remove(this.scrollTea);
				this.revalidate();
				this.repaint();
				add(this.scrollCoffee,0);
				this.bt_coffe.setBounds(10,10,90,60);
				this.bt_tea.setBounds(100,30,90,40);
				this.cur = this.COFFEE;
			}
		}else if(e.getSource() == this.bt_tea) {
			if(this.cur != this.TEA) {
				remove(this.scrollCoffee);
				this.revalidate();
				this.repaint();
				add(this.scrollTea,0);
				this.bt_coffe.setBounds(10,30,90,40);
				this.bt_tea.setBounds(100,10,90,60);
				this.cur = this.TEA;
			}
		}else if(e.getSource() == this.cancle) {
			this.nextPage = "main";
			myMenu = new Vector<>();
			this.table.updateUI();
			totalCnt = 0;
			totalPrice =0;
		}else if(e.getSource() == this.pay) {
			this.nextPage = "pay";
		}
	}

	private void setMenuPanel() {
		this.coffeeMenu = new JPanel();
		this.coffeeMenu.setLayout(null);
		
		for(int i=0;i<16;i++) {
			this.coffee[i] = new JLabel(new ImageIcon(this.coffees[i].getIm().getIm()));
			this.coffee[i].setVisible(true);
			this.coffee[i].setBounds(this.coffees[i].getIm().getX(), this.coffees[i].getIm().getY(),this.coffees[i].getIm().getW(), this.coffees[i].getIm().getH());
			this.coffee[i].addMouseListener(this);
			
			this.coffeeMenu.add(this.coffee[i],0);
		}
		
		this.teaMenu = new JPanel();
		this.teaMenu.setLayout(null);
		
		for(int i=0;i<16;i++) {
			this.tea[i] = new JLabel(new ImageIcon(this.teas[i].getIm().getIm()));
			this.tea[i].setVisible(true);
			this.tea[i].setBounds(this.teas[i].getIm().getX(), this.teas[i].getIm().getY(),this.teas[i].getIm().getW(), this.teas[i].getIm().getH());
			this.tea[i].addMouseListener(this);
			
			this.teaMenu.add(this.tea[i],0);
		}

	}

	private void setmenu() {
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
			this.coffees[i] = new Menu(im, simple_im);

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
			this.teas[i] = new Menu(im, simple_im);
			
			x += 130;
			if (i != 0 && i % 4 == 3) {
				y += 170;
				x = 20;
			}
			
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(this.cur == this.COFFEE) {
			for(int i=0;i<16;i++) {
				if(e.getSource() == this.coffee[i]) {
					int check = -1;
					for(int k=0;k<this.myMenu.size();k++) {
						if(this.coffees[i].getName().equals(this.myMenu.get(k).get(0))) {
							check = k;
						}
					}
					
					if(check == -1) {
						Vector<String>temp = new Vector<>();
						temp.add(this.coffees[i].getName());
						temp.add(1+"");
						temp.add(this.coffees[i].getPrice()+"");
						totalCnt++;
						totalPrice+=this.coffees[i].getPrice();
						this.myMenu.add(temp);
					}else {
						int temp = Integer.parseInt(this.myMenu.get(check).get(1));
						int cnt = temp+1;
						this.myMenu.get(check).set(1, cnt+"");
						totalCnt++;
						totalPrice+=this.coffees[i].getPrice();
						
					}
					this.table.updateUI();
					
				}
			}
		}else {
			for(int i=0;i<16;i++) {
				if(e.getSource() == this.tea[i]) {
					int check = -1;
					for(int k=0;k<this.myMenu.size();k++) {
						if(this.teas[i].getName().equals(this.myMenu.get(k).get(0))) {
							check = k;
						}
					}
					
					if(check == -1) {
						Vector<String>temp = new Vector<>();
						temp.add(this.teas[i].getName());
						temp.add(1+"");
						temp.add(this.teas[i].getPrice()+"");
						totalCnt++;
						totalPrice+=this.teas[i].getPrice();
					
						this.myMenu.add(temp);
					}else {
						int temp = Integer.parseInt(this.myMenu.get(check).get(1));
						int cnt = temp+1;
						this.myMenu.get(check).set(1, cnt+"");
						totalCnt++;
						totalPrice+=this.coffees[i].getPrice();
					}
					this.table.updateUI();
				}
			}
		}
	}

	


	

	@Override
	public String updateNextPage() {
		return this.nextPage;
	}

	@Override
	public void resetNextPage() {
		this.nextPage = "";
	}
	

	private void setMenuNamePrice() {
		
		col.add("제품명");
		col.add("수량");
		col.add("가격");
		
		this.coffees[0].setNamePrice("아메리카노",1500);
		this.coffees[1].setNamePrice("스페셜아메리카노",2500);
		this.coffees[2].setNamePrice("헤이즐넛아메리카노",2000);
		this.coffees[3].setNamePrice("유자아메리카노",2500);
		this.coffees[4].setNamePrice("카푸치노",2500);
		this.coffees[5].setNamePrice("카페라떼",2500);
		this.coffees[6].setNamePrice("헤이즐넛라떼",3000);
		this.coffees[7].setNamePrice("바닐라라떼",3000);
		this.coffees[8].setNamePrice("크리미라떼",3000);
		this.coffees[9].setNamePrice("헤이즐넛크리미라떼",3500);
		this.coffees[10].setNamePrice("카페모카",3500);
		this.coffees[11].setNamePrice("카라멜마끼아또",3500);
		this.coffees[12].setNamePrice("에스프레소",1500);
		this.coffees[13].setNamePrice("더치커피",2500);
		this.coffees[14].setNamePrice("더치시나몬라떼",3000);
		this.coffees[15].setNamePrice("더치코코넛라떼",3000);
		
		this.teas[0].setNamePrice("허니레몬티",3500);
		this.teas[1].setNamePrice("허니유자티",3500);
		this.teas[2].setNamePrice("허니자몽티",3500);
		this.teas[3].setNamePrice("얼그레이",2500);
		this.teas[4].setNamePrice("국화차",2500);
		this.teas[5].setNamePrice("민트초코티",2500);
		this.teas[6].setNamePrice("캐모마일",2500);
		this.teas[7].setNamePrice("페퍼민트",2500);
		this.teas[8].setNamePrice("청포도에이드",3500);
		this.teas[9].setNamePrice("보이차",2500);
		this.teas[10].setNamePrice("루이보스",2500);
		this.teas[11].setNamePrice("로즈힙",2500);
		this.teas[12].setNamePrice("히비스커스",2500);
		this.teas[13].setNamePrice("복숭아아이스티",3000);
		this.teas[14].setNamePrice("블루레몬에이드",3500);
		this.teas[15].setNamePrice("자몽에이드",3500);
	}

}
