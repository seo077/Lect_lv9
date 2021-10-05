package controller;

import java.util.Scanner;

public class Rpg {
	public static Rpg instance = new Rpg();
	public static Scanner scan = new Scanner(System.in);
	
	private FileManager fm = FileManager.instance;
	private ItemManager im = ItemManager.instance;
	
	public void run() {
		while(true) {
			printMainMenu();
			if(selMainMenu()) {
				break;
			}
		}
	}

	private void printMainMenu() {
		String menu = "｛1.길드 관리｝｛2.상점｝｛3.인벤토리｝｛4.저장｝｛5.로드｝｛0.종료｝";
		System.out.println(menu);
	}
	
	private boolean selMainMenu() {
		System.out.print("메뉴 선택 : ");
		String temp = this.scan.next();
		
		int sel = intCheck(temp);
		
		if(sel == 1) {
			
		}else if(sel == 2) {
			
		}else if(sel == 3) {
			
		}else if(sel == 4) {
			
		}else if(sel == 5) {
			
		}else if(sel == 0) {
			return true;
		}
		
		return false;
	}

	public int intCheck(String temp) {
		int sel = -1;
		try {
			sel = Integer.parseInt(temp);
		} catch (Exception e) {
			System.out.println("숫자를 입력하세요");
		}
		return sel;
	}

}
