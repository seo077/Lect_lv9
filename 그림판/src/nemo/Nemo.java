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

	private JButton shape[] = new JButton[4];
	private int sel;
	
	private Color colors[] = {new Color(243, 129, 129),new Color(240, 138, 93),new Color(249, 237, 105),new Color(149, 225, 211),new Color(168, 216, 234),new Color(170, 150, 218)};
	private JButton color[] = new JButton[6];
	private int selColor = -1;
	
	private ArrayList<Rect> nemo = new ArrayList<>();
	private ArrayList<RectSemo> semo = new ArrayList<>();

	private ArrayList<RectBrush> brush = new ArrayList<>();
	
	private int brushCnt;
	private int nemoCnt;
	private int semoCnt;
	private boolean shift;
	private int startX, startY, width, height;
	private JButton reset = new JButton();

	public NemoPanel() {
		setLayout(null);
		setBounds(0, 0, 800, 800);
		//setBackground(Color.yellow);

		addMouseMotionListener(this);
		addMouseListener(this);

		setFocusable(true);
		addKeyListener(this);
		setReset();
		setShape();
		setColor();
		setColse();
	}

	private void setColor() {
		for(int i=0;i<6;i++) {
			this.color[i] = new JButton();
			this.color[i].setBounds(70+(i*50),650,50,50);
			this.color[i].setBackground(this.colors[i]);
			this.color[i].addActionListener(this);
			this.color[i].setVisible(true);
			this.color[i].setBorder(null);
			
			add(this.color[i]);
		}
	}

	private void setShape() {
		String str[] = { "■", "▲", "●", "/" };
		for (int i = 0; i < 4; i++) {
			this.shape[i] = new JButton();
			this.shape[i].setBounds(10, 10 + (i * 51), 50, 50);
			this.shape[i].setText(str[i]);
			this.shape[i].addActionListener(this);
			this.shape[i].setVisible(true);
			this.shape[i].setBackground(new Color(254, 245, 237));

			add(this.shape[i]);
		}
	}

	private void setColse() {
		this.close.setBounds(650, 650, 100, 100);
		this.close.setVisible(true);
		this.close.setBackground(new Color(254, 245, 237));
		add(this.close);
	}

	private void setReset() {
		this.reset.setBounds(650, 590, 100, 50);
		this.reset.setText("RESET");
		this.reset.setVisible(true);
		this.reset.addActionListener(this);
		this.reset.setBackground(new Color(254, 245, 237));

		add(this.reset);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.reset) {
			this.nemo = new ArrayList<>();
			this.semo = new ArrayList<>();
			this.brush = new ArrayList<>();
			
			this.brushCnt = 0;
			this.semoCnt = 0;
			this.nemoCnt = 0;
			this.sel = 0;
			if(this.selColor != -1) {
				this.color[this.selColor].setBounds(70+(this.selColor*50),650,50,50);
			}
			this.selColor = -1;
		}
		
		for(int i=0;i<4;i++) {
			if(e.getSource() == this.shape[i]) {
				this.sel = i;
			}
		}
		
		for(int i=0; i<6;i++) {
			if(e.getSource() == this.color[i]) {
				if(this.selColor != -1) {
					this.color[this.selColor].setBounds(70+(this.selColor*50),650,50,50);
				}
				this.selColor = i;
				this.color[i].setBounds(70+(i*50),650,50,90);
			}
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
		} else if(sel == 0 || sel == 2){
			Rect temp = new Rect(startX, startY, this.width, this.height);
			this.nemo.add(temp);
			this.nemoCnt++;
		}else if(this.sel == 3) {
			this.brush.add(new RectBrush());
			this.brushCnt++;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (this.sel == 1) {
			this.semo.get(this.semoCnt - 1).setEnd(true);
		} else if(this.sel == 0 || this.sel == 2) {
			this.nemo.get(this.nemoCnt - 1).setEnd(true);
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		//절대값 사용해서 간단히!(Math.abs())

		if (this.sel < 3) {
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
					this.semo.get(this.semoCnt -1 ).setColor(this.selColor);
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
					this.semo.get(this.semoCnt -1 ).setColor(this.selColor);
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
					this.semo.get(this.semoCnt -1 ).setColor(this.selColor);
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
					this.semo.get(this.semoCnt -1 ).setColor(this.selColor);
				}
			}

			if (this.sel != 1) {
				this.nemo.get(this.nemoCnt - 1).setWidth(this.width);
				this.nemo.get(this.nemoCnt - 1).setHeight(this.height);
				this.nemo.get(this.nemoCnt - 1).setShape(this.sel);
				this.nemo.get(this.nemoCnt -1).setColor(this.selColor);
			}

		}else {
			this.brush.get(this.brushCnt-1).addX(x);
			this.brush.get(this.brushCnt-1).addY(y);
			this.brush.get(this.brushCnt-1).setColor(this.selColor);
		}
		

	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		if (nemo != null) {
			for (int i = 0; i < this.nemoCnt; i++) {
//				if (this.nemo.get(i).isEnd()) {
//					g.setColor(Color.blue);
//				} else {
//					g.setColor(Color.red);
//				}
				if(this.nemo.get(i).getColor() != -1) {
					g.setColor(colors[this.nemo.get(i).getColor()]);
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
//				if (this.semo.get(i).isEnd()) {
//					g.setColor(Color.blue);
//				} else {
//					g.setColor(Color.red);
//				}
				if(this.semo.get(i).getColor() != -1) {
					g.setColor(colors[this.semo.get(i).getColor()]);
				}
				g.drawPolygon(this.semo.get(i).getX(), this.semo.get(i).getY(), this.semo.get(i).getCnt());
			}
		}

		if(this.brush != null) {
			for(int i=0;i<this.brushCnt;i++) {
				if(this.brush.get(i).getColor() != -1) {
					g.setColor(colors[this.brush.get(i).getColor()]);
				}
				int size = this.brush.get(i).getSize();
				int x[] = new int[size];
				int y[] = new int[size];
				for(int j=0;j<size;j++) {
					x[j] = this.brush.get(i).getX(j);
					y[j] = this.brush.get(i).getY(j);
				}
				
				g.drawPolyline(x, y, size);
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
