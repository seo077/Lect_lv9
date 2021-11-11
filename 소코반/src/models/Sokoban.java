package models;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

class Alert extends JFrame{
	JLabel text1 = new JLabel();
	JLabel text2 = new JLabel();
	
	public Alert(String show_time) {
		setLayout(null);
		setBounds(500, 100, 300, 300);
		setVisible(true);
		
		this.text1.setText("GAME CLEAR!!");
		this.text1.setFont(new Font("", Font.BOLD, 30));
		this.text2.setFont(new Font("", Font.BOLD, 20));
		this.text2.setText(String.format("소요 시간 : %s", show_time));
		this.text1.setBounds(50, 50, 300, 50);
		this.text2.setBounds(50, 150, 500, 50);
		this.text1.setForeground(Color.green);
		this.text2.setForeground(Color.red);
		add(this.text1);
		add(this.text2);
	}
}

public class Sokoban extends JFrame {

	public Sokoban() {
		super("Sokoban");

		setLayout(null);
		setBounds(300, 10, 710, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		add(new Panel());

		setVisible(true);
		revalidate();
	}
}

class Panel extends MyUtill implements Runnable {
	private Random ran = new Random();

	private JLabel title, time;
	private JButton reset;

	private final int SIZE = 10;
	private Tile[][] map = new Tile[SIZE][SIZE];

	private final int wallCnt = 13;
	private final int ballCnt = 6;
	private final int goalCnt = 6;
	private Image im = new ImageIcon("images/tile3.png").getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
	private int playerY, playerX;

	Thread th;
	private SimpleDateFormat time_format = new SimpleDateFormat("mm.ss.SSS");
	private long start_time, cur_time, end_time;
	private String show_time;
	private String start = "00.00.000";

	private boolean run; // 방향키 누르면 시작
	private int win;

	public Panel() {
		setLayout(null);
		setBounds(0, 0, 710, 800);
		setBackground(Color.orange);

		setLable();
		setButton();
		setMap();

		addKeyListener(this);
		setFocusable(true);

		this.th = new Thread(this);
		this.th.start();
	}

	private void setLable() {
		this.title = new JLabel();
		this.title.setText("Sokoban Game");
		this.title.setFont(new Font("", Font.BOLD, 30));
		this.title.setBounds(50, 00, 300, 80);

		this.time = new JLabel();
		this.show_time = this.start;
		this.time.setText(this.show_time);
		this.time.setFont(new Font("", Font.BOLD, 20));
		this.time.setForeground(Color.blue);
		this.time.setBounds(300, 10, 100, 70);

		add(this.time);
		add(this.title);
	}

	private void setButton() {
		this.reset = new JButton();
		this.reset.setText("reset");
		this.reset.setBounds(550, 30, 70, 25);
		this.reset.addActionListener(this);

		add(this.reset);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.reset) {
			this.run = false;
			this.show_time = this.start;
			this.time.setText(show_time);
			this.map = new Tile[SIZE][SIZE];
			this.win = 0;
			setMap();
			requestFocusInWindow();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (!this.run && this.win < this.ballCnt) {
			if (e.getKeyCode() == e.VK_DOWN || e.getKeyCode() == e.VK_UP || e.getKeyCode() == e.VK_RIGHT
					|| e.getKeyCode() == e.VK_LEFT) {
				this.run = true;
				this.start_time = System.currentTimeMillis();
			}
		}

		int tempX = this.playerX;
		int tempY = this.playerY;
		int bY = 0;
		int bX = 0;

		if (e.getKeyCode() == e.VK_DOWN) {
			tempY++;
			bY++;
		} else if (e.getKeyCode() == e.VK_UP) {
			tempY--;
			bY--;
		} else if (e.getKeyCode() == e.VK_RIGHT) {
			tempX++;
			bX++;
		} else if (e.getKeyCode() == e.VK_LEFT) {
			tempX--;
			bX--;
		}

		// 범위
		if (tempX >= 0 && tempX < SIZE && tempY >= 0 && tempY < SIZE) {
			int check = -1;
			// 벽 예외
			if (this.map[tempY][tempX].getState() != Tile.WALL) {
				// 공 만났을 때
				if (this.map[tempY][tempX].getState() == Tile.BALL) {
					int ballY = tempY + bY;
					int ballX = tempX + bX;
					if (ballX >= 0 && ballX < SIZE && ballY >= 0 && ballY < SIZE) {
						if (this.map[ballY][ballX].getState() != Tile.WALL && this.map[ballY][ballX].getState() != Tile.BALL && this.map[ballY][ballX].getState() != Tile.BALL_ENTERED) {
							// 골대
							if (this.map[ballY][ballX].getState() == Tile.GOAL) {
								this.map[tempY][tempX].setState(Tile.ROAD);
								this.map[ballY][ballX].setState(Tile.BALL_ENTERED);
								this.win++;
							} else {
								this.map[tempY][tempX].setState(Tile.ROAD);
								this.map[ballY][ballX].setState(Tile.BALL);
							}
						}else {
							check = 1;
						}
					}else {
						check = 1;
					}

				}
				// 공이 들어있는 골을 만났을 때
				if (this.map[tempY][tempX].getState() == Tile.BALL_ENTERED) {
					int ballY = tempY + bY;
					int ballX = tempX + bX;
					if (ballX >= 0 && ballX < SIZE && ballY >= 0 && ballY < SIZE) {
						if (this.map[ballY][ballX].getState() == Tile.ROAD) {
							this.map[tempY][tempX].setState(Tile.GOAL);
							this.map[ballY][ballX].setState(Tile.BALL);
							this.win--;
						}else if(this.map[ballY][ballX].getState() == Tile.GOAL){
							this.map[tempY][tempX].setState(Tile.GOAL);
							this.map[ballY][ballX].setState(Tile.BALL_ENTERED);	
							this.win--;
						}else {
							check = 1;
						}
					}else {
						check = 1;
					}
				}

				if(check == -1) {
					this.map[this.playerY][this.playerX].setPlayer(false);
					this.playerX = tempX;
					this.playerY = tempY;
					this.map[this.playerY][this.playerX].setPlayer(true);
					
				}

			}

		}

	}

