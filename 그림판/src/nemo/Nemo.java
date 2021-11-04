package nemo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

class NemoPanel extends MyUtill {

	public JButton close = new JButton("close");
	
	private ArrayList<Rect> nemo = new ArrayList<>();
	private int cnt;
	private boolean shift;
	private int startX, startY, width, height;
	private JButton reset = new JButton();

	public NemoPanel() {
		setLayout(null);
		setBounds(0, 0, 800, 800);
		setBackground(Color.yellow);

		addMouseMotionListener(this);
		addMouseListener(this);

		setFocusable(true);
		addKeyListener(this);
		setReset();
		setColse();
	}

	private void setColse() {
		this.close.setBounds(650, 650, 100, 100);
		this.reset.setVisible(true);
		add(this.close);
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
		
		requestFocusInWindow(); // keyListener에 대한 focus 요청
		repaint();
	}
}

public class Nemo extends JFrame implements ActionListener {

	private NemoPanel np = new NemoPanel();

	public Nemo() {
		super("Nemo");
		setLayout(null);
		setBounds(300, 0, 800, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		np.close.addActionListener(this);
		add(np);
		
		setVisible(true);
		revalidate();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if((JButton)e.getSource() == np.close) {
			this.dispose();
		}
	}

	

}
