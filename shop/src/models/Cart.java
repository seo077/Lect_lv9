package models;

public class Cart {
	private String userId;
	private String userItem;
	private int price;

	public Cart(String userId, String userItem, int price) {
		this.userId = userId;
		this.userItem = userItem;
		this.price = price;
	}

	@Override
	public String toString() {
		String cartData = "";
		cartData += this.userId + "/";
		cartData += this.userItem + "/";
		cartData += this.price;
		return cartData;
	}

	public String getUserId() {
		return this.userId;
	}

	public String getUserItem() {
		return this.userItem;
	}

	public int getPrice() {
		return this.price;
	}
}
