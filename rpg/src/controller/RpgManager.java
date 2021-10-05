package controller;

public class RpgManager {
	public static RpgManager instance = new RpgManager();
	
	private ItemManager im = ItemManager.instance;
	private GuildManager gm = GuildManager.instance;

	public void manage() {
		while (true) {
			printManageMenu();
			if (selManageMenu()) {
				break;
			}
		}
	}

	private void printManageMenu() {
		String menu = "��1.���� ��������2.������ ��������0.�ڷ� ���⡳";
		System.out.print(menu);
	}

	private boolean selManageMenu() {
		System.out.print("�޴� ���� :");
		String temp = Rpg.scan.next();
		
		int sel = Rpg.intCheck(temp);
		
		if(sel == 1) {
			manageGuildMember();
		}else if(sel == 2) {
			manageItem();
		}else if(sel == 0) {
			return true;
		}
		return false;
	}

	private void manageItem() {
		while(true) {
			String menu = "��1.������ �߰�����2.������ ��������0.�ڷ� ���⡳";
			System.out.println(menu);
			
			System.out.print("�޴� ���� :");
			String temp = Rpg.scan.next();
			
			int sel = Rpg.intCheck(temp);
			
			if(sel == 1) {
				im.addItem();
			}else if(sel == 2) {
				im.delItem();
			}else if(sel == 0) {
				break;
			}
			
		}
	}

	private void manageGuildMember() {
		while(true) {
			String menu = "��1.���� �߰�����2.���� ��������3.����(lv����)����0.�ڷ� ���⡳";
			System.out.println(menu);
			
			System.out.print("�޴� ���� :");
			String temp = Rpg.scan.next();
			
			int sel = Rpg.intCheck(temp);
			if(sel == 1) {
				gm.addMember();
			}else if(sel == 2) {
				gm.delMember();
			}else if(sel == 3) {
				gm.sort();
			}else if(sel == 0) {
				break;
			}
		}
	}

}
