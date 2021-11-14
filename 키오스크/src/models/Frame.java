package models;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame extends JFrame implements Runnable{

	private Map<String,MyUtill>panels = new HashMap<>();
	private String curPage = "main";
	private String nextPage ="";
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
		revalidate();
		setLocationRelativeTo(null);
	}


	private void setPanel() {
		this.panels.put("main", new MainPanel());
		this.panels.put("menu", new MenuPanel());
		this.panels.put("pay", new PayPanel());
	}



	@Override
	public void run() {
		while(true) {
			this.nextPage = page.updateNextPage();
			if(this.nextPage != "" && this.curPage != this.nextPage) {
				if(nextPage == "pay") {
					PayPanel.setTotal();
				}
				page.resetNextPage();
				remove(page);
				this.revalidate();
				this.curPage = this.nextPage;
				this.page = this.panels.get(this.curPage);
				add(this.page);
				this.nextPage = "";
				this.repaint();
			}
		}
	}
}
