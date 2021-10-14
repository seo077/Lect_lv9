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
		this.player = new Hero("용사", 100, 100, 5, 1, 1);
		
		this.enemy.add(new Zombie("그냥 좀비", 25, 25, 5, 1, 3));
		this.enemy.add(new Zombie("힘센 좀비", 45, 45, 10, 2, 6));
		this.enemy.add(new Zombie("정예 좀비", 65, 65, 15, 3, 9));
		this.enemy.add(new ZombieKing("좀비왕", 100, 100, 20, 4, 12, 50));
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
		System.out.printf("[현재 층 : %d]\n",player.getPos());
		System.out.println("[1]올라간다.");
		if(menu) {
			System.out.println("[2]체력을 회복한다.");
			System.out.println("[3]무기를 강화한다.");
		}
	}
	
	public int sel_int() {
		int sel = -1;
		while(true) {
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
