package models;


public class Menu {
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Images getIm() {
		return im;
	}

	public void setIm(Images im) {
		this.im = im;
	}

	public Images getSimple_im() {
		return simple_im;
	}

	public void setSimple_im(Images simple_im) {
		this.simple_im = simple_im;
	}

	private String name;
	private int price;
	private Images im;
	private Images simple_im;
	
	public Menu(Images im, Images simple_im) {
		this.im = im;
		this.simple_im = simple_im;
	}

	public void setNamePrice(String name, int price) {
		this.name = name;
		this.price = price;
	}
}
