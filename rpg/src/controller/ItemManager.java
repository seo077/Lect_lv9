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
			System.out.printf("{%d} %s <ȿ�� : %s>\n", i + 1, temp.getKind(), temp.getEffect());
			int itemSize = items.get(i).itemsSize();
			for (int j = 0; j < itemSize; j++) {
				System.out.printf(" �� (%d) <�̸� : %s> <�ɷ� : %d> <���� : %d>\n", j + 1, temp.getItemName(j),
						temp.getItemPower(j), temp.getItemPrice(j));
			}
		}
	}

	public void categoryManage() {
		while (true) {
			System.out.println("[1.��ü ī�װ�] [2.ī�װ� �߰�] [3.ī�װ� ����] [0.�ڷ� ����]");
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
			System.out.println("[1.��ü ������] [2.������ �߰�] [3.������ ����] [0.�ڷ� ����]");
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
			System.out.printf("{%d} %s <ȿ�� : %s>\n", i + 1, temp.getKind(), temp.getEffect());
		}
	}

	private void addCategory() {
		System.out.print("���� : ");
		String kind = Rpg.scan.next();
		String eff[] = ItemCategory.effects;
		int size = eff.length;
		for(int i=0;i<size;i++) {
			System.out.printf("[%d] %s \n",i+1,eff[i]);
		}
		System.out.print("ȿ�� ���� : ");
		String temp = Rpg.scan.next();
		int sel = this.intCheck(temp)-1;
		if(sel >=0 && sel <size) {
			this.items.add(new ItemCategory(kind,sel));
		}
	}

	private void delCategory() {
		this.printCategory();
		int size = this.itemSize();
		System.out.print("������ ī�װ��� �Է��ϼ���: ");
		String temp = Rpg.scan.next();

		int sel = intCheck(temp) - 1;
		if (sel >= 0 && sel < size) {
			this.items.remove(sel);
		}
	}

	private void printItem() {
		int size = this.itemSize();
		int idx = 1;
		for (int i = 0; i < size; i++) {
			ItemCategory temp = this.items.get(i);
			int itemSize = items.get(i).itemsSize();
			for (int j = 0; j < itemSize; j++) {
				System.out.printf("(%d) <���� : %s> <�̸� : %s> <�ɷ� : %d> <���� : %d>\n", idx, temp.getItemKind(j),
						temp.getItemName(j), temp.getItemPower(j), temp.getItemPrice(j));
				idx++;
			}
		}
	}

	private void addItem() {
		int size = this.itemSize();
		printCategory();
		int sel = Rpg.intSel() - 1;

		if (sel >= 0 && sel < size) {
			System.out.print("������ �̸�: ");
			String name = Rpg.scan.next();
			while (true) {
				System.out.print("������ �ɷ�: ");
				String temp = Rpg.scan.next();
				int power = intCheck(temp);
				if (power == -1) {
					continue;
				}
				System.out.print("������ ����: ");
				temp = Rpg.scan.next();
				int price = intCheck(temp);
				if (price == -1) {
					continue;
				}
				this.items.get(sel).addItems(name, power, price);
				break;
			}
		}
	}

	public static int intCheck(String temp) {
		int sel = -1;
		try {
			sel = Integer.parseInt(temp);
		} catch (Exception e) {
			System.out.println("���ڸ� �Է��ϼ���");
		}
		return sel;
	}

	private void delItem() {
		this.printCategory();
		int size = this.itemSize();
		System.out.print("������ ī�װ��� �Է��ϼ���: ");
		String tmp = Rpg.scan.next();

		int sel = intCheck(tmp) - 1;
		if (sel >= 0 && sel < size) {
			ItemCategory temp = this.items.get(sel);
			int itemSize = temp.getItemSize();
			for (int j = 0; j < itemSize; j++) {
				System.out.printf("(%d) <�̸� : %s> <�ɷ� : %d> <���� : %d>\n", j + 1, temp.getItemName(j),
						temp.getItemPower(j), temp.getItemPrice(j));
			}
			System.out.print("������ �����۸� �Է��ϼ���: ");
			tmp = Rpg.scan.next();

			int selItem = intCheck(tmp) - 1;
			if (selItem >= 0 && selItem < size) {
				this.items.get(sel).removeItem(selItem);
			}
		}

	}

	@Override
	public String toString() {
		String data = "";
		int size = this.itemSize();
		for (int i = 0; i < size; i++) {
			data += this.items.get(i).getKind() + "/";
			data += this.items.get(i).getEffect();
			if (i != size - 1) {
				data += "\n";
			}
		}
		for (int i = 0; i < size; i++) {
			int itemSize = this.items.get(i).getItemSize();
			if (itemSize > 0) {
				data += "\n";
				for (int j = 0; j < itemSize; j++) {
					data += this.items.get(i).getItemKind(j) + "/";
					data += this.items.get(i).getItemName(j) + "/";
					data += this.items.get(i).getItemPower(j) + "/";
					data += this.items.get(i).getItemPrice(j);
				}
			}
		}
		return data;
	}

	public void setData(String[] temp) {
		int size = temp.length;
		if (size > 2) {
			// ������
			int itemsSize = this.itemSize();
			int check = -1;
			for (int j = 0; j < itemsSize; j++) {
				if (temp[0].equals(this.items.get(j).getKind())) {
					check = j;
				}
			}
			this.items.get(check).addItems(temp[1], Integer.parseInt(temp[2]), Integer.parseInt(temp[3]));
		} else {
			// ī�װ�
			this.items.add(new ItemCategory(temp[0], Integer.parseInt(temp[1])));
		}
	}

	

	public void clear() {
		this.items = new ArrayList<>();
	}
}
