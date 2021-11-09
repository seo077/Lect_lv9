package 경마;

import javax.swing.JFrame;

public class Frame extends JFrame{
	
	private Panel p = new Panel();
	
	public Frame() {
		super("경마게임");
		setLayout(null);
		setBounds(100,100,700,700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		add(p);
		
		setVisible(true);
		revalidate();
	}
}
