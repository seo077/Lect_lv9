package 식권자판기;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

public class Menu {
	private Image im;
	private String menuName;
	private int price;
	private int ticket;
	private String kind;
	private Rect rect;
	
	public Menu(Image im,String menuName, int price, int ticket, String kind) {
		this.setIm(im);
		this.menuName = menuName;
		this.setPrice(price);
		this.setTicket(ticket);
		this.kind = kind;
	}

	public String getMenuName() {
		return this.menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getKind() {
		return this.kind;
	}

	public Image getIm() {
		return im;
	}

	public void setIm(Image im) {
		this.im = im;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getTicket() {
		return ticket;
	}

	public void setTicket(int ticket) {
		this.ticket = ticket;
	}

	public Rect getRect() {
		return rect;
	}

	public void setRect(Rect rect) {
		this.rect = rect;
	}
}
