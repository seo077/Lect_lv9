package nemo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;

class NemoPanel extends MyUtill {

	private Rect nemo;
	private int startX, startY, width, height;

	public NemoPanel() {
		setLayout(null);
		setBounds(0, 0, 800, 650);
		setBackground(Color.yellow);

		addMouseMotionListener(this);
		addMouseListener(this);
	}

	@Override
	public void mousePressed(MouseEvent e) {

		this.width = 0;
		this.height = 0;
		this.startX = e.getX();
		this.startY = e.getY();
		this.nemo = new Rect(startX, startY, this.width, this.height);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();

		if (x > this.startX && y > this.startY) {
			// 시작점 : start와 동일
			this.width = x - this.startX;
			this.height = y - this.startY;
			this.nemo.setX(this.startX);
			this.nemo.setY(this.startY);
		} else if (x > this.startX && y < this.startY) {
			// 시작점 : x-w,y
			this.width = x - this.startX;
			this.height = this.startY - y;
			this.nemo.setX(x - this.width);
			this.nemo.setY(y);
		} else if (x < this.startX && y > this.startY) {
			// 시작점 : x,y-h
			this.width = this.startX - x;
			this.height = y - this.startY;
			this.nemo.setX(x);
			this.nemo.setY(y - this.height);
		} else if (x < this.startX && y < this.startY) {
			// 시작점 : x,y
			this.width = this.startX - x;
			this.height = this.startY - y;
			this.nemo.setX(x);
			this.nemo.setY(y);
		}
		this.nemo.setWidth(this.width);
		this.nemo.setHeight(this.height);
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		if (nemo != null) {

			g.drawRect(nemo.getX(), nemo.getY(), nemo.getWidth(), nemo.getHeight());
		}
		repaint();
	}
}

public class Nemo extends JFrame implements MouseListener {

	private NemoPanel np = new NemoPanel();
	private JButton close = new JButton("close");

	public Nemo() {
		super("Nemo");
		setLayout(null);
		setBounds(300, 0, 800, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		add(np);

		close.setBounds(650, 650, 100, 100);
		close.addMouseListener(this);
		close.setVisible(true);
		add(close);

		setVisible(true);
		revalidate();

	}

	public static void main(String[] args) {
		Nemo m = new Nemo();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == this.close)
			this.dispose();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
