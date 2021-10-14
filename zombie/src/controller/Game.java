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
	private Game() {};
	public static Game getInstance() {
		return instance;
	}
	
	private Hero player;
	private ArrayList<Unit>enemy = new ArrayList<>();
	
	private void setGame() {
		this.player = new Hero("���", 100, 100, 5, 1, 1);
		
		this.enemy.add(new Zombie("�׳� ����", 25, 25, 5, 1, 3));
		this.enemy.add(new Zombie("���� ����", 45, 45, 10, 2, 6));
		this.enemy.add(new Zombie("���� ����", 65, 65, 15, 3, 9));
		this.enemy.add(new ZombieKing("�����", 100, 100, 20, 4, 12, 50));
	}
	public void run(){
		setGame();
		boolean menu = true;
		while(true) {
			printMenu(menu);
			menu = selMenu();
		}
	}
	
	private void printMenu(boolean menu) {
		System.out.printf("[���� �� : %d]\n",player.getPos());
		System.out.println("[1]�ö󰣴�.");
		if(menu) {
			System.out.println("[2]ü���� ȸ���Ѵ�.");
			System.out.println("[3]���⸦ ��ȭ�Ѵ�.");
		}
	}
	
	public int sel_int() {
		int sel = -1;
		while(true) {
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
	
	private boolean selMenu() {
		while(true) {
			int sel = sel_int();
			if(sel == 1) {
				goUp();
				return true;
			}else if(sel == 2) {
				recoverHp();
			}else if(sel == 3) {
				consolidation();
			}else {
				continue;
			}
			return false;
		}
	}
	
	private void goUp() {
		// TODO Auto-generated method stub
		
	}
	
	private void recoverHp() {
		// TODO Auto-generated method stub
		
	}
	
	private void consolidation() {
		// TODO Auto-generated method stub
		
	}
}
