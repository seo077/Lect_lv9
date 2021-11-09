package 식권자판기;

import java.awt.Image;

public class MyMenu {
	public static int total;
	public static int totalCnt;
	
	private Image im;
	private String menuName;
	private int price;
	private String kind;
	private int cnt;
	
	public MyMenu(Image im,String menuName, int price, String kind) {
		this.setIm(im);
		this.setMenuName(menuName);
		this.setPrice(price);
		this.setKind(kind);
		this.cnt++;
	}
	
	public Image getIm() {
		return im;
	}

	public void setIm(Image im) {
		this.im = im;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public void addCnt(int i) {
		this.cnt += i;
	}

	public int getCnt() {
		return this.cnt;
	}
}
