package models;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;


class Add extends JFrame{
	String str[] = {"name","price","imagefileName","simple_imagefileName"};
	
	JTextField fields[] = new JTextField[4];
	JLabel labels[] = new JLabel[4];
	public Add() {
		setLayout(null);
		setBounds(400, 200, 300, 300);
		setVisible(true);
		
		for(int i=0;i<4;i++) {
			this.labels[i] = new JLabel();
			this.fields[i] = new JTextField();
			
			this.labels[i].setBounds(10, 10 + (50 * i), 100, 50);
			this.labels[i].setText(str[i]);
			this.labels[i].setVisible(true);
			add(this.labels[i]);
			
			this.fields[i].setBounds(150, 10 + (50 * i), 100, 50);
			this.fields[i].setVisible(true);
			add(this.fields[i]);
		}
	}
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
		sales.setText(String.format("�� ���� : %d", MenuPanel.sales));
	}
	
	private void setLabel() {
		sales = new JLabel();
		sales.setBounds(10, 50, 500, 50);
		sales.setText(String.format("�� ���� : %d", MenuPanel.sales));
		sales.setVisible(true);
		add(sales);
		
	}

	private void setBtn() {
		this.back = new JButton();
		this.delMenu = new JButton();
		this.addMenu = new JButton();
		
		this.addMenu.setBounds(100, 150, 100, 100);
		this.addMenu.setText("�޴� �߰�");
		this.addMenu.setVisible(true);
		this.addMenu.addActionListener(this);
		add(this.addMenu);
		
		this.delMenu.setBounds(250, 150, 100, 100);
		this.delMenu.setText("�޴� ����");
		this.delMenu.setVisible(true);
		this.delMenu.addActionListener(this);
		add(this.delMenu);
		
		this.back.setBounds(400, 600, 100, 50);
		this.back.setText("�ڷΰ���");
		this.back.setVisible(true);
		this.back.addActionListener(this);
		add(this.back);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.back) {
			
		}else if(e.getSource() == this.addMenu) {
			new Add();
		}else if(e.getSource() == this.delMenu) {
			
		}
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
