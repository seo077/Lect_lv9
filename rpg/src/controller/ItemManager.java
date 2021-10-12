package controller;

import java.util.ArrayList;

import models.Inventory;
import models.Item;
import models.ItemCategory;

public class ItemManager {
	public static ItemManager instance = new ItemManager();

	private CharacterManager cm = CharacterManager.instance;

	private ArrayList<ItemCategory> items = new ArrayList<>();
	private ArrayList<Inventory> myItems = new ArrayList<>();

	public void shop() {
		while (true) {
			System.out.println("내 돈 : " + Rpg.myMoney + "원");
			int size = this.itemSize();
			printCategory();
			System.out.println("['0'입력 시 뒤로 가기]");
			int sel = Rpg.intSel() - 1;

			if (sel == -1) {
				break;
			}
			if (sel >= 0 && sel < size) {
				ItemCategory temp = this.items.get(sel);
				int itemSize = temp.getItemSize();
				for (int j = 0; j < itemSize; j++) {
					System.out.printf("(%d) <이름 : %s> <능력 : %d> <가격: %d>\n", j + 1, temp.getItemName(j),
							temp.getItemPower(j), temp.getItemPrice(j));
				}
				System.out.print("구매할 아이템 입력: ");
				String tmp = Rpg.scan.next();

				int selItem = intCheck(tmp) - 1;
				if (selItem >= 0 && selItem < size) {
					ItemCategory tt = this.items.get(sel);
					int price = tt.getItemPrice(selItem);
					if (Rpg.myMoney - price >= 0) {
						String kind = tt.getKind();
						String name = tt.getItemName(selItem);
						if (!dupCheck(kind, name)) {
							int effect = tt.getEffectNum();
							int power = tt.getItemPower(selItem);
							this.myItems.add(new Inventory(kind, effect, name, power, price));
						}
						Rpg.myMoney -= price;
					} else {
						System.out.println("잔액이 부족합니다.");
					}
				}
			}
		}

	}

	private boolean dupCheck(String kind, String name) {
		int size = this.myItems.size();
		int check = -1;
		for (int i = 0; i < size; i++) {
			if (kind.equals(this.myItems.get(i).getKind()) && name.equals(this.myItems.get(i).getName())) {
				check = i;
			}
		}

		if (check != -1) {
			this.myItems.get(check).addCnt(1);
		} else {
			return false;
		}
		return true;
	}

	private void printMyItems() {
		System.out.println("------- INVENTORY -------");
		int size = this.myItems.size();
		for (int i = 0; i < size; i++) {
			Inventory t = this.myItems.get(i);
			System.out.printf("(%d) <종류 : %s> <효과 : %s> <이름 : %s> <능력 : %d> <개수 : %d> <사용 가능 : %b>\n", i + 1,
					t.getKind(), ItemCategory.effects[t.getEffect()], t.getName(), t.getPower(), t.getCnt(),
					t.getAvailable());
		}
		System.out.println("-------------------------");
	}

	private int itemSize() {
		return this.items.size();
	}

