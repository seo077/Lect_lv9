package basic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class TictectoeFrame extends JFrame {
	private Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
	private int width = dm.width;
	private int height = dm.height;
	public final static int W = 800;
	public final static int H = 600;

	public TictectoeFrame() {
		super("TICTECTOE");
		setLayout(null);

		setBounds(this.width / 2 - W / 2, this.height / 2 - H / 2, W, H);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		add(new TictectoeTitle());
		TictectoeStart ts = new TictectoeStart();
		TictectoePanel tp = new TictectoePanel();
		add(ts);
		add(tp);

		while (true) {
			if (tp.win()) {
				tp.setButton();
			}
		}

	}

	class TictectoeTitle extends JPanel {
		JButton title = new JButton("tictectoe");

		public TictectoeTitle() {
			setBounds(TictectoeFrame.W / 2 - 50, 70, 100, 50);
			setBackground(Color.GRAY);
			this.title.setBounds(0, 0, 100, 50);
			this.title.setVisible(true);
			add(this.title);
		}
	}

	class TictectoePanel extends JPanel implements ActionListener {

		private int turn = 1;
		private int win = 0;

		final int SIZE = 60;
		JButton[] map = new JButton[9];
		int temp[] = new int[9];

		public TictectoePanel() {

			setLayout(null);
			setBounds(TictectoeFrame.W / 2 - 150, TictectoeFrame.H / 2 - 150, 300, 300);
			setBackground(Color.LIGHT_GRAY);

			setButton();

		}

		public boolean win() {
			if (this.win == 0) {
				return false;
			} else {
				return true;
			}
		}

		public void setButton() {
			int k = 0;
			for (int i = 0; i < 9; i++) {
				this.map[i] = new JButton();
				this.map[i].setBounds(SIZE + (SIZE * (i % 3)), (SIZE * k) + SIZE, SIZE, SIZE);
				this.map[i].setVisible(true);
				this.map[i].addActionListener(this);
				add(this.map[i]);
				if (i % 3 == 2) {
					k++;
				}
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton temp = (JButton) e.getSource();

			boolean check = true;
			for (int i = 0; i < this.map.length; i++) {
				if (this.map[i] == temp && this.temp[i] == 0 && this.turn == 1) {
					this.map[i].setBackground(Color.pink);
					this.temp[i] = this.turn;
				} else if (this.map[i] == temp && this.temp[i] == 0 && this.turn == 2) {
					this.map[i].setBackground(Color.blue);
					this.temp[i] = this.turn;
				} else if (this.map[i] == temp && this.temp[i] != 0) {
					check = false;
				}
			}

			if (check) {
				if (checkwin()) {
					printwinner();
				} else {
					this.turn = this.turn == 1 ? 2 : 1;
				}
			} else {
				System.out.println("같은 것 고르면 안돼");
			}
		}

		private void printwinner() {
			System.out.println("[게임 종료]");
			System.out.printf("PLAYER%d의 승리!!!\n", this.win);
			this.turn = 1;
			this.win = 0;

			this.map = new JButton[9];
			this.temp = new int[9];
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
			for (int i = 2; i < 9; i += 2) {
				if (this.temp[i] == this.turn) {
					cnt++;
				}
				if (cnt == 3) {
					return true;
				}

			}
			return false;
		}

		private boolean diagonal1() {
			int cnt = 0;
			for (int i = 0; i < 9; i += 4) {
				if (this.temp[i] == this.turn) {
					cnt++;
				}
				if (cnt == 3) {
					return true;
				}

			}
			return false;
		}

		private boolean serocheck() {
			for (int i = 0; i < 3; i++) {
				int cnt = 0;
				for (int j = 0; j < 9; j += 3) {
					if (this.temp[i + j] == this.turn) {
						cnt++;
					}
					if (cnt == 3) {
						return true;
					}
				}
			}
			return false;
		}

		private boolean garocheck() {
			int cnt = 0;
			for (int i = 0; i < 9; i++) {
				if (this.temp[i] == this.turn) {
					cnt++;
				}
				if (cnt == 3) {
					return true;
				}
				if (i % 3 == 2) {
					cnt = 0;
				}

			}
			return false;
		}

	}

	class TictectoeStart extends JPanel implements ActionListener {
		JButton start = new JButton("start");
		private boolean go = false;

		public TictectoeStart() {
			setBounds(TictectoeFrame.W / 2 - 50, TictectoeFrame.H - 100, 100, 50);
			this.start.setBounds(0, 0, 100, 50);
			this.start.setVisible(true);
			this.start.addActionListener(this);
			add(this.start);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton temp = (JButton) e.getSource(); // 이벤트가 발생한 객체를 가져옴

			if (start == temp) {
				this.start.setBackground(Color.GREEN);
				this.go = true;
			}

		}

		public boolean run() {
			if (this.go) {
				return true;
			} else {
				return false;
			}
		}
	}
}

public class Ex_tictectoe {
	public static void main(String[] args) {
		TictectoeFrame frame = new TictectoeFrame();
	}
}
