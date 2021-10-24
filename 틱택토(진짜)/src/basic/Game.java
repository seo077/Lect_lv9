package basic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Game {
	public static void main(String[] args) {
		MainFrame mf = new MainFrame();
	}
}

class MainFrame extends JFrame implements ActionListener,Runnable {
	private Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	private int width = screen.width;
	private int height = screen.height;
	public static final int W = 600;
	private static final int H = 600;

	private JButton bt_start = new JButton();
	private JButton bt_reset = new JButton();

	private GameTitle title = new GameTitle();
	private GamePanel gp;
	Thread th;
	
	public MainFrame() {
		super("틱택토 게임");
		add(title);
		init();
		defaultStart();
		setLayout(null);
		setBounds(width / 2 - W / 2, height / 2 - H / 2, W, H);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	

	private void init() {
		// 프레임에 표시할 게임 시작 및 리셋 버튼
		bt_start.setBounds(100, 520, 100, 30);
		bt_start.setText("start");
		bt_start.setFont(new Font("Default", Font.BOLD, 20));
		bt_start.addActionListener(this);
		add(bt_start);

		bt_reset.setBounds(400, 520, 100, 30);
		bt_reset.setText("reset");
		bt_reset.setFont(new Font("Default", Font.BOLD, 20));
		bt_reset.addActionListener(this);
		add(bt_reset);

		th = new Thread(this);
		th.start();
		
		// 게임 화면용 패널
		gp = new GamePanel();
		add(gp);
		
	}
	
	private void defaultStart() {
		gp.gameStart(false);
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bt_start) {
			gp.gameStart(true);

		} else if (e.getSource() == bt_reset) {
			gp.gameStart(false);

		}

	}

	@Override
	public void run() {
		while(true) {
			try {
				repaint();
				Thread.sleep(15);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}