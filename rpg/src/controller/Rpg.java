package controller;

import java.util.Scanner;

public class Rpg {
	public static Rpg instance = new Rpg();
	public static Scanner scan = new Scanner(System.in);
	public static int myMoney = 50000;
	public static int party = 0;
	public static int monsterHp = 100;
	
	private FileManager fm = FileManager.instance;
	private ItemManager im = ItemManager.instance;
	private CharacterManager cm = CharacterManager.instance;
	private RpgManager rm = RpgManager.instance;
	private MonsterManager mm = MonsterManager.instance;
	
	public void run() {
		fm.load(); //상점아이템 캐릭터 몬스터 불러오기
		while(true) {
			printMainMenu();
			if(selMainMenu()) {
				break;
			}
		}
	}

	private void printMainMenu() {
		System.out.println("내 돈 : "+Rpg.myMoney+"원");
		String menu = "[1.길드 관리] [2.상점] [3.인벤토리] [4.저장] [5.로드] [6.전투]\n[7.관리자] [0.종료]";
		System.out.println(menu);
	}
	
	private boolean selMainMenu() {
		int sel = intSel();
		
		if(sel == 1) {
			cm.ownGuildManage();
		}else if(sel == 2) {
			im.shop();
		}else if(sel == 3) {
			im.inventory();
		}else if(sel == 4) {
			fm.ownSave();
		}else if(sel == 5) {
			fm.ownLoad();
		}else if(sel == 6) {
			if(cm.MyMemberSize() < 4) {
				System.out.println("[전투 불가] 파티원이 부족합니다.");
			}else {
				battle();
			}
		}else if(sel == 7) {
			if(checkManager()) {
				rm.manage();
			}
			fm.save();
		}else if(sel == 0) {
			return true;
		}
		
		return false;
	}

	private void battle() {
		mm.pickBattleMonster();
		while(true) {
			System.out.println("3마리의 몬스터를 처치하세요!!");
			System.out.println("몬스터 hp : "+printHp()+"("+Rpg.monsterHp+")");
			mm.printBattleMonster();
			
			if(cm.battle()) {
				break;
			}
		}
	}

	private String printHp() {
		String hp ="";
		int cnt = Rpg.monsterHp/10;
		int x = Rpg.monsterHp%10;
		
		for(int i=0;i<cnt;i++) {
			hp += "■"+" ";
		}
		for(int i=cnt;i<10;i++) {
			if(x > 0 && i== cnt) {
				hp += "■"+" ";
			}else {
				hp += "□"+" ";
			}
		}
		return hp;
	}

	private boolean checkManager() {
		System.out.print("관리자 비밀번호를 입력하세요(0000): ");
		String pw = this.scan.next();
		
		if(pw.equals("0000")) {
			return true;
		}
		System.out.println("관리자만 입장 가능");
		return false;
	}

	public static int intSel() {
		while(true) {
			System.out.print("선택 : ");
			String temp = Rpg.scan.next();
			
			int sel = -1;
			try {
				sel = Integer.parseInt(temp);
				return sel;
			} catch (Exception e) {
				System.out.println("숫자를 입력하세요");
			}
		}
	}
}
