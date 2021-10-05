package controller;

import java.util.ArrayList;

import models.Category;

public class ItemManager {
	public static ItemManager instance = new ItemManager();
	private ArrayList<Category> items = new ArrayList<>();

	@Override
	public String toString() {
		String data = "";
		int size = this.items.size();
		for (int i = 0; i < size; i++) {
			data += this.items.get(i).toString();
			if (i != size - 1) {
				data += "\n";
			}
		}
		return data;
	}

	public void itemManage() {
		String menu = "[1.전체 아이템] [2.아이템 추가] [3.아이템 삭제] [4.뒤로가기]";
		while (true) {
			System.out.println(menu);
			int sel = selMenu();

			if (sel == 1) {
				printItems();
			} else if (sel == 2) {
				addItem();
			} else if (sel == 3) {
				removeItem();
			} else if (sel == 4) {
				break;
			}
		}
	}

	public void categoryManage() {
		String menu = "[1.전체 카테고리] [2.카테고리 추가] [3.카테고리 삭제] [4.뒤로가기]";
		while (true) {
			System.out.println(menu);
			int sel = selMenu();

			if (sel == 1) {
				printCategory();
			} else if (sel == 2) {
				addCategory();
			} else if (sel == 3) {
				removeCategory();
			} else if (sel == 4) {
				break;
			}
		}
	}

	private void removeCategory() {
		printCategory();
		int size = this.categoryCnt();
		System.out.printf("[카테고리 삭제]카테고리 선택(1-%d) :", size);
		String temp = Shop.scan.next();
		int category = intCheck(temp) - 1;

		if (category >= 0 && category < size) {
			this.items.remove(category);
		}
	}

	public int categoryCnt() {
		return this.items.size();
	}

	private void addCategory() {
		int idx = this.categoryCnt();

		System.out.print("[카테고리 추가]카테고리 입력 :");
		String category = Shop.scan.next();

		int check = categoryIdx(category);
		if (check == -1) {
			this.items.add(new Category(category));
		} else {
			System.out.println("이미 존재하는 카테고리입니다.");
		}

	}

	private int categoryIdx(String category) {
		int size = this.categoryCnt();
		int check = -1;
		for (int i = 0; i < size; i++) {
			if (category.equals(this.items.get(i).getCategory())) {
				check = i;
			}
		}
		return check;
	}

	public void printCategory() {
		int size = this.categoryCnt();
		for (int i = 0; i < size; i++) {
			System.out.println("(" + (i + 1) + ")" + this.items.get(i).getCategory());
		}
	}

	public int printItems() {
		int size = this.categoryCnt();
		int idx = 1;
		for (int i = 0; i < size; i++) {
			int itemSize = this.items.get(i).getItemSize();
			for (int j = 0; j < itemSize; j++) {
				System.out.println(
						">>[" + idx + "]" + this.items.get(i).getItemName(j) + "/" + this.items.get(i).getItemPrice(j));
				idx++;
			}
		}
		return idx;
	}

	private void removeItem() {
		printCategory();
		int size = this.categoryCnt();
		System.out.printf("[아이템 삭제]카테고리 선택(1-%d) :", size);
		String temp = Shop.scan.next();
		int category = intCheck(temp) - 1;

		if (category >= 0 && category < size) {
			int itemSize = this.items.get(category).getItemSize();
			for (int i = 0; i < itemSize; i++) {
				System.out.println(">> (" + (i + 1) + ")" + this.items.get(category).getItemName(i) + "/"
						+ this.items.get(category).getItemPrice(i));
			}
			System.out.printf("[아이템 삭제]아이템 선택(1-%d) :", itemSize);
			temp = Shop.scan.next();
			int item = intCheck(temp) - 1;

			if (item >= 0 && item < itemSize) {
				this.items.get(category).removeItem(item);
			}
		}
	}

	private void addItem() {
		int size = this.categoryCnt();
		printCategory();
		System.out.printf("[아이템 추가]카테고리 선택(1-%d) :", size);
		String temp = Shop.scan.next();
		int category = intCheck(temp) - 1;

		if (category >= 0 && category < size) {
			System.out.print("[아이템 추가]아이템 입력 :");
			String item = Shop.scan.next();
			int check = itemIdx(category, item);
			if (check != -1) {
				System.out.println("이미 존재하는 아이템입니다.");
				return;
			}
			System.out.print("[아이템 추가]가격 입력 :");
			temp = Shop.scan.next();
			int price = intCheck(temp);
			if (price == -1) {
				System.out.println("다시 입력하세요");
				return;
			}

			this.items.get(category).addItem(item, price);
		}

	}

	public int intCheck(String temp) {
		int num = -1;
		try {
			num = Integer.parseInt(temp);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return num;
	}

	private int itemIdx(int category, String item) {
		int check = -1;
		int size = this.items.get(category).getItemSize();
		for (int i = 0; i < size; i++) {
			if (item.equals(this.items.get(category).getItemName(i))) {
				check = i;
			}
		}
		return check;
	}

	private int selMenu() {
		System.out.print("메뉴 선택 : ");
		String temp = Shop.scan.next();

		int sel = -1;
		try {
			sel = Integer.parseInt(temp);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return sel;
	}

	public void addItem(String[] temp) {
		int size = this.categoryCnt();
		int check = categoryIdx(temp[0]);
		if (check == -1) {
			this.items.add(new Category(temp[0]));
			if (temp.length > 1) {
				this.items.get(size).addItem(temp[1], Integer.parseInt(temp[2]));
			}
		} else {
			this.items.get(check).addItem(temp[1], Integer.parseInt(temp[2]));
		}

	}

	public void clear() {
		this.items = new ArrayList<>();
	}

	public String getItem(int category, int item) {
		return this.items.get(category).getItemName(item);
	}

	public int getPrice(int category, int item) {
		return this.items.get(category).getItemPrice(item);
	}

	public int itemSize(int category) {
		return this.items.get(category).getItemSize();
	}

	public void printCategory_Items(int category) {
		int itemSize = this.items.get(category).getItemSize();
		for (int j = 0; j < itemSize; j++) {
			System.out.println(">>[" + (j + 1) + "]" + this.items.get(category).getItemName(j) + "/"
					+ this.items.get(category).getItemPrice(j));

		}
	}

}
