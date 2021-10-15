package controller;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import models.Hero;
import models.Unit;
import models.Zombie;
import models.ZombieKing;

public class Game {
	public static Scanner scan = new Scanner(System.in);
	public static Random ran = new Random();

	private static Game instance = new Game();

	private Game() {
	};

	public static Game getInstance() {
		return instance;
	}

	private int potion = 3;
	private Hero player;
	private ArrayList<Unit> enemy = new ArrayList<>();

	private void setGame() {
		this.player = new Hero("���", 100, 100, 5, 1, 1);

		this.enemy.add(new Zombie("�׳� ����", 25, 25, 5, 1, 3));
		this.enemy.add(new Zombie("���� ����", 45, 45, 10, 2, 6));
		this.enemy.add(new Zombie("���� ����", 65, 65, 15, 3, 9));
		this.enemy.add(new ZombieKing("�����", 100, 100, 20, 4, 12, 50));
	}

	public void run() {
		setGame();
		boolean menu = true;
		while (true) {
			printMenu(menu);
			menu = selMenu(menu);
			if (die()) {
				break;
			}
			if (win()) {
				System.out.println("������ �����ߴ�!");
				break;
			}
		}
	}

	private boolean win() {
		if (player.getPos() == 12 && player.getHp() >= 0) {
			return true;
		}
		return false;
	}

	private boolean die() {
		if (player.getHp() <= 0) {
			return true;
		}
		return false;
	}

	private void printMenu(boolean menu) {
		System.out.println("--------------");
		System.out.printf("[���� �� : %d]\n", player.getPos());
		System.out.println("[1]�ö󰣴�.");
		if (menu) {
			System.out.println("[2]ü���� ȸ���Ѵ�.");
			System.out.println("[3]���⸦ ��ȭ�Ѵ�.");
		}
		System.out.println("--------------");
	}

	public int sel_int() {
		int sel = -1;
		while (true) {
			System.out.print("���� : ");
			String temp = scan.next();

			try {
				sel = Integer.parseInt(temp);
				break;
			} catch (Exception e) {
			}
		}
		return sel;
	}

	private boolean selMenu(boolean menu) {
		while (true) {
			int sel = sel_int();
			if (sel == 1) {
				goUp();
				return true;
			} else if (sel == 2 && menu) {
				recoverHp();
			} else if (sel == 3 && menu) {
				consolidation();
			} else {
				continue;
			}
			return false;
		}
	}

	private void goUp() {
		player.setPos();
		int zombieExist = zombieExist();
		if (zombieExist == -1) {
			System.out.println("�ƹ� �ϵ� �Ͼ�� �ʾҴ�...");
		} else {
			System.out.println("���� ��Ÿ����.");
			if(this.enemy.get(zombieExist).getPos() != 12) {
				fightZombie(zombieExist);
			}else {
				fightZombieKing(zombieExist);
			}
		}
	}

	private void fightZombieKing(int zombieExist) {
		ZombieKing zom = (ZombieKing) this.enemy.get(zombieExist);
		while (true) {
			this.player.printInfo();
			System.out.println("===== vs =====");
			zom.printInfo();
			System.out.println("~~~~~~~~~~~~~~");
			System.out.println("[������ �ұ�?]");
			System.out.printf("1.����   2.����(%d�� ����)\n", this.potion); 
			int sel = this.sel_int();

			if (sel == 1) {
				System.out.println();
				int dam = (player.getAtt() - zom.getDef() )* (ran.nextInt(150)+100)/100;
				zom.damage(player.getName(), dam);
				System.out.println();
				
			} else if (sel == 2) {
				if(this.potion == 0) {
					System.out.println("������ �� �̻� �������� �ʽ��ϴ�.");
					continue;
				}
				this.potion-=1;
				player.resile();
			} else {
				System.out.println("�ٽ� �Է��ϼ���");
				continue;
			}
		
			System.out.println();
			int dam = (zom.getAtt() - player.getDef() )* (ran.nextInt(150)+100)/100;
			player.damage(zom.getName(), dam);
			System.out.println("\n");
			
			if(zom.getHp()<=0) {
				System.out.println("�¸��ߴ�!\n");
				player.eatItems(zom);
				break;
			}
			if(player.getHp()<=0) {
				System.out.println("����ߴ�...\n");
				break;
			}
		}
	}

	private void fightZombie(int zombieExist) {
		Zombie zom = (Zombie) this.enemy.get(zombieExist);
		while (true) {
			this.player.printInfo();
			System.out.println("===== vs =====");
			zom.printInfo();
			System.out.println("~~~~~~~~~~~~~~");
			System.out.println("[������ �ұ�?]");
			System.out.printf("1.����   2.����(%d�� ����)\n", this.potion); 
			int sel = this.sel_int();

			if (sel == 1) {
				System.out.println();
				int dam = (player.getAtt() - zom.getDef()) * (ran.nextInt(150)+100)/100;
				zom.damage(player.getName(), dam);
				System.out.println();
			} else if (sel == 2) {
				if(this.potion == 0) {
					System.out.println("������ �� �̻� �������� �ʽ��ϴ�.");
					continue;
				}
				this.potion-=1;
				player.resile();
			} else {
				System.out.println("�ٽ� �Է��ϼ���");
				continue;
			}
			
			if(zom.getHp()<=0) {
				System.out.println("�¸��ߴ�!\n");
				player.eatItems(zom);
				break;
			}
			
			System.out.println();
			int dam = (zom.getAtt() - player.getDef()) * (ran.nextInt(150)+100)/100;
			player.damage(zom.getName(), dam);
			System.out.println("\n");
			
			if(player.getHp()<=0) {
				System.out.println("����ߴ�...\n");
				break;
			}
		}

	}

	private int zombieExist() {
		int size = this.enemy.size();
		int check = -1;
		for (int i = 0; i < size; i++) {
			if (this.enemy.get(i).getPos() == this.player.getPos()) {
				check = i;
			}
		}
		return check;
	}

	private void recoverHp() {
		player.resile();
	}

	private void consolidation() {
		int ran = this.ran.nextInt(2);

		if (ran == 0) {
			double up = (double) player.getAtt() / (double) 10;
			up *= this.ran.nextInt(3) + 3;
			up = Math.round(up);
			System.out.printf("[�̸� : %s]�� ���ݷ��� %d��ŭ �����մϴ�.\n", player.getName(), (int) up);
			player.setAtt((int) up);
		} else {
			double up = (double) player.getDef() / (double) 10;
			up *= this.ran.nextInt(3) + 3;
			up = Math.round(up);
			System.out.printf("[�̸� : %s]�� ������ %d��ŭ �����մϴ�.\n", player.getName(), (int) up);
			player.setDef((int) up);
		}
	}
}
