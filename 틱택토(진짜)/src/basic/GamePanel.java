package basic;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements MouseListener {

	private int win = 0;
	private int turn = 1;
	private int gameCnt = -1;
	private final int SIZE = 3;
	private int posX = -1;
	private int posY = -1;
	private boolean right = true;

	private int map[][] = new int[SIZE][SIZE];
	private boolean game_start = false;

	public GamePanel() {
//		System.out.println("ac");
		this.addMouseListener(this);
		setBounds(100, 100, 410, 410);
//		setBackground(Color.black);
//		for(int i=0;i<SIZE;i++) {
//			for(int j=0;j<SIZE;j++) {
//				this.map[i][j] = new JButton();
//				this.map[i][j].setBounds(80+(85*i),80+(85*j), 80, 80);
//				this.map[i][j].setVisible(true);
//				this.map[i][j].addActionListener(this);
//				add(this.map[i][j]);
//			}
//		}
	}

	public void gameStart(boolean game_start) {
		this.game_start = game_start;
		if (game_start) { // 스타트

		} else { // 리셋
			this.win = 0;
			this.turn = 1;
			this.gameCnt = -1;

			this.posX = -1;
			this.posY = -1;
			this.right = true;

			this.map = new int[SIZE][SIZE];
			this.game_start = false;
		}
	}

	private boolean checkwin() {
		if (garocheck() || serocheck() || diagonal1() || diagonal2()) {
			this.win = this.turn;
			return true;
		}
		return false;
	}

	private boolean diagonal2() {
		int cnt = 0;
		for (int i = 0; i < SIZE; i++) {
				if(this.map[i][i] == this.turn) {
					cnt++;
				}
			
		}
		if(cnt == this.SIZE) {
			this.win = this.turn;
			return true;
		}
		return false;
	}

	private boolean diagonal1() {
		int cnt = 0;
		for (int i = 0; i < SIZE; i++) {
				if(this.map[i][this.SIZE-1-i] == this.turn) {
					cnt++;
				}
			
		}
		if(cnt == this.SIZE) {
			this.win = this.turn;
			return true;
		}
		return false;
	}

	private boolean serocheck() {
		for (int i = 0; i < SIZE; i++) {
			int cnt = 0;
			for (int j = 0; j < SIZE; j++) {
				if(this.map[j][i] == this.turn) {
					cnt++;
				}
			}
			
			if(cnt == this.SIZE) {
				this.win = turn;
				return true;
			}
		}
		return false;
	}

	private boolean garocheck() {
		for (int i = 0; i < SIZE; i++) {
			int cnt = 0;
			for (int j = 0; j < SIZE; j++) {
				if(this.map[i][j] == this.turn) {
					cnt++;
				}
			}
			
			if(cnt == this.SIZE) {
				this.win = turn;
				return true;
			}
		}
		return false;
	}

	@Override
	public void paint(Graphics g) {
		// 게임 화면 사각 테두리
		g.drawRect(0, 0, 400, 400);
		if (game_start && this.gameCnt == -1) {
			g.setFont(new Font("Default", Font.BOLD, 50));
			g.drawString("GAME START", 70, 150);
			this.gameCnt++;
		} else if (game_start && this.gameCnt != -1) {

			for (int i = 0; i < SIZE; i++) {
				for (int j = 0; j < SIZE; j++) {
					g.drawRect(5 + (i * 80), 5 + (j * 80), 70, 70);
					if (map[i][j] == 1) {
						// g.setColor(Color.red);
						g.setFont(new Font("Default", Font.BOLD, 30));
						g.drawString("" + map[i][j], 5 + (i * 80) + 22, 5 + (j * 80) + 45);
					} else if (map[i][j] == 2) {
						// g.setColor(Color.blue);
						g.setFont(new Font("Default", Font.BOLD, 30));
						g.drawString("" + map[i][j], 5 + (i * 80) + 22, 5 + (j * 80) + 45);
					} else {
						// g.setColor(Color.black);
						g.setFont(new Font("Default", Font.BOLD, 30));
						g.drawString("" + map[i][j], 5 + (i * 80) + 22, 5 + (j * 80) + 45);
					}
				}
			}
		} else if (!game_start) {
			for (int i = 0; i < SIZE; i++) {
				for (int j = 0; j < SIZE; j++) {
					g.drawRect(5 + (i * 80), 5 + (j * 80), 70, 70);
					g.setFont(new Font("Default", Font.BOLD, 30));
					// g.drawString("" + map[i][j], 80+(85*i)+40,80+(85*j)+40);
				}
			}
		}

		if (this.win != 0) {
			// 게임이 클리어 되면 클리어 화면 표시
			g.setColor(Color.RED);
			g.setFont(new Font("Default", Font.BOLD, 30));
			g.drawString("PLAYER"+this.win+"승리!!", 40, 150);
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (this.game_start) {
			posX = e.getX() / 80;
			posY = e.getY() / 80;

			for (int i = 0; i < SIZE; i++) {
				for (int j = 0; j < SIZE; j++) {
					if (posX == (5 + i * 80) / 80 && posY == (5 + j * 80) / 80 && this.map[i][j] == 0) {
						this.map[i][j] = this.turn;
						this.right = true;
						this.gameCnt++;
					} else if (posX == (5 + i * 80) / 80 && posY == (5 + j * 80) / 80 && this.map[i][j] != 0) {
						this.right = false;
					}
				}
			}
			if (this.gameCnt != -1 && this.right == true) {
				System.out.println();
				if (checkwin()) {

				} else {
					this.turn = this.turn == 1 ? 2 : 1;
				}
			} else {
				System.out.println("이미 고름");
			}

		}

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
