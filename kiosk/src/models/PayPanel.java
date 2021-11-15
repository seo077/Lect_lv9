package models;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;

public class PayPanel extends MyUtill {
	private String nextPage = "";

	private JTable table;
	private Images order[] = new Images[6];
	private static JLabel price;

	private static JLabel cnt;
	private JButton card,cash;
	
	public PayPanel() {
		setLayout(null);
		setBounds(0, 0, 620, 800);
		setBackground(Color.white);
		
		setOrder();
		setLabel();
		setBtn();
		
		this.table = new JTable(MenuPanel.myMenu,MenuPanel.col);
		
		this.table.setBounds(10, 100, 582, 400);
		this.table.getColumn("제품명").setWidth(200);
		this.table.getColumn("수량").setWidth(182);
		this.table.getColumn("가격").setWidth(200);
		this.table.setCellEditor(null);
		this.table.setGridColor(Color.red);
		add(this.table);
	}
	
	private void setLabel() {
		this.price = new JLabel();
		this.price.setBounds(500, 520, 100, 50);
		this.price.setForeground(Color.blue);
		this.price.setText(MenuPanel.totalPrice+"");
		this.price.setFont(new Font("", Font.BOLD, 30));
		this.price.setVisible(true);
		add(this.price);
		
		this.cnt = new JLabel();
		this.cnt.setBounds(500, 450, 100, 50);
		this.cnt.setForeground(Color.blue);
		this.cnt.setText(MenuPanel.totalCnt+"");
		this.cnt.setFont(new Font("", Font.BOLD, 30));
		this.cnt.setVisible(true);
		add(this.cnt);
	}

	public static void setTotal() {
		price.setText(MenuPanel.totalPrice+"");
		cnt.setText(MenuPanel.totalCnt+"");
	}
	private void setBtn() {
		this.card = new JButton("카드 계산");
		this.card.setBounds(290, 700, 150, 50);
		this.card.addActionListener(this);
		this.card.setVisible(true);
		add(this.card);
		
		this.cash = new JButton("현금 계산");
		this.cash.setBounds(447, 700, 150, 50);
		this.cash.addActionListener(this);
		this.cash.setVisible(true);
		add(this.cash);
	}

	private void setOrder() {
		this.order[0] = new Images(8,10,300,50,"mainImages/order0.png");
		this.order[1] = new Images(8,70,200,30,"mainImages/order1.png");
		this.order[2] = new Images(208,70,182,30,"mainImages/order2.png");
		this.order[3] = new Images(390,70,200,30,"mainImages/order3.png");
		this.order[4] = new Images(250,450,150,50,"mainImages/order4.png");
		this.order[5] = new Images(250,520,150,50,"mainImages/order5.png");
		
		for(int i=0;i<6;i++) {
			JLabel temp = new JLabel(new ImageIcon(this.order[i].getIm()));
			temp.setBounds(this.order[i].getX(), this.order[i].getY(),this.order[i].getW(),this.order[i].getH());
			temp.setVisible(true);
			add(temp);
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

}
