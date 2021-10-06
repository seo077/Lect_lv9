package controller;

import java.util.Scanner;

public class Rpg {
	public static Rpg instance = new Rpg();
	public static Scanner scan = new Scanner(System.in);
	public static int myMoney = 50000;
	public static int party = 0;
	
	private FileManager fm = FileManager.instance;
	private ItemManager im = ItemManager.instance;
	private CharacterManager gm = CharacterManager.instance;
	private RpgManager rm = RpgManager.instance;
	
	public void run() {
		fm.load(); //상점아이템 캐릭터 불러오기
		while(true) {
			printMainMenu();
			if(selMainMenu()) {
				break;
			}
		}
	}

	private void printMainMenu() {
		System.out.println("내 돈 : "+Rpg.myMoney+"원");
		String menu = "[1.길드 관리] [2.상점] [3.인벤토리] [4.저장] [5.로드]\n[6.관리자] [0.종료]";
		System.out.println(menu);
	}
	
	private boolean selMainMenu() {
		int sel = intSel();
		
		if(sel == 1) {
			gm.ownGuildManage();
		}else if(sel == 2) {
			im.shop();
		}else if(sel == 3) {
			im.inventory();
		}else if(sel == 4) {
			fm.ownSave();
		}else if(sel == 5) {
			fm.ownLoad();
		}else if(sel == 6) {
			if(checkManager()) {
				rm.manage();
			}
			fm.save();
		}else if(sel == 0) {
			return true;
		}
		
		return false;
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
