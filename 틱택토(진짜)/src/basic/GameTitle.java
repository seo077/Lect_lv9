package basic;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class GameTitle extends JLabel{
	public GameTitle() {
		setBounds(35,30,500,100);
		setText("��TicTacToe Game��");
		setFont(new Font("", Font.BOLD, 30));
		setForeground(Color.pink);
		setHorizontalAlignment(CENTER);
		setVisible(true);
	}
}
