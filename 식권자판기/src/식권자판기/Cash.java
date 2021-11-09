package 식권자판기;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.JButton;

public class Cash extends MyUtill {

	private String nextPage = "";
	private JButton back = new JButton();

	public Cash() {
		setLayout(null);
		setBounds(0, 0, 700, 800);
		setBackground(new Color(193, 255, 215));
		
		setBack();
	}

	private void setBack() {
		this.back.setText("뒤로가기");
		this.back.setBounds(550, 700, 100, 50);
		this.back.addActionListener(this);

		add(this.back);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.back) {
			this.nextPage = "Order";
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
