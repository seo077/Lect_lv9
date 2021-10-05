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
		String menu = "〔1.길드원 관리〕〔2.아이템 관리〕〔0.뒤로 가기〕";
		System.out.print(menu);
	}

	private boolean selManageMenu() {
		int sel = Rpg.intSel();
		
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
			String menu = "〔1.카테고리 관리〕〔2.아이템 관리〕〔3.전체 아이템〕〔0.뒤로 가기〕";
			System.out.println(menu);
			
			
			int sel = Rpg.intSel();
			
			if(sel == 1) {
				im.categoryManage();
			}else if(sel == 2) {
				im.itemManage();
			}else if(sel == 3) {
				im.printAllItem();
			}else if(sel == 0) {
				break;
			}
			
		}
	}

	private void manageGuildMember() {
		while(true) {
			String menu = "〔1.길드원 추가〕〔2.길드원 삭제〕〔3.전체 길드원〕〔4.정렬(lv기준)〕〔0.뒤로 가기〕";
			System.out.println(menu);
		
			int sel = Rpg.intSel();
			if(sel == 1) {
				gm.addMember();
			}else if(sel == 2) {
				gm.delMember();
			}else if(sel == 3) {
				gm.printAllMember();
			}else if(sel == 4) {
				gm.sort();
			}else if(sel == 0) {
				break;
			}
		}
	}

}
