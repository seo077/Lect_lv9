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
		this.player = new Hero("용사", 100, 100, 5, 1, 1);

		this.enemy.add(new Zombie("그냥 좀비", 25, 25, 5, 1, 3));
		this.enemy.add(new Zombie("힘센 좀비", 45, 45, 10, 2, 6));
		this.enemy.add(new Zombie("정예 좀비", 65, 65, 15, 3, 9));
		this.enemy.add(new ZombieKing("좀비왕", 100, 100, 20, 4, 12, 50));
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
				System.out.println("생존에 성공했다!");
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
		System.out.printf("[현재 층 : %d]\n", player.getPos());
		System.out.println("[1]올라간다.");
		if (menu) {
			System.out.println("[2]체력을 회복한다.");
			System.out.println("[3]무기를 강화한다.");
		}
		System.out.println("--------------");
	}

	public int sel_int() {
		int sel = -1;
		while (true) {
			System.out.print("선택 : ");
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
			System.out.println("아무 일도 일어나지 않았다...");
		} else {
			System.out.println("좀비가 나타났다.");
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
			System.out.println("[무엇을 할까?]");
			System.out.printf("1.공격   2.물약(%d개 남음)\n", this.potion); 
			int sel = this.sel_int();

			if (sel == 1) {
				System.out.println();
				int dam = (player.getAtt() - zom.getDef() )* (ran.nextInt(150)+100)/100;
				zom.damage(player.getName(), dam);
				System.out.println();
				
			} else if (sel == 2) {
				if(this.potion == 0) {
					System.out.println("물약이 더 이상 존재하지 않습니다.");
					continue;
				}
				this.potion-=1;
				player.resile();
			} else {
				System.out.println("다시 입력하세요");
				continue;
			}
		
			System.out.println();
			int dam = (zom.getAtt() - player.getDef() )* (ran.nextInt(150)+100)/100;
			player.damage(zom.getName(), dam);
			System.out.println("\n");
			
			if(zom.getHp()<=0) {
				System.out.println("승리했다!\n");
				player.eatItems(zom);
				break;
			}
			if(player.getHp()<=0) {
				System.out.println("사망했다...\n");
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
			System.out.println("[무엇을 할까?]");
			System.out.printf("1.공격   2.물약(%d개 남음)\n", this.potion); 
			int sel = this.sel_int();

			if (sel == 1) {
				System.out.println();
				int dam = (player.getAtt() - zom.getDef()) * (ran.nextInt(150)+100)/100;
				zom.damage(player.getName(), dam);
				System.out.println();
			} else if (sel == 2) {
				if(this.potion == 0) {
					System.out.println("물약이 더 이상 존재하지 않습니다.");
					continue;
				}
				this.potion-=1;
				player.resile();
			} else {
				System.out.println("다시 입력하세요");
				continue;
			}
			
			if(zom.getHp()<=0) {
				System.out.println("승리했다!\n");
				player.eatItems(zom);
				break;
			}
			
			System.out.println();
			int dam = (zom.getAtt() - player.getDef()) * (ran.nextInt(150)+100)/100;
			player.damage(zom.getName(), dam);
			System.out.println("\n");
			
			if(player.getHp()<=0) {
				System.out.println("사망했다...\n");
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
			System.out.printf("[이름 : %s]의 공격력이 %d만큼 증가합니다.\n", player.getName(), (int) up);
			player.setAtt((int) up);
		} else {
			double up = (double) player.getDef() / (double) 10;
			up *= this.ran.nextInt(3) + 3;
			up = Math.round(up);
			System.out.printf("[이름 : %s]의 방어력이 %d만큼 증가합니다.\n", player.getName(), (int) up);
			player.setDef((int) up);
		}
	}
}
