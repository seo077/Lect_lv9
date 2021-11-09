package 경마;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Panel extends MyUtill {

	private Image horsesIcon[] = new Image[5];
	private Pos horses[] = new Pos[5];
	
	private ArrayList<ArrayList<Pos>> tempMove = new ArrayList<>();
	private int[]rank = new int[5];

	private JButton start = new JButton();
	private JButton reset = new JButton();
	private boolean run; //경마시작
	private int curTurn = 0;
	private int totalTurn;

	private final int GOAL = 530;

	public Panel() {
		setLayout(null);
		setBounds(0, 0, 700, 700);

		setStart();
		setReset();
		setHorsesIcon();
		setReady();
		setTempMove();

	}
	
	private void setTempMove() {
		Random ran = new Random();
		int end = 0;

		int index = 0; // turn
		int check = 1; // rank
		
		while (end < 5) {
			end = 0;
			boolean up = true;
			boolean goal = false;

			for (int i = 0; i < 5; i++) {
				int xx = this.tempMove.get(i).get(index).getX();
				int yy = this.tempMove.get(i).get(index).getY();

				if (!this.tempMove.get(i).get(index).isGoal()) {
					int r = ran.nextInt(50) + 10;
					int temp = xx + r;
					
					if(temp >= this.GOAL) {
						temp = this.GOAL;
					}
					this.tempMove.get(i).add(new Pos(temp, yy));
					
					if (temp >= this.GOAL && !goal) {
						this.tempMove.get(i).get(index+1).setGoal(true);
						goal = true;
						this.rank[i] = check;
						check++;
					} 
					else if (temp >= this.GOAL && goal) {
						this.tempMove = new ArrayList<>();
						setReady();
						this.rank = new int[5];
						check = 1;
						index = 0;
						up = false;
						break;
					}
				} else {
					this.tempMove.get(i).add(new Pos(this.GOAL, yy));
					this.tempMove.get(i).get(index+1).setGoal(true);
					end++;
				}
			}
			if(up) {
				index++;
			}
			
		}
		this.totalTurn = index;
	}

	private void setReady() {
		for (int i = 0; i < 5; i++) {
			this.horses[i] = new Pos(10, 50 + (100 * i));
			this.tempMove.add(new ArrayList<>());
			this.tempMove.get(i).add(new Pos(10, 50 + (100 * i)));
		}
	}

	private void setReset() {
		this.reset.setText("reset");
		this.reset.setBounds(300, 600, 100, 40);
		this.reset.addActionListener(this);

		add(this.reset);
	}

	private void setStart() {
		this.start.setText("start");
		this.start.setBounds(150, 600, 100, 40);
		this.start.addActionListener(this);

		add(this.start);
	}

	private void setHorsesIcon() {
		this.horsesIcon[0] = new ImageIcon("images/horse1.png").getImage().getScaledInstance(70, 70,
				Image.SCALE_SMOOTH);
		this.horsesIcon[1] = new ImageIcon("images/horse2.png").getImage().getScaledInstance(70, 70,
				Image.SCALE_SMOOTH);
		this.horsesIcon[2] = new ImageIcon("images/horse3.png").getImage().getScaledInstance(70, 70,
				Image.SCALE_SMOOTH);
		this.horsesIcon[3] = new ImageIcon("images/horse4.png").getImage().getScaledInstance(70, 70,
				Image.SCALE_SMOOTH);
		this.horsesIcon[4] = new ImageIcon("images/horse5.png").getImage().getScaledInstance(70, 70,
				Image.SCALE_SMOOTH);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		// 결승선
		int x[] = { 600, 600, 600, 600, 600, 600 };
		int y[] = { 50, 200, 300, 400, 500, 550 };
		g.drawPolyline(x, y, 6);

		for (int i = 0; i < 5; i++) {
			g.drawImage(this.horsesIcon[i], this.horses[i].getX(), this.horses[i].getY(), null);
		}

		if(this.totalTurn != 0 && this.totalTurn <= this.curTurn) {
			for(int i=0;i<5;i++) {
				g.setFont(new Font("", Font.BOLD, 30));
				g.drawString(String.valueOf(this.rank[i]),630, this.horses[i].getY()+50);
			}
			this.run = false;
		}
		
		if(this.run) {
			try {
				move();
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		repaint();
	}

	private void move() {
		this.curTurn++;
		for(int i=0;i<5;i++) {
			this.horses[i].setX(this.tempMove.get(i).get(this.curTurn).getX());
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.start) {
			this.run = true;
		}else {
			this.horses = new Pos[5];
			this.rank = new int[5];
			this.curTurn = 0;
			this.totalTurn = 0;
			this.tempMove = new ArrayList<>();
			setReady();
			setTempMove();
		}
	}
}
