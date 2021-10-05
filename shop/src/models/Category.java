package models;

import java.util.ArrayList;

public class Category {

	private String category;
	private ArrayList<Item>items;
	
	
	public Category(String category) {
		this.category = category;
		this.items = new ArrayList<>();
	}
	
	@Override
	public String toString() {
		String data = "";
		int size = this.items.size();
		if(size == 0) {
			data+=this.category+"\n";
		}else {
			for(int i=0;i<size;i++) {
				data+=this.category+"/";
				data+=this.items.get(i).get_name()+"/";
				data+=this.items.get(i).get_price()+"\n";
			}
		}
		data = data.substring(0, data.length()-1);
		return data;
	}

	public String getCategory() {
		return this.category;
	}

	public void addItem(String item, int price) {
		this.items.add(new Item(item,price));
	}

	public int getItemSize() {
		return this.items.size();
	}

	public String getItemName(int i) {
		return this.items.get(i).get_name();
	}
	public int getItemPrice(int i) {
		return this.items.get(i).get_price();
	}

	public void removeItem(int item) {
		this.items.remove(item);
	}
}
