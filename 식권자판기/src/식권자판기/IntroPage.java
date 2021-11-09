package 식권자판기;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;

public class IntroPage extends MyUtill{
	
	private JButton consumer = new JButton();
	private JButton manager = new JButton();
	
	private String nextPage = "";
	
	public IntroPage() {
		setLayout(null);
		setBounds(0, 0, 700, 800);
		this.nextPage = "";
		setConsumer();
		setManager();
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.setFont(new Font("", Font.BOLD, 30));
		g.drawString("주문하시려면", 220, 300);
		g.drawString("[주문하기]버튼을 클릭하세요", 120, 350);
		repaint();
	}
	private void setConsumer() {
		this.consumer.setText("주문하기");
		this.consumer.setBounds(0, 500, 700, 300);
		this.consumer.addActionListener(this);
		
		add(this.consumer);
	}

	private void setManager() {
		this.manager.setText("관리자");
		this.manager.setBounds(600, 50, 70, 50);
		this.manager.addActionListener(this);
		
		add(this.manager);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.consumer) {
			this.nextPage = "Order";
		}else {
			this.nextPage = "Manager";
		}
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
