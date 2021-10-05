package controller;

import java.util.ArrayList;

import models.Cart;
import models.Item;

public class CartManger {
	public static CartManger instance = new CartManger();

	private ItemManager im = ItemManager.instance;

	private ArrayList<Cart> carts = new ArrayList<>();

	@Override
	public String toString() {
		String data = "";
		int size = this.carts.size();
		for (int i = 0; i < size; i++) {
			data += this.carts.get(i).toString();
			if (i != size - 1) {
				data += "\n";
			}
		}
		return data;
	}

	public void clear() {
		this.carts = new ArrayList<>();
	}

	public void cartManage() {
		while (true) {
			String menu = "[1.��ü ��ٱ���] [2.��ٱ��� ����] [0.�ڷΰ���]";
			System.out.println(menu);
			String temp = Shop.scan.next();
			int sel = im.intCheck(temp);

			if (sel == 1) {
				System.out.println(this.toString());
			} else if (sel == 2) {
				sortCart();
			} else if (sel == 0) {
				break;
			}
		}
	}

	private void sortCart() {
		int size = this.carts.size();
		for (int i = 0; i < size; i++) {
			String first = this.carts.get(i).getUserId();
			int firstIdx = i;
			for (int j = i; j < size; j++) {
				if (first.compareTo(this.carts.get(j).getUserId()) > 0) {
					first = this.carts.get(j).getUserId();
					firstIdx = j;
				}
			}

			Cart temp = this.carts.get(i);
			this.carts.set(i, this.carts.get(firstIdx));
			this.carts.set(firstIdx, temp);
		}
	}

	public void shopping(String userId) {
		while (true) {
			im.printCategory();
			System.out.println("['-1' �Է� �� ����]");
			String temp = Shop.scan.next();
			int ca = im.intCheck(temp) - 1;
			int caSize = im.categoryCnt();
			if (ca == -2) {
				break;
			}
			if (ca >= 0 && ca < caSize) {
				im.printCategory_Items(ca);
				temp = Shop.scan.next();
				int item = im.intCheck(temp) - 1;
				int itemSize = im.itemSize(ca);

				if (item >= 0 && item < itemSize) {
					String userItem = this.im.getItem(ca, item);
					int price = this.im.getPrice(ca, item);
					this.carts.add(new Cart(userId, userItem, price));
				}
			}
		}
	}

	public ArrayList<Item> showMycart(String userId) {
		ArrayList<Item> myItems = new ArrayList<>();
		ArrayList<Integer> itemCnt = new ArrayList<>();

		int size = this.carts.size();
		int total = 0;
		int totalCnt = 0;
		for (int i = 0; i < size; i++) {
			if (this.carts.get(i).getUserId().equals(userId)) {
				int check = -1;
				int itemSize = myItems.size();
				for (int j = 0; j < itemSize; j++) {
					if (myItems.get(j).get_name().equals(this.carts.get(i).getUserItem())) {
						check = j;
					}
				}
				if (check == -1) {
					myItems.add(new Item(this.carts.get(i).getUserItem(), this.carts.get(i).getPrice()));
					itemCnt.add(1);
				} else {
					int num = itemCnt.get(check) + 1;
					itemCnt.set(check, num);
				}

				totalCnt++;
				total += this.carts.get(i).getPrice();
			}
		}
		System.out.println("----- ��ٱ��� -----");
		size = myItems.size();
		for (int i = 0; i < size; i++) {
			System.out.printf("[%d] %s(%d��) : %s��\n", i + 1, myItems.get(i).get_name(), myItems.get(i).get_price(),
					itemCnt.get(i));
		}
		System.out.println("�� ���� ���� : " + totalCnt + "��");
		System.out.println("�� ���� �ݾ� : " + total + "��");
		System.out.println("----------------");

		return myItems;
	}

	public int userTotalPrice(String userId) {
		int size = this.carts.size();
		int total = 0;
		for (int i = 0; i < size; i++) {
			if (this.carts.get(i).getUserId().equals(userId)) {
				total += this.carts.get(i).getPrice();
			}
		}
		return total;
	}

	public void clearUserCart(String userId) {
		ArrayList<Cart> temp = new ArrayList<>();
		int size = this.carts.size();
		for (int i = 0; i < size; i++) {
			if (!this.carts.get(i).getUserId().equals(userId)) {
				temp.add(this.carts.get(i));
			}
		}
		this.carts = temp;
	}

	public void addCart(String[] temp) {
		this.carts.add(new Cart(temp[0], temp[1], Integer.parseInt(temp[2])));
	}

	public void cart(String userId) {
		String menu = "[1.�� ��ٱ���] [2.����] [3.����] [4.�ڷΰ���]";
		while (true) {
			System.out.println(menu);
			String temp = Shop.scan.next();

			int sel = im.intCheck(temp);

			if (sel == 1) {
				showMycart(userId);
			} else if (sel == 2) {
				delMyItem(userId);
			} else if (sel == 3) {
				buy(userId);
			} else if (sel == 4) {
				break;
			}
		}
	}

	private void buy(String userId) {
		showMycart(userId);
		int totalPrice = this.userTotalPrice(userId);
		System.out.print("�ݾ��� �Է����ּ��� :");
		String temp = Shop.scan.next();

		int money = im.intCheck(temp);
		if (money < totalPrice) {
			System.out.println("�ܾ��� �����մϴ�.");
		} else {
			printReceipt(userId, money);
			clearUserCart(userId);
		}
	}

	private void printReceipt(String userId, int money) {

		ArrayList<Item> myItems = new ArrayList<>();
		ArrayList<Integer> itemCnt = new ArrayList<>();

		int size = this.carts.size();
		int total = 0;
		int totalCnt = 0;
		for (int i = 0; i < size; i++) {
			if (this.carts.get(i).getUserId().equals(userId)) {
				int check = -1;
				int itemSize = myItems.size();
				for (int j = 0; j < itemSize; j++) {
					if (myItems.get(j).get_name().equals(this.carts.get(i).getUserItem())) {
						check = j;
					}
				}
				if (check == -1) {
					myItems.add(new Item(this.carts.get(i).getUserItem(), this.carts.get(i).getPrice()));
					itemCnt.add(1);
				} else {
					int num = itemCnt.get(check) + 1;
					itemCnt.set(check, num);
				}

				totalCnt++;
				total += this.carts.get(i).getPrice();
			}
		}
		System.out.println("===== ������ =====");
		size = myItems.size();
		for (int i = 0; i < size; i++) {
			System.out.printf("[%d] %s(%d��) : %s��\n", i + 1, myItems.get(i).get_name(), myItems.get(i).get_price(),
					itemCnt.get(i));
		}
		System.out.println("�� ���� ���� : " + totalCnt + "��");
		System.out.println("�� ���� �ݾ� : " + total + "��");
		System.out.println("----------------");
		System.out.println("���� �ݾ� : " + money + "��");
		System.out.println("��       �� : " + (money - total) + "��");
	}

	private void delMyItem(String userId) {
		ArrayList<Item> myitems = showMycart(userId);
		System.out.print("������ �������� ��ȣ�� �Է��ϼ���: ");
		String temp = Shop.scan.next();

		int sel = im.intCheck(temp) - 1;

		String delItem = myitems.get(sel).get_name();

		int size = this.carts.size();
		for (int i = 0; i < size; i++) {
			if (delItem.equals(this.carts.get(i).getUserItem()) && userId.equals(this.carts.get(i).getUserId())) {
				this.carts.remove(i);
				break;
			}
		}
	}

}
