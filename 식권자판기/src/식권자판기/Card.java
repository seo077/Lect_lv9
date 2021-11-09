package 식권자판기;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

class Alert1 extends JFrame{
	JLabel alert = new JLabel();
	
	public Alert1() {
		setLayout(null);
		setBounds(200, 200, 300, 200);
		setVisible(true);
		
		this.alert.setText("결제완료!");
		this.alert.setBounds(0, 0, 300, 200);
		this.alert.setForeground(Color.red);
		this.alert.setHorizontalAlignment(JLabel.CENTER);
		this.alert.setVisible(true);
		add(this.alert);
	}
}

public class Card extends MyUtill{

	private String nextPage = "";
	private JButton back = new JButton();
	private JButton pay = new JButton();
	private ArrayList<MyMenu>myMenu;
	
	public Card() {
		setLayout(null);
		setBounds(0, 0, 700, 800);
		setBackground(new Color(148, 179, 253));
		
		setBack();
		setPay();
	}
	

	private void setPay() {
		this.pay.setText("결제하기");
		this.pay.setBounds(550, 540, 100, 50);
		this.pay.addActionListener(this);
		
		add(this.pay);
		
	}


	private void setBack() {
		this.back.setText("뒤로가기");
		this.back.setBounds(550, 600, 100, 50);
		this.back.addActionListener(this);

		add(this.back);

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.back) {
			this.nextPage = "Order";
		}else {
			new Alert1();
			this.nextPage = "Intro";
			OrderPage.reset();
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		this.myMenu = OrderPage.getMyMenu();
		// TODO Auto-generated method stub
		super.paintComponent(g);
		int size =this.myMenu.size();
		g.setFont(new Font("", Font.BOLD, 30));
		g.drawString("영수증", 300, 50);
		
		g.setFont(new Font("", Font.PLAIN, 20));
		for(int i=0;i<size;i++) {
			MyMenu temp = this.myMenu.get(i);
			g.drawString(String.format("%s  %d개  %d원", temp.getMenuName(),temp.getCnt(),temp.getPrice()), 100 , 150+ (30 * i));
		}
		g.drawString("---------------------------", 100, 165 +(30*(size-1)));
		g.drawString(String.format("총 금액 : %d", MyMenu.total), 100, 160 +(30*size));
		g.drawString(String.format("총 개수 : %d", MyMenu.totalCnt), 100, 160 +(30*(size+1)));
		
		repaint();
	}
	@Override
	public String nextPageCheck() {
		return this.nextPage;
	}

	@Override
	public void setNextPage(String str) {
		this.nextPage = str;
	}

}
