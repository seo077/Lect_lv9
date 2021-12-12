package models;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

public class Frame extends JFrame implements Runnable{

	private static Map<String,MyUtill>panels = new HashMap<>();
	public static String curPage = "main";
	public static String nextPage ="";
	private MyUtill page ;
	
	private Thread th;
	
	public Frame() {
		super("그린 카페 키오스크");
		
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(620,800);
		
		setPanel();
		this.page = this.panels.get(this.curPage);
		this.add(page);
		
		this.th = new Thread(this);
		th.start();
		
		
		setVisible(true);
		setLocationRelativeTo(null);
		revalidate();
	}


	private void setPanel() {
		this.panels.put("manager", new ManagerPanel());
		this.panels.put("main", new MainPanel());
		this.panels.put("menu", new MenuPanel());
		//this.panels.put("pay", new PayPanel());
	}

	public static void resetPanel() {
		panels.clear();
		panels.put("manager", new ManagerPanel());
		panels.put("main", new MainPanel());
		panels.put("menu", new MenuPanel());
		
	}


	@Override
	public void run() {
		while(true) {
			this.nextPage = page.updateNextPage();
			if(this.nextPage != "" && this.curPage != this.nextPage) {
				page.resetNextPage();
				this.curPage = this.nextPage;
				if(nextPage == "manager") {
					ManagerPanel.setSales();
				}
				if(this.nextPage == "pay") {
					this.page = new PayPanel();
					PayPanel.setTotal();
				}else {
					this.page = this.panels.get(this.curPage);
					
				}
//				remove(page);
//				this.revalidate();
				//add(this.page); // = this.getContentPane().add(this.page); //= setContentPane(this.page) --> setter 을 활용해서 pane을 교체
				this.setContentPane(this.page);
				
				this.nextPage = "";
				this.revalidate();
			}
		}
	}
}
