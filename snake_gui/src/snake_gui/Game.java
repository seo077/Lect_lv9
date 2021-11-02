package snake_gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

class Alert extends JFrame {
	JLabel text = new JLabel();

	public Alert(String text) {
		setLayout(null);
		setBounds(500, 500, 300, 300);
		setVisible(true);

		this.text.setBounds(0, 0, 300, 300);
		this.text.setText(text);
		this.text.setForeground(Color.red);
		this.text.setHorizontalAlignment(JLabel.CENTER);
		this.text.setVisible(true);
		add(this.text);
	}
}

class SnakePanel extends MyUtill {

	private int dir;

	private Rect[][] map;

	private Rect[] snake;
	private int size;
	private int itemSize = 6;

	private JLabel title = new JLabel();

	private JButton btn[] = new JButton[4];
	private JButton reset = new JButton();

	public SnakePanel() {
		setLayout(null);
		setBounds(0, 0, 1000, 700);
		setBackground(new Color(201, 182, 228));
		
		addKeyListener(this);
		setFocusable(true);

		setTitle();
		setReset();
		setBtn();
		setMap();
		setSnake();
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);

		update();

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				Rect temp = this.map[i][j];
				g.setColor(Color.black);
				g.drawRect(temp.getX(), temp.getY(), temp.getW(), temp.getH());
				if (temp.isItem()) {
					g.setColor(Color.yellow);
					g.fillRect(temp.getX() + 5, temp.getY() + 5, temp.getW() - 10, temp.getH() - 10);
				} else if (temp.isSnake()) {
					if (temp == this.snake[0]) {
						g.setColor(Color.GREEN);
					} else {
						g.setColor(Color.blue);
					}
					g.drawRoundRect(temp.getX(), temp.getY(), temp.getW(), temp.getH(), temp.getW() - 30,
							temp.getH() - 30);
					g.fillRoundRect(temp.getX(), temp.getY(), temp.getW(), temp.getH(), temp.getW() - 30,
							temp.getH() - 30);
				}
			}
		}
		repaint();
	}

	private void update() {
		if (this.dir != 0) {

			int y = -1;
			int x = -1;
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					if (this.snake[0] == this.map[i][j]) {
						y = i;
						x = j;
					}
				}
			}

			if (this.dir == 1) {
				y--;
			} else if (this.dir == 2) {
				x--;
			} else if (this.dir == 3) {
				y++;
			} else if (this.dir == 4) {
				x++;
			}

			if (y >= 0 && y < 10 && x >= 0 && x < 10) {
				setSnakeBoolean(false);

				// ¾ÆÀÌÅÛ ¸¸³µÀ» ¶§
				if (this.map[y][x].isItem()) {
					this.size++;
					this.itemSize--;
					this.map[y][x].setItem(false);

					if (this.itemSize == 0) {
						// game clear
						new Alert("GAME CLEAR");
					}
				}

				Rect temp[] = new Rect[this.size];
				temp[0] = this.map[y][x];
				for (int i = 0; i < this.size - 1; i++) {
					temp[i + 1] = this.snake[i];
				}
				this.snake = temp;

				// ÀÚ½ÅÀÇ ¸öÀ» ¸¸³µÀ» ¶§
				int check = -1;
				for (int i = 1; i < this.size; i++) {
					if (this.map[y][x] == this.snake[i]) {
						check = 1;
					}
				}

				if (check == 1) {
					// game over
					new Alert("GAME OVER");
				}

				setSnakeBoolean(true);
				temp = null;
			}

			try {
				Thread.sleep(300);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	}

	private void setSnakeBoolean(boolean bool) {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				for (int k = 0; k < this.size; k++) {
					if (this.snake[k] == this.map[i][j]) {
						this.map[i][j].setSnake(bool);
					}
				}
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource() == this.btn[0]) {
			this.dir = 1;
		} else if (e.getSource() == this.btn[1]) {
			this.dir = 2;
		} else if (e.getSource() == this.btn[2]) {
			this.dir = 3;
		} else if (e.getSource() == this.btn[3]) {
			this.dir = 4;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		this.dir = 0;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == e.VK_UP) {
			this.dir = 1;
		} else if (e.getKeyCode() == e.VK_LEFT) {
			this.dir = 2;
		} else if (e.getKeyCode() == e.VK_DOWN) {
			this.dir = 3;
		} else if (e.getKeyCode() == e.VK_RIGHT) {
			this.dir = 4;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		this.dir = 0;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.reset) {
			this.itemSize = 6;
			this.size = 4;
			this.dir = 0;

			setMap();
			setSnake();
		}
	}

	private void setSnake() {
		Random ran = new Random();

		// snake
		this.size = 4;
		this.snake = new Rect[this.size];
		for (int i = 0; i < this.size; i++) {
			this.map[0][i].setSnake(true);
			this.snake[i] = this.map[0][i];
		}

		// item
		int tempItemSize = this.itemSize;
		while (tempItemSize > 0) {
			int y = ran.nextInt(10);
			int x = ran.nextInt(10);

			if (!this.map[y][x].isItem() && !this.map[y][x].isSnake()) {
				this.map[y][x].setItem(true);
				tempItemSize--;
			}
		}
		
		//*
		requestFocusInWindow();
	}

	private void setMap() {
		// map
		this.map = new Rect[10][10];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				this.map[i][j] = new Rect(100 + (j * 50), 100 + (i * 50), 50, 50);
			}
		}
	}

	private void setTitle() {
		this.title.setText("Snake Game");
		this.title.setBounds(100, 10, 400, 100);
		this.title.setFont(new Font("", Font.BOLD, 40));
		this.title.setForeground(new Color(248, 237, 227));

		this.title.setVisible(true);
		add(this.title);
	}

	private void setReset() {
		this.reset.setText("Reset");
		this.reset.setBounds(830, 620, 100, 30);
		this.reset.setBackground(new Color(201, 182, 228));
		this.reset.setForeground(new Color(248, 237, 227));

		this.reset.addActionListener(this);
		this.reset.setVisible(true);
		add(this.reset);
	}

	private void setBtn() {
		String[] btnName = { "¡è", "¡ç", "¡é", "¡æ" };
		for (int i = 0; i < 4; i++) {
			this.btn[i] = new JButton();
			if (i == 0) {
				this.btn[i].setBounds(850, 500, 50, 50);
			} else {
				this.btn[i].setBounds(800 + ((i - 1) * 50), 550, 50, 50);
			}

			this.btn[i].setText(btnName[i]);
			this.btn[i].setBackground(new Color(201, 182, 228));
			this.btn[i].setForeground(new Color(248, 237, 227));

			this.btn[i].setVisible(true);
			this.btn[i].addMouseListener(this);
			add(this.btn[i]);
		}
	}
}

public class Game extends JFrame {

	private SnakePanel panel = new SnakePanel();

	public Game() {
		super("Snake Game");
		setLayout(null);
		setBounds(50, 50, 1000, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		add(panel);

		setVisible(true);
		revalidate();
	}
}
