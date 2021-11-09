package 식권자판기;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

public class Frame extends JFrame implements Runnable{

	private Map< String, MyUtill>pages = new HashMap<String, MyUtill>();
	private String curPage = "Intro";
	private String nextPage = "";
	private Thread th;
	
	public Frame() {
		super("식권자판기");
		setLayout(null);
		setBounds(50, 50, 700, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		ManagerPage.setting();
		
		setPages();
		th = new Thread(this);
		this.th.start();
		add(this.pages.get(curPage));
		
		setVisible(true);
		revalidate();
	}

	private void setPages() {
		this.pages.put("Intro",new IntroPage());
		this.pages.put("Order", new OrderPage());
		this.pages.put("Manager", new ManagerPage());
		this.pages.put("Pay", new PayPage());
		this.pages.put("Cash", new Cash());
		this.pages.put("Card", new Card());
	}

	@Override
	public void run() {
		while(true) {
			try {
				
				this.nextPage = this.pages.get(curPage).nextPageCheck();

				if(this.nextPage != "" && !this.curPage.equals(this.nextPage)) {
					this.pages.get(curPage).setNextPage("");
					remove(this.pages.get(curPage));
					this.curPage = this.nextPage;
					add(this.pages.get(curPage));
					this.revalidate();
					this.repaint();
					this.nextPage = "";
				}
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
