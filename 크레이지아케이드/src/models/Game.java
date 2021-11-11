package models;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

class Guide extends JFrame {
	JLabel text1 = new JLabel();
	JLabel text2 = new JLabel();
	JLabel text3 = new JLabel();
	
	public Guide() {
		setLayout(null);
		setBounds(300, 100, 300, 300);
		setVisible(true);
		
		this.text1.setForeground(Color.blue);
		this.text1.setFont(new Font("", Font.BOLD, 20));
		this.text2.setFont(new Font("", Font.BOLD, 20));
		this.text3.setFont(new Font("", Font.BOLD, 20));
		this.text1.setBounds(50, 10, 250, 100);
		this.text1.setText("[방향키] : 이동");
		this.text2.setForeground(Color.blue);
		this.text2.setBounds(50,60, 250, 100);
		this.text2.setText("[스페이스바] : 폭탄 설치");
		this.text3.setForeground(Color.blue);
		this.text3.setBounds(50, 130, 250, 100);
		this.text3.setText("[엔터키] : 폭파");
		
		add(this.text1);
		add(this.text2);
		add(this.text3);
	}
}
class GameOverAlert extends JFrame {
	JLabel text = new JLabel();

	public GameOverAlert() {
		setLayout(null);
		setBounds(300, 100, 300, 300);
		setVisible(true);

		this.text.setBounds(50, 0, 250, 300);
		this.text.setText("GAME OVER");
		this.text.setFont(new Font("", Font.BOLD, 30));
		this.text.setForeground(Color.red);

		add(this.text);
	}
}

class winAlert extends JFrame {
	JLabel text = new JLabel();

	public winAlert() {
		setLayout(null);
		setBounds(300, 100, 300, 300);
		setVisible(true);

		this.text.setBounds(50, 0, 250, 300);
		this.text.setText("GAME CLEAR");
		this.text.setFont(new Font("", Font.BOLD, 30));
		this.text.setForeground(Color.blue);

		add(this.text);
	}
}

public class Game extends JFrame {
	public Game() {
		super("크레이지 아케이드");
		setLayout(null);
		setBounds(200, 10, 700, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		add(new Panel());

		setVisible(true);
		revalidate();
	}
}

class Panel extends MyUtill {
	private Random ran = new Random();

	private final int SIZE = 10;
	private Map map[][] = new Map[SIZE][SIZE];
	private final int BrickCnt = 20;

	private Image im = new ImageIcon("images/bazzi.jpg").getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
	private int playerX, playerY;

	private JLabel title = new JLabel();
	private JButton reset = new JButton();
	private JButton guide = new JButton();
	private int itemCnt;
	private int bombCnt;
	private ArrayList<Integer> bombY = new ArrayList<>();
	private ArrayList<Integer> bombX = new ArrayList<>();
	private int breakBrick = 0;

	private boolean end;

	public Panel() {
		setLayout(null);
		setBounds(0, 0, 700, 800);

		addKeyListener(this);
		setFocusable(true);

		setTitle();
		setReset();
		setGuide();
		setMap();
	}

	private void setTitle() {
		this.title.setText("크레이지 아케이드");
		this.title.setFont(new Font("", Font.BOLD, 30));
		this.title.setBounds(50, 00, 300, 80);

		add(this.title);
	}

	private void setReset() {
		this.reset.setText("reset");
		this.reset.setBounds(550, 30, 70, 25);
		this.reset.addActionListener(this);

		add(this.reset);
	}

	private void setGuide() {
		this.guide.setText("Guide");
		this.guide.setBounds(470, 30, 70, 25);
		this.guide.addActionListener(this);

		add(this.guide);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.reset) {
			this.map = new Map[SIZE][SIZE];
			this.itemCnt = 0;
			this.bombCnt = 0;
			this.bombX = new ArrayList<>();
			this.bombY = new ArrayList<>();
			this.breakBrick = 0;
			this.end = false;
			setMap();
		} else if (e.getSource() == this.guide) {
			new Guide();
		}
	}

