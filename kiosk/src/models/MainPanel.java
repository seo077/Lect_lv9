package models;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

class InDrinkAlert extends JFrame{
	private Image im = new ImageIcon("menuImages/main_info.png").getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
	private JLabel alert = new JLabel(new ImageIcon(im));
	
	public InDrinkAlert() {
		setLayout(null);
		setBounds(500, 200, 300, 300);

		setVisible(true);
		
		this.alert.setBounds(0, 0, 300, 300);
		this.alert.setVisible(true);
		add(this.alert);
	}
}


public class MainPanel extends MyUtill{
	private JLabel takeout, inDrink, main;
	private int idx = 0;
	private String nextPage ="";
	private JButton manager;
	
	public MainPanel() {
		setLayout(null);
		setBounds(0, 0, 620, 800);
		addMouseListener(this);
		
		setMainImages();
		setLabel();
		setManager();
	}

	private void setMainImages() {
//		Image im = new ImageIcon("mainImages/main.gif").getImage();
//		im.getScaledInstance(620, 600, Image.SCALE_SMOOTH);
		Image im = new ImageIcon("mainImages/main.gif").getImage().getScaledInstance(620, 600, Image.SCALE_FAST);
//		this.main = new JLabel(new ImageIcon("mainImages/main.gif"));
		this.main = new JLabel(new ImageIcon(im));
		this.main.setBounds(0, 0, 620, 600);
		this.main.setVisible(true);
		
		add(this.main);
	}

	private void setManager() {
		this.manager = new JButton();
		this.manager.setText("°ü¸®ÀÚ");
		this.manager.setBounds(500, 10, 100, 50);
		this.manager.setVisible(true);
		this.manager.addActionListener(this);
		
		add(this.manager,0);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.manager) {
			this.nextPage = "manager";
		}
	}
	
	private void setLabel() {
		Image im1 = new ImageIcon("menuImages/takeout.png").getImage().getScaledInstance(290, 145, Image.SCALE_SMOOTH);
		this.takeout = new JLabel(new ImageIcon(im1));
		this.takeout.setBounds(5, 610, 300, 140);
		this.takeout.setVisible(true);
		this.takeout.addMouseListener(this);
		add(this.takeout);
		
		Image im2 = new ImageIcon("menuImages/inDrink.png").getImage().getScaledInstance(290, 145, Image.SCALE_SMOOTH);
		this.inDrink = new JLabel(new ImageIcon(im2));
		this.inDrink.setBounds(300, 610, 300, 140);
		this.inDrink.setVisible(true);
		this.inDrink.addMouseListener(this);
		add(this.inDrink);
	}

	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == this.inDrink) {
			new InDrinkAlert();
			this.nextPage = "menu";
		}else if(e.getSource() == this.takeout) {
			this.nextPage = "menu";
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
