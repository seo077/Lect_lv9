package controller;

public class RpgManager {
	public static RpgManager instance = new RpgManager();
	
	private ItemManager im = ItemManager.instance;
	private CharacterManager cm = CharacterManager.instance;
	private MonsterManager mm = MonsterManager.instance;

	public void manage() {
		while (true) {
			printManageMenu();
			if (selManageMenu()) {
				break;
			}
		}
	}

	private void printManageMenu() {
		String menu = "[1.길드 관리] [2.아이템 관리] [3.몬스터 관리] [0.뒤로가기]\n";
		System.out.print(menu);
	}

	private boolean selManageMenu() {
		int sel = Rpg.intSel();
		
		if(sel == 1) {
			manageGuildMember();
		}else if(sel == 2) {
			manageItem();
		}else if(sel == 3) {
			manageMonster();
		}else if(sel == 0) {
			return true;
		}
		return false;
	}

	private void manageMonster() {
		while(true) {
			String menu = "[1.몬스터 추가] [2.몬스터 삭제] [3.전체 몬스터] [0.뒤로 가기]";
			System.out.println(menu);
		
			int sel = Rpg.intSel();
			if(sel == 1) {
				mm.addMonster();
			}else if(sel == 2) {
				mm.delMonster();
			}else if(sel == 3) {
				mm.printAllMonster();
			}else if(sel == 0) {
				break;
			}
		}
	}

	private void manageItem() {
		while(true) {
			String menu = "[1.카테고리 관리] [2.아이템 관리] [3.전체 아이템] [0.뒤로 가기]";
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
			String menu = "[1.캐릭터 추가] [2.캐릭터 삭제] [3.전체 캐릭터] [4.레벨순 정렬] [0.뒤로 가기]";
			System.out.println(menu);
		
			int sel = Rpg.intSel();
			if(sel == 1) {
				cm.addCharacter();
			}else if(sel == 2) {
				cm.delCharacter();
			}else if(sel == 3) {
				cm.printAllCharacter();
			}else if(sel == 4) {
				cm.sort();
			}else if(sel == 0) {
				break;
			}
		}
	}

}
