package nemo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

class NemoPanel extends MyUtill {

	private ArrayList<Rect> nemo = new ArrayList<>();
	private int cnt;
	private boolean shift;
	private int startX, startY, width, height;
	private JButton reset = new JButton();

	public NemoPanel() {
		setLayout(null);
		setBounds(0, 0, 800, 650);
		setBackground(Color.yellow);

		addMouseMotionListener(this);
		addMouseListener(this);

		setFocusable(true);
		addKeyListener(this);
		setReset();
	}

	private void setReset() {
		this.reset.setBounds(650, 550, 100, 50);
		this.reset.setText("RESET");
		this.reset.setVisible(true);
		this.reset.addActionListener(this);

		add(this.reset);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.reset) {
			this.nemo = new ArrayList<>();
			this.cnt = 0;

			requestFocusInWindow();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.isShiftDown()) {
			this.shift = true;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		this.shift = false;
		this.width = 0;
		this.height = 0;
		this.startX = e.getX();
		this.startY = e.getY();
		this.cnt++;
		Rect temp = new Rect(startX, startY, this.width, this.height);
		this.nemo.add(temp);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if (x > this.startX && y > this.startY) {
			// 시작점 : start와 동일
			if (this.shift) {
				this.width = x - this.startX;
				this.height = this.width;
			} else {
				this.width = x - this.startX;
				this.height = y - this.startY;
			}

			this.nemo.get(this.cnt - 1).setX(this.startX);
			this.nemo.get(this.cnt - 1).setY(this.startY);
		} else if (x > this.startX && y < this.startY) {
			// 시작점 : x-w,y
			if (this.shift) {
				this.width = x - this.startX;
				this.height = this.width;
			} else {
				this.width = x - this.startX;
				this.height = this.startY - y;
			}
			this.nemo.get(this.cnt - 1).setX(x - this.width);
			this.nemo.get(this.cnt - 1).setY(y);
		} else if (x < this.startX && y > this.startY) {
			// 시작점 : x,y-h
			if (this.shift) {
				this.width = this.startX - x;
				this.height = this.width;
			} else {
				this.width = this.startX - x;
				this.height = y - this.startY;

			}
			this.nemo.get(this.cnt - 1).setX(x);
			this.nemo.get(this.cnt - 1).setY(y - this.height);
		} else if (x < this.startX && y < this.startY) {
			// 시작점 : x,y
			if (this.shift) {
				this.width = this.startX - x;
				this.height = this.width;
			} else {
				this.width = this.startX - x;
				this.height = this.startY - y;

			}
			this.nemo.get(this.cnt - 1).setX(x);
			this.nemo.get(this.cnt - 1).setY(y);
		}
		this.nemo.get(this.cnt - 1).setWidth(this.width);
		this.nemo.get(this.cnt - 1).setHeight(this.height);
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		if (nemo != null) {
			for (int i = 0; i < this.cnt; i++) {
				g.drawRect(nemo.get(i).getX(), nemo.get(i).getY(), nemo.get(i).getWidth(), nemo.get(i).getHeight());

			}
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