	public void printAllItem() {
		int size = this.itemSize();
		for (int i = 0; i < size; i++) {
			ItemCategory temp = this.items.get(i);
			System.out.printf("{%d} %s <효과 : %s>\n", i + 1, temp.getKind(), temp.getEffect());
			int itemSize = items.get(i).itemsSize();
			for (int j = 0; j < itemSize; j++) {
				System.out.printf(" → (%d) <이름 : %s> <능력 : %d> <가격 : %d>\n", j + 1, temp.getItemName(j),
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
		System.out.print("종류: ");
		String kind = Rpg.scan.next();
		String eff[] = ItemCategory.effects;
		int size = eff.length;
		for (int i = 0; i < size; i++) {
			System.out.printf("[%d] %s \n", i + 1, eff[i]);
		}
		System.out.print("효과 선택 : ");
		String temp = Rpg.scan.next();
		int sel = this.intCheck(temp) - 1;
		if (sel >= 0 && sel < size) {
			this.items.add(new ItemCategory(kind, sel));
		}
	}

	private void delCategory() {
		this.printCategory();
		int size = this.itemSize();
		System.out.print("삭제할 카테고리를 입력하세요: ");
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
				System.out.printf("(%d) <종류 : %s> <이름 : %s> <능력: %d> <가격 : %d>\n", idx, temp.getItemKind(j),
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
			System.out.print("아이템 이름: ");
			String name = Rpg.scan.next();
			while (true) {
				System.out.print("아이템 능력: ");
				String temp = Rpg.scan.next();
				int power = intCheck(temp);
				if (power == -1) {
					continue;
				}
				System.out.print("아이템 가격: ");
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
			System.out.println("숫자를 입력하세요");
		}
		return sel;
	}

	private void delItem() {
		this.printCategory();
		int size = this.itemSize();
		System.out.print("삭제할 카테고리 입력: ");
		String tmp = Rpg.scan.next();

		int sel = intCheck(tmp) - 1;
		if (sel >= 0 && sel < size) {
			ItemCategory temp = this.items.get(sel);
			int itemSize = temp.getItemSize();
			for (int j = 0; j < itemSize; j++) {
				System.out.printf("(%d) <이름 : %s> <능력 : %d> <가격: %d>\n", j + 1, temp.getItemName(j),
						temp.getItemPower(j), temp.getItemPrice(j));
			}
			System.out.print("삭제할 아이템 입력: ");
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
			data += this.items.get(i).getEffectNum();
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
					if (j != itemSize - 1) {
						data += "\n";
					}
				}
			}
		}
		return data;
	}

	public void setData(String[] temp) {
		int size = temp.length;
		if (size > 3) {
			// 아이템
			int itemsSize = this.itemSize();
			int check = -1;
			for (int j = 0; j < itemsSize; j++) {
				if (temp[0].equals(this.items.get(j).getKind())) {
					check = j;
				}
			}
			this.items.get(check).addItems(temp[1], Integer.parseInt(temp[2]), Integer.parseInt(temp[3]));
		} else {
			// 카테고리
			this.items.add(new ItemCategory(temp[0], Integer.parseInt(temp[1])));
		}
	}

	public void clear() {
		this.items = new ArrayList<>();
	}

	public void inventory() {
		while (true) {
			System.out.println("[1.내 아이템] [2. 착용] [3. 판매] [0.뒤로 가기]");
			int sel = Rpg.intSel();

			if (sel == 1) {
				printMyItems();
			} else if (sel == 2) {
				equipItem();
			} else if (sel == 3) {
				sellItem();
			} else if (sel == 0) {
				break;
			}

		}
	}

	private void equipItem() {
		int chasize = cm.getCharacterSize();
		cm.printMyMembers();
		System.out.println("멤버 선택 : ");
		String idx = Rpg.scan.next();
		int chasel = ItemManager.intCheck(idx) - 1;
		if (chasel >= 0 && chasel < chasize) {
			int itemsize = this.myItems.size();
			printMyItems();
			System.out.print("착용할 아이템 입력: ");
			String tmp = Rpg.scan.next();

			int itemsel = intCheck(tmp) - 1;
			if (itemsel >= 0 && itemsel < itemsize) {
				if (!this.myItems.get(itemsel).getAvailable()) {
					System.out.println("이미 착용 중인 아이템입니다.");
					return;
				}
				String name = cm.getName(chasel);
				System.out.printf("<이름 : %s > %s  +%d\n", name,
						ItemCategory.effects[this.myItems.get(itemsel).getEffect()],
						this.myItems.get(itemsel).getPower());
				cm.addItem(chasel, this.myItems.get(itemsel));
				this.myItems.get(itemsel).addCnt(-1);
				if (this.myItems.get(itemsel).getCnt() == 0) {
					this.myItems.get(itemsel).setAvailable(false);
				}
			}
		}
	}

	private void sellItem() {
		printMyItems();
		int size = this.myItems.size();
		System.out.print("판매할 아이템 입력: ");
		String tmp = Rpg.scan.next();

		int sel = intCheck(tmp) - 1;
		if (sel >= 0 && sel < size) {
			if (!this.myItems.get(sel).getAvailable()) {
				System.out.println("착용하고 있는 물건은 판매가 불가능합니다.");
				return;
			}

			this.myItems.get(sel).addCnt(-1);
			Rpg.myMoney += this.myItems.get(sel).getPrice();
			if (this.myItems.get(sel).getCnt() == 0) {
				this.myItems.remove(sel);
			}
			System.out.println("판매 완료!");
		}
	}

	public void inventoryClear() {
		Rpg.myMoney = 50000;
		this.myItems = new ArrayList<>();
	}

	public String inventoryToString() {
		String data = "";
		int size = this.myItems.size();
		for (int i = 0; i < size; i++) {
			data += this.myItems.get(i).getKind() + "/";
			data += this.myItems.get(i).getEffect() + "/";
			data += this.myItems.get(i).getName() + "/";
			data += this.myItems.get(i).getPower() + "/";
			data += this.myItems.get(i).getPrice() + "/";
			data += this.myItems.get(i).getCnt() + "/";
			data += this.myItems.get(i).getAvailable();
			if (i != size - 1) {
				data += "\n";
			}
		}
		return data;
	}

	public void setInventory(String[] temp) {
		String kind = temp[0];
		int effect = Integer.parseInt(temp[1]);
		String name = temp[2];
		int power = Integer.parseInt(temp[3]);
		int price = Integer.parseInt(temp[4]);
		Rpg.myMoney -= price;
		int cnt = Integer.parseInt(temp[5]);
		boolean b = Boolean.parseBoolean(temp[6]);
		this.myItems.add(new Inventory(kind, effect, name, power, price, cnt, b));
	}

	public void addItems(ArrayList<Inventory> items) {
		int itemsSize = items.size();
		int size = this.myItems.size();
		for (int j = 0; j < itemsSize; j++) {
			int check = -1;
			for (int i = 0; i < size; i++) {
				if (items.get(j).getName().equals(this.myItems.get(i).getName())) {
					check = i;
				}
			}
			this.myItems.get(check).addCnt(1);
			if(!this.myItems.get(check).getAvailable()) {
				this.myItems.get(check).setAvailable(true);
			}
		}
	}
}
