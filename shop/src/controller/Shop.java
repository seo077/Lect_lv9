package controller;

import java.util.Scanner;

public class Shop {
	public static Shop instance = new Shop();
	public static Scanner scan = new Scanner(System.in);

	public static int log = -1;
	private UserManager um = UserManager.instance;
	private CartManger cm = CartManger.instance;
	private ItemManager im = ItemManager.instance;
	private FileManager fm = FileManager.instance;

	String shopName = "GREEN";

	public void run() {
		fm.load();
		while (true) {
			System.out.printf("===== %s =====\n", this.shopName);
			printMenu();
			if (selMenu()) {
				break;
			}
		}
		fm.save();
	}

	private boolean selMenu() {
		System.out.print("메뉴 선택 :");
		String temp = this.scan.next();
		int sel = -1;
		try {
			sel = Integer.parseInt(temp);
		} catch (Exception e) {
			// TODO: handle exception
		}

		if (sel == 1) {
			um.join();
		} else if (sel == 2) {
			um.leave();
		} else if (sel == 3) {
			if (login()) {
				userMenu();
			}
		} else if (sel == 4) {
			logout();
		} else if (sel == 100) {
			managerMenu();
		} else if (sel == 0) {
			return true;
		}

		return false;
	}

	private void userMenu() {
		while (true) {
			System.out.println("-------------------------");
			System.out.println(this.um.getUserId(Shop.log) + "님 로그인 중...");
			System.out.println("-------------------------");
			String menu = "[1.쇼핌] [2.장바구니] [0.뒤로가기]";
			System.out.println(menu);

			String temp = this.scan.next();
			int sel = intCheck(temp);

			if (sel == 1) {
				cm.shopping(this.um.getUserId(Shop.log));
			} else if (sel == 2) {
				cm.cart(this.um.getUserId(Shop.log));
			} else if (sel == 0) {
				break;
			}
		}
	}

	private void managerMenu() {
		while (true) {
			String menu = "[1.아이템 관리] [2.카테고리 관리] [3.장바구니 관리] [4.유저 관리] [0.뒤로가기]";
			System.out.println(menu);

			String temp = this.scan.next();
			int sel = intCheck(temp);

			if (sel == 1) {
				im.itemManage();
			} else if (sel == 2) {
				im.categoryManage();
			} else if (sel == 3) {
				cm.cartManage();
			} else if (sel == 4) {
				um.userManage();
			} else if (sel == 0) {
				break;
			}
		}
	}

	private int intCheck(String temp) {
		int sel = -1;
		try {
			sel = Integer.parseInt(temp);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return sel;
	}

	private void logout() {
		if (this.log == -1) {
			System.out.println("로그인 후 이용해주세요");
			return;
		}

		this.log = -1;
	}

	private boolean login() {
		if (this.log != -1) {
			System.out.println("로그아웃 후 이용해주세요");
			return false;
		}

		System.out.print("[로그인]아이디 : ");
		String id = Shop.scan.next();
		System.out.print("[로그인]비밀번호 : ");
		String pw = Shop.scan.next();

		int check = um.idIdx(id);
		if (check != -1 && pw.equals(um.getPw(check))) {
			this.log = check;
			return true;
		} else {
			System.out.println("아이디와 비밀번호를 확인해주세요");
			return false;
		}
	}

	private void printMenu() {
		String menu = "[1.가입] [2.탈퇴] [3.로그인] [4.로그아웃]\n" + "[100.관리자]  [0.종료]";
		System.out.println(menu);
	}
}
