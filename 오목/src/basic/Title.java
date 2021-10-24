package basic;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class Title extends JLabel{
	public Title() {
		setBounds(30,30,500,100);
		setText("¢½TicTacToe Game¢½");
		setFont(new Font("", Font.BOLD, 30));
		setForeground(Color.pink);
		setHorizontalAlignment(CENTER);
		setVisible(true);
	}
}
