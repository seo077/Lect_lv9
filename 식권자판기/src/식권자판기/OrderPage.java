package 식권자판기;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

class Alert extends JFrame{
	JLabel alert = new JLabel();
	
	public Alert() {
		setLayout(null);
		setBounds(200, 200, 300, 200);
		setVisible(true);
		
		this.alert.setText("상품을 선택한 후 [구매하기]버튼을 누르세요");
		this.alert.setBounds(0, 0, 300, 200);
		this.alert.setForeground(Color.red);
		this.alert.setHorizontalAlignment(JLabel.CENTER);
		this.alert.setVisible(true);
		add(this.alert);
	}
}
public class OrderPage extends MyUtill {

	private String nextPage = "";

	private JButton kinds[];
	private JButton buy = new JButton();
	private JButton back = new JButton();

	private Menu[] menu = ManagerPage.getAllMenu();
	private Map<String, Integer> kind = ManagerPage.getKinds();
	private Object key[] = kind.keySet().toArray();

	private String selKind = "한식";
	private static ArrayList<MyMenu>myMenu = new ArrayList<>();
	private static JLabel total;

	private static JLabel cnt;
	
	public OrderPage() {
		setLayout(null);
		setBounds(0, 0, 700, 800);

		this.nextPage = "";
		addMouseListener(this);
		setBuy();
		setBack();
		setKinds();
		setTotalCnt();
	}

	public static ArrayList<MyMenu> getMyMenu() {
		return myMenu;
	}
	
	private void setTotalCnt() {
		this.total = new JLabel();
		this.cnt = new JLabel();
		
		this.total.setBounds(500, 670, 150, 50);
		this.total.setFont(new Font("", Font.BOLD, 20));
		this.cnt.setFont(new Font("", Font.BOLD, 20));
		this.cnt.setBounds(500, 700, 150, 50);
		this.total.setText(String.format("금액 : %d", MyMenu.total));
		this.cnt.setText(String.format("개수 : %d", MyMenu.totalCnt));
		
		add(this.total);
		add(this.cnt);
		
	}

	private void setKinds() {
		int size = this.kind.size();
		this.kinds = new JButton[size];

		for (int i = 0; i < size; i++) {
			this.kinds[i] = new JButton();
			this.kinds[i].setText(key[i] + "");

			if (this.selKind.equals(key[i] + "")) {
				this.kinds[i].setBounds(50, 50 + (105 * i), 100, 70);
			} else {
				this.kinds[i].setBounds(50, 50 + (105 * i), 70, 70);
			}

			this.kinds[i].addActionListener(this);
			this.kinds[i].setVisible(true);
			add(this.kinds[i]);
		}
	}

	private void setBack() {
		this.back.setText("처음으로");
		this.back.setBounds(550, 540, 100, 50);
		this.back.addActionListener(this);

		add(this.back);

	}

