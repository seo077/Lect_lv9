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
	
	private static int cSize = ManagerPanel.coffeeMenu.size();
	private static int tSize = ManagerPanel.teaMenu.size();
	
	private static  Menu coffees[];
	private static Menu teas[];
	
	private JLabel coffee[];
	private JLabel tea[];
	
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
	public static int sales;
	
	private JScrollPane scroll;
	private static JTable table;

	public MenuPanel() {
		setLayout(null);
		setBounds(0, 0, 620, 800);
		
		JLabel temp = new JLabel(new ImageIcon(this.back));
		temp.setBounds(0, 0, 620, 800);
		temp.setVisible(true);
		add(temp);
		
		addMouseListener(this);
		
		menuSetting();
		setBtn();
		setMenuPanel();
		setScroll();
		setTable();
	}

	public static void menuSetting() {

		
		coffees = new Menu[cSize];
		teas = new Menu[tSize];
		
		for(int i=0;i<cSize;i++) {
			Menu temp = ManagerPanel.coffeeMenu.get(i);
			coffees[i] = new Menu(temp.getIm(), temp.getSimple_im());
			coffees[i].setNamePrice(temp.getName(), temp.getPrice());
		}
	
		for(int i=0;i<tSize;i++) {
			Menu temp = ManagerPanel.teaMenu.get(i);
			teas[i] = new Menu(temp.getIm(), temp.getSimple_im());
			teas[i].setNamePrice(temp.getName(), temp.getPrice());
		}
	}

	private void setTable() { //내가 주문한 메뉴 테이블
		col.add("제품명");
		col.add("수량");
		col.add("가격");
		
		this.table = new JTable(myMenu,col);
		
		this.scroll = new JScrollPane(this.table);
		this.scroll.setBounds(10, 500, 582, 190);
		this.add(scroll,0);
		
		PayPanel.table = new JTable(MenuPanel.myMenu, MenuPanel.col);
		PayPanel.table.getColumn("제품명").setWidth(200);
		PayPanel.table.getColumn("수량").setWidth(182);
		PayPanel.table.getColumn("가격").setWidth(200);
		PayPanel.table.setCellEditor(null);
		PayPanel.table.setGridColor(Color.red);
		PayPanel.table.setVisible(true);

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
			myMenu = new Vector<>();
			this.table.updateUI();
			this.scroll.revalidate();
			
			PayPanel.table.updateUI();
			PayPanel.table.revalidate();
			PayPanel.table.repaint();
			totalCnt = 0;
			totalPrice =0;
			this.nextPage = "main";
		}else if(e.getSource() == this.pay) {
			this.nextPage = "pay";
			PayPanel.table.updateUI();
			PayPanel.table.revalidate();
			PayPanel.table.repaint();
			
		}
	}

	private void setMenuPanel() {
		this.coffeeMenu = new JPanel();
		this.coffeeMenu.setLayout(null);
		
		coffee = new JLabel[cSize];
		for(int i=0;i<cSize;i++) {
			this.coffee[i] = new JLabel(new ImageIcon(this.coffees[i].getIm().getIm()));
			this.coffee[i].setVisible(true);
			this.coffee[i].setBounds(this.coffees[i].getIm().getX(), this.coffees[i].getIm().getY(),this.coffees[i].getIm().getW(), this.coffees[i].getIm().getH());
			this.coffee[i].addMouseListener(this);
			
			this.coffeeMenu.add(this.coffee[i],0);
		}
		
		this.teaMenu = new JPanel();
		this.teaMenu.setLayout(null);
		tea = new JLabel[tSize];
		for(int i=0;i<tSize;i++) {
			this.tea[i] = new JLabel(new ImageIcon(this.teas[i].getIm().getIm()));
			this.tea[i].setVisible(true);
			this.tea[i].setBounds(this.teas[i].getIm().getX(), this.teas[i].getIm().getY(),this.teas[i].getIm().getW(), this.teas[i].getIm().getH());
			this.tea[i].addMouseListener(this);
			
			this.teaMenu.add(this.tea[i],0);
		}

	}

	

	@Override
	public void mouseClicked(MouseEvent e) {
		if(this.cur == this.COFFEE) {
			for(int i=0;i<cSize;i++) {
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
					PayPanel.table.updateUI();
				}
			}
		}else {
			for(int i=0;i<tSize;i++) {
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
						sales+=this.coffees[i].getPrice();
						this.myMenu.add(temp);
					}else {
						int temp = Integer.parseInt(this.myMenu.get(check).get(1));
						int cnt = temp+1;
						this.myMenu.get(check).set(1, cnt+"");
						totalCnt++;
						totalPrice+=this.coffees[i].getPrice();
						sales+=this.coffees[i].getPrice();
					}
					this.table.updateUI();
					PayPanel.table.updateUI();
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
		this.myMenu = new Vector<>();
		this.table = new JTable(myMenu,col);
		
		this.scroll = new JScrollPane(this.table);
		this.scroll.setBounds(10, 500, 582, 190);
		this.add(this.scroll,0);
		
		this.revalidate();
		this.repaint();
	}
	
}
