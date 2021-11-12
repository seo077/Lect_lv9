package basic;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class JoinFrame extends JFrame {
	private JLabel id, pw, name;
	JTextField idField, pwField, nameField;
	JButton done = new JButton();

	public JoinFrame() {
		setLayout(null);
		setBounds(200, 200, 250, 200);

		setLabel();
		setField();
		setBtn();

		setVisible(true);
		revalidate();
	}

	private void setBtn() {
		this.done.setText("완료");
		this.done.setBounds(100, 100, 80, 20);
		this.done.setVisible(true);
		add(this.done);
	}

	private void setLabel() {
		this.id = new JLabel("id");
		this.id.setBounds(10, 20, 30, 20);
		add(this.id);

		this.pw = new JLabel("pw");
		this.pw.setBounds(10, 40, 30, 20);
		add(this.pw);

		this.name = new JLabel("name");
		this.name.setBounds(10, 60, 30, 20);
		add(this.name);
	}

	private void setField() {
		this.idField = new JTextField();
		this.idField.setBounds(50, 20, 150, 20);
		this.idField.setText("아이디를 입력하세요");
		add(this.idField);
		this.pwField = new JTextField();
		this.pwField.setBounds(50, 40, 150, 20);
		this.pwField.setText("비밀번호를 입력하세요");
		add(this.pwField);
		this.nameField = new JTextField();
		this.nameField.setBounds(50, 60, 150, 20);
		this.nameField.setText("이름를 입력하세요");
		add(this.nameField);
	}
}

class LoginFrame extends JFrame {
	private JLabel id, pw;
	JTextField idField, pwField;
	JButton done = new JButton();

	public LoginFrame() {
		setLayout(null);
		setBounds(200, 200, 250, 200);

		setLabel();
		setField();
		setBtn();

		setVisible(true);
		revalidate();
	}

	private void setBtn() {
		this.done.setText("로그인");
		this.done.setBounds(100, 100, 80, 20);
		this.done.setVisible(true);
		add(this.done);
	}

	private void setLabel() {
		this.id = new JLabel("id");
		this.id.setBounds(10, 20, 30, 20);
		add(this.id);

		this.pw = new JLabel("pw");
		this.pw.setBounds(10, 40, 30, 20);
		add(this.pw);

	}

	private void setField() {
		this.idField = new JTextField();
		this.idField.setBounds(50, 20, 150, 20);
		this.idField.setText("아이디를 입력하세요");
		add(this.idField);
		this.pwField = new JTextField();
		this.pwField.setBounds(50, 40, 150, 20);
		this.pwField.setText("비밀번호를 입력하세요");
		add(this.pwField);

	}
}

public class ExText extends JFrame implements ActionListener, KeyListener, MouseListener {

	JLabel dataField = new JLabel(String.format("<html>%s<br>%d</html>", "", 123));
	JButton login, join;

	JoinFrame joinFrame;
	LoginFrame loginFrame;

	JLabel text = new JLabel();
	JLabel text2 = new JLabel();

	private Vector<Vector<String>> users = new Vector<>();
	private int cnt = 0;
	private int log = -1;

	public ExText() {
		setLayout(null);
		setBounds(100, 100, 400, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setBtn();
		printUsers();
		printLogin();

		setFocusable(true);
		addKeyListener(this);

		setVisible(true);
		revalidate();

	}

	private void printLogin() {
		this.text2.setBounds(0, 0, 300, 50);
		this.text2.setForeground(Color.blue);
		if (log == -1) {
			this.text2.setText("로그인 중인 회원 없음...");
		} else {
			this.text2.setText(String.format("현재 로그인 중인 회원은 %s입니다.", this.users.get(log).get(0)));
		}
		this.text2.setVisible(true);
		add(this.text2);
	}

	private void printUsers() {
		String text = "<html>";
		if (this.cnt > 0) {
			for (int i = 0; i < this.cnt; i++) {
				text += this.users.get(i).get(0) + "/";
				text += this.users.get(i).get(1) + "/";
				text += this.users.get(i).get(2) + "<br>";
			}
		}
		text += "</html>";
		this.text.setBounds(10, 50, 300, 50);
		this.text.setText(text);
		this.text.setVisible(true);
		add(this.text);
	}

	private void setBtn() {
		this.join = new JButton();
		this.join.setText("join");
		this.join.setBounds(100, 100, 100, 100);
		this.join.setVisible(true);
		this.join.addActionListener(this);
		add(this.join);

		this.login = new JButton();
		this.login.setText("login");
		this.login.setBounds(220, 100, 100, 100);
		this.login.setVisible(true);
		this.login.addActionListener(this);
		add(this.login);
	}

	public static void main(String[] args) {
		new ExText();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.join) {
			this.joinFrame = new JoinFrame();
			this.joinFrame.idField.addMouseListener(this);

			this.joinFrame.pwField.addMouseListener(this);

			this.joinFrame.nameField.addMouseListener(this);
			this.joinFrame.done.addActionListener(this);
		}

		else if (e.getSource() == this.login) {
			this.loginFrame = new LoginFrame();
			this.loginFrame.idField.addMouseListener(this);
			this.loginFrame.pwField.addMouseListener(this);
			this.loginFrame.done.addActionListener(this);
		}

		else if (this.joinFrame != null && e.getSource() == this.joinFrame.done) {
			String id = this.joinFrame.idField.getText();
			String pw = this.joinFrame.pwField.getText();
			String name = this.joinFrame.nameField.getText();

			this.users.add(new Vector());
			this.users.get(this.cnt).add(id);
			this.users.get(this.cnt).add(pw);
			this.users.get(this.cnt).add(name);
			this.cnt++;
			this.joinFrame.dispose();
			this.printUsers();
		}

		else if (e.getSource() == this.loginFrame.done) {
			String id = this.loginFrame.idField.getText();
			String pw = this.loginFrame.pwField.getText();

			for (int i = 0; i < this.cnt; i++) {
				Vector temp = this.users.get(i);
				if (temp.get(0).equals(id) && temp.get(1).equals(pw)) {
					this.log = i;
					break;
				} else {
					this.log = -1;
				}
			}
			this.loginFrame.dispose();
			this.printLogin();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (this.joinFrame != null) {
			if (e.getSource() == this.joinFrame.idField) {
				this.joinFrame.idField.setText("");
			} else if (e.getSource() == this.joinFrame.pwField) {
				this.joinFrame.pwField.setText("");
			} else if (e.getSource() == this.joinFrame.nameField) {
				this.joinFrame.nameField.setText("");
			}
		}

		if (this.loginFrame != null) {
			if (e.getSource() == this.loginFrame.idField) {
				this.loginFrame.idField.setText("");
			} else if (e.getSource() == this.loginFrame.pwField) {
				this.loginFrame.pwField.setText("");
			}
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

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
