package �ı����Ǳ�;

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
		kinds.put("�ѽ�", 0);
		kinds.put("�߽�", 1);
		kinds.put("���", 2);
		
		Image im = new ImageIcon("image/�Ƿη�.jpg").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		menu.add(new Menu(im, "��ġ�", 5000, 5,"�ѽ�"));
		im = new ImageIcon("image/�Ƿη�.jpg").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		menu.add(new Menu(im, "�����", 5000, 6,"�ѽ�"));
		im = new ImageIcon("image/�Ƿη�.jpg").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		menu.add(new Menu(im, "��ġ������", 5000, 6,"�ѽ�"));
		im = new ImageIcon("image/�Ƿη�.jpg").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		menu.add(new Menu(im, "�����", 5000, 6,"�ѽ�"));
		im = new ImageIcon("image/�Ƿη�.jpg").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		menu.add(new Menu(im, "�ᳪ������", 5000, 6,"�ѽ�"));
		
		im = new ImageIcon("image/�Ƿη�.jpg").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		menu.add(new Menu(im, "¥���", 5000, 6,"�߽�"));
		im = new ImageIcon("image/�Ƿη�.jpg").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		menu.add(new Menu(im, "«��", 6000, 6,"�߽�"));
		
		im = new ImageIcon("image/�Ƿη�.jpg").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		menu.add(new Menu(im, "������ũ", 50000, 6,"���"));
		
	}
	
	public int menuSize() {
		return this.menu.size();
	}

	@Override
	public void setNextPage(String str) {
		this.nextPage = str;
	}
}