	private void setMap() {
		setBrick();
		setPlayer();
		setGrass();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.blue);
		g.drawString(String.format("아이템 수 : %d", this.itemCnt), 340, 30);
		g.drawString(String.format("설치한 폭탄 수 : %d", this.bombCnt), 340, 50);

		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (this.map[i][j].isPlayer()) {
					g.drawImage(this.im, this.map[i][j].getX(), this.map[i][j].getY(), null);
				} else {
					g.drawImage(this.map[i][j].getIm().getImage(), this.map[i][j].getX(), this.map[i][j].getY(), null);
				}
			}
		}

		if (!this.end && this.breakBrick == this.BrickCnt) {
			new winAlert();
			this.end = true;
			this.breakBrick++;
		}
		requestFocusInWindow();
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (!this.end) {
			int tempY = this.playerY;
			int tempX = this.playerX;

			if (e.getKeyCode() == e.VK_ENTER) { // 폭파
				if (this.bombCnt > 0) {
					int bY = this.bombY.get(0);
					int bX = this.bombX.get(0);
					this.bombY.remove(0);
					this.bombX.remove(0);
					this.bombCnt--;
					explode(bY, bX);
				}
			} else if (e.getKeyCode() == e.VK_SPACE) { // 폭탄 설치
				if(this.bombCnt == 0) {
					this.map[tempY][tempX].setState(Map.BOMB);
					this.bombY.add(tempY);
					this.bombX.add(tempX);
					this.bombCnt++;
				}else {
					if(this.itemCnt != 0) {
						this.map[tempY][tempX].setState(Map.BOMB);
						this.bombY.add(tempY);
						this.bombX.add(tempX);
						this.bombCnt++;
						this.itemCnt--;
					}
				}

			} else if (e.getKeyCode() == e.VK_DOWN) {
				tempY++;
			} else if (e.getKeyCode() == e.VK_UP) {
				tempY--;
			} else if (e.getKeyCode() == e.VK_RIGHT) {
				tempX++;
			} else if (e.getKeyCode() == e.VK_LEFT) {
				tempX--;
			}

			if (tempY >= 0 && tempY < SIZE && tempX >= 0 && tempX < SIZE
					&& this.map[tempY][tempX].getState() != Map.BRICK) {
				if (this.map[tempY][tempX].getState() == Map.ITEM) {
					this.itemCnt++;
					this.map[tempY][tempX].setState(Map.GRASS);
				}
				this.map[this.playerY][this.playerX].setPlayer(false);
				this.playerX = tempX;
				this.playerY = tempY;
				this.map[this.playerY][this.playerX].setPlayer(true);
			}
		}
	}

	private void explode(int bY, int bX) {

		for (int i = -1; i < 2; i++) {
			if (i != 0 && bY + i >= 0 && bY + i < SIZE) {
				if (this.map[bY + i][bX].getState() == Map.BRICK) {
					breakBrick(bY + i, bX);
				} else if (this.map[bY + i][bX].getState() == Map.ITEM) {
					breakItem(bY + i, bX);
				} else if (this.map[bY + i][bX].getState() == Map.BOMB) {
					this.map[bY][bX].setState(Map.GRASS);
					breakBomb(bY + i, bX);
				} else if (this.map[bY + i][bX].isPlayer()) {
					die();
					break;
				}
			}
			if (i != 0 && bX + i >= 0 && bX + i < SIZE) {
				if (this.map[bY][bX + i].getState() == Map.BRICK) {
					breakBrick(bY, bX + i);
				} else if (this.map[bY][bX + i].getState() == Map.ITEM) {
					breakItem(bY, bX + i);
				} else if (this.map[bY][bX + i].getState() == Map.BOMB) {
					this.map[bY][bX].setState(Map.GRASS);
					breakBomb(bY, bX + i);
				} else if (this.map[bY][bX + i].isPlayer()) {
					die();
					break;
				}
			}
		}

		this.map[bY][bX].setState(Map.GRASS);

	}

	private void breakBrick(int bY, int bX) {
		this.breakBrick++;
		int r = ran.nextInt(2);
		if (r == 0) {
			this.map[bY][bX].setState(Map.GRASS);
		} else {
			this.map[bY][bX].setState(Map.ITEM);
		}
	}

	private void breakItem(int bY, int bX) {
		this.map[bY][bX].setState(Map.GRASS);
	}

	private void breakBomb(int bY, int bX) {
		int check = -1;
		System.out.println(this.bombX.size());
		for (int i = 0; i < this.bombX.size(); i++) {
			if (bY == (int) this.bombY.get(i) && bX == (int) this.bombX.get(i)) {
				check = i;
			}
		}

		int tY = this.bombY.get(check);
		int tX = this.bombX.get(check);

		this.bombX.remove(check);
		this.bombY.remove(check);
		this.bombCnt--;

		explode(tY, tX);
	}

	private void die() {
		new GameOverAlert();
		this.end = true;
	}

	private void setBrick() {
		int cnt = this.BrickCnt;
		while (cnt > 0) {
			int x = ran.nextInt(SIZE);
			int y = ran.nextInt(SIZE);

			if (this.map[y][x] == null) {
				this.map[y][x] = new Map(Map.BRICK, 0 + (70 * x), 70 + (70 * y), 70, 70);
				cnt--;
			}

		}
	}

	private void setPlayer() {
		while (true) {
			int x = ran.nextInt(SIZE - 2) + 1;
			int y = ran.nextInt(SIZE - 2) + 1;

			if (this.map[y][x] == null) {
				int check = 0;
				for (int i = -1; i < 2; i++) {
					for (int j = -1; j < 2; j++) {
						if (this.map[y + i][x + j] != null && this.map[y + i][x + j].getState() == Map.BRICK) {
							check++;
						}
					}
				}

				if (check < 4) {
					this.map[y][x] = new Map(Map.GRASS, 0 + (70 * x), 70 + (70 * y), 70, 70);
					this.map[y][x].setPlayer(true);
					this.playerX = x;
					this.playerY = y;
					break;
				}
			}
		}
	}

	private void setGrass() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (this.map[i][j] == null) {
					this.map[i][j] = new Map(Map.GRASS, 0 + (70 * j), 70 + (70 * i), 70, 70);
				}
			}
		}
	}
}
