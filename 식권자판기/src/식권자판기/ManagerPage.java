package ½Ä±ÇÀÚÆÇ±â;

import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ManagerPage extends MyUtill {

	private JButton back = new JButton();
	
	private static Map<String, Integer>kinds = new HashMap<String, Integer>();
	private static ArrayList<Menu>menu = new ArrayList<>();
	private String nextPage = "";
	
	@Override
	public String nextPageCheck() {
		return this.nextPage;
	}
	
	public static Map getKinds() {
		return kinds;
	}
	
	public static Menu[] getAllMenu() {
		int size = menu.size();
		Menu[] menus = new Menu[size];
		for(int i=0;i<size;i++) {
			menus[i] =menu.get(i);
		}
		return menus;
	}
	
	public static void setting() {
		kinds.put("ÇÑ½Ä", 0);
		kinds.put("Áß½Ä", 1);
		kinds.put("¾ç½Ä", 2);
		
		Image im = new ImageIcon("image/»Ç·Î·Î.jpg").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		menu.add(new Menu(im, "±èÄ¡Âî°³", 5000, 5,"ÇÑ½Ä"));
		im = new ImageIcon("image/»Ç·Î·Î.jpg").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		menu.add(new Menu(im, "µÈÀåÂî°³", 5000, 6,"ÇÑ½Ä"));
		im = new ImageIcon("image/»Ç·Î·Î.jpg").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		menu.add(new Menu(im, "±èÄ¡ººÀ½¹ä", 5000, 6,"ÇÑ½Ä"));
		im = new ImageIcon("image/»Ç·Î·Î.jpg").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		menu.add(new Menu(im, "ºñºö¹ä", 5000, 6,"ÇÑ½Ä"));
		im = new ImageIcon("image/»Ç·Î·Î.jpg").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		menu.add(new Menu(im, "Äá³ª¹°±¹¹ä", 5000, 6,"ÇÑ½Ä"));
		
		im = new ImageIcon("image/»Ç·Î·Î.jpg").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		menu.add(new Menu(im, "Â¥Àå¸é", 5000, 6,"Áß½Ä"));
		im = new ImageIcon("image/»Ç·Î·Î.jpg").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		menu.add(new Menu(im, "Â«»Í", 6000, 6,"Áß½Ä"));
		
		im = new ImageIcon("image/»Ç·Î·Î.jpg").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		menu.add(new Menu(im, "½ºÅ×ÀÌÅ©", 50000, 6,"¾ç½Ä"));
		
	}
	
	public int menuSize() {
		return this.menu.size();
	}

	@Override
	public void setNextPage(String str) {
		this.nextPage = str;
	}
}
