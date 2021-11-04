package nemo;
//
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

	private JButton shape[] = new JButton[3];
	private int sel;

	private ArrayList<Rect> nemo = new ArrayList<>();
	private ArrayList<RectSemo> semo = new ArrayList<>();

	private int nemoCnt;
	private int semoCnt;
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
		setShape();
		setColse();
	}

	private void setShape() {
		String str[] = { "■", "▲", "●" };
		for (int i = 0; i < 3; i++) {
			this.shape[i] = new JButton();
			this.shape[i].setBounds(10, 10 + (i * 51), 50, 50);
			this.shape[i].setText(str[i]);
			this.shape[i].addActionListener(this);
			this.shape[i].setVisible(true);

			add(this.shape[i]);
		}
	}

	private void setColse() {
		this.close.setBounds(650, 650, 100, 100);
		this.reset.setVisible(true);
		add(this.close);
	}

	private void setReset() {
		this.reset.setBounds(650, 590, 100, 50);
		this.reset.setText("RESET");
		this.reset.setVisible(true);
		this.reset.addActionListener(this);

		add(this.reset);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.reset) {
			this.nemo = new ArrayList<>();
			this.semo = new ArrayList<>();
			this.semoCnt = 0;
			this.nemoCnt = 0;
			this.sel = 0;
		} else if (e.getSource() == this.shape[0]) {
			this.sel = 0;
		} else if (e.getSource() == this.shape[1]) {
			this.sel = 1;
		} else if (e.getSource() == this.shape[2]) {
			this.sel = 2;

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

		if (this.sel == 1) {
			this.semo.add(new RectSemo());
			this.semoCnt++;
		} else {
			Rect temp = new Rect(startX, startY, this.width, this.height);
			this.nemo.add(temp);
			this.nemoCnt++;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (this.sel == 1) {
			this.semo.get(this.semoCnt - 1).setEnd(true);
		} else {
			this.nemo.get(this.nemoCnt - 1).setEnd(true);
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if (x > this.startX && y > this.startY) {
			// 시작점 : start와 동일
			if (this.shift) {
				if (this.sel == 1) {
					this.width = x - this.startX;
					this.height = (int) (this.width * Math.sqrt(3));
				} else {
					this.width = x - this.startX;
					this.height = this.width;
				}
			} else {
				this.width = x - this.startX;
				this.height = y - this.startY;
			}

			if (this.sel != 1) {
				this.nemo.get(this.nemoCnt - 1).setX(this.startX);
				this.nemo.get(this.nemoCnt - 1).setY(this.startY);
			} else {
				int tempX[] = { startX, startX - this.width, startX + this.width };
				int tempY[] = { startY, startY + this.height, startY + this.height };
				this.semo.get(this.semoCnt - 1).setX(tempX);
				this.semo.get(this.semoCnt - 1).setY(tempY);
			}

		} else if (x > this.startX && y < this.startY) {
			// 시작점 : x-w,y
			if (this.shift) {
				if (this.sel == 1) {
					this.width = x - this.startX;
					this.height = (int) (this.width * Math.sqrt(3));
				} else {
					this.width = x - this.startX;
					this.height = this.width;
				}
			} else {
				this.width = x - this.startX;
				this.height = this.startY - y;
			}

			if (this.sel != 1) {
				this.nemo.get(this.nemoCnt - 1).setX(x - this.width);
				this.nemo.get(this.nemoCnt - 1).setY(y);
			} else {
				int tempX[] = { startX, startX - this.width, startX + this.width };
				int tempY[] = { startY, startY - this.height, startY - this.height };
				this.semo.get(this.semoCnt - 1).setX(tempX);
				this.semo.get(this.semoCnt - 1).setY(tempY);
			}
		} else if (x < this.startX && y > this.startY) {
			// 시작점 : x,y-h
			if (this.shift) {
				if (this.sel == 1) {
					this.width = this.startX - x;
					this.height = (int) (this.width * Math.sqrt(3));
				} else {
					this.width = this.startX - x;
					this.height = this.width;
				}
			} else {
				this.width = this.startX - x;
				this.height = y - this.startY;

			}

			if (this.sel != 1) {
				this.nemo.get(this.nemoCnt - 1).setX(x);
				this.nemo.get(this.nemoCnt - 1).setY(y - this.height);
			} else {
				int tempX[] = { startX, startX - this.width, startX + this.width };
				int tempY[] = { startY, startY + this.height, startY + this.height };
				this.semo.get(this.semoCnt - 1).setX(tempX);
				this.semo.get(this.semoCnt - 1).setY(tempY);
			}
		} else if (x < this.startX && y < this.startY) {
			// 시작점 : x,y
			if (this.shift) {
				if (this.sel == 1) {
					this.width = this.startX - x;
					this.height = (int) (this.width * Math.sqrt(3));
				} else {
					this.width = this.startX - x;
					this.height = this.width;
				}
			} else {
				this.width = this.startX - x;
				this.height = this.startY - y;

			}

			if (this.sel != 1) {
				this.nemo.get(this.nemoCnt - 1).setX(x);
				this.nemo.get(this.nemoCnt - 1).setY(y);
			} else {
				int tempX[] = { startX, startX - this.width, startX + this.width };
				int tempY[] = { startY, startY - this.height, startY - this.height };
				this.semo.get(this.semoCnt - 1).setX(tempX);
				this.semo.get(this.semoCnt - 1).setY(tempY);
			}
		}

		if (this.sel != 1) {
			this.nemo.get(this.nemoCnt - 1).setWidth(this.width);
			this.nemo.get(this.nemoCnt - 1).setHeight(this.height);
			this.nemo.get(this.nemoCnt - 1).setShape(this.sel);
		}

	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		if (nemo != null) {
			for (int i = 0; i < this.nemoCnt; i++) {
				if (this.nemo.get(i).isEnd()) {
					g.setColor(Color.blue);
				} else {
					g.setColor(Color.red);
				}
				if (this.nemo.get(i).getShape() == 0) {
					g.drawRect(nemo.get(i).getX(), nemo.get(i).getY(), nemo.get(i).getWidth(), nemo.get(i).getHeight());
				} else if (this.nemo.get(i).getShape() == 2) {
					g.drawRoundRect(nemo.get(i).getX(), nemo.get(i).getY(), nemo.get(i).getWidth(),
							nemo.get(i).getHeight(), nemo.get(i).getWidth(), nemo.get(i).getHeight());
				}
			}
		}

		if (semo != null) {
			for (int i = 0; i < this.semoCnt; i++) {
				if (this.semo.get(i).isEnd()) {
					g.setColor(Color.blue);
				} else {
					g.setColor(Color.red);
				}
				g.drawPolygon(this.semo.get(i).getX(), this.semo.get(i).getY(), this.semo.get(i).getCnt());
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
		if ((JButton) e.getSource() == np.close) {
			this.dispose();
		}
	}

}
