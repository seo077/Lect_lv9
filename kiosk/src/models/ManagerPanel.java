package models;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;


class Add extends JFrame implements KeyListener{
	String str[] = {"name","price","imagefileName","simple_imagefileName"};
	public int size = 4;
	private JTextField fields[] = new JTextField[4];
	private JLabel labels[] = new JLabel[4];
	public Add() {
		setLayout(null);
		setBounds(500, 200, 300, 300);
		setVisible(true);
		
		addKeyListener(this);
		setFocusable(true);
		
		for(int i=0;i<4;i++) {
			this.labels[i] = new JLabel();
			this.fields[i] = new JTextField();
			
			this.labels[i].setBounds(10, 10 + (50 * i), 110, 50);
			this.labels[i].setText(str[i]);
			this.labels[i].setVisible(true);
			add(this.labels[i]);
			
			this.fields[i].setBounds(150, 10 + (50 * i), 100, 50);
			this.fields[i].setVisible(true);
			add(this.fields[i]);
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == e.VK_ENTER) {
			System.out.println("akhfkfjhka");
			int check = 0;
			for(int i=0;i<4;i++) {
				if(this.fields[i].getText()!=null) {
					check++;
				}
			}
			
			if(check == 4) {
				Images im = new Images(0, 0, 120, 160, this.fields[2].getText());
				Images simple_im = new Images(0, 0, 120, 160, this.fields[3].getText());
				Menu temp = new Menu(im, simple_im);
				temp.setNamePrice(this.fields[0].getText(),Integer.parseInt( this.fields[1].getText()));
				if(ManagerPanel.sort == ManagerPanel.COFFEE) {
					ManagerPanel.coffeeMenu.add(temp);
				}else {
					ManagerPanel.teaMenu.add(temp);
					
				}
				this.dispose();
			}
			
			getWindowFocusListeners();
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


}

class Del extends JFrame{
	
}

public class ManagerPanel extends MyUtill{
	private String nextPage = "";
	private JButton back, add_coffeeMenu,del_coffeeMenu  ,add_teaMenu,del_teaMenu; //-> menuSett
	private static JLabel sales;
	
	private JTable coffeeTable,teaTable;
	private JScrollPane coffeeScroll,teaScroll;
	public static int sort;
	
	public static  final int COFFEE = 1;
	public static final int TEA = 2;
	
	private JLabel coffee,tea;
	public static ArrayList<Menu>coffeeMenu = new ArrayList<>();
	public static int coffeeCnt = 0;
	public static int teaCnt = 0;
	public static ArrayList<Menu>teaMenu = new ArrayList<>();
	private Vector<String>col = new Vector<>();
	
	Vector<Vector<String>>coffeeInfo = new Vector<>();
	Vector<Vector<String>>teaInfo = new Vector<>();
	
	public ManagerPanel() {
		setLayout(null);
		setBounds(0, 0, 620, 800);
		
		setmenu();
		setCol();
		setMenuNamePrice();
		setLabel();
		setBtn();
		setScroll();
	}
	
	private void setCol() {
		this.col.add("��ǰ��");
		this.col.add("����");
	}

	private void setScroll() {
		for(int i=0;i<coffeeMenu.size();i++) {
			Vector< String>temp = new Vector<>();
			temp.add(coffeeMenu.get(i).getName());
			temp.add(coffeeMenu.get(i).getPrice()+"");
			this.coffeeInfo.add(temp);
		}
		this.coffeeTable = new JTable(this.coffeeInfo,col); 
		this.coffeeTable.setBounds(50, 200, 200, 300);
		this.coffeeTable.getColumn("��ǰ��").setWidth(130);
		this.coffeeTable.getColumn("����").setWidth(70);
		this.coffeeTable.setCellEditor(null);
		this.coffeeTable.setGridColor(Color.blue);
		this.coffeeTable.setVisible(true);
		
		this.coffeeScroll = new JScrollPane(this.coffeeTable);
		this.coffeeScroll.setAutoscrolls(true);
		this.coffeeScroll.setBounds(50, 200, 200, 300);
		this.coffeeScroll.setVisible(true);
		add(this.coffeeScroll);
	
		for(int i=0;i<teaMenu.size();i++) {
			Vector< String>temp = new Vector<>();
			temp.add(teaMenu.get(i).getName());
			temp.add(teaMenu.get(i).getPrice()+"");
			this.teaInfo.add(temp);
		}
		this.teaTable = new JTable(this.teaInfo,col); 
		this.teaTable.setBounds(320, 200, 200, 300);
		this.teaTable.getColumn("��ǰ��").setWidth(130);
		this.teaTable.getColumn("����").setWidth(70);
		this.teaTable.setCellEditor(null);
		this.teaTable.setGridColor(Color.blue);
		this.teaTable.setVisible(true);
		
		this.teaScroll = new JScrollPane(this.teaTable);
		this.teaScroll.setAutoscrolls(true);
		this.teaScroll.setBounds(320, 200, 200, 300);
		this.teaScroll.setVisible(true);
		add(this.teaScroll);
	}
	
	

	public static void setSales() {
		sales.setText(String.format("�� ���� : %d", PayPanel.sales));
	}
	
	private void setLabel() {
		sales = new JLabel();
		sales.setBounds(10, 50, 500, 50);
		sales.setFont(new Font("", Font.BOLD, 30));
		sales.setForeground(Color.red);
		sales.setText(String.format("�� ���� : %d", PayPanel.sales));
		sales.setVisible(true);
		add(sales);
		
		this.tea = new JLabel();
		this.tea.setBounds(320, 100, 200, 90);
		this.tea.setFont(new Font("", Font.BOLD, 30));
		this.tea.setText("Ƽ/���̵�");
		this.tea.setVisible(true);
		add(this.tea);
		
		this.coffee = new JLabel();
		this.coffee.setBounds(50, 100, 100, 90);
		this.coffee.setFont(new Font("", Font.BOLD, 30));
		this.coffee.setText("Ŀ��");
		this.coffee.setVisible(true);
		add(this.coffee);
	}

	private void setBtn() {
		this.back = new JButton();
		this.del_coffeeMenu = new JButton();
		this.add_coffeeMenu = new JButton();
		
		this.add_teaMenu = new JButton();
		this.del_teaMenu = new JButton();
		
		this.add_coffeeMenu.setBounds(52, 520, 90, 40);
		this.add_coffeeMenu.setText("�޴� �߰�");
		this.add_coffeeMenu.setVisible(true);
		this.add_coffeeMenu.addActionListener(this);
		add(this.add_coffeeMenu);
		
		this.del_coffeeMenu.setBounds(157, 520, 90, 40);
		this.del_coffeeMenu.setText("�޴� ����");
		this.del_coffeeMenu.setVisible(true);
		this.del_coffeeMenu.addActionListener(this);
		add(this.del_coffeeMenu);
	
		this.add_teaMenu.setBounds(322, 520, 90, 40);
		this.add_teaMenu.setText("�޴� �߰�");
		this.add_teaMenu.setVisible(true);
		this.add_teaMenu.addActionListener(this);
		add(this.add_teaMenu);
		
		this.del_teaMenu.setBounds(412, 520, 90, 40);
		this.del_teaMenu.setText("�޴� ����");
		this.del_teaMenu.setVisible(true);
		this.del_teaMenu.addActionListener(this);
		add(this.del_teaMenu);
		
		this.back.setBounds(400, 700, 100, 50);
		this.back.setText("�ڷΰ���");
		this.back.setVisible(true);
		this.back.addActionListener(this);
		add(this.back);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.back) {
			
		}else if(e.getSource() == this.add_coffeeMenu) {
			Add add = new Add();
			sort = COFFEE;
			
		}else if(e.getSource() == this.del_coffeeMenu) {
			new Del();
			sort = COFFEE;
		}else if(e.getSource() == this.add_teaMenu) {
			new Add();
			sort = TEA;
		}else if(e.getSource() == this.del_teaMenu) {
			new Del();
			sort = TEA;
		}
		
		this.coffeeScroll.revalidate();
		this.teaScroll.revalidate();
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
		
		coffeeMenu.get(0).setNamePrice("�Ƹ޸�ī��",1500);
		coffeeMenu.get(1).setNamePrice("����ȾƸ޸�ī��",2500);
		coffeeMenu.get(2).setNamePrice("������ӾƸ޸�ī��",2000);
		coffeeMenu.get(3).setNamePrice("���ھƸ޸�ī��",2500);
		coffeeMenu.get(4).setNamePrice("īǪġ��",2500);
		coffeeMenu.get(5).setNamePrice("ī���",2500);
		coffeeMenu.get(6).setNamePrice("������Ӷ�",3000);
		coffeeMenu.get(7).setNamePrice("�ٴҶ��",3000);
		coffeeMenu.get(8).setNamePrice("ũ���̶�",3000);
		coffeeMenu.get(9).setNamePrice("�������ũ���̶�",3500);
		coffeeMenu.get(10).setNamePrice("ī���ī",3500);
		coffeeMenu.get(11).setNamePrice("ī��Ḷ���ƶ�",3500);
		coffeeMenu.get(12).setNamePrice("����������",1500);
		coffeeMenu.get(13).setNamePrice("��ġĿ��",2500);
		coffeeMenu.get(14).setNamePrice("��ġ�ó����",3000);
		coffeeMenu.get(15).setNamePrice("��ġ���ڳӶ�",3000);
		
		teaMenu.get(0).setNamePrice("��Ϸ���Ƽ",3500);
		teaMenu.get(1).setNamePrice("�������Ƽ",3500);
		teaMenu.get(2).setNamePrice("����ڸ�Ƽ",3500);
		teaMenu.get(3).setNamePrice("��׷���",2500);
		teaMenu.get(4).setNamePrice("��ȭ��",2500);
		teaMenu.get(5).setNamePrice("��Ʈ����Ƽ",2500);
		teaMenu.get(6).setNamePrice("ĳ����",2500);
		teaMenu.get(7).setNamePrice("���۹�Ʈ",2500);
		teaMenu.get(8).setNamePrice("û�������̵�",3500);
		teaMenu.get(9).setNamePrice("������",2500);
		teaMenu.get(10).setNamePrice("���̺���",2500);
		teaMenu.get(11).setNamePrice("������",2500);
		teaMenu.get(12).setNamePrice("����Ŀ��",2500);
		teaMenu.get(13).setNamePrice("�����ƾ��̽�Ƽ",3000);
		teaMenu.get(14).setNamePrice("��緹���̵�",3500);
		teaMenu.get(15).setNamePrice("�ڸ����̵�",3500);
		
		this.coffeeCnt = 16;
		this.teaCnt = 16;
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
