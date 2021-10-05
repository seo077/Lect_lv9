package controller;

import java.util.ArrayList;

import models.Item;
import models.ItemCategory;

public class ItemManager {
	public static ItemManager instance = new ItemManager();

	private ArrayList<ItemCategory> items = new ArrayList<>();

	public void shop() {
		// TODO Auto-generated method stub

	}

	private int itemSize() {
		return this.items.size();
	}

	public void printAllItem() {
		int size = this.itemSize();
		for (int i = 0; i < size; i++) {
			ItemCategory temp = this.items.get(i);
			System.out.printf("{%d} %s <효과 : %d>\n", i + 1, temp.getKind(), temp.getEffect());
			int itemSize = items.get(i).itemsSize();
			for (int j = 0; j < itemSize; j++) {
				System.out.printf("(%d) <이름 : %s> <능력 : %d> <가격 : %d>\n", j + 1, temp.getItemName(j),
						temp.getItemPower(j), temp.getItemPrice(j));
			}
		}
	}


	public void categoryManage() {
		while (true) {
			System.out.println("[1.전체 카테고리] [2.카테고리 추가] [3.카테고리 삭제] [0.뒤로 가기]");
			int sel = Rpg.intSel();

			if (sel == 1) {
				printCategory();
			} else if (sel == 2) {
				addCategory();
			} else if (sel == 3) {
				delCategory();
			} else if (sel == 0) {
				break;
			}
		}
	}


	public void itemManage() {
		while (true) {
			System.out.println("[1.전체 아이템] [2.아이템 추가] [3.아이템 삭제] [0.뒤로 가기]");
			int sel = Rpg.intSel();

			if (sel == 1) {
				printItem();
			} else if (sel == 2) {
				addItem();
			} else if (sel == 3) {
				delItem();
			} else if (sel == 0) {
				break;
			}
		}
	}

	private void printCategory() {
		int size = this.itemSize();
		for (int i = 0; i < size; i++) {
			ItemCategory temp = this.items.get(i);
			System.out.printf("{%d} %s <효과 : %s>\n", i + 1, temp.getKind(), temp.getEffect());
		}
	}
	
	private void addCategory() {
		System.out.print("종류 : ");
		String kind = Rpg.scan.next();
		System.out.print("효과 : ");
		String effect = Rpg.scan.next();
		
		this.items.add(new ItemCategory(kind, effect));
	}
	
	private void delCategory() {
		// TODO Auto-generated method stub
		
	}
	
	
	private void printItem() {
		int size = this.itemSize();
		int idx = 1;
		for (int i = 0; i < size; i++) {
			ItemCategory temp = this.items.get(i);
			int itemSize = items.get(i).itemsSize();
			for (int j = 0; j < itemSize; j++) {
				System.out.printf("(%d) <이름 : %s> <능력 : %d> <가격 : %d>\n", idx, temp.getItemName(j),
						temp.getItemPower(j), temp.getItemPrice(j));
				idx++;
			}
		}
	}
	
	private void addItem() {
		int size = this.itemSize();
		printCategory();
		int sel = Rpg.intSel()-1;
		
		if(sel >=0 && sel <size) {
			while(true) {
				System.out.print("아이템 이름: ");
				String name = Rpg.scan.next();
				System.out.print("아이템 능력: ");
				String temp = Rpg.scan.next();
				int power = intCheck(temp);
				if(power == -1) {
					continue;
				}
				System.out.print("아이템 가격: ");
				temp = Rpg.scan.next();
				int price = intCheck(temp);
				if(price == -1) {
					continue;
				}
				this.items.get(sel).addItems(name,power,price);
				break;
			}
		}
	}
	
	private int intCheck(String temp) {
		int sel = -1;
		try {
			sel = Integer.parseInt(temp);
		} catch (Exception e) {
			System.out.println("숫자를 입력하세요");
		}
		return sel;
	}

	private void delItem() {
		// TODO Auto-generated method stub
		
	}
}
