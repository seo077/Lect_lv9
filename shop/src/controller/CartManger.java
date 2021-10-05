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
			String menu = "[1.전체 장바구니] [2.장바구니 정렬] [0.뒤로가기]";
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
			System.out.println("['-1' 입력 시 종료]");
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
		System.out.println("----- 장바구니 -----");
		size = myItems.size();
		for (int i = 0; i < size; i++) {
			System.out.printf("[%d] %s(%d원) : %s개\n", i + 1, myItems.get(i).get_name(), myItems.get(i).get_price(),
					itemCnt.get(i));
		}
		System.out.println("총 구매 개수 : " + totalCnt + "개");
		System.out.println("총 구매 금액 : " + total + "원");
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
		String menu = "[1.내 장바구니] [2.삭제] [3.구입] [4.뒤로가기]";
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
		System.out.print("금액을 입력해주세요 :");
		String temp = Shop.scan.next();

		int money = im.intCheck(temp);
		if (money < totalPrice) {
			System.out.println("잔액이 부족합니다.");
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
		System.out.println("===== 영수증 =====");
		size = myItems.size();
		for (int i = 0; i < size; i++) {
			System.out.printf("[%d] %s(%d원) : %s개\n", i + 1, myItems.get(i).get_name(), myItems.get(i).get_price(),
					itemCnt.get(i));
		}
		System.out.println("총 구매 개수 : " + totalCnt + "개");
		System.out.println("총 구매 금액 : " + total + "원");
		System.out.println("----------------");
		System.out.println("지불 금액 : " + money + "원");
		System.out.println("잔       돈 : " + (money - total) + "원");
	}

	private void delMyItem(String userId) {
		ArrayList<Item> myitems = showMycart(userId);
		System.out.print("삭제할 아이템의 번호를 입력하세요: ");
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