	private void setMap() {
		setWall();
		setBall();
		setGoal();
		setPlayer();
		setRest();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (!this.map[i][j].isPlayer()) {
					g.drawImage(this.map[i][j].getImage().getImage(), this.map[i][j].getX(), this.map[i][j].getY(),
							null);
				} else {
					g.drawImage(im, this.map[i][j].getX(), this.map[i][j].getY(), null);
				}
			}
		}
		
		if(this.win == this.ballCnt) {
			this.run = false;
			new Alert(show_time);
			this.win++;
		}
		repaint();
	}

	private void setWall() {
		int cnt = this.wallCnt;
		while (cnt > 0) {
			int x = ran.nextInt(SIZE);
			int y = ran.nextInt(SIZE);
			if (this.map[y][x] == null) {
				this.map[y][x] = new Tile(Tile.WALL, 0 + (70 * x), 60 + (70 * y), 70, 70);
				cnt--;
			}

		}
	}

	private void setBall() {
		int cnt = this.ballCnt;
		while (cnt > 0) {
			int x = ran.nextInt(SIZE - 2) + 1;
			int y = ran.nextInt(SIZE - 2) + 1;
			// 사방에 벽이 없도록
			if (this.map[y][x] == null) {
				int check = 0;
				for (int i = -1; i < 2; i++) {
					for (int j = -1; j < 2; j++) {
						if (this.map[y + i][x + j] != null && this.map[y + i][x + j].getState() == Tile.WALL) {
							check++;
						}
					}
				}
				if (check == 0) {
					this.map[y][x] = new Tile(Tile.BALL, 0 + (70 * x), 60 + (70 * y), 70, 70);
					cnt--;
				}
			}
		}

	}

	private void setGoal() {
		int cnt = this.goalCnt;
		while (cnt > 0) {
			int x = ran.nextInt(SIZE);
			int y = ran.nextInt(SIZE);
			if (this.map[y][x] == null) {
				this.map[y][x] = new Tile(Tile.GOAL, 0 + (70 * x), 60 + (70 * y), 70, 70);
				cnt--;
			}

		}
	}

	private void setPlayer() {
		while (true) {
			int x = ran.nextInt(SIZE - 2) + 1;
			int y = ran.nextInt(SIZE - 2) + 1;
			// 사방에 벽이 없도록
			if (this.map[y][x] == null) {
				int check = 0;
				for (int i = -1; i < 2; i++) {
					for (int j = -1; j < 2; j++) {
						if (this.map[y + i][x + j] != null && this.map[y + i][x + j].getState() == Tile.WALL) {
							check++;
						}
					}
				}
				if (check < 3) {
					this.map[y][x] = new Tile(Tile.ROAD, 0 + (70 * x), 60 + (70 * y), 70, 70);
					this.map[y][x].setPlayer(true);
					this.playerX = x;
					this.playerY = y;
					break;

				}
			}

		}
	}

	private void setRest() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (this.map[i][j] == null) {
					this.map[i][j] = new Tile(Tile.ROAD, 0 + (70 * j), 60 + (70 * i), 70, 70);
				}
			}
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				if (this.run) {
					time_check();
				}
				Thread.sleep(15);
			} catch (Exception e) {
			}
		}

	}

	private void time_check() {
		this.cur_time = System.currentTimeMillis();
		this.show_time = String.valueOf(time_format.format(this.cur_time - this.start_time));
		this.time.setText(show_time);
	}
}
