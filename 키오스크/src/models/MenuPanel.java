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
	public static Vector<String> col = new Vector<>(); //��ǰ��, ����, ����
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
		this.bt_coffe = new JButton("Ŀ��");
		this.bt_coffe.setBounds(10, 10, 90, 60);
		this.bt_coffe.setVisible(true);
		this.bt_coffe.addActionListener(this);
		add(this.bt_coffe,0);
		
		this.bt_tea = new JButton("Ƽ/���̵�");
		this.bt_tea.setBounds(100, 30, 90, 40);
		this.bt_tea.setVisible(true);
		this.bt_tea.addActionListener(this);
		add(this.bt_tea,0);
		
		this.cancle = new JButton("����ϱ�");
		this.cancle.setBounds(290, 700, 150, 50);
		this.cancle.setVisible(true);
		this.cancle.addActionListener(this);
	
		add(this.cancle,0);
		
		this.pay = new JButton("�����ϱ�");
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
		
		col.add("��ǰ��");
		col.add("����");
		col.add("����");
		
		this.coffees[0].setNamePrice("�Ƹ޸�ī��",1500);
		this.coffees[1].setNamePrice("����ȾƸ޸�ī��",2500);
		this.coffees[2].setNamePrice("������ӾƸ޸�ī��",2000);
		this.coffees[3].setNamePrice("���ھƸ޸�ī��",2500);
		this.coffees[4].setNamePrice("īǪġ��",2500);
		this.coffees[5].setNamePrice("ī���",2500);
		this.coffees[6].setNamePrice("������Ӷ�",3000);
		this.coffees[7].setNamePrice("�ٴҶ��",3000);
		this.coffees[8].setNamePrice("ũ���̶�",3000);
		this.coffees[9].setNamePrice("�������ũ���̶�",3500);
		this.coffees[10].setNamePrice("ī���ī",3500);
		this.coffees[11].setNamePrice("ī��Ḷ���ƶ�",3500);
		this.coffees[12].setNamePrice("����������",1500);
		this.coffees[13].setNamePrice("��ġĿ��",2500);
		this.coffees[14].setNamePrice("��ġ�ó����",3000);
		this.coffees[15].setNamePrice("��ġ���ڳӶ�",3000);
		
		this.teas[0].setNamePrice("��Ϸ���Ƽ",3500);
		this.teas[1].setNamePrice("�������Ƽ",3500);
		this.teas[2].setNamePrice("����ڸ�Ƽ",3500);
		this.teas[3].setNamePrice("��׷���",2500);
		this.teas[4].setNamePrice("��ȭ��",2500);
		this.teas[5].setNamePrice("��Ʈ����Ƽ",2500);
		this.teas[6].setNamePrice("ĳ����",2500);
		this.teas[7].setNamePrice("���۹�Ʈ",2500);
		this.teas[8].setNamePrice("û�������̵�",3500);
		this.teas[9].setNamePrice("������",2500);
		this.teas[10].setNamePrice("���̺���",2500);
		this.teas[11].setNamePrice("������",2500);
		this.teas[12].setNamePrice("����Ŀ��",2500);
		this.teas[13].setNamePrice("�����ƾ��̽�Ƽ",3000);
		this.teas[14].setNamePrice("��緹���̵�",3500);
		this.teas[15].setNamePrice("�ڸ����̵�",3500);
	}

}
