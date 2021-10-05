package controller;

import java.util.ArrayList;

import models.User;

public class UserManager {
	public static UserManager instance = new UserManager();

	private ArrayList<User> users = new ArrayList<>();

	private CartManger cm = CartManger.instance;

	public void join() {
		String id = "";
		while (true) {
			System.out.print("���̵� : ");
			id = Shop.scan.next();
			if (!dup_check(id)) {
				break;
			} else
				System.out.println("[���̵� �ߺ�]");
		}
		System.out.print("��й�ȣ : ");
		String pw = Shop.scan.next();
		System.out.print("�̸� : ");
		String name = Shop.scan.next();

		this.users.add(new User(name, id, pw));
	}

	private boolean dup_check(String id) {
		int size = this.size();
		int check = -1;
		for (int i = 0; i < size; i++) {
			if (id.equals(this.users.get(i).getId())) {
				check = i;
			}
		}
		if (check == -1)
			return false;
		else
			return true;
	}

	public int size() {
		return this.users.size();
	}

	public void leave() {
		System.out.print("���̵� : ");
		String id = Shop.scan.next();
		System.out.print("��й�ȣ : ");
		String pw = Shop.scan.next();

		int check = idIdx(id);
		if (check != -1 && this.users.get(check).getPw().equals(pw)) {
			this.users.remove(check);
			cm.clearUserCart(id);
		} else {
			System.out.println("���̵�� ��й�ȣ�� Ȯ�����ּ���");
		}
	}

	public void managerLeave() {
		System.out.print("���̵� : ");
		String id = Shop.scan.next();

		int check = idIdx(id);
		if (check != -1) {
			this.users.remove(check);
			cm.clearUserCart(id);
		} else {
			System.out.println("�ش� ���̵�� �������� �ʽ��ϴ�.");
		}
	}

	public int idIdx(String id) {
		int check = -1;
		int size = this.size();
		for (int i = 0; i < size; i++) {
			if (id.equals(this.users.get(i).getId())) {
				check = i;
			}
		}
		return check;
	}

	public String getPw(int check) {
		return this.users.get(check).getPw();
	}

	@Override
	public String toString() {
		String data = "";
		int size = this.users.size();
		for (int i = 0; i < size; i++) {
			data += this.users.get(i).toString();
			if (i != size - 1) {
				data += "\n";
			}
		}
		return data;
	}

	public void clear() {
		this.users = new ArrayList<>();
	}

	public void userManage() {
		while (true) {
			String menu = "[1.��ü ����] [2.���� �߰�] [3.���� ����] [0.�ڷΰ���]";
			System.out.println(menu);
			String temp = Shop.scan.next();
			int sel = -1;
			try {
				sel = Integer.parseInt(temp);
			} catch (Exception e) {
				// TODO: handle exception
			}

			if (sel == 1) {
				System.out.println(this.toString());
			} else if (sel == 2) {
				this.join();
			} else if (sel == 3) {
				this.managerLeave();
			} else if (sel == 0) {
				break;
			}
		}
	}

	public String getUserId(int log) {
		return this.users.get(log).getId();
	}

	public void addUser(String[] temp) {
		this.users.add(new User(temp[0], temp[1], temp[2]));
	}

}
