package 식권자판기;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.JButton;

public class PayPage extends MyUtill{

	private String nextPage = "";
	private JButton card,cash;
	
	public PayPage() {
		setLayout(null);
		setBounds(0, 0, 700, 800);
		
		setBtn();
	}
	
	private void setBtn() {
		this.card = new JButton();
		this.cash = new JButton();
		
		this.card.setText("카드계산");
		this.card.setBounds(100, 200, 200, 400);
		this.card.setBackground(new Color(148, 179, 253));
		this.card.addActionListener(this);
		add(this.card);
		
		this.cash.setText("현금계산");
		this.cash.setBounds(350, 200, 200, 400);
		this.cash.setBackground(new Color(193, 255, 215));
		this.cash.addActionListener(this);
		add(this.cash);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.card) {
			this.nextPage = "Card";
		}else {
			this.nextPage = "Cash";
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
