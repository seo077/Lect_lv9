package controller;

import java.util.Scanner;

public class Rpg {
	public static Rpg instance = new Rpg();
	public static Scanner scan = new Scanner(System.in);
	
	private FileManager fm = FileManager.instance;
	private ItemManager im = ItemManager.instance;
	private GuildManager gm = GuildManager.instance;
	private RpgManager rm = RpgManager.instance;
	
	public void run() {
		while(true) {
			printMainMenu();
			if(selMainMenu()) {
				break;
			}
		}
	}

	private void printMainMenu() {
		String menu = "��1.��� ��������2.��������3.�κ��丮����4.�������5.�ε��\n��6.�����ڣ���0.�����";
		System.out.println(menu);
	}
	
	private boolean selMainMenu() {
		int sel = intSel();
		
		if(sel == 1) {
			gm.ownGuildManage();
		}else if(sel == 2) {
			im.shop();
		}else if(sel == 3) {
			
		}else if(sel == 4) {
			fm.save();
		}else if(sel == 5) {
			fm.load();
		}else if(sel == 6) {
			if(checkManager()) {
				rm.manage();
			}
		}else if(sel == 0) {
			return true;
		}
		
		return false;
	}

	private boolean checkManager() {
		System.out.print("������ ��й�ȣ�� �Է��ϼ���(0000): ");
		String pw = this.scan.next();
		
		if(pw.equals("0000")) {
			return true;
		}
		System.out.println("�����ڸ� ���� ����");
		return false;
	}

	public static int intSel() {
		while(true) {
			System.out.print("���� : ");
			String temp = Rpg.scan.next();
			
			int sel = -1;
			try {
				sel = Integer.parseInt(temp);
				return sel;
			} catch (Exception e) {
				System.out.println("���ڸ� �Է��ϼ���");
			}
		}
	}
}