	private void setBuy() {
		this.buy.setText("구매하기");
		this.buy.setBounds(550, 600, 100, 50);
		this.buy.addActionListener(this);

		add(this.buy);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int idx = -1;
		for(int i=0;i<key.length;i++) {
			if(this.selKind.equals(key[i])) {
				idx = i;
			}
		}
		
		if (e.getSource() == this.back) {
			this.nextPage = "Intro";
			this.myMenu = new ArrayList<>();
			MyMenu.total = 0;
			MyMenu.totalCnt = 0;
			this.total.setText(String.format("금액 : %d", MyMenu.total));
			this.cnt.setText(String.format("개수 : %d", MyMenu.totalCnt));
			
		} else if (e.getSource() == this.buy) {
			if(MyMenu.total == 0) {
				//알람 + intro로 돌아감
				new Alert();
				this.nextPage = "Intro";
				this.myMenu = new ArrayList<>();
				MyMenu.total = 0;
				MyMenu.totalCnt = 0;
				this.total.setText(String.format("금액 : %d", MyMenu.total));
				this.cnt.setText(String.format("개수 : %d", MyMenu.totalCnt));
				
			}else {
				this.nextPage = "Pay";
			}
		} else if (e.getSource() == this.kinds[0]) {
			this.kinds[idx].setBounds(50, 50 + (105 * idx), 70, 70);
			this.selKind = key[0] + "";
			this.kinds[0].setBounds(50, 50 + (105 * 0), 100, 70);
		} else if (e.getSource() == this.kinds[1]) {
			this.kinds[idx].setBounds(50, 50 + (105 * idx), 70, 70);
			this.selKind = key[1] + "";
			this.kinds[1].setBounds(50, 50 + (105 * 1), 100, 70);
		} else if (e.getSource() == this.kinds[2]) {
			this.kinds[idx].setBounds(50, 50 + (105 * idx), 70, 70);
			this.selKind = key[2] + "";
			this.kinds[2].setBounds(50, 50 + (105 * 2), 100, 70);
		}

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setFont(new Font("", Font.BOLD, 20));
		g.drawString("전체 메뉴", 50, 30);

		int cnt = 0;
		int yy = 50;
		int xx = 0;
		for (int i = 0; i < this.menu.length; i++) {
			if (this.menu[i].getKind().equals(this.selKind)) {
				if (cnt != 0 && cnt % 3 == 0) {
					yy += 160;
					xx = 0;
				}
				ImageIcon image = new ImageIcon(this.menu[i].getIm());
				g.drawImage(image.getImage(), 200 + (xx * 130), yy, null);
				g.drawString(this.menu[i].getMenuName(), 200 + (xx * 130), yy + 112);
				g.drawString(this.menu[i].getPrice() + "", 200 + (xx * 130), yy + 132);
				Rect r = new Rect(190 + (xx * 130), yy-10, 120, 150);
				this.menu[i].setRect(r);
				g.drawRect(190 + (xx * 130), yy-10, 120, 150);
				xx++;
				cnt++;
			}
		}

		g.setColor(new Color(246, 215, 167));
		g.setFont(new Font("", Font.BOLD, 30));
		g.drawString("내가 선택한 메뉴", 10, 390);

		g.drawRect(0, 400, 700, 400);
		g.fillRect(0, 400, 700, 400);
		
		if(this.myMenu.size() != 0) {
			g.setColor(Color.black);
			g.setFont(new Font("", Font.PLAIN, 10));
			
			yy = 420;
			xx = 0;
			for(int i=0;i<this.myMenu.size();i++) {
				if(i != 0 && i%5==0) {
					yy += 140;
					xx = 0;
				}
				ImageIcon image = new ImageIcon(this.myMenu.get(i).getIm());
				g.drawImage(image.getImage(),(xx* 130), yy, null);
				g.drawString(this.myMenu.get(i).getMenuName(), (xx * 130), yy + 112);
				g.drawString(this.myMenu.get(i).getPrice() + "", (xx * 130), yy + 132);
				
				if(this.myMenu.get(i).getCnt() > 1) {
					g.setColor(Color.blue);
					g.drawString(String.format("%d개",this.myMenu.get(i).getCnt()), (xx * 130) +50, yy + 132);
					g.setColor(Color.black);
				}
				xx ++;
			}
		}
		repaint();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		for (int i = 0; i < this.menu.length; i++) {
			if (this.menu[i].getKind().equals(this.selKind)) {
				Rect temp = this.menu[i].getRect();
				if(x >= temp.getX() && x <= temp.getX()+temp.getWidth() && y >= temp.getY() && y <= temp.getY()+temp.getHeight()) {
					String menuName = this.menu[i].getMenuName();
					Image im = this.menu[i].getIm();
					int price = this.menu[i].getPrice();
					if(this.myMenu.size() == 0) {
						this.myMenu.add(new MyMenu(im,menuName,price,this.selKind));
					}else {
						int check = -1;
						for(int j=0;j<this.myMenu.size();j++) {
							if(menuName.equals(this.myMenu.get(j).getMenuName())) {
								check = j;
							}
						}
						if(check == -1) {
							this.myMenu.add(new MyMenu(im,menuName,price,this.selKind));
						}else {
							this.myMenu.get(check).addCnt(1);
						}
						
					}
					MyMenu.total += price;
					MyMenu.totalCnt ++;
					this.total.setText(String.format("금액 : %d", MyMenu.total));
					this.cnt.setText(String.format("개수 : %d", MyMenu.totalCnt));
				}
			}
		}
	}

	@Override
	public String nextPageCheck() {
		return this.nextPage;
	}

	@Override
	public void setNextPage(String str) {
		this.nextPage = str;
	}

	public static void reset() {
		myMenu = new ArrayList<>();
		MyMenu.total = 0;
		MyMenu.totalCnt = 0;
		total.setText(String.format("금액 : %d", MyMenu.total));
		cnt.setText(String.format("개수 : %d", MyMenu.totalCnt));
	}

}
